package org.arbor.gtnn.data.recipes;

import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import net.minecraft.data.recipes.FinishedRecipe;
import org.arbor.gtnn.data.GTNNRecipes;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static org.arbor.gtnn.data.GTNNMaterials.*;
import static org.arbor.gtnn.data.GTNNRecipes.dur;
import static org.arbor.gtnn.data.GTNNRecipes.setNA;
import static org.arbor.gtnn.data.GTNNRecipesTypes.*;

public class NaquadahLine {
    public static void init(Consumer<FinishedRecipe> consumer) {
        remove(consumer);
        epLine(consumer);
    }

    public static void remove(Consumer<FinishedRecipe> consumer) {
        CENTRIFUGE_RECIPES.recipeBuilder("impure_enriched_naquadah_solution_separation").save(consumer);
        CENTRIFUGE_RECIPES.recipeBuilder("acidic_enriched_naquadah_separation").save(consumer);
        CENTRIFUGE_RECIPES.recipeBuilder("impure_naquadria_solution_separation").save(consumer);
        CENTRIFUGE_RECIPES.recipeBuilder("acidic_naquadria_solution_separation").save(consumer);
    }

    // Author: Magic_Sweepy
    public static void epLine(Consumer<FinishedRecipe> consumer) {
        //  Impure Enriched Naquadah Solution + Fluorine -> Hexafluoride Enriched Naquadah Solution
        CHEMICAL_RECIPES.recipeBuilder("hexafluoride_enriched_naquadah_solution")
                .inputFluids(ImpureEnrichedNaquadahSolution.getFluid(1000))
                .inputFluids(Fluorine.getFluid(6000))
                .outputFluids(HexafluorideEnrichedNaquadahSolution.getFluid(1000))
                .EUt(VA[IV])
                .duration(400)
                .save(consumer);

        //  Hexafluoride Enriched Naquadah Solution + Xenon -> Xenon Hexafluoro Enriched Naquadate
        CHEMICAL_RECIPES.recipeBuilder("xenon_hexafluoro_enriched_naquadate")
                .inputFluids(HexafluorideEnrichedNaquadahSolution.getFluid(1000))
                .inputFluids(Xenon.getFluid(1000))
                .outputFluids(XenonHexafluoroEnrichedNaquadate.getFluid(1000))
                .EUt(VA[HV])
                .duration(200)
                .cleanroom(CleanroomType.CLEANROOM)
                .save(consumer);

        //  Palladium on Carbon + Gold Trifluoride + Xenon Hexafluoro Enriched Naquadate + Fluoroantimonic Acid + Hydrogen -> Enriched Naquadah Solution + Enriched Naquadah Residue Solution + Hydrofluoric Acid
        CHEMICAL_PLANT_RECIPES.recipeBuilder("enriched_naquadah_residue_solution")
                .notConsumable(dust, PalladiumOnCarbon)
                .inputItems(dust, GoldTrifluoride, 4)
                .inputFluids(XenonHexafluoroEnrichedNaquadate.getFluid(1000))
                .inputFluids(FluoroantimonicAcid.getFluid(1000))
                .inputFluids(Hydrogen.getFluid(9000))
                .outputFluids(EnrichedNaquadahSolution.getFluid(1000))
                .outputFluids(EnrichedNaquadahResidueSolution.getFluid(1000))
                .outputFluids(HydrofluoricAcid.getFluid(8000))
                .EUt(VA[LuV])
                .duration(1200)
                .addCondition(GTNNRecipes.setPlantCasing(5))
                .save(consumer);

        //  Enriched Naquadah Residue Solution -> Trinium Sulfide + Xenoauric Fluoroantimonic Acid
        DRYER_RECIPES.recipeBuilder("xenoauric_fluoroantimonic_acid")
                .inputFluids(EnrichedNaquadahResidueSolution.getFluid(2000))
                .outputItems(dust, TriniumSulfide)
                .outputFluids(XenoauricFluoroantimonicAcid.getFluid(1000))
                .EUt(VA[EV])
                .duration(480)
                .save(consumer);

        //  Xenoauric Fluoroantimonic Acid Cycle
        ELECTROLYZER_RECIPES.recipeBuilder("xenoauric_fluoroantimonic_acid")
                .inputFluids(XenoauricFluoroantimonicAcid.getFluid(1000))
                .outputItems(dust, Gold)
                .outputItems(dust, AntimonyTrifluoride)
                .outputFluids(Xenon.getFluid(1000))
                .outputFluids(HydrofluoricAcid.getFluid(3000))
                .EUt(VA[IV])
                .duration(1200)
                .save(consumer);

        //  Gold Chloride + Bromine Trifluoride -> Gold Trifluoride + Bromine + Chlorine
        CHEMICAL_RECIPES.recipeBuilder("gold_chloride")
                .inputFluids(GoldChloride.getFluid(1000))
                .inputFluids(BromineTrifluoride.getFluid(2000))
                .outputItems(dust, GoldTrifluoride, 8)
                .outputFluids(Bromine.getFluid(2000))
                .outputFluids(Chlorine.getFluid(6000))
                .EUt(VA[MV])
                .duration(200)
                .save(consumer);

        //  Bromine + Chlorine -> Bromine Trifluoride
        CHEMICAL_RECIPES.recipeBuilder("bromine_trifluoride")
                .inputFluids(Bromine.getFluid(1000))
                .inputFluids(Chlorine.getFluid(3000))
                .circuitMeta(3)
                .outputFluids(BromineTrifluoride.getFluid(1000))
                .EUt(VA[LV])
                .duration(120)
                .save(consumer);

        //  Gold + Chlorine -> Gold Chloride
        ELECTROLYZER_RECIPES.recipeBuilder("gold_chloride")
                .inputItems(dust, Gold, 2)
                .inputFluids(Chlorine.getFluid(6000))
                .outputFluids(GoldChloride.getFluid(1000))
                .EUt(VA[MV])
                .duration(360)
                .save(consumer);

        //  Impure Naquadria Solution + Fluorine -> Hexafluoride Naquadria Solution
        CHEMICAL_RECIPES.recipeBuilder("hexafluoride_naquadria_solution")
                .inputFluids(ImpureNaquadriaSolution.getFluid(1000))
                .inputFluids(Fluorine.getFluid(6000))
                .outputFluids(HexafluorideNaquadriaSolution.getFluid(1000))
                .EUt(VA[IV])
                .duration(400)
                .save(consumer);

        //  HexafluorideNaquadriaSolution + Radon Difluoride -> Radon Naquadria Octafluoride + Naquadria Residue Solution
        CHEMICAL_RECIPES.recipeBuilder("radon_naquadria_octafluoride")
                .inputFluids(HexafluorideNaquadriaSolution.getFluid(1000))
                .inputFluids(RadonDifluoride.getFluid(1000))
                .outputFluids(RadonNaquadriaOctafluoride.getFluid(1000))
                .outputFluids(NaquadriaResidueSolution.getFluid(1000))
                .EUt(VA[HV])
                .duration(800)
                .cleanroom(CleanroomType.CLEANROOM)
                .save(consumer);

        //  Radon + Fluorine -> Radon Difluoride
        CHEMICAL_RECIPES.recipeBuilder("radon_naquadria")
                .inputFluids(Radon.getFluid(1000))
                .inputFluids(Fluorine.getFluid(2000))
                .circuitMeta(2)
                .outputFluids(RadonDifluoride.getFluid(1000))
                .EUt(VA[MV])
                .duration(100)
                .save(consumer);

        //  Naquadria Residue Solution -> Indium Phosphide + Naquadria Solution
        // todo BURNER_REACTOR_RECIPES
        BLAST_RECIPES.recipeBuilder("naquadria_solution")
                .inputFluids(NaquadriaResidueSolution.getFluid(2000))
                .outputItems(dust, IndiumPhosphide)
                .outputFluids(NaquadriaSolution.getFluid(1000))
                .EUt(VA[IV])
                .duration(1200)
                .blastFurnaceTemp(880)
                .save(consumer);

        //  Naquadria Solution -> Sulfur + Naquadria Waste + Sulfur dust
        DRYER_RECIPES.recipeBuilder("naquadria_waste")
                .inputFluids(NaquadriaSolution.getFluid(3000))
                .outputItems(dust, Sulfur, 6)
                .outputFluids(NaquadriaWaste.getFluid(1000))
                .EUt(VA[HV])
                .duration(100)
                .save(consumer);

        //  Caesium Xenontrioxide Fluoride
        CHEMICAL_RECIPES.recipeBuilder("caesium_xenontrioxide_fluoride")
                .inputFluids(CaesiumFluoride.getFluid(1000))
                .inputFluids(XenonTrioxide.getFluid(1000))
                .outputFluids(CaesiumXenontrioxideFluoride.getFluid(1000))
                .EUt(VA[MV])
                .duration(480)
                .save(consumer);

        //  Caesium + Fluorine -> Cesium Fluoride
        CHEMICAL_RECIPES.recipeBuilder("caesium_fluoride")
                .inputItems(dust, Caesium)
                .inputFluids(Fluorine.getFluid(1000))
                .circuitMeta(1)
                .outputFluids(CaesiumFluoride.getFluid(1000))
                .EUt(VA[MV])
                .duration(100)
                .save(consumer);

        //  Xenon + Oxygen -> Xenon Trioxide
        CHEMICAL_RECIPES.recipeBuilder("xenon_trioxide")
                .inputFluids(Xenon.getFluid(1000))
                .inputFluids(Oxygen.getFluid(3000))
                .outputFluids(XenonTrioxide.getFluid(1000))
                .EUt(VA[MV])
                .duration(100)
                .save(consumer);

        //  Radon Naquadria Octafluoride + Caesium Xenontrioxide Fluoride -> Naquadria Caesium Xenonnonfluoride + Radon Trioxide
        CHEMICAL_RECIPES.recipeBuilder("naquadria_caesium_xenonnonfluoride")
                .inputFluids(RadonNaquadriaOctafluoride.getFluid(1000))
                .inputFluids(CaesiumXenontrioxideFluoride.getFluid(1000))
                .outputFluids(NaquadriaCaesiumXenonnonfluoride.getFluid(1000))
                .outputFluids(RadonTrioxide.getFluid(1000))
                .EUt(VA[IV])
                .duration(800)
                .cleanroom(CleanroomType.CLEANROOM)
                .save(consumer);

        //  Naquadria Caesium Xenonnonfluoride + Nitryl Fluoride -> Naquadria Caesiumfluoride + Nitrosonium Octafluoroxenate
        // todo CRYOGENIC_REACTOR_RECIPES
        CHEMICAL_RECIPES.recipeBuilder("nitrosonium_octafluoroxenate")
                .inputFluids(NaquadriaCaesiumXenonnonfluoride.getFluid(1000))
                .inputFluids(NitrylFluoride.getFluid(2000))
                .outputFluids(NaquadriaCaesiumfluoride.getFluid(1000))
                .outputFluids(NitrosoniumOctafluoroxenate.getFluid(1000))
                .EUt(VA[EV])
                .duration(400)
                // .temperature(75)
                .cleanroom(CleanroomType.CLEANROOM)
                .save(consumer);

        //  Nitrogen Dioxide + Fluorine -> Nitryl Fluoride
        CHEMICAL_RECIPES.recipeBuilder("nitryl_fluoride")
                .inputFluids(NitrogenDioxide.getFluid(1000))
                .inputFluids(Fluorine.getFluid(1000))
                .outputFluids(NitrylFluoride.getFluid(1000))
                .EUt(VA[MV])
                .duration(160)
                .save(consumer);

        //  Sulfuric Acid + Naquadria Caesiumfluoride -> Acidic Naquadria Caesiumfluoride
        MIXER_RECIPES.recipeBuilder("acidic_naquadria_caesiumfluoride")
                .inputFluids(SulfuricAcid.getFluid(2000))
                .inputFluids(NaquadriaCaesiumfluoride.getFluid(1000))
                .outputFluids(AcidicNaquadriaCaesiumfluoride.getFluid(3000))
                .EUt(VA[IV])
                .duration(360)
                .save(consumer);

        /*  Acidic Naquadria Caesiumfluoride -> Naquadria Sulfate + Caesium + Fluorine
        ELECTROLYZER_RECIPES.recipeBuilder("acidic_naquadria_caesiumfluoride")
                .inputFluids(AcidicNaquadriaCaesiumfluoride.getFluid(1000))
                .outputItems(dust, NaquadriaSulfate)
                .outputItems(dust, Caesium)
                .outputFluids(Fluorine.getFluid(3000))
                .EUt(VA[LuV])
                .duration(120)
                .save(consumer); */

        //  Acidic Naquadria Solution Cycle
        // todo BURNER_REACTOR_RECIPES
        BLAST_RECIPES.recipeBuilder("impure_enriched_naquadah_solution")
                .inputFluids(AcidicNaquadriaSolution.getFluid(3000))
                .outputFluids(NaquadriaWaste.getFluid(1000))
                .outputFluids(ImpureEnrichedNaquadahSolution.getFluid(1000))
                .EUt(VA[ZPM])
                .duration(1000)
                .blastFurnaceTemp(1280)
                .save(consumer);

        NEUTRON_ACTIVATOR_RECIPES.recipeBuilder("naquadria")
                .inputFluids(AcidicNaquadriaCaesiumfluoride.getFluid(9000))
                .outputItems(dust, NaquadriaSulfate, 3)
                .outputItems(dust, Caesium, 3)
                .outputFluids(Fluorine.getFluid(18000))
                .duration(dur(5))
                .addCondition(setNA(1100, 1050))
                .save(consumer);

        NEUTRON_ACTIVATOR_RECIPES.recipeBuilder("enriched_naquadah")
                .inputFluids(AcidicEnrichedNaquadahSolution.getFluid(16000))
                .outputItems(dust, EnrichedNaquadahSulfate, 15)
                .outputFluids(ImpureNaquadriaSolution.getFluid(1000))
                .duration(dur(6))
                .addCondition(setNA(480, 460))
                .save(consumer);

        NEUTRON_ACTIVATOR_RECIPES.recipeBuilder("naquadah")
                .inputItems(dust, NaquadahOxideMixture, 4)
                .inputFluids(FluoroantimonicAcid.getFluid(6000))
                .outputItems(dust, TitaniumTrifluoride, 2)
                .outputItems(dust, Naquadah, 2)
                .chancedOutput(dust, Gallium, 5000, 0)
                .duration(dur(5))
                .addCondition(setNA(230, 220))
                .save(consumer);

        CHEMICAL_RECIPES.recipeBuilder(EnrichedNaquadahOxideMixture.getName())
                .inputItems(dust, EnrichedNaquadahOxideMixture, 2)
                .inputFluids(FluoroantimonicAcid.getFluid(3000))
                .outputItems(dust, TitaniumTrifluoride, 1)
                .outputFluids(ImpureEnrichedNaquadahSolution.getFluid(2000))
                .EUt(VA[EV])
                .duration(dur(10))
                .save(consumer);

        CHEMICAL_RECIPES.recipeBuilder(NaquadriaOxideMixture.getName())
                .inputItems(dust, NaquadriaOxideMixture, 2)
                .inputFluids(FluoroantimonicAcid.getFluid(3000))
                .outputItems(dust, TitaniumTrifluoride, 1)
                .outputFluids(ImpureNaquadriaSolution.getFluid(2000))
                .EUt(VA[IV])
                .duration(dur(20))
                .save(consumer);
    }

}
