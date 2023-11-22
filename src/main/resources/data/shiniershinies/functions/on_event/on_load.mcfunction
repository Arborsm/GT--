# runs on every load or reload

# register scores
scoreboard objectives add shiniershinies.temp dummy
scoreboard objectives add shiniershinies.timer dummy
scoreboard objectives add shiniershinies.size dummy

# pause loops
schedule clear shiniershinies:loop/every_20_ticks

# init loops
function shiniershinies:loop/every_20_ticks