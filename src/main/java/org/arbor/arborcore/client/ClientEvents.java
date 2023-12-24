package org.arbor.arborcore.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.arbor.arborcore.ArborCore;

@Mod.EventBusSubscriber(modid = ArborCore.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientEvents {
    @SubscribeEvent
    static void FMLCommonSetupEvent(FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new ExtraHeartRenderHandler());
    }
}
