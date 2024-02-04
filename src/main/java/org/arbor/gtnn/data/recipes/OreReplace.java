package org.arbor.gtnn.data.recipes;

import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.RecipeCapability;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.api.recipe.ingredient.SizedIngredient;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static org.arbor.gtnn.data.GTNNMaterials.*;

public class OreReplace {
    public static final String[] ores;

    static{
        ores = new String[]{
                "dirty_dust_to_dust", "refined_ore_to_dust", "pure_dust_to_dust","crushed_ore_to_refined_ore",
                "crushed_ore_to_impure_dust", "crushed_ore_to_purified_ore", "crushed_ore_to_dust", "ore_to_raw_ore"
        };
    }

    public static void init(Recipe<?> recipe) {
        replace(recipe, Platinum, PlatinumMetal);
        replace(recipe, Palladium, PalladiumMetal);
        replace(recipe, Naquadah, NaquadahOxideMixture);
        replace(recipe, NaquadahEnriched, EnrichedNaquadahOxideMixture);
        replace(recipe, Naquadria, NaquadriaOxideMixture);
    }

    private static void replace(Recipe<?> recipe, Material material, Material replacedMaterial){
        for (String string : ores){
            if (recipe instanceof GTRecipe gtRecipe && gtRecipe.id.getPath().contains(string) && gtRecipe.id.getPath().contains(material.getName())) {
                setOutputs(gtRecipe.outputs, material, SizedIngredient.create(dust(replacedMaterial)));
            }
        }
    }

    private static ItemStack dust(Material material) {
        return ChemicalHelper.get(TagPrefix.dust, material, 1);
    }

    private static void setOutputs(Map<RecipeCapability<?>, List<Content>> outputs, Material material, SizedIngredient sizedIngredient) {
        List<Content> contents = outputs.get(ItemRecipeCapability.CAP);
        for (Content content : contents) {
            if (content.content instanceof Ingredient ingredient) {
                if (Arrays.stream(ingredient.getItems()).anyMatch(itemStack -> itemStack.getItem() == dust(material).getItem())) {
                    content.content = sizedIngredient;
                }
            }
        }
    }
}
