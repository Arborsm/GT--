package org.arbor.arborcore.mixin;

import dev.emi.emi.api.recipe.EmiPlayerInventory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = EmiPlayerInventory.class, remap = false)
class MEmiPlayerInventory {
    @Unique
    private static int arborCore$getDamageAmount(EmiStack emiStack, boolean damage) {
        ItemStack itemStack = emiStack.getItemStack();
        if (!itemStack.isEmpty()) {
            ItemStack newItemStack = itemStack.getItem().getDefaultInstance();
            ItemStack remainder = ForgeHooks.getCraftingRemainingItem(newItemStack);
            int damage0 = newItemStack.getDamageValue();
            int damage1 = remainder.getDamageValue();
            int dDamage = damage1 - damage0;
            if (!remainder.isEmpty() && remainder.is(newItemStack.getItem()) && damage0 != damage1) {
                return damage ? dDamage : (itemStack.getMaxDamage() - itemStack.getDamageValue()) / (dDamage);
            }
        }
        return 0;
    }
    @Unique
    private static int arborCore$getDamage(EmiStack emiStack) {
        return arborCore$getDamageAmount(emiStack, true);
    }
    @Unique
    private static int arborCore$getAmount(EmiStack emiStack) {
        return arborCore$getDamageAmount(emiStack, false);
    }
    @Redirect(method = "addStack(Ldev/emi/emi/api/stack/EmiStack;)V", at = @At(value = "INVOKE", target = "Ldev/emi/emi/api/stack/EmiStack;getAmount()J"))
    private long getRestDamageAmount(EmiStack emiStack) {
        int amount = arborCore$getAmount(emiStack);
        return amount != 0 ? amount : emiStack.getAmount();
    }
    @ModifyArg(method = "addStack(Ldev/emi/emi/api/stack/EmiStack;)V", at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"), index = 1)
    private Object setAmount(Object emiStack0) {
        EmiStack emiStack = (EmiStack) emiStack0;
        int amount = arborCore$getAmount(emiStack);
        if (amount != 0) {
            emiStack.setAmount(amount);
        }
        return emiStack;
    }
    @Redirect(method = "canCraft(Ldev/emi/emi/api/recipe/EmiRecipe;J)Z", at = @At(value = "INVOKE", target = "Ldev/emi/emi/api/stack/EmiStack;getAmount()J", ordinal = 0))
    private long getDamageAmount(EmiStack emiStack) {
        int damage = arborCore$getDamage(emiStack);
        return damage != 0 ? damage : emiStack.getAmount();
    }
}
