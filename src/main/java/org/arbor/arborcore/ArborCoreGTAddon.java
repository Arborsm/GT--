package org.arbor.arborcore;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.addon.events.KJSRecipeKeyEvent;
import com.gregtechceu.gtceu.api.addon.events.MaterialCasingCollectionEvent;
import com.lowdragmc.lowdraglib.LDLib;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import org.arbor.arborcore.data.ArborElement;
import org.arbor.arborcore.data.ArborMaterials;
import org.arbor.arborcore.data.ArborRecipes;
import org.arbor.arborcore.data.ArborRecipesTypes;
import org.arbor.arborcore.data.misc.AdAstraTagPrefix;
import org.arbor.arborcore.data.misc.MetaTileEntityLoader;
import org.arbor.arborcore.init.CommonProxy;

import java.util.function.Consumer;

@GTAddon
public class ArborCoreGTAddon implements IGTAddon {
    @Override
    public void initializeAddon() {
        ArborCore.LOGGER.info("Arbor's GT Addon Loaded!");
        CommonProxy.init();
    }

    @Override
    public String addonModId() {
        return ArborCore.MODID;
    }

    @Override
    public void registerTagPrefixes() {
        IGTAddon.super.registerTagPrefixes();
        if (LDLib.isModLoaded("ad_astra")) {
            AdAstraTagPrefix.init();
        }
    }

    @Override
    public void registerElements() {
        IGTAddon.super.registerElements();
        ArborElement.init();
    }

    @Override
    public void registerMaterials() {
        IGTAddon.super.registerMaterials();
        ArborMaterials.init();
    }

    @Override
    public void addRecipes(Consumer<FinishedRecipe> provider) {
        ArborRecipesTypes.init();
        ArborRecipes.init(provider);
        IGTAddon.super.addRecipes(provider);
        MetaTileEntityLoader.init(provider);
    }

    @Override
    public void removeRecipes(Consumer<ResourceLocation> consumer) {
        IGTAddon.super.removeRecipes(consumer);
        ArborRecipes.remove(consumer);
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
    public void registerWorldgenLayers() {
        IGTAddon.super.registerWorldgenLayers();
    }

    @Override
    public void registerVeinGenerators() {
        IGTAddon.super.registerVeinGenerators();
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
