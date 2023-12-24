package org.arbor.arborcore.config;

import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.Config;
import dev.toma.configuration.config.Configurable;
import dev.toma.configuration.config.format.ConfigFormats;
import org.arbor.arborcore.ArborCore;

@Config(id = ArborCore.MODID)
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

