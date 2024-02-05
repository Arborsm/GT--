package org.arbor.gtnn;

import com.lowdragmc.lowdraglib.LDLib;

public class GTNNIntegration {
    public static boolean isAdAstraLoaded() {
        return LDLib.isModLoaded("ad_astra");
    }
    public static boolean isTwilightForestLoaded() {
        return LDLib.isModLoaded("twilightforest");
    }
    public static boolean isBotaniaLoaded() {
        return LDLib.isModLoaded("botania");
    }
    public static boolean isForbiddenArcanusLoaded() {
        return LDLib.isModLoaded("forbidden_arcanus");
    }
    public static boolean isCreateLoaded() {
        return LDLib.isModLoaded("create");
    }
}