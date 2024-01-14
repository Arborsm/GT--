package org.arbor.gtnn.data;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

public class GTNNTags {
    public static final TagKey<Fluid> TIER1 = TagKey.create(Registries.FLUID, new ResourceLocation("gtnn:tier1"));
    public static final TagKey<Fluid> TIER2 = TagKey.create(Registries.FLUID, new ResourceLocation("gtnn:tier2"));
    public static final TagKey<Fluid> TIER3 = TagKey.create(Registries.FLUID, new ResourceLocation("arbor:tier3"));
    public static final TagKey<Fluid> TIER4 = TagKey.create(Registries.FLUID, new ResourceLocation("arbor:tier4"));
}
