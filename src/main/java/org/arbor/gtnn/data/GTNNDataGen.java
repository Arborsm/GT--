package org.arbor.gtnn.data;

import com.tterrag.registrate.providers.ProviderType;
import org.arbor.gtnn.api.registry.CNLangProvider;
import org.arbor.gtnn.data.lang.LangHandlerCN;
import org.arbor.gtnn.data.lang.LangHandlerEN;

import static org.arbor.gtnn.GTNNRegistries.REGISTRATE;

public class GTNNDataGen {
    public static final ProviderType<CNLangProvider> CN_LANG = ProviderType.register("cn_lang", (p, e) -> new CNLangProvider(p, e.getGenerator().getPackOutput()));
    public static void init() {
        REGISTRATE.addDataGenerator(ProviderType.LANG, LangHandlerEN::init);
        REGISTRATE.addDataGenerator(CN_LANG, LangHandlerCN::init);
    }

}
