package dev.arbor.gtnn.api.machine.multiblock

import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity
import com.gregtechceu.gtceu.api.machine.multiblock.WorkableElectricMultiblockMachine
import com.lowdragmc.lowdraglib.syncdata.field.ManagedFieldHolder
import dev.arbor.gtnn.api.machine.feature.IEnhanceFancyUIMachine
import dev.arbor.gtnn.api.machine.trait.MultiblockTrait
import lombok.Getter
import net.minecraft.network.chat.Component

open class MultiStatsElectricMultiblockMachine(holder: IMachineBlockEntity, vararg args: Any) :
    WorkableElectricMultiblockMachine(holder, *args), IEnhanceFancyUIMachine {
    @Getter
    protected var multiblockStats = ArrayList<MultiblockTrait>()

    override fun onStructureFormed() {
        super.onStructureFormed()
        multiblockStats.forEach { it.onStructureFormed(getMultiblockState()) }
    }

    override fun onStructureInvalid() {
        super.onStructureInvalid()
        multiblockStats.forEach(MultiblockTrait::onStructureInvalid)
    }

    override fun addDisplayText(textList: MutableList<Component>) {
        super.addDisplayText(textList)
        multiblockStats.forEach { it.addDisplayText(textList) }
    }

    fun addStats(state: MultiblockTrait) {
        multiblockStats.add(state)
    }

    override fun getFieldHolder(): ManagedFieldHolder {
        return MANAGED_FIELD_HOLDER
    }

    companion object {
        @JvmStatic
        protected val MANAGED_FIELD_HOLDER: ManagedFieldHolder = ManagedFieldHolder(
            MultiStatsElectricMultiblockMachine::class.java,
            WorkableElectricMultiblockMachine.MANAGED_FIELD_HOLDER
        )
    }
}