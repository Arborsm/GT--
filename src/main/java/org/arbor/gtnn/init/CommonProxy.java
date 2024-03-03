package org.arbor.gtnn.init;

import org.arbor.gtnn.GTNNRegistries;
import org.arbor.gtnn.data.GTNNBlocks;
import org.arbor.gtnn.data.GTNNDataGen;
import org.arbor.gtnn.data.GTNNItems;

public class CommonProxy {
    public static void init() {
        GTNNItems.init();
        GTNNBlocks.init();
        GTNNDataGen.init();
        GTNNRegistries.REGISTRATE.registerRegistrate();
    }
}
