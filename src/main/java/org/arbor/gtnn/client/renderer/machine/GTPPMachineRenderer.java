package org.arbor.gtnn.client.renderer.machine;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.capability.IWorkable;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.api.machine.multiblock.MultiblockControllerMachine;
import com.gregtechceu.gtceu.client.model.WorkableOverlayModel;
import com.gregtechceu.gtceu.client.renderer.machine.IControllerRenderer;
import com.gregtechceu.gtceu.client.renderer.machine.MachineRenderer;
import com.lowdragmc.lowdraglib.client.bakedpipeline.FaceQuad;
import com.lowdragmc.lowdraglib.client.model.ModelFactory;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.arbor.gtnn.api.machine.feature.IGTPPMachine;
import org.arbor.gtnn.block.PlantCasingBlock;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@OnlyIn(Dist.CLIENT)
public class GTPPMachineRenderer extends MachineRenderer implements IControllerRenderer {
    protected final WorkableOverlayModel overlayModel;
    private void render(Direction side, Direction modelFacing, List<BakedQuad> quads, MetaMachine machine, ModelState modelState) {
        if (side != null && modelFacing != null) {
            quads.add(FaceQuad.bakeFace(modelFacing, ModelFactory.getBlockSprite(PlantCasingBlock.getByTier(((IGTPPMachine) machine).getTier()).getResourceLocation()), modelState));
        }
        BlockEntry<Block> casing = PlantCasingBlock.getByTier(((IGTPPMachine) machine).getTier()).getPlantCasing(((IGTPPMachine) machine).getTier());
        machine.self().getDefinition().setAppearance(casing::getDefaultState);
    }

    public GTPPMachineRenderer(ResourceLocation baseCasing, ResourceLocation workableModel, boolean tint) {
        super(tint ? GTCEu.id("block/tinted_cube_all") : GTCEu.id("block/cube_all"));
        this.overlayModel = new WorkableOverlayModel(workableModel);
        this.setTextureOverride(Map.of("all", baseCasing));
    }

    @Override
    public void renderMachine(List<BakedQuad> quads, MachineDefinition definition, @Nullable MetaMachine machine,
                              Direction frontFacing, @Nullable Direction side, RandomSource rand, Direction modelFacing, ModelState modelState) {
        super.renderMachine(quads, definition, machine, frontFacing, side, rand, modelFacing, modelState);
        if (machine instanceof IGTPPMachine && machine instanceof MultiblockControllerMachine multiblockControllerMachine) {
            if (multiblockControllerMachine.isFormed()) {
                quads.clear();
                render(side, modelFacing, quads, machine, modelState);
            }
        }
        if (machine instanceof IWorkable workable) {
            quads.addAll(this.overlayModel.bakeQuads(side, frontFacing, workable.isActive(), workable.isWorkingEnabled()));
        } else {
            quads.addAll(this.overlayModel.bakeQuads(side, frontFacing, false, false));
        }
    }

    @Override
    public void renderPartModel(List<BakedQuad> quads, IMultiController machine, IMultiPart part, Direction frontFacing,
                                @org.jetbrains.annotations.Nullable Direction side, RandomSource rand, Direction modelFacing, ModelState modelState) {
        render(side, modelFacing, quads, machine.self(), modelState);
    }

    @Override
    public void onPrepareTextureAtlas(ResourceLocation atlasName, Consumer<ResourceLocation> register) {
        super.onPrepareTextureAtlas(atlasName, register);
        if (atlasName.equals(InventoryMenu.BLOCK_ATLAS)) {
            this.overlayModel.registerTextureAtlas(register);
        }
    }
}
