package org.arbor.arborcore.data;

import com.gregtechceu.gtceu.api.item.ComponentItem;
import com.tterrag.registrate.util.entry.RegistryEntry;
import net.minecraft.core.NonNullList;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.*;
import org.arbor.arborcore.api.registry.registrate.ArborRegistrate;
import org.jetbrains.annotations.NotNull;

import static org.arbor.arborcore.api.registry.ArborRegistries.REGISTRATE;

public class ArborCreativeModeTabs {
    public static RegistryEntry<CreativeModeTab> ArborCreativeModeTab = REGISTRATE.defaultCreativeTab("arbor",
                    builder -> builder.displayItems(new RegistrateDisplayItemsGenerator("arbor", REGISTRATE))
                            .icon(ArborMachines.CHEMICAL_PLANT::asStack)
                            .build())
            .register();

    public record RegistrateDisplayItemsGenerator(String name,
                                                  ArborRegistrate registrate) implements CreativeModeTab.DisplayItemsGenerator {

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
