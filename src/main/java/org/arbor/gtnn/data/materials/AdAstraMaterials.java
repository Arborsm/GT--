package org.arbor.gtnn.data.materials;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.METALLIC;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static org.arbor.gtnn.data.GTNNMaterials.*;

public class AdAstraMaterials {
    public static void init(){
        addOre(Neutronium);
        addOre(Perlite);
        addOre(Uvarovite);
        addOre(Andradite);
        addOre(Arsenic);
        addOre(Bismuth);
        addOre(Antimony);
        addOre(Uranium235);
        addOre(Uranium238);
        addOre(Plutonium241);
        addOre(Gallium);
        addOre(Niobium);
        addOre(Vanadium);
        addOre(Osmium);
        addOre(Iridium);
        addOre(Titanium);
        addOre(Manganese);
        addOre(Rutile);
        addOre(Tungsten);
        addOre(Chromium);
        addOre(NaquadahEnriched);
        addOre(Naquadria);
        Desh = Builder("desh")
                .ingot().fluid().ore()
                .color(0xF2A057).secondaryColor(0x2E2F04).iconSet(METALLIC)
                .appendFlags(EXT2_METAL, GENERATE_ROTOR, GENERATE_DENSE, GENERATE_SMALL_GEAR)
                .buildAndRegister();
        Ostrum = Builder("ostrum")
                .ingot().fluid().ore()
                .color(0xE5939B).secondaryColor(0x2F0425).iconSet(METALLIC)
                .appendFlags(EXT2_METAL, GENERATE_ROTOR, GENERATE_DENSE, GENERATE_SMALL_GEAR)
                .buildAndRegister();
        Calorite = Builder("calorite")
                .ingot().fluid().ore()
                .color(0xE65757).secondaryColor(0x2F0506).iconSet(METALLIC)
                .appendFlags(EXT2_METAL, GENERATE_ROTOR, GENERATE_DENSE, GENERATE_SMALL_GEAR)
                .buildAndRegister();
    }
}
