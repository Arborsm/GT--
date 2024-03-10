package org.arbor.gtnn.api.machine.multiblock;

import com.gregtechceu.gtceu.api.capability.recipe.EURecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IExplosionMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.machine.multiblock.part.FluidHatchPartMachine;
import com.lowdragmc.lowdraglib.side.fluid.FluidStack;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import it.unimi.dsi.fastutil.longs.Long2ObjectMaps;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.level.material.Fluid;
import org.arbor.gtnn.data.GTNNMaterials;
import org.arbor.gtnn.data.GTNNRecipeTypes;
import org.jetbrains.annotations.Nullable;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class LargeNaquadahReactorMachine extends WorkableElectricMultiblockMachine implements IExplosionMachine {
    private static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(LargeNaquadahReactorMachine.class, WorkableMultiblockMachine.MANAGED_FIELD_HOLDER);
    private static final Map<Fluid, Integer> activeFluid = Map.of(
            GTMaterials.Caesium.getFluid(), 2,
            GTMaterials.Uranium235.getFluid(), 3,
            GTMaterials.Naquadah.getFluid(), 4
    );
    private static final Map<Fluid, Integer> activeFluidCost = Map.of(
            GTMaterials.Caesium.getFluid(), 180,
            GTMaterials.Uranium235.getFluid(), 180,
            GTMaterials.Naquadah.getFluid(), 20
    );
    private final List<Fluid> fuelFluids = List.of(
            GTNNMaterials.ThoriumBasedLiquidFuelExcited.getFluid(),
            GTNNMaterials.UraniumBasedLiquidFuelExcited.getFluid(),
            GTNNMaterials.PlutoniumBasedLiquidFuelExcited.getFluid()
    );
    private Set<FluidHatchPartMachine> hatchPartMachines;
    @Persisted
    private boolean hasAir = false;
    @Persisted
    private boolean hasCool = false;
    @Persisted
    private int activeFluidPower = 1;
    private Fluid lockFluid = null;

    public LargeNaquadahReactorMachine(IMachineBlockEntity holder) {
        super(holder);
    }

    @Nullable
    public static GTRecipe modifyRecipe(MetaMachine machine, GTRecipe recipe) {
        if (!(recipe.recipeType == GTNNRecipeTypes.LARGE_NAQUADAH_REACTOR_RECIPES)) return null;
        if (machine instanceof LargeNaquadahReactorMachine largeNaquadahReactorMachine) {
            final int duration = recipe.duration;
            checkHatch(largeNaquadahReactorMachine, duration);
            GTRecipe copyRecipe = recipe.copy();
            if (!largeNaquadahReactorMachine.hasAir) {
                copyRecipe.tickOutputs.clear();
                copyRecipe.outputs.clear();
                return copyRecipe;
            }
            long eut = RecipeHelper.getOutputEUt(copyRecipe);
            if (largeNaquadahReactorMachine.hasCool) {
                eut = (long) (eut * 1.5);
            }
            eut *= largeNaquadahReactorMachine.activeFluidPower;
            copyRecipe.tickOutputs.put(EURecipeCapability.CAP, List.of(new Content(eut, 1.0f, 0.0f, null, null)));
            return copyRecipe;
        }
        return null;
    }

    private static void checkHatch(LargeNaquadahReactorMachine largeNaquadahReactorMachine, int duration) {
        largeNaquadahReactorMachine.hasCool = false;
        largeNaquadahReactorMachine.hasAir = false;
        largeNaquadahReactorMachine.activeFluidPower = 1;
        for (var hatch : largeNaquadahReactorMachine.hatchPartMachines) {
            var tank = hatch.tank;
            var io = tank.getHandlerIO();
            if (io == IO.IN || io == IO.BOTH) {
                for (int i = 0; i < tank.getTanks(); i++) {
                    final FluidStack fluid = tank.storages[i].getFluid();
                    checkLockFluid(largeNaquadahReactorMachine, fluid);
                    active(largeNaquadahReactorMachine, fluid, duration);
                    if (cool(fluid, duration)) largeNaquadahReactorMachine.hasCool = true;
                    if (air(fluid, duration)) largeNaquadahReactorMachine.hasAir = true;
                }
            }
        }
    }

    private static void checkLockFluid(LargeNaquadahReactorMachine machine, FluidStack fluid) {
        if (machine.fuelFluids.contains(fluid.getFluid())) {
            if (machine.lockFluid == null) {
                machine.lockFluid = fluid.getFluid();
            } else if (machine.lockFluid != fluid.getFluid()) {
                machine.doExplosion(4 * 32);
            }
        }
    }


    //////////////////////////////////////
    //******     RECIPE LOGIC    *******//
    //////////////////////////////////////

    private static boolean air(FluidStack fluid, int duration) {
        if (fluid.getFluid().isSame(GTMaterials.LiquidAir.getFluid())) {
            final int airAmount = 2400 / 20 * duration;
            if (fluid.getAmount() >= airAmount) {
                fluid.setAmount(fluid.getAmount() - airAmount);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private static void active(LargeNaquadahReactorMachine machine, FluidStack fluid, int duration) {
        if (activeFluid.containsKey(fluid.getFluid())) {
            final int activeFluidCostI = activeFluidCost.get(fluid.getFluid()) / 20 * duration;
            final int activeFluidPower = activeFluid.get(fluid.getFluid());
            if (machine.activeFluidPower <= activeFluidPower && fluid.getAmount() >= activeFluidCostI) {
                machine.activeFluidPower = activeFluidPower;
                fluid.setAmount(fluid.getAmount() - activeFluidCostI);
            }
        }
    }

    private static boolean cool(FluidStack fluid, int duration) {
        if (fluid.getFluid().isSame(GTMaterials.PCBCoolant.getFluid())) {
            final int coldAmount = 1000 / 20 * duration;
            if (fluid.getAmount() >= coldAmount) {
                fluid.setAmount(fluid.getAmount() - coldAmount);
                return true;
            }
        }
        return false;
    }

    //////////////////////////////////////
    //***    Multiblock LifeCycle    ***//
    //////////////////////////////////////
    @Override
    public void onStructureFormed() {
        super.onStructureFormed();
        var matchContext = getMultiblockState().getMatchContext();
        Map<Long, IO> ioMap = matchContext.getOrCreate("ioMap", Long2ObjectMaps::emptyMap);
        // Cache the result of getParts() to prevent repetitive calls
        List<IMultiPart> parts = getParts();
        for (IMultiPart part : parts) {
            IO io = ioMap.getOrDefault(part.self().getPos().asLong(), IO.BOTH);
            if (io == IO.NONE) continue;
            if (part instanceof FluidHatchPartMachine fluidHatchPartMachine) {
                hatchPartMachines = APartAbility.getOrDefault(hatchPartMachines, HashSet::new);
                hatchPartMachines.add(fluidHatchPartMachine);
            }
        }
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();
        hatchPartMachines = null;
        lockFluid = null;
    }

    @Override
    public boolean alwaysTryModifyRecipe() {
        return true;
    }

    public double getFinalPowerRate() {
        double activeFluidPower = this.activeFluidPower;
        if (hasCool) activeFluidPower *= 1.5;
        return activeFluidPower;
    }

    //////////////////////////////////////
    //******       NBT SAVE      *******//
    //////////////////////////////////////
    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }
}
