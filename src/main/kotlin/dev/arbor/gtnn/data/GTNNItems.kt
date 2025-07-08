package dev.arbor.gtnn.data

import com.gregtechceu.gtceu.api.item.ComponentItem
import com.gregtechceu.gtceu.api.item.IComponentItem
import com.gregtechceu.gtceu.api.item.component.ICustomRenderer
import com.gregtechceu.gtceu.api.item.component.IItemComponent
import com.gregtechceu.gtceu.common.data.GTItems
import com.gregtechceu.gtceu.common.item.TooltipBehavior
import com.lowdragmc.lowdraglib.Platform
import com.tterrag.registrate.Registrate
import com.tterrag.registrate.builders.ItemBuilder
import com.tterrag.registrate.util.entry.ItemEntry
import com.tterrag.registrate.util.nullness.NonNullConsumer
import com.tterrag.registrate.util.nullness.NonNullFunction
import dev.arbor.gtnn.GTNN
import dev.arbor.gtnn.GTNNRegistries.REGISTRATE
import dev.arbor.gtnn.api.item.behaviors.StructureWriteBehavior
import dev.arbor.gtnn.data.materials.GTNNChemicalItems
import net.minecraft.network.chat.Component
import net.minecraft.world.item.Item
import net.minecraft.world.item.Rarity
import org.jetbrains.annotations.ApiStatus
import java.util.function.Consumer

object GTNNItems {
    init {
        REGISTRATE.creativeModeTab { GTNNCreativeModeTabs.MAIN_TAB }
    }

    val RADIOACTIVE_WASTE: ItemEntry<Item> =
        createItem<Item>("radioactive_waste") { properties: Item.Properties -> Item(properties) }
            .lang("Radioactive Waste")
            .properties { p: Item.Properties -> p.rarity(Rarity.UNCOMMON) }
            .register()

    val HEAVY_INGOT_T1: ItemEntry<ComponentItem> =
        createItem("heavy_ingot_t1") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Heavy Alloy Ingot T1")
            .properties { p: Item.Properties -> p.rarity(Rarity.UNCOMMON) }
            .onRegister(attach(TooltipBehavior { text: MutableList<Component?> ->
                text.add(Component.translatable("item.gtnn.heavy_ingot_t1.tooltip"))
            }))
            .register()

    val HEAVY_INGOT_T2: ItemEntry<ComponentItem> =
        createItem("heavy_ingot_t2") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Heavy Alloy Ingot T2")
            .properties { p: Item.Properties -> p.rarity(Rarity.UNCOMMON) }
            .onRegister(attach(TooltipBehavior { text: MutableList<Component?> ->
                text.add(Component.translatable("item.gtnn.heavy_ingot_t2.tooltip"))
            }))
            .register()

    val HEAVY_INGOT_T3: ItemEntry<ComponentItem> =
        createItem("heavy_ingot_t3") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Heavy Alloy Ingot T3")
            .properties { p: Item.Properties -> p.rarity(Rarity.UNCOMMON) }
            .onRegister(attach(TooltipBehavior { text: MutableList<Component?> ->
                text.add(Component.translatable("item.gtnn.heavy_ingot_t3.tooltip"))
            }))
            .register()

    val HEAVY_INGOT_T4: ItemEntry<ComponentItem> =
        createItem("heavy_ingot_t4") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Heavy Alloy Ingot T4")
            .properties { p: Item.Properties -> p.rarity(Rarity.UNCOMMON) }
            .onRegister(attach(TooltipBehavior { text: MutableList<Component?> ->
                text.add(Component.translatable("item.gtnn.heavy_ingot_t4.tooltip"))
            }))
            .register()

    val HEAVY_PLATE_T1: ItemEntry<ComponentItem> =
        createItem("heavy_plate_t1") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Heavy Alloy Plate T1")
            .properties { p: Item.Properties -> p.rarity(Rarity.UNCOMMON) }
            .onRegister(attach(TooltipBehavior { text: MutableList<Component?> ->
                text.add(Component.translatable("item.gtnn.heavy_plate_t1.tooltip"))
            }))
            .register()

    val HEAVY_PLATE_T2: ItemEntry<ComponentItem> =
        createItem("heavy_plate_t2") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Heavy Alloy Plate T2")
            .properties { p: Item.Properties -> p.rarity(Rarity.UNCOMMON) }
            .onRegister(attach(TooltipBehavior { text: MutableList<Component?> ->
                text.add(Component.translatable("item.gtnn.heavy_plate_t2.tooltip"))
            }))
            .register()

