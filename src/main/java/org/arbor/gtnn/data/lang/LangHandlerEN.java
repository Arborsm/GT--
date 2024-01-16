package org.arbor.gtnn.data.lang;

import com.tterrag.registrate.providers.RegistrateLangProvider;
import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.format.ConfigFormats;
import dev.toma.configuration.config.value.ConfigValue;
import dev.toma.configuration.config.value.ObjectValue;
import org.arbor.gtnn.GTNN;
import org.arbor.gtnn.config.ConfigHandler;
import org.arbor.gtnn.data.lang.en.MachineLang;
import org.arbor.gtnn.data.lang.en.MiscLang;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LangHandlerEN {
    public static void init(RegistrateLangProvider provider){
        MachineLang.init(provider);
        MiscLang.init(provider);
        dfs(new HashSet<>(), Configuration.registerConfig(ConfigHandler.class, ConfigFormats.yaml()).getValueMap(), provider);
    }

    private static void dfs(Set<String> added, Map<String, ConfigValue<?>> map, RegistrateLangProvider provider) {
        for (var entry : map.entrySet()) {
            var id = entry.getValue().getId();
            if (added.add(id)) {
                provider.add(String.format("config.%s.option.%s", GTNN.MODID, id), id);
            }
            if (entry.getValue() instanceof ObjectValue objectValue) {
                dfs(added, objectValue.get(), provider);
            }
        }
    }
}
