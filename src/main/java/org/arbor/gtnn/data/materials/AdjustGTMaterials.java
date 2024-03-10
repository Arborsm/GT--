package org.arbor.gtnn.data.materials;

import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags;

import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static org.arbor.gtnn.data.GTNNMaterials.*;

public class AdjustGTMaterials {
    public static void init() {
        NaquadahEnriched.addFlags(MaterialFlags.GENERATE_BOLT_SCREW);
        Brass.addFlags(MaterialFlags.GENERATE_DENSE);
        Aluminium.addFlags(MaterialFlags.GENERATE_DENSE);
        Steel.addFlags(MaterialFlags.GENERATE_DENSE);
        Lanthanum.addFlags(MaterialFlags.GENERATE_DENSE);
        Iridium.addFlags(MaterialFlags.GENERATE_DENSE);
        Lead.addFlags(MaterialFlags.GENERATE_DENSE);
        IronMagnetic.addFlags(MaterialFlags.GENERATE_PLATE);
        SteelMagnetic.addFlags(MaterialFlags.GENERATE_PLATE);
        NeodymiumMagnetic.addFlags(MaterialFlags.GENERATE_PLATE);
        SamariumMagnetic.addFlags(MaterialFlags.GENERATE_PLATE);
        NaquadahEnriched.addFlags(MaterialFlags.GENERATE_LONG_ROD);
        NickelZincFerrite.addFlags(MaterialFlags.GENERATE_LONG_ROD);
        BlueAlloy.addFlags(MaterialFlags.GENERATE_FRAME);
        Nichrome.addFlags(MaterialFlags.GENERATE_GEAR);
        Zeron100.addFlags(MaterialFlags.GENERATE_GEAR);
        Aluminium.addFlags(MaterialFlags.GENERATE_ROTOR);
        addGas(Oganesson);
        addGas(Calcium);
        addFluid(Californium);
        addFluid(Caesium);
        addDust(Praseodymium);
    }
}
