package org.arbor.gtnn.data;

import com.gregtechceu.gtceu.api.data.tag.TagUtil;
import com.tterrag.registrate.providers.RegistrateTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.arbor.gtnn.GTNNIntegration;
import org.arbor.gtnn.data.tags.AdAstraTag;

public class GTNNTags {
    public static void initBlock(RegistrateTagsProvider<Block> provider) {
        if (GTNNIntegration.isAdAstraLoaded()){
            AdAstraTag.init(provider);
        }
    }
    public static final TagKey<Block> AD_ASTRA_STONES = TagUtil.createBlockTag("ad_astra_stones");
}
