package org.arbor.gtnn.mixin;

import com.lowdragmc.lowdraglib.core.mixins.MixinPluginShared;
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
            return MixinPluginShared.isClassFound("dev.emi.emi.api.EmiPlugin");
        } else if (mixinClassName.contains("org.arbor.gtnn.mixin.create.CreateFanMixin")) {
            return MixinPluginShared.isClassFound("com.simibubi.create.compat.Mods");
        } else if (mixinClassName.contains("org.arbor.gtnn.mixin.AdastraMixin")) {
            return MixinPluginShared.isClassFound("earth.terrarium.ad_astra.forge.AdAstraForge");
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
