package org.arbor.arborcore.worldgen;

import com.gregtechceu.gtceu.api.addon.AddonFinder;
import com.gregtechceu.gtceu.api.addon.IGTAddon;
import com.gregtechceu.gtceu.api.data.worldgen.IWorldGenLayer;
import com.gregtechceu.gtceu.api.data.worldgen.WorldGeneratorUtils;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public enum AdGenLayers implements IWorldGenLayer, StringRepresentable {
    AD(
            "ad", new TagMatchTest(Registry.ADSTONES),
            Set.of(Registry.MOON.location())
    );

    private final String name;

    @SuppressWarnings("NonFinalFieldInEnum")
    @Getter
    @Setter
    private Set<ResourceLocation> levels;

    @SuppressWarnings("NonFinalFieldInEnum")
    @Getter
    @Setter
    private RuleTest target;

    AdGenLayers(String name, RuleTest target, Set<ResourceLocation> levels) {
        this.name = name;
        this.target = target;
        this.levels = levels;
        WorldGeneratorUtils.WORLD_GEN_LAYERS.put(name, this);
    }

    public static void registerAll() {
        AddonFinder.getAddons().forEach(IGTAddon::registerWorldgenLayers);
    }

    @Override
    public boolean isApplicableForLevel(ResourceLocation level) {
        return levels.contains(level);
    }

    @Override
    @NotNull
    public String getSerializedName() {
        return name;
    }
}
