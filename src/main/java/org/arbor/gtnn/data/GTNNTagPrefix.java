package org.arbor.gtnn.data;

import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import org.arbor.gtnn.GTNNIntegration;
import org.arbor.gtnn.data.misc.adastra.AdAstraAddon;

public class GTNNTagPrefix {
    public static TagPrefix oreMoonStone;
    public static TagPrefix oreVenusStone;
    public static TagPrefix oreMarsStone;
    public static TagPrefix oreMercuryStone;
    public static TagPrefix oreGlacioStone;
    public static TagPrefix oreBlackStone;
    public static TagPrefix oreSoulSoil;

    public static void init() {
        if (GTNNIntegration.isAdAstraLoaded()) AdAstraAddon.init();
    }
}
