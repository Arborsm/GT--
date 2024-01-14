package org.arbor.gtnn.api.recipe;

import com.google.gson.JsonObject;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.trait.RecipeLogic;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.RecipeCondition;
import lombok.Getter;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.util.GsonHelper;
import org.arbor.gtnn.api.machine.multiblock.NeutronActivator;
import org.jetbrains.annotations.NotNull;

@Getter
public class NeutronActivatorCondition extends RecipeCondition {
    public static final NeutronActivatorCondition INSTANCE = new NeutronActivatorCondition();
    private int evRange = 0;
    public NeutronActivatorCondition(int max, int min){
        super();
        this.evRange = max * 10000 + min;
    }

    public NeutronActivatorCondition() {
        super();
    }

    @Override
    public String getType() {
        return "neutron_activator_condition";
    }

    @Override
    public Component getTooltips() {
        int max = evRange % 10000;
        int min = evRange / 10000;
        return Component.translatable("gtnn.recipe.condition.neutron_activator_condition_tooltip", max, min);
    }

    @Override
    public boolean test(@NotNull GTRecipe gtRecipe, @NotNull RecipeLogic recipeLogic) {
        return NeutronActivator.checkNeutronActivatorCondition((MetaMachine) recipeLogic.machine, gtRecipe);
    }

    @Override
    public RecipeCondition createTemplate() {
        return new NeutronActivatorCondition();
    }

    @NotNull
    @Override
    public JsonObject serialize() {
        JsonObject value = super.serialize();
        value.addProperty("evRange", this.evRange);
        return value;
    }

    @Override
    public RecipeCondition deserialize(@NotNull JsonObject config) {
        super.deserialize(config);
        this.evRange = GsonHelper.getAsInt(config, "evRange", 0);
        return this;
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf) {
        super.toNetwork(buf);
        buf.writeInt(this.evRange);
    }

    @Override
    public RecipeCondition fromNetwork(FriendlyByteBuf buf) {
        super.fromNetwork(buf);
        this.evRange = buf.readInt();
        return this;
    }
}
