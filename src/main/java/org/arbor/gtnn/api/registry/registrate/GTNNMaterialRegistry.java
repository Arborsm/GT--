package org.arbor.gtnn.api.registry.registrate;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialRegistryEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.registry.MaterialRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.arbor.gtnn.GTNN;

public class GTNNMaterialRegistry {
    public static MaterialRegistry MATERIAL_REGISTRY;

    @SubscribeEvent
    public static void registerMaterialRegistryEvent(MaterialRegistryEvent event) {
        MATERIAL_REGISTRY = GTCEuAPI.materialManager.createRegistry(GTNN.MODID);
    }
}
