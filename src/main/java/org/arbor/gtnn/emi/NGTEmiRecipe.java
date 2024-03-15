package org.arbor.gtnn.emi;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.OverclockingLogic;
import com.gregtechceu.gtceu.api.recipe.RecipeHelper;
import com.gregtechceu.gtceu.integration.emi.recipe.GTRecipeTypeEmiCategory;
import com.lowdragmc.lowdraglib.emi.ModularEmiRecipe;
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup;
import com.lowdragmc.lowdraglib.utils.LocalizationUtils;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.widget.WidgetHolder;
import dev.emi.emi.screen.RecipeScreen;
import it.unimi.dsi.fastutil.longs.LongIntPair;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.arbor.gtnn.GTNN;
import org.arbor.gtnn.mixin.emi.ARecipeScreen;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

import static com.gregtechceu.gtceu.api.GTValues.*;

public class NGTEmiRecipe extends ModularEmiRecipe<WidgetGroup> {
    final GTRecipeTypeEmiCategory category;
    final GTRecipe recipe;
    final NGTRecipeWidget gtRecipeWidget;

    private static final ResourceLocation BUTTONS = GTNN.id("textures/gui/buttons.png");

    public NGTEmiRecipe(GTRecipeTypeEmiCategory category, GTRecipe recipe) {
        super(() -> new NGTRecipeWidget(recipe));
        this.gtRecipeWidget = (NGTRecipeWidget) super.widget.get();
        this.category = category;
        this.recipe = recipe;
    }

    @Override
    public void addWidgets(WidgetHolder widgets) {
        super.addWidgets(widgets);
        int tier = gtRecipeWidget.getTier();
        widgets.addButton(gtRecipeWidget.getSize().width + 5, 3, 12, 12, 0, 0, BUTTONS, () -> true, (mouseX, mouseY, button) -> {
            if (button == GLFW.GLFW_MOUSE_BUTTON_LEFT) {
                gtRecipeWidget.setTier(tier + 1);
            } else if (button == GLFW.GLFW_MOUSE_BUTTON_RIGHT) {
                gtRecipeWidget.setTier(tier - 1);
            } else if (button == GLFW.GLFW_MOUSE_BUTTON_MIDDLE) {
                gtRecipeWidget.setTierToMin();
            }
            if (Minecraft.getInstance().screen instanceof RecipeScreen screen0) {
                var screen = (RecipeScreen & ARecipeScreen) screen0;
                screen.setPage(screen.getTabPage(), screen.getTab(), screen.getPage());
            }
        });
        widgets.addTooltipText(List.of(
                Component.translatable("gtnn.emi.tooltip.1", GTValues.VNF[gtRecipeWidget.getMinTier()]),
                Component.translatable("gtnn.emi.tooltip.2"),
                Component.translatable("gtnn.emi.tooltip.3"),
                Component.translatable("gtnn.emi.tooltip.4")
        ), gtRecipeWidget.getSize().width + 3, 3, 15, 15);

        String tierText = GTValues.VNF[tier];
        int textsY = gtRecipeWidget.getYOffset() - 10;
        int duration = recipe.duration;
        long inputEUt = RecipeHelper.getInputEUt(recipe);
        long outputEUt = RecipeHelper.getOutputEUt(recipe);
        if (tier > gtRecipeWidget.getMinTier() && inputEUt != 0) {
            LongIntPair pair = OverclockingLogic.NON_PERFECT_OVERCLOCK.getLogic().runOverclockingLogic(
                    recipe, inputEUt, GTValues.V[tier], duration, GTValues.MAX);
            duration = pair.rightInt();
            inputEUt = pair.firstLong();
            tierText = tierText.formatted(ChatFormatting.ITALIC);
        }
        List<Component> texts = new ArrayList<>(4);
        texts.add(Component.literal(LocalizationUtils.format("gtceu.recipe.duration", duration / 20f)));
        if (inputEUt != 0) {
            texts.add(Component.literal(LocalizationUtils.format("gtnn.recipe.eu", inputEUt, tierText)));
            texts.add(Component.literal(LocalizationUtils.format("gtceu.recipe.total", (inputEUt * duration))));
        }
        if (outputEUt != 0) {
            texts.add(Component.literal(LocalizationUtils.format("gtceu.recipe.eu_inverted", outputEUt)));
            texts.add(Component.literal(LocalizationUtils.format("gtceu.recipe.total", (outputEUt * duration))));
        }
        for (Component text : texts) {
            textsY += 10;
            widgets.addText(text, 3, textsY, -1, true);
        }
        if (inputEUt != 0) {
            int x = gtRecipeWidget.getSize().width - switch (tier) {
                case ULV, LuV, ZPM, UHV, UEV, UXV -> 20;
                case OpV, MAX -> 22;
                case UIV -> 18;
                case IV -> 12;
                default -> 14;
            };
            widgets.addText(Component.literal(tierText), x, gtRecipeWidget.getSize().height - 10, -1, false);
        }
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return category;
    }

    @Override
    public @Nullable ResourceLocation getId() {
        return recipe.getId();
    }
}
