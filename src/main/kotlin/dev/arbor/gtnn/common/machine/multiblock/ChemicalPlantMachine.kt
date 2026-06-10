package dev.arbor.gtnn.common.machine.multiblock

import com.gregtechceu.gtceu.api.GTValues
import com.gregtechceu.gtceu.api.GTValues.VNF
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine
import com.gregtechceu.gtceu.api.recipe.GTRecipe
import com.gregtechceu.gtceu.api.recipe.RecipeHelper
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted
import com.lowdragmc.lowdraglib.syncdata.annotation.RequireRerender
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder
import dev.arbor.gtnn.api.machine.feature.IGTPPMachine
import dev.arbor.gtnn.api.machine.feature.IGTPPRenderMachine
import dev.arbor.gtnn.data.block.NNBlockMaps
import dev.arbor.gtnn.data.pattern.NNPredicates
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.network.chat.Component
import net.minecraft.world.level.BlockAndTintGetter
import net.minecraft.world.level.block.state.BlockState
import kotlin.math.max
import kotlin.math.min

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
        val modified = super.getRealRecipe(recipe)
        if (casingTier > 0) {
            val copied = if (recipe == modified) modified.copy() else modified
            if (copied != null) {
                copied.duration = (copied.duration / (1 + coilLevel * 0.5)).toInt()
            }
            return copied
        }
        return modified
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
                    "gtnn.multiblock.chemical_plant.parallel_level", tubeTier * 2
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
        return min((100 - 20 * (tubeTier - 1)).toDouble(), 100.0).toInt()
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
            return APPEARANCE_MAP[casingTier]!!
        }
        return APPEARANCE_MAP[1]!!
    }

    companion object {
        private val APPEARANCE_MAP: Map<Int, BlockState> =
            NNBlockMaps.ALL_CP_CASINGS.keys
                .associate { it.tier to NNBlockMaps.ALL_CP_CASINGS[it]!!.get().defaultBlockState() }

        private val MANAGED_FIELD_HOLDER =
            ManagedFieldHolder(ChemicalPlantMachine::class.java, WorkableMultiblockMachine.MANAGED_FIELD_HOLDER)
    }
}
