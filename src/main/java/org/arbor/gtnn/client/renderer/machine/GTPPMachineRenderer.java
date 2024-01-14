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
import com.lowdragmc.lowdraglib.utils.FacadeBlockAndTintGetter;
import com.tterrag.registrate.util.entry.BlockEntry;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.arbor.gtnn.api.machine.feature.IGTPPMachine;
import org.arbor.gtnn.block.PlantCasingBlock;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class GTPPMachineRenderer extends MachineRenderer implements IControllerRenderer {
    protected final WorkableOverlayModel overlayModel;
    @OnlyIn(Dist.CLIENT)
    private void render(List<BakedQuad> quads, MetaMachine machine, ModelState modelState){
        var sprite = ModelFactory.getBlockSprite(PlantCasingBlock.PlantCasing.getByTier(((IGTPPMachine)machine).getTier()).getResourceLocation());
        quads.add(FaceQuad.bakeFace(Direction.DOWN, sprite, modelState));
        quads.add(FaceQuad.bakeFace(Direction.UP, sprite, modelState));
        quads.add(FaceQuad.bakeFace(Direction.NORTH, sprite, modelState));
        quads.add(FaceQuad.bakeFace(Direction.SOUTH, sprite, modelState));
        quads.add(FaceQuad.bakeFace(Direction.WEST, sprite, modelState));
        quads.add(FaceQuad.bakeFace(Direction.EAST, sprite, modelState));
        BlockEntry<Block> casing = PlantCasingBlock.PlantCasing.getByTier(((IGTPPMachine)machine).getTier()).getPlantCasing();
        machine.self().getDefinition().setAppearance(casing::getDefaultState);
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
        if (machine instanceof IGTPPMachine igtppMachine && machine instanceof MultiblockControllerMachine multiblockControllerMachine) {
            if (multiblockControllerMachine.isFormed() && igtppMachine.getTier() != 0){
                quads.clear();
                render(quads, machine, modelState);
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
        render(quads, machine.self(), modelState);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void onPrepareTextureAtlas(ResourceLocation atlasName, Consumer<ResourceLocation> register) {
        super.onPrepareTextureAtlas(atlasName, register);
        if (atlasName.equals(InventoryMenu.BLOCK_ATLAS)) {
            this.overlayModel.registerTextureAtlas(register);
        }
    }

    @Override
    public boolean isConnected(BlockAndTintGetter level, BlockState state, BlockPos pos, BlockState sourceState, BlockPos sourcePos, Direction side) {
        BlockState stateAppearance = FacadeBlockAndTintGetter.getAppearance(state, level, pos, side, sourceState, sourcePos);
        BlockState sourceStateAppearance = FacadeBlockAndTintGetter.getAppearance(sourceState, level, sourcePos, side, state, pos);
        return stateAppearance == sourceStateAppearance;
    }
}
