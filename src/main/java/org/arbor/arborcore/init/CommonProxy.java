package org.arbor.arborcore.init;

import com.gregtechceu.gtceu.api.registry.GTRegistries;
import org.arbor.arborcore.api.recipe.PlantCasingCondition;
import org.arbor.arborcore.data.ArborMachines;

public class CommonProxy {
    public static void init() {
        ArborMachines.init();
        GTRegistries.RECIPE_CONDITIONS.register(PlantCasingCondition.INSTANCE.getType(), PlantCasingCondition.class);
    }
}
