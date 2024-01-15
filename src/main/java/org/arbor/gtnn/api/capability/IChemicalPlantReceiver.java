package org.arbor.gtnn.api.capability;

import org.arbor.gtnn.api.machine.feature.IChemicalPlantProvider;

import javax.annotation.Nullable;

/**
 * Implement this interface in order to make a BlockEntity into a block that receives a ChemicalPlant from other blocks
 */
public interface IChemicalPlantReceiver {
    /**
     * @return the cleanroom the machine is receiving from
     */
    @Nullable
    IChemicalPlantProvider getChemicalPlant();
}
