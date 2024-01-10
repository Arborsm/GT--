package org.arbor.arborcore.client.renderer.machine;

import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.client.renderer.machine.IPartRenderer;
import com.gregtechceu.gtceu.client.renderer.machine.MachineRenderer;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public class BlockMachineRenderer extends MachineRenderer implements IPartRenderer {
    public BlockMachineRenderer(ResourceLocation modelLocation) {
        super(modelLocation);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void renderMachine(List<BakedQuad> quads, MachineDefinition definition, @javax.annotation.Nullable MetaMachine machine, Direction frontFacing, @javax.annotation.Nullable Direction side, RandomSource rand, @javax.annotation.Nullable Direction modelFacing, ModelState modelState) {
        this.renderBaseModel(quads, definition, machine, frontFacing, side, rand);
    }
}
