package org.arbor.gtnn.data.materials;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty;
import org.arbor.gtnn.data.GTNNElement;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.SAND;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.SHINY;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static org.arbor.gtnn.data.GTNNMaterials.*;

public class FirstMaterials {
    public static void init() {
        NaquadahEnriched.addFlags(MaterialFlags.GENERATE_BOLT_SCREW);
        NaquadahEnriched.addFlags(MaterialFlags.GENERATE_LONG_ROD);
        Brass.addFlags(MaterialFlags.GENERATE_DENSE);
        Aluminium.addFlags(MaterialFlags.GENERATE_DENSE);
        Steel.addFlags(MaterialFlags.GENERATE_DENSE);
        IronMagnetic.addFlags(MaterialFlags.GENERATE_PLATE);
        SteelMagnetic.addFlags(MaterialFlags.GENERATE_PLATE);
        NeodymiumMagnetic.addFlags(MaterialFlags.GENERATE_PLATE);
        SamariumMagnetic.addFlags(MaterialFlags.GENERATE_PLATE);
        NickelZincFerrite.addFlags(MaterialFlags.GENERATE_LONG_ROD);
        BlueAlloy.addFlags(MaterialFlags.GENERATE_FRAME);
        Nichrome.addFlags(MaterialFlags.GENERATE_GEAR);
        Zeron100.addFlags(MaterialFlags.GENERATE_GEAR);
        addGas(Oganesson);
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
