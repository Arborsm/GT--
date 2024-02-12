package org.arbor.gtnn.api.machine.multiblock;

import com.gregtechceu.gtceu.api.capability.recipe.EURecipeCapability;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.MultiblockState;
import com.gregtechceu.gtceu.api.pattern.util.PatternMatchContext;
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
import org.arbor.gtnn.api.machine.feature.IGTPPMachine;
import org.arbor.gtnn.api.recipe.PlantCasingCondition;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ChemicalPlantMachine extends CoilWorkableElectricMultiblockMachine implements IGTPPMachine {
    private static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(ChemicalPlantMachine.class, WorkableMultiblockMachine.MANAGED_FIELD_HOLDER);

    @Getter
    @Persisted
    @DescSynced
    @RequireRerender
    private int tier;
    private MachineCasingType machineCasingType;
    private PipeType pipeType;
    private PlantCasingType plantCasingType;

    public ChemicalPlantMachine(IMachineBlockEntity holder) {
        super(holder);
    }

    //////////////////////////////////////
    //***    Multiblock LifeCycle    ***//
    //////////////////////////////////////

    @Override
    public void onStructureFormed() {
        scheduleRenderUpdate();
        super.onStructureFormed();

        // Retrieve the multiblock state
        MultiblockState multiblockState = getMultiblockState();
        PatternMatchContext matchContext = multiblockState.getMatchContext();

        // Get and store type objects to avoid repeated retrieval
        MachineCasingType machineCasingType = matchContext.get("MachineCasing") instanceof MachineCasingType ? (MachineCasingType) matchContext.get("MachineCasing") : null;
        PipeType pipeType = matchContext.get("Pipe") instanceof PipeType ? (PipeType) matchContext.get("Pipe") : null;
        PlantCasingType plantCasingType = matchContext.get("PlantCasing") instanceof PlantCasingType ? (PlantCasingType) matchContext.get("PlantCasing") : null;

        // Set type variables
        this.machineCasingType = machineCasingType;
        this.pipeType = pipeType;
        this.plantCasingType = plantCasingType;

        // Get the plant casing tier
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
        if (machine instanceof ChemicalPlantMachine chemicalPlantMachine) {
            if (RecipeHelper.getRecipeEUtTier(recipe) > chemicalPlantMachine.getMachineCasingTier() + 1) {
                return null;
            }
            if (recipe.conditions.get(0) instanceof PlantCasingCondition plantCasingCondition) {
                if (plantCasingCondition.getPlantCasing().getTier() > chemicalPlantMachine.getPlantCasingTier()) {
                    return null;
                }
            }

            var maxParallel = 1 + 2 * chemicalPlantMachine.getPipeTier();
            var parallelLimit = Math.min(maxParallel, (int) (chemicalPlantMachine.getMaxVoltage()));
            var result = GTRecipeModifiers.accurateParallel(machine, recipe, parallelLimit, false);
            recipe = result.getA() == recipe ? result.getA().copy() : result.getA();
            var parallelValue = result.getB();
            recipe.duration = Math.max(1, 256 * parallelValue / maxParallel);
            recipe.tickInputs.put(EURecipeCapability.CAP, List.of(new Content((long) parallelValue, 1.0f, 0.0f, null, null)));

            GTRecipe finalRecipe = recipe;
            return RecipeHelper.applyOverclock(new OverclockingLogic((recipe1, recipeEUt, maxVoltage, duration, amountOC) -> {
                var runOverclockingLogic = OverclockingLogic.NON_PERFECT_OVERCLOCK.getLogic().runOverclockingLogic(finalRecipe, recipeEUt, maxVoltage, duration, amountOC);
                if (chemicalPlantMachine.getCoilTier() > 0) {
                    var eu = runOverclockingLogic.firstLong() * (1 - chemicalPlantMachine.getCoilTier() * 0.5);
                    runOverclockingLogic.first((long) Math.max(1, eu));
                }
                return runOverclockingLogic;
            }), recipe, chemicalPlantMachine.getMaxVoltage());
        }
        throw new RuntimeException("Machine is not a ChemicalPlant");
    }

    //////////////////////////////////////
    //******       NBT SAVE      *******//
    //////////////////////////////////////

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }
}
