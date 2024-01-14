package org.arbor.gtnn;

import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.arbor.gtnn.config.ConfigHandler;
import org.slf4j.Logger;

@Mod(GTNN.MODID)
public class GTNN {

    public static final String MODID = "gtnn";
    public static final Logger LOGGER = LogUtils.getLogger();

    public GTNN() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        ConfigHandler.init();
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MODID, FormattingUtil.toLowerCaseUnder(path));
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Loading GTNN");
    }
}
