GTCEuStartupEvents.registry('gtceu:world_gen_layer', event => {
  event.create('ad')
    .target(['ad_astra:moon_stone', 'ad_astra:venus_stone', 'ad_astra:mars_stone', 'minecraft:blackstone', 'minecraft:basalt', 'ad_astra:mercury_stone', 'minecraft:deepslate', 'ad_astra:glacio_stone'])
    .addDimension('ad_astra:moon')
    .addDimension('ad_astra:venus')
    .addDimension('ad_astra:mars')
    .addDimension('ad_astra:mercury')
    .addDimension('ad_astra:glacio')
})
