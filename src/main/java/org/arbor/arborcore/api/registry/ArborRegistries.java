package org.arbor.arborcore.api.registry;

import com.lowdragmc.lowdraglib.Platform;
import net.minecraft.core.RegistryAccess;
import org.arbor.arborcore.api.registry.registrate.ArborRegistrate;

public final class ArborRegistries {
    public static final ArborRegistrate REGISTRATE = ArborRegistrate.create("arbor");

    public ArborRegistries() {
    }

    public static RegistryAccess builtinRegistry() {
        return Platform.getFrozenRegistry();
    }
}
