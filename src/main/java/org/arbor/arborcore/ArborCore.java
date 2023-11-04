package org.arbor.arborcore;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.fabricmc.api.ModInitializer;
import org.arbor.arborcore.init.loads;

public class ArborCore implements ModInitializer {
    public static final String MOD_ID = "arborcore";
    public static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing ArborCore");
        loads.load();
    }
}
