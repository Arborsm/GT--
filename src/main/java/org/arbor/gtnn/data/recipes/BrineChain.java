package org.arbor.gtnn.data.recipes;

import net.minecraft.data.recipes.FinishedRecipe;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.HV;
import static com.gregtechceu.gtceu.api.GTValues.VA;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.DULL;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.dust;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static org.arbor.gtnn.data.GTNNMaterials.*;
import static org.arbor.gtnn.data.GTNNRecipeTypes.DEHYDRATOR_RECIPES;

public class BrineChain {
    //  Code from GT lite core by Magic_Sweep
    //  Complete Bromine-Iodine Chain
    //  in gcys, this is infinite bromine until Sodium Chloride Solution is separate from Salt Water

    public static void init(Consumer<FinishedRecipe> provider) {
        IodineChain(provider);
        BromineChain(provider);
    }

    public static void init() {
        addFluid(Bromine);
        addDust(Iodine);
        IodizedBrine = Builder("iodized_brine")
                .fluid().color(0x525246)
                .buildAndRegister()
                .setFormula("I?", false);
        IodineBrineMixture = Builder("iodine_brine_mixture")
                .fluid()
                .color(0x525234)
                .buildAndRegister()
                .setFormula("I?Cl", false);

        //  18059 Brominated Brine
        BrominatedBrine = Builder("brominated_brine")
                .fluid()
                .color(0xA9A990)
                .buildAndRegister()
                .setFormula("Br?", false);

        //  24039 Iodine Slurry
        IodineSlurry = Builder("iodine_slurry")
                .fluid()
                .color(0x292923)
                .buildAndRegister()
                .setFormula("I?", false);

        //  24040 Acidic Brominated Brine
        AcidicBrominatedBrine = Builder("acidic_brominated_brine")
                .acid()
                .color(0xC6A76F)
                .buildAndRegister()
                .setFormula("Br?(H2SO4)Cl", true);

        //  24041 Bromine Sulfate Solutions
        BromineSulfateSolution = Builder("bromine_sulfate_solution")
                .fluid()
                .color(0xCC9966)
                .buildAndRegister()
                .setFormula("H2SO4Br(H2O)Cl2", true);

        //  24042 Overheated Bromine Sulfate Gas
        OverheatedBromineSulfateSolution = Builder("overheated_bromine_sulfate_gas")
                .gas()
                .color(0xC69337)
                .iconSet(DULL)
                .buildAndRegister()
                .setFormula("H2SO4Br(H2O)2Cl2", true);

        //  24043 Wet Bromine
        WetBromine = Builder("wet_bromine")
                .gas()
                .color(0xDB5C5C)
                .iconSet(DULL)
                .buildAndRegister()
                .setFormula("Br(H2O)", true);

        //  24044 Debrominated Water
        DebrominatedWater = Builder("debrominated_water")
                .fluid()
                .color(0x24A3A3)
                .components(Hydrogen, 2, Oxygen, 1)
                .buildAndRegister();
    }

    private static void IodineChain(Consumer<FinishedRecipe> provider) {

        //  KNO3 + HCl -> K + I? todo INDUSTRIAL_ROASTER_RECIPES
        BLAST_RECIPES.recipeBuilder("iodine_brine")
                .inputItems(dust, Saltpeter)
                .inputFluids(SaltWater.getFluid(1000))
                .outputItems(dust, Potassium)
                .outputFluids(IodizedBrine.getFluid(1000))
                .circuitMeta(1)
                .EUt(1280)
                .duration(240)
                .blastFurnaceTemp(1128)
                .save(provider);

        //  I? + 0.3 Cl -> I?Cl
        MIXER_RECIPES.recipeBuilder("iodine_brine_mixture")
                .inputFluids(IodizedBrine.getFluid(1000))
                .inputFluids(Chlorine.getFluid(300))
                .outputFluids(IodineBrineMixture.getFluid(1300))
                .EUt(480)
                .duration(240)
                .save(provider);

        //  I?Cl -> Br? + I?
        CENTRIFUGE_RECIPES.recipeBuilder("brominated_brine")
                .inputFluids(IodineBrineMixture.getFluid(1300))
                .outputFluids(BrominatedBrine.getFluid(1000))
                .outputFluids(IodineSlurry.getFluid(300))
                .EUt(980)
                .duration(120)
                .save(provider);

        //  I? -> I
        DEHYDRATOR_RECIPES.recipeBuilder("iodine")
                .inputFluids(IodineSlurry.getFluid(1200))
                .outputItems(dust, Iodine)
                .EUt(1280)
                .duration(200)
                .save(provider);
    }

    private static void BromineChain(Consumer<FinishedRecipe> provider) {

        //  Br? + H2SO4 -> Br?(H2SO4)
        MIXER_RECIPES.recipeBuilder("acidic_brominated_brine")
                .inputFluids(BrominatedBrine.getFluid(1000))
                .inputFluids(SulfuricAcid.getFluid(1000))
                .outputFluids(AcidicBrominatedBrine.getFluid(1000))
                .EUt(480)
                .duration(200)
                .save(provider);

        //  Br?(H2SO4) + SO2 + H2O -> H2SO4Br(H2O)Cl2
        CHEMICAL_RECIPES.recipeBuilder("bromine_sulfate_solution")
                .inputFluids(AcidicBrominatedBrine.getFluid(1000))
                .inputFluids(SulfurDioxide.getFluid(1000))
                .inputFluids(Water.getFluid(1000))
                .circuitMeta(3)
                .outputFluids(BromineSulfateSolution.getFluid(1000))
                .outputFluids(SaltWater.getFluid(1000))
                .EUt(480)
                .duration(200)
                .save(provider);

        //  2H2SO4Br(H2O)Cl2 + H2O -> 3H2SO4Br(H2O)2Cl2 todo INDUSTRIAL_ROASTER_RECIPES
        CRACKING_RECIPES.recipeBuilder("overheated_bromine_sulfate_gas")
                .inputFluids(BromineSulfateSolution.getFluid(2000))
                .inputFluids(Steam.getFluid(1000))
                .outputFluids(OverheatedBromineSulfateSolution.getFluid(3000))
                .EUt(VA[HV])
                .duration(400)
                .save(provider);

        //  3H2SO4Br(H2O)2Cl2 -> Br(H2O) + H2O + 2Cl + H2SO4
        CENTRIFUGE_RECIPES.recipeBuilder("overheated_bromine_sulfate_gas")
                .inputFluids(OverheatedBromineSulfateSolution.getFluid(3000))
                .outputFluids(WetBromine.getFluid(1000))
                .outputFluids(DebrominatedWater.getFluid(1000))
                .outputFluids(Chlorine.getFluid(2000))
                .outputFluids(SulfuricAcid.getFluid(1000))
                .EUt(VA[HV])
                .duration(280)
                .save(provider);

        //  Br(H2O) -> Br + H2O (lost)
        DEHYDRATOR_RECIPES.recipeBuilder("wet_bromine")
                .inputFluids(WetBromine.getFluid(1000))
                .outputFluids(Bromine.getFluid(1000))
                .EUt(360)
                .duration(80)
                .save(provider);

        //  Salt Water recycle
        DEHYDRATOR_RECIPES.recipeBuilder("debrominated_water")
                .inputFluids(DebrominatedWater.getFluid(1000))
                .outputFluids(SaltWater.getFluid(100))
                .EUt(360)
                .duration(80)
                .save(provider);
    }

}