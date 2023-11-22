# runs globally

# get current time
execute store result score #shiniershinies.current_time shiniershinies.temp run time query gametime

# find shiny with timer score matching current gametime
execute as @e[type=cobblemon:pokemon,tag=shiniershinies.shiny] if score @s shiniershinies.timer = #shiniershinies.current_time shiniershinies.temp run function shiniershinies:shine