package org.arbor.gtnn.data.recipes;

import com.gregtechceu.gtceu.api.data.chemical.material.stack.UnificationEntry;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.machine.multiblock.CleanroomType;
import com.gregtechceu.gtceu.common.data.GTBlocks;
import com.gregtechceu.gtceu.common.data.GTItems;
import com.gregtechceu.gtceu.data.recipe.VanillaRecipeHelper;
import net.mehvahdjukaar.supplementaries.Supplementaries;
import net.mehvahdjukaar.supplementaries.reg.ModRegistry;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import org.arbor.gtnn.GTNNIntegration;
import org.arbor.gtnn.data.GTNNBlocks;
import org.arbor.gtnn.data.GTNNItems;

import java.util.function.Consumer;
import java.util.function.Supplier;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.plate;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.rodLong;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static org.arbor.gtnn.data.GTNNRecipes.dur;

public class DefaultRecipes {
    public static void init(Consumer<FinishedRecipe> provider) {
        Misc.init(provider);
        SelfRecipes.init(provider);
        CHEMICAL_RECIPES.recipeBuilder("itnt")
                .inputItems(GTItems.GELLED_TOLUENE.asStack(4))
                .inputFluids(NitrationMixture.getFluid(200))
                .outputItems(GTNNBlocks.ITNT)
                .circuitMeta(1)
                .duration(dur(4)).EUt(VA[HV]).save(provider);
        addBOOMRecipes("heavy_plate_t1", GTNNItems.HEAVY_INGOT_T1, GTNNItems.HEAVY_PLATE_T1, VA[LV], dur(15), 1, provider);
        addBOOMRecipes("heavy_plate_t2", GTNNItems.HEAVY_INGOT_T2, GTNNItems.HEAVY_PLATE_T2, VA[LV], dur(15), 2, provider);
        addBOOMRecipes("heavy_plate_t3", GTNNItems.HEAVY_INGOT_T3, GTNNItems.HEAVY_PLATE_T3, VA[LV], dur(15), 3, provider);
        addBOOMRecipes("heavy_plate_t4", GTNNItems.HEAVY_INGOT_T4, GTNNItems.HEAVY_PLATE_T4, VA[LV], dur(15), 4, provider);

    }

    private static void addBOOMRecipes(String name, Supplier<? extends Item> input, Supplier<? extends Item> output, int eu, int time, int level, Consumer<FinishedRecipe> provider) {
        IMPLOSION_RECIPES.recipeBuilder(name)
                .inputItems(input)
                .inputItems(Items.AIR)
                .inputItems(new ItemStack(Items.TNT, level * 8))
                .outputItems(output)
                .duration(time).EUt(eu).save(provider);
        IMPLOSION_RECIPES.recipeBuilder(name + "_2")
                .inputItems(input)
                .inputItems(Items.AIR)
                .inputItems(GTNNBlocks.ITNT.asStack(level * 2))
                .outputItems(output)
                .duration(time).EUt(eu).save(provider);
        if (!GTNNIntegration.isSupplementariesLoaded()) return;
        IMPLOSION_RECIPES.recipeBuilder(name + "_3")
                .inputItems(input)
                .inputItems(Items.AIR)
                .inputItems(new ItemStack(ModRegistry.BOMB_ITEM.get(), level * 4))
                .outputItems(output)
                .duration(time).EUt(eu).save(provider);
    }

