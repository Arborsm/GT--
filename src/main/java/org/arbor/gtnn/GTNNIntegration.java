package org.arbor.gtnn;

import com.lowdragmc.lowdraglib.LDLib;
import net.minecraftforge.fml.loading.FMLLoader;

public class GTNNIntegration {
    public static boolean isAdAstraLoaded() {
        return isLoaded("ad_astra");
    }
    public static boolean isTwilightForestLoaded() {
        return isLoaded("twilightforest");
    }
    public static boolean isBotaniaLoaded() {
        return isLoaded("botania");
    }
    public static boolean isForbiddenArcanusLoaded() {
        return isLoaded("forbidden_arcanus");
    }
    public static boolean isCreateLoaded() {
        return isLoaded("create");
    }

    private static boolean isLoaded(String mod) {
        return LDLib.isModLoaded(mod) || !FMLLoader.isProduction();
    }

}