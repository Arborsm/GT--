package org.arbor.gtnn.data;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;

import static com.lowdragmc.lowdraglib.gui.texture.ProgressTexture.FillDirection.LEFT_TO_RIGHT;

public class GTNNRecipeTypes {
    public static final GTRecipeType CHEMICAL_PLANT_RECIPES = GTRecipeTypes.register("chemical_plant", GTRecipeTypes.MULTIBLOCK)
            .setMaxIOSize(4, 4, 4, 2).setEUIO(IO.IN)
            .prepareBuilder(gtRecipeBuilder -> gtRecipeBuilder.EUt(GTValues.VA[GTValues.LV]))
            .setSlotOverlay(false, false, GuiTextures.BOX_OVERLAY)
            .setProgressBar(GuiTextures.PROGRESS_BAR_BATH, ProgressTexture.FillDirection.LEFT_TO_RIGHT)
            .setMaxTooltips(4)
            .setSound(GTSoundEntries.COOLING);

    public static final GTRecipeType NEUTRON_ACTIVATOR_RECIPES = GTRecipeTypes.register("neutron_activator", GTRecipeTypes.MULTIBLOCK)
            .setMaxIOSize(9, 9, 1, 1)
            .setMaxTooltips(5)
            .setSound(GTSoundEntries.COOLING);

    public static final GTRecipeType DRYER_RECIPES = GTRecipeTypes.register("dryer", GTRecipeTypes.ELECTRIC).setMaxIOSize(2, 9, 1, 1).setEUIO(IO.IN)
            .prepareBuilder(recipeBuilder -> recipeBuilder.EUt(5))
            .setProgressBar(GuiTextures.PROGRESS_BAR_EXTRACT, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CENTRIFUGE);

    public static final GTRecipeType NAQUADAH_REACTOR_RECIPES = GTRecipeTypes.register("naquadah_reactor", GTRecipeTypes.ELECTRIC)
            .setMaxIOSize(1, 1, 0, 0).setEUIO(IO.OUT)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CENTRIFUGE);

    public static final GTRecipeType ROCKET_ENGINE_RECIPES = GTRecipeTypes.register("rocket_engine", GTRecipeTypes.ELECTRIC)
            .setMaxIOSize(0, 0, 1, 1).setEUIO(IO.OUT)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CENTRIFUGE);

    public static final GTRecipeType NAQUADAH_REACTOR_MULTIBLOCK_RECIPES = GTRecipeTypes.register("naquadah_reactor_multiblock", GTRecipeTypes.MULTIBLOCK)
            .setMaxIOSize(0, 0, 1, 1).setEUIO(IO.OUT)
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.CENTRIFUGE);

    public static final GTRecipeType PRECISION_ASSEMBLY_RECIPES = GTRecipeTypes.register("precision_assembly", GTRecipeTypes.MULTIBLOCK)
            .setMaxIOSize(4, 1, 4, 0).setEUIO(IO.IN)
            .prepareBuilder(gtRecipeBuilder -> gtRecipeBuilder.EUt(GTValues.VA[GTValues.LV]))
            .setProgressBar(GuiTextures.PROGRESS_BAR_ARROW, LEFT_TO_RIGHT)
            .setSound(GTSoundEntries.COOLING);

    public static void init() {
    }

}
