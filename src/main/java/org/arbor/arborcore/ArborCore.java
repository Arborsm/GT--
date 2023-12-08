package org.arbor.arborcore;

import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.lowdragmc.lowdraglib.Platform;
import com.lowdragmc.lowdraglib.gui.factory.UIFactory;
import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.arbor.arborcore.data.ArborRegistration;
import org.arbor.arborcore.kjs.kjsWidgetRegistry;
import org.slf4j.Logger;

import java.io.File;

@Mod(ArborCore.MODID)
public class ArborCore {
    public static File location;
    public static final String MODID = "arbor";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ArborCore() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        ArborRegistration.BLOCKS.register(modEventBus);
        ArborRegistration.ITEMS.register(modEventBus);
        UIFactory.register(kjsWidgetRegistry.INSTANCE);
    }

    public static File getArborDir(){
        if (location == null){
            location = new File(Platform.getGamePath().toFile(), "Arbor");
            if (location.mkdir()){
                ArborCore.LOGGER.info("create ArborCore's folder");
            }
        }
        return location;
    }

    @SubscribeEvent
    public void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ArborRegistration.ITNT_ITEM);
        }
    }

    public static ResourceLocation id(String path) {
        return new ResourceLocation(MODID, FormattingUtil.toLowerCaseUnder(path));
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        // Some common setup code
        LOGGER.info("Loading ArborCore");
    }
}
