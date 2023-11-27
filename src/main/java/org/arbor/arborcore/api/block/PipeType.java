package org.arbor.arborcore.api.block;

import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.Block;

public interface PipeType {
    /**
     * @return the Tier of the Pipe
     */
    int getTier();

    BlockEntry<Block> getPipe();
}
