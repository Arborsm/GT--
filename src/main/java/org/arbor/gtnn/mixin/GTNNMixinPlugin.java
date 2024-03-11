package org.arbor.gtnn.mixin;

import com.lowdragmc.lowdraglib.core.mixins.MixinPluginShared;
import org.arbor.gtnn.config.ConfigHandler;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class GTNNMixinPlugin implements IMixinConfigPlugin {
    @Override
    public void onLoad(String mixinPackage) {
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.contains("org.arbor.gtnn.mixin.emi")) {
            return MixinPluginShared.isClassFound("dev.emi.emi.api.EmiPlugin")
                    && ConfigHandler.INSTANCE.Server.makesEMIBetter;
        } else if (mixinClassName.contains("org.arbor.gtnn.mixin.create")) {
            return MixinPluginShared.isClassFound("com.simibubi.create.compat.Mods")
                    && ConfigHandler.INSTANCE.Server.banCreateFanBlasting;
        } else if (mixinClassName.contains("org.arbor.gtnn.mixin.adastra")) {
            return MixinPluginShared.isClassFound("earth.terrarium.ad_astra.forge.AdAstraForge");
        } else if (mixinClassName.contains("org.arbor.gtnn.mixin.client.ShimmerMixin")){
            return MixinPluginShared.isClassFound("com.lowdragmc.shimmer.Utils");
        } else if (mixinClassName.contains("org.arbor.gtnn.mixin.client.ToastKiller")) {
            return ConfigHandler.INSTANCE.Client.killToast;
        } else if (mixinClassName.contains("org.arbor.gtnn.mixin.client.ChatMixin")) {
            return ConfigHandler.INSTANCE.Client.addChatAnimation;
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
