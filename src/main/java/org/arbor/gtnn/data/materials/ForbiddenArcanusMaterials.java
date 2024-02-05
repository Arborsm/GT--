package org.arbor.gtnn.data.materials;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.DIAMOND;
import static org.arbor.gtnn.data.GTNNMaterials.ArcaneCrystal;
import static org.arbor.gtnn.data.GTNNMaterials.Builder;

public class ForbiddenArcanusMaterials {
    public static void init(){
        ArcaneCrystal = Builder("arcane_crystal")
                .dust().ore().gem()
                .color(0x93AEFF).iconSet(DIAMOND)
                .buildAndRegister();
    }
}
