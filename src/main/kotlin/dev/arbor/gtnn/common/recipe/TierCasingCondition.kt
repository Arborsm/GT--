package dev.arbor.gtnn.common.recipe

import com.gregtechceu.gtceu.api.GTValues
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic
import com.gregtechceu.gtceu.api.recipe.GTRecipe
import com.gregtechceu.gtceu.api.recipe.RecipeCondition
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.arbor.gtnn.api.machine.feature.ICasingMachine
import dev.arbor.gtnn.data.GTNNRecipeConditions
import net.minecraft.network.chat.Component
import net.minecraft.util.Mth

class TierCasingCondition(tier: Int) : RecipeCondition<TierCasingCondition>() {
    var tier = tier
        set(value) {
            field = Mth.clamp(value, 0, 14)
        }

    constructor() : this(0)

    constructor(isReverse: Boolean, tier: Int) : this(tier) {
        super.isReverse = isReverse
    }

    override fun getType(): RecipeConditionType<TierCasingCondition> {
        return GTNNRecipeConditions.TIER_CASING
    }

    override fun getTooltips(): Component {
        return Component.translatable("gtnn.recipe.condition.tier_casing.desc", GTValues.VN[tier])
    }

    override fun testCondition(gtRecipe: GTRecipe, recipeLogic: RecipeLogic): Boolean {
        val machine = recipeLogic.machine
        if (machine is ICasingMachine) {
            return machine.casingTier >= tier
        }
        return false
    }

    override fun createTemplate(): TierCasingCondition {
        return TierCasingCondition()
    }

    companion object {
        val CODEC: Codec<TierCasingCondition> = RecordCodecBuilder
            .create { instance: RecordCodecBuilder.Instance<TierCasingCondition> ->
                isReverse(instance)
                    .and(Codec.INT.fieldOf("tierCasing").forGetter { it.tier })
                    .apply(instance, ::TierCasingCondition)
            }
    }
}
