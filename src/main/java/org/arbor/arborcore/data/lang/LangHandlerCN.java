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

public class LangHandlerCN extends LanguageProvider {

    public LangHandlerCN(DataGenerator gen, String locale) {
        super(gen.getPackOutput(), ArborCore.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        addMachine();
        addMaterial();
        addThings();
        Config();
    }
    public void Config() {
        dfs(new HashSet<>(), Configuration.registerConfig(ConfigHandler.class, ConfigFormats.yaml()).getValueMap());
    }

    private void addMachine() {
        add("block.arbor.chemical_plant", "埃克森美孚化工厂");
        add("gtceu.chemical_plant", "埃克森美孚化工厂");
        add("arbor.recipe.condition.plant_casing.tooltip", "外壳等级: %s (%s)");
        add("arbor.multiblock.pattern.error.plant_casings", "所有外壳必须相同");
        add("arbor.multiblock.pattern.error.pipe", "所有管道必须相同");
        add("arbor.multiblock.pattern.error.machine_casing", "所有机械方块必须相同");
        add("gtceu.multiblock.chemical_plant.tooltip1", "§o§7重工业，现在就在你家门口！");
        add("gtceu.multiblock.chemical_plant.tooltip2", "§6线圈：§e+50%§6 速度/级");
        add("gtceu.multiblock.chemical_plant.tooltip3", "§b管道方块：§e+2§b 并行/级");
        add("gtceu.multiblock.chemical_plant.tooltip4", "§5机械方块：配方电压支持等级");
        add("gtceu.multiblock.chemical_plant.energy_hatch", "§e支持能源仓: %s §e及以下");
        add("gtceu.multiblock.chemical_plant.parallel_level", "§b并行: %s");
        add("gtceu.multiblock.chemical_plant.heating_coil", "§6提速: %s%%");
        add("block.arbor.lv_neutron_accelerator", "§7LV中子加速器");
        add("block.arbor.mv_neutron_accelerator", "§bMV中子加速器");
        add("block.arbor.hv_neutron_accelerator", "§6HV中子加速器");
        add("block.arbor.ev_neutron_accelerator", "§5EV中子加速器");
        add("block.arbor.iv_neutron_accelerator", "§1IV中子加速器");
        add("block.arbor.luv_neutron_accelerator", "§dLuV中子加速器");
        add("block.arbor.zpm_neutron_accelerator", "§cZPM中子加速器");
        add("block.arbor.uv_neutron_accelerator", "§3UV中子加速器");
        add("gtceu.machine.neutron_accelerator.tooltip1", "§o§7输入EU，加速中子!");
        add("gtceu.machine.neutron_accelerator.tooltip2", "§6最大EU输入: %s");
        add("gtceu.machine.neutron_accelerator.tooltip3", "§6最大EU消耗: %s");
        add("gtceu.machine.neutron_accelerator.tooltip4", "§b每点EU都会转化为§e10~20-eV§b中子动能.");
        add("block.arbor.neutron_activator", "中子活化器");
        add("gtceu.neutron_activator", "中子活化器");
        add("gtceu.multiblock.neutron_activator.tooltip1", "§o§7超光速运动!");
        add("gtceu.multiblock.neutron_activator.tooltip2", "§6额外的高速管道方块提供配方时间减免，同时降低中子加速器的效率");
        add("gtceu.multiblock.neutron_activator.tooltip3", "§6没有中子加速器运行时，中子动能每秒降低§e72KeV§6中子动能");
        add("gtceu.multiblock.neutron_activator.tooltip4", "§6输入石墨/铍粉可以立即吸收§e10MeV§6中子动能");
        add("gtceu.multiblock.neutron_activator.tooltip5", "§6当中子动能超过§41200MeV§6后将会爆炸！");
        add("gtceu.multiblock.neutronactivator.ev", "当前中子动能: %deV");
        add("gtceu.multiblock.neutronactivator.height", "高度: %s");
        add("gtceu.multiblock.neutronactivator.efficiency", "效率: %s%%");
        add("arbor.recipe.condition.neutron_activator_condition_tooltip", "最小中子动能:\n%s MeV\n最大中子动能:\n%s MeV");
    }

    private void addThings(){
        add("itemGroup.arbor.arbor", "ArborCore");
        add("block.arbor.high_speed_pipe_block", "高速管道方块");
        add("block.arbor.clean_machine_casing", "洁净机器方块");
        add("item.arbor.radioactive_waste", "放射性废料");
    }

    private void addMaterial() {
        add("tagprefix.blackstone", "嵌%s黑石");
        add("tagprefix.glacio_stone", "坚冰岩%s矿石");
        add("tagprefix.mars_stone", "深红岩%s矿石");
        add("tagprefix.mercury_stone", "旱海岩%s矿石");
        add("tagprefix.moon_stone", "月岩%s矿石");
        add("tagprefix.soul_soil", "含%s灵魂土");
        add("tagprefix.venus_stone", "锃金岩%s矿石");
        add("material.andesite_alloy", "安山合金");
        add("material.space_neutronium", "中子");
        add("material.infinity", "无尽");
        add("material.infinity_catalyst", "无尽催化剂");
        add("material.desh", "戴斯");
        add("material.ostrum", "紫金");
        add("material.calorite", "耐热金属");
        add("material.rp_1_mixed_fuel", "RP-1混合燃料");
        add("material.rp_1_rocket_fuel", "RP-1火箭燃料");
        add("material.kerosene", "煤油");
        add("material.dense_hydrazine_mixed_fuel", "浓缩肼混合燃料");
        add("material.hydrazine", "肼");
        add("material.hydrogen_peroxide", "过氧化氢");
        add("material.ethyl_anthra_quinone", "乙基蒽醌");
        add("material.ethyl_anthra_hydro_quinone", "乙基蒽醌醇");
        add("material.anthracene", "蒽");
        add("material.methylhydrazine_nitrate_rocket_fuel", "CN3H7O3火箭燃料");
        add("material.methyl_hydrazine", "甲基肼");
        add("material.udmh_rocket_fuel", "H8N4C2O4火箭燃料");
        add("material.udmh", "偏二甲肼");
        add("material.orange_metal_catalyst", "橙色金属催化剂");
        add("material.phthalic_anhydride", "邻苯二甲酸酐");
        add("material.vanadium_pentoxide", "五氧化二钒");
        add("material.black_matter", "黑物质");
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
