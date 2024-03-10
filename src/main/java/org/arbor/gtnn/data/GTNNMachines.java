package org.arbor.gtnn.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.block.ICoilType;
import com.gregtechceu.gtceu.api.capability.recipe.FluidRecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.*;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.MultiblockShapeInfo;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import com.gregtechceu.gtceu.client.renderer.machine.SimpleGeneratorMachineRenderer;
import com.gregtechceu.gtceu.common.data.*;
import it.unimi.dsi.fastutil.ints.Int2LongFunction;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.arbor.gtnn.GTNN;
import org.arbor.gtnn.api.machine.GTNNGeneratorMachine;
import org.arbor.gtnn.api.machine.multiblock.APartAbility;
import org.arbor.gtnn.api.machine.multiblock.ChemicalPlantMachine;
import org.arbor.gtnn.api.machine.multiblock.LargeNaquadahReactorMachine;
import org.arbor.gtnn.api.machine.multiblock.NeutronActivatorMachine;
import org.arbor.gtnn.api.machine.multiblock.part.HighSpeedPipeBlock;
import org.arbor.gtnn.api.machine.multiblock.part.NeutronAcceleratorMachine;
import org.arbor.gtnn.api.machine.multiblock.part.NeutronSensorMachine;
import org.arbor.gtnn.api.pattern.APredicates;
import org.arbor.gtnn.block.BlockTier;
import org.arbor.gtnn.block.MachineCasingBlock;
import org.arbor.gtnn.block.PipeBlock;
import org.arbor.gtnn.block.PlantCasingBlock;
import org.arbor.gtnn.client.renderer.machine.BlockMachineRenderer;
import org.arbor.gtnn.client.renderer.machine.GTPPMachineRenderer;

import java.util.*;
import java.util.function.BiFunction;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.abilities;
import static com.gregtechceu.gtceu.api.pattern.Predicates.autoAbilities;
import static com.gregtechceu.gtceu.api.pattern.util.RelativeDirection.*;
import static com.gregtechceu.gtceu.utils.FormattingUtil.toEnglishName;
import static org.arbor.gtnn.GTNNRegistries.REGISTRATE;

@SuppressWarnings("unused")
public class GTNNMachines {
    public static final int[] ULV2UV = GTValues.tiersBetween(0, 8);
    public static final int[] MV2ZPM = GTValues.tiersBetween(2, 7);
    public static final int[] EV2UV = GTValues.tiersBetween(4, 8);
    static {
        REGISTRATE.creativeModeTab(() -> GTNNCreativeModeTabs.MAIN_TAB);
    }
    //////////////////////////////////////
    //**********     Part     **********//
    //////////////////////////////////////

    public static final MachineDefinition[] NEUTRON_ACCELERATOR = registerTieredMachines("neutron_accelerator",
            NeutronAcceleratorMachine::new,
            (tier, builder) -> builder
                    .langValue(VNF[tier] + " 中子加速器")
                    .rotationState(RotationState.ALL)
                    .abilities(APartAbility.NEUTRON_ACCELERATOR)
                    .tooltips(Component.translatable("gtnn.machine.neutron_accelerator.tooltip1"))
                    .tooltips(Component.translatable("gtnn.machine.neutron_accelerator.tooltip2", V[tier]))
                    .tooltips(Component.translatable("gtnn.machine.neutron_accelerator.tooltip3", V[tier] * 8 / 10))
                    .tooltips(Component.translatable("gtnn.machine.neutron_accelerator.tooltip4"))
                    .overlayTieredHullRenderer("neutron_accelerator")
                    .compassNode("neutron_accelerator")
                    .register(),
            ULV2UV);

    public static final MachineDefinition HIGH_SPEED_PIPE_BLOCK = REGISTRATE.machine("high_speed_pipe_block", HighSpeedPipeBlock::new)
            .renderer(() -> new BlockMachineRenderer(GTNN.id("block/machine/part/high_speed_pipe_block")))
            .rotationState(RotationState.Y_AXIS)
            .register();

    public final static MachineDefinition NEUTRON_SENSOR = REGISTRATE.machine("neutron_sensor", NeutronSensorMachine::new)
            .langValue("Neutron Sensor")
            .tier(GTValues.IV)
            .rotationState(RotationState.ALL)
            .abilities(APartAbility.NEUTRON_SENSOR)
            .overlayTieredHullRenderer("neutron_sensor")
            .tooltips(Component.translatable("block.gtnn.neutron_sensor.tooltip1"))
            .tooltips(Component.translatable("block.gtnn.neutron_sensor.tooltip2"))
            .register();

