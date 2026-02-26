package dev.arbor.gtnn.common.recipe

import com.gregtechceu.gtceu.api.machine.MetaMachine
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic
import com.gregtechceu.gtceu.api.recipe.GTRecipe
import com.gregtechceu.gtceu.api.recipe.RecipeCondition
import com.gregtechceu.gtceu.api.recipe.condition.RecipeConditionType
import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.arbor.gtnn.common.machine.multiblock.NeutronActivatorMachine.Companion.checkNeutronActivatorCondition
import dev.arbor.gtnn.data.GTNNRecipeConditions
import net.minecraft.network.chat.Component

class NeutronActivatorCondition(max: Int, min: Int) : RecipeCondition<NeutronActivatorCondition>() {
    companion object {
        val CODEC: Codec<NeutronActivatorCondition> = RecordCodecBuilder
            .create { instance: RecordCodecBuilder.Instance<NeutronActivatorCondition> ->
                isReverse(instance)
                    .and(Codec.INT.fieldOf("evRange").forGetter { it.evRange })
                    .apply(instance, ::NeutronActivatorCondition)
            }
    }

    var evRange: Int = 0

    constructor() : this(0, 0)

    constructor(evRange: Int) : this() {
        this.evRange = evRange
    }

    constructor(isReverse: Boolean, evRange: Int) : this(evRange) {
        super.isReverse = isReverse
    }

    init {
        this.evRange = max * 10000 + min
    }

    override fun getType(): RecipeConditionType<NeutronActivatorCondition> {
        return GTNNRecipeConditions.NEUTRON_ACTIVATOR
    }

    override fun getTooltips(): Component {
        val max = evRange % 10000
        val min = evRange / 10000
        return Component.translatable("gtnn.recipe.condition.neutron_activator_condition_tooltip", max, min)
    }

    override fun testCondition(gtRecipe: GTRecipe, recipeLogic: RecipeLogic): Boolean {
        return checkNeutronActivatorCondition((recipeLogic.machine as MetaMachine), gtRecipe)
    }

    override fun createTemplate(): NeutronActivatorCondition {
        return NeutronActivatorCondition()
    }
}
