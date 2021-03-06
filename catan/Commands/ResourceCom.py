import numpy as np

from Commands.Command import Command


class ResourceCom(Command):
    def __init__(self):
        super().__init__()
        self.RESOURCES = ['sheep', 'wood', 'ore', 'wheat', 'clay']

    # execute() rolls the die for a player and distributes resources
    # this method also is in charge of handling the in-game robber and all related actions
    def execute(self, sim):
        if sim.current_player.rolled:
            print(f'{sim.current_player.name} rolled already.')
            return

        roll = sim.roll()
        sim.update(f'{sim.current_player.name} rolled a {roll}.')
        if roll == 7:
            for player in sim.playerlist:
                if self.countResources(player) > 7:
                    self.giveUpResources(sim, player)

            # move robber, steal 1 resource from each player with settlement on new hexagon
            robberhex = self.moveRobber(sim)
            self.robResources(sim, robberhex)
            self.robPlayer(sim, robberhex)

        else:
            for hexagon in sim.field:
                # if robber is on the hex, skip hex
                if hexagon.value == roll:
                    if hexagon.Robber:
                        print(f'Robber on Hexagon {hexagon.name}, no resources distributed')
                        pass
                    # else check all hexagons with value == roll, and distribute resources accordingly
                    else:
                        # distribute resources to all players next to hex
                        for player in sim.playerlist:
                            for node in player.settlement:
                                if node in hexagon.get_nodes():
                                    if node in player.city:
                                        player.resources[hexagon.Resource.type] += 2
                                        sim.update(f'{player.name} received 2 {hexagon.Resource.type}')
                                    else:
                                        player.resources[hexagon.Resource.type] += 1
                                        sim.update(f'{player.name} received 1 {hexagon.Resource.type}')
                                    print(f'{player.name}: {player.resources}')
        sim.current_player.rolled=True

    # robResources() lets current_player steal resources from players with settlements by the hexagon with the robber
    def robResources(self, sim, hexagon):
        for player in sim.playerlist:
            for node in hexagon.get_nodes():
                if node in player.settlement:
                    if player.resources[hexagon.Resource.type] > 0:
                        player.resources[hexagon.Resource.type] -= 1
                        break

    # countResources() counts a player's resources
    # helper method for giveUpResources()
    def countResources(self, player):
        total = 0
        for value in player.resources.values():
            total += value
        return total

    # giveUpResources() called when players have 8+ resources
    # this method also makes a player give up half of their resources (rounded down)
    def giveUpResources(self, sim, player):
        count = self.countResources(player)
        half = int(np.floor(count/2))
        given = 0
        resources = []
        print(f'{player.name}, you have {count} resources, you must give up {half}. ')
        while given < half:
            resources = []
            print(f'What resources would you like to give up ({given}/{half})?')
            for key, value in player.resources.items():
                if value > 0:
                    print(f'{key}: {value} / ', end='')
                    resources.append(key)
            res = input(f'\n{resources}:')
            while res not in self.RESOURCES:
                print('Enter a valid resource. ')
                res = input(f'{resources}:')
            num = input(f'How many {res} will you give up?: ')
            while not num.isnumeric():
                print('Enter a number.')
                num = input(f'\nHow many {res} will you give up?: ')
            num = int(num)
            if num <= player.resources[res] and given + num <= half:
                player.resources[res] -= num
                given += num
            else:
                print('Not enough/too many resources.')
        sim.update(f'{player.name} was robbed for {half} of his resources.')
        print(f'{player.name}\'s resources: ')
        for key, value in player.resources.items():
            print(f'{key}: {value} / ', end='')
        print()

    # moveRobber() updates Hex fields that correspond to the robber's current and previous positions
    def moveRobber(self, sim):
        print(f'{sim.current_player.name}, where would you like to move the robber? ')
        hexes = []
        for hexagon in sim.field:
            hexes.append(hexagon.name)
        hexmove = input(f'{hexes}: ')
        while hexmove not in hexes:
            print('Enter a valid hexagon. ')
            hexmove = input(f'{hexes}: ')
        robberhex = None

        for hexagon in sim.field:
            # set robber to new location
            if hexagon.name == hexmove:
                hexagon.Robber = True
                robberhex = hexagon
            # remove robber from old location
            elif hexagon.Robber:
                hexagon.Robber = False
        return robberhex

    # robPlayer() lets the current_player pick which other player they want to rob after moving the robber
    def robPlayer(self, sim, hexagon):
        print(f'{sim.current_player.name}, which player would you like to rob? ')
        players = []
        for player in sim.playerlist:
            if player is not sim.current_player:
                for node in player.settlement:
                    for node1 in hexagon.get_nodes():
                        if node.label == node1.label:
                            players.append(player.name)
                            break
        if not players:
            print('There is nobody to rob at this hexagon.')
        else:
            robbed = ''
            while robbed not in players:
                robbed = input(f'{players}: ')
            for player in sim.playerlist:
                if player.name == robbed:
                    robbed = player
                    break

            if robbed.totalResources() == 0:
                print(f'{robbed.name} does not have enough resources, 0 resources robbed.')
                return

            options = ['sheep', 'wood', 'ore', 'clay', 'wheat']
            resource = np.random.choice(options)

            # pick a resource at random, if that resource is 0 then pick another resource at random
            while robbed.resources[resource] == 0:
                options.remove(resource)
                if len(options) == 0:
                    break
                resource = np.random.choice(options)
            robbed.resources[resource] -= 1
            sim.current_player.resources[resource] += 1
            sim.update(f'{sim.current_player.name} robbed 1 {resource} from {player.name}.')