    //////////////////////////////////////
    //**********   Machine    **********//
    //////////////////////////////////////
    public static final MachineDefinition[] DEHYDRATOR =
            registerSimpleMachines("dehydrator", GTNNRecipeTypes.DEHYDRATOR_RECIPES, GTMachines.defaultTankSizeFunction, MV2ZPM);

    public static final MachineDefinition[] NAQUADAH_REACTOR = registerGTNNGeneratorMachines(
            "naquadah_reactor", GTNNRecipeTypes.NAQUADAH_REACTOR_RECIPES, GTNNGeneratorMachine::nonParallel,
            GTMachines.genericGeneratorTankSizeFunction, EV2UV);

    public static final MachineDefinition[] Rocket_Engine = registerGTNNGeneratorMachines(
            "rocket_engine", GTNNRecipeTypes.ROCKET_ENGINE_RECIPES, GTNNGeneratorMachine::parallel,
            GTMachines.genericGeneratorTankSizeFunction, EV, IV, LuV);

    public static final MultiblockMachineDefinition CHEMICAL_PLANT = REGISTRATE.multiblock("chemical_plant", ChemicalPlantMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .tooltips(Component.translatable("gtnn.multiblock.chemical_plant.tooltip1"))
            .tooltips(Component.translatable("gtnn.multiblock.chemical_plant.tooltip2"))
            .tooltips(Component.translatable("gtnn.multiblock.chemical_plant.tooltip3"))
            .tooltips(Component.translatable("gtnn.multiblock.chemical_plant.tooltip4"))
            .recipeTypes(GTNNRecipeTypes.CHEMICAL_PLANT_RECIPES)
            .recipeModifier(ChemicalPlantMachine::chemicalPlantRecipe)
            .appearanceBlock(GTBlocks.CASING_BRONZE_BRICKS)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("VVVVVVV", "A#####A", "A#####A", "A#####A", "A#####A", "A#####A", "AAAAAAA")
                    .aisle("VBBBBBV", "#BBBBB#", "#######", "#######", "#######", "#BBBBB#", "AAAAAAA")
                    .aisle("VBBBBBV", "#BCCCB#", "##DDD##", "##CCC##", "##DDD##", "#BCCCB#", "AAAAAAA")
                    .aisle("VBBBBBV", "#BCCCB#", "##DDD##", "##CCC##", "##DDD##", "#BCCCB#", "AAAAAAA")
                    .aisle("VBBBBBV", "#BCCCB#", "##DDD##", "##CCC##", "##DDD##", "#BCCCB#", "AAAAAAA")
                    .aisle("VBBBBBV", "#BBBBB#", "#######", "#######", "#######", "#BBBBB#", "AAAAAAA")
                    .aisle("AVVSVVA", "A#####A", "A#####A", "A#####A", "A#####A", "A#####A", "AAAAAAA")
                    .where("S", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("V", APredicates.plantCasings()
                            .or(autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, false))
                            .or(abilities(PartAbility.INPUT_ENERGY))
                            .or(abilities(PartAbility.IMPORT_ITEMS))
                            .or(abilities(PartAbility.EXPORT_ITEMS))
                            .or(abilities(PartAbility.IMPORT_FLUIDS))
                            .or(abilities(PartAbility.EXPORT_FLUIDS))
                    )
                    .where("A", APredicates.plantCasings())
                    .where("D", APredicates.pipeBlock())
                    .where("C", Predicates.heatingCoils())
                    .where("B", APredicates.machineCasing())
                    .where("#", Predicates.air())
                    .build())
            .shapeInfos(definition -> {
                List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
                var builder = MultiblockShapeInfo.builder()
                        .aisle("AAOSJAA", "A#####A", "A#####A", "A#####A", "A#####A", "A#####A", "AAAAAAA")
                        .aisle("MBBBBBN", "#BBBBB#", "#######", "#######", "#######", "#BBBBB#", "AAAAAAA")
                        .aisle("KBBBBBL", "#BCCCB#", "##DDD##", "##CCC##", "##DDD##", "#BCCCB#", "AAAAAAA")
                        .aisle("ABBBBBA", "#BCCCB#", "##DDD##", "##CCC##", "##DDD##", "#BCCCB#", "AAAAAAA")
                        .aisle("ABBBBBA", "#BCCCB#", "##DDD##", "##CCC##", "##DDD##", "#BCCCB#", "AAAAAAA")
                        .aisle("ABBBBBA", "#BBBBB#", "#######", "#######", "#######", "#BBBBB#", "AAAAAAA")
                        .aisle("AAAAAAA", "A#####A", "A#####A", "A#####A", "A#####A", "A#####A", "AAAAAAA")
                        .where('S', definition, Direction.NORTH)
                        .where('#', Blocks.AIR.defaultBlockState())
                        .where('J', GTMachines.MAINTENANCE_HATCH, Direction.NORTH);
                Map<Integer, BlockState> shapeBlock = new HashMap<>();
                for (PlantCasingBlock casing : PlantCasingBlock.values()) {
                    shapeBlock.put(casing.getTier() + 10, casing.getPlantCasing(casing.getTier()).getDefaultState());
                }
                for (MachineCasingBlock.MachineCasing machineCasing : MachineCasingBlock.MachineCasing.values()) {
                    shapeBlock.put(machineCasing.getTier() + 20, machineCasing.getMachineCasing(machineCasing.getTier()).getDefaultState());
                }
                for (ICoilType coil : GTBlocks.ALL_COILS.keySet()) {
                    shapeBlock.put(coil.getTier() + 30, GTBlocks.ALL_COILS.get(coil).get().defaultBlockState());
                }
                for (PipeBlock.Pipe pipe : PipeBlock.Pipe.values()) {
                    shapeBlock.put(pipe.getTier() + 40, pipe.getPipe(pipe.getTier()).getDefaultState());
                }
                for (BlockTier tier : BlockTier.values()) {
                    builder.where('A', shapeBlock.get(tier.tier() + 10));
                    builder.where('B', shapeBlock.get(tier.tier() + 20));
                    builder.where('C', shapeBlock.get(tier.tier() + 30));
                    builder.where('D', shapeBlock.get(tier.tier() + 40));
                    builder.where('K', GTMachines.ITEM_IMPORT_BUS[tier.tier()], Direction.WEST);
                    builder.where('L', GTMachines.ITEM_EXPORT_BUS[tier.tier()], Direction.EAST);
                    builder.where('M', GTMachines.FLUID_IMPORT_HATCH[tier.tier()], Direction.WEST);
                    builder.where('N', GTMachines.FLUID_EXPORT_HATCH[tier.tier()], Direction.EAST);
                    builder.where('O', GTMachines.ENERGY_INPUT_HATCH[tier.tier()], Direction.NORTH);
                    shapeInfo.add(builder.shallowCopy().build());
                }
                return shapeInfo;
            })
            .renderer(() -> new GTPPMachineRenderer(GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"),
                    GTNN.id("block/multiblock/chemical_plant"), false))
            .additionalDisplay((controller, components) -> {
                if (controller instanceof ChemicalPlantMachine chemicalPlantMachine && controller.isFormed()) {
                    components.add(Component.translatable("gtnn.multiblock.chemical_plant.heating_coil", chemicalPlantMachine.getCoilTier() * 50));
                    components.add(Component.translatable("gtnn.multiblock.chemical_plant.parallel_level", chemicalPlantMachine.getPipeTier() * 2));
                    components.add(Component.translatable("gtnn.multiblock.chemical_plant.tier", VNF[chemicalPlantMachine.getPlantCasingTier()]));
                }
            })
            .register();

