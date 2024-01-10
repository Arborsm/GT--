package org.arbor.arborcore.data;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;
import org.arbor.arborcore.api.registry.ArborRegistries;

public class ArborItems {
    public static ItemEntry<Item> RADIOACTIVE_WASTE = ArborRegistries.REGISTRATE.item("radioactive_waste", Item::new).lang("Radioactive Waste").register();

    public static void init() {
    }
}
