package org.arbor.arborcore.mixin;

import org.arbor.arborcore.worldgen.Registry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import earth.terrarium.ad_astra.common.entity.vehicle.Rocket;
import earth.terrarium.ad_astra.common.entity.vehicle.Vehicle;
import earth.terrarium.ad_astra.common.registry.ModTags;
import earth.terrarium.ad_astra.common.util.FluidUtils;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

@Mixin(Vehicle.class)
public abstract class AdAstraMixin{

    @Inject(method = "tryInsertingIntoTank()V", at = @At("HEAD"), cancellable = true, remap = false)
    @SuppressWarnings("deprecation")
    public void arbor$TryInsertingIntoTank(CallbackInfo ci) {
        if (((Vehicle)(Object)this).getInventorySize() > 1 && !((Vehicle)(Object)this).getInventory().getItem(0).isEmpty()) {
            if (!((Vehicle)(Object)this).level().isClientSide) {
                FluidUtils.insertItemFluidToTank(((Vehicle)(Object)this).getTank(), ((Vehicle)(Object)this).getInventory(), 0, 1, 0, f -> f.is(fuel()));
                FluidUtils.extractTankFluidToItem(((Vehicle)(Object)this).getTank(), ((Vehicle)(Object)this).getInventory(), 0, 1, 0, f -> true);
            }
        }
        ci.cancel();
    }

    @Unique
    public TagKey<Fluid> fuel(){
        if(((Vehicle)(Object)this).getInventorySize() == 10){
            int tier = ((Rocket)(Object)this).getTier();
            return switch (tier) {
                case 1 -> Registry.TIER1;
                case 2 -> Registry.TIER2;
                case 3 -> Registry.TIER3;
                case 4 -> Registry.TIER4;
                default -> ModTags.FUELS;
            };
        }else return ModTags.FUELS;
    }
}