package org.arbor.arborcore.mixin;

import com.gregtechceu.gtceu.common.data.GTItems;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.bom.TreeCost;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashSet;
import java.util.Set;

@Mixin(TreeCost.class)
public abstract class EMIMixin {
    @Unique
    private static final Set<EmiStack> arborCore$CATALYST = new HashSet<>();
    static {
        arborCore$CATALYST.add(EmiStack.of(GTItems.INTEGRATED_CIRCUIT.asStack()));
    }
    
    @SuppressWarnings("SuspiciousMethodCalls")
    @Inject(method = "isCatalyst", at = @At("HEAD"), cancellable = true, remap = false)
    private static void $isCatalyst(EmiIngredient ing, CallbackInfoReturnable<Boolean> info) {
        if (arborCore$CATALYST.contains(ing)) {
            info.setReturnValue(true);
        }
    }
}

