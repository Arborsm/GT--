package org.arbor.gtnn.api.machine.multiblock;

import com.gregtechceu.gtceu.api.machine.multiblock.PartAbility;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class APartAbility extends PartAbility {
    public static final PartAbility NEUTRON_ACCELERATOR = new PartAbility("neutron_accelerator");
    public static final PartAbility NEUTRON_SENSOR = new PartAbility("neutron_sensor");
    public APartAbility(String name) {
        super(name);
    }

    public static <T> T getOrDefault(@Nullable T value, Supplier<T> defaultSupplier) {
        if (value == null) return defaultSupplier.get();
        return value;
    }
}
