package org.arbor.gtnn.api.registry;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialRegistryEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.registry.MaterialRegistry;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.arbor.gtnn.GTNN;
import org.arbor.gtnn.api.registry.registrate.GTNNRegistrate;
import org.arbor.gtnn.data.GTNNMachines;

@Mod.EventBusSubscriber(modid = GTNN.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class GTNNRegistries {
    public static final GTNNRegistrate REGISTRATE = GTNNRegistrate.create(GTNN.MODID);
    public static MaterialRegistry MATERIAL_REGISTRY;

    @SubscribeEvent
    public static void registerMachine(GTCEuAPI.RegisterEvent<ResourceLocation, MachineDefinition> event){
        GTNNMachines.init();
    }

    @SubscribeEvent
    public static void registerMaterialRegistryEvent(MaterialRegistryEvent event) {
        MATERIAL_REGISTRY = GTCEuAPI.materialManager.createRegistry(GTNN.MODID);
    }

    public GTNNRegistries() {
    }
}
