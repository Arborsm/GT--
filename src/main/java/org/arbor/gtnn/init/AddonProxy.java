package org.arbor.gtnn.init;

import org.arbor.gtnn.api.registry.GTNNRegistries;
import org.arbor.gtnn.data.GTNNBlocks;
import org.arbor.gtnn.data.GTNNItems;
import org.arbor.gtnn.data.GTNNMachines;

public class AddonProxy {
    public static void init(){
        GTNNItems.init();
        GTNNBlocks.init();
        GTNNMachines.init();
        GTNNRegistries.REGISTRATE.registerRegistrate();
    }
}
