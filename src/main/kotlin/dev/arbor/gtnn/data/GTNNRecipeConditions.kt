package dev.arbor.gtnn.data

import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType
import com.gregtechceu.gtceu.api.registry.GTRegistries
import dev.arbor.gtnn.common.recipe.NeutronActivatorCondition
import dev.arbor.gtnn.common.recipe.PlantCasingCondition
import dev.arbor.gtnn.common.recipe.TierCasingCondition

object GTNNRecipeConditions {
    @JvmStatic
    val PLANT_CASING: RecipeConditionType<PlantCasingCondition> = GTRegistries.RECIPE_CONDITIONS.register(
        "plant_casing_condition",
        RecipeConditionType(::PlantCasingCondition, PlantCasingCondition.CODEC)
    )

    @JvmStatic
    val NEUTRON_ACTIVATOR: RecipeConditionType<NeutronActivatorCondition> = GTRegistries.RECIPE_CONDITIONS.register(
        "neutron_activator_condition",
        RecipeConditionType(::NeutronActivatorCondition, NeutronActivatorCondition.CODEC)
    )

    @JvmStatic
    val TIER_CASING: RecipeConditionType<TierCasingCondition> = GTRegistries.RECIPE_CONDITIONS.register(
        "tier_casing_condition",
        RecipeConditionType(::TierCasingCondition, TierCasingCondition.CODEC)
    )

    fun init() {
    }
}
