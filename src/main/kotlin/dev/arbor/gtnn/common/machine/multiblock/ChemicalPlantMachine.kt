package dev.arbor.gtnn.common.machine.multiblock

import com.gregtechceu.gtceu.api.GTValues
import com.gregtechceu.gtceu.api.GTValues.VNF
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine
import com.gregtechceu.gtceu.api.recipe.GTRecipe
import com.gregtechceu.gtceu.api.recipe.RecipeHelper
import com.gregtechceu.gtceu.api.recipe.content.Content
import com.gregtechceu.gtceu.api.recipe.content.ContentModifier
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted
import com.lowdragmc.lowdraglib.syncdata.annotation.RequireRerender
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder
import dev.arbor.gtnn.api.machine.feature.IGTPPMachine
import dev.arbor.gtnn.api.machine.feature.IGTPPRenderMachine
import dev.arbor.gtnn.common.machine.multiblock.part.CatalystHatchPartMachine
import dev.arbor.gtnn.data.block.NNBlockMaps
import dev.arbor.gtnn.data.pattern.NNPredicates
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.ListTag
import net.minecraft.nbt.StringTag
import net.minecraft.nbt.Tag
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.BlockAndTintGetter
import net.minecraft.world.level.block.state.BlockState
import kotlin.math.ceil
import kotlin.math.max
import kotlin.math.sqrt

