package org.arbor.gtnn.data;

import com.tterrag.registrate.providers.ProviderType;
import org.arbor.gtnn.api.registry.CNLangProvider;
import org.arbor.gtnn.data.lang.LangHandler;

import static org.arbor.gtnn.GTNNRegistries.REGISTRATE;

public class GTNNDataGen {
    public static final ProviderType<CNLangProvider> CN_LANG = ProviderType.register("cn_lang", (p, e) -> new CNLangProvider(p, e.getGenerator().getPackOutput()));
    public static void init() {
        REGISTRATE.addDataGenerator(ProviderType.LANG, LangHandler::enInitialize);
        REGISTRATE.addDataGenerator(CN_LANG, LangHandler::cnInitialize);
        REGISTRATE.addDataGenerator(ProviderType.BLOCK_TAGS, GTNNTags::initBlock);
    }

}
