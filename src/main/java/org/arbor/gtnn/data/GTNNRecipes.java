package org.arbor.gtnn.data;

import net.minecraft.data.recipes.FinishedRecipe;
import org.arbor.gtnn.GTNNIntegration;
import org.arbor.gtnn.api.recipe.NeutronActivatorCondition;
import org.arbor.gtnn.api.recipe.PlantCasingCondition;
import org.arbor.gtnn.block.PlantCasingBlock;
import org.arbor.gtnn.config.ConfigHandler;
import org.arbor.gtnn.data.recipes.*;

import java.util.function.Consumer;

@SuppressWarnings("unused")
public class GTNNRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        DefaultRecipes.init(provider);
        NaquadahReactor.init(provider);
        RocketFuel.init(provider);
        if (ConfigHandler.INSTANCE.Server.enableHarderPlatinumLine) PlatinumLine.init(provider);
        if (ConfigHandler.INSTANCE.Server.enableHarderNaquadahLine) NaquadahLine.init(provider);
        if (GTNNIntegration.isAdAstraLoaded()) AdAstraRecipes.init(provider);
    }

    public static int dur(double seconds) {
        return (int) seconds * 20;
    }


    public static NeutronActivatorCondition setNA(int max, int min){
        return new NeutronActivatorCondition(max, min);
    }
    public static PlantCasingCondition setPlantCasing(int tier) {
        return new PlantCasingCondition(PlantCasingBlock.getByTier(tier));
    }

    public static PlantCasingCondition setPlantCasing(PlantCasingBlock plantCasing) {
        return new PlantCasingCondition(plantCasing);
    }

    public static PlantCasingCondition setPlantCasing(String name) {
        PlantCasingBlock plantCasing = PlantCasingBlock.getByName(name);
        return new PlantCasingCondition(plantCasing);
    }
}
