package org.arbor.gtnn.data;

import com.gregtechceu.gtceu.api.data.worldgen.IWorldGenLayer;
import com.gregtechceu.gtceu.api.data.worldgen.WorldGeneratorUtils;
import lombok.Getter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

public class GTNNWorld {
    protected static final ResourceLocation TWILIGHT_FOREST = new ResourceLocation("twilightforest", "twilight_forest");
    protected static final ResourceLocation MOON = new ResourceLocation("ad_astra", "moon");
    protected static final ResourceLocation VENUS = new ResourceLocation("ad_astra", "venus");
    protected static final ResourceLocation MARS = new ResourceLocation("ad_astra", "mars");
    protected static final ResourceLocation MERCURY = new ResourceLocation("ad_astra", "mercury");
    protected static final ResourceLocation GLACIO = new ResourceLocation("ad_astra", "glacio");
    public enum GTNNWorldGenLayers implements IWorldGenLayer, StringRepresentable {
        AD("ad", new TagMatchTest(GTNNTags.AD_ASTRA_STONES), Set.of(MOON, MARS, MERCURY, VENUS, GLACIO)),
        TF("tf", new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES), Set.of(TWILIGHT_FOREST));

        private final String name;
        @Getter
        private final Set<ResourceLocation> levels;
        @Getter
        private final RuleTest target;

        GTNNWorldGenLayers(String name, RuleTest target, Set<ResourceLocation> levels) {
            this.name = name;
            this.target = target;
            this.levels = levels;
            WorldGeneratorUtils.WORLD_GEN_LAYERS.put(name, this);
        }

        public static void init(){
        }

        @Override
        public boolean isApplicableForLevel(ResourceLocation level) {
            return levels.contains(level);
        }

        @Override
        @NotNull
        public String getSerializedName() {
            return name;
        }
    }
}
