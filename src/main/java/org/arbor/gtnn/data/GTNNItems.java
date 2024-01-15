package org.arbor.gtnn.data;

import com.tterrag.registrate.util.entry.ItemEntry;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import static org.arbor.gtnn.api.registry.GTNNRegistries.REGISTRATE;

@SuppressWarnings("unused")
public class GTNNItems {
    static {
        REGISTRATE.creativeModeTab(() -> GTNNCreativeModeTabs.MAIN_TAB);
    }
    public static final ItemEntry<Item> RADIOACTIVE_WASTE = REGISTRATE.item("radioactive_waste", Item::new)
            .lang("放射性废料")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .register();

    public static final ItemEntry<Item> HEAVY_INGOT_T1 = REGISTRATE.item("heavy_ingot_t1", Item::new)
            .lang("T1重型锭")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            //.onRegister(item -> item.appendHoverText())
            .register();

    public static void init() {
    }
}
