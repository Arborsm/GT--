package org.arbor.gtnn.data.materials;

import com.gregtechceu.gtceu.api.fluids.FluidBuilder;
import com.gregtechceu.gtceu.api.fluids.attribute.FluidAttributes;
import com.gregtechceu.gtceu.api.fluids.store.FluidStorageKeys;
import org.arbor.gtnn.GTNNIntegration;

import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialFlags.DISABLE_DECOMPOSITION;
import static com.gregtechceu.gtceu.api.data.chemical.material.info.MaterialIconSet.*;
import static com.gregtechceu.gtceu.common.data.GTMaterials.*;
import static org.arbor.gtnn.data.GTNNMaterials.*;

public class NaquadahMaterials {
    public static void init() {
        if (!GTNNIntegration.isGCYRLoaded()) addFluid(Bromine);
        NaquadahOxideMixture = Builder("naquadah_oxide_mixture")
                .dust()
                .color(0x4c4c4c).iconSet(ROUGH)
                .buildAndRegister()
                .setFormula("??NqTiGa??");
        EnrichedNaquadahOxideMixture = Builder("enriched_naquadah_oxide_mixture")
                .dust()
                .color(0x826868).iconSet(ROUGH)
                .buildAndRegister()
                .setFormula("??Nq+??");
        NaquadriaOxideMixture = Builder("naquadria_oxide_mixture")
                .dust()
                .color(0x4d4d55).secondaryColor(0xe7e7ff)
                .iconSet(RADIOACTIVE)
                .buildAndRegister()
                .setFormula("??*Nq*In??");
        HexafluorideEnrichedNaquadahSolution = Builder("hexafluoride_enriched_naquadah_solution")
                .fluid()
                .color(0x868D7F)
                .components(NaquadahEnriched, 1, Fluorine, 6)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        XenonHexafluoroEnrichedNaquadate = Builder("xenon_hexafluoro_enriched_naquadate")
                .fluid()
                .color(0x255A55)
                .components(Xenon, 1, NaquadahEnriched, 1, Fluorine, 6)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        PalladiumOnCarbon = Builder("palladium_on_carbon")
                .dust()
                .color(0x480104)
                .iconSet(DULL)
                .flags(DISABLE_DECOMPOSITION)
                .components(Palladium, 1, Carbon, 1)
                .buildAndRegister();
        GoldTrifluoride = Builder("gold_trifluoride")
                .dust()
                .color(0xE8C478)
                .iconSet(BRIGHT)
                .components(Gold, 1, Fluorine, 3)
                .buildAndRegister();
        EnrichedNaquadahResidueSolution = Builder("enriched_naquadah_residue_solution")
                .fluid()
                .color(0x868D7F)
                .iconSet(DULL)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister()
                .setFormula("XeAuSbKeF6S2?");
        XenoauricFluoroantimonicAcid = Builder("xenoauric_fluoroantimonic_acid")
                .fluid(FluidStorageKeys.LIQUID, new FluidBuilder().attribute(FluidAttributes.ACID))
                .color(0xE0BD74)
                .components(Xenon, 1, Gold, 1, Antimony, 1, Fluorine, 6)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        GoldChloride = Builder("gold_chloride")
                .fluid()
                .color(0xCCCC66)
                .components(Gold, 2, Chlorine, 6)
                .buildAndRegister();
        BromineTrifluoride = Builder("bromine_trifluoride")
                .fluid()
                .color(0xA88E57)
                .components(Bromine, 1, Fluorine, 3)
                .buildAndRegister();
        HexafluorideNaquadriaSolution = Builder("hexafluoride_naquadria_solution")
                .fluid()
                .color(0x25C213)
                .components(Naquadria, 1, Fluorine, 6)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        RadonDifluoride = Builder("radon_difluoride")
                .fluid()
                .color(0x8B7EFF)
                .components(Radon, 1, Fluorine, 2)
                .buildAndRegister();
        RadonNaquadriaOctafluoride = Builder("radon_naquadria_octafluoride")
                .fluid()
                .color(0x85F378)
                .components(Radon, 1, Naquadria, 1, Fluorine, 8)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        NaquadriaResidueSolution = Builder("naquadria_residue_solution")
                .fluid()
                .color(0x25C213)
                .iconSet(DULL)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister()
                .setFormula("InPS6?", true);
        CaesiumFluoride = Builder("caesium_fluoride")
                .fluid()
                .color(0xFF7A5F)
                .components(Caesium, 1, Fluorine, 1)
                .buildAndRegister();
        XenonTrioxide = Builder("xenon_trioxide")
                .fluid()
                .color(0x359FC3)
                .components(Xenon, 1, Oxygen, 3)
                .buildAndRegister();
        CaesiumXenontrioxideFluoride = Builder("caesium_xenontrioxide_fluoride")
                .fluid()
                .color(0x5067D7)
                .flags(DISABLE_DECOMPOSITION)
                .components(Caesium, 1, Xenon, 1, Oxygen, 3, Fluorine, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        NaquadriaCaesiumXenonnonfluoride = Builder("naquadria_caesium_xenonnonfluoride")
                .fluid()
                .color(0x54C248)
                .components(Naquadria, 1, Caesium, 1, Xenon, 1, Fluorine, 9)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        RadonTrioxide = Builder("radon_trioxide")
                .fluid()
                .color(0x9A6DD7)
                .components(Radon, 1, Oxygen, 3)
                .buildAndRegister();
        NaquadriaCaesiumfluoride = Builder("naquadria_caesiumfluoride")
                .fluid()
                .color(0xAAEB69)
                .components(Naquadria, 1, Fluorine, 3, Caesium, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister()
                .setFormula("*Nq*F2CsF", true);
        NitrosoniumOctafluoroxenate = Builder("nitrosonium_octafluoroxenate")
                .fluid()
                .color(0x953D9F)
                .components(NitrogenDioxide, 2, Xenon, 1, Fluorine, 8)
                .buildAndRegister()
                .setFormula("(NO2)2XeF8", true);
        NitrylFluoride = Builder("nitryl_fluoride")
                .fluid()
                .color(0x8B7EFF)
                .components(Nitrogen, 1, Oxygen, 2, Fluorine, 1)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister();
        AcidicNaquadriaCaesiumfluoride = Builder("acidic_naquadria_caesiumfluoride")
                .fluid()
                .color(0x75EB00)
                .components(Naquadria, 1, Fluorine, 3, Caesium, 1, Sulfur, 2, Oxygen, 8)
                .flags(DISABLE_DECOMPOSITION)
                .buildAndRegister()
                .setFormula("*Nq*F2CsF(SO4)2", true);
    }
}
