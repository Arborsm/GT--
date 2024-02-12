package org.arbor.gtnn.data.recipes;

import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import net.minecraft.data.recipes.FinishedRecipe;
import org.arbor.gtnn.block.PlantCasingBlock;
import org.arbor.gtnn.data.GTNNMachines;

import java.util.function.Consumer;

import static com.gregtechceu.gtceu.api.GTValues.*;
import static com.gregtechceu.gtceu.api.data.tag.TagPrefix.*;
import static com.gregtechceu.gtceu.common.data.GTMachines.HULL;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static com.gregtechceu.gtceu.common.data.GTRecipeTypes.*;
import static org.arbor.gtnn.data.GTNNMaterials.*;
import static org.arbor.gtnn.data.GTNNRecipes.dur;
import static org.arbor.gtnn.data.GTNNRecipes.setPlantCasing;
import static org.arbor.gtnn.data.GTNNRecipesTypes.CHEMICAL_PLANT_RECIPES;
import static org.arbor.gtnn.data.GTNNRecipesTypes.ROCKET_ENGINE_RECIPES;

public class RocketFuel {

    public static void init(Consumer<FinishedRecipe> provider) {
        rocketEngine(provider);
        MIXER_RECIPES.recipeBuilder("black_matter")
                .inputItems(ChemicalHelper.get(dust, Lead, 3))
                .inputItems(ChemicalHelper.get(dust, Manganese, 5))
                .inputItems(ChemicalHelper.get(dust, Carbon, 12))
                .outputItems(ChemicalHelper.get(dust, BlackMatter, 20))
                .circuitMeta(13)
                .duration(82).EUt(VA[MV]).save(provider);

        ASSEMBLER_RECIPES.recipeBuilder("chemical_plant")
                .inputItems(HULL[MV].asStack(4))
                .inputItems(ChemicalHelper.get(plate, AnnealedCopper, 16))
                .inputItems(ChemicalHelper.get(pipeLargeFluid, Polyethylene, 4))
                .inputItems(ChemicalHelper.get(gear, Aluminium, 4))
                .inputItems(ChemicalHelper.get(frameGt, BlackMatter, 4))
                .inputFluids(BlackSteel.getFluid(1000))
                .outputItems(GTNNMachines.CHEMICAL_PLANT.asStack())
                .circuitMeta(19)
                .duration(dur(120)).EUt(VA[MV]).save(provider);


        CHEMICAL_PLANT_RECIPES.recipeBuilder("rp_1_mixed_fuel")
                .addCondition(setPlantCasing(PlantCasingBlock.STAINLESS))
                .inputFluids(Oxygen.getFluid(2000))
                .inputFluids(RP1RocketFuel.getFluid(500))
                .outputFluids(RP1.getFluid(1000))
                .circuitMeta(1)
                .duration(dur(15)).EUt(VA[HV]).save(provider);

        DISTILLERY_RECIPES.recipeBuilder("rp_1_rocket_fuel")
                .inputFluids(Kerosene.getFluid(20))
                .outputFluids(RP1RocketFuel.getFluid(15))
                .circuitMeta(23)
                .duration(dur(5)).EUt(VA[MV]).save(provider);

        DISTILLERY_RECIPES.recipeBuilder("kerosene")
                .inputFluids(Diesel.getFluid(250))
                .outputFluids(Kerosene.getFluid(150))
                .circuitMeta(23)
                .duration(16).EUt(VA[MV]).save(provider);

        CHEMICAL_PLANT_RECIPES.recipeBuilder("dense_hydrazine_mixed_fuel")
                .addCondition(setPlantCasing(PlantCasingBlock.TITANIUM))
                .inputFluids(Methanol.getFluid(6000))
                .inputFluids(Hydrazine.getFluid(4000))
                .outputFluids(DenseHydrazineMixedFuel.getFluid(10000))
                .circuitMeta(2)
                .duration(dur(30)).EUt(VA[HV]).save(provider);

        CHEMICAL_PLANT_RECIPES.recipeBuilder("hydrazine")
                .addCondition(setPlantCasing(PlantCasingBlock.STEEL))
                .inputFluids(Ammonia.getFluid(1000))
                .inputFluids(HydrogenPeroxide.getFluid(1000))
                .outputFluids(Hydrazine.getFluid(1000))
                .circuitMeta(2)
                .duration(dur(10)).EUt(VA[HV]).save(provider);

        CHEMICAL_PLANT_RECIPES.recipeBuilder("hydrogen_peroxide_oxygen")
                .addCondition(setPlantCasing(PlantCasingBlock.STEEL))
                .inputFluids(Oxygen.getFluid(10000))
                .inputFluids(EthylAnthraHydroQuinone.getFluid(5000))
                .inputFluids(Anthracene.getFluid(50))
                .outputFluids(HydrogenPeroxide.getFluid(5000))
                .outputFluids(EthylAnthraQuinone.getFluid(5000))
                .circuitMeta(4)
                .duration(dur(5)).EUt(VA[HV]).save(provider);

        CHEMICAL_PLANT_RECIPES.recipeBuilder("hydrogen_peroxide_air")
                .addCondition(setPlantCasing(PlantCasingBlock.STEEL))
                .inputFluids(Air.getFluid(20000))
                .inputFluids(EthylAnthraHydroQuinone.getFluid(5000))
                .inputFluids(Anthracene.getFluid(50))
                .outputFluids(HydrogenPeroxide.getFluid(5000))
                .outputFluids(EthylAnthraQuinone.getFluid(5000))
                .circuitMeta(4)
                .duration(dur(30)).EUt(VA[HV]).save(provider);

        CHEMICAL_PLANT_RECIPES.recipeBuilder("ethyl_anthra_quinone")
                .addCondition(setPlantCasing(PlantCasingBlock.ALUMINIUM))
                .inputItems(ChemicalHelper.get(dust, PhthalicAnhydride, 15))
                .inputFluids(Ethylbenzene.getFluid(1000))
                .outputFluids(EthylAnthraQuinone.getFluid(1000))
                .circuitMeta(4)
                .duration(dur(15)).EUt(VA[MV]).save(provider);

        CHEMICAL_RECIPES.recipeBuilder("phthalic_anhydride")
                .inputItems(ChemicalHelper.get(dustSmall, VanadiumPentoxide, 1))
                .inputFluids(Naphthalene.getFluid(1000))
                .inputFluids(Air.getFluid(1000))
                .outputItems(ChemicalHelper.get(dust, PhthalicAnhydride, 15))
                .duration(dur(40)).EUt(VA[EV]).save(provider);

        BLAST_RECIPES.recipeBuilder("vanadium_pentoxide")
                .inputItems(ChemicalHelper.get(dust, Vanadium, 2))
                .inputFluids(Oxygen.getFluid(5000))
                .outputItems(ChemicalHelper.get(dust, VanadiumPentoxide, 7))
                .circuitMeta(24)
                .blastFurnaceTemp(2500)
                .duration(dur(10)).EUt(VA[MV]).save(provider);

        MIXER_RECIPES.recipeBuilder("orange_metal_catalyst")
                .inputItems(ChemicalHelper.get(dust, Vanadium, 1))
                .inputItems(ChemicalHelper.get(dust, Palladium, 1))
                .outputItems(ChemicalHelper.get(dust, OrangeMetalCatalyst, 64))
                .outputItems(ChemicalHelper.get(dust, OrangeMetalCatalyst, 32))
                .circuitMeta(32)
                .duration(dur(8)).EUt(VA[HV]).save(provider);

        CHEMICAL_PLANT_RECIPES.recipeBuilder("ethyl_anthra_hydro_quinone")
                .addCondition(setPlantCasing(PlantCasingBlock.ALUMINIUM))
                .inputFluids(EthylAnthraQuinone.getFluid(1000))
                .inputFluids(HydrogenPeroxide.getFluid(1000))
                .chancedInput(ChemicalHelper.get(dust, OrangeMetalCatalyst, 1), 5000, -1000)
                .outputFluids(EthylAnthraHydroQuinone.getFluid(1000))
                .circuitMeta(4)
                .duration(dur(40)).EUt(VA[MV]).save(provider);

        DISTILLATION_RECIPES.recipeBuilder("distill_coal_tar")
                .inputFluids(CoalTar.getFluid(1000))
                .outputItems(dustSmall, Coke)
                .outputFluids(Naphthalene.getFluid(400))
                .outputFluids(HydrogenSulfide.getFluid(300))
                .outputFluids(Creosote.getFluid(200))
                .outputFluids(Phenol.getFluid(100))
                .outputFluids(Anthracene.getFluid(50))
                .duration(80).EUt(VA[MV])
                .save(provider);

        CHEMICAL_PLANT_RECIPES.recipeBuilder("methyl_hydrazine")
                .addCondition(setPlantCasing(PlantCasingBlock.ALUMINIUM))
                .inputItems(ChemicalHelper.get(dust, Carbon, 1))
                .inputFluids(Hydrogen.getFluid(2000))
                .inputFluids(Hydrazine.getFluid(1000))
                .outputFluids(MethylHydrazine.getFluid(1000))
                .circuitMeta(21)
                .duration(dur(48)).EUt(VA[HV]).save(provider);

        CHEMICAL_PLANT_RECIPES.recipeBuilder("methylhydrazine_nitrate_rocket_fuel")
                .addCondition(setPlantCasing(PlantCasingBlock.TUNGSTENSTEEL))
                .inputFluids(MethylHydrazine.getFluid(2000))
                .inputFluids(NitricAcid.getFluid(1000))
                .outputFluids(MethylhydrazineNitrateRocketFuel.getFluid(2000))
                .circuitMeta(3)
                .duration(dur(45)).EUt(VA[HV]).save(provider);
    }

    private static void rocketEngine(Consumer<FinishedRecipe> provider) {
        ROCKET_ENGINE_RECIPES.recipeBuilder(RP1RocketFuel.getName())
                .inputFluids(RP1RocketFuel.getFluid(4))
                .EUt(-V[EV])
                .duration(3)
                .save(provider);
        ROCKET_ENGINE_RECIPES.recipeBuilder(DenseHydrazineMixedFuel.getName())
                .inputFluids(DenseHydrazineMixedFuel.getFluid(2))
                .EUt(-V[EV])
                .duration(3)
                .save(provider);
        ROCKET_ENGINE_RECIPES.recipeBuilder(MethylhydrazineNitrateRocketFuel.getName())
                .inputFluids(MethylhydrazineNitrateRocketFuel.getFluid(1))
                .EUt(-V[EV])
                .duration(3)
                .save(provider);
        ROCKET_ENGINE_RECIPES.recipeBuilder(UDMHRocketFuel.getName())
                .inputFluids(UDMHRocketFuel.getFluid(1))
                .EUt(-V[EV])
                .duration(6)
                .save(provider);
    }
}
