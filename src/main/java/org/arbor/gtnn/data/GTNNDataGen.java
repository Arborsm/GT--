package org.arbor.gtnn.data;

import com.tterrag.registrate.providers.ProviderType;
import org.arbor.gtnn.api.registry.GTNNRegistries;
import org.arbor.gtnn.data.lang.LangHandler;

public class GTNNDataGen {
    public static void init() {
        GTNNRegistries.REGISTRATE.addDataGenerator(ProviderType.LANG, LangHandler::init);
    }
}
