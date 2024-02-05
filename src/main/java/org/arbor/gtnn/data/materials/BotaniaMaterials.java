package org.arbor.gtnn.data.materials;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.SHINY;
import static com.gregtechceu.gtceu.common.data.GTMaterials.EXT2_METAL;
import static org.arbor.gtnn.data.GTNNMaterials.*;

public class BotaniaMaterials {
    public static void init(){
        ManaSteel = Builder("mana_steel")
                .ingot().fluid()
                .color(0x2177b8).iconSet(SHINY)
                .appendFlags(EXT2_METAL, GENERATE_FINE_WIRE, GENERATE_GEAR, GENERATE_FRAME)
                .buildAndRegister();
        TerraSteel = Builder("terra_steel")
                .ingot().fluid()
                .color(0x5dbe8a).iconSet(SHINY)
                .appendFlags(EXT2_METAL, GENERATE_FINE_WIRE, GENERATE_GEAR, GENERATE_FRAME)
                .buildAndRegister();
        Elementium = Builder("elementium")
                .ingot().fluid()
                .color(0xFFB3D6).iconSet(SHINY)
                .appendFlags(EXT2_METAL, GENERATE_FINE_WIRE, GENERATE_GEAR, GENERATE_FRAME)
                .buildAndRegister();
    }
}
