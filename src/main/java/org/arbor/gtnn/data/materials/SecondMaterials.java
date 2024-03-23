package org.arbor.gtnn.data.materials;

import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.GENERATE_FRAME;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static org.arbor.gtnn.data.GTNNMaterials.*;

public class SecondMaterials {
    public static void init(){
        ArcaneCrystal = Builder("arcane_crystal")
                .dust().ore().gem()
                .color(0x93AEFF).iconSet(DIAMOND)
                .buildAndRegister();
        RP1 = Builder("rp_1_mixed_fuel")
                .fluid()
                .color(0xC02928).iconSet(DULL)
                .buildAndRegister();
        RP1RocketFuel = Builder("rp_1_rocket_fuel")
                .fluid()
                .color(0x9E2A2A).iconSet(DULL)
                .buildAndRegister();
        Kerosene = Builder("kerosene")
                .fluid()
                .color(0x752275).iconSet(DULL)
                .buildAndRegister();
        DenseHydrazineMixedFuel = Builder("dense_hydrazine_mixed_fuel")
                .fluid()
                .color(0x833D59).iconSet(DULL)
                .buildAndRegister();
        Hydrazine = Builder("hydrazine")
                .fluid()
                .color(0xBBBBBB).iconSet(DULL)
                .buildAndRegister();
        HydrogenPeroxide = Builder("hydrogen_peroxide")
                .fluid()
                .color(0xC3EDED).iconSet(DULL)
                .buildAndRegister();
        EthylAnthraQuinone = Builder("ethyl_anthra_quinone")
                .fluid()
                .color(0xAABE77).iconSet(DULL)
                .buildAndRegister();
        EthylAnthraHydroQuinone = Builder("ethyl_anthra_hydro_quinone")
                .fluid()
                .color(0xC9E08D).iconSet(DULL)
                .buildAndRegister();
        Anthracene = Builder("anthracene")
                .fluid()
                .color(0xBBBABA).iconSet(DULL)
                .buildAndRegister();
        MethylhydrazineNitrateRocketFuel = Builder("methylhydrazine_nitrate_rocket_fuel")
                .fluid()
                .color(0x613B87).iconSet(DULL)
                .buildAndRegister();
        MethylHydrazine = Builder("methyl_hydrazine")
                .fluid()
                .color(0x606060).iconSet(DULL)
                .buildAndRegister();
        UDMHRocketFuel = Builder("udmh_rocket_fuel")
                .fluid()
                .color(0x2AA327).iconSet(DULL)
                .buildAndRegister();
        UDMH = Builder("udmh")
                .fluid()
                .color(0x050543).iconSet(DULL)
                .buildAndRegister();
        OrangeMetalCatalyst = Builder("orange_metal_catalyst")
                .dust()
                .color(0xfa7e23).iconSet(ROUGH)
                .buildAndRegister();
        PhthalicAnhydride = Builder("phthalic_anhydride")
                .dust()
                .color(0x6C863A).iconSet(ROUGH)
                .buildAndRegister();
        VanadiumPentoxide = Builder("vanadium_pentoxide")
                .dust()
                .components(Vanadium, 2, Oxygen, 5)
                .color(0xB5730F).iconSet(METALLIC)
                .buildAndRegister();
        BlackMatter = Builder("black_matter")
                .dust().ingot().fluid()
                .components(Lead, 3, Manganese, 5, Carbon, 12)
                .color(0x000000).iconSet(DULL)
                .appendFlags(EXT_METAL, GENERATE_FRAME)
                .buildAndRegister();
        Cerrobase140 = Builder("cerrobase_140")
                .dust().fluid()
                .components(Bismuth, 47, Lead, 25, Tin, 13, Cadmium, 10, Indium, 5)
                .color(0x9e9e9e).iconSet(METALLIC)
                .blastTemp(1230)
                .buildAndRegister();
        PotassiumPyrosulfate = Builder("potassium_pyrosulfate")
                .dust().fluid(FluidStorageKeys.MOLTEN, new FluidBuilder())
                .components(Potassium, 2, Sulfur, 2, Oxygen, 7)
                .color(0xff9900).iconSet(METALLIC)
                .buildAndRegister();
        SodiumSulfate = Builder("sodium_sulfate")
                .dust()
                .components(Sodium, 2, Sulfur, 1, Oxygen, 4)
                .color(0xF9F6CF).iconSet(SAND)
                .buildAndRegister();
        ZincSulfate = Builder("zinc_sulfate")
                .dust()
                .components(Zinc, 1, Sulfur, 1, Oxygen, 4)
                .color(0x533c1b).iconSet(SAND)
                .buildAndRegister();
        Wollastonite = Builder("wollastonite")
                .dust().ore()
                .components(Calcium, 1, Silicon, 1, Oxygen, 3)
                .color(0xc4cbcf).iconSet(SAND)
                .buildAndRegister();
        Kaolinite = Builder("kaolinite")
                .dust().ore()
                .color(0x969090).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        Dolomite = Builder("dolomite")
                .dust().ore()
                .color(0x9F9191).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        GraphiteUraniumMixture = Builder("graphite_uranium_mixture")
                .dust()
                .components(Graphite, 3, Uranium238, 1)
                .color(0x2f734c).iconSet(METALLIC)
                .buildAndRegister();
        PlutoniumOxideUraniumMixture = Builder("plutonium_oxide_uranium_mixture")
                .dust()
                .components(Plutonium239, 10, Oxygen, 12, Uranium238, 2, Carbon, 8)
                .color(0xc51d46).iconSet(METALLIC)
                .buildAndRegister();
        UraniumCarbideThoriumMixture = Builder("uranium_carbide_thorium_mixture")
                .dust()
                .components(Thorium, 11, Thorium232, 1, Uranium235, 1, Carbon, 3)
                .color(0x15231b).iconSet(METALLIC)
                .buildAndRegister();
        ThoriumBasedLiquidFuel = Builder("thorium_based_liquid_fuel")
                .fluid()
                .color(0x3b264d).iconSet(METALLIC)
                .buildAndRegister().setFormula("Th432Li4D2Hg");
        ThoriumBasedLiquidFuelExcited = Builder("thorium_based_liquid_fuel_excited")
                .fluid()
                .color(0x3f2850).iconSet(METALLIC)
                .buildAndRegister().setFormula("*(Th432Li4D2HG)*");
        ThoriumBasedLiquidFuelDepleted = Builder("thorium_based_liquid_fuel_depleted")
                .fluid()
                .color(0x5d5166).iconSet(METALLIC)
                .buildAndRegister().setFormula("Th?Pr?B?In?");
        UraniumBasedLiquidFuel = Builder("uranium_based_liquid_fuel")
                .fluid()
                .color(0x02ba05).iconSet(METALLIC)
                .buildAndRegister().setFormula("U36K8Qt4Rn");
        UraniumBasedLiquidFuelExcited = Builder("uranium_based_liquid_fuel_excited")
                .fluid()
                .color(0x04bc04).iconSet(METALLIC)
                .buildAndRegister().setFormula("*(U36K8Qt4Rn)*");
        UraniumBasedLiquidFuelDepleted = Builder("uranium_based_liquid_fuel_depleted")
                .fluid()
                .color(0x576d31).iconSet(METALLIC)
                .buildAndRegister().setFormula("PB?Bi?Ba?Xe?");
        PlutoniumBasedLiquidFuel = Builder("plutonium_based_liquid_fuel")
                .fluid()
                .color(0xb71213).iconSet(METALLIC)
                .buildAndRegister().setFormula("Pu45Nt8Cs16Nq2");
        PlutoniumBasedLiquidFuelExcited = Builder("plutonium_based_liquid_fuel_excited")
                .fluid()
                .color(0xb81312).iconSet(METALLIC)
                .buildAndRegister().setFormula("*(Pu45Nt8Cs16Nq2)*");
        PlutoniumBasedLiquidFuelDepleted = Builder("plutonium_based_liquid_fuel_depleted")
                .fluid()
                .color(0x4e1414).iconSet(METALLIC)
                .buildAndRegister().setFormula("Th?Ce?Au?Kr?");
        RadiationProtection = Builder("radiation_protection")
                .dust()
                .flags(GENERATE_FRAME)
                .color(0x4C4C4B).iconSet(METALLIC)
                .buildAndRegister();
        NaquadahBasedLiquidFuel = Builder("naquadah_based_liquid_fuel")
                .fluid()
                .color(0x43b54a).iconSet(METALLIC)
                .buildAndRegister().setFormula("Nq42Ce16Nd16");
        NaquadahBasedLiquidFuelExcited = Builder("naquadah_based_liquid_fuel_excited")
                .fluid()
                .color(0x41b349).iconSet(METALLIC)
                .buildAndRegister().setFormula("*(Nq42Ce16Nd16)*");
        NaquadahBasedLiquidFuelDepleted = Builder("naquadah_based_liquid_fuel_depleted")
                .fluid()
                .color(0x215825).iconSet(METALLIC)
                .buildAndRegister().setFormula("Nq?Ke?Nd?");
        NitrateSulfurMixedAcid = Builder("nitrate_sulfur_mixed_acid")
                .acid()
                .components(SulfuricAcid, 1, NitricAcid, 1)
                .color(0xc0872a).iconSet(ROUGH)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
    }
}
