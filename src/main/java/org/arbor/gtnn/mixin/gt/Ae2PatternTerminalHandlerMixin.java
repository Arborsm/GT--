package org.arbor.gtnn.mixin.gt;

import com.gregtechceu.gtceu.integration.emi.recipe.Ae2PatternTerminalHandler;
import dev.emi.emi.api.recipe.EmiRecipe;
import org.arbor.gtnn.emi.NGTEmiRecipe;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Ae2PatternTerminalHandler.class)
public class Ae2PatternTerminalHandlerMixin {
    @Inject(method = "supportsRecipe", at = @At("HEAD"), cancellable = true, remap = false)
    private void whyGTMStillNotUpdate(EmiRecipe recipe, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(recipe instanceof NGTEmiRecipe);
    }
}
