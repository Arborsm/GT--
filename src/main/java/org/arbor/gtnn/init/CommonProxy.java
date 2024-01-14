package org.arbor.gtnn.init;

import com.gregtechceu.gtceu.api.registry.GTRegistries;
import org.arbor.gtnn.api.recipe.NeutronActivatorCondition;
import org.arbor.gtnn.api.recipe.PlantCasingCondition;
import org.arbor.gtnn.api.registry.GTNNRegistries;
import org.arbor.gtnn.data.GTNNBlocks;
import org.arbor.gtnn.data.GTNNItems;
import org.arbor.gtnn.data.GTNNMachines;

public class CommonProxy {
    public static void init() {
        GTNNItems.init();
        GTNNBlocks.init();
        GTNNMachines.init();
        GTRegistries.RECIPE_CONDITIONS.register(PlantCasingCondition.INSTANCE.getType(), PlantCasingCondition.class);
        GTRegistries.RECIPE_CONDITIONS.register(NeutronActivatorCondition.INSTANCE.getType(), NeutronActivatorCondition.class);
        GTNNRegistries.REGISTRATE.registerRegistrate();
    }
}
