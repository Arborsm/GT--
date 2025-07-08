package dev.arbor.gtnn.config;

import dev.arbor.gtnn.GTNN;
import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.Config;
import dev.toma.configuration.config.Configurable;
import dev.toma.configuration.config.format.ConfigFormats;

@Config(id = GTNN.MOD_ID)
public final class GTNNConfigHandler {

    public static GTNNConfigHandler INSTANCE = Configuration
            .registerConfig(GTNNConfigHandler.class, ConfigFormats.json())
            .getConfigInstance();
    @Configurable
    public ClientConfigs Client = new ClientConfigs();
    @Configurable
    public ServerConfigs Server = new ServerConfigs();

    public static class ServerConfigs {

        @Configurable
        @Configurable.Synchronized
        @Configurable.Comment({ "Enable Harder Platinum Line", "Default: true" })
        public boolean enableHarderPlatinumLine = true;
        @Configurable
        @Configurable.Synchronized
        @Configurable.Comment({ "Enable Harder Naquadah Line", "Default: true" })
        public boolean enableHarderNaquadahLine = true;
        @Configurable
        @Configurable.Synchronized
        @Configurable.Comment({ "Ban Create Fan Blasting", "Default: false" })
        public boolean banCreateFanBlasting = false;
        @Configurable
        @Configurable.Synchronized
        @Configurable.Comment({ "Click Fluid to Get Bucket of Fliud by Cheat", "Default: true" })
        public boolean makesEMIBetter = true;
        @Configurable
        @Configurable.Synchronized
        @Configurable.Comment({ "Enable Ender Fluid Link Cover", "Default: true" })
        public boolean isTurnOnEnderFluidCover = true;
        @Configurable
        @Configurable.Synchronized
        @Configurable.Comment({ "Enable Ender Item Link Cover", "Default: false" })
        public boolean isTurnOnEnderItemCover = false;
        @Configurable
        @Configurable.Synchronized
        @Configurable.Comment({ "Skyblock Mode", "Default: false" })
        public boolean skyblock = false;
        @Configurable
        @Configurable.Synchronized
        @Configurable.DecimalRange(min = 0F, max = 10.0F)
        @Configurable.Comment({ "GT Ores Generated Size Multiplier", "Default: 1.0F" })
        public float gtOresMultiplyNum = 1.0F;
        @Configurable
        @Configurable.Synchronized
        @Configurable.Range(min = 1, max = 10)
        @Configurable.Comment({ "* Times OreVeins in One Chunk", "Default: 1" })
        public int timesOreVeins = 1;

        ServerConfigs() {}
    }

    public static class ClientConfigs {

        @Configurable
        public ItemConfigs items = new ItemConfigs();
        @Configurable
        @Configurable.Comment({ "Use Extra Heart Renderer", "Default: false" })
        public boolean extraHeartRenderer = false;
        @Configurable
        @Configurable.Comment({ "Kill Toast", "Default: false" })
        public boolean killToast = false;
        @Configurable
        @Configurable.Comment({ "Add Chat Animation", "Default: false" })
        public boolean addChatAnimation = false;

        ClientConfigs() {}

        public static class ItemConfigs {

            @Configurable
            @Configurable.Comment({
                    "The subscript used to warp items",
                    "When true, use like \"I\" to subscript",
                    "Default: true"
            })
            public boolean perfRomanSubscript = true;
        }
    }
}
