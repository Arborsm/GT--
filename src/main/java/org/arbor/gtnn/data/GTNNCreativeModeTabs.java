package org.arbor.gtnn.data;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.*;
import org.arbor.gtnn.GTNN;
import org.arbor.gtnn.api.registry.GTNNRegistrate;
import org.jetbrains.annotations.NotNull;

import static org.arbor.gtnn.GTNNRegistries.REGISTRATE;

public class GTNNCreativeModeTabs {
    public static RegistryEntry<CreativeModeTab> MAIN_TAB = REGISTRATE.defaultCreativeTab("main",
                    builder -> builder.displayItems(new RegistrateDisplayItemsGenerator("main", REGISTRATE))
                            .icon(GTNNMachines.CHEMICAL_PLANT::asStack)
                            .title(REGISTRATE.addLang("itemGroup", GTNN.id("main"), "GTNN"))
                            .build())
            .register();

    public record RegistrateDisplayItemsGenerator(String name,
                                                  GTNNRegistrate registrate) implements CreativeModeTab.DisplayItemsGenerator {

        @Override
        public void accept(CreativeModeTab.@NotNull ItemDisplayParameters itemDisplayParameters, CreativeModeTab.@NotNull Output output) {
            var tab = registrate.get(name, Registries.CREATIVE_MODE_TAB);
            for (var entry : registrate.getAll(Registries.BLOCK)) {
                if (!registrate.isInCreativeTab(entry, tab))
                    continue;
                Item item = entry.get().asItem();
                if (item == Items.AIR)
                    continue;
                if (item instanceof ComponentItem componentItem) {
                    NonNullList<ItemStack> list = NonNullList.create();
                    componentItem.fillItemCategory(tab.get(), list);
                    list.forEach(output::accept);
                } else {
                    output.accept(item);
                }
            }
            for (var entry : registrate.getAll(Registries.ITEM)) {
                if (!registrate.isInCreativeTab(entry, tab))
                    continue;
                Item item = entry.get();
                if (item instanceof BlockItem)
                    continue;
                if (item instanceof ComponentItem componentItem) {
                    NonNullList<ItemStack> list = NonNullList.create();
                    componentItem.fillItemCategory(tab.get(), list);
                    list.forEach(output::accept);
                } else {
                    output.accept(item);
                }
            }
        }
    }
}
