package org.arbor.arborcore;

import com.gregtechceu.gtceu.api.addon.GTAddon;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import org.arbor.arborcore.worldgen.ATagPrefix;

@GTAddon
public class Addon implements IGTAddon {
    @Override
    public void initializeAddon() {
        System.out.println("ArborCore addon init!");
    }

    @Override
    public String addonModId() {
        return ArborCore.MOD_ID;
    }

    @Override
    public void registerTagPrefixes() {
        ATagPrefix.init();
    }
}
