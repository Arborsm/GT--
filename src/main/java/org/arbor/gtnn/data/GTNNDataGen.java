package org.arbor.gtnn.data;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.arbor.gtnn.GTNN;
import org.arbor.gtnn.data.lang.LangHandlerCN;

@Mod.EventBusSubscriber(modid = GTNN.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class GTNNDataGen {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(event.includeClient(), new LangHandlerCN(generator, "en_us"));
        // generator.addProvider(event.includeClient(), new LangHandlerEN(generator, "en_us"));
    }
}
