package org.arbor.gtnn.api.recipe;

import com.google.gson.JsonObject;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeCondition;
import lombok.Getter;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.util.GsonHelper;
import org.arbor.gtnn.block.PlantCasingBlock;
import org.jetbrains.annotations.NotNull;

@Getter
public class PlantCasingCondition extends RecipeCondition {
    public final static PlantCasingCondition INSTANCE = new PlantCasingCondition();

    private PlantCasingBlock plantCasing = PlantCasingBlock.BRONZE;

    public PlantCasingCondition(PlantCasingBlock plantCasing) {
        super();
        this.plantCasing = plantCasing;
    }


    public PlantCasingCondition() {
        super();
    }

    @Override
    public String getType() {
        return "chemical_plant_casing";
    }

    @Override
    public Component getTooltips() {
        return Component.translatable("gtnn.recipe.condition.plant_casing.tooltip", plantCasing.getTier() + 1, plantCasing.getName());
    }

    @Override
    public boolean test(@NotNull GTRecipe recipe, @NotNull RecipeLogic recipeLogic) {
        MetaMachine machine = recipeLogic.getMachine();
        return machine instanceof IMultiController && this.plantCasing != null;
    }

    @Override
    public RecipeCondition createTemplate() {
        return new PlantCasingCondition();
    }

    @NotNull
    @Override
    public JsonObject serialize() {
        JsonObject value = super.serialize();
        value.addProperty("plantCasing", plantCasing.getName());
        return value;
    }

    @Override
    public RecipeCondition deserialize(@NotNull JsonObject config) {
        super.deserialize(config);
        this.plantCasing = PlantCasingBlock.getByName(
                GsonHelper.getAsString(config, "plantCasing", "plantCasing"));
        return this;
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf) {
        super.toNetwork(buf);
        buf.writeUtf(this.plantCasing.getName());
    }

    @Override
    public RecipeCondition fromNetwork(FriendlyByteBuf buf) {
        super.fromNetwork(buf);
        this.plantCasing = PlantCasingBlock.getByNameOrDefault(buf.readUtf());
        return this;
    }
}
