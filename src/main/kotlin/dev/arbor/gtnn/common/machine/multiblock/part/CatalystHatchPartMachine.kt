package dev.arbor.gtnn.common.machine.multiblock.part

import com.gregtechceu.gtceu.api.GTValues
import com.gregtechceu.gtceu.api.capability.recipe.IO
import com.gregtechceu.gtceu.api.gui.GuiTextures
import com.gregtechceu.gtceu.api.gui.widget.SlotWidget
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity
import com.gregtechceu.gtceu.api.machine.TickableSubscription
import com.gregtechceu.gtceu.api.machine.multiblock.part.TieredIOPartMachine
import com.gregtechceu.gtceu.api.machine.trait.NotifiableItemStackHandler
import com.gregtechceu.gtceu.api.machine.trait.RecipeHandlerList
import com.gregtechceu.gtceu.api.transfer.item.CustomItemStackHandler
import com.lowdragmc.lowdraglib.gui.texture.ResourceTexture
import com.lowdragmc.lowdraglib.gui.widget.ImageWidget
import com.lowdragmc.lowdraglib.gui.widget.Widget
import com.lowdragmc.lowdraglib.gui.widget.WidgetGroup
import com.lowdragmc.lowdraglib.syncdata.ISubscription
import com.lowdragmc.lowdraglib.syncdata.annotation.Persisted
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder
import dev.arbor.gtnn.common.item.behaviors.CatalystBehavior
import net.minecraft.MethodsReturnNonnullByDefault
import net.minecraft.world.item.Item
import java.util.function.IntFunction
import javax.annotation.ParametersAreNonnullByDefault

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
class CatalystHatchPartMachine(holder: IMachineBlockEntity) : TieredIOPartMachine(holder, GTValues.IV, IO.IN) {
    @Persisted
    val buffer: NotifiableItemStackHandler = NotifiableItemStackHandler(this, 16, IO.NONE, IO.BOTH)

    @Persisted
    val inventory: NotifiableItemStackHandler = createInventory()
    private var bufferSubs: ISubscription? = null
    private var inventorySubs: ISubscription? = null
    private var transferSubs: TickableSubscription? = null

    override fun getRecipeHandlers(): List<RecipeHandlerList> {
        return listOf(RecipeHandlerList.of(IO.IN, paintingColor, inventory))
    }


    //////////////////////////////////////
    // *****     Initialization    ******//
    //////////////////////////////////////
    override fun onLoad() {
        super.onLoad()
        if (!isRemote) {
            bufferSubs = buffer.addChangedListener { this.onInventoryChanged() }
            inventorySubs = inventory.addChangedListener { this.onInventoryChanged() }
        }
    }

    override fun onUnload() {
        super.onUnload()
        bufferSubs?.unsubscribe()
        inventorySubs?.unsubscribe()
    }

    private fun createInventory(): NotifiableItemStackHandler =
        object : NotifiableItemStackHandler(this, 16, IO.IN, IO.OUT,
            IntFunction { slots: Int ->
                object : CustomItemStackHandler(slots) {
                    override fun getSlotLimit(slot: Int): Int {
                        return 1
                    }
                }
            }) {}


    //////////////////////////////////////
    // ********   Subscriptions  ********//
    //////////////////////////////////////
    private fun onInventoryChanged() {
        if (isWorkingEnabled && !buffer.isEmpty) {
            transferSubs = subscribeServerTick(transferSubs) { this.transferItems() }
        } else unsubscribe()
    }

    private fun transferItems() {
        for (i in 0 until buffer.slots) {
            val stack = buffer.getStackInSlot(i)
            if (stack.isEmpty || !inventory.getStackInSlot(i).isEmpty) continue
            if (!buffer.extractItem(i, 1, true).isEmpty) {
                val copy = stack.copyWithCount(1)
                if (inventory.insertItemInternal(i, copy, true).isEmpty) {
                    buffer.extractItem(i, 1, false)
                    inventory.insertItemInternal(i, copy, false)
                }
            }
        }
        unsubscribe()
    }

    private fun unsubscribe() {
        transferSubs?.unsubscribe()
        transferSubs = null
    }

    //////////////////////////////////////
    // **********     GUI     ***********//
    //////////////////////////////////////
    override fun createUIWidget(): Widget {
        val group = WidgetGroup(0, 0, 18 * 8 + 31, 18 * 4 + 16)
        val slotsContainer = WidgetGroup(4, 4, 18 * 8 + 23, 18 * 4 + 8)
        slotsContainer.addWidget(ImageWidget(75, 31, 18, 18, SMALL_ARROW_OVERLAY))
        addSlots(slotsContainer, buffer, 4, 4, true)
        addSlots(slotsContainer, inventory, 91, 4, false)
        slotsContainer.setBackground(GuiTextures.BACKGROUND_INVERSE)
        group.addWidget(slotsContainer)
        return group
    }

    private fun addSlots(
        container: WidgetGroup,
        handler: NotifiableItemStackHandler,
        x: Int,
        @Suppress("SameParameterValue") y: Int,
        canPutItems: Boolean
    ) {
        var index = 0
        for (j in 0..3) {
            for (i in 0..3) {
                container.addWidget(
                    SlotWidget(
                        handler,
                        index++,
                        x + i * 18,
                        y + j * 18,
                        true,
                        canPutItems
                    ).setBackground(GuiTextures.SLOT)
                )
            }
        }
    }


    //////////////////////////////////////
    // **********     Data     **********//
    //////////////////////////////////////
    fun getCatalystDurability(items: Set<Item>): Int {
        transferItems()
        var durability = 0
        for (i in 0 until inventory.slots) {
            val stack = inventory.getStackInSlot(i)
            if (stack.item !in items) continue
            val behavior = CatalystBehavior.getBehaviour(stack) ?: continue
            durability += behavior.getDurability(stack)
        }
        return durability
    }

    fun consumeCatalyst(items: Set<Item>, amount: Int): Int {
        transferItems()
        var remaining = amount
        for (i in 0 until inventory.slots) {
            if (remaining == 0) break

            val stack = inventory.getStackInSlot(i)
            if (stack.item !in items) continue
            val behavior = CatalystBehavior.getBehaviour(stack) ?: continue
            val damage = remaining.coerceAtMost(behavior.getDurability(stack))
            behavior.applyDamage(stack, damage)
            remaining -= damage
            if (stack.isEmpty) transferItems()
        }
        return remaining
    }

    override fun setWorkingEnabled(workingEnabled: Boolean) {
        super.setWorkingEnabled(workingEnabled)
        onInventoryChanged()
    }

    override fun getFieldHolder(): ManagedFieldHolder {
        return MANAGED_FIELD_HOLDER
    }

    companion object {
        val MANAGED_FIELD_HOLDER =
            ManagedFieldHolder(CatalystHatchPartMachine::class.java, TieredIOPartMachine.MANAGED_FIELD_HOLDER)
        val SMALL_ARROW_OVERLAY = ResourceTexture("gtnn:textures/gui/arrows/small_arrow_overlay.png")
    }
}
