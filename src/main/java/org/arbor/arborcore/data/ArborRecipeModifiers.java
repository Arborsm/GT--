package org.arbor.arborcore.data;

import com.gregtechceu.gtceu.api.capability.recipe.EURecipeCapability;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import org.arbor.arborcore.api.machine.multiblock.ChemicalPlant;
import org.arbor.arborcore.api.recipe.PlantCasingCondition;

import javax.annotation.Nonnull;
import java.util.List;

public class ArborRecipeModifiers {
    public static GTRecipe chemicalPlantRecipe(MetaMachine machine, @Nonnull GTRecipe recipe) {
        if (machine instanceof ChemicalPlant chemicalPlant) {
            if (RecipeHelper.getRecipeEUtTier(recipe) > chemicalPlant.getMachineCasingTier() + 1) {
                return null;
            }
            if (recipe.conditions.get(0) instanceof PlantCasingCondition plantCasingCondition) {
                if (plantCasingCondition.getPlantCasing().getTier() > chemicalPlant.getPlantCasingTier()) {
                    return null;
                }
            }

            var maxParallel = 1 + 2 * chemicalPlant.getPipeTier();
            var parallelLimit = Math.min(maxParallel, (int) (chemicalPlant.getMaxVoltage()));
            var result = GTRecipeModifiers.accurateParallel(machine, recipe, parallelLimit, false);
            recipe = result.getA() == recipe ? result.getA().copy() : result.getA();
            int parallelValue = result.getB();
            recipe.duration = Math.max(1, 256 * parallelValue / maxParallel);
            recipe.tickInputs.put(EURecipeCapability.CAP, List.of(new Content((long) parallelValue, 1.0f, 0.0f, null, null)));

            GTRecipe finalRecipe = recipe;
            return RecipeHelper.applyOverclock(new OverclockingLogic((recipe1, recipeEUt, maxVoltage, duration, amountOC) -> {
                var pair = OverclockingLogic.NON_PERFECT_OVERCLOCK.getLogic().runOverclockingLogic(finalRecipe, recipeEUt, maxVoltage, duration, amountOC);
                if (chemicalPlant.getCoilTier() > 0) {
                    var eu = pair.firstLong() * (1 - chemicalPlant.getCoilTier() * 0.5);
                    pair.first((long) Math.max(1, eu));
                }
                return pair;
            }), recipe, chemicalPlant.getMaxVoltage());
        }
        return null;
    }
}
