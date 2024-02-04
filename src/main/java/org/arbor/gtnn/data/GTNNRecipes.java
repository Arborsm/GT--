package org.arbor.gtnn.data;

import net.minecraft.data.recipes.FinishedRecipe;
import org.arbor.gtnn.api.recipe.NeutronActivatorCondition;
import org.arbor.gtnn.api.recipe.PlantCasingCondition;
import org.arbor.gtnn.block.PlantCasingBlock;
import org.arbor.gtnn.data.recipes.PlatinumLine;
import org.arbor.gtnn.data.recipes.RocketFuel;

import java.util.function.Consumer;

@SuppressWarnings("unused")
public class GTNNRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        RocketFuel.init(provider);
        PlatinumLine.init(provider);
    }

    public static int dur(int seconds) {
        return seconds * 20;
    }


    public static NeutronActivatorCondition setNA(int max, int min){
        return new NeutronActivatorCondition(max, min);
    }

    public static PlantCasingCondition setPlantCasing(PlantCasingBlock plantCasing) {
        return new PlantCasingCondition(plantCasing);
    }

    public static PlantCasingCondition setPlantCasing(String name) {
        PlantCasingBlock plantCasing = PlantCasingBlock.getByName(name);
        return new PlantCasingCondition(plantCasing);
    }
}
