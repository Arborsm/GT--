package org.arbor.gtnn.api.machine.multiblock.part;

import com.gregtechceu.gtceu.api.item.tool.GTToolType;
import com.gregtechceu.gtceu.api.machine.IMachineBlockEntity;
import com.gregtechceu.gtceu.api.machine.multiblock.part.MultiblockPartMachine;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Set;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class HighSpeedPipeBlock extends MultiblockPartMachine{
    public HighSpeedPipeBlock(IMachineBlockEntity holder) {
        super(holder);
    }

    @Override
    public boolean shouldOpenUI(Player player, InteractionHand hand, BlockHitResult hit) {
        return false;
    }

    @Override
    public void scheduleRenderUpdate() {
    }

    @Override
    public boolean shouldRenderGrid(Player player, ItemStack held, Set<GTToolType> toolTypes) {
        return false;
    }
}
