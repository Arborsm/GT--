package org.arbor.gtnn.block;

import com.gregtechceu.gtceu.GTCEu;
import com.tterrag.registrate.util.entry.BlockEntry;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.arbor.gtnn.api.block.ITier;
import org.arbor.gtnn.api.block.PlantCasingType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

import static com.gregtechceu.gtceu.common.data.GTBlocks.*;
import static org.arbor.gtnn.block.BlockTier.*;

public class PlantCasingBlock extends Block {
    private static final Map<String, PlantCasing> All_PlantCasing = new Object2ObjectOpenHashMap<>();
    private static final Map<Integer, PlantCasing> All_PlantCasing_Tier = new Object2ObjectOpenHashMap<>();

    public PlantCasingBlock(Properties properties) {
        super(properties);
    }

    public enum PlantCasing implements PlantCasingType {
        BRONZE(TIER0, "Bronze", GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks")),
        STEEL(TIER1, "Steel", GTCEu.id("block/casings/solid/machine_casing_solid_steel")),
        ALUMINIUM(TIER2, "Aluminium", GTCEu.id("block/casings/solid/machine_casing_frost_proof")),
        STAINLESS(TIER3, "Stainless", GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel")),
        TITANIUM(TIER4, "Titanium", GTCEu.id("block/casings/solid/machine_casing_stable_titanium")),
        TUNGSTENSTEEL(TIER5, "Tungstensteel", GTCEu.id("block/casings/solid/machine_casing_robust_tungstensteel")),
        CHROME(TIER6, "Chrome", GTCEu.id("block/casings/solid/machine_casing_robust_tungstensteel")),
        IRIDIUM(TIER7, "Iridium", GTCEu.id("block/casings/solid/machine_casing_robust_tungstensteel"));

        private final ITier tier;
        private final String name;
        private final ResourceLocation resourceLocation;

        PlantCasing(ITier tier, String name, ResourceLocation resourceLocation) {
            this.tier = tier;
            this.name = name;
            this.resourceLocation = resourceLocation;
            All_PlantCasing.put(name, this);
            All_PlantCasing_Tier.put(tier.tier(), this);
        }
        @Getter
        private enum PlantCasingBlockEntry{
            BRONZE(TIER0.tier(), CASING_BRONZE_BRICKS),
            STEEL(TIER1.tier(), CASING_STEEL_SOLID),
            ALUMINIUM(TIER2.tier(), CASING_ALUMINIUM_FROSTPROOF),
            STAINLESS(TIER3.tier(), CASING_STAINLESS_CLEAN),
            TITANIUM(TIER4.tier(), CASING_TITANIUM_STABLE),
            TUNGSTENSTEEL(TIER5.tier(), CASING_TUNGSTENSTEEL_ROBUST),
            CHROME(TIER6.tier(), CASING_TUNGSTENSTEEL_ROBUST),
            IRIDIUM(TIER7.tier(), CASING_TUNGSTENSTEEL_ROBUST);

            private final int tier;
            private final BlockEntry<Block> plantCasing;
            PlantCasingBlockEntry(int tier, BlockEntry<Block> plantCasing){
                this.tier = tier;
                this.plantCasing = plantCasing;
            }
        }

        public static PlantCasing getByTier(int tier){
            return All_PlantCasing_Tier.get(tier);
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
        public BlockEntry<Block> getPlantCasing(int tier) {
            return PlantCasingBlockEntry.values()[tier].getPlantCasing();
        }

        @Override
        public ResourceLocation getResourceLocation() {
            return resourceLocation;
        }
    }
}
