package org.arbor.gtnn.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.attribute.FluidAttributes;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import net.minecraft.resources.ResourceLocation;

public class GTNNBuilder extends Material.Builder {
    public GTNNBuilder(ResourceLocation resourceLocation) {
        super(resourceLocation);
    }

    public Material.Builder acid() {
        fluid(FluidStorageKeys.LIQUID, new FluidBuilder().attribute(FluidAttributes.ACID));
        return this;
    }
}
