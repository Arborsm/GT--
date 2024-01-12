package org.arbor.arborcore.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.arbor.arborcore.ArborCore;
import org.arbor.arborcore.data.lang.LangHandlerCN;

@Mod.EventBusSubscriber(modid = ArborCore.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ArborDataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(event.includeClient(), new LangHandlerCN(generator, "en_us"));
        // generator.addProvider(event.includeClient(), new LangHandlerEN(generator, "en_us"));
    }
}
