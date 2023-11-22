package org.arbor.arborcore.data;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.block.ICoilType;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.data.RotationState;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MultiblockMachineDefinition;
import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine;
import com.gregtechceu.gtceu.api.pattern.FactoryBlockPattern;
import com.gregtechceu.gtceu.api.pattern.MultiblockShapeInfo;
import com.gregtechceu.gtceu.api.pattern.Predicates;
import com.gregtechceu.gtceu.api.registry.GTRegistries;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTCompassSections;
import com.gregtechceu.gtceu.common.data.GTMachines;
import com.gregtechceu.gtceu.common.machine.multiblock.part.SteamItemBusPartMachine;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Blocks;
import org.arbor.arborcore.api.block.IPlantCasing;
import org.arbor.arborcore.block.PlantCasingBlock;

import java.util.ArrayList;
import java.util.List;

import static com.gregtechceu.gtceu.api.pattern.Predicates.autoAbilities;

public class ArborMachines {
    //////////////////////////////////////
    //**********     Part     **********//
    //////////////////////////////////////
    public static final MachineDefinition CATALYTIC_HATCH = GTRegistries.REGISTRATE.machine("catalytic_hatch",
                    (holder) -> new SteamItemBusPartMachine(holder, IO.IN))
            .rotationState(RotationState.ALL)
            .abilities(PartAbility.IMPORT_ITEMS)
            .overlaySteamHullRenderer("item_bus.import")
            .langValue("Catalytic Hatch")
            .compassSections(GTCompassSections.PARTS)
            .compassNode("item_bus")
            .register();

    //////////////////////////////////////
    //**********   Machine    **********//
    //////////////////////////////////////
    public static final MultiblockMachineDefinition CHEMICAL_PLANT = GTRegistries.REGISTRATE.multiblock("chemical_plant", WorkableElectricMultiblockMachine::new)
            .rotationState(RotationState.NON_Y_AXIS)
            .recipeTypes(ArborRecipes.CHEMICAL_PLANT_RECIPES)
            .appearanceBlock(GTBlocks.CASING_BRONZE_BRICKS)
            .pattern(definition -> FactoryBlockPattern.start()
            .aisle("VVVVVVV", "A#####A", "A#####A", "A#####A", "A#####A", "A#####A", "AAAAAAA")
            .aisle("VBBBBBV", "#BBBBB#", "#######", "#######", "#######", "#BBBBB#", "AAAAAAA")
            .aisle("VBBBBBV", "#BCCCB#", "##DDD##", "##CCC##", "##DDD##", "#BCCCB#", "AAAAAAA")
            .aisle("VBBBBBV", "#BCCCB#", "##DDD##", "##CCC##", "##DDD##", "#BCCCB#", "AAAAAAA")
            .aisle("VBBBBBV", "#BCCCB#", "##DDD##", "##CCC##", "##DDD##", "#BCCCB#", "AAAAAAA")
            .aisle("VBBBBBV", "#BBBBB#", "#######", "#######", "#######", "#BBBBB#", "AAAAAAA")
            .aisle("VVVSVVV", "A#####A", "A#####A", "A#####A", "A#####A", "A#####A", "AAAAAAA")
            .where("S", Predicates.controller(Predicates.blocks(definition.get())))
            .where("V", Predicates.blocks(GTBlocks.CASING_BRONZE_BRICKS.get())
                    .or(autoAbilities(definition.getRecipeTypes()))
                    .or(autoAbilities(true, false, false)))
            .where("A", Predicates.blocks(GTBlocks.CASING_BRONZE_BRICKS.get()))
            .where("B", Predicates.blocks(GTBlocks.CASING_STEEL_PIPE.get()).setMinGlobalLimited(5))
            .where("C", Predicates.heatingCoils())
            .where("D", Predicates.blocks(GTBlocks.MACHINE_CASING_LV.get()).setMinGlobalLimited(5))
            .where("#", Predicates.air())
            .build())
            .shapeInfos(definition -> {
                List<MultiblockShapeInfo> shapeInfo = new ArrayList<>();
                var builder = MultiblockShapeInfo.builder()
                        .aisle("JKLSMNP", "A#####A", "A#####A", "A#####A", "A#####A", "A#####A", "AAAAAAA")
                        .aisle("ABBBBBA", "#BBBBB#", "#######", "#######", "#######", "#BBBBB#", "AAAAAAA")
                        .aisle("ABBBBBA", "#BCCCB#", "##DDD##", "##CCC##", "##DDD##", "#BCCCB#", "AAAAAAA")
                        .aisle("ABBBBBA", "#BCCCB#", "##DDD##", "##CCC##", "##DDD##", "#BCCCB#", "AAAAAAA")
                        .aisle("ABBBBBA", "#BCCCB#", "##DDD##", "##CCC##", "##DDD##", "#BCCCB#", "AAAAAAA")
                        .aisle("ABBBBBA", "#BBBBB#", "#######", "#######", "#######", "#BBBBB#", "AAAAAAA")
                        .aisle("AAAOAAA", "A#####A", "A#####A", "A#####A", "A#####A", "A#####A", "AAAAAAA")
                        .where('B', GTBlocks.CASING_STEEL_PIPE.getDefaultState())
                        .where('D', GTBlocks.MACHINE_CASING_LV.getDefaultState())
                        .where('X', GTBlocks.CASING_BRONZE_BRICKS.getDefaultState())
                        .where('S', definition, Direction.NORTH)
                        .where('#', Blocks.AIR.defaultBlockState())
                        .where('J', GTMachines.MAINTENANCE_HATCH, Direction.NORTH)
                        .where('K', GTMachines.ITEM_IMPORT_BUS[GTValues.LV], Direction.NORTH)
                        .where('L', GTMachines.ITEM_EXPORT_BUS[GTValues.LV], Direction.NORTH)
                        .where('M', GTMachines.FLUID_IMPORT_HATCH[GTValues.LV], Direction.NORTH)
                        .where('N', GTMachines.FLUID_EXPORT_HATCH[GTValues.LV], Direction.NORTH)
                        .where('O', GTMachines.ENERGY_INPUT_HATCH[GTValues.LV], Direction.SOUTH)
                        .where('P', CATALYTIC_HATCH, Direction.NORTH);
                for (ICoilType coil : GTBlocks.ALL_COILS.keySet()) {
                    for (IPlantCasing casing : PlantCasingBlock.AllPlantCasings.keySet()) {
                        builder.where('A', PlantCasingBlock.AllPlantCasings.get(casing).get());
                    }
                    builder.where('C', GTBlocks.ALL_COILS.get(coil));
                    shapeInfo.add(builder.build());
                }
                return shapeInfo;
            })
            .workableCasingRenderer(
                    GTCEu.id("block/casings/solid/machine_casing_bronze_plated_bricks"),
                    GTCEu.id("block/multiblock/implosion_compressor"), false)
            .register();


    public static void init() {
    }
}
