package org.arbor.gtnn.data;

import com.gregtechceu.gtceu.common.data.GTCreativeModeTabs;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.world.item.CreativeModeTab;
import org.arbor.gtnn.GTNN;

import static org.arbor.gtnn.GTNNRegistries.REGISTRATE;

public class GTNNCreativeModeTabs {
    public static final RegistryEntry<CreativeModeTab> MAIN_TAB = REGISTRATE.defaultCreativeTab("main",
                    builder -> builder.displayItems(new GTCreativeModeTabs.RegistrateDisplayItemsGenerator("main", REGISTRATE))
                            .title(REGISTRATE.addLang("itemGroup", GTNN.id("main"), "GT--"))
                            .icon(GTNNMachines.CHEMICAL_PLANT::asStack)
                            .build())
            .register();

}
