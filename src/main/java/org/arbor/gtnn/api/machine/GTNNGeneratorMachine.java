package org.arbor.gtnn.api.machine;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.SimpleGeneratorMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import it.unimi.dsi.fastutil.ints.Int2LongFunction;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

@Getter
public class GTNNGeneratorMachine extends SimpleGeneratorMachine {
    private final int efficiency;

    public GTNNGeneratorMachine(IMachineBlockEntity holder, int tier, String name, Int2LongFunction tankScalingFunction, Object... args) {
        super(holder, tier, tankScalingFunction, args);
        this.efficiency = getEfficiency(tier, name);
    }

    public static int getEfficiency(int tier, String name) {
        return switch (name) {
            case "naquadah_reactor" -> tier == 4 ? 80 : (tier - 5) * 50 + 100;
            case "rocket_engine" -> 80 - (tier - 4) * 10;
            default -> tier * 20 + 100;
        };
    }

    @Nullable
    public static GTRecipe nonParallel(MetaMachine machine, @Nonnull GTRecipe recipe) {
        if (machine instanceof GTNNGeneratorMachine generator) {
            var EUt = RecipeHelper.getOutputEUt(recipe);
            var recipeModifier = recipe.copy();
            RecipeHelper.setOutputEUt(recipeModifier, EUt * (generator.efficiency / 100));
            return recipeModifier;
        }
        return null;
    }

    @Nullable
    public static GTRecipe parallel(MetaMachine machine, @Nonnull GTRecipe recipe) {
        GTRecipe recipeModifier = nonParallel(machine, recipe);
        assert recipeModifier != null;
        return SimpleGeneratorMachine.recipeModifier(machine, recipeModifier);
    }
}
