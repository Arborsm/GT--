package org.arbor.arborcore.block;

import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.Block;
import org.arbor.arborcore.api.block.ITier;
import org.arbor.arborcore.api.block.PipeType;

import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static org.arbor.arborcore.block.BlockTier.*;

public class PipeBlock extends Block {
    public PipeBlock(Properties properties) {
        super(properties);
    }

    public enum Pipe implements PipeType {
        BRONZE(TIER0, CASING_BRONZE_PIPE),
        STEEL(TIER1, CASING_STEEL_PIPE),
        TITANIUM(TIER2, CASING_TITANIUM_PIPE),
        TUNGSTENSTEEL(TIER3, CASING_TUNGSTENSTEEL_PIPE),
        CHROME(TIER4, CASING_TUNGSTENSTEEL_PIPE),
        IRIDIUM(TIER5, CASING_TUNGSTENSTEEL_PIPE),
        OSMIUM(TIER6, CASING_TUNGSTENSTEEL_PIPE),
        NEUTRONIUM(TIER7, CASING_TUNGSTENSTEEL_PIPE);

        private final ITier tier;
        private final BlockEntry<Block> casingBlock;


        Pipe(ITier tier, BlockEntry<Block> casingBlock) {
            this.tier = tier;
            this.casingBlock = casingBlock;
        }

        public PipeType pipeType() {
            return this;
        }

        @Override
        public int getTier() {
            return tier.tier();
        }

        @Override
        public BlockEntry<Block> getPipe() {
            return casingBlock;
        }

        public BlockEntry<Block> getPipe(int tier) {
            return Pipe.values()[tier].getPipe();
        }
    }
}
