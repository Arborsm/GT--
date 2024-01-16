package org.arbor.gtnn.data.lang.en;

import com.tterrag.registrate.providers.RegistrateLangProvider;

public class MachineLang {
    public static void init(RegistrateLangProvider provider) {
        provider.add("block.gtceu.chemical_plant", "ExxonMobil Chemical Plant");
        provider.add("gtceu.chemical_plant", "ExxonMobil Chemical Plant");
        provider.add("gtnn.recipe.condition.plant_casing.tooltip", "Casing Tier: %s (%s)");
        provider.add("gtnn.multiblock.pattern.error.plant_casings", "All casing must be the same");
        provider.add("gtnn.multiblock.pattern.error.pipe", "All casing must be the same");
        provider.add("gtnn.multiblock.pattern.error.machine_casing", "All machine casing must be the same");
        provider.add("gtceu.multiblock.chemical_plant.tooltip1", "§o§7Heavy industry, right at your doorstep now！");
        provider.add("gtceu.multiblock.chemical_plant.tooltip2", "§6Coil：§e+50%§6 speed/tier");
        provider.add("gtceu.multiblock.chemical_plant.tooltip3", "§bPipe：§e+2§b parallel/tier");
        provider.add("gtceu.multiblock.chemical_plant.tooltip4", "§5MachineCasing：Recipe voltage support level");
        provider.add("gtceu.multiblock.chemical_plant.energy_hatch", "§eSupport Energy Hatch: %s §eand following");
        provider.add("gtceu.multiblock.chemical_plant.parallel_level", "§bParallel: %s");
        provider.add("gtceu.multiblock.chemical_plant.heating_coil", "§6Speed: %s%%");
        provider.add("gtceu.machine.neutron_accelerator.tooltip1", "Input EU to Accelerate the Neutron!");
        provider.add("gtceu.machine.neutron_accelerator.tooltip2", "Max EU input: %s");
        provider.add("gtceu.machine.neutron_accelerator.tooltip3", "Max EU consumption: %s");
        provider.add("gtceu.machine.neutron_accelerator.tooltip4", "Every EU can be transformed into 10~20 eV Neutron Kinetic Energy.");
        provider.add("gtceu.multiblock.neutronactivator.ev", "Current Neutron Energy: %deV");
        provider.add("gtceu.multiblock.neutronactivator.heigh", "Height: %s");
        provider.add("arbor.recipe.condition.neutron_activator_condition_tooltip", "Minimum Neutron Kinetic Energy:\n%s MeV\nMaximum Neutron Kinetic Energy:\n%s MeV");
    }
}
