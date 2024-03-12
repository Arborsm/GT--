package org.arbor.gtnn.data.lang;

import static org.arbor.gtnn.data.GTNNMaterials.*;
import static org.arbor.gtnn.data.lang.LangHandler.*;

public class MaterialLang {
    public static void init() {
        tagPrefix();
        materials();
        jeiOreVeins();
    }

    private static void tagPrefix() {
        tsl("tagprefix.blackstone", "嵌%s黑石", "Blackstone %s Ore");
        tsl("tagprefix.glacio_stone", "坚冰岩%s矿石", "Glacio Stone %s Ore");
        tsl("tagprefix.mars_stone", "深红岩%s矿石", "Mars Stone %s Ore");
        tsl("tagprefix.mercury_stone", "旱海岩%s矿石", "Mercury Stone %s Ore");
        tsl("tagprefix.moon_stone", "月岩%s矿石", "Moon Stone %s Ore");
        tsl("tagprefix.soul_soil", "含%s灵魂土", "Soul Soil %s Ore");
        tsl("tagprefix.venus_stone", "锃金岩%s矿石", "Venus Stone %s Ore");
    }


    private static void materials() {
        translateMaterial(AndesiteAlloy, "安山合金");
        translateMaterial(SpaceNeutronium, "中子");
        translateMaterial(Infinity, "无尽");
        translateMaterial(InfinityCatalyst, "无尽催化剂");
        translateMaterial(Desh, "戴斯");
        translateMaterial(Ostrum, "紫金");
        translateMaterial(Calorite, "耐热金属");
        translateMaterial(RP1, "RP-1混合燃料");
        translateMaterial(RP1RocketFuel, "RP-1火箭燃料");
        translateMaterial(Kerosene, "煤油");
        translateMaterial(DenseHydrazineMixedFuel, "浓缩肼混合燃料");
        translateMaterial(Hydrazine, "肼");
        translateMaterial(HydrogenPeroxide, "过氧化氢");
        translateMaterial(EthylAnthraQuinone, "乙基蒽醌");
        translateMaterial(EthylAnthraHydroQuinone, "乙基蒽醌醇");
        translateMaterial(Anthracene, "蒽");
        translateMaterial(MethylhydrazineNitrateRocketFuel, "CN3H7O3火箭燃料");
        translateMaterial(MethylHydrazine, "甲基肼");
        translateMaterial(UDMHRocketFuel, "H8N4C2O4火箭燃料");
        translateMaterial(UDMH, "偏二甲肼");
        translateMaterial(OrangeMetalCatalyst, "橙色金属催化剂");
        translateMaterial(PhthalicAnhydride, "邻苯二甲酸酐");
        translateMaterial(VanadiumPentoxide, "五氧化二钒");
        translateMaterial(BlackMatter, "黑物质");
        translateMaterial(Cerrobase140, "铋铅合金140");
        translateMaterial(ManaSteel, "魔力钢");
        translateMaterial(TerraSteel, "泰拉钢");
        translateMaterial(Elementium, "源质钢");
        translateMaterial(ShadowSteel, "暗影钢");
        translateMaterial(RefinedRadiance, "光辉石");
        translateMaterial(PlatinumSalt, "铂盐");
        translateMaterial(PlatinumSlag, "铂渣");
        translateMaterial(RefinedPlatinumSalt, "精炼铂盐");
        translateMaterial(PalladiumSalt, "钯盐");
        translateMaterial(RhodiumFilterCake, "铑滤饼");
        translateMaterial(RhodiumNitrate, "硝酸铑");
        translateMaterial(RoughlyRhodiumMetal, "粗制铑金属");
        translateMaterial(PalladiumMetal, "钯金属");
        translateMaterial(SodiumRutheniate, "钌酸钠");
        translateMaterial(IridiumDioxide, "二氧化铱");
        translateMaterial(MetalSludge, "金属泥渣");
        translateMaterial(ConcentratedPlatinum, "浓缩铂");
        translateMaterial(RhodiumSulfateGas, "硫酸铑");
        translateMaterial(PalladiumRichAmmonia, "富钯氨");
        translateMaterial(RhodiumSalt, "铑盐");
        translateMaterial(RutheniumTetroxideHot, "热四氧化钌");
        translateMaterial(RutheniumTetroxideLQ, "四氧化钌");
        translateMaterial(ReprecipitatedRhodium, "再沉淀铑");
        translateMaterial(AcidicIridium, "酸性铱");
        translateMaterial(PlatinumMetal, "铂金属");
        translateMaterial(SodiumPyrosulfate, "焦硫酸钠");
        translateMaterial(SodiumFormate, "甲酸钠");
        translateMaterial(FormicAcid, "甲酸");
        translateMaterial(SodiumSulfate, "硫酸钠");
        translateMaterial(SodiumNitrate, "硝酸钠");
        translateMaterial(ZincSulfate, "硫酸锌");
        translateMaterial(Kaolinite, "高岭石");
        translateMaterial(Dolomite, "白云石");
        translateMaterial(Wollastonite, "硅灰石");
        translateMaterial(ArcaneCrystal, "神秘水晶");
        translateMaterial(NaquadahOxideMixture, "氧化硅岩混合物");
        translateMaterial(EnrichedNaquadahOxideMixture, "氧化富集硅岩混合物");
        translateMaterial(NaquadriaOxideMixture, "氧化超能硅岩混合物");
        translateMaterial(HexafluorideEnrichedNaquadahSolution, "六氟化富集硅岩溶液");
        translateMaterial(XenonHexafluoroEnrichedNaquadate, "六氟氙酸富集硅岩");
        translateMaterial(PalladiumOnCarbon, "钯碳催化剂");
        translateMaterial(GoldTrifluoride, "三氟化金");
        translateMaterial(EnrichedNaquadahResidueSolution, "富集硅岩残余物溶液");
        translateMaterial(XenoauricFluoroantimonicAcid, "氟锑酸二氙");
        translateMaterial(GoldChloride, "氯化金");
        translateMaterial(BromineTrifluoride, "三氟化溴");
        translateMaterial(HexafluorideNaquadriaSolution, "六氟化超能硅岩溶液");
        translateMaterial(RadonDifluoride, "二氟化氡");
        translateMaterial(RadonNaquadriaOctafluoride, "八氟超能硅岩酸氡");
        translateMaterial(NaquadriaResidueSolution, "超能硅岩残余物溶液");
        translateMaterial(CaesiumFluoride, "氟化铯");
        translateMaterial(XenonTrioxide, "三氧化氙");
        translateMaterial(CaesiumXenontrioxideFluoride, "二氟三氧氙酸铯");
        translateMaterial(NaquadriaCaesiumXenonnonfluoride, "九氟氙酸超能硅岩铯");
        translateMaterial(RadonTrioxide, "三氧化氡");
        translateMaterial(NaquadriaCaesiumfluoride, "二氟超能硅岩酸铯");
        translateMaterial(NitrosoniumOctafluoroxenate, "八氟氙酸亚硝酰");
        translateMaterial(NitrylFluoride, "硝酰氟");
        translateMaterial(AcidicNaquadriaCaesiumfluoride, "硫酸二氟超能硅岩酸铯");
        translateMaterial(GraphiteUraniumMixture, "石墨-铀混合物");
        translateMaterial(PlutoniumOxideUraniumMixture, "氧化钚-铀混合物");
        translateMaterial(UraniumCarbideThoriumMixture, "碳化铀-钍混合物");
        translateMaterial(ThoriumBasedLiquidFuel, "钍基流体燃料");
        translateMaterial(ThoriumBasedLiquidFuelExcited, "钍基流体燃料(激发态)");
        translateMaterial(ThoriumBasedLiquidFuelDepleted, "钍基流体燃料(枯竭态)");
        translateMaterial(UraniumBasedLiquidFuel, "铀基流体燃料");
        translateMaterial(UraniumBasedLiquidFuelExcited, "铀基流体燃料(激发态)");
        translateMaterial(UraniumBasedLiquidFuelDepleted, "铀基流体燃料(枯竭态)");
        translateMaterial(PlutoniumBasedLiquidFuel, "钚基流体燃料");
        translateMaterial(PlutoniumBasedLiquidFuelExcited, "钚基流体燃料(激发态)");
        translateMaterial(PlutoniumBasedLiquidFuelDepleted, "钚基流体燃料(枯竭态)");
        translateMaterial(RadiationProtection, "防辐射");
        translateMaterial(NaquadahBasedLiquidFuel, "硅岩流体燃料");
        translateMaterial(NaquadahBasedLiquidFuelExcited, "硅岩流体燃料(激发态)");
        translateMaterial(NaquadahBasedLiquidFuelDepleted, "硅岩流体燃料(枯竭态)");
        translateMaterial(Thorium232, "钍-232");
    }

