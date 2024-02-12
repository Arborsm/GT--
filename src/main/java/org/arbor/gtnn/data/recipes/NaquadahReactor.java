package org.arbor.gtnn.data.recipes;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static org.arbor.gtnn.data.GTNNRecipesTypes.NAQUADAH_REACTOR_RECIPES;

public class NaquadahReactor {
    public static void init(Consumer<FinishedRecipe> consumer) {
        NAQUADAH_REACTOR_RECIPES.recipeBuilder("naquadah_reactor_I")
                .inputItems(TagPrefix.bolt, GTMaterials.NaquadahEnriched)
                .outputItems(TagPrefix.bolt, GTMaterials.Naquadah)
                .duration((int) (50_000_000L / V[EV]))
                .EUt(-V[EV])
                .save(consumer);
        NAQUADAH_REACTOR_RECIPES.recipeBuilder("naquadah_reactor_II")
                .inputItems(TagPrefix.rod, GTMaterials.NaquadahEnriched)
                .outputItems(TagPrefix.rod, GTMaterials.Naquadah)
                .duration((int) (250_000_000L / V[IV]))
                .EUt(-V[IV])
                .save(consumer);
        NAQUADAH_REACTOR_RECIPES.recipeBuilder("naquadah_reactor_III")
                .inputItems(TagPrefix.rodLong, GTMaterials.NaquadahEnriched)
                .outputItems(TagPrefix.rodLong, GTMaterials.Naquadah)
                .duration((int) (500_000_000L / V[LuV]))
                .EUt(-V[LuV])
                .save(consumer);
        NAQUADAH_REACTOR_RECIPES.recipeBuilder("naquadah_reactor_IV")
                .inputItems(TagPrefix.bolt, GTMaterials.Naquadria)
                .outputItems(TagPrefix.bolt, GTMaterials.Naquadah)
                .duration((int) (250_000_000L / V[ZPM]))
                .EUt(-V[ZPM])
                .save(consumer);
        NAQUADAH_REACTOR_RECIPES.recipeBuilder("naquadah_reactor_V")
                .inputItems(TagPrefix.rod, GTMaterials.Naquadria)
                .outputItems(TagPrefix.rod, GTMaterials.Naquadah)
                .duration((int) (1_000_000_000L / V[UV]))
                .EUt(-V[UV])
                .save(consumer);
    }
}
