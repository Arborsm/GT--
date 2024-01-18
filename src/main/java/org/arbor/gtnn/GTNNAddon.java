package org.arbor.gtnn;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.addon.events.KJSRecipeKeyEvent;
import com.gregtechceu.gtceu.api.addon.events.MaterialCasingCollectionEvent;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import org.arbor.gtnn.api.recipe.NeutronActivatorCondition;
import org.arbor.gtnn.api.recipe.PlantCasingCondition;
import org.arbor.gtnn.data.*;
import org.arbor.gtnn.data.misc.adastra.AdAstraAddon;

import java.util.function.Consumer;

@GTAddon
public class GTNNAddon implements IGTAddon {
    @Override
    public GTRegistrate getRegistrate() {
        return GTNNRegistries.REGISTRATE;
    }

    @Override
    public void initializeAddon() {
        org.arbor.gtnn.GTNN.LOGGER.info("GTNN Loaded!");
    }

    @Override
    public String addonModId() {
        return GTNN.MODID;
    }

    @Override
    public void registerTagPrefixes() {
        if (GTNN.isAdAstraLoaded()) {
            AdAstraAddon.init();
        }
    }

    @Override
    public void registerElements() {
        GTNNElement.init();
    }

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        GTNNRecipes.init(provider);
    }

    @Override
    public void removeRecipes(Consumer<ResourceLocation> consumer) {
        GTNNRecipes.remove(consumer);
    }

    @Override
    public void registerOreVeins() {
        GTNNOres.init();
    }

    @Override
    public void registerFluidVeins() {
        IGTAddon.super.registerFluidVeins();
    }

    @Override
    public void registerSounds() {
        IGTAddon.super.registerSounds();
    }

    @Override
    public void registerCovers() {
        IGTAddon.super.registerCovers();
    }

    @Override
    public void registerRecipeCapabilities() {
        IGTAddon.super.registerRecipeCapabilities();
    }

    @Override
    public void registerRecipeConditions() {
        GTRegistries.RECIPE_CONDITIONS.register(PlantCasingCondition.INSTANCE.getType(), PlantCasingCondition.class);
        GTRegistries.RECIPE_CONDITIONS.register(NeutronActivatorCondition.INSTANCE.getType(), NeutronActivatorCondition.class);
    }

    @Override
    public void registerWorldgenLayers() {
        if (GTNN.isAdAstraLoaded()){
            GTNNWorld.GTNNWorldGenLayers.init();
        }
    }

    @Override
    public void registerVeinGenerators() {
        IGTAddon.super.registerVeinGenerators();
    }

    @Override
    public void registerIndicatorGenerators() {
        IGTAddon.super.registerIndicatorGenerators();
    }

    @Override
    public void collectMaterialCasings(MaterialCasingCollectionEvent event) {
        GTNNCasingBlocks.init();
    }

    @Override
    public void registerRecipeKeys(KJSRecipeKeyEvent event) {
        IGTAddon.super.registerRecipeKeys(event);
    }

    @Override
    public boolean requiresHighTier() {
        return IGTAddon.super.requiresHighTier();
    }
}
