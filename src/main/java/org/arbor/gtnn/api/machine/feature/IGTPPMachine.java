package org.arbor.gtnn.api.machine.feature;

import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;

public interface IGTPPMachine {
    int getTier();

    default void scheduleRenderUpdate(MultiblockControllerMachine machine){
        for (IMultiPart part : machine.getParts()) {
            part.self().scheduleRenderUpdate();
        }
    }
}
