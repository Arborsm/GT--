package org.arbor.arborcore.worldgen;

import java.util.*;

import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.api.data.worldgen.generator.VeinedVeinGenerator;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;

public class GTOreVein{
    private static final Map<ResourceLocation, GTOreDefinition> toReRegister = new HashMap<>();
    private static GTOreDefinition create(String name, int clusterSize, float density, int weight, AdGenLayers layer, Set<ResourceKey<Level>> dimensionFilter, HeightRangePlacement range) {
        ResourceLocation id = GTCEu.id(name);
        GTOreDefinition def = new GTOreDefinition(id, clusterSize, density, weight, layer, dimensionFilter, range, 0.0F, null, null, null);
        toReRegister.put(id, def);
        return def;
    }
    private static final Set<ResourceLocation> OreVeinsRemoved = new HashSet<>();
    public static final GTOreDefinition COPPER_VEIN_AD;

    static {
        OreVeinsRemoved.addAll(Arrays.asList(
            GTCEu.id("banded_iron_vein"),
            GTCEu.id("manganese_vein"),
            GTCEu.id("monazite_vein"),
            GTCEu.id("redstone_vein"),
            GTCEu.id("topaz_vein"),
            GTCEu.id("bauxite_vein_end"),
            GTCEu.id("magnetite_vein_end"),
            GTCEu.id("naquadah_vein"),
            GTCEu.id("pitchblende_vein_end"),
            GTCEu.id("scheelite_vein"),
            GTCEu.id("sheldonite_vein")
        ));

        COPPER_VEIN_AD = create("copper_vein_ad", 40, 1.0F, 80, AdGenLayers.AD, Registry.AD(), HeightRangePlacement.uniform(VerticalAnchor.absolute(-40), VerticalAnchor.absolute(10)))
                .veinedVeinGenerator()
                .oreBlock(new VeinedVeinGenerator.VeinBlockDefinition(GTMaterials.Chalcopyrite, 5))
                .oreBlock(new VeinedVeinGenerator.VeinBlockDefinition(GTMaterials.Iron, 2))
                .oreBlock(new VeinedVeinGenerator.VeinBlockDefinition(GTMaterials.Pyrite, 2))
                .oreBlock(new VeinedVeinGenerator.VeinBlockDefinition(GTMaterials.Copper, 2))
                .veininessThreshold(0.1F)
                .maxRichnessThreshold(0.4F)
                .minRichness(0.2F)
                .maxRichness(0.5F)
                .edgeRoundoffBegin(12)
                .minYLevel(-40)
                .maxYLevel(-10)
                .parent();
    }
    public static void OreRemove(){
        for (ResourceLocation id : OreVeinsRemoved) {
            GTRegistries.ORE_VEINS.remove(id);
        }
    }

    public static void init() {
        toReRegister.forEach(GTRegistries.ORE_VEINS::registerOrOverride);
    }
}
