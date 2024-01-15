package org.arbor.gtnn.data.lang;

import com.tterrag.registrate.providers.RegistrateLangProvider;

import static com.gregtechceu.gtceu.data.lang.LangHandler.replace;

public class MachineLang {
    protected static void init(RegistrateLangProvider provider) {
        replace(provider, "block.gtnn.chemical_plant", "埃克森美孚化工厂");
        replace(provider, "block.gtnn.lv_neutron_accelerator", "§7LV中子加速器");
        replace(provider, "block.gtnn.mv_neutron_accelerator", "§bMV中子加速器");
        replace(provider, "block.gtnn.hv_neutron_accelerator", "§6HV中子加速器");
        replace(provider, "block.gtnn.ev_neutron_accelerator", "§5EV中子加速器");
        replace(provider, "block.gtnn.iv_neutron_accelerator", "§1IV中子加速器");
        replace(provider, "block.gtnn.luv_neutron_accelerator", "§dLuV中子加速器");
        replace(provider, "block.gtnn.zpm_neutron_accelerator", "§cZPM中子加速器");
        replace(provider, "block.gtnn.uv_neutron_accelerator", "§3UV中子加速器");
        replace(provider, "block.gtnn.neutron_activator", "中子活化器");
        provider.add("gtceu.chemical_plant", "埃克森美孚化工厂");
        provider.add("gtnn.recipe.condition.plant_casing.tooltip", "外壳等级: %s (%s)");
        provider.add("gtnn.multiblock.pattern.error.plant_casings", "所有外壳必须相同");
        provider.add("gtnn.multiblock.pattern.error.pipe", "所有管道必须相同");
        provider.add("gtnn.multiblock.pattern.error.machine_casing", "所有机械方块必须相同");
        provider.add("gtceu.multiblock.chemical_plant.tooltip1", "§o§7重工业，现在就在你家门口！");
        provider.add("gtceu.multiblock.chemical_plant.tooltip2", "§6线圈：§e+50%§6 速度/级");
        provider.add("gtceu.multiblock.chemical_plant.tooltip3", "§b管道方块：§e+2§b 并行/级");
        provider.add("gtceu.multiblock.chemical_plant.tooltip4", "§5机械方块：配方电压支持等级");
        provider.add("gtceu.multiblock.chemical_plant.energy_hatch", "§e支持能源仓: %s §e及以下");
        provider.add("gtceu.multiblock.chemical_plant.parallel_level", "§5并行: %s");
        provider.add("gtceu.multiblock.chemical_plant.heating_coil", "§6提速: %s%%");
        provider.add("gtceu.multiblock.chemical_plant.tier", "§e配方电压最大支持: %s");
        provider.add("gtceu.machine.neutron_accelerator.tooltip1", "§o§7输入EU，加速中子!");
        provider.add("gtceu.machine.neutron_accelerator.tooltip2", "§6最大EU输入: %s");
        provider.add("gtceu.machine.neutron_accelerator.tooltip3", "§6最大EU消耗: %s");
        provider.add("gtceu.machine.neutron_accelerator.tooltip4", "§b每点EU都会转化为§e10~20-eV§b中子动能.");
        provider.add("gtceu.neutron_activator", "中子活化器");
        provider.add("gtceu.multiblock.neutron_activator.tooltip1", "§o§7超光速运动!");
        provider.add("gtceu.multiblock.neutron_activator.tooltip2", "§6额外的高速管道方块提供配方时间减免，同时降低中子加速器的效率");
        provider.add("gtceu.multiblock.neutron_activator.tooltip3", "§6没有中子加速器运行时，中子动能每秒降低§e72KeV§6中子动能");
        provider.add("gtceu.multiblock.neutron_activator.tooltip4", "§6输入石墨/铍粉可以立即吸收§e10MeV§6中子动能");
        provider.add("gtceu.multiblock.neutron_activator.tooltip5", "§6当中子动能超过§41200MeV§6后将会爆炸！");
        provider.add("gtceu.multiblock.neutronactivator.ev", "当前中子动能: %deV");
        provider.add("gtceu.multiblock.neutronactivator.height", "高度: %s");
        provider.add("gtceu.multiblock.neutronactivator.efficiency", "耗时: %s%%");
        provider.add("gtnn.recipe.condition.neutron_activator_condition_tooltip", "最小中子动能:\n%s MeV\n最大中子动能:\n%s MeV");
    }
}
