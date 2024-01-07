package org.arbor.arborcore.api.machine.multiblock;

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.CoilWorkableElectricMultiblockMachine;
import lombok.Setter;
import net.minecraft.MethodsReturnNonnullByDefault;
import org.arbor.arborcore.api.block.MachineCasingType;
import org.arbor.arborcore.api.block.PipeType;
import org.arbor.arborcore.api.block.PlantCasingType;
import org.arbor.arborcore.api.machine.feature.IChemicalPlantProvider;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ChemicalPlant extends CoilWorkableElectricMultiblockMachine implements IChemicalPlantProvider {
    private MachineCasingType machineCasingType;
    @Setter
    private int tier = this.getMachineCasingTier();
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
    }

    public int getMachineCasingTier() {
        if (this.machineCasingType != null) {
            return this.machineCasingType.getTier();
        }
        return 0;
    }

    public int getPipeTier() {
        if (this.machineCasingType != null) {
            return this.pipeType.getTier();
        }
        return -1;
    }

    public int getPlantCasingTier() {
        if (this.machineCasingType != null) {
            return this.plantCasingType.getTier();
        }
        return -1;
    }

    public String getEnergyHatchLevel() {
        return this.machineCasingType.getEnergyHatchLevel();
    }

    @Override
    public int getTier() {
        return tier;
    }

    @Override
    public Set<ChemicalPlant> getTypes() {
        return this.machineCasingType == null ? Set.of() : Set.of(this);
    }
}
