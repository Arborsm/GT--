package dev.arbor.gtnn.data

import com.google.common.primitives.Ints
import com.gregtechceu.gtceu.GTCEu
import com.gregtechceu.gtceu.api.GTValues.*
import com.gregtechceu.gtceu.api.data.RotationState
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper
import com.gregtechceu.gtceu.api.data.tag.TagPrefix
import com.gregtechceu.gtceu.api.machine.MachineDefinition
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition
import com.gregtechceu.gtceu.api.machine.SimpleTieredMachine
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern
import com.gregtechceu.gtceu.api.pattern.Predicates.*
import com.gregtechceu.gtceu.api.pattern.util.RelativeDirection
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic
import com.gregtechceu.gtceu.common.data.GCYMBlocks
import com.gregtechceu.gtceu.common.data.GTBlocks
import com.gregtechceu.gtceu.common.data.GTMaterials
import com.gregtechceu.gtceu.common.data.GTRecipeModifiers
import com.gregtechceu.gtceu.common.data.machines.GTMachineUtils
import com.gregtechceu.gtceu.common.data.models.GTMachineModels
import com.gregtechceu.gtceu.utils.FormattingUtil
import dev.arbor.gtnn.GTNN
import dev.arbor.gtnn.GTNNRegistries.REGISTRATE
import dev.arbor.gtnn.api.pattern.NNBlockPattern
import dev.arbor.gtnn.api.pattern.NNFactoryPattern
import dev.arbor.gtnn.client.renderer.machine.GTPPMachineRender
import dev.arbor.gtnn.common.machine.GTNNGeneratorMachine
import dev.arbor.gtnn.common.machine.StoneBedrockOreMinerMachine
import dev.arbor.gtnn.common.machine.multiblock.ChemicalPlantMachine
import dev.arbor.gtnn.common.machine.multiblock.LargeNaquadahReactorMachine
import dev.arbor.gtnn.common.machine.multiblock.NNPartAbility
import dev.arbor.gtnn.common.machine.multiblock.NeutronActivatorMachine
import dev.arbor.gtnn.common.machine.multiblock.part.CatalystHatchPartMachine
import dev.arbor.gtnn.common.machine.multiblock.part.HighSpeedPipeBlock
import dev.arbor.gtnn.common.machine.multiblock.part.NeutronAcceleratorMachine
import dev.arbor.gtnn.common.machine.multiblock.part.NeutronSensorMachine
import dev.arbor.gtnn.data.block.GTNNCasingBlocks
import dev.arbor.gtnn.data.block.NNBlockMaps
import dev.arbor.gtnn.data.machine.MachineReg
import dev.arbor.gtnn.data.machine.ModifyMachines
import dev.arbor.gtnn.data.pattern.NNPredicates
import dev.arbor.gtnn.extension.StringExtension.gt
import dev.arbor.gtnn.extension.StringExtension.nn
import dev.arbor.gtnn.extension.StructureUtil
import net.minecraft.network.chat.Component

object GTNNMachines {
    private val ULV2UV: IntArray = tiersBetween(0, 8)
    private val MV2ZPM: IntArray = tiersBetween(2, 7)
    private val EV2UV: IntArray = tiersBetween(4, 8)

    //////////////////////////////////////
    //**********    Block     **********//
    //////////////////////////////////////
    val HIGH_SPEED_PIPE_BLOCK: MachineDefinition = REGISTRATE.machine("high_speed_pipe_block", ::HighSpeedPipeBlock)
        .simpleModel("block/machine/part/high_speed_pipe_block".nn())
        .rotationState(RotationState.Y_AXIS)
        .register()

