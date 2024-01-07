package org.arbor.arborcore.api.machine.multiblock.part;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.capability.recipe.IO;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine;
import com.gregtechceu.gtceu.common.machine.multiblock.part.EnergyHatchPartMachine;
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder;
import net.minecraft.MethodsReturnNonnullByDefault;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class NeutronAccelerator extends EnergyHatchPartMachine {
    protected static final ManagedFieldHolder MANAGED_FIELD_HOLDER;

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

    @Override
    public void onLoad() {
       super.onLoad();
       if (this.isRemote()){
           if (this.energyContainer.getEnergyStored() >= getMaxEUConsume() && this.isWorkingEnabled()){
               this.energyContainer.setEnergyStored(this.energyContainer.getEnergyStored() - getMaxEUConsume());
           }
       }
    }

    static {
        MANAGED_FIELD_HOLDER = new ManagedFieldHolder(NeutronAccelerator.class, TieredIOPartMachine.MANAGED_FIELD_HOLDER);
    }
}
