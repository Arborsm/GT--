package org.arbor.gtnn.data.recipes;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.chemical.material.stack.UnificationEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.common.data.GCyMRecipeTypes;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static org.arbor.gtnn.data.GTNNMaterials.*;
import static org.arbor.gtnn.data.GTNNRecipes.dur;

public class PlatinumLine {
    public static void init(Consumer<FinishedRecipe> provider) {
        replace(provider);
        platinumLineProcesses(provider);
    }

    private static void replace(Consumer<FinishedRecipe> provider) {
        remove(provider);
        ELECTROLYZER_RECIPES.recipeBuilder("decomposition_electrolyzing_cooperite")
                .duration(648)
                .inputItems(ChemicalHelper.get(TagPrefix.dust, Cooperite))
                .outputItems(ChemicalHelper.get(TagPrefix.dust, PlatinumMetal, 3), ChemicalHelper.get(TagPrefix.dust, Nickel),
                        ChemicalHelper.get(TagPrefix.dust, Sulfur), ChemicalHelper.get(TagPrefix.dust, PalladiumMetal))
                .EUt(60).save(provider);
        VanillaRecipeHelper.addShapelessRecipe(provider, "centrifuged_ore_to_dust_platinum", ChemicalHelper.get(TagPrefix.dust, PlatinumMetal),
                GTToolType.HARD_HAMMER.itemTags.get(0), new UnificationEntry(TagPrefix.crushedRefined, Platinum));
        VanillaRecipeHelper.addShapelessRecipe(provider, "centrifuged_ore_to_dust_palladium", ChemicalHelper.get(TagPrefix.dust, PalladiumMetal),
                GTToolType.HARD_HAMMER.itemTags.get(0), new UnificationEntry(TagPrefix.crushedRefined, Palladium));
    }
    public static void platinumLineProcesses(Consumer<FinishedRecipe> provider) {

        CHEMICAL_RECIPES.recipeBuilder("dissolve_platinum_metallic_powder")
                .inputItems(dust, PlatinumMetal)
                .circuitMeta(1)
                .inputFluids(AquaRegia.getFluid(1000))
                .chancedOutput(dust, PlatinumSlag, 1111, 0)
                .outputFluids(ConcentratedPlatinum.getFluid(1000))
                .duration(250).EUt(VA[LV]).save(provider);
        CHEMICAL_RECIPES.recipeBuilder("dissolve_platinum_metallic_powder9")
                .inputItems(dust, PlatinumMetal, 9)
                .circuitMeta(9)
                .inputFluids(AquaRegia.getFluid(9000))
                .outputItems(dust, PlatinumSlag)
                .outputFluids(ConcentratedPlatinum.getFluid(9000))
                .duration(2250).EUt(VA[LV]).save(provider);
        CHEMICAL_RECIPES.recipeBuilder("platinum_group_sludge_dust1_lv")
                .inputItems(crushedPurified, Pentlandite, 1)
                .circuitMeta(1)
                .inputFluids(SulfuricAcid.getFluid(1000))
                .chancedOutput(dust, PlatinumGroupSludge, 1111, 0)
                .outputFluids(SulfuricNickelSolution.getFluid(2000))
                .duration(50).EUt(VA[LV]).save(provider);
        LARGE_CHEMICAL_RECIPES.recipeBuilder("platinum_group_sludge_dust1_lv")
                .inputItems(crushedPurified, Pentlandite, 1)
                .circuitMeta(1)
                .inputFluids(SulfuricAcid.getFluid(1000))
                .chancedOutput(dust, PlatinumGroupSludge, 1111, 0)
                .outputFluids(SulfuricNickelSolution.getFluid(2000))
                .duration(50).EUt(VA[LV]).save(provider);
        LARGE_CHEMICAL_RECIPES.recipeBuilder("platinum_group_sludge_dust1")
                .inputItems(crushedPurified, Pentlandite, 9)
                .circuitMeta(9)
                .inputFluids(SulfuricAcid.getFluid(9000))
                .outputItems(dust, PlatinumGroupSludge)
                .outputFluids(SulfuricNickelSolution.getFluid(18000))
                .duration(25).EUt(VA[HV]).save(provider);
        CHEMICAL_RECIPES.recipeBuilder("platinum_group_sludge_dust2_lv")
                .inputItems(crushedPurified, Chalcopyrite, 1)
                .circuitMeta(1)
                .inputFluids(SulfuricAcid.getFluid(1000))
                .chancedOutput(dust, PlatinumGroupSludge, 1111, 0)
                .outputFluids(SulfuricCopperSolution.getFluid(2000))
                .duration(50).EUt(VA[LV]).save(provider);
        LARGE_CHEMICAL_RECIPES.recipeBuilder("platinum_group_sludge_dust2_lv")
                .inputItems(crushedPurified, Chalcopyrite, 1)
                .circuitMeta(1)
                .inputFluids(SulfuricAcid.getFluid(1000))
                .chancedOutput(dust, PlatinumGroupSludge, 1111, 0)
                .outputFluids(SulfuricCopperSolution.getFluid(2000))
                .duration(50).EUt(VA[LV]).save(provider);
        LARGE_CHEMICAL_RECIPES.recipeBuilder("platinum_group_sludge_dust2")
                .inputItems(crushedPurified, Chalcopyrite, 9)
                .circuitMeta(9)
                .inputFluids(SulfuricAcid.getFluid(9000))
                .outputItems(dust, PlatinumGroupSludge)
                .outputFluids(SulfuricCopperSolution.getFluid(18000))
                .duration(25).EUt(VA[HV]).save(provider);
        CHEMICAL_RECIPES.recipeBuilder("platinum_group_sludge_tiny_dust1")
                .inputItems(crushedPurified, Pentlandite)
                .circuitMeta(1)
                .inputFluids(SulfuricAcid.getFluid(1000))
                .chancedOutput(dust, PlatinumGroupSludge, 1111, 0)
                .outputFluids(SulfuricNickelSolution.getFluid(2000))
                .duration(50).EUt(VA[LV]).save(provider);
        LARGE_CHEMICAL_RECIPES.recipeBuilder("platinum_group_sludge_tiny_dust1")
                .inputItems(crushedPurified, Pentlandite)
                .circuitMeta(1)
                .inputFluids(SulfuricAcid.getFluid(1000))
                .chancedOutput(dust, PlatinumGroupSludge, 1111, 0)
                .outputFluids(SulfuricNickelSolution.getFluid(2000))
                .duration(50).EUt(VA[LV]).save(provider);
        CHEMICAL_RECIPES.recipeBuilder("platinum_group_sludge_tiny_dust2")
                .inputItems(crushedPurified, Pentlandite)
                .circuitMeta(1)
                .inputFluids(SulfuricAcid.getFluid(1000))
                .chancedOutput(dust, PlatinumGroupSludge, 1111, 0)
                .outputFluids(SulfuricCopperSolution.getFluid(2000))
                .duration(50).EUt(VA[LV]).save(provider);
        LARGE_CHEMICAL_RECIPES.recipeBuilder("platinum_group_sludge_tiny_dust2")
                .inputItems(crushedPurified, Pentlandite)
                .circuitMeta(1)
                .inputFluids(SulfuricAcid.getFluid(1000))
                .chancedOutput(dust, PlatinumGroupSludge, 1111, 0)
                .outputFluids(SulfuricCopperSolution.getFluid(2000))
                .duration(50).EUt(VA[LV]).save(provider);

        LARGE_CHEMICAL_RECIPES.recipeBuilder("tiny_platinum_dusts")
                .inputFluids(ConcentratedPlatinum.getFluid(2000))
                .inputFluids(AmmoniumChloride.getFluid(400))
                .circuitMeta(2)
                .chancedOutput(dust, PlatinumSalt,16, 1111, 0)
                .chancedOutput(dust, PlatinumRaw,4, 1111, 0)
                .outputFluids(PalladiumRichAmmonia.getFluid(400))
                .outputFluids(NitrogenDioxide.getFluid(1000))
                .outputFluids(DilutedSulfuricAcid.getFluid(1000))
                .duration(1200).EUt(VA[LV]).save(provider);

        LARGE_CHEMICAL_RECIPES.recipeBuilder("large_platinum_dusts")
                .inputFluids(ConcentratedPlatinum.getFluid(18000))
                .inputFluids(AmmoniumChloride.getFluid(3600))
                .circuitMeta(1)
                .outputItems(dust, PlatinumSalt,16)
                .outputItems(dust, PlatinumRaw,4)
                .outputFluids(PalladiumRichAmmonia.getFluid(3600))
                .outputFluids(NitrogenDioxide.getFluid(9000))
                .outputFluids(DilutedSulfuricAcid.getFluid(9000))
                .duration(1400).EUt(VA[HV]).save(provider);

        BLAST_RECIPES.recipeBuilder("refined_platinum_salt_dust_ebf")
                .inputItems(dust, RefinedPlatinumSalt)
                .circuitMeta(1)
                .outputItems(dust, PlatinumMetal,1)
                .blastFurnaceTemp(900)
                .duration(dur(10)).EUt(VA[MV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("palladium_metallic_powder_conversion")
                .inputItems(dust, PalladiumMetal)
                .circuitMeta(1)
                .inputFluids(Ammonia.getFluid(1000))
                .outputFluids(PalladiumRichAmmonia.getFluid(1000))
                .duration(250).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("raw_platinum_separation")
                .inputItems(dust, PlatinumRaw,4)
                .inputItems(dust, Calcium,1)
                .outputItems(dust, Platinum,2)
                .outputItems(dust, CalciumChloride,3)
                .duration(30).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("low_yeild_palladium_salt")
                .inputFluids(PalladiumRichAmmonia.getFluid(1000))
                .circuitMeta(2)
                .outputItems(dust, PalladiumSalt,1)
                .duration(250).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("tiny_palladium_dusts")
                .inputItems(dust, PalladiumMetal,1)
                .inputFluids(PalladiumRichAmmonia.getFluid(1000))
                .circuitMeta(1)
                .outputItems(dustTiny, PalladiumSalt,16)
                .outputItems(dustTiny, PalladiumRaw,2)
                .duration(250).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("large_palladium_dusts")
                .inputItems(dust, PalladiumMetal,9)
                .inputFluids(PalladiumRichAmmonia.getFluid(9000))
                .circuitMeta(9)
                .outputItems(dust, PalladiumSalt,16)
                .outputItems(dust, PalladiumRaw,2)
                .duration(2250).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("raw_palladium_separation")
                .inputItems(dust, PalladiumRaw,4)
                .inputFluids(FormicAcid.getFluid(4000))
                .outputItems(dust, Palladium,2)
                .outputFluids(Ammonia.getFluid(4000))
                .duration(250).EUt(VA[LV]).save(provider);

        LARGE_CHEMICAL_RECIPES.recipeBuilder("raw_palladium_separation_large")
                .inputItems(dust, PalladiumRaw,4)
                .inputFluids(FormicAcid.getFluid(4000))
                .outputItems(dust, Palladium,2)
                .outputFluids(Ammonia.getFluid(4000))
                .outputFluids(Ethylene.getFluid(1000))
                .outputFluids(Water.getFluid(1000))
                .duration(250).EUt(VA[LV]).save(provider);

        EXTRACTOR_RECIPES.recipeBuilder("sodium_pyrosulfate_dust")
                .inputItems(dust, SodiumPyrosulfate)
                .outputFluids(SodiumPyrosulfate.getFluid(FluidStorageKeys.MOLTEN, 144))
                .duration(23).EUt(30).save(provider);

        BLAST_RECIPES.recipeBuilder("leach_residue_one")
                .inputItems(dust, PlatinumSlag)
                .circuitMeta(11)
                .inputFluids(SodiumPyrosulfate.getFluid(FluidStorageKeys.MOLTEN, 360))
                .outputItems(dust, InertMetalMixture)
                .outputFluids(RhodiumSulfateGas.getFluid(360))
                .blastFurnaceTemp(775)
                .duration(200).EUt(VA[MV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("rhodium_sulfate_to_solution")
                .circuitMeta(1)
                .inputFluids(Water.getFluid(10000))
                .inputFluids(RhodiumSulfateGas.getFluid(11000))
                .chancedOutput(dust, PlatinumSlag,10, 1111, 0)
                .outputFluids(Potassium.getFluid(2000))
                .outputFluids(RhodiumSulfate.getFluid(10000))
                .duration(300).EUt(VA[LV]).save(provider);

        LARGE_CHEMICAL_RECIPES.recipeBuilder("rhodium_sulfate_to_solution_l")
                .circuitMeta(11)
                .inputFluids(Water.getFluid(36000))
                .inputFluids(RhodiumSulfateGas.getFluid(39000))
                .outputItems(dust, PlatinumSlag,4)
                .outputFluids(Potassium.getFluid(7000))
                .outputFluids(RhodiumSulfate.getFluid(39000))
                .duration(dur(60)).EUt(VA[LV]).save(provider);



        CHEMICAL_RECIPES.recipeBuilder("crude_rhodium_metallic_powder")
                .inputItems(dust, Zinc, 1)
                .inputFluids(RhodiumSulfate.getFluid(1000))
                .outputItems(dust, ZincSulfate,6)
                .outputItems(dust, RoughlyRhodiumMetal,1)
                .duration(300).EUt(VA[LV]).save(provider);

        BLAST_RECIPES.recipeBuilder("rhodium_salt_ebf")
                .inputItems(dust, RoughlyRhodiumMetal,1)
                .inputItems(dust, Salt, 1)
                .inputFluids(Chlorine.getFluid(1000))
                .outputItems(dust, RhodiumSalt, 3)
                .blastFurnaceTemp(600)
                .duration(200).EUt(VA[MV]).save(provider);

        MIXER_RECIPES.recipeBuilder("rhodium_salt_solution")
                .inputItems(dust, RhodiumSalt, 1)
                .inputFluids(Water.getFluid(200))
                .outputFluids(RhodiumSalt.getFluid(200))
                .duration(30).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("rhodium_nitrate")
                .inputItems(dust, SodiumNitrate,5)
                .inputFluids(RhodiumSalt.getFluid(1000))
                .circuitMeta(1)
                .outputItems(dust, RhodiumNitrate,1)
                .outputItems(dust, Salt,2)
                .duration(300).EUt(VA[LV]).save(provider);

        MIXER_RECIPES.recipeBuilder("rhodium_filter_cake_solution")
                .inputItems(dust, RhodiumFilterCake,1)
                .inputFluids(Water.getFluid(1000))
                .outputFluids(RhodiumFilterCake.getFluid(1000))
                .duration(300).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("reprecipitated_rhodium")
                .inputFluids(RhodiumFilterCake.getFluid(1000))
                .circuitMeta(2)
                .outputItems(dust, ReprecipitatedRhodium,1)
                .duration(300).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("rhodium_dust")
                .inputItems(dust, ReprecipitatedRhodium,1)
                .inputFluids(RhodiumFilterCake.getFluid(1000))
                .outputItems(dust, Rhodium,1)
                .outputFluids(Chlorine.getFluid(1000))
                .outputFluids(Ammonia.getFluid(1000))
                .duration(300).EUt(VA[LV]).save(provider);

        BLAST_RECIPES.recipeBuilder("rarest_metal_residue")
                .inputItems(dust, InertMetalMixture,10)
                .inputItems(dust, Saltpeter,10)
                .inputFluids(SaltWater.getFluid(1000))
                .outputItems(dust, SodiumRutheniate, 3)
                .outputItems(dust, RarestMetalMixture,6)
                .outputFluids(Steam.getFluid(1000))
                .blastFurnaceTemp(775)
                .duration(200).EUt(VA[MV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("ruthenium_tetroxide")
                .inputItems(dust, SodiumRutheniate,6)
                .inputFluids(Chlorine.getFluid(3000))
                .outputFluids(RutheniumTetroxide.getFluid(9000))
                .duration(300).EUt(VA[LV]).save(provider);

        CRACKING_RECIPES.recipeBuilder("hot_ruthenium_tetroxide")
                .inputFluids(RutheniumTetroxide.getFluid(1000))
                .inputFluids(Steam.getFluid(1000))
                .outputFluids(RutheniumTetroxideHot.getFluid(2000))
                .circuitMeta(17)
                .duration(150).EUt(VA[HV]).save(provider);

        DISTILLATION_RECIPES.recipeBuilder("hot_ruthenium_tetroxide_distill")
                .inputFluids(RutheniumTetroxideHot.getFluid(9000))
                .outputItems(dust, Salt,6)
                .outputFluids(Water.getFluid(1800))
                .outputFluids(RutheniumTetroxideLQ.getFluid(7200))
                .duration(1500).EUt(VA[HV]).save(provider);

        FLUID_SOLIDFICATION_RECIPES.recipeBuilder("ruthenium_tetroxide_dust")
                .inputFluids(RutheniumTetroxideLQ.getFluid(1000))
                .outputItems(dust, RutheniumTetroxide)
                .duration(33).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("ruthenium_tetroxide_separation")
                .inputItems(dust, RutheniumTetroxide,1)
                .inputFluids(HydrochloricAcid.getFluid(6000))
                .outputItems(dust, Ruthenium,1)
                .outputFluids(Water.getFluid(2000))
                .outputFluids(Chlorine.getFluid(6000))
                .duration(33).EUt(VA[LV]).save(provider);

        // Osmium Processing Time!

        BLAST_RECIPES.recipeBuilder("rarest_metal_residue_ebf")
                .inputItems(dust, RarestMetalMixture,2)
                .circuitMeta(2)
                .inputFluids(HydrochloricAcid.getFluid(500))
                .outputItems(dust, IridiumMetalResidue, 1)
                .outputFluids(AcidicOsmiumSolution.getFluid(1000))
                .blastFurnaceTemp(775)
                .duration(100).EUt(VA[MV]).save(provider);

        DISTILLATION_RECIPES.recipeBuilder("acidic_osmium_solution_distill")
                .inputFluids(AcidicOsmiumSolution.getFluid(1000))
                .outputFluids(OsmiumTetroxide.getFluid(100))
                .outputFluids(Water.getFluid(900))
                .duration(150).EUt(VA[MV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("osmium_dust_completion")
                .inputFluids(HydrochloricAcid.getFluid(6000))
                .inputFluids(OsmiumTetroxide.getFluid(1000))
                .outputFluids(Chlorine.getFluid(7000))
                .outputFluids(Water.getFluid(2000))
                .outputItems(dust, Osmium,1)
                .duration(300).EUt(VA[LV]).save(provider);

        BLAST_RECIPES.recipeBuilder("iridium_metal_residue_process")
                .inputItems(dust, IridiumMetalResidue)
                .circuitMeta(1)
                .outputItems(dust, PlatinumSludgeResidue)
                .outputItems(dust, IridiumDioxide)
                .blastFurnaceTemp(775)
                .duration(200).EUt(VA[MV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("iridium_dioxide_dissolving")
                .inputItems(dust, IridiumDioxide,1)
                .inputFluids(HydrochloricAcid.getFluid(1000))
                .outputFluids(AcidicIridium.getFluid(1000))
                .duration(300).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("iridium_chloride")
                .inputItems(dust, AmmoniumChloride, 11)
                .inputFluids(AcidicIridium.getFluid(1000))
                .outputItems(dust,IridiumChloride)
                .outputFluids(Ammonia.getFluid(3000))
                .duration(300).EUt(VA[LV]).save(provider);

        CENTRIFUGE_RECIPES.recipeBuilder("platinum_group_sludge_dust")
                .inputItems(dust, PlatinumGroupSludge)
                .outputItems(ChemicalHelper.get(dust, SiliconDioxide, 3), ChemicalHelper.get(dust, Gold, 3), ChemicalHelper.get(dust, PlatinumMetal, 6))
                .chancedOutput(dust, PalladiumMetal, 4, 9500, 500)
                .chancedOutput(dust, IridiumMetalResidue, 9000, 500)
                .chancedOutput(dust, RarestMetalMixture, 8500, 500)
                .duration(2700)
                .EUt(LV).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("iridium_chloride_separation")
                .inputItems(dust, IridiumChloride,1)
                .inputItems(dust, Calcium,1)
                .outputItems(dust, MetalSludge,1)
                .outputItems(dust, Iridium,1)
                .outputItems(dust, CalciumChloride, 4)
                .duration(300).EUt(VA[EV]).save(provider);

        // Remaining Sifting Recipes
        SIFTER_RECIPES.recipeBuilder("rhodium_filter_cake")
                .inputItems(dust, RhodiumNitrate)
                .chancedOutput(dust, RhodiumFilterCake, 2000,0)
                .chancedOutput(dust, RhodiumFilterCake, 2000,0)
                .chancedOutput(dust, RhodiumFilterCake, 2000,0)
                .chancedOutput(dust, RhodiumFilterCake, 1500,0)
                .chancedOutput(dust, RhodiumFilterCake, 1000,0)
                .chancedOutput(dust, RhodiumFilterCake, 1500,0)
                .duration(600).EUt(VA[LV]).save(provider);

        SIFTER_RECIPES.recipeBuilder("refined_platinum_salt")
                .inputItems(dust, PlatinumSalt,1)
                .chancedOutput(dust, RefinedPlatinumSalt, 2000,0)
                .chancedOutput(dust, RefinedPlatinumSalt, 2000,0)
                .chancedOutput(dust, RefinedPlatinumSalt, 2000,0)
                .chancedOutput(dust, RefinedPlatinumSalt, 1500,0)
                .chancedOutput(dust, RefinedPlatinumSalt, 1000,0)
                .chancedOutput(dust, RefinedPlatinumSalt, 1500,0)
                .duration(600).EUt(VA[LV]).save(provider);

        SIFTER_RECIPES.recipeBuilder("salt_to_metallic_powder_palladium")
                .inputItems(dust, PalladiumSalt,1)
                .chancedOutput(dust, PalladiumMetal, 2000,0)
                .chancedOutput(dust, PalladiumMetal, 2000,0)
                .chancedOutput(dust, PalladiumMetal, 2000,0)
                .chancedOutput(dust, PalladiumMetal, 1500,0)
                .chancedOutput(dust, PalladiumMetal, 1000,0)
                .chancedOutput(dust, PalladiumMetal, 1000,0)
                .duration(600).EUt(VA[LV]).save(provider);




        // The Extra Stuff Overwriting Progression ETC


        ELECTROLYZER_RECIPES.recipeBuilder("raw_platinum_separation")
                .inputItems(dust, PlatinumRaw,3)
                .outputItems(dust, PlatinumMetal,1)
                .outputFluids(Chlorine.getFluid(800))
                .duration(2400).EUt(VA[MV]).save(provider);

        ELECTROLYZER_RECIPES.recipeBuilder("decomposition_electrolyzing_cooperite")
                .inputItems(dust, Cooperite,6)
                .outputItems(dust, PlatinumMetal,3)
                .outputItems(dust, Nickel,1)
                .outputItems(dust, Sulfur,1)
                .outputItems(dust, PalladiumSalt,1)
                .duration(1200).EUt(VA[MV]).save(provider);

        LARGE_CHEMICAL_RECIPES.recipeBuilder("pgs_from_tetrahedrite")
                .inputItems(crushedPurified, Tetrahedrite,9)
                .circuitMeta(9)
                .inputFluids(AquaRegia.getFluid(9000))
                .outputFluids(ConcentratedPlatinum.getFluid(9000))
                .duration(2250).EUt(VA[LV]).save(provider);

        LARGE_CHEMICAL_RECIPES.recipeBuilder("pgs_from_bornite")
                .inputItems(crushedPurified, Bornite,9)
                .circuitMeta(9)
                .inputFluids(AquaRegia.getFluid(9000))
                .outputFluids(ConcentratedPlatinum.getFluid(9000))
                .duration(250).EUt(VA[LV]).save(provider);

        LARGE_CHEMICAL_RECIPES.recipeBuilder("pgs_from_chalcopyrite")
                .inputItems(crushedPurified, Chalcopyrite,9)
                .circuitMeta(9)
                .inputFluids(AquaRegia.getFluid(9000))
                .outputFluids(ConcentratedPlatinum.getFluid(9000))
                .duration(250).EUt(VA[LV]).save(provider);

        LARGE_CHEMICAL_RECIPES.recipeBuilder("pgs_from_pentlandite")
                .inputItems(crushedPurified, Pentlandite,9)
                .circuitMeta(9)
                .inputFluids(AquaRegia.getFluid(9000))
                .outputFluids(ConcentratedPlatinum.getFluid(9000))
                .duration(250).EUt(VA[LV]).save(provider);

        LARGE_CHEMICAL_RECIPES.recipeBuilder("pgs_from_cooperite")
                .inputItems(crushedPurified, Cooperite,9)
                .circuitMeta(9)
                .inputFluids(AquaRegia.getFluid(9000))
                .outputFluids(ConcentratedPlatinum.getFluid(9000))
                .duration(250).EUt(VA[LV]).save(provider);

        LARGE_CHEMICAL_RECIPES.recipeBuilder("pgs_from_chalcocite")
                .inputItems(crushedPurified, Chalcocite,9)
                .circuitMeta(9)
                .inputFluids(AquaRegia.getFluid(9000))
                .outputFluids(ConcentratedPlatinum.getFluid(9000))
                .duration(250).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("pgs_from_tetrahedrite")
                .inputItems(crushedPurified, Tetrahedrite,9)
                .circuitMeta(9)
                .inputFluids(AquaRegia.getFluid(9000))
                .outputFluids(ConcentratedPlatinum.getFluid(9000))
                .duration(250).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("pgs_from_bornite")
                .inputItems(crushedPurified, Bornite,9)
                .circuitMeta(9)
                .inputFluids(AquaRegia.getFluid(9000))
                .outputFluids(ConcentratedPlatinum.getFluid(9000))
                .duration(250).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("pgs_from_chalcopyrite")
                .inputItems(crushedPurified, Chalcopyrite,9)
                .circuitMeta(9)
                .inputFluids(AquaRegia.getFluid(9000))
                .outputFluids(ConcentratedPlatinum.getFluid(9000))
                .duration(250).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("pgs_from_pentlandite")
                .inputItems(crushedPurified, Pentlandite,9)
                .circuitMeta(9)
                .inputFluids(AquaRegia.getFluid(9000))
                .outputFluids(ConcentratedPlatinum.getFluid(9000))
                .duration(250).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("pgs_from_cooperite")
                .inputItems(crushedPurified, Cooperite,9)
                .circuitMeta(9)
                .inputFluids(AquaRegia.getFluid(9000))
                .outputFluids(ConcentratedPlatinum.getFluid(9000))
                .duration(250).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("pgs_from_chalcocite")
                .inputItems(crushedPurified, Chalcocite,9)
                .circuitMeta(9)
                .inputFluids(AquaRegia.getFluid(9000))
                .outputFluids(ConcentratedPlatinum.getFluid(9000))
                .duration(250).EUt(VA[LV]).save(provider);

        // with platinum metal
        LARGE_CHEMICAL_RECIPES.recipeBuilder("pgs_from_tetrahedrite_p")
                .inputItems(crushedPurified, Tetrahedrite,9)
                .inputItems(dust, PlatinumMetal, 9)
                .inputFluids(AquaRegia.getFluid(10000))
                .outputItems(dust, PlatinumSlag)
                .outputFluids(ConcentratedPlatinum.getFluid(10000))
                .duration(2250).EUt(VA[LV]).save(provider);

        LARGE_CHEMICAL_RECIPES.recipeBuilder("pgs_from_bornite_p")
                .inputItems(crushedPurified, Bornite,9)
                .inputItems(dust, PlatinumMetal, 9)
                .inputFluids(AquaRegia.getFluid(10000))
                .outputItems(dust, PlatinumSlag)
                .outputFluids(ConcentratedPlatinum.getFluid(10000))
                .duration(2250).EUt(VA[LV]).save(provider);

        LARGE_CHEMICAL_RECIPES.recipeBuilder("pgs_from_chalcopyrite_p")
                .inputItems(crushedPurified, Chalcopyrite,9)
                .inputItems(dust, PlatinumMetal, 9)
                .inputFluids(AquaRegia.getFluid(10000))
                .outputItems(dust, PlatinumSlag)
                .outputFluids(ConcentratedPlatinum.getFluid(10000))
                .duration(2250).EUt(VA[LV]).save(provider);

        LARGE_CHEMICAL_RECIPES.recipeBuilder("pgs_from_pentlandite_p")
                .inputItems(crushedPurified, Pentlandite,9)
                .inputItems(dust, PlatinumMetal, 9)
                .inputFluids(AquaRegia.getFluid(10000))
                .outputItems(dust, PlatinumSlag)
                .outputFluids(ConcentratedPlatinum.getFluid(10000))
                .duration(2250).EUt(VA[LV]).save(provider);

        LARGE_CHEMICAL_RECIPES.recipeBuilder("pgs_from_cooperite_p")
                .inputItems(crushedPurified, Cooperite,9)
                .inputItems(dust, PlatinumMetal, 9)
                .inputFluids(AquaRegia.getFluid(10000))
                .outputItems(dust, PlatinumSlag)
                .outputFluids(ConcentratedPlatinum.getFluid(10000))
                .duration(2250).EUt(VA[LV]).save(provider);

        LARGE_CHEMICAL_RECIPES.recipeBuilder("pgs_from_chalcocite_p")
                .inputItems(crushedPurified, Chalcocite,9)
                .inputItems(dust, PlatinumMetal, 9)
                .inputFluids(AquaRegia.getFluid(10000))
                .outputItems(dust, PlatinumSlag)
                .outputFluids(ConcentratedPlatinum.getFluid(10000))
                .duration(2250).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("pgs_from_tetrahedrite_p")
                .inputItems(crushedPurified, Tetrahedrite,9)
                .inputItems(dust, PlatinumMetal, 9)
                .inputFluids(AquaRegia.getFluid(10000))
                .outputItems(dust, PlatinumSlag)
                .outputFluids(ConcentratedPlatinum.getFluid(10000))
                .duration(2250).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("pgs_from_bornite_p")
                .inputItems(crushedPurified, Bornite,9)
                .inputItems(dust, PlatinumMetal, 9)
                .inputFluids(AquaRegia.getFluid(10000))
                .outputItems(dust, PlatinumSlag)
                .outputFluids(ConcentratedPlatinum.getFluid(10000))
                .duration(2250).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("pgs_from_chalcopyrite_p")
                .inputItems(crushedPurified, Chalcopyrite,9)
                .inputItems(dust, PlatinumMetal, 9)
                .inputFluids(AquaRegia.getFluid(10000))
                .outputItems(dust, PlatinumSlag)
                .outputFluids(ConcentratedPlatinum.getFluid(10000))
                .duration(2250).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("pgs_from_pentlandite_p")
                .inputItems(crushedPurified, Pentlandite,9)
                .inputItems(dust, PlatinumMetal, 9)
                .inputFluids(AquaRegia.getFluid(10000))
                .outputItems(dust, PlatinumSlag)
                .outputFluids(ConcentratedPlatinum.getFluid(10000))
                .duration(2250).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("pgs_from_cooperite_p")
                .inputItems(crushedPurified, Cooperite,9)
                .inputItems(dust, PlatinumMetal, 9)
                .inputFluids(AquaRegia.getFluid(10000))
                .outputItems(dust, PlatinumSlag)
                .outputFluids(ConcentratedPlatinum.getFluid(10000))
                .duration(2250).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("pgs_from_chalcocite_p")
                .inputItems(crushedPurified, Chalcocite,9)
                .inputItems(dust, PlatinumMetal, 9)
                .inputFluids(AquaRegia.getFluid(10000))
                .outputItems(dust, PlatinumSlag)
                .outputFluids(ConcentratedPlatinum.getFluid(10000))
                .duration(2250).EUt(VA[LV]).save(provider);

        CENTRIFUGE_RECIPES.recipeBuilder("iridium_metal_residue_separation")
                .inputItems(dust, IridiumMetalResidue,1)
                .outputItems(dust, PlatinumSludgeResidue,1)
                .duration(250).EUt(VA[LV]).save(provider);

        // Formic Acid
        CHEMICAL_RECIPES.recipeBuilder("sodium_formate")
                .inputItems(dust, SodiumHydroxide,3)
                .inputFluids(CarbonMonoxide.getFluid(1000))
                .outputFluids(SodiumFormate.getFluid(1000))
                .duration(15).EUt(VA[LV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("formic_acid")
                .inputFluids(SodiumFormate.getFluid(2000))
                .inputFluids(SulfuricAcid.getFluid(1000))
                .circuitMeta(1)
                .outputFluids(FormicAcid.getFluid(2000))
                .outputItems(dust, SodiumSulfate,7)
                .duration(15).EUt(VA[LV]).save(provider);
    }
    private static void remove(Consumer<FinishedRecipe> provider){
        GCyMRecipeTypes.ALLOY_BLAST_RECIPES.recipeBuilder("sodium_pyrosulfate").save(provider);
        EXTRACTOR_RECIPES.recipeBuilder("extract_osmium_tetroxide_dust").save(provider);
        ELECTROLYZER_RECIPES.recipeBuilder("raw_platinum_separation").save(provider);
        DISTILLERY_RECIPES.recipeBuilder("acidic_osmium_solution_separation_to_hydrochloric_acid").save(provider);
        DISTILLERY_RECIPES.recipeBuilder("acidic_osmium_solution_separation_to_water").save(provider);
        DISTILLATION_RECIPES.recipeBuilder("acidic_osmium_solution_separation").save(provider);
        CHEMICAL_RECIPES.recipeBuilder("extract_ruthenium_tetroxide_dust").save(provider);
        CENTRIFUGE_RECIPES.recipeBuilder("pgs_separation").save(provider);
        LARGE_CHEMICAL_RECIPES.recipeBuilder("rarest_metal_mixture_separation").save(provider);
        CENTRIFUGE_RECIPES.recipeBuilder("iridium_metal_residue_separation").save(provider);
        ELECTROLYZER_RECIPES.recipeBuilder("rhodium_sulfate_separation").save(provider);
        chemicalRemoval(provider, "osmium_tetroxide_separation");
        chemicalRemoval(provider, "inert_metal_mixture_separation");
        chemicalRemoval(provider, "iridium_chloride_separation");
        chemicalRemoval(provider, "ruthenium_tetroxide_separation");
        chemicalRemoval(provider, "raw_palladium_separation");
        chemicalRemoval(provider, "pgs_from_pentlandite");
        chemicalRemoval(provider, "pgs_from_tetrahedrite");
        chemicalRemoval(provider, "pgs_from_chalcocite");
        chemicalRemoval(provider, "pgs_from_cooperite");
        chemicalRemoval(provider, "pgs_from_bornite");
        chemicalRemoval(provider, "pgs_from_chalcopyrite");
    }
    private static void chemicalRemoval(Consumer<FinishedRecipe> provider, String id){
        CHEMICAL_RECIPES.recipeBuilder(id).save(provider);
        LARGE_CHEMICAL_RECIPES.recipeBuilder(id).save(provider);
    }
}