    //////////////////////////////////////
    //**********     Part     **********//
    //////////////////////////////////////
    val NEUTRON_ACCELERATOR: Array<MachineDefinition?> = MachineReg.registerTieredMachines(
        "neutron_accelerator", ::NeutronAcceleratorMachine, { tier, builder ->
            builder.langValue(VNF[tier] + " Neutron Accelerator").rotationState(RotationState.ALL)
                .abilities(NNPartAbility.NEUTRON_ACCELERATOR)
                .tooltips(Component.translatable("gtnn.machine.neutron_accelerator.tooltip1"))
                .tooltips(Component.translatable("gtnn.machine.neutron_accelerator.tooltip2", V[tier]))
                .tooltips(Component.translatable("gtnn.machine.neutron_accelerator.tooltip3", V[tier] * 8 / 10))
                .tooltips(Component.translatable("gtnn.machine.neutron_accelerator.tooltip4"))
                .colorOverlayTieredHullModel("overlay_na")
                .register()
        }, ULV2UV
    )

    val NEUTRON_SENSOR: MachineDefinition = REGISTRATE
        .machine("neutron_sensor", ::NeutronSensorMachine)
        .langValue("Neutron Sensor")
        .tier(IV)
        .rotationState(RotationState.ALL)
        .abilities(NNPartAbility.NEUTRON_SENSOR)
        .colorOverlayTieredHullModel("overlay_neutron_sensor", null, "overlay_neutron_sensor_emissive")
        .tooltips(Component.translatable("block.gtnn.neutron_sensor.tooltip1"))
        .tooltips(Component.translatable("block.gtnn.neutron_sensor.tooltip2")).register()

    val CATALYST_HATCH: MachineDefinition = REGISTRATE
        .machine("catalyst_hatch", ::CatalystHatchPartMachine)
        .langValue("Catalyst Hatch")
        .tier(IV)
        .rotationState(RotationState.ALL)
        .abilities(NNPartAbility.CATALYST)
        .colorOverlayTieredHullModel("overlay_catalyst_in", null, "overlay_catalyst_hatch")
        .tooltips()
        .register()

    //////////////////////////////////////
    //**********   Machine    **********//
    //////////////////////////////////////

    val DEHYDRATOR: Array<MachineDefinition?> = MachineReg.registerSimpleMachines(
        "dehydrator", GTNNRecipeTypes.DEHYDRATOR_RECIPES, GTMachineUtils.defaultTankSizeFunction, MV2ZPM
    )


    val NAQUADAH_REACTOR: Array<MachineDefinition?> = MachineReg.registerGTNNGeneratorMachines(
        "naquadah_reactor",
        GTNNRecipeTypes.NAQUADAH_REACTOR_RECIPES,
        GTNNGeneratorMachine::nonParallel,
        GTMachineUtils.genericGeneratorTankSizeFunction,
        EV2UV
    )


    val Rocket_Engine: Array<MachineDefinition?> = MachineReg.registerGTNNGeneratorMachines(
        "rocket_engine",
        GTNNRecipeTypes.ROCKET_ENGINE_RECIPES,
        GTNNGeneratorMachine::parallel,
        GTMachineUtils.genericGeneratorTankSizeFunction,
        intArrayOf(EV, IV, LuV)
    )

    val STONE_BEDROCK_ORE_MACHINE: MachineDefinition =
        REGISTRATE.machine("homemade_bedrock_ore_machine", ::StoneBedrockOreMinerMachine)
            .langValue("Homemade Bedrock Ore Machine").rotationState(RotationState.ALL)
            .recipeType(GTNNRecipeTypes.STONE_BEDROCK_ORE_MACHINE_RECIPES).editableUI(
                SimpleTieredMachine.EDITABLE_UI_CREATOR.apply(
                    "homemade_bedrock_ore_machine".nn(), GTNNRecipeTypes.STONE_BEDROCK_ORE_MACHINE_RECIPES
                )
            ).workableTieredHullModel(GTCEu.id("block/machines/miner")).tooltips(
                Component.translatable("gtceu.machine.bedrock_ore_miner.description"), Component.translatable(
                    "gtceu.machine.bedrock_ore_miner.depletion", FormattingUtil.formatNumbers(100.0)
                )
            ).register()

