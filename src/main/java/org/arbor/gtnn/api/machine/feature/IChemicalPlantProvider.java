package org.arbor.gtnn.api.machine.feature;

import com.gregtechceu.gtceu.api.machine.feature.IMachineFeature;
import org.arbor.gtnn.api.machine.multiblock.ChemicalPlant;

import java.util.Set;

/**
 * Implement this interface in order to make a Machine into a block that provides a ChemicalPlant to other blocks
 */
public interface IChemicalPlantProvider extends IMachineFeature {
}
