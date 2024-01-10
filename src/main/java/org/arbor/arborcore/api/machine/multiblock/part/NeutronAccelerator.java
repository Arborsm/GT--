package org.arbor.arborcore.api.machine.multiblock.part;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.part.EnergyHatchPartMachine;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import lombok.Getter;
import lombok.Setter;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.ParametersAreNonnullByDefault;

@Getter@Setter
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class NeutronAccelerator extends EnergyHatchPartMachine {
    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER;
    private boolean isActive;

    @Override
    public boolean shouldOpenUI(Player player, InteractionHand hand, BlockHitResult hit) {
        return true;
    }

    public NeutronAccelerator(IMachineBlockEntity holder, int tier, Object... args) {
        super(holder, tier, IO.IN, 1, args);
    }

    public int getMaxEUConsume() {
        return (int) (GTValues.V[tier] * 8 / 10);
    }

    @Override
    public  ManagedFieldHolder getFieldHolder() {
        return MANAGED_FIELD_HOLDER;
    }

    public void  energyTick(){
        if (!this.getLevel().isClientSide){
            if (this.energyContainer.getEnergyStored() >= getMaxEUConsume() && this.isWorkingEnabled()){
                this.energyContainer.setEnergyStored(this.energyContainer.getEnergyStored() - getMaxEUConsume());
                isActive = true;
            } else{
                isActive = false;
            }
        }
    }

    static {
        MANAGED_FIELD_HOLDER = new ManagedFieldHolder(NeutronAccelerator.class, TieredIOPartMachine.MANAGED_FIELD_HOLDER);
    }
}