    val CHEMICAL_PLANT: MultiblockMachineDefinition = REGISTRATE.multiblock(
        "exxonmobil_chemical_plant"
    ) { ChemicalPlantMachine(it) }.rotationState(RotationState.NON_Y_AXIS)
        .tooltips(Component.translatable("gtnn.multiblock.chemical_plant.tooltip1"))
        .tooltips(Component.translatable("gtnn.multiblock.chemical_plant.tooltip2"))
        .tooltips(Component.translatable("gtnn.multiblock.chemical_plant.tooltip3"))
        .tooltips(Component.translatable("gtnn.multiblock.chemical_plant.tooltip4"))
        .recipeTypes(GTNNRecipeTypes.CHEMICAL_PLANT_RECIPES)
        .recipeModifiers(GTNNRecipeModifiers.GTPP_MODIFIER)
        .appearanceBlock(GTBlocks.CASING_BRONZE_BRICKS)
        .pattern { definition -> NNFactoryPattern.start()
                .aisle("VVVVVVV", "A#####A", "A#####A", "A#####A", "A#####A", "A#####A", "AAAAAAA")
                .aisle("VBBBBBV", "#BBBBB#", "#######", "#######", "#######", "#BBBBB#", "AAAAAAA")
                .aisle("VBBBBBV", "#BCCCB#", "##DDD##", "##CCC##", "##DDD##", "#BCCCB#", "AAAAAAA")
                .aisle("VBBBBBV", "#BCCCB#", "##DDD##", "##CCC##", "##DDD##", "#BCCCB#", "AAAAAAA")
                .aisle("VBBBBBV", "#BCCCB#", "##DDD##", "##CCC##", "##DDD##", "#BCCCB#", "AAAAAAA")
                .aisle("VBBBBBV", "#BBBBB#", "#######", "#######", "#######", "#BBBBB#", "AAAAAAA")
                .aisle("VVVSVVV", "A#####A", "A#####A", "A#####A", "A#####A", "A#####A", "AAAAAAA")
                .where("S", controller(blocks(definition.block)))
                .where(
                    "V", NNPredicates.plantCasings
                        .or(abilities(PartAbility.MAINTENANCE).setExactLimit(1))
                        .or(abilities(PartAbility.EXPORT_FLUIDS).setMinGlobalLimited(1))
                        .or(abilities(PartAbility.EXPORT_ITEMS).setMinGlobalLimited(1))
                        .or(abilities(PartAbility.IMPORT_ITEMS).setMinGlobalLimited(1))
                        .or(abilities(PartAbility.IMPORT_FLUIDS).setMinGlobalLimited(1))
                        .or(abilities(NNPartAbility.CATALYST).setMaxGlobalLimited(2))
                        .or(abilities(PartAbility.INPUT_ENERGY)
                            .setMinGlobalLimited(1).setMaxGlobalLimited(2)))
                .where("A", NNPredicates.plantCasings)
                .where("D", NNPredicates.pipeBlock)
                .where("C", NNPredicates.coilBlock)
                .where("B", NNPredicates.machineCasing)
                .where("#", any())
                .build()
        }.shapeInfos { definition ->
            val maxSize = Ints.max(
                NNBlockMaps.ALL_CP_CASINGS.size,
                NNBlockMaps.ALL_CP_TUBES.size,
                NNBlockMaps.ALL_MACHINE_CASINGS.size,
                NNBlockMaps.ALL_COIL_BLOCKS.size
            )
            return@shapeInfos StructureUtil.getMatchingShapes(
                definition.patternFactory.get() as NNBlockPattern,
                maxSize
            )
        }.partSorter(Comparator.comparingInt { a -> a.self().pos.y })
        .model(GTMachineModels.createWorkableCasingMachineModel(
            GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"),
            GTNN.id("block/multiblock/chemical_plant")).andThen {
                it.addDynamicRenderer { GTPPMachineRender(
                    GTBlocks.CASING_BRONZE_BRICKS, GTPPMachineRender.ModelTypes.ChemicalPlantMachine) }
        })
        .register()

