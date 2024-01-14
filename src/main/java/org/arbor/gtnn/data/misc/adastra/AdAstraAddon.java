package org.arbor.gtnn.data.misc.adastra;

import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconType;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import earth.terrarium.ad_astra.common.registry.ModBlocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.MapColor;

@SuppressWarnings("unused")
public class AdAstraAddon {
    public static final TagPrefix oreMoonStone = TagPrefix.oreTagPrefix("moon_stone")
            .langValue("MoonStone %s Ore")
            .materialIconType(MaterialIconType.ore)
            .miningToolTag(BlockTags.MINEABLE_WITH_PICKAXE)
            .unificationEnabled(true)
            .generationCondition(TagPrefix.Conditions.hasOreProperty)
            .registerOre(() -> ModBlocks.MOON_STONE.get().defaultBlockState(), false, MapColor.STONE, SoundType.STONE, false);
    public static final TagPrefix oreVenusStone = TagPrefix.oreTagPrefix("venus_stone")
            .langValue("VenusStone %s Ore")
            .materialIconType(MaterialIconType.ore)
            .miningToolTag(BlockTags.MINEABLE_WITH_PICKAXE)
            .unificationEnabled(true)
            .generationCondition(TagPrefix.Conditions.hasOreProperty)
            .registerOre(() -> ModBlocks.VENUS_STONE.get().defaultBlockState(), false, MapColor.STONE, SoundType.STONE, false);

    public static final TagPrefix oreMarsStone = TagPrefix.oreTagPrefix("mars_stone")
            .langValue("MarsStone %s Ore")
            .materialIconType(MaterialIconType.ore)
            .miningToolTag(BlockTags.MINEABLE_WITH_PICKAXE)
            .unificationEnabled(true)
            .generationCondition(TagPrefix.Conditions.hasOreProperty)
            .registerOre(() -> ModBlocks.MARS_STONE.get().defaultBlockState(), false, MapColor.STONE, SoundType.STONE, false);

    public static final TagPrefix oreMercuryStone = TagPrefix.oreTagPrefix("mercury_stone")
            .langValue("MercuryStone %s Ore")
            .materialIconType(MaterialIconType.ore)
            .miningToolTag(BlockTags.MINEABLE_WITH_PICKAXE)
            .unificationEnabled(true)
            .generationCondition(TagPrefix.Conditions.hasOreProperty)
            .registerOre(() -> ModBlocks.MERCURY_STONE.get().defaultBlockState(), false, MapColor.STONE, SoundType.STONE, false);

    public static final TagPrefix oreGlacioStone = TagPrefix.oreTagPrefix("glacio_stone")
            .langValue("GlacioStone %s Ore")
            .materialIconType(MaterialIconType.ore)
            .miningToolTag(BlockTags.MINEABLE_WITH_PICKAXE)
            .unificationEnabled(true)
            .generationCondition(TagPrefix.Conditions.hasOreProperty)
            .registerOre(() -> ModBlocks.GLACIO_STONE.get().defaultBlockState(), false, MapColor.STONE, SoundType.STONE, false);

    public static final TagPrefix oreBlackStone = TagPrefix.oreTagPrefix("blackstone")
            .langValue("BlackStone %s Ore")
            .materialIconType(MaterialIconType.ore)
            .miningToolTag(BlockTags.MINEABLE_WITH_PICKAXE)
            .unificationEnabled(true)
            .generationCondition(TagPrefix.Conditions.hasOreProperty)
            .registerOre(Blocks.BLACKSTONE::defaultBlockState, false, MapColor.STONE, SoundType.STONE, false);

    public static final TagPrefix oreSoulSoil = TagPrefix.oreTagPrefix("soul_soil")
            .langValue("SoulSoil %s Ore")
            .materialIconType(MaterialIconType.ore)
            .miningToolTag(BlockTags.MINEABLE_WITH_SHOVEL)
            .unificationEnabled(true)
            .generationCondition(TagPrefix.Conditions.hasOreProperty)
            .registerOre(Blocks.SOUL_SOIL::defaultBlockState, false, MapColor.DIRT, SoundType.SOUL_SOIL, true);

    public static void init() {
    }
}
