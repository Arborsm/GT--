package org.arbor.arborcore;

import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.arbor.arborcore.config.ConfigHandler;
import org.slf4j.Logger;

@Mod(ArborCore.MODID)
public class ArborCore {

    public static final String MODID = "arbor";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ArborCore() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        ConfigHandler.init();
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MODID, FormattingUtil.toLowerCaseUnder(path));
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("Loading ArborCore");
    }
}
