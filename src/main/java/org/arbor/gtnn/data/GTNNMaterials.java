package org.arbor.gtnn.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.FluidProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.OreProperty;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.PropertyKey;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

@SuppressWarnings("unused")
public class GTNNMaterials {
    // MaterialIconSet
    public static final MaterialIconSet InfinityIcon = new MaterialIconSet("infinity", SHINY);
    // region first degree mats
    public static final Material AndesiteAlloy = Builder("andesite_alloy")
            .ingot().fluid()
            .color(0x99B09F).iconSet(DULL)
            .buildAndRegister()
            .setFormula("(Mg3Si2H4O9)4(KNO3)Fe");
    public static final Material Desh = Builder("desh")
            .ingot().fluid().ore()
            .color(0xF2A057).secondaryColor(0x2E2F04).iconSet(METALLIC)
            .appendFlags(EXT2_METAL, GENERATE_ROTOR, GENERATE_DENSE, GENERATE_SMALL_GEAR)
            .buildAndRegister();
    public static final Material Ostrum = Builder("ostrum")
            .ingot().fluid().ore()
            .color(0xE5939B).secondaryColor(0x2F0425).iconSet(METALLIC)
            .appendFlags(EXT2_METAL, GENERATE_ROTOR, GENERATE_DENSE, GENERATE_SMALL_GEAR)
            .buildAndRegister();
    public static final Material Calorite = Builder("calorite")
            .ingot().fluid().ore()
            .color(0xE65757).secondaryColor(0x2F0506).iconSet(METALLIC)
            .appendFlags(EXT2_METAL, GENERATE_ROTOR, GENERATE_DENSE, GENERATE_SMALL_GEAR)
            .buildAndRegister();
    public static final Material SpaceNeutronium = Builder("space_neutronium")
            .ingot().fluid().ore().dust()
            .color(0x11111b).iconSet(SHINY)
            .element(GTNNElement.SpNt)
            .blastTemp(9900, BlastProperty.GasTier.HIGH, GTValues.VA[GTValues.ZPM], 21825)
            .buildAndRegister();
    public static final Material Infinity = Builder("infinity")
            .ingot().fluid().ore().dust()
            .color(0xFFFFFF).iconSet(InfinityIcon)
            .element(GTNNElement.IF2)
            .blastTemp(10800, BlastProperty.GasTier.HIGHEST, GTValues.VA[GTValues.UHV], 54562)
            .buildAndRegister();
    public static final Material InfinityCatalyst = Builder("infinity_catalyst")
            .dust().ore()
            .color(0xE5E2E1).iconSet(SAND)
            .element(GTNNElement.IF)
            .buildAndRegister();
    // Chemical Plant
    public static final Material RP1 = Builder("rp_1_mixed_fuel")
            .fluid()
            .color(0xC02928).iconSet(DULL)
            .buildAndRegister();
    public static final Material RP1RocketFuel = Builder("rp_1_rocket_fuel")
            .fluid()
            .color(0x9E2A2A).iconSet(DULL)
            .buildAndRegister();
    public static final Material Kerosene = Builder("kerosene")
            .fluid()
            .color(0x752275).iconSet(DULL)
            .buildAndRegister();
    public static final Material DenseHydrazineMixedFuel = Builder("dense_hydrazine_mixed_fuel")
            .fluid()
            .color(0x833D59).iconSet(DULL)
            .buildAndRegister();
    public static final Material Hydrazine = Builder("hydrazine")
            .fluid()
            .color(0xBBBBBB).iconSet(DULL)
            .buildAndRegister();
    public static final Material HydrogenPeroxide = Builder("hydrogen_peroxide")
            .fluid()
            .color(0xC3EDED).iconSet(DULL)
            .buildAndRegister();
    public static final Material EthylAnthraQuinone = Builder("ethyl_anthra_quinone")
            .fluid()
            .color(0xAABE77).iconSet(DULL)
            .buildAndRegister();
    public static final Material EthylAnthraHydroQuinone = Builder("ethyl_anthra_hydro_quinone")
            .fluid()
            .color(0xC9E08D).iconSet(DULL)
            .buildAndRegister();
    public static final Material Anthracene = Builder("anthracene")
            .fluid()
            .color(0xBBBABA).iconSet(DULL)
            .buildAndRegister();
    public static final Material MethylhydrazineNitrateRocketFuel = Builder("methylhydrazine_nitrate_rocket_fuel")
            .fluid()
            .color(0x613B87).iconSet(DULL)
            .buildAndRegister();
    public static final Material MethylHydrazine = Builder("methyl_hydrazine")
            .fluid()
            .color(0x606060).iconSet(DULL)
            .buildAndRegister();
    public static final Material UDMHRocketFuel = Builder("udmh_rocket_fuel")
            .fluid()
            .color(0x2AA327).iconSet(DULL)
            .buildAndRegister();
    public static final Material UDMH = Builder("udmh")
            .fluid()
            .color(0x050543).iconSet(DULL)
            .buildAndRegister();
    public static final Material OrangeMetalCatalyst = Builder("orange_metal_catalyst")
            .dust()
            .color(0xfa7e23).iconSet(ROUGH)
            .buildAndRegister();
    public static final Material PhthalicAnhydride = Builder("phthalic_anhydride")
            .dust()
            .color(0x6C863A).iconSet(ROUGH)
            .buildAndRegister();
    public static final Material VanadiumPentoxide = Builder("vanadium_pentoxide")
            .dust()
            .components(Vanadium, 2, Oxygen, 5)
            .color(0xB5730F).iconSet(METALLIC)
            .buildAndRegister();
    public static final Material BlackMatter = Builder("black_matter")
            .dust().ingot().fluid()
            .components(Lead, 3, Manganese, 5, Carbon, 12)
            .color(0x000000).iconSet(DULL)
            .appendFlags(EXT_METAL, GENERATE_FRAME)
            .buildAndRegister();
    public static final Material Cerrobase140 = Builder("cerrobase_140")
            .dust().fluid()
            .components(Bismuth, 47, Lead, 25, Tin, 13, Cadmium, 10, Indium, 5)
            .color(0x9e9e9e).iconSet(METALLIC)
            .blastTemp(1230)
            .buildAndRegister();
    public static final Material SodiumPyrosulfate = Builder("sodium_pyrosulfate")
            .dust().fluid(FluidStorageKeys.MOLTEN, new FluidBuilder())
            .components(Potassium, 2, Sulfur, 2, Oxygen, 7)
            .color(0xff9900).iconSet(METALLIC)
            .buildAndRegister();
    public static final Material SodiumSulfate = Builder("sodium_sulfate")
            .dust()
            .components(Sodium, 2, Sulfur, 1, Oxygen, 4)
            .color(0xF9F6CF).iconSet(SAND)
            .buildAndRegister();
    public static final Material ZincSulfate = Builder("zinc_sulfate")
            .dust()
            .components(Zinc, 1, Sulfur, 1, Oxygen, 4)
            .color(0x533c1b).iconSet(SAND)
            .buildAndRegister();
    public static final Material Wollastonite = Builder("wollastonite")
            .dust().ore()
            .components(Calcium, 1, Silicon, 1, Oxygen, 3)
            .color(0xc4cbcf).iconSet(SAND)
            .buildAndRegister();
    public static final Material ArcaneCrystal = Builder("arcane_crystal")
            .dust().ore().gem()
            .color(0x93AEFF).iconSet(DIAMOND)
            .buildAndRegister();
    // Ingot
    public static final Material ManaSteel = Builder("mana_steel")
            .ingot().fluid()
            .color(0x2177b8).iconSet(SHINY)
            .appendFlags(EXT2_METAL, GENERATE_FINE_WIRE, GENERATE_GEAR, GENERATE_FRAME)
            .buildAndRegister();
    public static final Material TerraSteel = Builder("terra_steel")
            .ingot().fluid()
            .color(0x5dbe8a).iconSet(SHINY)
            .appendFlags(EXT2_METAL, GENERATE_FINE_WIRE, GENERATE_GEAR, GENERATE_FRAME)
            .buildAndRegister();
    public static final Material Elementium = Builder("elementium")
            .ingot().fluid()
            .color(0xFFB3D6).iconSet(SHINY)
            .appendFlags(EXT2_METAL, GENERATE_FINE_WIRE, GENERATE_GEAR, GENERATE_FRAME)
            .buildAndRegister();
    public static final Material RefinedRadiance = Builder("refined_radiance")
            .ingot().fluid()
            .color(0xfffef9).iconSet(METALLIC)
            .appendFlags(EXT2_METAL, GENERATE_FINE_WIRE, GENERATE_GEAR, GENERATE_FRAME)
            .buildAndRegister();
    public static final Material ShadowSteel = Builder("shadow_steel")
            .ingot().fluid()
            .color(0x35333c).iconSet(METALLIC)
            .appendFlags(EXT2_METAL, GENERATE_FINE_WIRE, GENERATE_GEAR, GENERATE_FRAME)
            .buildAndRegister();
    // Platinum
    public static final Material PlatinumSalt = Builder("platinum_salt")
            .dust()
            .color(0xEEF2AE).iconSet(SAND)
            .flags(DISABLE_DECOMPOSITION)
            .buildAndRegister();
    public static final Material RefinedPlatinumSalt = Builder("refined_platinum_salt")
            .dust()
            .color(0xFFFEC2).iconSet(SAND)
            .flags(DISABLE_DECOMPOSITION)
            .buildAndRegister();
    public static final Material PalladiumSalt = Builder("palladium_salt")
            .dust()
            .color(0x33302D).iconSet(SAND)
            .flags(DISABLE_DECOMPOSITION)
            .buildAndRegister();
    public static final Material RhodiumNitrate = Builder("rhodium_nitrate")
            .dust()
            .color(0x8C5A0C).iconSet(SAND)
            .flags(DISABLE_DECOMPOSITION)
            .buildAndRegister();
    public static final Material RoughlyRhodiumMetal = Builder("roughly_rhodium_metal")
            .dust()
            .color(0x594C1A).iconSet(SAND)
            .flags(DISABLE_DECOMPOSITION)
            .buildAndRegister();
    public static final Material PalladiumMetal = Builder("palladium_metal")
            .dust()
            .color(0x30302E).iconSet(SAND)
            .flags(DISABLE_DECOMPOSITION)
            .buildAndRegister();
    public static final Material MetalSludge = Builder("metal_sludge")
            .dust()
            .color(0x362605).iconSet(SAND)
            .buildAndRegister();
    public static final Material PlatinumSlag = Builder("platinum_slag")
            .dust()
            .color(0x343318).iconSet(SAND)
            .flags(DISABLE_DECOMPOSITION)
            .buildAndRegister();
    public static final Material ReprecipitatedRhodium = Builder("reprecipitated_rhodium")
            .dust()
            .color(0xD40849).iconSet(SAND)
            .flags(DISABLE_DECOMPOSITION)
            .buildAndRegister();
    public static final Material SodiumNitrate = Builder("sodium_nitrate")
            .dust()
            .color(0x4e2a40).iconSet(SAND)
            .flags(DISABLE_DECOMPOSITION)
            .buildAndRegister();
    public static final Material RhodiumSalt = Builder("rhodium_salt")
            .dust().fluid()
            .color(0x61200A).iconSet(SAND)
            .flags(DISABLE_DECOMPOSITION)
            .buildAndRegister();
    public static final Material RhodiumFilterCake = Builder("rhodium_filter_cake")
            .dust().fluid()
            .color(0x87350C).iconSet(ROUGH)
            .flags(DISABLE_DECOMPOSITION)
            .buildAndRegister();
    public static final Material PlatinumMetal = Builder("platinum_metal")
            .dust()
            .color(0xEBEBB2).iconSet(ROUGH)
            .flags(DISABLE_DECOMPOSITION)
            .buildAndRegister();
    public static final Material Kaolinite = Builder("kaolinite")
            .dust().ore()
            .color(0x969090).iconSet(ROUGH)
            .flags(DISABLE_DECOMPOSITION)
            .buildAndRegister();
    public static final Material Dolomite = Builder("dolomite")
            .dust().ore()
            .color(0x9F9191).iconSet(ROUGH)
            .flags(DISABLE_DECOMPOSITION)
            .buildAndRegister();
    public static final Material SodiumRutheniate = Builder("sodium_rutheniate")
            .dust()
            .color(0x282C8C).iconSet(METALLIC)
            .flags(DISABLE_DECOMPOSITION)
            .buildAndRegister();
    public static final Material IridiumDioxide = Builder("iridium_dioxide")
            .dust()
            .color(0xA2BFFF).iconSet(METALLIC)
            .flags(DISABLE_DECOMPOSITION)
            .buildAndRegister();
    public static final Material ConcentratedPlatinum = Builder("concentrated_platinum")
            .fluid()
            .color(0xC3C39A).iconSet(ROUGH)
            .buildAndRegister();
    public static final Material PalladiumRichAmmonia = Builder("palladium_rich_ammonia")
            .fluid()
            .color(0x676767).iconSet(ROUGH)
            .buildAndRegister();
    public static final Material RutheniumTetroxideLQ = Builder("ruthenium_tetroxide_lq")
            .fluid()
            .color(0xA8A8A8).iconSet(ROUGH)
            .buildAndRegister();
    public static final Material SodiumFormate = Builder("sodium_formate")
            .fluid()
            .color(0xf1939c).iconSet(ROUGH)
            .buildAndRegister();
    public static final Material FormicAcid = Builder("formic_acid")
            .fluid()
            .color(0xf8c387).iconSet(ROUGH)
            .buildAndRegister();
    public static final Material RhodiumSulfateGas = Builder("rhodium_sulfate_gas")
            .gas()
            .color(0xBD8743).iconSet(ROUGH)
            .buildAndRegister();
    public static final Material AcidicIridium = Builder("acidic_iridium")
            .gas()
            .color(0x634E3A).iconSet(ROUGH)
            .buildAndRegister();
    public static final Material RutheniumTetroxideHot = Builder("ruthenium_tetroxide_hot")
            .gas()
            .color(0x9B9B9B).iconSet(ROUGH)
            .buildAndRegister();
    // Naquadah
    public static final Material NaquadahOxideMixture = Builder("naquadah_oxide_mixture")
            .dust()
            .color(0x4c4c4c).iconSet(ROUGH)
            .buildAndRegister()
            .setFormula("??NqTiGaAd??");
    public static final Material EnrichedNaquadahOxideMixture = Builder("enriched_naquadah_oxide_mixture")
            .dust()
            .color(0x826868).iconSet(ROUGH)
            .buildAndRegister()
            .setFormula("??KeNq+??");
    public static final Material NaquadriaOxideMixture = Builder("naquadria_oxide_mixture")
            .dust()
            .color(0x4d4d55).secondaryColor(0xe7e7ff)
            .iconSet(RADIOACTIVE)
            .buildAndRegister()
            .setFormula("??*Nq*BaIn??");

