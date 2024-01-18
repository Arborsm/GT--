package org.arbor.gtnn.mixin.adastra;

import com.gregtechceu.gtceu.config.ConfigHolder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

import static earth.terrarium.adastra.common.registry.ModBlocks.*;

@Mixin(OreConfiguration.class)
public class OreConfigurationMixin {
    @Unique
    private static final List<Block> EXCLUDED_BLOCKS = List.of(
            MOON_CHEESE_ORE.get(), MOON_DESH_ORE.get(), DEEPSLATE_DESH_ORE.get(), MOON_IRON_ORE.get(), MOON_ICE_SHARD_ORE.get(), DEEPSLATE_ICE_SHARD_ORE.get()
    );
    @ModifyVariable(method = "<init>(Ljava/util/List;IF)V", at = @At("HEAD"), index = 1, argsOnly = true)
    private static List<OreConfiguration.TargetBlockState> gtceu$init(List<OreConfiguration.TargetBlockState> targetStates) {
        if (ConfigHolder.INSTANCE == null || !ConfigHolder.INSTANCE.worldgen.oreVeins.removeVanillaOreGen)
            return targetStates;

        return targetStates.stream()
                .filter(targetState -> !EXCLUDED_BLOCKS.contains(targetState.state.getBlock()))
                .toList();
    }
}
