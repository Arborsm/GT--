package org.arbor.arborcore.block;

import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.Block;
import org.arbor.arborcore.api.block.ITier;

import java.util.HashMap;
import java.util.Map;

import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static org.arbor.arborcore.block.BlockTier.*;

public class MachineCasing implements ITier {
    public static final Map<ITier, BlockEntry<Block>> AllMachineCasing = new HashMap<>();

    static {
        AllMachineCasing.put(TIER1, MACHINE_CASING_LV);
        AllMachineCasing.put(TIER2, MACHINE_CASING_MV);
        AllMachineCasing.put(TIER3, MACHINE_CASING_HV);
        AllMachineCasing.put(TIER4, MACHINE_CASING_EV);
        AllMachineCasing.put(TIER5, MACHINE_CASING_IV);
        AllMachineCasing.put(TIER6, MACHINE_CASING_LuV);
        AllMachineCasing.put(TIER7, MACHINE_CASING_ZPM);
    }

    @Override
    public int getTier() {
        return 0;
    }
}
