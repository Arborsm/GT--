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
        Config();
    }
    public void Config() {
        dfs(new HashSet<>(), Configuration.registerConfig(ConfigHandler.class, ConfigFormats.yaml()).getValueMap());
    }

    private void addMachine() {
        add("block.gtceu.chemical_plant", "埃克森美孚化工厂");
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
