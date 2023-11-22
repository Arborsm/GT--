# runs as shiny pokemon

# shine
execute at @s if score @s shiniershinies.size matches ..0 run particle minecraft:end_rod ~ ~.5 ~ .3 .3 .3 .02 5 force
execute at @s if score @s shiniershinies.size matches 1 run particle minecraft:end_rod ~ ~.7 ~ .5 .5 .5 .05 10 force
execute at @s if score @s shiniershinies.size matches 2.. run particle minecraft:end_rod ~ ~.5 ~ .8 .8 .8 .12 15 force

# queue next shine
function shiniershinies:queue_shine