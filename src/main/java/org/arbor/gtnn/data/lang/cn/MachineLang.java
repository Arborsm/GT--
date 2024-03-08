package org.arbor.gtnn.data.lang.cn;

import org.arbor.gtnn.api.registry.CNLangProvider;

public class MachineLang {
    public static void init(CNLangProvider provider) {
        machineNames(provider);
        provider.add("block.gtnn.neutron_sensor", "中子传感器");
        provider.add("block.gtnn.chemical_plant", "埃克森美孚化工厂");
        provider.add("block.gtnn.neutron_activator", "中子活化器");
        provider.add("block.gtnn.naquadah_reactor", "硅岩发电机");
        provider.add("gtceu.chemical_plant", "化工厂");
        provider.add("gtceu.neutron_activator", "中子活化");
        provider.add("gtceu.naquadah_reactor", "硅岩发电");
        provider.add("gtceu.dehydrator", "脱水");
        provider.add("gtceu.rocket_engine", "火箭燃料发电");
        provider.add("gtceu.precision_assembly", "精密组装");
        provider.add("gtnn.precision_assembly.tooltip.1", "其他可用配方类型: 精密组装 (GT--)");
        provider.add("gtnn.precision_assembly.tooltip.2", "注意：在精密组装模式下无法并行");
        provider.add("gtnn.recipe.condition.plant_casing.tooltip", "外壳等级: %s (%s)");
        provider.add("gtnn.multiblock.pattern.error.plant_casings", "所有外壳必须相同");
        provider.add("gtnn.multiblock.pattern.error.pipe", "所有管道必须相同");
        provider.add("gtnn.multiblock.pattern.error.machine_casing", "所有机械方块必须相同");
        provider.add("gtnn.multiblock.chemical_plant.tooltip1", "§o§7重工业，现在就在你家门口！");
        provider.add("gtnn.multiblock.chemical_plant.tooltip2", "§6线圈：§e+50%§6 速度/级");
        provider.add("gtnn.multiblock.chemical_plant.tooltip3", "§b管道方块：§e+2§b 并行/级");
        provider.add("gtnn.multiblock.chemical_plant.tooltip4", "§5机械方块：配方电压支持等级");
        provider.add("gtnn.multiblock.chemical_plant.energy_hatch", "§e支持能源仓: %s §e及以下");
        provider.add("gtnn.multiblock.chemical_plant.parallel_level", "§5并行: %s");
        provider.add("gtnn.multiblock.chemical_plant.heating_coil", "§6提速: %s%%");
        provider.add("gtnn.multiblock.chemical_plant.tier", "§e配方电压最大支持: %s");
        provider.add("gtnn.machine.neutron_accelerator.tooltip1", "§o§7输入EU，加速中子!");
        provider.add("gtnn.machine.neutron_accelerator.tooltip2", "§6最大EU输入: %s");
        provider.add("gtnn.machine.neutron_accelerator.tooltip3", "§6最大EU消耗: %s");
        provider.add("gtnn.machine.neutron_accelerator.tooltip4", "§b每点EU都会转化为§e10~20-eV§b中子动能.");
        provider.add("gtnn.multiblock.neutron_activator.tooltip1", "§o§7超光速运动!");
        provider.add("gtnn.multiblock.neutron_activator.tooltip2", "§6额外的高速管道方块提供配方时间减免，同时降低中子加速器的效率");
        provider.add("gtnn.multiblock.neutron_activator.tooltip3", "§6没有中子加速器运行时，中子动能每秒降低§e72KeV§6中子动能");
        provider.add("gtnn.multiblock.neutron_activator.tooltip4", "§6输入石墨/铍粉可以立即吸收§e10MeV§6中子动能");
        provider.add("gtnn.multiblock.neutron_activator.tooltip5", "§6当中子动能超过§41200MeV§6后将会爆炸！");
        provider.add("gtnn.multiblock.neutronactivator.ev", "当前中子动能: %deV");
        provider.add("gtnn.multiblock.neutronactivator.height", "高度: %s");
        provider.add("gtnn.multiblock.neutronactivator.efficiency", "耗时: %s%%");
        provider.add("gtnn.recipe.condition.neutron_activator_condition_tooltip", "最小中子动能:\n%s MeV\n最大中子动能:\n%s MeV");
        provider.add("block.gtnn.neutron_sensor.tooltip1", "§7可安装在§b中子活化器§7上");
        provider.add("block.gtnn.neutron_sensor.tooltip2", "基于§6中子动能§7输出红石信号，右键以打开GUI进行设置。");
        provider.add("gtnn.universal.desc.neutron_kinetic_energy.min", "最小中子动能\n(%s)");
        provider.add("gtnn.universal.desc.neutron_kinetic_energy.max", "最大中子动能\n(%s)");
        provider.add("gui.gtnn.neutron_sensor.invert.enabled", "输出：反转\n\n切换以反转红石逻辑\n默认情况下，中子动能介于所设定的最小值和最大值之间时传感器将发出红石信号，小于最小值时则停止发出红石信号");
        provider.add("gui.gtnn.neutron_sensor.invert.disabled", "输出：正常\n\n切换以反转红石逻辑\n默认情况下，中子动能介于所设定的最小值和最大值之间时传感器将发出红石信号，小于最小值时则停止发出红石信号");
        provider.add("gtnn.machine.naquadah_reactor.tooltip", "效率: %s%%");
        provider.add("gtnn.machine.rocket_engine.tooltip", "效率: %s%%");
    }

    private static void machineNames(CNLangProvider provider) {
        provider.add("block.gtnn.mv_dehydrator", "§b高级脱水机 §r");
        provider.add("block.gtnn.hv_dehydrator", "§6高级脱水机 II§r");
        provider.add("block.gtnn.ev_dehydrator", "§5高级脱水机 III§r");
        provider.add("block.gtnn.iv_dehydrator", "§9精英脱水机 §r");
        provider.add("block.gtnn.luv_dehydrator", "§d精英脱水机 II§r");
        provider.add("block.gtnn.zpm_dehydrator", "§c精英脱水机 III§r");
        provider.add("block.gtnn.ev_naquadah_reactor", "§5高级硅岩发电机 I");
        provider.add("block.gtnn.iv_naquadah_reactor", "§9精英硅岩发电机 II");
        provider.add("block.gtnn.luv_naquadah_reactor", "§d精英硅岩发电机 III");
        provider.add("block.gtnn.zpm_naquadah_reactor", "§c精英硅岩发电机 IV");
        provider.add("block.gtnn.uv_naquadah_reactor", "§3终极硅岩发电机 V");
        provider.add("block.gtnn.ev_rocket_engine", "§5高级火箭引擎发电机 I");
        provider.add("block.gtnn.iv_rocket_engine", "§9精英火箭引擎发电机 II");
        provider.add("block.gtnn.luv_rocket_engine", "§d精英火箭引擎发电机 III");
    }
}
