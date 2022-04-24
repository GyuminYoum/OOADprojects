import numpy as np

from Commands.Command import Command


class Resource(Command):
    def __init__(self):
        super().__init__()

    def execute(self, sim):
        roll = sim.roll()

        if roll == 7:
            # TODO : robber phase
            for player in sim.playerlist:
                if self.countResources(player) > 7:
                    self.giveUpResources(sim, player)

            # move robber, steal 1 resource from each player with settlement on new hexagon
            self.robResources(sim, self.moveRobber(sim))
            # self.robPlayer(sim)

        else:
            for hexagon in sim.field.hexlist:
                # if robber is on the hex, skip hex
                if hexagon.Robber:
                    print(f'Robber on Hexagon {hexagon.name}, no resources distributed')
                    pass
                # else check all hexagons with value == roll, and distribute resources accordingly
                else:
                    if hexagon.value == roll:
                        # distribute resources to all players next to hex
                        for player in sim.playerlist:
                            for node in player.settlement:
                                if node in hexagon.getNodes():
                                    player.resources[hexagon.Resource] += 1
                                    sim.update(f'{player.name} received 1 {hexagon.Resource}')

    # steal resources from players with settlements by hexagon with robber
    def robResources(self, sim, hexagon):
        for player in sim.playerlist:
            for node in hexagon.getnodes():
                if node in player.settlement:
                    if player.resources[hexagon.Resource] > 0:
                        player.resources[hexagon.Resource] -= 1
                        break

    def countResources(self, player):
        total = 0
        for value in player.resources.values():
            total += value
        return total

    # called when players have 8+ resources
    def giveUpResources(self, sim, player):
        count = self.countResources(player)
        half = int(np.floor(count/2))
        given = 0
        resources = []
        print(f'{player.name}, you have {count} resources, you must give up {half}. ')
        while given < half:
            print('What resources would you like to give up?')
            for key, value in player.resources.items():
                if value > 0:
                    print(f'{key}: {value} / ', end='')
                    resources.append(key)
            res = input(resources)
            num = input(f'\nHow many {res} will you give up? :')
            if num <= player.resources[res]:
                player.resources[res] -= num
                given += num
        sim.update(f'\n{player.name} was robbed for {half} of his resources. ')
        print(f'{player.name}\'s resources: ')
        for key, value in player.resources.items():
            print(f'{key}: {value} / ', end='')

    def moveRobber(self, sim):
        print(f'{sim.current_player}, where would you like to move the robber? ')
        # TODO: implement this
        hexes = []
        for hexagon in sim.field:
            hexes.append(hexagon.name)
        hexmove = input(f'{hexes}: ')

        for hexagon in sim.field:
            if hexagon.name == hexmove:
                hexagon.Robber = True
        # return hexagon the robber was moved to

    def robPlayer(self, sim):
        print(f'{sim.current_player}, which player would you like to rob? ')
        players = []
        for player in sim.playerlist:
            if player is not sim.current_player:
                players.append(player)
        rob = input(f'{player}: ')
        # TODO: finish this
