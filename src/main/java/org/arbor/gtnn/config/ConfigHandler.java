package org.arbor.gtnn.config;

import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.Config;
import dev.toma.configuration.config.Configurable;
import dev.toma.configuration.config.format.ConfigFormats;
import org.arbor.gtnn.GTNN;

@Config(id = GTNN.MODID)
public class ConfigHandler {
    public static ConfigHandler INSTANCE;
    public static void init() {
        INSTANCE = Configuration.registerConfig(ConfigHandler.class, ConfigFormats.json()).getConfigInstance();
    }
    @Configurable
    public ClientConfigs Client = new ClientConfigs();
    public static class ClientConfigs{
        @Configurable
        @Configurable.Comment({"Use Extra Heart Renderer", "Default: true"})
        public boolean ExtraHeartRenderer = true;
        ClientConfigs(){
        }
    }

}

