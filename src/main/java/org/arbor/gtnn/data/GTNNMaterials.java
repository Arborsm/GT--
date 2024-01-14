package org.arbor.gtnn.data;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;

@SuppressWarnings("unused")
public class GTNNMaterials {
    // MaterialIconSet
    public static final MaterialIconSet InfinityIcon = new MaterialIconSet("infinity", MaterialIconSet.SHINY);
    // region first degree mats
    public static final Material AndesiteAlloy = new Material.Builder("andesite_alloy")
            .ingot().fluid()
            .color(0x99B09F).iconSet(MaterialIconSet.DULL)
            .buildAndRegister()
            .setFormula("(Mg3Si2H4O9)4(KNO3)Fe");
    public static final Material Desh = new Material.Builder("desh")
            .ingot().fluid().ore()
            .color(0xF2A057).secondaryColor(0x2E2F04).iconSet(MaterialIconSet.METALLIC)
            .appendFlags(EXT2_METAL, GENERATE_ROTOR, GENERATE_DENSE, GENERATE_SMALL_GEAR)
            .buildAndRegister();
    public static final Material Ostrum = new Material.Builder("ostrum")
            .ingot().fluid().ore()
            .color(0xE5939B).secondaryColor(0x2F0425).iconSet(MaterialIconSet.METALLIC)
            .appendFlags(EXT2_METAL, GENERATE_ROTOR, GENERATE_DENSE, GENERATE_SMALL_GEAR)
            .buildAndRegister();
    public static final Material Calorite = new Material.Builder("calorite")
            .ingot().fluid().ore()
            .color(0xE65757).secondaryColor(0x2F0506).iconSet(MaterialIconSet.METALLIC)
            .appendFlags(EXT2_METAL, GENERATE_ROTOR, GENERATE_DENSE, GENERATE_SMALL_GEAR)
            .buildAndRegister();
    public static final Material SpaceNeutronium = new Material.Builder("space_neutronium")
            .ingot().fluid().ore().dust()
            .color(0x11111b).iconSet(MaterialIconSet.SHINY)
            .element(GTNNElement.SpNt)
            .blastTemp(9900, BlastProperty.GasTier.HIGH, GTValues.VA[GTValues.ZPM], 21825)
            .buildAndRegister();
    public static final Material Infinity = new Material.Builder("infinity")
            .ingot().fluid().ore().dust()
            .color(0xFFFFFF).iconSet(InfinityIcon)
            .element(GTNNElement.IF2)
            .blastTemp(10800, BlastProperty.GasTier.HIGHEST, GTValues.VA[GTValues.UHV], 54562)
            .buildAndRegister();
    public static final Material InfinityCatalyst = new Material.Builder("infinity_catalyst")
            .dust().ore()
            .color(0xE5E2E1).iconSet(MaterialIconSet.SAND)
            .element(GTNNElement.IF)
            .buildAndRegister();
    // Chemical Plant
    public static final Material RP1 = new Material.Builder("rp_1_mixed_fuel")
            .fluid()
            .color(0xC02928).iconSet(MaterialIconSet.DULL)
            .buildAndRegister();
    public static final Material RP1RocketFuel = new Material.Builder("rp_1_rocket_fuel")
            .fluid()
            .color(0x9E2A2A).iconSet(MaterialIconSet.DULL)
            .buildAndRegister();
    public static final Material Kerosene = new Material.Builder("kerosene")
            .fluid()
            .color(0x752275).iconSet(MaterialIconSet.DULL)
            .buildAndRegister();
    public static final Material DenseHydrazineMixedFuel = new Material.Builder("dense_hydrazine_mixed_fuel")
            .fluid()
            .color(0x833D59).iconSet(MaterialIconSet.DULL)
            .buildAndRegister();
    public static final Material Hydrazine = new Material.Builder("hydrazine")
            .fluid()
            .color(0xBBBBBB).iconSet(MaterialIconSet.DULL)
            .buildAndRegister();
    public static final Material HydrogenPeroxide = new Material.Builder("hydrogen_peroxide")
            .fluid()
            .color(0xC3EDED).iconSet(MaterialIconSet.DULL)
            .buildAndRegister();
    public static final Material EthylAnthraQuinone = new Material.Builder("ethyl_anthra_quinone")
            .fluid()
            .color(0xAABE77).iconSet(MaterialIconSet.DULL)
            .buildAndRegister();
    public static final Material EthylAnthraHydroQuinone = new Material.Builder("ethyl_anthra_hydro_quinone")
            .fluid()
            .color(0xC9E08D).iconSet(MaterialIconSet.DULL)
            .buildAndRegister();
    public static final Material Anthracene = new Material.Builder("anthracene")
            .fluid()
            .color(0xBBBABA).iconSet(MaterialIconSet.DULL)
            .buildAndRegister();
    public static final Material MethylhydrazineNitrateRocketFuel = new Material.Builder("methylhydrazine_nitrate_rocket_fuel")
            .fluid()
            .color(0x613B87).iconSet(MaterialIconSet.DULL)
            .buildAndRegister();
    public static final Material MethylHydrazine = new Material.Builder("methyl_hydrazine")
            .fluid()
            .color(0x606060).iconSet(MaterialIconSet.DULL)
            .buildAndRegister();
    public static final Material UDMHRocketFuel = new Material.Builder("udmh_rocket_fuel")
            .fluid()
            .color(0x2AA327).iconSet(MaterialIconSet.DULL)
            .buildAndRegister();
    public static final Material UDMH = new Material.Builder("udmh")
            .fluid()
            .color(0x050543).iconSet(MaterialIconSet.DULL)
            .buildAndRegister();
    public static final Material OrangeMetalCatalyst = new Material.Builder("orange_metal_catalyst")
            .dust()
            .color(0xfa7e23).iconSet(MaterialIconSet.ROUGH)
            .buildAndRegister();
    public static final Material PhthalicAnhydride = new Material.Builder("phthalic_anhydride")
            .dust()
            .color(0x6C863A).iconSet(MaterialIconSet.ROUGH)
            .buildAndRegister();
    public static final Material VanadiumPentoxide = new Material.Builder("vanadium_pentoxide")
            .dust()
            .components(Vanadium, 2, Oxygen, 5)
            .color(0xB5730F).iconSet(MaterialIconSet.METALLIC)
            .buildAndRegister();
    public static final Material BlackMatter = new Material.Builder("black_matter")
            .dust().ingot().fluid()
            .components(Lead, 3, Manganese, 5, Carbon, 12)
            .color(0x000000).iconSet(MaterialIconSet.DULL)
            .appendFlags(EXT_METAL, GENERATE_FRAME)
            .buildAndRegister();

    public static void init() {
        // Add flags to base GT materials
    }
}
