package dev.arbor.gtnn;

import dev.arbor.gtnn.init.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(GTNN.MOD_ID)
public class GTNNForge {
    public GTNNForge() {
        GTNN.init();
    }
}