    val HEAVY_PLATE_T3: ItemEntry<ComponentItem> =
        createItem("heavy_plate_t3") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Heavy Alloy Plate T3")
            .properties { p: Item.Properties -> p.rarity(Rarity.UNCOMMON) }
            .onRegister(attach(TooltipBehavior { text: MutableList<Component?> ->
                text.add(Component.translatable("item.gtnn.heavy_plate_t3.tooltip"))
            }))
            .register()

    val HEAVY_PLATE_T4: ItemEntry<ComponentItem> =
        createItem("heavy_plate_t4") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Heavy Alloy Plate T4")
            .properties { p: Item.Properties -> p.rarity(Rarity.UNCOMMON) }
            .onRegister(attach(TooltipBehavior { text: MutableList<Component?> ->
                text.add(Component.translatable("item.gtnn.heavy_plate_t4.tooltip"))
            }))
            .register()

    val CHIP_T1: ItemEntry<ComponentItem> =
        createItem("t1_chip") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Chip T1")
            .properties { p: Item.Properties -> p.rarity(Rarity.UNCOMMON) }
            .onRegister(attach(TooltipBehavior { text: MutableList<Component?> ->
                text.add(Component.translatable("item.gtnn.chip_t1.tooltip"))
            }))
            .register()

    val CHIP_T2: ItemEntry<ComponentItem> =
        createItem("t2_chip") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Chip T2")
            .properties { p: Item.Properties -> p.rarity(Rarity.UNCOMMON) }
            .onRegister(attach(TooltipBehavior { text: MutableList<Component?> ->
                text.add(Component.translatable("item.gtnn.chip_t2.tooltip"))
            }))
            .register()

    val CHIP_T3: ItemEntry<ComponentItem> =
        createItem("t3_chip") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Chip T3")
            .properties { p: Item.Properties -> p.rarity(Rarity.EPIC) }
            .onRegister(attach(TooltipBehavior { text: MutableList<Component?> ->
                text.add(Component.translatable("item.gtnn.chip_t3.tooltip"))
            }))
            .register()

    val CHIP_T4: ItemEntry<ComponentItem> =
        createItem("t4_chip") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Chip T4")
            .properties { p: Item.Properties -> p.rarity(Rarity.EPIC) }
            .onRegister(attach(TooltipBehavior { text: MutableList<Component?> ->
                text.add(Component.translatable("item.gtnn.chip_t4.tooltip"))
            }))
            .register()

    val INVERTER: ItemEntry<ComponentItem> =
        createItem("inverter") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Inverter")
            .register()

    val EncapsulatedUranium: ItemEntry<ComponentItem> =
        createItem("encapsulated_uranium") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Encapsulated Uranium")
            .register()

    val EnrichedUraniumNugget: ItemEntry<ComponentItem> =
        createItem("enriched_uranium_nugget") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Enriched Uranium Nugget")
            .register()

    val EnrichedUranium: ItemEntry<ComponentItem> =
        createItem("enriched_uranium") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Enriched Uranium")
            .register()

    val EncapsulatedThorium: ItemEntry<ComponentItem> =
        createItem("encapsulated_thorium") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Encapsulated Thorium")
            .register()

    val EnrichedThoriumNugget: ItemEntry<ComponentItem> =
        createItem("enriched_thorium_nugget") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Enriched Thorium Nugget")
            .register()

    val EnrichedThorium: ItemEntry<ComponentItem> =
        createItem("enriched_thorium") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Enriched Thorium")
            .register()

    val EncapsulatedPlutonium: ItemEntry<ComponentItem> =
        createItem("encapsulated_plutonium") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Encapsulated Plutonium")
            .register()

    val EnrichedPlutoniumNugget: ItemEntry<ComponentItem> =
        createItem("enriched_plutonium_nugget") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Enriched Plutonium Nugget")
            .register()

    val EnrichedPlutonium: ItemEntry<ComponentItem> =
        createItem("enriched_plutonium") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Enriched Plutonium")
            .register()

    val NeutronSource: ItemEntry<ComponentItem> =
        createItem("neutron_source") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Neutron Source")
            .register()

    val QuarkCore: ItemEntry<ComponentItem> =
        createItem("quark_core") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Quark Core")
            .register()

    val PlateRadiationProtection: ItemEntry<ComponentItem> =
        createItem("plate_radiation_protection") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Radiation Protection Plate")
            .register()

    val COMPUTER: ItemEntry<ComponentItem> =
        createItem("computer_circuit") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Computer Chip")
            .properties { p: Item.Properties -> p.rarity(Rarity.UNCOMMON) }
            .register()

