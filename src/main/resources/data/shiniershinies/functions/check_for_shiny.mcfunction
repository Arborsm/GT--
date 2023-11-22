# runs as a pokemon

# check if shiny
execute if data entity @s {Pokemon:{Shiny:1b}} run tag @s add shiniershinies.shiny

# get size
execute unless score @s shiniershinies.size matches 0.. if entity @s[tag=shiniershinies.shiny] at @s run function shiniershinies:detect_size

# queue shine
execute if entity @s[tag=shiniershinies.shiny] run function shiniershinies:queue_shine

# ignore after check
tag @s add shiniershinies.ignore