package org.arbor.arborcore.mixin;

import org.arbor.arborcore.worldgen.GTOreVein;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.gregtechceu.gtceu.data.loader.OreDataLoader;

@Mixin(OreDataLoader.class)
public class GTOreMIXIN {
    @Inject(method = "apply", at = @At(value = "INVOKE", target = "Lcom/gregtechceu/gtceu/common/data/GTOres;init()V", shift = At.Shift.AFTER), remap = false)
    private void postInit(CallbackInfo ci) {
        GTOreVein.OreRemove();
        GTOreVein.init();
    }

}
