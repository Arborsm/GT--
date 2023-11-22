package org.arbor.arborcore.api.block;

import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.Block;

public interface IPlantCasing {
    /**
     * @return the Tier of this casing as an integer
     */
    int getTier();

    /**
     * @return the casing block
     */
    BlockEntry<Block> getCasingBlock();
}
