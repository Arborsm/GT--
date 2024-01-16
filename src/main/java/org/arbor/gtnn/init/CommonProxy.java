package org.arbor.gtnn.init;

import com.lowdragmc.lowdraglib.Platform;
import org.arbor.gtnn.GTNNRegistries;
import org.arbor.gtnn.data.GTNNDataGen;

public class CommonProxy {
    public static void init() {
        if (Platform.isDatagen()){
            AddonProxy.init();
        }
        GTNNDataGen.init();
        GTNNRegistries.REGISTRATE.registerRegistrate();
    }
}
