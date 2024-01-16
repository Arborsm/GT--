package org.arbor.gtnn.init;

import com.lowdragmc.lowdraglib.Platform;
import org.arbor.gtnn.api.registry.GTNNRegistries;
import org.arbor.gtnn.data.GTNNCasingBlocks;
import org.arbor.gtnn.data.GTNNDataGen;

public class CommonProxy {
    public static void init() {
        if (Platform.isDatagen()){
            AddonProxy.init();
            GTNNCasingBlocks.init();
        }
        GTNNDataGen.init();
        GTNNRegistries.REGISTRATE.registerRegistrate();
    }
}
