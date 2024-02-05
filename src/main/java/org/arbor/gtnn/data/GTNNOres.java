package org.arbor.gtnn.data;

import com.gregtechceu.gtceu.api.data.worldgen.GTOreDefinition;
import com.gregtechceu.gtceu.api.data.worldgen.WorldGenLayers;
import com.gregtechceu.gtceu.api.data.worldgen.generator.indicators.SurfaceIndicatorGenerator;
import com.gregtechceu.gtceu.common.data.GTMaterials;
import com.gregtechceu.gtceu.common.data.GTOres;
import earth.terrarium.adastra.common.registry.ModBlocks;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import org.arbor.gtnn.GTNN;
import org.arbor.gtnn.GTNNIntegration;

import java.util.function.Consumer;

import static org.arbor.gtnn.data.GTNNMaterials.*;
import static org.arbor.gtnn.data.GTNNWorld.*;
import static org.arbor.gtnn.data.GTNNWorld.GTNNWorldGenLayers.AD;
import static org.arbor.gtnn.data.GTNNWorld.GTNNWorldGenLayers.TF;

@SuppressWarnings("unused")
public class GTNNOres {
    public static final GTOreDefinition kaolinite_vein = create("kaolinite_vein", vein -> vein
            .clusterSize(24)
            .weight(60)
            .layer(WorldGenLayers.STONE)
            .density(0.4f)
            .dimensions(BuiltinDimensionTypes.OVERWORLD.location())
            .heightRangeUniform(30, 70)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(2).mat(Kaolinite).size(1, 3))
                            .layer(l -> l.weight(2).mat(GTMaterials.Zeolite).size(1, 3))
                            .layer(l -> l.weight(2).mat(GTMaterials.FullersEarth).size(1, 3))
                            .layer(l -> l.weight(1).mat(GTMaterials.GlauconiteSand).size(1, 1)))), true
    );
    public static final GTOreDefinition WOLLASTONITE_VEIN = create("wollastonite_vein", vein -> vein
            .clusterSize(36)
            .weight(40)
            .layer(WorldGenLayers.STONE)
            .density(0.4f)
            .dimensions(BuiltinDimensionTypes.OVERWORLD.location())
            .heightRangeUniform(120, 200)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(2).mat(Dolomite).size(1, 3))
                            .layer(l -> l.weight(2).mat(Wollastonite).size(1, 3))
                            .layer(l -> l.weight(2).mat(GTMaterials.Trona).size(1, 3))
                            .layer(l -> l.weight(1).mat(GTMaterials.Andradite).size(1, 1)))), true
    );
    public static final GTOreDefinition GALENA_VEIN_TF = create("galena_vein_tf", vein -> vein
            .clusterSize(30)
            .weight(40)
            .layer(TF)
            .density(0.25f)
            .dimensions(TWILIGHT_FOREST)
            .heightRangeUniform(-30, 0)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Galena).size(2, 4))
                            .layer(l -> l.weight(3).mat(GTMaterials.Silver).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Lead).size(1, 1))
                    ))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Galena)
                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)
                    .density(0.4f)
                    .radius(5)), GTNNIntegration.isTwilightForestLoaded()
    );
    public static final GTOreDefinition SAPPHIRE_VEIN_TF = create("sapphire_vein_tf", vein -> vein
            .clusterSize(25)
            .weight(60)
            .layer(TF)
            .density(0.25f)
            .dimensions(TWILIGHT_FOREST)
            .heightRangeUniform(-30, 0)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Almandine).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Pyrope).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Sapphire).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.GreenSapphire).size(1, 1))
                    ))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Sapphire)
                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)
                    .density(0.4f)
                    .radius(5)), GTNNIntegration.isTwilightForestLoaded()
    );
    // 橄榄石
    public static final GTOreDefinition OLIVINE_VEIN_TF = create("olivine_vein_tf", vein -> vein
            .clusterSize(30)
            .weight(20)
            .layer(TF)
            .density(0.25f)
            .dimensions(TWILIGHT_FOREST)
            .heightRangeUniform(-30, 0)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Bentonite).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Magnetite).size(1, 1))
                            .layer(l -> l.weight(2).mat(GTMaterials.Olivine).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.GlauconiteSand).size(1, 1))
                    ))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Olivine)
                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)
                    .density(0.4f)
                    .radius(5)), GTNNIntegration.isTwilightForestLoaded()
    );
    // 镍
    public static final GTOreDefinition NICKEL_VEIN_TF = create("nickel_vein_tf", vein -> vein
            .clusterSize(30)
            .weight(40)
            .layer(TF)
            .density(0.25f)
            .dimensions(TWILIGHT_FOREST)
            .heightRangeUniform(-30, 0)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Garnierite).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Nickel).size(1, 1))
                            .layer(l -> l.weight(2).mat(GTMaterials.Cobaltite).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Pentlandite).size(1, 1))
                    ))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Nickel)
                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)
                    .density(0.4f)
                    .radius(5)), GTNNIntegration.isTwilightForestLoaded()
    );
    // 钻石
    public static final GTOreDefinition DIAMOND_VEIN_TF = create("diamond_vein_tf", vein -> vein
            .clusterSize(30)
            .weight(40)
            .layer(TF)
            .density(0.25f)
            .dimensions(TWILIGHT_FOREST)
            .heightRangeUniform(-30, 0)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Graphite).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Diamond).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Coal).size(1, 1))
                    ))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Diamond)
                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)
                    .density(0.4f)
                    .radius(5)), GTNNIntegration.isTwilightForestLoaded()
    );
    // 青金石
    public static final GTOreDefinition LAPIS_VEIN_TF = create("lapis_vein_tf", vein -> vein
            .clusterSize(40)
            .weight(40)
            .layer(TF)
            .density(0.25f)
            .dimensions(TWILIGHT_FOREST)
            .heightRangeUniform(-30, 0)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Lazurite).size(1, 3))
                            .layer(l -> l.weight(2).mat(GTMaterials.Sodalite).size(1, 2))
                            .layer(l -> l.weight(2).mat(GTMaterials.Lapis).size(1, 2))
                            .layer(l -> l.weight(1).mat(GTMaterials.Calcite).size(1, 1))
                    ))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Lapis)
                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)
                    .density(0.4f)
                    .radius(5)), GTNNIntegration.isTwilightForestLoaded()
    );
    // 钼
    public static final GTOreDefinition MOLYBDENITE_VEIN_TF = create("molybdenite_vein_tf", vein -> vein
            .clusterSize(25)
            .weight(5)
            .layer(TF)
            .density(0.25f)
            .dimensions(TWILIGHT_FOREST)
            .heightRangeUniform(-30, 0)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Wulfenite).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Molybdenite).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Molybdenum).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Powellite).size(1, 1))
                    ))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Molybdenite)
                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)
                    .density(0.4f)
                    .radius(5)), GTNNIntegration.isTwilightForestLoaded()
    );
    // 煤
    public static final GTOreDefinition COAL_VEIN_TF = create("coal_vein_tf", vein -> vein
            .clusterSize(35)
            .weight(80)
            .layer(TF)
            .density(0.25f)
            .dimensions(TWILIGHT_FOREST)
            .heightRangeUniform(-30, 0)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Coal).size(2, 4))
                            .layer(l -> l.weight(3).mat(GTMaterials.Coal).size(2, 4))
                    ))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Coal)
                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)
                    .density(0.4f)
                    .radius(5)), GTNNIntegration.isTwilightForestLoaded()
    );
    // 皂石
    public static final GTOreDefinition LUBRICANT_VEIN_TF = create("lubricant_vein_tf", vein -> vein
            .clusterSize(25)
            .weight(40)
            .layer(TF)
            .density(0.25f)
            .dimensions(TWILIGHT_FOREST)
            .heightRangeUniform(-30, 0)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Soapstone).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Talc).size(1, 1))
                            .layer(l -> l.weight(2).mat(GTMaterials.GlauconiteSand).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Pentlandite).size(1, 1))
                    ))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Soapstone)
                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)
                    .density(0.4f)
                    .radius(5)), GTNNIntegration.isTwilightForestLoaded()
    );
    // 金
    public static final GTOreDefinition GOLD_VEIN_TF = create("gold_vein_tf", vein -> vein
            .clusterSize(35)
            .weight(80)
            .layer(TF)
            .density(0.15f)
            .dimensions(TWILIGHT_FOREST)
            .heightRangeUniform(-30, 0)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Magnetite).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.VanadiumMagnetite).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Gold).size(1, 1))
                    ))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Gold)
                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)
                    .density(0.4f)
                    .radius(5)), GTNNIntegration.isTwilightForestLoaded()
    );
    // 铁
    public static final GTOreDefinition IRON_VEIN_TF = create("iron_vein_tf", vein -> vein
            .clusterSize(36)
            .weight(120)
            .layer(TF)
            .density(0.3f)
            .dimensions(TWILIGHT_FOREST)
            .heightRangeUniform(-30, 0)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(5).mat(GTMaterials.Goethite).size(1, 3))
                            .layer(l -> l.weight(2).mat(GTMaterials.YellowLimonite).size(1, 2))
                            .layer(l -> l.weight(2).mat(GTMaterials.Hematite).size(1, 2))
                            .layer(l -> l.weight(1).mat(GTMaterials.Malachite).size(1, 2))
                    ))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Goethite)
                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)
                    .density(0.4f)
                    .radius(5)), GTNNIntegration.isTwilightForestLoaded()
    );
    // 磷灰石
    public static final GTOreDefinition APATITE_VEIN_TF = create("apatite_vein_tf", vein -> vein
            .clusterSize(30)
            .weight(40)
            .layer(TF)
            .density(0.25f)
            .dimensions(TWILIGHT_FOREST)
            .heightRangeUniform(-30, 0)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Apatite).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.TricalciumPhosphate).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Pyrochlore).size(1, 1))
                    ))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Apatite)
                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)
                    .density(0.4f)
                    .radius(5)), GTNNIntegration.isTwilightForestLoaded()
    );
    // 盐
    public static final GTOreDefinition SALTS_VEIN_TF = create("salts_vein_tf", vein -> vein
            .clusterSize(30)
            .weight(50)
            .layer(TF)
            .density(0.2f)
            .dimensions(TWILIGHT_FOREST)
            .heightRangeUniform(-30, 0)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.RockSalt).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Salt).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Lepidolite).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Spodumene).size(1, 1))
                    ))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Salt)
                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)
                    .density(0.4f)
                    .radius(5)), GTNNIntegration.isTwilightForestLoaded()
    );
    // 锡
    public static final GTOreDefinition CASSITERITE_VEIN_TF = create("cassiterite_vein_tf", vein -> vein
            .clusterSize(36)
            .weight(50)
            .layer(TF)
            .density(0.4f)
            .dimensions(TWILIGHT_FOREST)
            .heightRangeUniform(-30, 0)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Tin).size(2, 3))
                            .layer(l -> l.weight(1).mat(GTMaterials.Cassiterite).size(1, 2))
                    ))
            .surfaceIndicatorGenerator(indicator -> indicator
                    .surfaceRock(GTMaterials.Tin)
                    .placement(SurfaceIndicatorGenerator.IndicatorPlacement.ABOVE)
                    .density(0.4f)
                    .radius(5)), GTNNIntegration.isTwilightForestLoaded()
    );
    // moon
    // 独居石
    public static final GTOreDefinition MONAZITE_VEIN_N = create("monazite_vein_n", vein -> vein
            .clusterSize(24)
            .weight(30)
            .layer(AD)
            .density(0.2f)
            .dimensions(MOON, VENUS, GLACIO)
            .heightRangeUniform(20, 40)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Bastnasite).size(2, 4))
                            .layer(l -> l.weight(1).mat(GTMaterials.Monazite).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Neodymium).size(1, 1))
                    )));
    // 铝土
    public static final GTOreDefinition BAUXITE_VEIN = create("bauxite_vein", vein -> vein
            .clusterSize(36)
            .weight(80)
            .layer(AD)
            .density(0.3f)
            .dimensions(MOON)
            .heightRangeUniform(10, 80)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(2).state(() -> ModBlocks.MOON_STONE.get().defaultBlockState()).size(1, 6))
                            .layer(l -> l.weight(2).mat(GTMaterials.Bauxite).size(1, 4))
                            .layer(l -> l.weight(1).mat(GTMaterials.Ilmenite).size(1, 2))
                            .layer(l -> l.weight(1).mat(GTMaterials.Aluminium).size(1, 1))
                    )));
    // 钛铁
    public static final GTOreDefinition ILMENITE_VEIN = create("ilmenite_vein", vein -> vein
            .clusterSize(24)
            .weight(16)
            .layer(AD)
            .density(0.2f)
            .dimensions(MOON)
            .heightRangeUniform(-70, 10)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Ilmenite).size(1, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Chromite).size(1, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Uvarovite).size(1, 2))
                            .layer(l -> l.weight(1).mat(GTMaterials.Perlite).size(1, 1))
                    )));
    // 石英
    public static final GTOreDefinition QUARTZITE_VEIN = create("quartzite_vein", vein -> vein
            .clusterSize(24)
            .weight(20)
            .layer(AD)
            .density(0.3f)
            .dimensions(MOON, MARS, VENUS)
            .heightRangeUniform(30, 80)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Quartzite).size(2, 4))
                            .layer(l -> l.weight(3).mat(GTMaterials.Barite).size(2, 4))
                            .layer(l -> l.weight(3).mat(GTMaterials.CertusQuartz).size(2, 4))
                    )));
    // 钼
    public static final GTOreDefinition MOLYBDENUM_VEIN_AD = create("molybdenum_vein_ad", vein -> vein
            .clusterSize(25)
            .weight(5)
            .layer(AD)
            .density(0.25f)
            .dimensions(MOON, MERCURY)
            .heightRangeUniform(20, 50)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Wulfenite).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Molybdenite).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Molybdenum).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Powellite).size(1, 1))
                    )));
    // 方铅矿
    public static final GTOreDefinition GALENA_VEIN_AD = create("galena_vein_ad", vein -> vein
            .clusterSize(30)
            .weight(40)
            .layer(AD)
            .density(0.25f)
            .dimensions(MOON, MARS, VENUS, GLACIO)
            .heightRangeUniform(-15, 45)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Galena).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Silver).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Lead).size(1, 1))
                    )));
    // 铜
    public static final GTOreDefinition COPPER_VEIN_AD = create("copper_vein_ad", vein -> vein
            .clusterSize(36)
            .weight(80)
            .layer(AD)
            .density(0.3f)
            .dimensions(MOON, MERCURY)
            .heightRangeUniform(-40, 15)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(2).mat(GTMaterials.Chalcopyrite).size(2, 3))
                            .layer(l -> l.weight(1).mat(GTMaterials.Iron).size(1, 2))
                            .layer(l -> l.weight(1).mat(GTMaterials.Pyrite).size(1, 2))
                            .layer(l -> l.weight(1).mat(GTMaterials.Copper).size(1, 2))
                    )));
    // 锡石
    public static final GTOreDefinition CASSITERITE_VEIN_AD = create("cassiterite_vein_ad", vein -> vein
            .clusterSize(36)
            .weight(50)
            .layer(AD)
            .density(0.4f)
            .dimensions(MOON, VENUS)
            .heightRangeUniform(10, 80)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Tin).size(2, 3))
                            .layer(l -> l.weight(1).mat(GTMaterials.Cassiterite).size(1, 2))
                    )));
    // 戴斯
    public static final GTOreDefinition DESH_VEIN_AD = create("desh_vein_ad", vein -> vein
            .clusterSize(24)
            .weight(30)
            .layer(AD)
            .density(0.3f)
            .dimensions(MOON)
            .heightRangeUniform(5, 40)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(Desh).size(2, 3))
                                    .layer(l -> l.weight(1).mat(ArcaneCrystal).size(1, 2))
                            //.layer(l -> l.weight(1).state(() -> Block.getBlock("forbidden_arcanus:xpetrified_ore").defaultBlockState()))
                    )));
    // Mars
    // 紫金
    public static final GTOreDefinition OSTRUM_VEIN_AD = create("ostrum_vein_ad", vein -> vein
            .clusterSize(24)
            .weight(30)
            .layer(AD)
            .density(0.3f)
            .dimensions(MARS)
            .heightRangeUniform(5, 40)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(Ostrum).size(2, 3))
                            .layer(l -> l.weight(1).mat(GTMaterials.Scheelite).size(1, 2))
                            .layer(l -> l.weight(1).mat(GTMaterials.Tungstate).size(1, 1))
                    )));
    // 坤
    public static final GTOreDefinition ARSENIC_VEIN_AD = create("arsenic_vein_ad", vein -> vein
            .clusterSize(32)
            .weight(60)
            .layer(AD)
            .density(0.4f)
            .dimensions(MARS)
            .heightRangeUniform(40, 60)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(1).mat(GTMaterials.Arsenic).size(1, 2))
                            .layer(l -> l.weight(1).mat(GTMaterials.Bismuth).size(1, 2))
                            .layer(l -> l.weight(1).mat(GTMaterials.Antimony).size(1, 2))
                    )));
    // 沥青铀
    public static final GTOreDefinition PITCHBLENDE_VEIN_AD = create("pitchblende_vein_ad", vein -> vein
            .clusterSize(24)
            .weight(40)
            .layer(AD)
            .density(0.2f)
            .dimensions(MARS, VENUS)
            .heightRangeUniform(20, 60)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(2).mat(GTMaterials.Pitchblende).size(1, 2))
                            .layer(l -> l.weight(2).mat(GTMaterials.Uraninite).size(1, 2))
                    )));
    // 晶质铀
    public static final GTOreDefinition TUFF_URANINITE_VEIN_AD = create("tuff_uraninite_vein_ad", vein -> vein
            .clusterSize(24)
            .weight(20)
            .layer(AD)
            .density(0.2f)
            .dimensions(MARS, MERCURY)
            .heightRangeUniform(20, 30)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(2).mat(GTMaterials.Uraninite).size(1, 2))
                            .layer(l -> l.weight(2).mat(GTMaterials.Uranium238).size(1, 2))
                    )));
    // 钨酸锂
    public static final GTOreDefinition SCHEELITE_VEIN_AD = create("scheelite_vein_ad", vein -> vein
            .clusterSize(24)
            .weight(16)
            .layer(AD)
            .density(0.2f)
            .dimensions(MARS, GLACIO)
            .heightRangeUniform(20, 60)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Scheelite).size(2, 4))
                            .layer(l -> l.weight(3).mat(GTMaterials.Tungstate).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Lithium).size(1, 1))
                    )));
    // 硫
    public static final GTOreDefinition SULFUR_VEIN_AD = create("sulfur_vein_ad", vein -> vein
            .clusterSize(30)
            .weight(100)
            .layer(AD)
            .density(0.2f)
            .dimensions(MARS, VENUS)
            .heightRangeUniform(10, 30)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Sulfur).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Pyrite).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Sphalerite).size(1, 1))
                    )));
    // 红石
    public static final GTOreDefinition REDSTONE_VEIN_AD = create("redstone_vein_ad", vein -> vein
            .clusterSize(30)
            .weight(60)
            .layer(AD)
            .density(0.2f)
            .dimensions(MARS, VENUS)
            .heightRangeUniform(5, 40)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Redstone).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Ruby).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Cinnabar).size(1, 1))
                    )));
    // 镍
    public static final GTOreDefinition NICKEL_VEIN_AD = create("nickel_vein_ad", vein -> vein
            .clusterSize(30)
            .weight(40)
            .layer(AD)
            .density(0.25f)
            .dimensions(MARS, VENUS, GLACIO)
            .heightRangeUniform(-10, 60)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Garnierite).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Nickel).size(1, 1))
                            .layer(l -> l.weight(2).mat(GTMaterials.Cobaltite).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Pentlandite).size(1, 1))
                    )));
    // 金
    public static final GTOreDefinition MAGNETITE_VEIN_AD = create("magnetite_vein_ad", vein -> vein
            .clusterSize(35)
            .weight(80)
            .layer(AD)
            .density(0.15f)
            .dimensions(MARS, MERCURY, GLACIO)
            .heightRangeUniform(10, 60)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Magnetite).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.VanadiumMagnetite).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Gold).size(1, 1))
                    )));
    // 铁
    public static final GTOreDefinition IRON_VEIN_AD = create("iron_vein_ad", vein -> vein
            .clusterSize(36)
            .weight(120)
            .layer(AD)
            .density(0.3f)
            .dimensions(MARS, MERCURY)
            .heightRangeUniform(-10, 60)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(5).mat(GTMaterials.Goethite).size(1, 3))
                            .layer(l -> l.weight(2).mat(GTMaterials.YellowLimonite).size(1, 2))
                            .layer(l -> l.weight(2).mat(GTMaterials.Hematite).size(1, 2))
                            .layer(l -> l.weight(1).mat(GTMaterials.Malachite).size(1, 2))
                    )));
    // 铍
    public static final GTOreDefinition BERYLLIUM_VEIN_AD = create("beryllium_vein_ad", vein -> vein
            .clusterSize(24)
            .weight(30)
            .layer(AD)
            .density(0.2f)
            .dimensions(MARS, MERCURY, VENUS)
            .heightRangeUniform(5, 30)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Beryllium).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Emerald).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Thorium).size(1, 1))
                    )));
    // 黝铜
    public static final GTOreDefinition TETRAHEDRITE_VEIN_AD = create("tetrahedrite_vein_ad", vein -> vein
            .clusterSize(36)
            .weight(70)
            .layer(AD)
            .density(0.3f)
            .dimensions(MARS, VENUS)
            .heightRangeUniform(80, 120)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(4).mat(GTMaterials.Tetrahedrite).size(2, 3))
                            .layer(l -> l.weight(2).mat(GTMaterials.Copper).size(1, 2))
                            .layer(l -> l.weight(1).mat(GTMaterials.Stibnite).size(1, 1))
                    )));
    // 盐
    public static final GTOreDefinition SALTS_VEIN_AD = create("salts_vein_ad", vein -> vein
            .clusterSize(30)
            .weight(50)
            .layer(AD)
            .density(0.2f)
            .dimensions(MARS)
            .heightRangeUniform(30, 70)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.RockSalt).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Salt).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Lepidolite).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Spodumene).size(1, 1))
                    )));
    // 硅岩火星
    public static final GTOreDefinition NAQUADAH_VEIN_AD_MARS = create("naquadah_vein_ad_mars", vein -> vein
            .clusterSize(48)
            .weight(5)
            .layer(AD)
            .density(0.4f)
            .dimensions(MARS)
            .heightRangeUniform(10, 90)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Naquadah).size(2, 4))
                            .layer(l -> l.weight(1).mat(GTMaterials.NaquadahEnriched).size(1, 2))
                    )));
    // 水星
    // 铬
    public static final GTOreDefinition CHROMIUM_VEIN_AD = create("chromium_vein_ad", vein -> vein
            .clusterSize(24)
            .weight(5)
            .layer(AD)
            .density(0.2f)
            .dimensions(MERCURY)
            .heightRangeUniform(-15, 15)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(1).mat(GTMaterials.Chromium).size(1, 2))
                            .layer(l -> l.weight(1).mat(GTMaterials.Tungsten).size(1, 2))
                            .layer(l -> l.weight(1).mat(GTMaterials.Molybdenum).size(1, 2))
                            .layer(l -> l.weight(1).mat(GTMaterials.Manganese).size(1, 2))
                    )));
    // 铀-238
    public static final GTOreDefinition URANIUM238_VEIN_AD = create("uranium238_vein_ad", vein -> vein
            .clusterSize(12)
            .weight(5)
            .layer(AD)
            .density(0.2f)
            .dimensions(MERCURY)
            .heightRangeUniform(65, 120)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(2).mat(GTMaterials.Uranium238).size(1, 2))
                            .layer(l -> l.weight(1).mat(GTMaterials.Plutonium239).size(1, 2))
                            .layer(l -> l.weight(1).mat(GTMaterials.Thorium).size(1, 2))
                    )));
    // 菱镁矿
    public static final GTOreDefinition MAGNESITE_VEIN_AD = create("magnesite_vein_ad", vein -> vein
            .clusterSize(36)
            .weight(55)
            .layer(AD)
            .density(0.4f)
            .dimensions(MERCURY)
            .heightRangeUniform(35, 65)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(2).mat(GTMaterials.Magnesite).size(1, 3))
                            .layer(l -> l.weight(2).mat(GTMaterials.Hematite).size(1, 2))
                            .layer(l -> l.weight(2).mat(GTMaterials.Sulfur).size(1, 2))
                            .layer(l -> l.weight(1).mat(GTMaterials.Opal).size(1, 2))
                    )));
    // 铂
    public static final GTOreDefinition PLATINUM_VEIN_AD = create("platinum_vein_ad", vein -> vein
            .clusterSize(24)
            .weight(10)
            .layer(AD)
            .density(0.2f)
            .dimensions(MERCURY)
            .heightRangeUniform(-5, 25)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Platinum).size(1, 3))
                            .layer(l -> l.weight(2).mat(GTMaterials.Chromium).size(1, 2))
                            .layer(l -> l.weight(1).mat(GTMaterials.Cooperite).size(1, 2))
                    )));
    // 青金石
    public static final GTOreDefinition LAPIS_VEIN_AD = create("lapis_vein_ad", vein -> vein
            .clusterSize(24)
            .weight(40)
            .layer(AD)
            .density(0.3f)
            .dimensions(MERCURY)
            .heightRangeUniform(20, 50)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Lazurite).size(1, 3))
                            .layer(l -> l.weight(2).mat(GTMaterials.Sodalite).size(1, 2))
                            .layer(l -> l.weight(2).mat(GTMaterials.Lapis).size(1, 2))
                            .layer(l -> l.weight(1).mat(GTMaterials.Calcite).size(1, 1))
                    )));
    // 橄榄石
    public static final GTOreDefinition OLIVINE_VEIN_AD = create("olivine_vein_ad", vein -> vein
            .clusterSize(30)
            .weight(30)
            .layer(AD)
            .density(0.25f)
            .dimensions(MERCURY)
            .heightRangeUniform(-20, 10)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Bentonite).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Magnetite).size(1, 1))
                            .layer(l -> l.weight(2).mat(GTMaterials.Olivine).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.GlauconiteSand).size(1, 1))
                    )));
    // 锰
    public static final GTOreDefinition MANGANESE_VEIN_AD = create("manganese_vein_ad", vein -> vein
            .clusterSize(24)
            .weight(20)
            .layer(AD)
            .density(0.2f)
            .dimensions(MERCURY, GLACIO)
            .heightRangeUniform(-30, 0)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(2).mat(GTMaterials.Grossular).size(1, 2))
                            .layer(l -> l.weight(2).mat(GTMaterials.Spessartine).size(1, 2))
                            .layer(l -> l.weight(2).mat(GTMaterials.Pyrolusite).size(1, 2))
                            .layer(l -> l.weight(1).mat(GTMaterials.Tantalite).size(1, 2))
                    )));
    //
    public static final GTOreDefinition LUBRICANT_VEIN_AD = create("lubricant_vein_ad", vein -> vein
            .clusterSize(25)
            .weight(40)
            .layer(AD)
            .density(0.25f)
            .dimensions(MERCURY)
            .heightRangeUniform(0, 50)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Soapstone).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Talc).size(1, 1))
                            .layer(l -> l.weight(2).mat(GTMaterials.GlauconiteSand).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Pentlandite).size(1, 1))
                    )));
    // 蓝石
    public static final GTOreDefinition SALTPETER_VEIN_AD = create("saltpeter_vein_ad", vein -> vein
            .clusterSize(30)
            .weight(40)
            .layer(AD)
            .density(0.25f)
            .dimensions(MERCURY)
            .heightRangeUniform(5, 45)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(2).state(() -> ModBlocks.MERCURY_STONE.get().defaultBlockState()))
                            .layer(l -> l.weight(3).mat(GTMaterials.Saltpeter).size(2, 4))
                            .layer(l -> l.weight(2).mat(GTMaterials.Diatomite).size(1, 1))
                            .layer(l -> l.weight(2).mat(GTMaterials.Electrotine).size(1, 1))
                            .layer(l -> l.weight(1).mat(GTMaterials.Alunite).size(1, 1))
                    )));
    // 金星
    // 耐热合金
    public static final GTOreDefinition CALORITE_VEIN_AD = create("calorite_vein_ad", vein -> vein
            .clusterSize(24)
            .weight(30)
            .layer(AD)
            .density(0.3f)
            .dimensions(VENUS)
            .heightRangeUniform(5, 40)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                                    .layer(l -> l.weight(3).mat(Calorite).size(2, 3))
                                    .layer(l -> l.weight(1).mat(GTMaterials.Amethyst).size(1, 2))
                            //.layer(l -> l.weight(1).state(() -> Block.getBlock("forbidden_arcanus:stella_arcanum").defaultBlockState()))
                    )));
    // 金红石
    public static final GTOreDefinition RUTILE_VEIN_AD = create("rutile_vein_ad", vein -> vein
            .clusterSize(18)
            .weight(8)
            .layer(AD)
            .density(0.4f)
            .dimensions(VENUS)
            .heightRangeUniform(-15, 20)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Rutile).size(1, 3))
                            .layer(l -> l.weight(2).mat(GTMaterials.Titanium).size(1, 2))
                            .layer(l -> l.weight(2).mat(GTMaterials.Bauxite).size(1, 2))
                    )));
    // 铱
    public static final GTOreDefinition IRIDIUM_VEIN_AD = create("iridium_vein_ad", vein -> vein
            .clusterSize(24)
            .weight(10)
            .layer(AD)
            .density(0.2f)
            .dimensions(VENUS)
            .heightRangeUniform(-5, 40)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(2).mat(GTMaterials.Nickel).size(2, 3))
                            .layer(l -> l.weight(1).mat(GTMaterials.Iridium).size(1, 2))
                            .layer(l -> l.weight(1).mat(GTMaterials.Palladium).size(1, 2))
                    )));
    // 软锰
    public static final GTOreDefinition PYROLUSITE_VEIN_AD = create("pyrolusite_vein_ad", vein -> vein
            .clusterSize(24)
            .weight(10)
            .layer(AD)
            .density(0.4f)
            .dimensions(VENUS)
            .heightRangeUniform(0, 30)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Pyrolusite).size(2, 3))
                            .layer(l -> l.weight(2).mat(GTMaterials.Apatite).size(1, 2))
                            .layer(l -> l.weight(2).mat(GTMaterials.Tantalite).size(1, 2))
                            .layer(l -> l.weight(1).mat(GTMaterials.Pyrochlore).size(1, 1))
                    )));
    // 硅岩
    public static final GTOreDefinition NAQUADAH_VEIN_AD = create("naquadah_vein_ad", vein -> vein
            .clusterSize(48)
            .weight(30)
            .layer(AD)
            .density(0.4f)
            .dimensions(VENUS)
            .heightRangeUniform(10, 90)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Naquadah).size(2, 3))
                            .layer(l -> l.weight(1).mat(GTMaterials.NaquadahEnriched).size(1, 2))
                    )));
    // Glacio
    // 锇
    public static final GTOreDefinition OSMIUM_VEIN_AD = create("osmium_vein_ad", vein -> vein
            .clusterSize(24)
            .weight(10)
            .layer(AD)
            .density(0.2f)
            .dimensions(GLACIO)
            .heightRangeUniform(-5, 30)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(3).mat(GTMaterials.Nickel).size(2, 3))
                            .layer(l -> l.weight(2).mat(GTMaterials.Osmium).size(1, 2))
                            .layer(l -> l.weight(1).mat(GTMaterials.Iridium).size(1, 1))
                    )));
    // 中子素
    public static final GTOreDefinition NEUTRONIUM_VEIN_AD = create("neutronium_vein_ad", vein -> vein
            .clusterSize(24)
            .weight(10)
            .layer(AD)
            .density(0.2f)
            .dimensions(GLACIO)
            .heightRangeUniform(-50, -10)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(2).mat(GTMaterials.Neutronium).size(1, 2))
                            .layer(l -> l.weight(1).mat(InfinityCatalyst).size(1, 2))
                            .layer(l -> l.weight(1).mat(GTMaterials.Naquadria).size(1, 2))
                            .layer(l -> l.weight(1).mat(GTMaterials.Titanium).size(1, 2))
                    )));
    // 铌
    public static final GTOreDefinition NIOBIUM_VEIN_AD = create("niobium_vein_ad", vein -> vein
            .clusterSize(36)
            .weight(60)
            .layer(AD)
            .density(0.2f)
            .dimensions(GLACIO)
            .heightRangeUniform(-50, -10)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(2).mat(GTMaterials.Niobium).size(1, 2))
                            .layer(l -> l.weight(2).mat(GTMaterials.Iridium).size(1, 2))
                            .layer(l -> l.weight(1).mat(GTMaterials.Gallium).size(1, 1))
                    )));
    // 钍
    public static final GTOreDefinition THORIUM_VEIN_AD = create("thorium_vein_ad", vein -> vein
            .clusterSize(36)
            .weight(60)
            .layer(AD)
            .density(0.2f)
            .dimensions(GLACIO)
            .heightRangeUniform(-10, 30)
            .discardChanceOnAirExposure(0)
            .layeredVeinGenerator(generator -> generator
                    .buildLayerPattern(pattern -> pattern
                            .layer(l -> l.weight(2).mat(GTMaterials.Thorium).size(1, 2))
                            .layer(l -> l.weight(2).mat(GTMaterials.Uranium235).size(1, 2))
                            .layer(l -> l.weight(2).mat(GTMaterials.Plutonium241).size(1, 2))
                    )));

    public static GTOreDefinition create(String name, Consumer<GTOreDefinition> config, boolean t) {
        if (t){
            return GTOres.create(GTNN.id(name), config);
        }else return GTOres.blankOreDefinition();
    }

    public static GTOreDefinition create(String name, Consumer<GTOreDefinition> config) {
        if (GTNNIntegration.isAdAstraLoaded()){
           return GTOres.create(GTNN.id(name), config);
        }else return GTOres.blankOreDefinition();
    }

    public static void init() {
    }
}
