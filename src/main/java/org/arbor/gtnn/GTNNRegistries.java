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

@Mod.EventBusSubscriber(modid = GTNN.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class GTNNRegistries {
    public static final GTNNRegistrate REGISTRATE = GTNNRegistrate.create(GTNN.MODID);
    public static MaterialRegistry MATERIAL_REGISTRY;

    public static PackResources getAllPackResources(){
        File tempFile = null;
        try {
            InputStream inputStream = GTNNRegistries.class.getResourceAsStream("/data/gtnn/ad_astra.zip");
            tempFile = File.createTempFile("temp", ".tmp");
            assert inputStream != null;
            FileUtils.copyInputStreamToFile(inputStream, tempFile);
            inputStream.close();
        } catch (IOException e) {
            GTNN.LOGGER.error("getAllPackResources() wrong!", e);
        }
        if (tempFile != null) {
            return new FilePackResources(tempFile.getPath(), tempFile, false);
        }
        return null;
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
