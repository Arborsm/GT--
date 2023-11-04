package org.arbor.arborcore.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.gregtechceu.gtceu.common.CommonProxy;
import com.gregtechceu.gtceu.common.fabric.CommonProxyImpl;
import com.lowdragmc.lowdraglib.LDLib;

@Mixin(CommonProxyImpl.class)
public class GTStartMixin {
    @Inject(method = "init", at = @At(value = "INVOKE", target = "Lcom/gregtechceu/gtceu/GTCEu;isKubeJSLoaded()Z", ordinal = 0, shift = At.Shift.BEFORE),remap = false)
    private static void beforeIsKubeJSLoadedCheck(CallbackInfo ci) {
        if (!LDLib.isModLoaded("ad_astra")) {
            CommonProxy.init();
        }
    }

}
