package org.arbor.arborcore.block;

import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.Block;
import org.arbor.arborcore.api.block.ITier;

import java.util.HashMap;
import java.util.Map;

import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static org.arbor.arborcore.block.BlockTier.*;

public class PipeBlock implements ITier {
    public static final Map<ITier, BlockEntry<Block>> AllPipes = new HashMap<>();

    static {
        AllPipes.put(TIER1, CASING_BRONZE_PIPE);
        AllPipes.put(TIER2, CASING_STEEL_PIPE );
        AllPipes.put(TIER3, CASING_TITANIUM_PIPE);
        AllPipes.put(TIER4, CASING_TUNGSTENSTEEL_PIPE);
    }

    @Override
    public int getTier() {
        return 0;
    }
}
