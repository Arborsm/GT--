package org.arbor.gtnn;

import com.gregtechceu.gtceu.api.GTCEuAPI;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.event.MaterialRegistryEvent;
import com.gregtechceu.gtceu.api.data.chemical.material.registry.MaterialRegistry;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.FilePackResources;
import net.minecraft.server.packs.PackResources;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.io.FileUtils;
import org.arbor.gtnn.api.registry.GTNNRegistrate;
import org.arbor.gtnn.data.GTNNMachines;
import org.arbor.gtnn.data.GTNNMaterials;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = GTNN.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class GTNNRegistries {
    public static final GTNNRegistrate REGISTRATE = GTNNRegistrate.create(GTNN.MODID);
    public static MaterialRegistry MATERIAL_REGISTRY;

    public static List<PackResources> getAllPackResources() {
        List<PackResources> packResources = new ArrayList<>();
        try {
            InputStream inputStream = GTNNRegistries.class.getResourceAsStream("/data/gtnn/ad_astra.zip");
            File tempFile = File.createTempFile("temp", ".tmp");
            assert inputStream != null;
            FileUtils.copyInputStreamToFile(inputStream, tempFile);
            inputStream.close();
            packResources.add(new FilePackResources(tempFile.getName(), tempFile, false));
        } catch (IOException e) {
            GTNN.LOGGER.error("ad_astra.zip wrong!", e);
        }
        return packResources;
    }

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
