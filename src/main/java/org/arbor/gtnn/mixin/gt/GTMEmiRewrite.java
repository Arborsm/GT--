package org.arbor.gtnn.mixin.gt;

import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.integration.emi.recipe.GTRecipeTypeEmiCategory;
import dev.emi.emi.api.EmiRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.ForgeRegistries;
import org.arbor.gtnn.GTNN;
import org.arbor.gtnn.emi.NGTEmiRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.gregtechceu.gtceu.integration.emi.recipe.GTRecipeTypeEmiCategory.CATEGORIES;

@Mixin(value = GTRecipeTypeEmiCategory.class, remap = false)
public class GTMEmiRewrite {
    @Inject(method = "registerDisplays", at = @At("HEAD"), cancellable = true)
    private static void registerDisplays(EmiRegistry registry, CallbackInfo ci) {
        if (GTNN.getServerConfig().makesEMIBetter) {
            for (RecipeType<?> recipeType : ForgeRegistries.RECIPE_TYPES) {
                if (recipeType instanceof GTRecipeType gtRecipeType) {
                    ClientLevel level = Minecraft.getInstance().level;
                    assert level != null;
                    level.getRecipeManager().getAllRecipesFor(gtRecipeType).stream()
                            .map(recipe -> new NGTEmiRecipe(CATEGORIES.apply(gtRecipeType), recipe))
                            .forEach(registry::addRecipe);
                }
            }
            ci.cancel();
        }
    }
}
