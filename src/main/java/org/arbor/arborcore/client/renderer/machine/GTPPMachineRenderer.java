package org.arbor.arborcore.client.renderer.machine;

import com.gregtechceu.gtceu.GTCEu;
import com.gregtechceu.gtceu.api.capability.IWorkable;
import com.gregtechceu.gtceu.api.machine.MachineDefinition;
import com.gregtechceu.gtceu.api.machine.MetaMachine;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiController;
import com.gregtechceu.gtceu.api.machine.feature.multiblock.IMultiPart;
import com.gregtechceu.gtceu.client.model.SpriteOverrider;
import com.gregtechceu.gtceu.client.model.WorkableOverlayModel;
import com.gregtechceu.gtceu.client.renderer.machine.IControllerRenderer;
import com.gregtechceu.gtceu.client.renderer.machine.MachineRenderer;
import com.lowdragmc.lowdraglib.client.model.ModelFactory;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
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
    BakedModel bakeModel;

    public GTPPMachineRenderer(ResourceLocation baseCasing,ResourceLocation workableModel, boolean tint) {
        super(tint ? GTCEu.id("block/tinted_cube_all") : GTCEu.id("block/cube_all"));
        this.overlayModel = new WorkableOverlayModel(workableModel);
        this.setTextureOverride(Map.of("all", baseCasing));
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void renderMachine(List<BakedQuad> quads, MachineDefinition definition, @Nullable MetaMachine machine,
                              Direction frontFacing, @Nullable Direction side, RandomSource rand, Direction modelFacing, ModelState modelState) {
        quads.clear();
        if (machine instanceof IGTPPMachine igtppMachine) {
            ResourceLocation baseCasing = PlantCasingBlock.PlantCasing.getByTier(igtppMachine.getTier()).getResourceLocation();
            bakeModel = ModelFactory.getUnBakedModel(this.modelLocation).bake(ModelFactory.getModeBaker(),
                    new SpriteOverrider(Map.of("all", baseCasing)), ModelFactory.getRotation(frontFacing), this.modelLocation);
            if (bakeModel != null) {
                quads.addAll(bakeModel.getQuads(definition.defaultBlockState(), side, rand));
            }
        }else {
            super.renderMachine(quads, definition, machine, frontFacing, side, rand, modelFacing, modelState);
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
        quads.clear();
        if (side != null && modelFacing != null) {
            if (bakeModel != null) {
                quads.addAll(bakeModel.getQuads(part.self().getDefinition().defaultBlockState(), side, rand));
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void onPrepareTextureAtlas(ResourceLocation atlasName, Consumer<ResourceLocation> register) {
        super.onPrepareTextureAtlas(atlasName, register);
        if (atlasName.equals(TextureAtlas.LOCATION_BLOCKS)) {
            this.overlayModel.registerTextureAtlas(register);
        }
    }
}
