package dev.arbor.gtnn.common.recipe

import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic
import com.gregtechceu.gtceu.api.recipe.GTRecipe
import com.gregtechceu.gtceu.api.recipe.RecipeCondition
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.arbor.gtnn.common.machine.multiblock.ChemicalPlantMachine
import dev.arbor.gtnn.data.GTNNRecipeConditions.PLANT_CASING
import net.minecraft.network.chat.Component
import net.minecraft.util.Mth

class PlantCasingCondition : RecipeCondition<PlantCasingCondition> {
    private var tier = 0

    constructor()

    constructor(tier: Int) {
        this.tier = Mth.clamp(tier, 1, 6)
    }

    constructor(isReverse: Boolean, tier: Int) : super(isReverse) {
        this.tier = Mth.clamp(tier, 1, 6)
    }

    override fun getType(): RecipeConditionType<PlantCasingCondition> {
        return PLANT_CASING
    }

    override fun getTooltips(): Component {
        return Component.translatable(
            "gtnn.recipe.condition.plant_casing.tooltip",
            tier, Component.translatable(CASING_TIERS[tier]!!)
        )
    }

    override fun testCondition(gtRecipe: GTRecipe, recipeLogic: RecipeLogic): Boolean {
        val machine = recipeLogic.machine
        if (machine is ChemicalPlantMachine) {
            return machine.casingTier >= tier
        }
        return false
    }

    override fun createTemplate(): PlantCasingCondition {
        return PlantCasingCondition()
    }

    companion object {
        val CODEC: Codec<PlantCasingCondition> = RecordCodecBuilder
            .create { instance: RecordCodecBuilder.Instance<PlantCasingCondition> ->
                isReverse(instance)
                    .and(Codec.INT.fieldOf("plantCasing").forGetter{ it.tier })
                    .apply(instance, ::PlantCasingCondition)
            }

        const val BRONZE: Int = 1
        const val STEEL: Int = 2
        const val ALUMINIUM: Int = 3
        const val STAINLESS_STEEL: Int = 4
        const val TITANIUM: Int = 5
        const val TUNGSTEN_STEEL: Int = 6

        var CASING_TIERS: Map<Int, String> = mapOf(
            BRONZE to "gtnn.recipe.condition.plant_casing.tier.bronze",
            STEEL to "gtnn.recipe.condition.plant_casing.tier.steel",
            ALUMINIUM to "gtnn.recipe.condition.plant_casing.tier.aluminium",
            STAINLESS_STEEL to "gtnn.recipe.condition.plant_casing.tier.stainless_steel",
            TITANIUM to "gtnn.recipe.condition.plant_casing.tier.titanium",
            TUNGSTEN_STEEL to "gtnn.recipe.condition.plant_casing.tier.tungsten_steel"
        )
    }
}
