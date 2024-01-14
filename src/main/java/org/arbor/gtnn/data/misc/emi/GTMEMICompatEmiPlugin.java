package org.arbor.gtnn.data.misc.emi;

import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SortedSetMultimap;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.stack.EmiIngredient;
import org.jetbrains.annotations.Contract;

import java.util.Comparator;
import java.util.List;

@SuppressWarnings("all")
@EmiEntrypoint
public final class GTMEMICompatEmiPlugin implements EmiPlugin {
    public static final SortedSetMultimap<GTRecipeType, MachineDefinition> RECIPE_MACHINES = MultimapBuilder.hashKeys().treeSetValues(Comparator.comparingInt(MachineDefinition::getTier)).build();
    static {
        for (MachineDefinition machine : GTRegistries.MACHINES) {
            GTRecipeType[] recipeTypes = machine.getRecipeTypes();
            if (recipeTypes == null) continue;
            for (GTRecipeType recipeType : recipeTypes) {
                RECIPE_MACHINES.put(recipeType, machine);
            }
        }
    }
    /**
     because of the search mechanism of EMI, we have to pack all workstations into one EmiIngredient
     @param catalysts see {@link EmiRecipe#getCatalysts()}
     */
    @Contract(mutates = "param")
    public static void normalizeCatalysts(List<EmiIngredient> catalysts) {
        EmiIngredient catalyst = EmiIngredient.of(catalysts);
        catalysts.clear();
        catalysts.add(catalyst);
    }
    @Override
    public void register(EmiRegistry registry) {
        GTEmiOreProcessingV2.register(registry);
    }
}
