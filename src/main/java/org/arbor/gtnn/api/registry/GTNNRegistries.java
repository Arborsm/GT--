package org.arbor.gtnn.api.registry;

import org.arbor.gtnn.GTNN;
import org.arbor.gtnn.api.registry.registrate.GTNNRegistrate;

public final class GTNNRegistries {
    public static final GTNNRegistrate REGISTRATE = GTNNRegistrate.create(GTNN.MODID);

    public GTNNRegistries() {
    }
}
