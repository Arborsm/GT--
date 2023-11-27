package org.arbor.arborcore.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.block.ICoilType;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.MultiblockShapeInfo;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTMachines;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.arbor.arborcore.ArborCore;
import org.arbor.arborcore.api.machine.multiblock.ChemicalPlant;
import org.arbor.arborcore.api.pattern.APredicates;
import org.arbor.arborcore.block.BlockTier;
import org.arbor.arborcore.block.MachineCasingBlock;
import org.arbor.arborcore.block.PipeBlock;
import org.arbor.arborcore.block.PlantCasingBlock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gregtechceu.gtceu.api.pattern.Predicates.abilities;
import static com.gregtechceu.gtceu.api.pattern.Predicates.autoAbilities;

@SuppressWarnings("unused")
public class ArborMachines {
    //////////////////////////////////////
    //**********     Part     **********//
    //////////////////////////////////////

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
    public static final MultiblockMachineDefinition CHEMICAL_PLANT = GTRegistries.REGISTRATE.multiblock("chemical_plant", ChemicalPlant::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .tooltips(Component.translatable("gtceu.multiblock.chemical_plant.tooltip1"))
            .tooltips(Component.translatable("gtceu.multiblock.chemical_plant.tooltip2"))
            .tooltips(Component.translatable("gtceu.multiblock.chemical_plant.tooltip3"))
            .tooltips(Component.translatable("gtceu.multiblock.chemical_plant.tooltip4"))
            .recipeTypes(ArborRecipesTypes.CHEMICAL_PLANT_RECIPES)
            .recipeModifier(ArborRecipeModifiers::chemicalPlantRecipe)
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
                    shapeInfo.add(builder.build());
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
                    // components.add(Component.translatableWithFallback("gtceu.multiblock.chemical_plant.energy_hatch", chemicalPlant.getEnergyHatchLevel()));
                }
            })
            .register();


    // public static final MultiblockMachineDefinition NeutronActivator = GTRegistries.REGISTRATE.multiblock("neutron_activator", NeutronActivator::new)
    //         .rotationState(RotationState.NON_Y_AXIS)
    //         .tooltips(Component.translatable("gtceu.multiblock.neutron_activator.tooltip1"))
    //         .tooltips(Component.translatable("gtceu.multiblock.neutron_activator.tooltip2"))
    //         .tooltips(Component.translatable("gtceu.multiblock.neutron_activator.tooltip3"))
    //         .tooltips(Component.translatable("gtceu.multiblock.neutron_activator.tooltip4"))
    //         .recipeTypes(ArborRecipesTypes.NEUTRON_ACTIVATOR_RECIPES)
    //         .appearanceBlock(GTBlocks.CASING_STAINLESS_CLEAN)
    //         .pattern(definition -> FactoryBlockPattern.start()
    //                 .aisle("AAAAA", "C###C", "C###C", "C###C", "C###C", "C###C", "VVVVV")
    //                 .aisle("ABBBA", "#DDD#", "#DDD#", "#DDD#", "#DDD#", "#DDD#", "VBBBV")
    //                 .aisle("ABBBA", "#DED#", "#DED#", "#DED#", "#DED#", "#DED#", "VBBBV")
    //                 .aisle("ABBBA", "#DDD#", "#DDD#", "#DDD#", "#DDD#", "#DDD#", "VBBBV")
    //                 .aisle("AASAA", "C###C", "C###C", "C###C", "C###C", "C###C", "VVVVV")
    //                 .where("S", Predicates.controller(Predicates.blocks(definition.get())))
    //                 .where("V", Predicates.blocks(GTBlocks.CASING_STAINLESS_CLEAN.get())
    //                         .or(autoAbilities(definition.getRecipeTypes()))
    //                         .or(autoAbilities(true, false, false)))
    //                 .where("A", Predicates.blocks(GTBlocks.CASING_STAINLESS_CLEAN.get())
    //                         .or(autoAbilities(definition.getRecipeTypes()))
    //                         .or(autoAbilities(true, false, false)))
    //                 .where("B", Predicates.blocks(GTBlocks.CASING_STEEL_SOLID.get()))
    //                 .where("C", Predicates.blocks(ChemicalHelper.getBlock(TagPrefix.frameGt, GTMaterials.Steel)))
    //                 .where("D", Predicates.blocks(GTBlocks.CASING_LAMINATED_GLASS.get()))
    //                 .where("E", Predicates.blocks(GTBlocks.CASING_BRONZE_BRICKS.get()))
    //                 .where("#", Predicates.air())
    //                 .build())
    //         .workableCasingRenderer(
    //                 GTCEu.id("block/casings/solid/machine_casing_clean_stainless_steel"),
    //                 ArborCore.id("block/multiblock/neutron_activator"), false)
    //         .register();

    public static void init() {
    }
}
