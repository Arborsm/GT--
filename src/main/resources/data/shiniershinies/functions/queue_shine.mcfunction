# runs as shiny pokemon

# queue shiny particles
execute store result score @s shiniershinies.timer run time query gametime
scoreboard players add @s shiniershinies.timer 40
schedule function shiniershinies:find_shiny 40t append