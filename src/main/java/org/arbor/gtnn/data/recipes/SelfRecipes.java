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
import static org.arbor.gtnn.data.GTNNRecipes.dur;

public class SelfRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
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
        GTNNRecipeTypes.PRECISION_ASSEMBLY_RECIPES.recipeBuilder("neutron_activator")
                .inputItems(GTNNItems.QuarkCore.asStack(2))
                .inputItems(GTItems.SENSOR_EV.asStack(2))
                .inputItems(GTNNItems.NeutronSource)
                .inputFluids(StainlessSteel.getFluid(576))
                .inputFluids(TungstenCarbide.getFluid(144))
                .outputItems(GTNNMachines.NEUTRON_ACTIVATOR.asStack())
                .EUt(VA[IV]).duration(dur(5)).save(provider);
        MIXER_RECIPES.recipeBuilder("graphite_uranium_mixture")
                .inputItems(dust, Graphite, 3)
                .inputItems(dust, Uranium238)
                .outputItems(dust, GTNNMaterials.GraphiteUraniumMixture, 4)
                .EUt(VA[LV]).duration(dur(1.7)).save(provider);
        ASSEMBLER_RECIPES.recipeBuilder("encapsulated_uranium_ingot")
                .inputItems(dust, GTNNMaterials.GraphiteUraniumMixture, 4)
                .inputItems(foil, TungstenCarbide, 2)
                .outputItems(GTNNItems.EncapsulatedUraniumIngot)
                .EUt(VA[HV]).duration(dur(70)).save(provider);
        DefaultRecipes.addBOOMRecipes("enriched_uranium_nugget", GTNNItems.EncapsulatedUraniumIngot, GTNNItems.EnrichedUraniumNugget, VA[LV], dur(1), 1, provider);
        COMPRESSOR_RECIPES.recipeBuilder("enriched_uranium")
                .inputItems(GTNNItems.EnrichedUraniumNugget.asStack(9))
                .outputItems(GTNNItems.EnrichedUranium)
                .EUt(VA[HV]).duration(dur(30)).save(provider);
        VanillaRecipeHelper.addShapedRecipe(provider, true, "neutron_source", GTNNItems.NeutronSource.asStack(),
                " A ", "ABA", " A ",
                'A', new UnificationEntry(plateDense, Steel),
                'B', GTNNItems.EnrichedUranium.asStack()
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
    }
}
