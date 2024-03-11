package org.arbor.gtnn.data.lang;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.utils.FormattingUtil;
import com.tterrag.registrate.providers.RegistrateLangProvider;
import org.arbor.gtnn.GTNN;
import org.arbor.gtnn.api.registry.CNLangProvider;
import org.jetbrains.annotations.Nullable;

public class LangHandler {
    public static final LangHandler INSTANCE = new LangHandler();
    private CNLangProvider cnLangProvider;
    private RegistrateLangProvider enLangProvider;

    public static void cnInitialize(CNLangProvider provider) {
        INSTANCE.cnLangProvider = provider;
        init();
    }

    public static void enInitialize(RegistrateLangProvider provider) {
        INSTANCE.enLangProvider = provider;
        init();
    }

    public static void tsl(String key, String cn, String en) {
        INSTANCE.translate(key, cn, en);
    }

    public static void tsl(String key, String cn) {
        INSTANCE.translate(key, cn);
    }

    public static void translateOreVein(String key, String cn) {
        tsl(key, cn, FormattingUtil.toEnglishName(key));
    }

    public static void translateMaterial(Material material, String cn, @Nullable String en) {
        if (en == null) {
            if (INSTANCE.enLangProvider != null && INSTANCE.cnLangProvider == null)
                translateMaterial(INSTANCE.enLangProvider, material);
        } else {
            try {
                INSTANCE.enLangProvider.add("material.gtceu." + material.getName(), en);
            } catch (NullPointerException e) {
                GTNN.LOGGER.error("Failed to translate material(EN)", e);
            }
        }
        if (INSTANCE.cnLangProvider != null) INSTANCE.cnLangProvider.translateMaterial(material, cn);
    }

    public static void translateMaterial(Material material, String cn) {
        translateMaterial(material, cn, null);
    }

    private static void translateMaterial(RegistrateLangProvider provider, Material material) {
        try {
            provider.add("material.gtceu." + material.getName(), FormattingUtil.toEnglishName(material.getName()));
        } catch (NullPointerException e) {
            GTNN.LOGGER.error("Failed to translate material(EN)", e);
        }
    }

    public static void init() {
        MachineLang.init();
        MaterialLang.init();
        MiscLang.init();
    }

    public void translate(String key, String cn, String en) {
        if (enLangProvider != null && cnLangProvider == null) {
            enLangProvider.add(key, en);
        }
        if (cnLangProvider != null) cnLangProvider.add(key, cn);
    }

    public void translate(String key, String cn) {
        if (cnLangProvider != null) cnLangProvider.add(key, cn);
    }
}
