package org.arbor.gtnn.data.recipes;

import com.gregtechceu.gtceu.api.data.chemical.material.stack.UnificationEntry;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.data.recipe.CustomTags;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import net.minecraft.data.recipes.FinishedRecipe;
import org.arbor.gtnn.data.*;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static org.arbor.gtnn.data.GTNNMachines.NEUTRON_ACCELERATOR;
import static org.arbor.gtnn.data.GTNNMaterials.NaquadahBasedLiquidFuel;
import static org.arbor.gtnn.data.GTNNRecipes.dur;
import static org.arbor.gtnn.data.GTNNRecipes.setNA;

public class SelfRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        machineRecipes(provider);
        blockRecipes(provider);
        materialRecipes(provider);
    }

    private static void materialRecipes(Consumer<FinishedRecipe> provider) {
        DefaultRecipes.addBOOMRecipes("enriched_uranium_nugget", GTNNItems.EncapsulatedUranium, GTNNItems.EnrichedUraniumNugget, VA[LV], dur(1), 1, provider);
        DefaultRecipes.addBOOMRecipes("enriched_thorium_nugget", GTNNItems.EncapsulatedThorium, GTNNItems.EnrichedThoriumNugget, VA[LV], dur(1), 1, provider);
        DefaultRecipes.addBOOMRecipes("enriched_plutonium_nugget", GTNNItems.EncapsulatedPlutonium, GTNNItems.EnrichedPlutoniumNugget, VA[LV], dur(1), 1, provider);
        GTNNRecipeTypes.PRECISION_ASSEMBLY_RECIPES.recipeBuilder("quark_core")
                .inputItems(CustomTags.IV_CIRCUITS, 2)
                .inputItems(lens, Diamond, 8)
                .inputItems(GTItems.NAND_MEMORY_CHIP.asStack(16))
                .inputItems(rotor, Aluminium)
                .inputFluids(Polyethylene.getFluid(576))
                .inputFluids(SodiumPotassium.getFluid(288))
                .inputFluids(Lubricant.getFluid(144))
                .inputFluids(StyreneButadieneRubber.getFluid(144))
                .outputItems(GTNNItems.QuarkCore)
                .EUt(VA[LuV]).duration(dur(5)).save(provider);
        MIXER_RECIPES.recipeBuilder("graphite_uranium_mixture")
                .inputItems(dust, Graphite, 3)
                .inputItems(dust, Uranium238)
                .outputItems(dust, GTNNMaterials.GraphiteUraniumMixture, 4)
                .EUt(VA[LV]).duration(dur(1.7)).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("encapsulated_uranium")
                .inputItems(dust, GTNNMaterials.GraphiteUraniumMixture, 4)
                .inputItems(foil, TungstenCarbide, 2)
                .outputItems(GTNNItems.EncapsulatedUranium)
                .EUt(VA[HV]).duration(dur(70)).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("uranium_carbide_thorium_mixture")
                .inputItems(dust, Thorium, 11)
                .inputItems(dust, GTNNMaterials.Thorium232)
                .inputItems(dust, Uranium235)
                .inputItems(dust, Carbon, 3)
                .outputItems(dust, GTNNMaterials.UraniumCarbideThoriumMixture, 16)
                .EUt(VA[LV]).duration(dur(2.35)).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("encapsulated_thorium")
                .inputItems(dust, GTNNMaterials.UraniumCarbideThoriumMixture, 64)
                .inputItems(foil, TungstenSteel, 4)
                .outputItems(GTNNItems.EnrichedThorium)
                .EUt(VA[HV]).duration(dur(15)).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("plutonium_oxide_uranium_mixture")
                .inputItems(dust, Plutonium239, 10)
                .inputItems(dust, Uranium238, 2)
                .inputItems(dust, Carbon, 8)
                .inputFluids(Oxygen.getFluid(12000))
                .outputItems(dust, GTNNMaterials.PlutoniumOxideUraniumMixture, 32)
                .EUt(VA[LV]).duration(dur(1.25)).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("encapsulated_plutonium")
                .inputItems(dust, GTNNMaterials.PlutoniumOxideUraniumMixture, 8)
                .inputItems(foil, HSSS, 4)
                .outputItems(GTNNItems.EncapsulatedPlutonium)
                .EUt(VA[HV]).duration(dur(70)).save(provider);
        COMPRESSOR_RECIPES.recipeBuilder("enriched_uranium")
                .inputItems(GTNNItems.EnrichedUraniumNugget.asStack(9))
                .outputItems(GTNNItems.EnrichedUranium)
                .EUt(VA[HV]).duration(dur(30)).save(provider);
        COMPRESSOR_RECIPES.recipeBuilder("enriched_thorium")
                .inputItems(GTNNItems.EnrichedThoriumNugget.asStack(9))
                .outputItems(GTNNItems.EnrichedThorium)
                .EUt(VA[MV]).duration(dur(10)).save(provider);
        COMPRESSOR_RECIPES.recipeBuilder("enriched_plutonium")
                .inputItems(GTNNItems.EnrichedPlutoniumNugget.asStack(9))
                .outputItems(GTNNItems.EnrichedPlutonium)
                .EUt(VA[MV]).duration(dur(60)).save(provider);
        FUSION_RECIPES.recipeBuilder("californium")
                .inputFluids(Plutonium239.getFluid(48))
                .inputFluids(Beryllium.getFluid(48))
                .outputFluids(Californium.getFluid(48))
                .fusionStartEU(120_000_000)
                .EUt(VA[ZPM]).duration(dur(12)).save(provider);
        FUSION_RECIPES.recipeBuilder("oganesson")
                .inputFluids(Californium.getFluid(32))
                .inputFluids(Calcium.getFluid(720))
                .outputFluids(Oganesson.getFluid(720))
                .fusionStartEU(600_000_000)
                .EUt(VA[ZPM]).duration(dur(12)).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("inverter")
                .inputItems(plate, NetherQuartz, 2)
                .inputItems(CustomTags.MV_CIRCUITS)
                .inputItems(GTItems.COVER_SCREEN)
                .inputItems(GTItems.DIODE.asStack(16))
                .inputItems(wireGtSingle, Aluminium, 8)
                .inputFluids(SolderingAlloy.getFluid(144))
                .outputItems(GTNNItems.INVERTER)
                .EUt(VA[MV]).duration(dur(12)).save(provider);
        MIXER_RECIPES.recipeBuilder("thorium_based_liquid_fuel")
                .inputItems(GTNNItems.EnrichedThorium)
                .inputItems(dust, Lithium, 4)
                //.inputItems(dust, ) Dragon
                .inputFluids(Mercury.getFluid(1000))
                .outputFluids(GTNNMaterials.ThoriumBasedLiquidFuel.getFluid(1000))
                .circuitMeta(2)
                .EUt(VHA[HV]).duration(dur(150)).save(provider);
        MIXER_RECIPES.recipeBuilder("uranium_based_liquid_fuel")
                .inputItems(GTNNItems.EnrichedUranium)
                .inputItems(dust, Potassium, 8)
                //.inputItems(dust, ) Qt
                .inputFluids(Radon.getFluid(1000))
                .outputFluids(GTNNMaterials.UraniumBasedLiquidFuel.getFluid(1000))
                .circuitMeta(1)
                .EUt(VHA[LuV]).duration(dur(10)).save(provider);
        MIXER_RECIPES.recipeBuilder("plutonium_based_liquid_fuel")
                .inputItems(GTNNItems.EnrichedPlutonium)
                .inputItems(dust, Neutronium, 8)
                .inputItems(dust, Caesium, 16)
                .inputItems(dust, Naquadah, 2)
                .outputFluids(GTNNMaterials.PlutoniumBasedLiquidFuel.getFluid(1000))
                .circuitMeta(1)
                .EUt(VA[LuV]).duration(dur(18)).save(provider);
        MIXER_RECIPES.recipeBuilder("thorium_based_liquid_fuel_excited")
                .inputFluids(GTNNMaterials.ThoriumBasedLiquidFuel.getFluid(1000))
                .inputFluids(Helium.getFluid(250))
                .outputFluids(GTNNMaterials.ThoriumBasedLiquidFuelExcited.getFluid(1000))
                .circuitMeta(1)
                .EUt(VHA[IV]).duration(dur(6)).save(provider);
        GTNNRecipeTypes.NEUTRON_ACTIVATOR_RECIPES.recipeBuilder("uranium_based_liquid_fuel_excited")
                .notConsumable(plate, Tungsten)
                .inputFluids(GTNNMaterials.UraniumBasedLiquidFuel.getFluid(100))
                .outputFluids(GTNNMaterials.UraniumBasedLiquidFuelExcited.getFluid(100))
                .addCondition(setNA(550, 450))
                .duration(dur(4)).save(provider);
        FUSION_RECIPES.recipeBuilder("uranium_based_liquid_fuel_excited")
                .inputFluids(GTNNMaterials.UraniumBasedLiquidFuel.getFluid(10))
                .inputFluids(Hydrogen.getFluid(100))
                .outputFluids(GTNNMaterials.UraniumBasedLiquidFuelExcited.getFluid(10))
                .fusionStartEU(200_000_000)
                .EUt(VA[IV]).duration(dur(2)).save(provider);
        GTNNRecipeTypes.NEUTRON_ACTIVATOR_RECIPES.recipeBuilder("plutonium_based_liquid_fuel_excited")
                .notConsumable(plate, Tritanium)
                .inputFluids(GTNNMaterials.PlutoniumBasedLiquidFuel.getFluid(100))
                .outputFluids(GTNNMaterials.PlutoniumBasedLiquidFuelExcited.getFluid(100))
                .addCondition(setNA(600, 500))
                .duration(dur(4)).save(provider);
        FUSION_RECIPES.recipeBuilder("plutonium_based_liquid_fuel_excited")
                .inputFluids(Lutetium.getFluid(16))
                .inputFluids(GTNNMaterials.PlutoniumBasedLiquidFuel.getFluid(20))
                .outputFluids(GTNNMaterials.PlutoniumBasedLiquidFuelExcited.getFluid(20))
                .fusionStartEU(220_000_000)
                .EUt(VA[LuV]).duration(dur(1)).save(provider);
        GTNNRecipeTypes.NEUTRON_ACTIVATOR_RECIPES.recipeBuilder("thorium_based_liquid_fuel_depleted")
                .inputFluids(GTNNMaterials.ThoriumBasedLiquidFuelExcited.getFluid(200))
                .outputFluids(GTNNMaterials.ThoriumBasedLiquidFuelDepleted.getFluid(200))
                .addCondition(setNA(700, 500))
                .duration(dur(500)).save(provider);
        GTNNRecipeTypes.LARGE_NAQUADAH_REACTOR_RECIPES.recipeBuilder("thorium_based_liquid_fuel_depleted")
                .inputFluids(GTNNMaterials.ThoriumBasedLiquidFuelExcited.getFluid(1000))
                .outputFluids(GTNNMaterials.ThoriumBasedLiquidFuelDepleted.getFluid(1000))
                .EUt(-2200)
                .duration(dur(25)).save(provider);
        GTNNRecipeTypes.LARGE_NAQUADAH_REACTOR_RECIPES.recipeBuilder("uranium_based_liquid_fuel_depleted")
                .inputFluids(GTNNMaterials.UraniumBasedLiquidFuelExcited.getFluid(1000))
                .outputFluids(GTNNMaterials.UraniumBasedLiquidFuelDepleted.getFluid(1000))
                .EUt(-12960)
                .duration(dur(5)).save(provider);
        GTNNRecipeTypes.LARGE_NAQUADAH_REACTOR_RECIPES.recipeBuilder("plutonium_based_liquid_fuel_depleted")
                .inputFluids(GTNNMaterials.PlutoniumBasedLiquidFuelExcited.getFluid(1000))
                .outputFluids(GTNNMaterials.PlutoniumBasedLiquidFuelDepleted.getFluid(1000))
                .EUt(-32400)
                .duration(dur(7.5)).save(provider);
        CENTRIFUGE_RECIPES.recipeBuilder("thorium_based_liquid_fuel_depleted")
                .inputFluids(GTNNMaterials.ThoriumBasedLiquidFuelDepleted.getFluid(500))
                .outputItems(dust, GTNNMaterials.Thorium232, 32)
                .chancedOutput(dust, GTNNMaterials.Thorium232, 8, 8000, 0)
                .outputItems(dust, Praseodymium, 32)
                .chancedOutput(dust, Praseodymium, 16, 8000, 0)
                .chancedOutput(dust, Boron, 3000, 0)
                .chancedOutput(dust, Indium, 2, 5000, 0)
                .circuitMeta(1)
                .EUt(1040).duration(dur(37.5)).save(provider);
        CENTRIFUGE_RECIPES.recipeBuilder("uranium_based_liquid_fuel_depleted")
                .inputFluids(GTNNMaterials.UraniumBasedLiquidFuelDepleted.getFluid(1000))
                .chancedOutput(dust, Lead, 16, 6000, 0)
                .chancedOutput(dust, Bismuth, 1000, 0)
                .chancedOutput(dust, Barium, 6, 5000, 0)
                .circuitMeta(1)
                .EUt(1040).duration(dur(50)).save(provider);
        CENTRIFUGE_RECIPES.recipeBuilder("plutonium_based_liquid_fuel_depleted")
                .inputFluids(GTNNMaterials.PlutoniumBasedLiquidFuelDepleted.getFluid(1000))
                .chancedOutput(dust, Tritanium, 9, 5000, 0)
                .chancedOutput(dust, Cerium, 4, 8000, 0)
                .chancedOutput(dust, Gold, 2, 7500, 0)
                .outputFluids(Krypton.getFluid(144))
                .circuitMeta(1)
                .EUt(VA[IV]).duration(dur(125)).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("plate_radiation")
                .inputItems(plateDense, Iridium, 8)
                .inputItems(plateDense, NaquadahAlloy, 8)
                .inputFluids(Lead.getFluid(1152))
                .outputItems(GTNNItems.PlateRadiationProtection)
                .circuitMeta(1)
                .EUt(VA[EV]).duration(dur(20)).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("plate_radiation_2")
                .inputItems(plateDense, Lanthanum, 4)
                .inputItems(plateDense, NaquadahAlloy, 8)
                .inputFluids(Lead.getFluid(1152))
                .outputItems(GTNNItems.PlateRadiationProtection)
                .circuitMeta(1)
                .EUt(VA[EV]).duration(dur(20)).save(provider);
        BENDER_RECIPES.recipeBuilder("dense_lanthanum")
                .inputItems(plate, Lanthanum, 9)
                .outputItems(plateDense, Lanthanum)
                .circuitMeta(9)
                .EUt(96).duration(dur(62.1)).save(provider);
        MIXER_RECIPES.recipeBuilder("naquadah_based_liquid_fuel")
                .inputItems(dust, Naquadria, 42)
                .inputItems(dust, Cerium, 16)
                .inputItems(dust, Neodymium, 16)
                .outputFluids(NaquadahBasedLiquidFuel.getFluid(1000))
                .EUt(VA[IV]).duration(dur(15)).save(provider);
        FUSION_RECIPES.recipeBuilder("naquadah_based_liquid_fuel_excited")
                .inputFluids(GTNNMaterials.NaquadahBasedLiquidFuel.getFluid(800))
                .inputFluids(Radon.getFluid(200))
                .outputFluids(GTNNMaterials.NaquadahBasedLiquidFuelExcited.getFluid(100))
                .fusionStartEU(320_000_000)
                .EUt(26000).duration(dur(25)).save(provider);
        GTNNRecipeTypes.LARGE_NAQUADAH_REACTOR_RECIPES.recipeBuilder("naquadah_based_liquid_fuel_depleted")
                .inputFluids(GTNNMaterials.NaquadahBasedLiquidFuelExcited.getFluid(1))
                .outputFluids(GTNNMaterials.NaquadahBasedLiquidFuelDepleted.getFluid(1))
                .EUt(-975_000L)
                .duration(dur(3)).save(provider);
        CENTRIFUGE_RECIPES.recipeBuilder("naquadah_based_liquid_fuel_depleted")
                .inputFluids(GTNNMaterials.NaquadahBasedLiquidFuelDepleted.getFluid(125))
                .chancedOutput(dust, Naquadah, 8, 9000, 0)
                .chancedOutput(dust, Naquadah, 6, 8500, 0)
                .chancedOutput(dust, Naquadah, 4, 5000, 0)
                .chancedOutput(dust, Neodymium, 4, 4000, 0)
                .chancedOutput(dust, Europium, 4, 2000, 0)
                .outputFluids(Xenon.getFluid(18))
                .EUt(VA[EV]).duration(dur(30));
    }

    private static void blockRecipes(Consumer<FinishedRecipe> provider) {
        VanillaRecipeHelper.addShapedRecipe(provider, true, "clean_machine_casing", GTNNCasingBlocks.PROCESS_MACHINE_CASING.asStack(),
                "ABA", "BCB", "ABA",
                'A', new UnificationEntry(foil, StainlessSteel),
                'B', CustomTags.IV_CIRCUITS,
                'C', GTBlocks.CASING_STEEL_SOLID
        );
        ASSEMBLER_RECIPES.recipeBuilder("high_speed_pipe_block")
                .inputItems(pipeHugeFluid, StainlessSteel)
                .inputItems(frameGt, BlueAlloy, 32)
                .inputItems(wireGtSingle, MercuryBariumCalciumCuprate, 32)
                .inputItems(plate, Beryllium, 32)
                .inputItems(CustomTags.IV_CIRCUITS)
                .outputItems(GTNNMachines.HIGH_SPEED_PIPE_BLOCK.asStack())
                .EUt(VA[EV]).duration(dur(15)).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("radiation_proof_machine_casing")
                .inputItems(plateDense, Lead, 6)
                .inputItems(frameGt, TungstenSteel)
                .inputFluids(Concrete.getFluid(1296))
                .outputItems(GTNNCasingBlocks.RADIATION_PROOF_MACHINE_CASING)
                .EUt(VA[IV]).duration(dur(2)).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("mar_casing")
                .inputItems(GTNNItems.PlateRadiationProtection.asStack(6))
                .inputItems(frameGt, Europium)
                .inputItems(GTItems.FIELD_GENERATOR_MV)
                .outputItems(GTNNCasingBlocks.MAR_CASING)
                .circuitMeta(1)
                .EUt(VA[EV]).duration(dur(20)).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("frame_radiation")
                .inputItems(rodLong, NaquadahAlloy, 8)
                .inputItems(frameGt, HSSE, 4)
                .outputItems(frameGt, GTNNMaterials.RadiationProtection)
                .circuitMeta(24)
                .EUt(VA[EV]).duration(dur(16)).save(provider);
    }

    private static void machineRecipes(Consumer<FinishedRecipe> provider) {
        ASSEMBLY_LINE_RECIPES.recipeBuilder("large_naquadah_reactor")
                .inputItems(frameGt, Neutronium, 8)
                .inputItems(GTNNItems.PlateRadiationProtection.asStack(16))
                .inputItems(GTItems.FIELD_GENERATOR_ZPM.asStack(2))
                .inputItems(GTItems.ELECTRIC_PUMP_ZPM.asStack(8))
                .inputItems(CustomTags.UV_CIRCUITS, 4)
                .inputItems(wireGtOctal, IndiumTinBariumTitaniumCuprate, 8)
                .inputItems(pipeHugeFluid, Naquadah, 4)
                .inputItems(plate, NaquadahAlloy, 8)
                .inputItems(screw, Osmium, 16)
                .outputItems(GTNNMachines.LargeNaquadahReactor)
                .EUt(VA[ZPM]).duration(dur(210)).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("neutron_accelerator_mv")
                .inputItems(GTNNItems.INVERTER.asStack())
                .inputItems(GTMachines.HULL[MV].asStack())
                .inputItems(cableGtSingle, Copper, 2)
                .inputItems(plate, Polyethylene)
                .inputItems(plate, Beryllium, 2)
                .inputItems(GTItems.ELECTRIC_MOTOR_MV)
                .outputItems(NEUTRON_ACCELERATOR[MV].asStack())
                .EUt(VA[MV]).duration(dur(15)).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("neutron_accelerator_hv")
                .inputItems(GTNNItems.INVERTER.asStack())
                .inputItems(GTMachines.HULL[HV].asStack())
                .inputItems(cableGtSingle, Gold, 2)
                .inputItems(plate, PolyvinylChloride)
                .inputItems(plateDouble, Beryllium, 2)
                .inputItems(GTItems.ELECTRIC_MOTOR_HV.asStack(2))
                .outputItems(NEUTRON_ACCELERATOR[HV].asStack())
                .EUt(VA[HV]).duration(dur(15)).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("neutron_accelerator_ev")
                .inputItems(GTNNItems.INVERTER.asStack())
                .inputItems(GTMachines.HULL[EV].asStack())
                .inputItems(cableGtSingle, Aluminium, 2)
                .inputItems(plate, StyreneButadieneRubber)
                .inputItems(plate, IronMagnetic, 4)
                .inputItems(plate, TungstenCarbide, 2)
                .inputItems(GTItems.ELECTRIC_MOTOR_EV.asStack(2))
                .outputItems(NEUTRON_ACCELERATOR[EV].asStack())
                .EUt(VA[EV]).duration(dur(15)).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("neutron_accelerator_iv")
                .inputItems(GTNNItems.INVERTER.asStack())
                .inputItems(GTMachines.HULL[IV].asStack())
                .inputItems(cableGtSingle, Tungsten, 2)
                .inputItems(plate, SiliconeRubber)
                .inputItems(plate, SteelMagnetic, 4)
                .inputItems(plateDouble, TungstenCarbide, 2)
                .inputItems(GTItems.ELECTRIC_MOTOR_IV.asStack(2))
                .outputItems(NEUTRON_ACCELERATOR[IV].asStack())
                .EUt(VA[IV]).duration(dur(15)).save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("neutron_accelerator_luv")
                .inputItems(GTNNItems.INVERTER.asStack(2))
                .inputItems(GTMachines.HULL[LuV].asStack())
                .inputItems(cableGtSingle, YttriumBariumCuprate, 2)
                .inputItems(plate, NetherStar)
                .inputItems(plate, Polybenzimidazole, 4)
                .inputItems(plate, NeodymiumMagnetic, 4)
                .inputItems(plate, NeodymiumMagnetic, 4)
                .inputItems(GTItems.ELECTRIC_MOTOR_LuV.asStack(2))
                // .inputItems(wireGtQuadruple, ) todo mv超导
                .inputFluids(Argon.getFluid(3000))
                .outputItems(NEUTRON_ACCELERATOR[LuV].asStack())
                .EUt(VA[LuV]).duration(dur(15)).save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("neutron_accelerator_zpm")
                .inputItems(GTNNItems.INVERTER.asStack(2))
                .inputItems(GTMachines.HULL[ZPM].asStack())
                .inputItems(cableGtSingle, VanadiumGallium, 2)
                .inputItems(plate, NetherStar)
                .inputItems(plate, Polybenzimidazole, 8)
                .inputItems(plate, SamariumMagnetic, 4)
                .inputItems(plate, SamariumMagnetic, 4)
                .inputItems(GTItems.ELECTRIC_MOTOR_ZPM.asStack(2))
                .inputItems(wireGtQuadruple, UraniumTriplatinum, 4)
                .inputFluids(Xenon.getFluid(3000))
                .outputItems(NEUTRON_ACCELERATOR[ZPM].asStack())
                .EUt(VA[ZPM]).duration(dur(15)).save(provider);
        ASSEMBLY_LINE_RECIPES.recipeBuilder("neutron_accelerator_uv")
                .inputItems(GTNNItems.INVERTER.asStack(4))
                .inputItems(GTMachines.HULL[UV].asStack())
                .inputItems(cableGtSingle, NaquadahAlloy, 2)
                .inputItems(plate, NetherStar, 2)
                .inputItems(plate, Polybenzimidazole, 4)
                .inputItems(GTItems.VOLTAGE_COIL_ZPM.asStack(4))
                .inputItems(rodLong, NickelZincFerrite, 16)
                .inputItems(GTItems.VOLTAGE_COIL_ZPM.asStack(4))
                .inputItems(GTItems.ELECTRIC_MOTOR_UV.asStack(2))
                .inputItems(wireGtQuadruple, IndiumTinBariumTitaniumCuprate, 4)
                .inputFluids(Oganesson.getFluid(3000))
                .outputItems(NEUTRON_ACCELERATOR[UV].asStack())
                .EUt(VA[UV]).duration(dur(15)).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("neutron_sensor")
                .inputItems(GTBlocks.MACHINE_CASING_IV.asStack())
                .inputItems(GTItems.COVER_ACTIVITY_DETECTOR)
                .inputItems(GTItems.COVER_SCREEN)
                .inputItems(plate, VanadiumGallium, 4)
                .inputItems(CustomTags.EV_CIRCUITS)
                .inputItems(GTItems.SENSOR_HV.asStack(2))
                .circuitMeta(1)
                .outputItems(GTNNMachines.NEUTRON_SENSOR.asStack())
                .EUt(VA[EV]).duration(dur(15)).save(provider);
        GTNNRecipeTypes.PRECISION_ASSEMBLY_RECIPES.recipeBuilder("neutron_activator")
                .inputItems(GTNNItems.QuarkCore.asStack(2))
                .inputItems(GTItems.SENSOR_EV.asStack(2))
                .inputItems(GTNNItems.NeutronSource)
                .inputFluids(StainlessSteel.getFluid(576))
                .inputFluids(TungstenCarbide.getFluid(144))
                .outputItems(GTNNMachines.NEUTRON_ACTIVATOR.asStack())
                .EUt(VA[IV]).duration(dur(5)).save(provider);
        VanillaRecipeHelper.addShapedRecipe(provider, true, "neutron_source", GTNNItems.NeutronSource.asStack(),
                " A ", "ABA", " A ",
                'A', new UnificationEntry(plateDense, Steel),
                'B', GTNNItems.EnrichedUranium.asStack()
        );
        VanillaRecipeHelper.addShapedRecipe(provider, "neutron_accelerator_ulv", NEUTRON_ACCELERATOR[ULV].asStack(),
                "ABC", "DEF", "ABC",
                'A', new UnificationEntry(cableGtSingle, Lead),
                'B', new UnificationEntry(plate, Lead),
                'C', new UnificationEntry(rotor, Lead),
                'D', new UnificationEntry(plate, Wood),
                'E', GTMachines.HULL[ULV].asStack(),
                'F', GTNNItems.INVERTER.asStack()
        );
        VanillaRecipeHelper.addShapedRecipe(provider, "neutron_accelerator_lv", NEUTRON_ACCELERATOR[LV].asStack(),
                "ABC", "DEF", "ABC",
                'A', new UnificationEntry(cableGtSingle, Tin),
                'B', new UnificationEntry(plateDouble, Lead),
                'C', GTItems.ELECTRIC_MOTOR_LV.asStack(),
                'D', new UnificationEntry(plate, Rubber),
                'E', GTMachines.HULL[LV].asStack(),
                'F', GTNNItems.INVERTER.asStack()
        );
        VanillaRecipeHelper.addShapedRecipe(provider, true, "dehydrator_mv", GTNNMachines.DEHYDRATOR[MV].asStack(),
                "ABA", "CDC", "EFE",
                'A', new UnificationEntry(wireFine, RedAlloy),
                'B', CustomTags.MV_CIRCUITS,
                'C', new UnificationEntry(cableGtQuadruple, Copper),
                'D', GTMachines.HULL[MV].asStack(),
                'E', new UnificationEntry(gear, Steel),
                'F', GTItems.ROBOT_ARM_MV
        );
        VanillaRecipeHelper.addShapedRecipe(provider, true, "dehydrator_hv", GTNNMachines.DEHYDRATOR[HV].asStack(),
                "ABA", "CDC", "EFE",
                'A', new UnificationEntry(wireFine, Electrum),
                'B', CustomTags.HV_CIRCUITS,
                'C', new UnificationEntry(cableGtQuadruple, Silver),
                'D', GTMachines.HULL[HV].asStack(),
                'E', new UnificationEntry(gear, Potin),
                'F', GTItems.ROBOT_ARM_HV
        );
        VanillaRecipeHelper.addShapedRecipe(provider, true, "dehydrator_ev", GTNNMachines.DEHYDRATOR[EV].asStack(),
                "ABA", "CDC", "EFE",
                'A', GTItems.VOLTAGE_COIL_EV,
                'B', CustomTags.EV_CIRCUITS,
                'C', new UnificationEntry(cableGtQuadruple, Aluminium),
                'D', GTMachines.HULL[EV].asStack(),
                'E', new UnificationEntry(gear, TungstenSteel), //todo
                'F', GTItems.ROBOT_ARM_EV
        );
        VanillaRecipeHelper.addShapedRecipe(provider, true, "dehydrator_iv", GTNNMachines.DEHYDRATOR[IV].asStack(),
                "ABA", "CDC", "EFE",
                'A', GTItems.VOLTAGE_COIL_IV,
                'B', CustomTags.IV_CIRCUITS,
                'C', new UnificationEntry(cableGtQuadruple, Tungsten),
                'D', GTMachines.HULL[IV].asStack(),
                'E', new UnificationEntry(gear, Nichrome),
                'F', GTItems.ROBOT_ARM_IV
        );
        VanillaRecipeHelper.addShapedRecipe(provider, true, "dehydrator_luv", GTNNMachines.DEHYDRATOR[LuV].asStack(),
                "ABA", "CDC", "EFE",
                'A', GTItems.VOLTAGE_COIL_LuV,
                'B', CustomTags.LuV_CIRCUITS,
                'C', new UnificationEntry(cableGtQuadruple, Naquadah),
                'D', GTMachines.HULL[LuV].asStack(),
                'E', new UnificationEntry(gear, Ultimet), //todo
                'F', GTItems.ROBOT_ARM_LuV
        );
        VanillaRecipeHelper.addShapedRecipe(provider, true, "dehydrator_zpm", GTNNMachines.DEHYDRATOR[ZPM].asStack(),
                "ABA", "CDC", "EFE",
                'A', GTItems.VOLTAGE_COIL_ZPM,
                'B', CustomTags.ZPM_CIRCUITS,
                'C', new UnificationEntry(cableGtQuadruple, Osmium),
                'D', GTMachines.HULL[ZPM].asStack(),
                'E', new UnificationEntry(gear, Zeron100),
                'F', GTItems.ROBOT_ARM_ZPM
        );
        VanillaRecipeHelper.addShapedRecipe(provider, true, "rocket_engine_ev", GTNNMachines.Rocket_Engine[EV].asStack(),
                "ABA", "CDC", "EFE",
                'A', GTItems.ELECTRIC_PISTON_EV,
                'B', CustomTags.EV_CIRCUITS,
                'C', GTItems.ELECTRIC_MOTOR_EV,
                'D', GTMachines.HULL[EV].asStack(),
                'E', new UnificationEntry(gear, TungstenSteel), //todo
                'F', new UnificationEntry(cableGtDouble, Aluminium)
        );
        VanillaRecipeHelper.addShapedRecipe(provider, true, "rocket_engine_iv", GTNNMachines.Rocket_Engine[IV].asStack(),
                "ABA", "CDC", "EFE",
                'A', GTItems.ELECTRIC_PISTON_IV,
                'B', CustomTags.IV_CIRCUITS,
                'C', GTItems.ELECTRIC_MOTOR_IV,
                'D', GTMachines.HULL[IV].asStack(),
                'E', new UnificationEntry(gear, Titanium), //todo
                'F', new UnificationEntry(cableGtDouble, Platinum)
        );
        VanillaRecipeHelper.addShapedRecipe(provider, true, "rocket_engine_luv", GTNNMachines.Rocket_Engine[LuV].asStack(),
                "ABA", "CDC", "EFE",
                'A', GTItems.ELECTRIC_PISTON_LUV,
                'B', CustomTags.LuV_CIRCUITS,
                'C', GTItems.ELECTRIC_MOTOR_LuV,
                'D', GTMachines.HULL[LuV].asStack(),
                'E', new UnificationEntry(gear, Zeron100),
                'F', new UnificationEntry(cableGtDouble, Tungsten)
        );
        VanillaRecipeHelper.addShapedRecipe(provider, true, "naquadah_reactor_ev", GTNNMachines.NAQUADAH_REACTOR[EV].asStack(),
                "ABA", "CDC", "EBE",
                'A', new UnificationEntry(rod, Uranium235),
                'B', CustomTags.IV_CIRCUITS,
                'C', GTItems.FIELD_GENERATOR_EV,
                'D', GTMachines.HULL[EV].asStack(),
                'E', new UnificationEntry(cableGtQuadruple, Aluminium)
        );
        VanillaRecipeHelper.addShapedRecipe(provider, true, "naquadah_reactor_iv", GTNNMachines.NAQUADAH_REACTOR[IV].asStack(),
                "ABA", "CDC", "EBE",
                'A', new UnificationEntry(rod, Plutonium241),
                'B', CustomTags.LuV_CIRCUITS,
                'C', GTItems.FIELD_GENERATOR_IV,
                'D', GTMachines.HULL[IV].asStack(),
                'E', new UnificationEntry(cableGtQuadruple, Tungsten)
        );
        VanillaRecipeHelper.addShapedRecipe(provider, true, "naquadah_reactor_luv", GTNNMachines.NAQUADAH_REACTOR[LuV].asStack(),
                "ABA", "CDC", "EBE",
                'A', new UnificationEntry(rod, Europium),
                'B', CustomTags.ZPM_CIRCUITS,
                'C', GTItems.FIELD_GENERATOR_LuV,
                'D', GTMachines.HULL[LuV].asStack(),
                'E', new UnificationEntry(cableGtQuadruple, HSSG)
        );
        VanillaRecipeHelper.addShapedRecipe(provider, true, "naquadah_reactor_zpm", GTNNMachines.NAQUADAH_REACTOR[ZPM].asStack(),
                "ABA", "CDC", "EBE",
                'A', new UnificationEntry(rod, Americium),
                'B', CustomTags.UV_CIRCUITS,
                'C', GTItems.FIELD_GENERATOR_ZPM,
                'D', GTMachines.HULL[ZPM].asStack(),
                'E', new UnificationEntry(cableGtQuadruple, Naquadah)
        );
        VanillaRecipeHelper.addShapedRecipe(provider, true, "naquadah_reactor_uv", GTNNMachines.NAQUADAH_REACTOR[UV].asStack(),
                "ABA", "CDC", "EBE",
                'A', new UnificationEntry(rod, NaquadahAlloy),
                'B', CustomTags.UHV_CIRCUITS,
                'C', GTItems.FIELD_GENERATOR_UV,
                'D', GTMachines.HULL[UV].asStack(),
                'E', new UnificationEntry(cableGtQuadruple, EnrichedNaquadahTriniumEuropiumDuranide)
        );
    }
}
