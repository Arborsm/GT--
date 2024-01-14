package org.arbor.gtnn.data;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.recipe.GTRecipeType;
import com.gregtechceu.gtceu.common.data.GTRecipeTypes;
import com.gregtechceu.gtceu.common.data.GTSoundEntries;
import com.lowdragmc.lowdraglib.gui.texture.ProgressTexture;

public class GTNNRecipesTypes {
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

    public static void init() {
    }
}
