package org.arbor.arborcore.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.block.ICoilType;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.MultiblockShapeInfo;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.registry.registrate.MachineBuilder;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.arbor.arborcore.ArborCore;
import org.arbor.arborcore.api.machine.multiblock.APartAbility;
import org.arbor.arborcore.api.machine.multiblock.ChemicalPlant;
import org.arbor.arborcore.api.machine.multiblock.NeutronActivator;
import org.arbor.arborcore.api.machine.multiblock.part.HighSpeedPipeBlock;
import org.arbor.arborcore.api.machine.multiblock.part.NeutronAccelerator;
import org.arbor.arborcore.api.pattern.APredicates;
import org.arbor.arborcore.block.BlockTier;
import org.arbor.arborcore.block.MachineCasingBlock;
import org.arbor.arborcore.block.PipeBlock;
import org.arbor.arborcore.block.PlantCasingBlock;
import org.arbor.arborcore.client.renderer.machine.BlockMachineRenderer;

import java.util.*;
import java.util.function.BiFunction;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.*;
import static com.gregtechceu.gtceu.api.pattern.Predicates.abilities;
import static com.gregtechceu.gtceu.api.pattern.util.RelativeDirection.*;
import static org.arbor.arborcore.api.registry.ArborRegistries.REGISTRATE;

@SuppressWarnings("unused")
public class ArborMachines {
    public static final int[] NA_TIERS = GTValues.tiersBetween(1, 8);
    static {
        REGISTRATE.creativeModeTab(() -> ArborCreativeModeTabs.ArborCreativeModeTab);
    }
    //////////////////////////////////////
    //**********     Part     **********//
    //////////////////////////////////////

    public static final MachineDefinition[] NEUTRON_ACCELERATOR = registerTieredMachines("neutron_accelerator",
            NeutronAccelerator::new,
            (tier, builder) ->builder
                    .langValue(VNF[tier] + "Neutron Accelerator")
                    .rotationState(RotationState.ALL)
                    .abilities(APartAbility.NEUTRON_ACCELERATOR)
                    .tooltips(Component.translatable("gtceu.machine.neutron_accelerator.tooltip1"))
                    .tooltips(Component.translatable("gtceu.machine.neutron_accelerator.tooltip2", V[tier]))
                    .tooltips(Component.translatable("gtceu.machine.neutron_accelerator.tooltip3", V[tier] * 8 / 10))
                    .tooltips(Component.translatable("gtceu.machine.neutron_accelerator.tooltip4"))
                    .overlayTieredHullRenderer("neutron_accelerator")
                    .compassNode("neutron_accelerator")
                    .register(),
            NA_TIERS);

    public static final MachineDefinition HIGH_SPEED_PIPE_BLOCK = REGISTRATE.machine("high_speed_pipe_block", HighSpeedPipeBlock::new)
            .renderer(() -> new BlockMachineRenderer(ArborCore.id("block/machine/part/high_speed_pipe_block")))
            .rotationState(RotationState.Y_AXIS)
            .register();

    // public static final MachineDefinition CATALYTIC_HATCH = GTRegistries.REGISTRATE.machine("catalytic_hatch",
    //                 (holder) -> new SteamItemBusPartMachine(holder, IO.IN))
    //         .rotationState(RotationState.ALL)
    //         .abilities(PartAbility.IMPORT_ITEMS)
    //         .overlaySteamHullRenderer("item_bus.import")
    //         .langValue("Catalytic Hatch")
    //         .compassSections(GTCompassSections.PARTS)
    //         .compassNode("item_bus")
    //         .register();

