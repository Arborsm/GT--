package org.arbor.gtnn.data;

import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
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

    public static void remove(Consumer<ResourceLocation> consumer) {
        // not work fk
        // consumer.accept(GTCEu.id("alloy_blast_smelter/sodium_pyrosulfate"));
        // consumer.accept(GTCEu.id("chemical_reactor/osmium_tetroxide_separation"));
        // consumer.accept(GTCEu.id("extractor/extract_osmium_tetroxide_dust"));
        // consumer.accept(GTCEu.id("large_chemical_reactor/osmium_tetroxide_separation"));
    }

    public static int dur(int seconds) {
        return seconds * 20;
    }


    public static NeutronActivatorCondition setNA(int max, int min){
        return new NeutronActivatorCondition(max, min);
    }

    public static PlantCasingCondition setPlantCasing(PlantCasingBlock.PlantCasing plantCasing) {
        return new PlantCasingCondition(plantCasing);
    }

    public static PlantCasingCondition setPlantCasing(String name) {
        PlantCasingBlock.PlantCasing plantCasing = PlantCasingBlock.PlantCasing.getByName(name);
        return new PlantCasingCondition(plantCasing);
    }


}
