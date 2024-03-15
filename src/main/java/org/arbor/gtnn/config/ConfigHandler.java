package org.arbor.gtnn.config;

import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.Config;
import dev.toma.configuration.config.Configurable;
import dev.toma.configuration.config.format.ConfigFormats;
import org.arbor.gtnn.GTNN;

@Config(id = GTNN.MODID)
public class ConfigHandler {
    public static ConfigHandler INSTANCE = Configuration.registerConfig(ConfigHandler.class, ConfigFormats.json()).getConfigInstance();
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
        @Configurable
        @Configurable.Comment({"Ban Create Fan Blasting", "Default: false"})
        public boolean banCreateFanBlasting = false;
        @Configurable
        @Configurable.Comment({"Makes EMI Better", "Default: true"})
        public boolean makesEMIBetter = true;
        ServerConfigs(){
        }
    }
    public static class ClientConfigs{
        @Configurable
        @Configurable.Comment({"Use Extra Heart Renderer", "Default: false"})
        public boolean extraHeartRenderer = false;
        @Configurable
        @Configurable.Comment({"Kill Toast", "Default: false"})
        public boolean killToast = false;
        @Configurable
        @Configurable.Comment({"Add Chat Animation", "Default: false"})
        public boolean addChatAnimation = false;

        @Configurable
        @Configurable.Comment({"Enable Remake GTM EMI Integration", "Default: true"})
        public boolean enableRemakeGTMEMI = true;
        ClientConfigs(){
        }
    }

}

