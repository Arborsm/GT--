# runs every 20 ticks (1 second)

# check for shiny
execute as @e[type=cobblemon:pokemon,tag=!shiniershinies.ignore] run function shiniershinies:check_for_shiny

# loop
schedule function shiniershinies:loop/every_20_ticks 20t replace