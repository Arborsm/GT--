package org.arbor.arborcore.init;

import com.gregtechceu.gtceu.api.registry.GTRegistries;
import org.arbor.arborcore.api.recipe.NeutronActivatorCondition;
import org.arbor.arborcore.api.recipe.PlantCasingCondition;
import org.arbor.arborcore.api.registry.ArborRegistries;
import org.arbor.arborcore.data.ArborBlocks;
import org.arbor.arborcore.data.ArborItems;
import org.arbor.arborcore.data.ArborMachines;

public class CommonProxy {
    public static void init() {
        ArborItems.init();
        ArborBlocks.init();
        ArborMachines.init();
        GTRegistries.RECIPE_CONDITIONS.register(PlantCasingCondition.INSTANCE.getType(), PlantCasingCondition.class);
        GTRegistries.RECIPE_CONDITIONS.register(NeutronActivatorCondition.INSTANCE.getType(), NeutronActivatorCondition.class);
        ArborRegistries.REGISTRATE.registerRegistrate();
    }
}
