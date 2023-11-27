package org.arbor.arborcore.api.machine.feature;

import com.gregtechceu.gtceu.api.machine.feature.IMachineFeature;
import org.arbor.arborcore.api.machine.multiblock.ChemicalPlant;

import java.util.Set;

/**
 * Implement this interface in order to make a Machine into a block that provides a ChemicalPlant to other blocks
 */
public interface IChemicalPlantProvider extends IMachineFeature {
    /**
     * @return a {@link Set} of {@link ChemicalPlant} which the ChemicalPlant provides
     */
    Set<ChemicalPlant> getTypes();
}
