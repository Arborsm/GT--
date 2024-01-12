package org.arbor.arborcore.client.renderer.machine;

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
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.arbor.arborcore.api.machine.feature.IGTPPMachine;
import org.arbor.arborcore.block.PlantCasingBlock;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class GTPPMachineRenderer extends MachineRenderer implements IControllerRenderer {
    protected final WorkableOverlayModel overlayModel;
    @OnlyIn(Dist.CLIENT)
    private void render(List<BakedQuad> quads, MetaMachine machine, ModelState modelState){
        quads.clear();
        var sprite = ModelFactory.getBlockSprite(PlantCasingBlock.PlantCasing.getByTier(((IGTPPMachine)machine).getTier()).getResourceLocation());
        quads.add(FaceQuad.bakeFace(Direction.DOWN, sprite, modelState));
        quads.add(FaceQuad.bakeFace(Direction.UP, sprite, modelState));
        quads.add(FaceQuad.bakeFace(Direction.NORTH, sprite, modelState));
        quads.add(FaceQuad.bakeFace(Direction.SOUTH, sprite, modelState));
        quads.add(FaceQuad.bakeFace(Direction.WEST, sprite, modelState));
        quads.add(FaceQuad.bakeFace(Direction.EAST, sprite, modelState));
    }

    public GTPPMachineRenderer(ResourceLocation baseCasing,ResourceLocation workableModel, boolean tint) {
        super(tint ? GTCEu.id("block/tinted_cube_all") : GTCEu.id("block/cube_all"));
        this.overlayModel = new WorkableOverlayModel(workableModel);
        this.setTextureOverride(Map.of("all", baseCasing));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void renderMachine(List<BakedQuad> quads, MachineDefinition definition, @Nullable MetaMachine machine,
                              Direction frontFacing, @Nullable Direction side, RandomSource rand, Direction modelFacing, ModelState modelState) {
        super.renderMachine(quads, definition, machine, frontFacing, side, rand, modelFacing, modelState);
        if (machine instanceof IGTPPMachine && machine instanceof MultiblockControllerMachine multiblockControllerMachine) {
            if (multiblockControllerMachine.isFormed()){
                render(quads, machine, modelState);
                if (((IGTPPMachine)machine).getTier() != 0){
                    int i = 1 + 1;
                }
            }
        }
        if (machine instanceof IWorkable workable) {
            quads.addAll(this.overlayModel.bakeQuads(side, frontFacing, workable.isActive(), workable.isWorkingEnabled()));
        } else {
            quads.addAll(this.overlayModel.bakeQuads(side, frontFacing, false, false));
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void renderPartModel(List<BakedQuad> quads, IMultiController machine, IMultiPart part, Direction frontFacing,
                                @org.jetbrains.annotations.Nullable Direction side, RandomSource rand, Direction modelFacing, ModelState modelState) {
        if (side != null && modelFacing != null) {
            render(quads, machine.self(), modelState);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void onPrepareTextureAtlas(ResourceLocation atlasName, Consumer<ResourceLocation> register) {
        super.onPrepareTextureAtlas(atlasName, register);
        if (atlasName.equals(TextureAtlas.LOCATION_BLOCKS)) {
            this.overlayModel.registerTextureAtlas(register);
        }
    }
}