package org.arbor.gtnn.api.machine.multiblock;

import com.gregtechceu.gtceu.api.capability.recipe.EURecipeCapability;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.api.syncdata.RequireRerender;
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import lombok.Getter;
import net.minecraft.MethodsReturnNonnullByDefault;
import org.arbor.gtnn.api.block.MachineCasingType;
import org.arbor.gtnn.api.block.PipeType;
import org.arbor.gtnn.api.block.PlantCasingType;
import org.arbor.gtnn.api.machine.feature.IChemicalPlantProvider;
import org.arbor.gtnn.api.machine.feature.IGTPPMachine;
import org.arbor.gtnn.api.recipe.PlantCasingCondition;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ChemicalPlant extends CoilWorkableElectricMultiblockMachine implements IChemicalPlantProvider, IGTPPMachine {
    private static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(ChemicalPlant.class, WorkableMultiblockMachine.MANAGED_FIELD_HOLDER);

    @Getter
    @Persisted
    @DescSynced
    @RequireRerender
    private int tier;
    private MachineCasingType machineCasingType;
    private PipeType pipeType;
    private PlantCasingType plantCasingType;

    public ChemicalPlant(IMachineBlockEntity holder) {
        super(holder);
    }

    //////////////////////////////////////
    //***    Multiblock LifeCycle    ***//
    //////////////////////////////////////
    @Override
    public void onStructureFormed() {
        scheduleRenderUpdate();
        super.onStructureFormed();
        if (getMultiblockState().getMatchContext().get("MachineCasing") instanceof MachineCasingType machineCasing) {
            this.machineCasingType = machineCasing;
        }
        if (getMultiblockState().getMatchContext().get("Pipe") instanceof PipeType pipe) {
            this.pipeType = pipe;
        }
        if (getMultiblockState().getMatchContext().get("PlantCasing") instanceof PlantCasingType plantCasing) {
            this.plantCasingType = plantCasing;
        }
        this.tier = getPlantCasingTier();
    }

    @Override
    public void scheduleRenderUpdate(){
        scheduleRenderUpdate(this);
    }

    public int getMachineCasingTier() {
        if (this.machineCasingType != null) {
            return this.machineCasingType.getTier();
        }
        return 0;
    }

    public int getPipeTier() {
        if (this.pipeType != null) {
            return this.pipeType.getTier();
        }
        return 0;
    }

    public int getPlantCasingTier() {
        if (this.plantCasingType != null) {
            return this.plantCasingType.getTier();
        }
        return 0;
    }

    //////////////////////////////////////
    //******     RECIPE LOGIC    *******//
    //////////////////////////////////////

    @Nullable
    public static GTRecipe chemicalPlantRecipe(MetaMachine machine, @Nonnull GTRecipe recipe) {
        if (machine instanceof ChemicalPlant chemicalPlant) {
            if (RecipeHelper.getRecipeEUtTier(recipe) > chemicalPlant.getMachineCasingTier() + 1) {
                return null;
            }
            if (recipe.conditions.get(0) instanceof PlantCasingCondition plantCasingCondition) {
                if (plantCasingCondition.getPlantCasing().getTier() > chemicalPlant.getPlantCasingTier()) {
                    return null;
                }
            }

            var maxParallel = 1 + 2 * chemicalPlant.getPipeTier();
            var parallelLimit = Math.min(maxParallel, (int) (chemicalPlant.getMaxVoltage()));
            var result = GTRecipeModifiers.accurateParallel(machine, recipe, parallelLimit, false);
            recipe = result.getA() == recipe ? result.getA().copy() : result.getA();
            int parallelValue = result.getB();
            recipe.duration = Math.max(1, 256 * parallelValue / maxParallel);
            recipe.tickInputs.put(EURecipeCapability.CAP, List.of(new Content((long) parallelValue, 1.0f, 0.0f, null, null)));

            GTRecipe finalRecipe = recipe;
            return RecipeHelper.applyOverclock(new OverclockingLogic((recipe1, recipeEUt, maxVoltage, duration, amountOC) -> {
                var pair = OverclockingLogic.NON_PERFECT_OVERCLOCK.getLogic().runOverclockingLogic(finalRecipe, recipeEUt, maxVoltage, duration, amountOC);
                if (chemicalPlant.getCoilTier() > 0) {
                    var eu = pair.firstLong() * (1 - chemicalPlant.getCoilTier() * 0.5);
                    pair.first((long) Math.max(1, eu));
                }
                return pair;
            }), recipe, chemicalPlant.getMaxVoltage());
        }
        return null;
    }

    //////////////////////////////////////
    //******       NBT SAVE      *******//
    //////////////////////////////////////

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }
}