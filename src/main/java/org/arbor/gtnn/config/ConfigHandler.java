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
    @Configurable
    public ServerConfigs Server = new ServerConfigs();
    public static class ServerConfigs{
        @Configurable
        @Configurable.Comment({"Enable Harder Platinum Line", "Default: true"})
        public boolean enableHarderPlatinumLine = true;
        @Configurable
        @Configurable.Comment({"Enable Harder Naquadah Line", "Default: true"})
        public boolean enableHarderNaquadahLine = true;
        ServerConfigs(){
        }
    }
    public static class ClientConfigs{
        @Configurable
        @Configurable.Comment({"Use Extra Heart Renderer", "Default: true"})
        public boolean extraHeartRenderer = true;
        ClientConfigs(){
        }
    }

}

