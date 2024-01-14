package org.arbor.gtnn.data;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import static org.arbor.gtnn.api.registry.GTNNRegistries.REGISTRATE;

public class GTNNItems {
    static {
        REGISTRATE.creativeModeTab(() -> GTNNCreativeModeTabs.ArborCreativeModeTab);
    }
    public static ItemEntry<Item> RADIOACTIVE_WASTE = REGISTRATE.item("radioactive_waste", Item::new)
            .lang("Radioactive Waste")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .register();

    public static void init() {
    }
}
