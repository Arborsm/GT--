package org.arbor.arborcore.worldgen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

import java.util.Set;

public class Registry {
    public static final TagKey<Fluid> TIER1 = TagKey.create(Registries.FLUID, new ResourceLocation("arbor:tier1"));
    public static final TagKey<Fluid> TIER2 = TagKey.create(Registries.FLUID, new ResourceLocation("arbor:tier2"));
    public static final TagKey<Fluid> TIER3 = TagKey.create(Registries.FLUID, new ResourceLocation("arbor:tier3"));
    public static final TagKey<Fluid> TIER4 = TagKey.create(Registries.FLUID, new ResourceLocation("arbor:tier4"));
    public static final TagKey<Block> ADSTONES = TagKey.create(Registries.BLOCK,new ResourceLocation("arbor:ad_stones"));
    public static Set<ResourceKey<Level>> AD(){
        return Set.of(MOON);
    }

    public static final ResourceKey<Level> MOON = ResourceKey.create(Registries.DIMENSION, new ResourceLocation("ad_astra:moon"));
}
