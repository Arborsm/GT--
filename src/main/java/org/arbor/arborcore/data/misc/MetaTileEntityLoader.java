package org.arbor.arborcore.data.misc;

import com.gregtechceu.gtceu.api.data.chemical.material.stack.UnificationEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.level.block.Blocks;
import org.arbor.arborcore.ArborCore;
import org.arbor.arborcore.data.ArborMachines;

import java.util.function.Consumer;

public class MetaTileEntityLoader {
    public static void init(Consumer<FinishedRecipe> provider){
        VanillaRecipeHelper.addShapedRecipe(provider, true, ArborCore.id("chemical_plant"), ArborMachines.CHEMICAL_PLANT.asStack(), "FFF", "CMC", "WCW", 'M', GTBlocks.CASING_INVAR_HEATPROOF.asStack(), 'F', Blocks.FURNACE.asItem(), 'C', CustomTags.LV_CIRCUITS, 'W', new UnificationEntry(TagPrefix.cableGtSingle, GTMaterials.Tin));
    }
}
