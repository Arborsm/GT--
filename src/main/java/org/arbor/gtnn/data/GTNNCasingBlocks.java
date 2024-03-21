package org.arbor.gtnn.data;

import com.gregtechceu.gtceu.api.block.RendererBlock;
import com.gregtechceu.gtceu.api.item.RendererBlockItem;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.client.renderer.block.TextureOverrideRenderer;
import com.lowdragmc.lowdraglib.Platform;
import com.lowdragmc.lowdraglib.client.renderer.IRenderer;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullBiConsumer;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.arbor.gtnn.GTNN;

import java.util.Map;
import java.util.function.BiFunction;

import static org.arbor.gtnn.GTNNRegistries.REGISTRATE;

public class GTNNCasingBlocks {
    static {
        REGISTRATE.creativeModeTab(() -> GTNNCreativeModeTabs.MAIN_TAB);
    }

    public static final BlockEntry<Block> PROCESS_MACHINE_CASING = createCasingBlock(
            "clean_machine_casing", "Clean Machine Casing", RendererBlock::new,
            GTNN.id("block/casings/solid/process_machine_casing"), () -> Blocks.IRON_BLOCK);

    public static final BlockEntry<Block> RADIATION_PROOF_MACHINE_CASING = createCasingBlock(
            "radiation_proof_machine_casing", "Radiation Proof Machine Casing", RendererBlock::new,
            GTNN.id("block/casings/solid/radiation_proof_machine_casing"), () -> Blocks.IRON_BLOCK);

    public static final BlockEntry<Block> MAR_CASING = createCasingBlock(
            "mar_casing", "Field Restriction Casing", RendererBlock::new,
            GTNN.id("block/casings/solid/mar_casing"), () -> Blocks.IRON_BLOCK);

    @SuppressWarnings("all")
    private static BlockEntry<Block> createCasingBlock(
            String name, String displayName,
            BiFunction<BlockBehaviour.Properties, IRenderer, ? extends RendererBlock> blockSupplier,
            ResourceLocation texture, NonNullSupplier<? extends Block> properties) {
        return REGISTRATE.block(name, p -> (Block) blockSupplier.apply(p,
                        Platform.isClient() ? new TextureOverrideRenderer(new ResourceLocation("block/cube_all"),
                                Map.of("all", texture)) : null))
                .initialProperties(properties)
                .lang(displayName)
                .blockstate(NonNullBiConsumer.noop())
                .tag(GTToolType.WRENCH.harvestTags.get(0), BlockTags.MINEABLE_WITH_PICKAXE)
                .item(RendererBlockItem::new)
                .model(NonNullBiConsumer.noop())
                .build()
                .register();
    }

    public static void init(){
    }
}
