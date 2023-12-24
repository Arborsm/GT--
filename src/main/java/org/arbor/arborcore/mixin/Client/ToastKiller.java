package org.arbor.arborcore.mixin.Client;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SuppressWarnings("unused")
@OnlyIn(Dist.CLIENT)
@Mixin(ToastComponent.class)
public class ToastKiller {
    @Inject(method = "render",at = @At("HEAD"),cancellable = true)
    public void killToasts(GuiGraphics p_283249_, CallbackInfo ci){
        //Debug.Log("Killed a toast that tried to draw");
        ci.cancel();
    }

    /**
     * @author AnOpenSauceDev
     * @reason forcefully overwrite toast behaviour
     */
    @Overwrite
    public void addToast(Toast toast){
    }

    @OnlyIn(Dist.CLIENT)
    @Mixin(targets = "net.minecraft.client.gui.components.toasts.ToastComponent$ToastInstance")
    static class ToastInstance<T extends Toast> { // inner class of ToastManager

        /**
         * @author AnOpenSauceDev
         * @reason prevent toasts rendering on the client
         */
        @Overwrite
        public boolean render(int x, GuiGraphics context) { // lie about drawing
            return true;
        }
    }

}