    val COMPUTER_ADVANCED: ItemEntry<ComponentItem> =
        createItem("computer_advanced_circuit") { properties: Item.Properties -> ComponentItem.create(properties) }
            .lang("Advanced Computer Chip")
            .properties { p: Item.Properties -> p.rarity(Rarity.RARE) }
            .register()

    @Deprecated("")
    @ApiStatus.ScheduledForRemoval(inVersion = "1.3.0")
    val COVER_ENDER_FLUID_LINK: ItemEntry<ComponentItem> =
        createItem("ender_fluid_link_cover") { properties -> ComponentItem.create(properties) }
            .lang("Ender Fluid Link")
            .onRegister(GTItems.attach(TooltipBehavior{
                it.add(Component.translatable("item.gtnn.ender_fluid_link_cover.tooltip"))
                if (!GTNN.getServerConfig().isTurnOnEnderFluidCover) it.add(
                    Component.translatable("tooltip.gtnn.banItem"))
            }))
            .register()

    val COVER_ENDER_ITEM_LINK: ItemEntry<ComponentItem> =
        createItem("ender_item_link_cover") { properties -> ComponentItem.create(properties) }
            .lang("Ender Item Link")
            .onRegister(GTItems.attach(TooltipBehavior {
                it.add(Component.translatable("item.gtnn.ender_item_link_cover.tooltip"))
                if (!GTNN.getServerConfig().isTurnOnEnderItemCover) it.add(
                    Component.translatable("tooltip.gtnn.banItem"))
            }))
            .register()

    val DEBUG_STRUCTURE_WRITER: ItemEntry<ComponentItem> =
        registerItemWithTooltip("debug_structure_writer", 1)
            .onRegister(GTItems.attach(StructureWriteBehavior))
            .register()

    private fun <T : IComponentItem> attach(components: IItemComponent): NonNullConsumer<T> {
        return NonNullConsumer { it.attachComponents(components) }
    }

    fun init() {
        GTNNChemicalItems.init()
        EPMItems.init()
        GTNNCircuitItems.init()
        GTNNWrapItem.init()
    }

    private fun <T : Item?> createItem(
        name: String, factory: NonNullFunction<Item.Properties, T>
    ): ItemBuilder<T, Registrate> {
        return REGISTRATE.item(name, factory)
    }

    fun registerItemWithTooltip(
        name: String, num: Int
    ): ItemBuilder<ComponentItem, Registrate> {
        return registerItemWithTooltip(name, { ComponentItem.create(it) }, num)
    }

    fun <T : ComponentItem> registerItemWithTooltip(
        name: String, factory: NonNullFunction<Item.Properties, T>, num: Int
    ): ItemBuilder<T, Registrate> {
        return REGISTRATE.item(name, factory).onRegister(GTItems.attach<T?>(TooltipBehavior {
            if (num <= 0) return@TooltipBehavior

            val modId = REGISTRATE.modid
            if (num == 1) {
                it += Component.translatable("item.$modId.$name.desc")
            } else {
                (0..<num).forEach { i ->
                    it += Component.translatable("item.$modId.$name.desc.$i")
                }
            }
        }))
    }

    fun registerItemWithTooltip(
        name: String, num: Int, vararg other: Component
    ): ItemBuilder<ComponentItem, Registrate> {
        return registerItemWithTooltip(
            name,
            { properties -> ComponentItem.create(properties) },
            num,
            *other
        )
    }

    fun <T : ComponentItem> registerItemWithTooltip(
        name: String, factory: NonNullFunction<Item.Properties, T>, num: Int, vararg other: Component
    ): ItemBuilder<T, Registrate> {
        return REGISTRATE.item(name, factory)
            .onRegister(GTItems.attach<T?>(TooltipBehavior(Consumer { lines ->
                if (num <= 0) return@Consumer
                if (num == 1) {
                    lines!!.add(
                        Component.translatable("item.%s.%s.desc".format(REGISTRATE.modid, name))
                    )
                    lines.addAll(listOf(*other))
                } else {
                    for (i in 0..<num) {
                        lines!!.add(
                            Component.translatable(
                                "item.%s.%s.desc.%s".format(REGISTRATE.modid, name, i)
                            )
                        )
                    }
                    lines!!.addAll(listOf(*other))
                }
            })))
    }

    fun <T : ComponentItem> attachRenderer(
        customRenderer: ICustomRenderer
    ): NonNullConsumer<T> {
        return if (!Platform.isClient())
            NonNullConsumer.noop<T>()
        else
            NonNullConsumer { item: T -> item.attachComponents(customRenderer) }
    }
}