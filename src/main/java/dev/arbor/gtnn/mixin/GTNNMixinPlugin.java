package dev.arbor.gtnn.mixin;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class GTNNMixinPlugin implements IMixinConfigPlugin {

    @Override
    public void onLoad(String mixinPackage) {}

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    // Checks class presence via the classpath resource
    private static boolean isClassFound(String className) {
        return GTNNMixinPlugin.class.getClassLoader().getResource(className.replace('.', '/') + ".class") != null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        if (mixinClassName.contains("dev.arbor.gtnn.mixin.emi")) {
            return isClassFound("dev.emi.emi.api.EmiPlugin");
        } else if (mixinClassName.contains("dev.arbor.gtnn.mixin.create")) {
            return isClassFound("com.simibubi.create.compat.Mods");
        } else if (mixinClassName.contains("dev.arbor.gtnn.mixin.adastra")) {
            return isClassFound("earth.terrarium.ad_astra.forge.AdAstraForge");
        } else if (mixinClassName.contains("dev.arbor.gtnn.client.mixin.ShimmerMixin")) {
            return isClassFound("com.lowdragmc.shimmer.Utils");
        } else if (mixinClassName.contains("GTRecipeWidgetMixin")) {
            return !isClassFound("io.github.cpearl0.ctnhcore.CTNHCore");
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {}

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {}
}
