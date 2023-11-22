package org.arbor.arborcore.data;

import com.gregtechceu.gtceu.api.GTValues;
import com.gregtechceu.gtceu.api.data.chemical.material.Material;
import com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet;
import com.gregtechceu.gtceu.api.data.chemical.material.properties.BlastProperty;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.EXT2_METAL;

@SuppressWarnings("unused")
public class ArborMaterials {
    public static void init() {
        // Add flags to base GT materials
    }

    // MaterialIconSet
    public static final MaterialIconSet InfinityIcon = new MaterialIconSet("infinity", MaterialIconSet.SHINY);

    // region first degree mats
    public static final Material AndesiteAlloy = new Material.Builder("andesite_alloy")
            .ingot().fluid()
            .color(0x99B09F).iconSet(MaterialIconSet.DULL)
            .buildAndRegister()
            .setFormula("(Mg3Si2H4O9)4(KNO3)Fe");
    public static final Material Desh = new Material.Builder("desh")
            .ingot().fluid().ore()
            .color(0xD38B4C).iconSet(MaterialIconSet.METALLIC)
            .appendFlags(EXT2_METAL, GENERATE_ROTOR, GENERATE_DENSE, GENERATE_SMALL_GEAR)
            .buildAndRegister();
    public static final Material Ostrum = new Material.Builder("ostrum")
            .ingot().fluid().ore()
            .color(0xA66B72).iconSet(MaterialIconSet.METALLIC)
            .appendFlags(EXT2_METAL, GENERATE_ROTOR, GENERATE_DENSE, GENERATE_SMALL_GEAR)
            .buildAndRegister();
    public static final Material Calorite = new Material.Builder("calorite")
            .ingot().fluid().ore()
            .color(0xC94D4E).iconSet(MaterialIconSet.METALLIC)
            .appendFlags(EXT2_METAL, GENERATE_ROTOR, GENERATE_DENSE, GENERATE_SMALL_GEAR)
            .buildAndRegister();
    public static final Material SpaceNeutronium = new Material.Builder("space_neutronium")
            .ingot().fluid().ore().dust()
            .color(0x11111b).iconSet(MaterialIconSet.SHINY)
            .element(ArborElement.SpNt)
            .blastTemp(9900, BlastProperty.GasTier.HIGH, GTValues.VA[GTValues.ZPM], 21825)
            .buildAndRegister();

    public static final Material Infinity = new Material.Builder("infinity")
            .ingot().fluid().ore().dust()
            .color(0xFFFFFF).iconSet(InfinityIcon)
            .element(ArborElement.IF2)
            .blastTemp(10800, BlastProperty.GasTier.HIGHEST, GTValues.VA[GTValues.UHV], 54562)
            .buildAndRegister();
    public static final Material InfinityCatalyst = new Material.Builder("infinity_catalyst")
            .dust().ore()
            .color(0xE5E2E1).iconSet(MaterialIconSet.SAND)
            .element(ArborElement.IF)
            .buildAndRegister();
}
