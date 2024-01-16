package org.arbor.gtnn.mixin.gt;

import com.gregtechceu.gtceu.common.data.GTMachines;
import org.arbor.gtnn.data.GTNNMachines;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GTMachines.class)
public class GTMachineMixin {
    @Inject(method = "init()V", at = @At("HEAD"), remap = false)
    private static void init(CallbackInfo ci){
        GTNNMachines.init();
    }
}
