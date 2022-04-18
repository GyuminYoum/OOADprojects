from Commands.Command import Command


class Trade(Command):
    def __init__(self):
        super().__init__()

    # TODO: use sim.update(message) to send messages from Trade to the observer
    def execute(self, sim):
        print(f'{sim.current_player.name}, would you like to trade on this turn?')
        trade = bool(int(input('(1: Yes, 0: No): ')))

        if not trade:
            sim.update(f'Trade phase over for {sim.current_player.name}, no trades made.')
            return

        while trade:
            # print out the current player's resources
            print(f'{sim.current_player.name}\'s resources: ')
            for key, value in sim.current_player.resources.items():
                print(f'{key}: {value}')

            print('Would you like to trade with other players? ')
            player_trade = bool(int(input('(1: Yes, 0: No): ')))

            if player_trade:
                print('What resource are you looking to trade for with another player?')
                wants = input('Enter text as shown (sheep, wood, ore, clay, wheat): ')
                wants_number = int(input('How many of this resource do you want?: '))
                print('What resource are you offering?')
                offers = input('Enter text as shown (sheep, wood, ore, clay, wheat): ')
                offers_number = int(input('How many of this resource are you offering?: '))

                # if current_player has enough resources to give
                if sim.current_player.resources[offers] >= offers_number:
                    # for players in the game
                    for player in sim.playerlist:
                        # if not the player initiating the trade
                        if player.name != sim.current_player.name:
                            # check if player has enough of the wanted resources
                            if player.resources[wants] >= wants_number:
                                print(f'{player.name}, would you like to trade {wants_number} {wants} '
                                      f'for {offers_number} {offers}?')

                                print(f'{player.name}\'s resources: ')
                                for key, value in player.resources.items():
                                    print(f'{key}: {value}')

                                trade_accepted = bool(int(input("(1: Yes, 0: No): ")))
                                # if player accepts trade
                                if trade_accepted:
                                    print('Trade accepted!')
                                    sim.current_player.resources[offers] -= offers_number
                                    player.resources[offers] += offers_number
                                    sim.current_player.resources[wants] += wants_number
                                    player.resources[wants] -= wants_number

                                    # print resources of both players after trade
                                    print(f'{sim.current_player.name}\'s resources: ')
                                    for key, value in sim.current_player.resources.items():
                                        print(f'{key}: {value}')
                                    print(f'{player.name}\'s resources: ')
                                    for key, value in player.resources.items():
                                        print(f'{key}: {value}')
                                    break
                                else:
                                    print('Trade rejected. ')

                    print('Would you like to trade with another player? ')
                    trade_again = bool(int(input("(1: Yes, 0: No): ")))
                    if trade_again:
                        continue
                else:
                    print('Not enough resources, trade canceled. ')
                    continue

            # ask user if they would like to trade with a harbor
            # possibly make trade strategy
            print('Would you like to check if you can trade with any harbors? ')
            print('(This includes 4:1 trading)')
            harbor_trade = bool(int(input('(1: Yes, 0: No): ')))

            if harbor_trade:
                self.harbor_trade(sim)

            # check if player wants to make more trades
            print('Would you like to make any more trades? ')
            trade = bool(int(input('(1: Yes, 0: No): ')))

        print(f'Trade phase over for {sim.current_player.name}.')

    # generic_trade represents the harbors in which a player can trade any resource in for a resource of their choice
    def generic_trade(self, sim, quantity_needed):
        print(f'You can trade {quantity_needed} of the same resource for 1 resource of your choice at a harbor. ')
        print(f'{sim.current_player.name}\'s resources: ')
        for key, value in sim.current_player.resources.items():
            print(f'{key}: {value}')
        print('Which resource will you trade in? ')
        resource = input('(sheep, wood, ore, clay, wheat, none): ')
        if resource == 'none':
            return
        elif sim.current_player.resources[resource] < quantity_needed:
            print('Insufficient resources. ')
        else:
            print('Which resource do you want in return? ')
            resource1 = input('(sheep, wood, ore, clay, wheat): ')
            sim.current_player.resources[resource] -= quantity_needed
            sim.current_player.resources[resource1] += 1
            print(f'{sim.current_player.name} traded 4 {resource} for 1 {resource1}')
            print(f'{sim.current_player.name}\'s resources: ')
            for key, value in sim.current_player.resources.items():
                print(f'{key}: {value}')

    # harbor_trade is called for all harbors with 2:1 trading ratio
    def harbor_trade(self, sim):
        harbor_resource = None
        for node in sim.current_player.settlement:
            if node.label == 'A1' or node.label == 'A6' or node.label == 'L2' or node.label == 'L3' \
                    or node.label == 'Q4' or node.label == 'Q5':
                self.generic_trade(sim, 3)
            elif node.label == 'B1' or node.label == 'B2':
                harbor_resource = 'wheat'
            elif node.label == 'C3' or node.label == 'G2':
                harbor_resource = 'ore'
            elif node.label == 'D5' or node.label == 'D6':
                harbor_resource = 'wood'
            elif node.label == 'H4' or node.label == 'M5':
                harbor_resource = 'clay'
            elif node.label == 'P3' or node.label == 'P4':
                harbor_resource = 'sheep'

            if harbor_resource is not None:
                # TODO: do harbor trade
                pass

        self.generic_trade(sim, 4)
