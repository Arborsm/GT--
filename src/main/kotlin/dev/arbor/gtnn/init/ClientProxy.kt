package dev.arbor.gtnn.init

import dev.arbor.gtnn.GTNNRegistries
import dev.arbor.gtnn.client.renderer.item.GTNNItemRenderers
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.eventbus.api.IEventBus

class ClientProxy(): CommonProxy() {
    override fun init() {
        super.init()
        val events = MinecraftForge.EVENT_BUS
        clientEventRegister(events)
        GTNNItemRenderers.init()
    }

    companion object {
        @JvmStatic
        fun clientEventRegister(events: IEventBus) {
            events.addListener(GTNNRegistries::onRenderWorldLast)
        }
    }
}