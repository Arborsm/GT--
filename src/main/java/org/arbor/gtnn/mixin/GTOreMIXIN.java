package org.arbor.gtnn.mixin;

import com.gregtechceu.gtceu.data.loader.OreDataLoader;
import org.arbor.gtnn.worldgen.GTOreVein;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OreDataLoader.class)
public class GTOreMIXIN {
    @Inject(method = "apply*", at = @At(value = "INVOKE", target = "Lcom/gregtechceu/gtceu/common/data/GTOres;init()V", shift = At.Shift.AFTER), remap = false)
    private void postInit(CallbackInfo ci) {
        GTOreVein.OreRemove();
    }

}
