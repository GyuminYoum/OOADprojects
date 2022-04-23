from Commands.Command import Command


class Resource(Command):
    def __init__(self):
        super().__init__()

    def execute(self, sim):
        roll = sim.roll()

        if roll == 7:
            # TODO : robber phase
            # for player in sim.playerlist:
                # if  player's total resources > 7:
                    # prompt player to give up int(np.floor(total resources / 2))
            #
            pass
        else:
            for hexagon in sim.field.hexlist:
                if hexagon.value == roll:
                    # distribute resources to all players next to hex
                    for player in sim.playerlist:
                        # TODO: check if player has settlement by hex
                        for node in player.settlement:
                            if node in hexagon.getNodes():
                                player.resources[hexagon.Resource] += 1
                                sim.update(f'{player.name} received 1 {hexagon.Resource}')