    public static void init() {
        RhodiumFilterCake.setFormula("?Ru?");
        PlatinumMetal.setFormula("??PtPdIrOsRhRu??");
        PalladiumMetal.setFormula("??Pd??");
        SodiumRutheniate.setFormula("Na2RbO3");
        IridiumDioxide.setFormula("IrO2");
        MetalSludge.setFormula("NiCu");
        PlatinumSlag.setFormula("??IrOsRhRb??");
        ReprecipitatedRhodium.setFormula("Rh2NH40");
        AcidicIridium.setFormula("???");
        SodiumNitrate.setFormula("Na2NO3");
        RoughlyRhodiumMetal.setFormula("??Rh??");
        addOre(Neutronium);
        addOre(Perlite);
        addOre(Uvarovite);
        addOre(Andradite);
        addOre(Arsenic);
        addOre(Bismuth);
        addOre(Antimony);
        addOre(Uranium235);
        addOre(Uranium238);
        addOre(Plutonium241);
        addOre(Gallium);
        addOre(Niobium);
        addOre(Vanadium);
        addOre(Osmium);
        addOre(Iridium);
        addOre(Titanium);
        addOre(Manganese);
        addOre(Rutile);
        addOre(Tungsten);
        addOre(Chromium);
        addOre(NaquadahEnriched);
        addOre(Naquadria);
        addFluid(RutheniumTetroxide);
        addFluid(OsmiumTetroxide);
        addFluid(AmmoniumChloride);
        addFluid(CalciumChloride);
    }
    private static void addFluid(Material material){
        material.setProperty(PropertyKey.FLUID, new FluidProperty());
        material.getProperty(PropertyKey.FLUID).getStorage().enqueueRegistration(FluidStorageKeys.LIQUID, new FluidBuilder());
    }
    private static void addOre(Material material){
        material.setProperty(PropertyKey.ORE, new OreProperty());
    }
    private static Material.Builder Builder(String id) {
        return new Material.Builder(GTCEu.id(id));
    }
}