    public static final MultiblockMachineDefinition NEUTRON_ACTIVATOR = REGISTRATE.multiblock("neutron_activator", NeutronActivatorMachine::new)
             .rotationState(RotationState.NON_Y_AXIS)
             .tooltips(Component.translatable("gtnn.multiblock.neutron_activator.tooltip1"))
             .tooltips(Component.translatable("gtnn.multiblock.neutron_activator.tooltip2"))
             .tooltips(Component.translatable("gtnn.multiblock.neutron_activator.tooltip3"))
             .tooltips(Component.translatable("gtnn.multiblock.neutron_activator.tooltip4"))
             .tooltips(Component.translatable("gtnn.multiblock.neutron_activator.tooltip5"))
            .recipeTypes(GTNNRecipeTypes.NEUTRON_ACTIVATOR_RECIPES)
             .appearanceBlock(GTBlocks.CASING_STAINLESS_CLEAN)
             .pattern(definition -> FactoryBlockPattern.start(RIGHT, BACK, UP)
                     .aisle("AASAA", "ABBBA", "ABBBA", "ABBBA", "AAAAA")
                     .aisle("C###C", "#DDD#", "#DED#", "#DDD#", "C###C").setRepeatable(4, 34)
                     .aisle("VVVVV", "VBBBV", "VBBBV", "VBBBV", "VVVVV")
                     .where("S", Predicates.controller(Predicates.blocks(definition.get())))
                     .where("V", Predicates.blocks(GTBlocks.CASING_STAINLESS_CLEAN.get())
                             .or(abilities(PartAbility.IMPORT_FLUIDS))
                             .or(abilities(PartAbility.IMPORT_ITEMS)))
                     .where("A", Predicates.blocks(GTBlocks.CASING_STAINLESS_CLEAN.get())
                             .or(abilities(PartAbility.EXPORT_FLUIDS))
                             .or(abilities(PartAbility.EXPORT_ITEMS))
                             .or(abilities(APartAbility.NEUTRON_ACCELERATOR))
                             .or(abilities(APartAbility.NEUTRON_SENSOR))
                             .or(autoAbilities(true, false, false)))
                     .where("B", Predicates.blocks(GTNNCasingBlocks.PROCESS_MACHINE_CASING.get()))
                     .where("C", Predicates.blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, GTMaterials.Steel)))
                     .where("D", Predicates.blocks(GTBlocks.CASING_LAMINATED_GLASS.get()))
                     .where("E", Predicates.blocks(HIGH_SPEED_PIPE_BLOCK.get()))
                     .where("#", Predicates.air())
                     .build())
             .workableCasingRenderer(
                     GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel"),
                     GTNN.id("block/multiblock/neutron_activator"), false)
             .register();

    public static final MultiblockMachineDefinition LargeNaquadahReactor = REGISTRATE.multiblock("large_naquadah_reactor", LargeNaquadahReactorMachine::new)
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
            .appearanceBlock(GTNNCasingBlocks.RADIATION_PROOF_MACHINE_CASING)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("AAAAAAA", "VBBBBBV", "VVVVVVV", "B#####B", "B#####B", "B#####B", "B#####B", "VVVVVVV")
                    .aisle("AAAAAAA", "B#####B", "V#####V", "#######", "#######", "#######", "#######", "VVVVVVV")
                    .aisle("AAAAAAA", "B#CCC#B", "V#CCC#V", "##CCC##", "##CCC##", "##CCC##", "##CCC##", "VVVVVVV")
                    .aisle("AAAAAAA", "B#CCC#B", "V#CDC#V", "##CDC##", "##CDC##", "##CDC##", "##CDC##", "VVVVVVV")
                    .aisle("AAAAAAA", "B#CCC#B", "V#CCC#V", "##CCC##", "##CCC##", "##CCC##", "##CCC##", "VVVVVVV")
                    .aisle("AAAAAAA", "B#####B", "V#####V", "#######", "#######", "#######", "#######", "VVVVVVV")
                    .aisle("AAASAAA", "VBBBBBV", "VVVVVVV", "B#####B", "B#####B", "B#####B", "B#####B", "VVVVVVV")
                    .where("S", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("V", Predicates.blocks(GTNNCasingBlocks.RADIATION_PROOF_MACHINE_CASING.get()))
                    .where("A", Predicates.blocks(GTNNCasingBlocks.RADIATION_PROOF_MACHINE_CASING.get())
                            .or(autoAbilities(true, false, false))
                            .or(abilities(PartAbility.OUTPUT_ENERGY).setMinGlobalLimited(1, 1))
                            .or(abilities(PartAbility.IMPORT_FLUIDS).setPreviewCount(1))
                            .or(abilities(PartAbility.EXPORT_FLUIDS).setPreviewCount(1)))
                    .where("B", Predicates.blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, GTNNMaterials.RadiationProtection)))
                    .where("C", Predicates.blocks(GTNNCasingBlocks.MAR_CASING.get()))
                    .where("D", Predicates.blocks(GTBlocks.CASING_TUNGSTENSTEEL_PIPE.get()))
                    .where("#", Predicates.air())
                    .build())
            .workableCasingRenderer(
                    GTNN.id("block/casings/solid/radiation_proof_machine_casing"),
                    GTNN.id("block/multiblock/large_naquadah_reactor"), false)
            .additionalDisplay((controller, components) -> {
                if (controller instanceof LargeNaquadahReactorMachine largeNaquadahReactorMachine && controller.isFormed()) {
                    components.add(Component.translatable("gtnn.multiblock.large_naquadah_reactor.power", largeNaquadahReactorMachine.getFinalPowerRate()));
                }
            })
            .register();

    public static MachineDefinition[] registerTieredMachines(String name,
                                                             BiFunction<IMachineBlockEntity, Integer, MetaMachine> factory,
                                                             BiFunction<Integer, MachineBuilder<MachineDefinition>, MachineDefinition> builder,
                                                             int... tiers) {
        MachineDefinition[] definitions = new MachineDefinition[GTValues.TIER_COUNT];
        for (int tier : tiers) {
            var register = REGISTRATE.machine(GTValues.VN[tier].toLowerCase(Locale.ROOT) + "_" + name, holder -> factory.apply(holder, tier))
                    .tier(tier);
            definitions[tier] = builder.apply(tier, register);
        }
        return definitions;
    }

    public static MachineDefinition[] registerSimpleMachines(String name,
                                                             GTRecipeType recipeType,
                                                             Int2LongFunction tankScalingFunction,
                                                             int... tiers) {
        return registerTieredMachines(name, (holder, tier) -> new SimpleTieredMachine(holder, tier, tankScalingFunction), (tier, builder) -> builder
                .langValue("%s %s %s".formatted(VLVH[tier], toEnglishName(name), VLVT[tier]))
                .editableUI(SimpleTieredMachine.EDITABLE_UI_CREATOR.apply(GTCEu.id(name), recipeType))
                .rotationState(RotationState.NON_Y_AXIS)
                .recipeType(recipeType)
                .recipeModifier(GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK))
                .workableTieredHullRenderer(GTNN.id("block/machines/" + name))
                .tooltips(GTMachines.explosion())
                .tooltips(GTMachines.workableTiered(tier, GTValues.V[tier], GTValues.V[tier] * 64, recipeType, tankScalingFunction.apply(tier), true))
                .compassNode(name)
                .register(), tiers);
    }

    public static MachineDefinition[] registerGTNNGeneratorMachines(String name,
                                                                    GTRecipeType recipeType,
                                                                    BiFunction<MetaMachine, GTRecipe, GTRecipe> recipeModifier,
                                                                    Int2LongFunction tankScalingFunction,
                                                                    int... tiers) {
        return registerTieredMachines(name, (holder, tier) -> new GTNNGeneratorMachine(holder, tier, name, tankScalingFunction), (tier, builder) -> builder
                .langValue("%s %s Generator %s".formatted(VLVH[tier], toEnglishName(name), LVT[tier - 3]))
                .editableUI(SimpleGeneratorMachine.EDITABLE_UI_CREATOR.apply(GTNN.id(name), recipeType))
                .rotationState(RotationState.ALL)
                .recipeType(recipeType)
                .recipeModifier(recipeModifier, true)
                .addOutputLimit(ItemRecipeCapability.CAP, 0)
                .addOutputLimit(FluidRecipeCapability.CAP, 0)
                .renderer(() -> new SimpleGeneratorMachineRenderer(tier, GTNN.id("block/generators/" + name)))
                .tooltips(Component.translatable("gtnn.machine." + name + ".tooltip", GTNNGeneratorMachine.getEfficiency(tier, name)))
                .tooltips(GTMachines.explosion())
                .tooltips(GTMachines.workableTiered(tier, GTValues.V[tier], GTValues.V[tier] * 64, recipeType, tankScalingFunction.apply(tier), false))
                .compassNode(name)
                .register(), tiers);
    }

    public static void init() {
        modifyGT();
    }

    private static void modifyGT() {
        if (GTRecipeTypes.ASSEMBLER_RECIPES.getMaxTooltips() == 3) GTRecipeTypes.ASSEMBLER_RECIPES.setMaxTooltips(4);
        var largeAssembler = GCyMMachines.LARGE_ASSEMBLER;
        var gtRecipeTypes = new ArrayList<>(Arrays.asList(Objects.requireNonNull(largeAssembler.getRecipeTypes())));
        gtRecipeTypes.add(GTNNRecipeTypes.PRECISION_ASSEMBLY_RECIPES);
        GTRecipeType[] gtRecipeTypesArray = gtRecipeTypes.toArray(GTRecipeType[]::new);
        largeAssembler.setRecipeTypes(gtRecipeTypesArray);
        largeAssembler.setTooltipBuilder(largeAssembler.getTooltipBuilder().andThen((itemStack, components) -> {
            components.add(Component.translatable("gtnn.precision_assembly.tooltip.1"));
            components.add(Component.translatable("gtnn.precision_assembly.tooltip.2"));
        }));
        largeAssembler.setRecipeModifier(GTNNMachines::assemblyRecipeModifier);
    }

    private static GTRecipe assemblyRecipeModifier(MetaMachine machine, GTRecipe gtRecipe) {
        if (gtRecipe.recipeType == GTNNRecipeTypes.PRECISION_ASSEMBLY_RECIPES) {
            return GTRecipeModifiers.ELECTRIC_OVERCLOCK.apply(OverclockingLogic.NON_PERFECT_OVERCLOCK).apply(machine, gtRecipe);
        } else {
            return GCyMMachines.LARGE_ASSEMBLER.getRecipeModifier().apply(machine, gtRecipe);
        }
    }
}
