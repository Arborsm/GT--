package dev.arbor.gtnn.mixin.gt;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.integration.xei.widgets.GTRecipeWidget;

import net.minecraft.ChatFormatting;

import dev.arbor.gtnn.client.gui.VoltageBorderWidget;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = GTRecipeWidget.class, remap = false)
public abstract class GTRecipeWidgetMixin {

    @Shadow
    private int tier;
    @Shadow
    @Final
    private GTRecipe recipe;
    @Shadow
    @Final
    private int xOffset;

    @Inject(method = "initializeRecipeTextWidget", at = @At("TAIL"))
    private void injectVoltageBorder(CallbackInfo ci) {
        GTRecipeWidget self = (GTRecipeWidget) (Object) this;
        if (recipe.getInputEUt().getTotalEU() > 0 &&
                self.widgets.stream().noneMatch(w -> w instanceof VoltageBorderWidget)) {
            int color = gtnn$getColorFromVNF(GTValues.VNF[tier]);
            self.widgets.add(new VoltageBorderWidget(-xOffset, 0, self.getSize().width, self.getSize().height, color));
        }
    }

    @Unique
    private static int gtnn$getColorFromVNF(String vnfText) {
        if (vnfText == null || vnfText.isEmpty()) {
            return 0xFFFFFFFF;
        }
        ChatFormatting[] formats = ChatFormatting.values();
        for (ChatFormatting format : formats) {
            if (!format.isColor()) continue;
            if (vnfText.contains(format.toString())) {
                Integer color = format.getColor();
                if (color != null) {
                    return 0xFF000000 | color;
                }
            }
        }
        return 0xFFFFFFFF;
    }
}
