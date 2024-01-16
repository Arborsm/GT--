package org.arbor.gtnn;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialRegistryEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.registry.MaterialRegistry;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.arbor.gtnn.api.registry.GTNNRegistrate;
import org.arbor.gtnn.data.GTNNMachines;
import org.arbor.gtnn.data.GTNNMaterials;

@Mod.EventBusSubscriber(modid = GTNN.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class GTNNRegistries {
    public static final GTNNRegistrate REGISTRATE = GTNNRegistrate.create(GTNN.MODID);
    public static MaterialRegistry MATERIAL_REGISTRY;

    public static void registerMachine(GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition> ignoredEvent){
        GTNNMachines.init();
    }

    @SubscribeEvent
    public static void registerMaterialRegistryEvent(MaterialRegistryEvent event) {
        MATERIAL_REGISTRY = GTCEuAPI.materialManager.createRegistry(GTNN.MODID);
    }

    @SubscribeEvent
    public static void registerMaterials(MaterialEvent event) {
        GTNNMaterials.init();
    }

    public GTNNRegistries() {
    }
}
