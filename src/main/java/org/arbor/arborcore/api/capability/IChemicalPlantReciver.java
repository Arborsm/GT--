package org.arbor.arborcore.api.capability;

import org.arbor.arborcore.api.machine.feature.IChemicalPlantProvider;

import javax.annotation.Nullable;

/**
 * Implement this interface in order to make a BlockEntity into a block that receives a ChemicalPlant from other blocks
 */
public interface IChemicalPlantReciver {
    /**
     * @return the cleanroom the machine is receiving from
     */
    @Nullable
    IChemicalPlantProvider getChemicalPlant();

    /**
     * sets the machine's ChemicalPlant to the provided one
     *
     * @param provider the ChemicalPlant to assign to this machine
     */
    void setChemicalPlant(IChemicalPlantProvider provider);
}