    public static class Misc {
        public static void init(Consumer<FinishedRecipe> provider) {
            if (GTNNIntegration.isSupplementariesLoaded()) {
                CHEMICAL_RECIPES.recipeBuilder("bomb")
                        .inputItems(Items.PAPER)
                        .inputItems(Items.STRING)
                        .inputFluids(GlycerylTrinitrate.getFluid(500))
                        .outputItems(ModRegistry.BOMB_ITEM)
                        .circuitMeta(1)
                        .duration(dur(8)).EUt(VA[ULV]).save(provider);
            }
            ASSEMBLER_RECIPES.recipeBuilder("computer_normal")
                    .inputItems(GTBlocks.MACHINE_CASING_MV.asStack(2))
                    .inputItems(TagPrefix.plate, Aluminium, 2)
                    .inputItems(TagPrefix.wireFine, Aluminium, 2)
                    .inputItems(TagPrefix.rotor, Iron)
                    .inputItems(GTItems.PLASTIC_CIRCUIT_BOARD)
                    .inputItems(GTItems.BASIC_CIRCUIT_BOARD)
                    .inputFluids(Polyethylene.getFluid(72))
                    .outputItems(getComputer())
                    .duration(dur(10)).EUt(VA[MV]).save(provider);
            ASSEMBLER_RECIPES.recipeBuilder("computer_advanced")
                    .inputItems(GTBlocks.MACHINE_CASING_HV.asStack())
                    .inputItems(TagPrefix.plate, Aluminium, 2)
                    .inputItems(TagPrefix.wireFine, Tantalum, 2)
                    .inputItems(TagPrefix.rotor, Iron)
                    .inputItems(GTItems.ADVANCED_CIRCUIT_BOARD)
                    .inputItems(GTItems.INTEGRATED_CIRCUIT_HV)
                    .inputFluids(Polyethylene.getFluid(72))
                    .outputItems(getComputerAdvanced())
                    .duration(dur(10)).EUt(VA[HV]).save(provider);
            ASSEMBLER_RECIPES.recipeBuilder("lightning_rod")
                    .inputItems(rodLong, Copper, 2)
                    .inputItems(plate, Copper, 3)
                    .outputItems(Items.LIGHTNING_ROD)
                    .EUt(VA[LV]).duration(50)
                    .circuitMeta(9).save(provider);
            VanillaRecipeHelper.addShapedRecipe(provider, "lightning_rod", new ItemStack(Items.LIGHTNING_ROD),
                    "hAf", "ABA", "dBs",
                    'A', new UnificationEntry(plate, Copper),
                    'B', new UnificationEntry(rodLong, Copper));
            ASSEMBLER_RECIPES.recipeBuilder("t1_chip")
                    .inputItems(getComputer().get().asItem())
                    .inputItems(GTItems.COVER_SCREEN)
                    .inputItems(GTItems.SENSOR_HV)
                    .inputItems(GTItems.EMITTER_HV)
                    .inputItems(GTNNItems.HEAVY_PLATE_T1)
                    .inputFluids(SolderingAlloy.getFluid(576))
                    .outputItems(GTNNItems.CHIP_T1)
                    .cleanroom(CleanroomType.CLEANROOM)
                    .duration(dur(30)).EUt(VA[HV]).save(provider);
            ASSEMBLER_RECIPES.recipeBuilder("t2_chip")
                    .inputItems(getComputer().get().asItem())
                    .inputItems(GTItems.COVER_SCREEN)
                    .inputItems(GTItems.FIELD_GENERATOR_EV.asStack(2))
                    .inputItems(GTNNItems.HEAVY_PLATE_T2)
                    .inputFluids(SolderingAlloy.getFluid(864))
                    .outputItems(GTNNItems.CHIP_T2)
                    .cleanroom(CleanroomType.CLEANROOM)
                    .duration(dur(30)).EUt(VA[EV]).save(provider);
            ASSEMBLER_RECIPES.recipeBuilder("t3_chip")
                    .inputItems(getComputer().get().asItem())
                    .inputItems(GTItems.COVER_SCREEN)
                    .inputItems(GTItems.SENSOR_IV)
                    .inputItems(GTItems.EMITTER_IV)
                    .inputItems(GTNNItems.HEAVY_PLATE_T3)
                    .inputFluids(SolderingAlloy.getFluid(1152))
                    .outputItems(GTNNItems.CHIP_T3)
                    .cleanroom(CleanroomType.CLEANROOM)
                    .duration(dur(30)).EUt(VA[IV]).save(provider);
            ASSEMBLER_RECIPES.recipeBuilder("t4_chip")
                    .inputItems(getComputerAdvanced().get().asItem())
                    .inputItems(GTItems.COVER_SCREEN)
                    .inputItems(GTItems.SENSOR_LuV)
                    .inputItems(GTItems.EMITTER_LuV)
                    .inputItems(GTNNItems.HEAVY_PLATE_T4)
                    .inputFluids(SolderingAlloy.getFluid(2304))
                    .outputItems(GTNNItems.CHIP_T4)
                    .cleanroom(CleanroomType.CLEANROOM)
                    .duration(dur(30)).EUt(VA[LuV]).save(provider);
        }

        private static Supplier<? extends ItemLike> getComputerAdvanced() {
            return GTNNIntegration.isCCTweakedLoaded() ? dan200.computercraft.shared.ModRegistry.Blocks.COMPUTER_ADVANCED : GTNNItems.COMPUTER_ADVANCED;
        }

        private static Supplier<? extends ItemLike> getComputer() {
            return GTNNIntegration.isCCTweakedLoaded() ? dan200.computercraft.shared.ModRegistry.Blocks.COMPUTER_NORMAL : GTNNItems.COMPUTER;
        }

        public static void removeRecipes(Consumer<ResourceLocation> consumer) {
            consumer.accept(new ResourceLocation("minecraft:lightning_rod"));
            if (GTNNIntegration.isSupplementariesLoaded()) {
                consumer.accept(Supplementaries.res("bomb"));
            }
            if (GTNNIntegration.isCCTweakedLoaded()) {
                consumer.accept(new ResourceLocation("computercraft", "computer_normal"));
                consumer.accept(new ResourceLocation("computercraft", "computer_advanced"));
                consumer.accept(new ResourceLocation("computercraft", "computer_advanced_upgrade"));
            }
        }
    }
}