package org.arbor.gtnn;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.addon.events.KJSRecipeKeyEvent;
import com.gregtechceu.gtceu.api.addon.events.MaterialCasingCollectionEvent;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.api.registry.registrate.GTRegistrate;
import com.lowdragmc.lowdraglib.LDLib;
import com.lowdragmc.lowdraglib.Platform;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import org.arbor.gtnn.api.recipe.NeutronActivatorCondition;
import org.arbor.gtnn.api.recipe.PlantCasingCondition;
import org.arbor.gtnn.api.registry.GTNNRegistries;
import org.arbor.gtnn.data.GTNNElement;
import org.arbor.gtnn.data.GTNNRecipes;
import org.arbor.gtnn.data.GTNNRecipesTypes;
import org.arbor.gtnn.data.misc.adastra.AdAstraAddon;
import org.arbor.gtnn.init.AddonProxy;

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
        if (!Platform.isDatagen()){
            AddonProxy.init();
        }
    }

    @Override
    public String addonModId() {
        return GTNN.MODID;
    }

    @Override
    public void registerTagPrefixes() {
        IGTAddon.super.registerTagPrefixes();
        if (LDLib.isModLoaded("ad_astra")) {
            AdAstraAddon.init();
        }
    }

    @Override
    public void registerElements() {
        IGTAddon.super.registerElements();
        GTNNElement.init();
    }

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        GTNNRecipesTypes.init();
        GTNNRecipes.init(provider);
        IGTAddon.super.addRecipes(provider);
    }

    @Override
    public void removeRecipes(Consumer<ResourceLocation> consumer) {
        IGTAddon.super.removeRecipes(consumer);
        GTNNRecipes.remove(consumer);
    }

    @Override
    public void registerOreVeins() {
        IGTAddon.super.registerOreVeins();
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
        IGTAddon.super.registerRecipeConditions();
        GTRegistries.RECIPE_CONDITIONS.register(PlantCasingCondition.INSTANCE.getType(), PlantCasingCondition.class);
        GTRegistries.RECIPE_CONDITIONS.register(NeutronActivatorCondition.INSTANCE.getType(), NeutronActivatorCondition.class);
    }

    @Override
    public void registerWorldgenLayers() {
        IGTAddon.super.registerWorldgenLayers();
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
        IGTAddon.super.collectMaterialCasings(event);
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
