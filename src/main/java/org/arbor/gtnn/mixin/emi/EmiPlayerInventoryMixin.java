package org.arbor.gtnn.mixin.emi;

import dev.emi.emi.api.recipe.EmiPlayerInventory;
import dev.emi.emi.api.stack.EmiStack;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = EmiPlayerInventory.class, remap = false)
class EmiPlayerInventoryMixin {
    @Unique
    private static int gtnn$getDamageAmount(EmiStack emiStack, boolean damage) {
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
    private static int gtnn$getDamage(EmiStack emiStack) {
        return gtnn$getDamageAmount(emiStack, true);
    }
    @Unique
    private static int gtnn$getAmount(EmiStack emiStack) {
        return gtnn$getDamageAmount(emiStack, false);
    }
    @Redirect(method = "lambda$addStack$1", at = @At(value = "INVOKE", target = "Ldev/emi/emi/api/stack/EmiStack;getAmount()J"))
    private static long getRestDamageAmount(EmiStack emiStack) {
        int amount = gtnn$getAmount(emiStack);
        return amount != 0 ? amount : emiStack.getAmount();
    }
    @Redirect(method = "canCraft(Ldev/emi/emi/api/recipe/EmiRecipe;J)Z", at = @At(value = "INVOKE", target = "Ldev/emi/emi/api/stack/EmiStack;getAmount()J", ordinal = 0))
    private long getDamageAmount(EmiStack emiStack) {
        int damage = gtnn$getDamage(emiStack);
        return damage != 0 ? damage : emiStack.getAmount();
    }
}
