package org.arbor.arborcore.worldgen;


import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconType;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;

import earth.terrarium.ad_astra.common.registry.ModBlocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

public class ATagPrefix{
    public static final TagPrefix oreMoonStone = new TagPrefix("moonstone")
            .langValue("MoonStone %s Ore")
            .materialIconType(MaterialIconType.ore)
            .miningToolTag(BlockTags.MINEABLE_WITH_PICKAXE)
            .unificationEnabled(true)
            .generationCondition(TagPrefix.Conditions.hasOreProperty)
            .registerOre(ModBlocks.MOON_STONE.get()::defaultBlockState, false, MapColor.STONE, SoundType.STONE, false);
    public static final TagPrefix oreBlackStone = new TagPrefix("blackstone")
            .langValue("BlackStone %s Ore")
            .materialIconType(MaterialIconType.ore)
            .miningToolTag(BlockTags.MINEABLE_WITH_PICKAXE)
            .unificationEnabled(true)
            .generationCondition(TagPrefix.Conditions.hasOreProperty)
            .registerOre(Blocks.BLACKSTONE::defaultBlockState, false, MapColor.STONE, SoundType.STONE, false);
    public static void init() {
    }
}
