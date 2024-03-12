package org.arbor.gtnn;

import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.arbor.gtnn.config.ConfigHandler;
import org.arbor.gtnn.init.CommonProxy;
import org.slf4j.Logger;

@Mod(GTNN.MODID)
public class GTNN {

    public static final String MODID = "gtnn";
    public static final Logger LOGGER = LogUtils.getLogger();

    public GTNN() {
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        eventBus.addGenericListener(MachineDefinition.class, GTNNRegistries::registerMachine);
        CommonProxy.init();
    }

    public static ConfigHandler.ClientConfigs getClientConfig() {
        return ConfigHandler.INSTANCE.Client;
    }

    public static ConfigHandler.ServerConfigs getServerConfig() {
        return ConfigHandler.INSTANCE.Server;
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MODID, FormattingUtil.toLowerCaseUnder(path));
    }

}
