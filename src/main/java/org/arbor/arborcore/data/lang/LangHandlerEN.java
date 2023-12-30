package org.arbor.arborcore.data.lang;

import dev.toma.configuration.Configuration;
import dev.toma.configuration.config.format.ConfigFormats;
import dev.toma.configuration.config.value.ConfigValue;
import dev.toma.configuration.config.value.ObjectValue;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;
import org.arbor.arborcore.ArborCore;
import org.arbor.arborcore.config.ConfigHandler;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LangHandlerEN extends LanguageProvider {

    public LangHandlerEN(DataGenerator gen, String locale) {
        super(gen.getPackOutput(), ArborCore.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        addMachine();
        addMaterial();
        Config();
    }
    public void Config() {
        dfs(new HashSet<>(), Configuration.registerConfig(ConfigHandler.class, ConfigFormats.yaml()).getValueMap());
    }

    private void addMachine() {
        add("block.gtceu.chemical_plant", "ExxonMobil Chemical Plant");
        add("gtceu.chemical_plant", "ExxonMobil Chemical Plant");
        add("arbor.recipe.condition.plant_casing.tooltip", "Casing Tier: %s (%s)");
        add("arbor.multiblock.pattern.error.plant_casings", "All casing must be the same");
        add("arbor.multiblock.pattern.error.pipe", "All casing must be the same");
        add("arbor.multiblock.pattern.error.machine_casing", "All machine casing must be the same");
        add("gtceu.multiblock.chemical_plant.tooltip1", "§o§7Heavy industry, right at your doorstep now！");
        add("gtceu.multiblock.chemical_plant.tooltip2", "§6Coil：§e+50%§6 speed/tier");
        add("gtceu.multiblock.chemical_plant.tooltip3", "§bPipe：§e+2§b parallel/tier");
        add("gtceu.multiblock.chemical_plant.tooltip4", "§5MachineCasing：Recipe voltage support level");
        add("gtceu.multiblock.chemical_plant.energy_hatch", "§eSupport Energy Hatch: %s §eand following");
        add("gtceu.multiblock.chemical_plant.parallel_level", "§bParallel: %s");
        add("gtceu.multiblock.chemical_plant.heating_coil", "§6Speed: %s%%");
    }

    private void addMaterial() {
        add("tagprefix.blackstone", "Blackstone %s Ores");
        add("tagprefix.glacio_stone", "Glacio Stone %s Ores");
        add("tagprefix.mars_stone", "Mars Stone %s Ores");
        add("tagprefix.mercury_stone", "Mercury Stone %s Ores");
        add("tagprefix.moon_stone", "Moon Stone %s Ores");
        add("tagprefix.soul_soil", "SoulSoil %s Ores");
        add("tagprefix.venus_stone", "Venus Stone %s Ores");
        add("material.andesite_alloy", "Andesite Alloy");
        add("material.space_neutronium", "Space Neutronium");
        add("material.infinity", "Infinity");
        add("material.infinity_catalyst", "Infinity Catalyst");
        add("material.desh", "Desh");
        add("material.ostrum", "Ostrum");
        add("material.calorite", "Calorite");
        add("material.rp_1_mixed_fuel", "RP-1 Mixed Fuel");
        add("material.rp_1_rocket_fuel", "RP-1 Rocket Fuel");
        add("material.kerosene", "Kerosene");
        add("material.dense_hydrazine_mixed_fuel", "Dense Hydrazine Mixed Fuel");
        add("material.hydrazine", "Hydrazine");
        add("material.hydrogen_peroxide", "Hydrogen Peroxide");
        add("material.ethyl_anthra_quinone", "Ethyl Anthra Quinone");
        add("material.ethyl_anthra_hydro_quinone", "Ethyl Anthra Hydro Quinone");
        add("material.anthracene", "Anthracene");
        add("material.methylhydrazine_nitrate_rocket_fuel", "CN3H7O3 Rocket Fuel");
        add("material.methyl_hydrazine", "CN3H7O3");
        add("material.udmh_rocket_fuel", "H8N4C2O4 Rocket Fuel");
        add("material.udmh", "H8N4C2O4");
        add("material.orange_metal_catalyst", "Orange Metal Catalyst");
        add("material.phthalic_anhydride", "Phthalic Anhydride");
        add("material.vanadium_pentoxide", "Vanadium Pentoxide");
        add("material.black_matter", "Black Matter");
    }

    private void dfs(Set<String> added, Map<String, ConfigValue<?>> map) {
        for (var entry : map.entrySet()) {
            var id = entry.getValue().getId();
            if (added.add(id)) {
                add(String.format("config.%s.option.%s", ArborCore.MODID, id), id);
            }
            if (entry.getValue() instanceof ObjectValue objectValue) {
                dfs(added, objectValue.get());
            }
        }
    }
}
