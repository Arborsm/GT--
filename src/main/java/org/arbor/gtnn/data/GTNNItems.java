package org.arbor.gtnn.data;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.item.TooltipBehavior;
import com.lowdragmc.lowdraglib.Platform;
import com.tterrag.registrate.util.entry.ItemEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

import static org.arbor.gtnn.GTNNRegistries.REGISTRATE;

@SuppressWarnings("unused")
public class GTNNItems {
    static {
        REGISTRATE.creativeModeTab(() -> GTNNCreativeModeTabs.MAIN_TAB);
    }
    public static final ItemEntry<Item> RADIOACTIVE_WASTE = REGISTRATE.item("radioactive_waste", Item::new)
            .lang("放射性废料")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .register();
    public static ItemEntry<ComponentItem> HEAVY_INGOT_T1 = REGISTRATE.item("heavy_ingot_t1", ComponentItem::create)
            .lang("T1重型锭")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .onRegister(attach(new TooltipBehavior(text ->
                    text.add(Component.translatable("item.gtnn.heavy_ingot_t1.tooltip")))))
            .register();
    public static ItemEntry<ComponentItem> HEAVY_INGOT_T2 = REGISTRATE.item("heavy_ingot_t2", ComponentItem::create)
            .lang("T2重型锭")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .onRegister(attach(new TooltipBehavior(text ->
                    text.add(Component.translatable("item.gtnn.heavy_ingot_t2.tooltip")))))
            .register();
    public static ItemEntry<ComponentItem> HEAVY_INGOT_T3 = REGISTRATE.item("heavy_ingot_t3", ComponentItem::create)
            .lang("T3重型锭")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .onRegister(attach(new TooltipBehavior(text ->
                    text.add(Component.translatable("item.gtnn.heavy_ingot_t3.tooltip")))))
            .register();
    public static ItemEntry<ComponentItem> HEAVY_INGOT_T4 = REGISTRATE.item("heavy_ingot_t4", ComponentItem::create)
            .lang("T4重型锭")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .onRegister(attach(new TooltipBehavior(text ->
                    text.add(Component.translatable("item.gtnn.heavy_ingot_t4.tooltip")))))
            .register();
    public static ItemEntry<ComponentItem> HEAVY_PLATE_T1 = REGISTRATE.item("heavy_plate_t1", ComponentItem::create)
            .lang("T1重型合金板")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .onRegister(attach(new TooltipBehavior(text ->
                    text.add(Component.translatable("item.gtnn.heavy_plate_t1.tooltip")))))
            .register();
    public static ItemEntry<ComponentItem> HEAVY_PLATE_T2 = REGISTRATE.item("heavy_plate_t2", ComponentItem::create)
            .lang("T2重型合金板")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .onRegister(attach(new TooltipBehavior(text ->
                    text.add(Component.translatable("item.gtnn.heavy_plate_t2.tooltip")))))
            .register();
    public static ItemEntry<ComponentItem> HEAVY_PLATE_T3 = REGISTRATE.item("heavy_plate_t3", ComponentItem::create)
            .lang("T3重型合金板")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .onRegister(attach(new TooltipBehavior(text ->
                    text.add(Component.translatable("item.gtnn.heavy_plate_t3.tooltip")))))
            .register();
    public static ItemEntry<ComponentItem> HEAVY_PLATE_T4 = REGISTRATE.item("heavy_plate_t4", ComponentItem::create)
            .lang("T4重型合金板")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .onRegister(attach(new TooltipBehavior(text ->
                    text.add(Component.translatable("item.gtnn.heavy_plate_t4.tooltip")))))
            .register();
    public static ItemEntry<ComponentItem> CHIP_T1 = REGISTRATE.item("t1_chip", ComponentItem::create)
            .lang("T1重型合金板")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .onRegister(attach(new TooltipBehavior(text ->
                    text.add(Component.translatable("item.gtnn.chip_t1.tooltip")))))
            .register();
    public static ItemEntry<ComponentItem> CHIP_T2 = REGISTRATE.item("t2_chip", ComponentItem::create)
            .lang("T2重型合金板")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .onRegister(attach(new TooltipBehavior(text ->
                    text.add(Component.translatable("item.gtnn.chip_t2.tooltip")))))
            .register();
    public static ItemEntry<ComponentItem> CHIP_T3 = REGISTRATE.item("t3_chip", ComponentItem::create)
            .lang("T3重型合金板")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .onRegister(attach(new TooltipBehavior(text ->
                    text.add(Component.translatable("item.gtnn.chip_t3.tooltip")))))
            .register();
    public static ItemEntry<ComponentItem> CHIP_T4 = REGISTRATE.item("t4_chip", ComponentItem::create)
            .lang("T4重型合金板")
            .properties(p -> p.rarity(Rarity.UNCOMMON))
            .onRegister(attach(new TooltipBehavior(text ->
                    text.add(Component.translatable("item.gtnn.chip_t4.tooltip")))))
            .register();

    private static NonNullConsumer<? super ComponentItem> attach(TooltipBehavior components) {
        if (!Platform.isDatagen()){
            return GTItems.attach(components);
        }
        return Item::asItem;
    }

    public static void init() {
    }
}
