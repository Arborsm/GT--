package dev.arbor.gtnn.data.materials

import com.gregtechceu.gtceu.api.data.tag.TagPrefix
import dev.arbor.gtnn.data.GTNNMaterials
import vazkii.botania.common.block.BotaniaBlocks
import vazkii.botania.common.item.BotaniaItems

object BotaniaMaterialsModification {
    fun init() {
        TagPrefix.ingot.setIgnored(GTNNMaterials.TerraSteel, { BotaniaItems.terrasteel })
        TagPrefix.block.setIgnoredBlock(GTNNMaterials.TerraSteel, BotaniaBlocks.terrasteelBlock)
        TagPrefix.nugget.setIgnored(GTNNMaterials.TerraSteel, { BotaniaItems.terrasteelNugget })

        TagPrefix.ingot.setIgnored(GTNNMaterials.ManaSteel, { BotaniaItems.manaSteel })
        TagPrefix.block.setIgnoredBlock(GTNNMaterials.ManaSteel, BotaniaBlocks.manasteelBlock)
        TagPrefix.nugget.setIgnored(GTNNMaterials.ManaSteel, { BotaniaItems.manasteelNugget })

        TagPrefix.ingot.setIgnored(GTNNMaterials.Elementium, { BotaniaItems.elementium })
        TagPrefix.block.setIgnoredBlock(GTNNMaterials.Elementium, BotaniaBlocks.elementiumBlock)
        TagPrefix.nugget.setIgnored(GTNNMaterials.Elementium, { BotaniaItems.elementiumNugget })
    }
}
