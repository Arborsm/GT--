package org.arbor.arborcore.data;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import static org.arbor.arborcore.api.registry.ArborRegistries.REGISTRATE;

public class ArborItems {
    static {
        REGISTRATE.creativeModeTab(() -> ArborCreativeModeTabs.ArborCreativeModeTab);
    }
    public static ItemEntry<Item> RADIOACTIVE_WASTE = REGISTRATE.item("radioactive_waste", Item::new)
            .lang("Radioactive Waste")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .register();

    public static void init() {
    }
}