    private static void jeiOreVeins() {
        translateOreVein("kaolinite_vein", "高岭石矿脉");
        translateOreVein("wollastonite_vein", "白云石矿脉");
        translateOreVein("galena_vein_tf", "方铅矿脉");
        translateOreVein("sapphire_vein_tf", "蓝宝石矿脉");
        translateOreVein("olivine_vein_tf", "橄榄石矿脉");
        translateOreVein("nickel_vein_tf", "镍矿脉");
        translateOreVein("diamond_vein_tf", "钻石矿脉");
        translateOreVein("lapis_vein_tf", "青金石矿脉");
        translateOreVein("molybdenite_vein_tf", "辉钼矿脉");
        translateOreVein("coal_vein_tf", "煤矿脉");
        translateOreVein("lubricant_vein_tf", "皂石矿脉");
        translateOreVein("gold_vein_tf", "金矿脉");
        translateOreVein("iron_vein_tf", "铁矿脉");
        translateOreVein("apatite_vein_tf", "磷灰石矿脉");
        translateOreVein("salts_vein_tf", "盐矿脉");
        translateOreVein("cassiterite_vein_tf", "锡石矿脉");
        translateOreVein("monazite_vein_n", "独居石矿脉");
        translateOreVein("bauxite_vein", "铝土矿脉");
        translateOreVein("ilmenite_vein", "钛铁矿脉");
        translateOreVein("quartzite_vein", "石英岩矿脉");
        translateOreVein("molybdenum_vein_ad", "钼矿脉");
        translateOreVein("galena_vein_ad", "方铅矿脉");
        translateOreVein("copper_vein_ad", "铜矿脉");
        translateOreVein("cassiterite_vein_ad", "锡石矿脉");
        translateOreVein("desh_vein_ad", "戴斯矿脉");
        translateOreVein("ostrum_vein_ad", "紫金矿脉");
        translateOreVein("arsenic_vein_ad", "砷矿脉");
        translateOreVein("pitchblende_vein_ad", "沥青铀矿脉");
        translateOreVein("tuff_uraninite_vein_ad", "晶质铀矿脉");
        translateOreVein("scheelite_vein_ad", "白钨矿脉");
        translateOreVein("sulfur_vein_ad", "硫矿脉");
        translateOreVein("redstone_vein_ad", "红石矿脉");
        translateOreVein("nickel_vein_ad", "镍矿脉");
        translateOreVein("magnetite_vein_ad", "磁铁矿脉");
        translateOreVein("iron_vein_ad", "铁矿脉");
        translateOreVein("beryllium_vein_ad", "铍矿脉");
        translateOreVein("tetrahedrite_vein_ad", "黝铜矿脉");
        translateOreVein("salts_vein_ad", "盐矿脉");
        translateOreVein("naquadah_vein_ad_mars", "硅岩矿脉");
        translateOreVein("chromium_vein_ad", "铬矿脉");
        translateOreVein("uranium238_vein_ad", "铀238矿脉");
        translateOreVein("magnesite_vein_ad", "菱镁矿脉");
        translateOreVein("platinum_vein_ad", "铂矿脉");
        translateOreVein("lapis_vein_ad", "青金石矿脉");
        translateOreVein("olivine_vein_ad", "橄榄石矿脉");
        translateOreVein("manganese_vein_ad", "锰矿脉");
        translateOreVein("lubricant_vein_ad", "皂石矿脉");
        translateOreVein("saltpeter_vein_ad", "蓝石矿脉");
        translateOreVein("calorite_vein_ad", "耐热合金矿脉");
        translateOreVein("rutile_vein_ad", "金红石矿脉");
        translateOreVein("iridium_vein_ad", "铱矿脉");
        translateOreVein("pyrolusite_vein_ad", "软锰矿脉");
        translateOreVein("naquadah_vein_ad", "硅岩矿脉");
        translateOreVein("osmium_vein_ad", "锇矿脉");
        translateOreVein("neutronium_vein_ad", "中子素矿脉");
        translateOreVein("niobium_vein_ad", "铌矿脉");
    }


}