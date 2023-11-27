package org.arbor.arborcore.data.misc;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

public class MetaTileEntityLoader {
    public static void init(Consumer<FinishedRecipe> provider) {
        // VanillaRecipeHelper.addShapedRecipe(provider, true, ArborCore.id("chemical_plant"), ArborMachines.CHEMICAL_PLANT.asStack(), "FFF", "CMC", "WCW", 'M', GTBlocks.CASING_INVAR_HEATPROOF.asStack(), 'F', Blocks.FURNACE.asItem(), 'C', CustomTags.LV_CIRCUITS, 'W', new UnificationEntry(TagPrefix.cableGtSingle, GTMaterials.Tin));
    }
}
