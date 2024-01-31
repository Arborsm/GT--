package org.arbor.gtnn.api.machine.multiblock;

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
import com.lowdragmc.lowdraglib.side.item.IItemTransfer;
import com.lowdragmc.lowdraglib.syncdata.annotation.DescSynced;
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import it.unimi.dsi.fastutil.longs.Long2ObjectMaps;
import lombok.Getter;
import net.minecraft.ChatFormatting;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.Ingredient;
import org.arbor.gtnn.api.machine.multiblock.part.HighSpeedPipeBlock;
import org.arbor.gtnn.api.machine.multiblock.part.NeutronAcceleratorMachine;
import org.arbor.gtnn.api.machine.multiblock.part.NeutronSensorMachine;
import org.arbor.gtnn.api.recipe.NeutronActivatorCondition;
import org.arbor.gtnn.data.GTNNItems;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class NeutronActivator extends WorkableMultiblockMachine implements IFancyUIMachine, IDisplayUIMachine, IExplosionMachine {
    public static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(NeutronActivator.class, WorkableMultiblockMachine.MANAGED_FIELD_HOLDER);
    @Getter
    @Persisted
    private int height = 0;
    @Getter
    @Persisted
    @DescSynced
    private int eV;
    @Persisted
    private boolean isWorking;
    protected ConditionalSubscriptionHandler neutronEnergySubs;
    protected ConditionalSubscriptionHandler moderateSubs;
    protected ConditionalSubscriptionHandler absorptionSubs;
    private Set<NeutronSensorMachine> sensorMachines;
    private Set<ItemBusPartMachine> busMachines;
    private Set<NeutronAcceleratorMachine> acceleratorMachines;
    private static final int M = 1000000;
    private static final int K = 1000;
    private final static int MAX_ENERGY = 1200 * M;
    public NeutronActivator(IMachineBlockEntity holder, Object... args) {
        super(holder, args);
        this.neutronEnergySubs = new ConditionalSubscriptionHandler(this, this::neutronEnergyUpdate, this::isFormed);
        this.moderateSubs = new ConditionalSubscriptionHandler(this, this::moderateUpdate, () -> eV > 0);
        this.absorptionSubs = new ConditionalSubscriptionHandler(this, this::absorptionUpdate, () -> eV > 0);
    }

    //////////////////////////////////////
    //***    Multiblock LifeCycle    ***//
    //////////////////////////////////////
    @Override
    public void onStructureFormed() {
        // Declare 'height' as a local variable if not used elsewhere
        height = 0;
        super.onStructureFormed();

        // Cache the Map access to avoid repeated calls
        var matchContext = getMultiblockState().getMatchContext();
        Map<Long, IO> ioMap = matchContext.getOrCreate("ioMap", Long2ObjectMaps::emptyMap);

        // Cache the result of getParts() to prevent repetitive calls
        List<IMultiPart> parts = getParts();
        for (IMultiPart part : parts) {
            IO io = ioMap.getOrDefault(part.self().getPos().asLong(), IO.BOTH);
            if (io == IO.NONE) continue;

            for (var handler : part.getRecipeHandlers()) {
                var handlerIO = handler.getHandlerIO();
                if (io != IO.BOTH && handlerIO != IO.BOTH && io != handlerIO) continue;
                if (handler.getCapability() == EURecipeCapability.CAP && handler instanceof IEnergyContainer) {
                    traitSubscriptions.add(handler.addChangedListener(neutronEnergySubs::updateSubscription));
                    traitSubscriptions.add(handler.addChangedListener(moderateSubs::updateSubscription));
                }
                if (handler.getCapability() == ItemRecipeCapability.CAP && handler instanceof IItemTransfer) {
                    if (handlerIO == IO.IN || handlerIO == IO.BOTH) {
                        traitSubscriptions.add(handler.addChangedListener(absorptionSubs::updateSubscription));
                    }
                }
            }
            if (part instanceof ItemBusPartMachine itemBusPartMachine) {
                busMachines = getOrDefault(busMachines, HashSet::new);
                busMachines.add(itemBusPartMachine);
            }
            if (part instanceof NeutronSensorMachine neutronSensorMachine) {
                sensorMachines = getOrDefault(sensorMachines, HashSet::new);
                sensorMachines.add(neutronSensorMachine);
            }
            if (part instanceof NeutronAcceleratorMachine neutronAccelerator) {
                acceleratorMachines = getOrDefault(acceleratorMachines, HashSet::new);
                acceleratorMachines.add(neutronAccelerator);
            }
            if (part instanceof HighSpeedPipeBlock) height++;
        }

        neutronEnergySubs.initialize(getLevel());
    }

    private static <T> T getOrDefault(@Nullable T value, Supplier<T> defaultSupplier) {
        if (value == null) return defaultSupplier.get();
        return value;
    }

    @Override
    public void onLoad() {
        super.onLoad();
        moderateSubs.initialize(getLevel());
    }

    @Override
    public void onStructureInvalid() {
        super.onStructureInvalid();
        height = 0;
        sensorMachines = null;
        busMachines = null;
        acceleratorMachines = null;
    }

    //////////////////////////////////////
    //***  Multiblock Subscriptions  ***//
    //////////////////////////////////////

    protected void neutronEnergyUpdate() {
        if (acceleratorMachines == null) return;

        boolean anyWorking = false;
        for (var accelerator : acceleratorMachines) {
            long increase = accelerator.consumeEnergy();
            if (increase > 0) {
                anyWorking = true;
                this.eV += (int) Math.round(Math.max(increase * getEfficiencyFactor(), 1));
            }
        }

        this.isWorking = anyWorking;

        if (this.eV > MAX_ENERGY) doExplosion(4 * 32);

        if (!isWorking) neutronEnergySubs.unsubscribe();
    }

    protected void moderateUpdate() {
        if (!isWorking && getOffsetTimer() % 20 == 0) {
            this.eV = Math.max(eV - 72 * K, 0);
        }
        if (this.eV < 0) this.eV = 0;

        if (!isFormed() || sensorMachines == null) return;
        sensorMachines.forEach(sensor -> sensor.update(eV));
    }

    protected void absorptionUpdate() {
        if (busMachines == null || eV <= 0) return;

        boolean hasSlower = false;
        for (var bus : busMachines) {
            var inv = bus.getInventory();
            var io = inv.getHandlerIO();
            if (io == IO.IN || io == IO.BOTH) {
                for (int i = 0; i < inv.getSlots(); i++) {
                    var dustBeryllium = ChemicalHelper.get(TagPrefix.dust, GTMaterials.Beryllium).getItem();
                    var dustGraphite = ChemicalHelper.get(TagPrefix.dust, GTMaterials.Graphite).getItem();
                    var stack = inv.getStackInSlot(i);
                    if (stack.is(dustBeryllium) || stack.is(dustGraphite)) {
                        hasSlower = true;
                        int consume = Math.min(Math.max(eV / (10 * M), 1), stack.getCount());
                        inv.extractItemInternal(i, consume, false);
                        this.eV -= 10 * M * consume;
                    }
                }
            }
        }

        if (!hasSlower) absorptionSubs.unsubscribe();
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
            textList.add(Component.translatable("gtnn.multiblock.neutronactivator.height", FormattingUtil.formatNumbers(height)));
            textList.add(Component.translatable("gtnn.multiblock.neutronactivator.efficiency", FormattingUtil.formatNumbers(getEfficiencyFactor() * 100)));
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
    //******   Multiblock Data   *******//
    //////////////////////////////////////
    private double getVelocityFactor() {
        return Math.pow(0.9, Math.max(height - 4, 0));
    }

    private double getEfficiencyFactor() {
        return Math.pow(0.95, Math.max(height - 4, 0));
    }
    @Override
    public @NotNull ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    //////////////////////////////////////
    //******     RECIPE LOGIC    *******//
    //////////////////////////////////////

    @Override
    public boolean alwaysTryModifyRecipe(){
        return true;
    }

    @Override
    protected @Nullable GTRecipe getRealRecipe(GTRecipe recipe) {
        var conditions = recipe.conditions.stream().filter(NeutronActivatorCondition.class::isInstance).toArray(NeutronActivatorCondition[]::new);
        GTRecipe newRecipe = recipe.copy();
        newRecipe.duration = (int) Math.round(Math.max(newRecipe.duration * getVelocityFactor(), 1));
        if (conditions.length > 0) {
            var condition = conditions[0];
            if (eV > (condition.getEvRange() / 10000) * 1000000 || eV < (condition.getEvRange() % 10000) * 1000000) {
                newRecipe.outputs.clear();
                newRecipe.outputs.put(ItemRecipeCapability.CAP, List.of(new Content(Ingredient.of(GTNNItems.RADIOACTIVE_WASTE), 1, 0, null, null)));
            }
        }
        return super.getRealRecipe(newRecipe);
    }

    public static boolean checkNeutronActivatorCondition(MetaMachine machine, GTRecipe recipe){
        if (machine instanceof NeutronActivator ) {
            return recipe.conditions.get(0) instanceof NeutronActivatorCondition;
        }
        return false;
    }
}
