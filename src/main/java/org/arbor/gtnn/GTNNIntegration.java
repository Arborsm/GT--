package org.arbor.gtnn;

import com.lowdragmc.lowdraglib.LDLib;

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

    public static boolean isGreateLoaded() {
        return isLoaded("greate");
    }
    public static boolean isSupplementariesLoaded() {
        return isLoaded("supplementaries");
    }

    public static boolean isCCTweakedLoaded() {
        return isLoaded("computercraft");
    }

    private static boolean isLoaded(String mod) {
        return LDLib.isModLoaded(mod);
    }

}