class ChemicalPlantMachine(holder: IMachineBlockEntity) : WorkableElectricMultiblockMachine(holder),
    IGTPPMachine, IGTPPRenderMachine {
    @Persisted
    @DescSynced
    @RequireRerender
    var casingTier = 0
    private var coilLevel = 0
    private var tubeTier = 0
    private var voltageTier = 0

    //////////////////////////////////////
    //***    Multiblock LifeCycle    ***//
    //////////////////////////////////////

    override fun onStructureFormed() {
        super.onStructureFormed()
        val context = multiblockState.matchContext
        this.coilLevel = NNPredicates.coilBlock.getTier(context)
        this.tubeTier = NNPredicates.pipeBlock.getTier(context)
        this.voltageTier = NNPredicates.machineCasing.getTier(context)
        this.casingTier = NNPredicates.plantCasings.getTier(context)
    }

    override fun onStructureInvalid() {
        super.onStructureInvalid()
        coilLevel = 0
        casingTier = 0
        tubeTier = 0
        voltageTier = 0
    }

    //////////////////////////////////////
    //******       NBT SAVE      *******//
    //////////////////////////////////////

    override fun getFieldHolder(): ManagedFieldHolder {
        return MANAGED_FIELD_HOLDER
    }

    //////////////////////////////////////
    //******     RECIPE LOGIC    *******//
    //////////////////////////////////////

    override fun getRealRecipe(recipe: GTRecipe): GTRecipe? {
        if (voltageTier < GTValues.UHV && RecipeHelper.getRecipeEUtTier(recipe) > voltageTier) {
            return null
        }
        val strippedRecipe = stripCatalystInputs(recipe)
        val modified = super.getRealRecipe(strippedRecipe)
        val catalystLimited = modified?.let { limitCatalystParallel(it) }
        if (casingTier > 0) {
            val copied = if (strippedRecipe == catalystLimited) catalystLimited.copy() else catalystLimited
            if (copied != null) {
                copied.duration = (copied.duration / (1 + coilLevel * 0.5)).toInt()
            }
            return copied
        }
        return catalystLimited
    }

    override fun beforeWorking(recipe: GTRecipe?): Boolean {
        if (!super.beforeWorking(recipe)) return false
        if (recipe == null) return true

        return consumeCatalysts(recipe)
    }


    //////////////////////////////////////
    // ***        Multiblock UI       ***//
    //////////////////////////////////////
    override fun addDisplayText(components: MutableList<Component?>) {
        super.addDisplayText(components)
        if (isFormed()) {
            components.add(
                Component.translatable(
                    "gtnn.multiblock.chemical_plant.heating_coil", coilLevel * 50
                )
            )
            components.add(
                Component.translatable(
                    "gtnn.multiblock.chemical_plant.parallel_level", maxParallel
                )
            )
            components.add(
                Component.translatable(
                    "gtnn.multiblock.chemical_plant.tier", VNF[voltageTier]
                )
            )
            components.add(
                Component.translatable(
                    "gtnn.multiblock.chemical_plant.chance", getChance()
                )
            )
        }
    }

    override val maxParallel: Int get() = max(tubeTier * 2, 1)

    //////////////////////////////////////
    // ***       Multiblock Data      ***//
    //////////////////////////////////////
    fun getChance(): Int {
        return (100 - 20 * (tubeTier - 1)).coerceIn(0, 100)
    }

    private fun stripCatalystInputs(recipe: GTRecipe): GTRecipe {
        val itemInputs = recipe.inputs[ItemRecipeCapability.CAP] ?: return recipe
        val catalystRequirements = mutableListOf<CatalystRequirement>()
        val nonCatalystInputs = mutableListOf<Content>()

        for (content in itemInputs) {
            val ingredient = ItemRecipeCapability.CAP.of(content.content)
            val catalystItems = getCatalystItems(ingredient)
            if (catalystItems.isEmpty()) {
                nonCatalystInputs.add(content)
            } else {
                catalystRequirements.add(CatalystRequirement(catalystItems, getIngredientAmount(ingredient)))
            }
        }

        if (catalystRequirements.isEmpty()) return recipe

        val copied = recipe.copy()
        copied.data = recipe.data.copy()
        if (nonCatalystInputs.isEmpty()) {
            copied.inputs.remove(ItemRecipeCapability.CAP)
        } else {
            copied.inputs[ItemRecipeCapability.CAP] = nonCatalystInputs
        }
        writeCatalystRequirements(copied, catalystRequirements)
        return copied
    }

    private fun limitCatalystParallel(recipe: GTRecipe): GTRecipe? {
        val catalystRequirements = getCatalystRequirements(recipe)
        if (catalystRequirements.isEmpty()) return recipe

        val catalystParallel = getMaxCatalystParallel(catalystRequirements)
        if (catalystParallel <= 0) return null
        if (catalystParallel >= recipe.parallels) return recipe

        val copied = recipe.copy(ContentModifier.multiplier(catalystParallel.toDouble() / recipe.parallels), false)
        copied.parallels = catalystParallel
        return copied
    }

    private fun consumeCatalysts(recipe: GTRecipe): Boolean {
        val catalystRequirements = getCatalystRequirements(recipe)
            .map { CatalystRequirement(it.items, it.amount * recipe.parallels) }
        if (catalystRequirements.isEmpty()) return true
        if (catalystRequirements.any { getCatalystDurability(it.items) < it.amount }) return false

        for (requirement in catalystRequirements) {
            var remaining = getCatalystDamage(requirement.amount)
            for (hatch in catalystHatches()) {
                remaining = hatch.consumeCatalyst(requirement.items, remaining)
                if (remaining == 0) break
            }
            if (remaining > 0) return false
        }
        return true
    }

    private fun getMaxCatalystParallel(catalystRequirements: List<CatalystRequirement>): Int {
        return catalystRequirements.minOf {
            getCatalystDurability(it.items) / it.amount
        }
    }

    private fun getCatalystDurability(items: Set<Item>): Int {
        return catalystHatches().sumOf { it.getCatalystDurability(items) }
    }

    private fun catalystHatches(): List<CatalystHatchPartMachine> {
        return parts.filterIsInstance<CatalystHatchPartMachine>()
    }

    private fun getCatalystItems(ingredient: Ingredient): Set<Item> {
        return ingredient.items
            .filter { it.`is`(CATALYST_TAG) }
            .map { it.item }
            .toSet()
    }

    private fun getIngredientAmount(ingredient: Ingredient): Int {
        return if (ingredient is SizedIngredient) {
            ingredient.amount
        } else {
            ingredient.items.firstOrNull()?.count ?: 1
        }.coerceAtLeast(1)
    }

    private fun writeCatalystRequirements(recipe: GTRecipe, catalystRequirements: List<CatalystRequirement>) {
        val list = ListTag()
        for (requirement in catalystRequirements) {
            val tag = CompoundTag()
            tag.putInt(CATALYST_COUNT_KEY, requirement.amount)
            val items = ListTag()
            requirement.items
                .map { BuiltInRegistries.ITEM.getKey(it).toString() }
                .sorted()
                .forEach { items.add(StringTag.valueOf(it)) }
            tag.put(CATALYST_ITEMS_KEY, items)
            list.add(tag)
        }
        recipe.data.put(CATALYST_REQUIREMENTS_KEY, list)
    }

    private fun getCatalystRequirements(recipe: GTRecipe): List<CatalystRequirement> {
        val list = recipe.data.getList(CATALYST_REQUIREMENTS_KEY, Tag.TAG_COMPOUND.toInt())
        if (list.isEmpty()) return emptyList()

        val requirements = mutableListOf<CatalystRequirement>()
        for (i in 0 until list.size) {
            val tag = list.getCompound(i)
            val itemTags = tag.getList(CATALYST_ITEMS_KEY, Tag.TAG_STRING.toInt())
            val items = mutableSetOf<Item>()
            for (j in 0 until itemTags.size) {
                items.add(BuiltInRegistries.ITEM.get(ResourceLocation(itemTags.getString(j))))
            }
            if (items.isNotEmpty()) {
                requirements.add(CatalystRequirement(items, tag.getInt(CATALYST_COUNT_KEY).coerceAtLeast(1)))
            }
        }
        return requirements
    }

    private fun getCatalystDamage(amount: Int): Int {
        val chance = getChance() / 100.0
        if (chance >= 1.0) return amount
        if (chance <= 0.0) return 0

        val average = amount * chance
        val variance = amount * chance * (1 - chance)
        return ceil(sqrt(variance) * GTValues.RNG.nextGaussian() + average).toInt().coerceIn(0, amount)
    }

    override fun getPartAppearance(
        part: IMultiPart, side: Direction, sourceState: BlockState, sourcePos: BlockPos
    ): BlockState? {
        val appearanceBlock: BlockState? = APPEARANCE_MAP[casingTier]
        return appearanceBlock ?: super<WorkableElectricMultiblockMachine>.getPartAppearance(part, side, sourceState, sourcePos)
    }

    override fun getBlockAppearance(
        state: BlockState,
        level: BlockAndTintGetter,
        pos: BlockPos,
        side: Direction,
        sourceState: BlockState,
        sourcePos: BlockPos
    ): BlockState {
        val appearanceBlock: BlockState = getAppearance()
        return appearanceBlock
    }

    override fun getAppearance(): BlockState {
        if (isFormed()) {
            return APPEARANCE_MAP[casingTier] ?: APPEARANCE_MAP[1]!!
        }
        return APPEARANCE_MAP[1]!!
    }

    companion object {
        private val APPEARANCE_MAP: Map<Int, BlockState> =
            NNBlockMaps.ALL_CP_CASINGS.keys
                .associate { it.tier to NNBlockMaps.ALL_CP_CASINGS[it]!!.get().defaultBlockState() }

        private val MANAGED_FIELD_HOLDER =
            ManagedFieldHolder(ChemicalPlantMachine::class.java, WorkableMultiblockMachine.MANAGED_FIELD_HOLDER)

        private val CATALYST_TAG = TagKey.create(Registries.ITEM, ResourceLocation("forge", "catalyst"))
        private const val CATALYST_REQUIREMENTS_KEY = "GTNN.CatalystRequirements"
        private const val CATALYST_ITEMS_KEY = "Items"
        private const val CATALYST_COUNT_KEY = "Count"
    }

    private data class CatalystRequirement(val items: Set<Item>, val amount: Int)
}
