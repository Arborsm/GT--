package org.arbor.gtnn.data.materials;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty;
import org.arbor.gtnn.data.GTNNElement;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.SAND;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.SHINY;
import static org.arbor.gtnn.data.GTNNMaterials.*;

public class FirstMaterials {
    public static void init() {
        SpaceNeutronium = Builder("space_neutronium")
                .ingot().fluid().ore().dust()
                .color(0x11111b).iconSet(SHINY)
                .element(GTNNElement.SpNt)
                .blastTemp(9900, BlastProperty.GasTier.HIGH, GTValues.VA[GTValues.ZPM], 21825)
                .buildAndRegister();
        Infinity = Builder("infinity")
                .ingot().fluid().ore().dust()
                .color(0xFFFFFF).iconSet(MaterialIcons.InfinityIcon)
                .element(GTNNElement.IF2)
                .blastTemp(10800, BlastProperty.GasTier.HIGHEST, GTValues.VA[GTValues.UHV], 54562)
                .buildAndRegister();
        InfinityCatalyst = Builder("infinity_catalyst")
                .dust().ore()
                .color(0xE5E2E1).iconSet(SAND)
                .element(GTNNElement.IF)
                .buildAndRegister();
    }
}
