from Commands.Command import Command


class Resource(Command):
    def __init__(self):
        super().__init__()

    def execute(self, sim):
        roll = sim.roll()
        for hexagon in sim.field.hexlist:
            if hexagon.value == roll:
                # distribute resources to all players next to hex
                for player in sim.playerlist:
                    # TODO: check if player has settlement by hex
                    pass
