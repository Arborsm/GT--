package dev.arbor.gtnn

import com.gregtechceu.gtceu.utils.FormattingUtil
import dev.arbor.gtnn.config.GTNNConfigHandler
import dev.arbor.gtnn.init.ClientProxy
import dev.arbor.gtnn.init.CommonProxy
import net.minecraft.resources.ResourceLocation
import net.minecraftforge.fml.DistExecutor
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import java.util.function.Supplier


object GTNN {
    const val MOD_ID = "gtnn"

    val LOGGER: Logger by lazy { LogManager.getLogger(MOD_ID) }

    @JvmStatic
    fun init() {
        DistExecutor.unsafeRunForDist({
            Supplier {
                ClientProxy()
            }
        }, {
            Supplier {
                CommonProxy()
            }
        }).init()
    }

    fun getClientConfig(): GTNNConfigHandler.ClientConfigs {
        return GTNNConfigHandler.INSTANCE.Client
    }

    fun getServerConfig(): GTNNConfigHandler.ServerConfigs {
        return GTNNConfigHandler.INSTANCE.Server
    }

    fun id(path: String): ResourceLocation {
        return ResourceLocation(MOD_ID, FormattingUtil.toLowerCaseUnder(path))
    }
}

