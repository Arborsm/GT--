package org.arbor.gtnn.api.machine.multiblock.part;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.trait.NotifiableEnergyContainer;
import com.gregtechceu.gtceu.common.machine.multiblock.part.EnergyHatchPartMachine;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.ThreadLocalRandom;

@Getter@Setter
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class NeutronAcceleratorMachine extends EnergyHatchPartMachine {
    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER = new ManagedFieldHolder(NeutronAcceleratorMachine.class, EnergyHatchPartMachine.MANAGED_FIELD_HOLDER);

    public NeutronAcceleratorMachine(IMachineBlockEntity holder, int tier, Object... args) {
        super(holder, tier, IO.IN, 1, args);
    }

    //////////////////////////////////////
    //*****     Initialization    ******//
    //////////////////////////////////////
    @Override
    protected NotifiableEnergyContainer createEnergyContainer(Object... args) {
        NotifiableEnergyContainer container;
        container = NotifiableEnergyContainer.receiverContainer(this, GTValues.V[tier] * 72L * amperage, GTValues.V[tier], amperage);
        container.setSideInputCondition((s) -> s == this.getFrontFacing() && this.isWorkingEnabled());
        container.setCapabilityValidator((s) -> s == null || s == this.getFrontFacing());
        return container;
    }

    public long consumeEnergy(){
        if (this.isWorkingEnabled() && this.energyContainer.getEnergyStored() > 0){
            return Math.abs(this.energyContainer.changeEnergy(-getMaxEUConsume())) * (10 + ThreadLocalRandom.current().nextInt(11));
        } else{
            return 0L;
        }
    }

    //////////////////////////////////////
    //**********     GUI     ***********//
    //////////////////////////////////////

    @Override
    public boolean shouldOpenUI(Player player, InteractionHand hand, BlockHitResult hit) {
        return true;
    }

    //////////////////////////////////////
    //**********     Data     **********//
    //////////////////////////////////////

    public long getMaxEUConsume() {
        return Math.round(GTValues.V[tier] * 0.8);
    }

    @Override
    public ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }
}
