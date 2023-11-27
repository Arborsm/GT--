package org.arbor.arborcore.block;

import com.tterrag.registrate.util.entry.BlockEntry;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.world.level.block.Block;
import org.arbor.arborcore.api.block.ITier;
import org.arbor.arborcore.api.block.PlantCasingType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static org.arbor.arborcore.block.BlockTier.*;

public class PlantCasingBlock extends Block {
    private static final Map<String, PlantCasing> All_PlantCasing = new Object2ObjectOpenHashMap<>();

    public PlantCasingBlock(Properties properties) {
        super(properties);
    }

    public enum PlantCasing implements PlantCasingType {
        BRONZE(TIER0, CASING_BRONZE_BRICKS, "Bronze"),
        STEEL(TIER1, CASING_STEEL_SOLID, "Steel"),
        ALUMINIUM(TIER2, CASING_ALUMINIUM_FROSTPROOF, "Aluminium"),
        STAINLESS(TIER3, CASING_STAINLESS_CLEAN, "Stainless"),
        TITANIUM(TIER4, CASING_TITANIUM_STABLE, "Titanium"),
        TUNGSTENSTEEL(TIER5, CASING_TUNGSTENSTEEL_ROBUST, "Tungstensteel"),
        CHROME(TIER6, CASING_TUNGSTENSTEEL_ROBUST, "Chrome"),
        IRIDIUM(TIER7, CASING_TUNGSTENSTEEL_ROBUST, "Iridium");

        private final ITier tier;
        private final BlockEntry<Block> plantCasing;
        private final String name;

        PlantCasing(ITier tier, BlockEntry<Block> plantCasing, String name) {
            this.tier = tier;
            this.plantCasing = plantCasing;
            this.name = name;

            All_PlantCasing.put(name, this);
        }

        @Nullable
        public static PlantCasing getByName(@Nullable String name) {
            return All_PlantCasing.get(name);
        }

        @Nonnull
        public static PlantCasing getByNameOrDefault(@Nullable String name) {
            var type = getByName(name);
            if (type == null) {
                return BRONZE;
            }
            return type;
        }

        public PlantCasingType plantCasingType() {
            return this;
        }

        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public int getTier() {
            return tier.tier();
        }

        @Override
        public BlockEntry<Block> getPlantCasing() {
            return plantCasing;
        }

        public BlockEntry<Block> getPlantCasing(int tier) {
            return PlantCasing.values()[tier].getPlantCasing();
        }
    }
}