    val NEUTRON_ACTIVATOR: MultiblockMachineDefinition =
        REGISTRATE.multiblock("neutron_activator", ::NeutronActivatorMachine)
            .rotationState(RotationState.NON_Y_AXIS)
            .tooltips(Component.translatable("gtnn.multiblock.neutron_activator.tooltip1"))
            .tooltips(Component.translatable("gtnn.multiblock.neutron_activator.tooltip2"))
            .tooltips(Component.translatable("gtnn.multiblock.neutron_activator.tooltip3"))
            .tooltips(Component.translatable("gtnn.multiblock.neutron_activator.tooltip4"))
            .tooltips(Component.translatable("gtnn.multiblock.neutron_activator.tooltip5"))
            .recipeTypes(GTNNRecipeTypes.NEUTRON_ACTIVATOR_RECIPES).appearanceBlock(GTBlocks.CASING_STAINLESS_CLEAN)
            .pattern { definition ->
                FactoryBlockPattern.start(RelativeDirection.RIGHT, RelativeDirection.BACK, RelativeDirection.UP)
                    .aisle("AASAA", "ABBBA", "ABBBA", "ABBBA", "AAAAA")
                    .aisle("C###C", "#DDD#", "#DED#", "#DDD#", "C###C").setRepeatable(4, 34)
                    .aisle("VVVVV", "VBBBV", "VBBBV", "VBBBV", "VVVVV").where("S", controller(blocks(definition.get())))
                    .where(
                        "V",
                        blocks(GTBlocks.CASING_STAINLESS_CLEAN.get()).or(abilities(PartAbility.IMPORT_FLUIDS))
                            .or(abilities(PartAbility.IMPORT_ITEMS))
                    ).where(
                        "A",
                        blocks(GTBlocks.CASING_STAINLESS_CLEAN.get()).or(abilities(PartAbility.EXPORT_FLUIDS))
                            .or(abilities(PartAbility.EXPORT_ITEMS)).or(abilities(NNPartAbility.NEUTRON_ACCELERATOR))
                            .or(abilities(NNPartAbility.NEUTRON_SENSOR)).or(autoAbilities(true, false, false))
                    ).where("B", blocks(GTNNCasingBlocks.PROCESS_MACHINE_CASING.get()))
                    .where("C", blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, GTMaterials.Steel)))
                    .where("D", blocks(GTBlocks.CASING_LAMINATED_GLASS.get()))
                    .where("E", blocks(HIGH_SPEED_PIPE_BLOCK.get())).where("#", air()).build()
            }.workableCasingModel(
                GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel"),
                GTNN.id("block/multiblock/neutron_activator")
            ).register()

    val LARGE_DEHYDRATOR: MultiblockMachineDefinition =
        REGISTRATE.multiblock("large_dehydrator") { WorkableElectricMultiblockMachine(it) }
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeTypes(GTNNRecipeTypes.DEHYDRATOR_RECIPES)
            .recipeModifiers(
                GTRecipeModifiers.DEFAULT_ENVIRONMENT_REQUIREMENT,
                GTRecipeModifiers.PARALLEL_HATCH,
                GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK)
            )
            .appearanceBlock(GCYMBlocks.CASING_HIGH_TEMPERATURE_SMELTING)
            .pattern { definition ->
                FactoryBlockPattern.start()
                    .aisle("XXX", "CCC", "CCC", "CCC", "XXX")
                    .aisle("XXX", "C#C", "C#C", "C#C", "XXX")
                    .aisle("XSX", "CCC", "CCC", "CCC", "XXX")
                    .where('S', controller(blocks(definition.block)))
                    .where(
                        'X', blocks(GCYMBlocks.CASING_HIGH_TEMPERATURE_SMELTING.get()).setMinGlobalLimited(9)
                            .or(autoAbilities(*definition.recipeTypes))
                            .or(autoAbilities(true, false, true))
                    )
                    .where('C', blocks(GTBlocks.COIL_NAQUADAH.get()))
                    .where('#', air())
                    .build()
            }.workableCasingModel(
                "block/casings/gcym/high_temperature_smelting_casing".gt(),
                "block/multiblock/gcym/large_assembler".gt()
            )
            .register()


    val LargeNaquadahReactor: MultiblockMachineDefinition =
        REGISTRATE.multiblock("large_naquadah_reactor") { LargeNaquadahReactorMachine(it) }
            .rotationState(RotationState.NON_Y_AXIS)
            .tooltips(Component.translatable("gtnn.multiblock.large_naquadah_reactor.tooltip1"))
            .tooltips(Component.translatable("gtnn.multiblock.large_naquadah_reactor.tooltip2"))
            .tooltips(Component.translatable("gtnn.multiblock.large_naquadah_reactor.tooltip3"))
            .tooltips(Component.translatable("gtnn.multiblock.large_naquadah_reactor.tooltip4"))
            .tooltips(Component.translatable("gtnn.multiblock.large_naquadah_reactor.tooltip5"))
            .tooltips(Component.translatable("gtnn.multiblock.large_naquadah_reactor.tooltip6"))
            .tooltips(Component.translatable("gtnn.multiblock.large_naquadah_reactor.tooltip7"))
            .tooltips(Component.translatable("gtnn.multiblock.large_naquadah_reactor.tooltip8"))
            .tooltips(Component.translatable("gtnn.multiblock.large_naquadah_reactor.tooltip9"))
            .recipeTypes(GTNNRecipeTypes.LARGE_NAQUADAH_REACTOR_RECIPES)
            .recipeModifier(LargeNaquadahReactorMachine::modifyRecipe)
            .appearanceBlock(GTNNCasingBlocks.RADIATION_PROOF_MACHINE_CASING).pattern { definition ->
                FactoryBlockPattern.start()
                    .aisle("AAAAAAA", "VBBBBBV", "VVVVVVV", "B#####B", "B#####B", "B#####B", "B#####B", "VVVVVVV")
                    .aisle("AAAAAAA", "B#####B", "V#####V", "#######", "#######", "#######", "#######", "VVVVVVV")
                    .aisle("AAAAAAA", "B#CCC#B", "V#CCC#V", "##CCC##", "##CCC##", "##CCC##", "##CCC##", "VVVVVVV")
                    .aisle("AAAAAAA", "B#CCC#B", "V#CDC#V", "##CDC##", "##CDC##", "##CDC##", "##CDC##", "VVVVVVV")
                    .aisle("AAAAAAA", "B#CCC#B", "V#CCC#V", "##CCC##", "##CCC##", "##CCC##", "##CCC##", "VVVVVVV")
                    .aisle("AAAAAAA", "B#####B", "V#####V", "#######", "#######", "#######", "#######", "VVVVVVV")
                    .aisle("AAASAAA", "VBBBBBV", "VVVVVVV", "B#####B", "B#####B", "B#####B", "B#####B", "VVVVVVV")
                    .where("S", controller(blocks(definition.get())))
                    .where("V", blocks(GTNNCasingBlocks.RADIATION_PROOF_MACHINE_CASING.get())).where(
                        "A", blocks(GTNNCasingBlocks.RADIATION_PROOF_MACHINE_CASING.get()).or(
                            autoAbilities(
                                true, false, false
                            )
                        ).or(abilities(PartAbility.OUTPUT_ENERGY, PartAbility.OUTPUT_LASER).setMinGlobalLimited(1, 1))
                            .or(abilities(PartAbility.IMPORT_FLUIDS).setPreviewCount(1))
                            .or(abilities(PartAbility.EXPORT_FLUIDS).setPreviewCount(1))
                    ).where("B", blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, GTNNMaterials.RadiationProtection)))
                    .where("C", blocks(GTNNCasingBlocks.MAR_CASING.get()))
                    .where("D", blocks(GTBlocks.CASING_TUNGSTENSTEEL_PIPE.get())).where("#", air()).build()
            }.workableCasingModel(
                GTNN.id("block/casings/solid/radiation_proof_machine_casing"),
                GTNN.id("block/multiblock/large_naquadah_reactor")
            ).additionalDisplay { controller, components ->
                if (controller is LargeNaquadahReactorMachine && controller.isFormed()) run {
                    components.add(
                        Component.translatable(
                            "gtnn.multiblock.large_naquadah_reactor.power", controller.getFinalPowerRate()
                        )
                    )
                }
            }.register()

    fun init() {
        REGISTRATE.creativeModeTab { GTNNCreativeModeTabs.MAIN_TAB }
        GTPPMachines.init()
        ModifyMachines.init()
    }
}