    //////////////////////////////////////
    //**********   Machine    **********//
    //////////////////////////////////////
    public static final MultiblockMachineDefinition CHEMICAL_PLANT = REGISTRATE.multiblock("chemical_plant", ChemicalPlant::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .tooltips(Component.translatable("gtceu.multiblock.chemical_plant.tooltip1"))
            .tooltips(Component.translatable("gtceu.multiblock.chemical_plant.tooltip2"))
            .tooltips(Component.translatable("gtceu.multiblock.chemical_plant.tooltip3"))
            .tooltips(Component.translatable("gtceu.multiblock.chemical_plant.tooltip4"))
            .recipeTypes(ArborRecipesTypes.CHEMICAL_PLANT_RECIPES)
            .recipeModifier(ChemicalPlant::chemicalPlantRecipe)
            .appearanceBlock(GTBlocks.CASING_BRONZE_BRICKS)
            .pattern(definition -> FactoryBlockPattern.start()
                    .aisle("VVVVVVV", "A#####A", "A#####A", "A#####A", "A#####A", "A#####A", "AAAAAAA")
                    .aisle("ABBBBBA", "#BBBBB#", "#######", "#######", "#######", "#BBBBB#", "AAAAAAA")
                    .aisle("ABBBBBA", "#BCCCB#", "##DDD##", "##CCC##", "##DDD##", "#BCCCB#", "AAAAAAA")
                    .aisle("ABBBBBA", "#BCCCB#", "##DDD##", "##CCC##", "##DDD##", "#BCCCB#", "AAAAAAA")
                    .aisle("ABBBBBA", "#BCCCB#", "##DDD##", "##CCC##", "##DDD##", "#BCCCB#", "AAAAAAA")
                    .aisle("ABBBBBA", "#BBBBB#", "#######", "#######", "#######", "#BBBBB#", "AAAAAAA")
                    .aisle("VVVSVVV", "A#####A", "A#####A", "A#####A", "A#####A", "A#####A", "AAAAAAA")
                    .where("S", Predicates.controller(Predicates.blocks(definition.get())))
                    .where("V", Predicates.blocks(GTBlocks.CASING_BRONZE_BRICKS.get())
                            .or(autoAbilities(definition.getRecipeTypes()))
                            .or(autoAbilities(true, false, false))
                            .or(abilities(PartAbility.INPUT_ENERGY).setMinGlobalLimited(1))
                            .or(abilities(PartAbility.IMPORT_ITEMS).setMinGlobalLimited(1))
                            .or(abilities(PartAbility.IMPORT_FLUIDS).setMinGlobalLimited(1))
                            .or(abilities(PartAbility.EXPORT_ITEMS).setMinGlobalLimited(1))
                            .or(abilities(PartAbility.EXPORT_FLUIDS).setMinGlobalLimited(1))
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
                        .aisle("JKLSMNO", "A#####A", "A#####A", "A#####A", "A#####A", "A#####A", "AAAAAAA")
                        .aisle("ABBBBBA", "#BBBBB#", "#######", "#######", "#######", "#BBBBB#", "AAAAAAA")
                        .aisle("ABBBBBA", "#BCCCB#", "##DDD##", "##CCC##", "##DDD##", "#BCCCB#", "AAAAAAA")
                        .aisle("ABBBBBA", "#BCCCB#", "##DDD##", "##CCC##", "##DDD##", "#BCCCB#", "AAAAAAA")
                        .aisle("ABBBBBA", "#BCCCB#", "##DDD##", "##CCC##", "##DDD##", "#BCCCB#", "AAAAAAA")
                        .aisle("ABBBBBA", "#BBBBB#", "#######", "#######", "#######", "#BBBBB#", "AAAAAAA")
                        .aisle("VVVVVVV", "A#####A", "A#####A", "A#####A", "A#####A", "A#####A", "AAAAAAA")
                        .where('S', definition, Direction.NORTH)
                        .where('V', GTBlocks.CASING_BRONZE_BRICKS)
                        .where('#', Blocks.AIR.defaultBlockState())
                        .where('J', GTMachines.MAINTENANCE_HATCH, Direction.NORTH);
                Map<Integer, BlockState> shapeBlock = new HashMap<>();
                for (PlantCasingBlock.PlantCasing casing : PlantCasingBlock.PlantCasing.values()) {
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
                    builder.where('K', GTMachines.ITEM_IMPORT_BUS[tier.tier()], Direction.NORTH);
                    builder.where('L', GTMachines.ITEM_EXPORT_BUS[tier.tier()], Direction.NORTH);
                    builder.where('M', GTMachines.FLUID_IMPORT_HATCH[tier.tier()], Direction.NORTH);
                    builder.where('N', GTMachines.FLUID_EXPORT_HATCH[tier.tier()], Direction.NORTH);
                    builder.where('O', GTMachines.ENERGY_INPUT_HATCH[tier.tier()], Direction.NORTH);
                    shapeInfo.add(builder.shallowCopy().build());
                }
                return shapeInfo;
            })
            .workableCasingRenderer(
                    GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"),
                    ArborCore.id("block/multiblock/chemical_plant"), false)
            .additionalDisplay((controller, components) -> {
                if (controller instanceof ChemicalPlant chemicalPlant && controller.isFormed()) {
                    components.add(Component.translatable("gtceu.multiblock.chemical_plant.heating_coil", chemicalPlant.getCoilTier() * 50));
                    components.add(Component.translatable("gtceu.multiblock.chemical_plant.parallel_level", chemicalPlant.getPipeTier() * 2));
                }
            })
            .register();

     public static final MultiblockMachineDefinition NEUTRON_ACTIVATOR = REGISTRATE.multiblock("neutron_activator", NeutronActivator::new)
             .rotationState(RotationState.NON_Y_AXIS)
             .tooltips(Component.translatable("gtceu.multiblock.neutron_activator.tooltip1"))
             .tooltips(Component.translatable("gtceu.multiblock.neutron_activator.tooltip2"))
             .tooltips(Component.translatable("gtceu.multiblock.neutron_activator.tooltip3"))
             .tooltips(Component.translatable("gtceu.multiblock.neutron_activator.tooltip4"))
             .recipeTypes(ArborRecipesTypes.NEUTRON_ACTIVATOR_RECIPES)
             .recipeModifier(NeutronActivator::neutronActivatorRecipe)
             .appearanceBlock(GTBlocks.CASING_STAINLESS_CLEAN)
             .pattern(definition -> FactoryBlockPattern.start(RIGHT, BACK, UP)
                     .aisle("AASAA", "ABBBA", "ABBBA", "ABBBA", "AAAAA")
                     .aisle("C###C", "#DDD#", "#DED#", "#DDD#", "C###C").setRepeatable(4, 24)
                     .aisle("VVVVV", "VBBBV", "VBBBV", "VBBBV", "VVVVV")
                     .where("S", Predicates.controller(Predicates.blocks(definition.get())))
                     .where("V", Predicates.blocks(GTBlocks.CASING_STAINLESS_CLEAN.get())
                             .or(abilities(PartAbility.IMPORT_FLUIDS))
                             .or(abilities(PartAbility.IMPORT_ITEMS)))
                     .where("A", Predicates.blocks(GTBlocks.CASING_STAINLESS_CLEAN.get())
                             .or(abilities(PartAbility.EXPORT_FLUIDS))
                             .or(abilities(PartAbility.EXPORT_ITEMS))
                             .or(abilities(APartAbility.NEUTRON_ACCELERATOR))
                             .or(autoAbilities(true, false, false)))
                     .where("B", Predicates.blocks(GTBlocks.CASING_STEEL_SOLID.get()))
                     .where("C", Predicates.blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, GTMaterials.Steel)))
                     .where("D", Predicates.blocks(GTBlocks.CASING_LAMINATED_GLASS.get()))
                     .where("E", Predicates.blocks(HIGH_SPEED_PIPE_BLOCK.get()))
                     .where("#", Predicates.air())
                     .build())
             .workableCasingRenderer(
                     GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel"),
                     ArborCore.id("block/multiblock/neutron_activator"), false)
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

    public static void init() {
    }
}
