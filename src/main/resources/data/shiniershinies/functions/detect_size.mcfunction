# runs on pokemon

# summon size detectors
execute at @s anchored eyes positioned ^ ^ ^ run summon minecraft:marker ~ ~ ~ {Tags:["shiniershinies.head","shiniershinies.size_detector","global.ignore"]}
execute at @s anchored feet positioned ^ ^ ^ run summon minecraft:marker ~ ~ ~ {Tags:["shiniershinies.feet","shiniershinies.size_detector","global.ignore"]}

# find size
execute store result score #shiniershinies:size.head shiniershinies.temp run data get entity @e[type=minecraft:marker,tag=shiniershinies.head,limit=1,sort=nearest] Pos[1] 1
execute store result score #shiniershinies:size.feet shiniershinies.temp run data get entity @e[type=minecraft:marker,tag=shiniershinies.feet,limit=1,sort=nearest] Pos[1] 1

# store size
execute store result score @s shiniershinies.size run scoreboard players operation #shiniershinies:size.head shiniershinies.temp -= #shiniershinies:size.feet shiniershinies.temp

# delete temp entities
kill @e[type=minecraft:marker,tag=shiniershinies.size_detector]