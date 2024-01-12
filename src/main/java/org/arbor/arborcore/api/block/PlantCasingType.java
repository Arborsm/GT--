package org.arbor.arborcore.api.block;

import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

public interface PlantCasingType {
    /**
     * @return the Tier of the Plant Casing
     */
    int getTier();

    /**
     * @return Block of the Plant Casing
     */
    BlockEntry<Block> getPlantCasing();

    /**
     * @return the Name of the Plant Casing
     */
    String getName();

    /**
     * @return the ResourceLocation of the Plant Casing
     */
    ResourceLocation getResourceLocation();
}
