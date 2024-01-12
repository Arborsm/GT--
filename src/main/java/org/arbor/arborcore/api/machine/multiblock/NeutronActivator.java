package org.arbor.arborcore.api.machine.multiblock;

import com.gregtechceu.gtceu.api.capability.IEnergyContainer;
import com.gregtechceu.gtceu.api.capability.recipe.EURecipeCapability;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.capability.recipe.ItemRecipeCapability;
import com.gregtechceu.gtceu.api.data.chemical.ChemicalHelper;
import com.gregtechceu.gtceu.api.data.tag.TagPrefix;
import com.gregtechceu.gtceu.api.gui.GuiTextures;
import com.gregtechceu.gtceu.api.gui.fancy.IFancyUIProvider;
import com.gregtechceu.gtceu.api.gui.fancy.TooltipsPanel;
import com.gregtechceu.gtceu.api.machine.ConditionalSubscriptionHandler;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.IExplosionMachine;
import com.gregtechceu.gtceu.api.machine.feature.IFancyUIMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IDisplayUIMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableMultiblockMachine;
import com.gregtechceu.gtceu.api.recipe.GTRecipe;
import com.gregtechceu.gtceu.api.recipe.content.Content;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.machine.multiblock.part.ItemBusPartMachine;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.lowdragmc.lowdraglib.gui.modular.ModularUI;
import com.lowdragmc.lowdraglib.gui.widget.*;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import it.unimi.dsi.fastutil.longs.Long2ObjectMaps;
import net.minecraft.ChatFormatting;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import org.arbor.arborcore.api.machine.multiblock.part.HighSpeedPipeBlock;
import org.arbor.arborcore.api.machine.multiblock.part.NeutronAccelerator;
import org.arbor.arborcore.api.recipe.NeutronActivatorCondition;
import org.arbor.arborcore.data.ArborItems;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class NeutronActivator extends WorkableMultiblockMachine implements IFancyUIMachine, IDisplayUIMachine, IExplosionMachine {
    public static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(NeutronActivator.class, WorkableMultiblockMachine.MANAGED_FIELD_HOLDER);
    protected int height;
    protected int eV = 0, mCeil = 0, mFloor = 0;
    protected ConditionalSubscriptionHandler tickSubscription;
    private int aTick = 0;
    private float efficiency = 0;
    public final int maxNeutronKineticEnergy = 1200000000;
    public NeutronActivator(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
        this.tickSubscription = new ConditionalSubscriptionHandler(this, this::naTick, this::isFormed);
    }

    //////////////////////////////////////
    //***    Multiblock LifeCycle    ***//
    //////////////////////////////////////
    @Override
    public void onStructureFormed() {
        height = 0;
        super.onStructureFormed();
        Map<Long, IO> ioMap = getMultiblockState().getMatchContext().getOrCreate("ioMap", Long2ObjectMaps::emptyMap);
        for (IMultiPart part : getParts()) {
            IO io = ioMap.getOrDefault(part.self().getPos().asLong(), IO.BOTH);
            if (io == IO.NONE) continue;
            for (var handler : part.getRecipeHandlers()) {
                var handlerIO = handler.getHandlerIO();
                // If IO not compatible
                if (io != IO.BOTH && handlerIO != IO.BOTH && io != handlerIO) continue;
                if (handler.getCapability() == EURecipeCapability.CAP && handler instanceof IEnergyContainer) {
                    traitSubscriptions.add(handler.addChangedListener(tickSubscription::updateSubscription));
                }
            }
            if (part instanceof HighSpeedPipeBlock) height++;
        }
        efficiency = (float) Math.pow(0.9f, height - 4);
    }

    @Override
    public void onStructureInvalid() {
        height = 0;
        mCeil = 0;
        mFloor = 0;
        super.onStructureInvalid();
    }

    protected void naTick() {
        boolean anyWorking = false;
        if (!getLevel().isClientSide) {
            for (IMultiPart part : getParts()) {
                if (part instanceof NeutronAccelerator na) {
                    na.energyTick();
                    if(na.isActive()){
                        anyWorking = true;
                        this.eV += (int) Math.max(
                                (ThreadLocalRandom.current().nextInt(na.getMaxEUConsume() + 1) + na.getMaxEUConsume()) * 10
                                        * Math.pow(0.95, height - 4),
                        10);
                    }
                }
                if (part instanceof ItemBusPartMachine itemBusPartMachine){
                    var inv = itemBusPartMachine.getInventory();
                    for (int i = 0; i < inv.getSlots(); i++){
                        if(inv.getStackInSlot(i).is(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Beryllium, 1).getItem()) ||
                                (inv.getStackInSlot(i).is(ChemicalHelper.get(TagPrefix.dust, GTMaterials.Graphite, 1).getItem()))
                        ){
                            int consume = Math.min(this.eV / 10000000, inv.getStackInSlot(i).getCount());
                            inv.setStackInSlot(i, ItemStack.EMPTY);
                            this.eV -=10000000 * consume;
                        }
                    }
                }
            }
            if (!anyWorking) {
                if (this.eV >= 72000 && aTick % 20 == 0) {
                    this.eV -= 72000;
                } else if (this.eV > 0 && aTick % 20 == 0) {
                    this.eV = 0;
                }
            }
            if (this.eV < 0) this.eV = 0;
            if (this.eV > maxNeutronKineticEnergy) doExplosion(4 * 32);
        }
        if (aTick == 20) {
            aTick = 0;
        } else {
            aTick++;
        }
    }

    @Override
    public void onPartUnload() {
        super.onPartUnload();
    }

    //////////////////////////////////////
    //**********     GUI     ***********//
    //////////////////////////////////////
    @Override
    public void addDisplayText(List<Component> textList) {
        IDisplayUIMachine.super.addDisplayText(textList);
        if (isFormed()) {
            textList.add(Component.translatable(getRecipeType().registryName.toLanguageKey())
                    .setStyle(Style.EMPTY.withColor(ChatFormatting.AQUA)
                            .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
                                    Component.translatable("gtceu.gui.machinemode.title")))));
            if (!isWorkingEnabled()) {
                textList.add(Component.translatable("gtceu.multiblock.work_paused"));
            } else if (isActive()) {
                textList.add(Component.translatable("gtceu.multiblock.running"));
                int currentProgress = (int) (recipeLogic.getProgressPercent() * 100);
                textList.add(Component.translatable("gtceu.multiblock.progress", currentProgress));
            } else {
                textList.add(Component.translatable("gtceu.multiblock.idling"));
            }
            if (recipeLogic.isWaiting()) {
                textList.add(Component.translatable("gtceu.multiblock.waiting").setStyle(Style.EMPTY.withColor(ChatFormatting.RED)));
            }
            textList.add(Component.translatable("gtceu.multiblock.neutronactivator.ev", processNumber(eV)));
            textList.add(Component.translatable("gtceu.multiblock.neutronactivator.height", FormattingUtil.formatNumbers(height)));
            textList.add(Component.translatable("gtceu.multiblock.neutronactivator.efficiency", FormattingUtil.formatNumbers(efficiency * 100)));
        }
        getDefinition().getAdditionalDisplay().accept(this, textList);
    }
    private String processNumber(int num) {
        float num2;
        num2 = ((float) num) / 1000F;
        if (num2 <= 0) {
            return String.format("%d", num);
        }
        if (num2 < 1000.0) {
            return String.format("%.1fK", num2);
        }
        num2 /= 1000F;
        return String.format("%.1fM", num2);
    }

    @Override
    public Widget createUIWidget() {
        var group = new WidgetGroup(0, 0, 170 + 8, 129 + 8);
        var container = new WidgetGroup(4, 4, 170, 129);
        container.addWidget(new DraggableScrollableWidgetGroup(4, 4, 162, 121).setBackground(getScreenTexture())
                .addWidget(new LabelWidget(4, 5, self().getBlockState().getBlock().getDescriptionId()))
                .addWidget(new ComponentPanelWidget(4, 17, this::addDisplayText)
                        .setMaxWidthLimit(150)
                        .clickHandler(this::handleDisplayClick)));
        container.setBackground(GuiTextures.BACKGROUND_INVERSE);
        group.addWidget(container);
        return group;
    }

    @Override
    public ModularUI createUI(Player entityPlayer) {
        return IFancyUIMachine.super.createUI(entityPlayer);
    }

    @SuppressWarnings("all")
    @Override
    public List<IFancyUIProvider> getSubTabs() {
        return getParts().stream().filter(e -> !(e instanceof HighSpeedPipeBlock)).filter(IFancyUIProvider.class::isInstance).map(IFancyUIProvider.class::cast).toList();
    }

    @Override
    public void attachTooltips(TooltipsPanel tooltipsPanel) {
        for (IMultiPart part : getParts()) {
            part.attachFancyTooltipsToController(this, tooltipsPanel);
        }
    }

    //////////////////////////////////////
    //******        NBT SAVE       *******//
    //////////////////////////////////////

    @Override
    public @NotNull ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    @Override
    public void saveCustomPersistedData(CompoundTag tag, boolean forDrop) {
        super.saveCustomPersistedData(tag, forDrop);
        CompoundTag naTag =writeToNBT(new CompoundTag());
        tag.put("NA", naTag);
    }

    @Override
    public void loadCustomPersistedData(CompoundTag tag) {
        super.loadCustomPersistedData(tag);
        readFromNBT(tag.getCompound("NA"));
    }

    public CompoundTag writeToNBT(CompoundTag compound) {
        compound.putInt("EV", eV);
        compound.putInt("Height", height);
        compound.putInt("Floor", mFloor);
        compound.putInt("Ceil",mCeil);
        compound.putFloat("Efficiency", efficiency);
        return compound;
    }

    public void readFromNBT(CompoundTag compound) {
        eV = compound.getInt("EV");
        height = compound.getInt("Height");
        mFloor = compound.getInt("Floor");
        mCeil = compound.getInt("Ceil");
        efficiency = compound.getFloat("Efficiency");
    }

    //////////////////////////////////////
    //******     RECIPE LOGIC    *******//
    //////////////////////////////////////

    @Override
    public boolean alwaysTryModifyRecipe(){
        return true;
    }

    @Nullable
    public static GTRecipe neutronActivatorRecipe(MetaMachine machine, GTRecipe recipe){
        if (machine instanceof NeutronActivator neutronActivator){
            GTRecipe recipe1 = recipe.copy();
            recipe1.duration = (int) Math.max(recipe1.duration * neutronActivator.efficiency, 1);
            if (recipe1.conditions.get(0) instanceof NeutronActivatorCondition neutronActivatorCondition){
                neutronActivator.mFloor = (neutronActivatorCondition.getEvRange() % 10000) * 1000000;
                neutronActivator.mCeil = (neutronActivatorCondition.getEvRange() / 10000) * 1000000;
                if (neutronActivator.eV > neutronActivator.mCeil || neutronActivator.eV < neutronActivator.mFloor) {
                    recipe1.outputs.clear();
                    recipe1.outputs.put(ItemRecipeCapability.CAP, List.of(new Content(Ingredient.of(ArborItems.RADIOACTIVE_WASTE), 1 ,0, null, null)));
                    return recipe1;
                }
                return recipe1;
            }
        }
        return null;
    }

    public static boolean checkNeutronActivatorCondition(MetaMachine machine, GTRecipe recipe){
        if (machine instanceof NeutronActivator ) {
            return recipe.conditions.get(0) instanceof NeutronActivatorCondition;
        }
        return false;
    }
}
