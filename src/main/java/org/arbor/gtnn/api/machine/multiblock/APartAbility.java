package org.arbor.gtnn.api.machine.multiblock;

import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;

public class APartAbility extends PartAbility {
    public static final PartAbility NEUTRON_ACCELERATOR = new PartAbility("neutron_accelerator");
    public APartAbility(String name) {
        super(name);
    }
}
