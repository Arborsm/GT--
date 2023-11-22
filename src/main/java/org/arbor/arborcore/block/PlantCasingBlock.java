package org.arbor.arborcore.block;

import net.minecraft.world.level.block.Block;
import org.arbor.arborcore.api.block.ITier;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static org.arbor.arborcore.block.BlockTier.*;

public class PlantCasingBlock{
    public static final Map<ITier, Supplier<Block>> AllPlantCasings = new HashMap<>();

    static {
        AllPlantCasings.put(TIER1, CASING_BRONZE_BRICKS);
        AllPlantCasings.put(TIER2, CASING_STEEL_SOLID);
        AllPlantCasings.put(TIER3, CASING_ALUMINIUM_FROSTPROOF);
        AllPlantCasings.put(TIER4, CASING_STAINLESS_CLEAN);
        AllPlantCasings.put(TIER5, CASING_TITANIUM_STABLE);
        AllPlantCasings.put(TIER6, CASING_TUNGSTENSTEEL_ROBUST);
    }
}
