package org.arbor.arborcore.block;

import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.world.level.block.Block;
import org.arbor.arborcore.api.block.IPlantCasing;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class PlantCasingBlock{
    public static final Map<IPlantCasing, Supplier<Block>> AllPlantCasings = new HashMap<>();

    static {
        AllPlantCasings.put(PlantCasings.CASING_BRONZE_BRICKS, GTBlocks.CASING_BRONZE_BRICKS);
        AllPlantCasings.put(PlantCasings.CASING_STEEL_SOLID, GTBlocks.CASING_STEEL_SOLID);
        AllPlantCasings.put(PlantCasings.CASING_ALUMINIUM_FROSTPROOF, GTBlocks.CASING_ALUMINIUM_FROSTPROOF);
        AllPlantCasings.put(PlantCasings.CASING_STAINLESS_CLEAN, GTBlocks.CASING_STAINLESS_CLEAN);
        AllPlantCasings.put(PlantCasings.CASING_TITANIUM_STABLE, GTBlocks.CASING_TITANIUM_STABLE);
        AllPlantCasings.put(PlantCasings.CASING_TUNGSTENSTEEL_ROBUST, GTBlocks.CASING_TUNGSTENSTEEL_ROBUST);
    }

    public enum PlantCasings implements IPlantCasing {
        CASING_BRONZE_BRICKS (1, GTBlocks.CASING_BRONZE_BRICKS),
        CASING_STEEL_SOLID (2, GTBlocks.CASING_STEEL_SOLID),
        CASING_ALUMINIUM_FROSTPROOF (3, GTBlocks.CASING_ALUMINIUM_FROSTPROOF),
        CASING_STAINLESS_CLEAN (4, GTBlocks.CASING_STAINLESS_CLEAN),
        CASING_TITANIUM_STABLE (5, GTBlocks.CASING_TITANIUM_STABLE),
        CASING_TUNGSTENSTEEL_ROBUST (6, GTBlocks.CASING_TUNGSTENSTEEL_ROBUST);

        private final int tier;
        private final BlockEntry<Block> casingBlock;

        PlantCasings(int tier, BlockEntry<Block> casingBlock) {
            this.tier = tier;
            this.casingBlock = casingBlock;
        }

        @Override
        public int getTier() {
            return tier;
        }

        @Override
        public BlockEntry<Block> getCasingBlock() {
            return casingBlock;
        }
    }
}
