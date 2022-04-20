from Commands.Command import Command


class Trade(Command):
    def __init__(self):
        super().__init__()

    # TODO: use sim.observer.update(message) to send messages from Trade to the observer
    def execute(self, sim):
        print(f'{sim.current_player.name}, would you like to trade on this turn?')
        trade = bool(int(input('(1: Yes, 0: No): ')))

        if not trade:
            print(f'Trade phase over for {sim.current_player.name}, no trades made.')
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
            print('Would you like to check if you can trade with any harbors? ')
            harbor_trade = bool(int(input('(1: Yes, 0: No): ')))
            if harbor_trade:
                # prompt user to trade with harbors if they have any
                # will replace (0, 0) with the correct nodes that have harbors
                if (0, 0) in sim.current_player.settlement or (0, 0) in sim.current_player.city:
                    print('You can trade x resources at this harbor at a 2:1 rate. ')
                    print('etc etc')
                    # TODO: hardcode harbor X node locations
                    # allow trading with harbor X

                if (0, 0) in sim.current_player.settlement or (0, 0) in sim.current_player.city:
                    print('You can trade y resources at this harbor at a 2:1 rate. ')
                    print('etc etc')
                    # TODO: hardcode harbor Y node locations
                    # allow trading with harbor Y

                if (0, 0) in sim.current_player.settlement or (0, 0) in sim.current_player.city:
                    print('You can trade z resources at this harbor at a 2:1 rate. ')
                    print('etc etc')
                    # TODO: hardcode harbor Z node locations
                    # allow trading with harbor Z

                # allow 4:1 trading regardless of settlement/city locations
                print('You can trade 4 of the same resource for 1 resource of your choice. ')
                temp = bool(int(input('Do you want to do so? (1: Yes, 0: No): ')))

                while temp:
                    print(f'{sim.current_player.name}\'s resources: ')
                    for key, value in sim.current_player.resources.items():
                        print(f'{key}: {value}')
                    print('Which resource will you trade in? ')
                    resource = input('(sheep, wood, ore, clay, wheat, none): ')
                    if resource == 'none':
                        break
                    elif sim.current_player.resources[resource] < 4:
                        print('Insufficient resources. ')
                    else:
                        print('Which resource do you want in return? ')
                        resource1 = input('(sheep, wood, ore, clay, wheat): ')
                        sim.current_player.resources[resource] -= 4
                        sim.current_player.resources[resource1] += 1
                        print(f'{sim.current_player.name} traded 4 {resource} for 1 {resource1}')
                        print(f'{sim.current_player.name}\'s resources: ')
                        for key, value in sim.current_player.resources.items():
                            print(f'{key}: {value}')

            # check if player wants to conduct more trades
            print('Would you like to make any more trades? ')
            trade = bool(int(input('(1: Yes, 0: No): ')))

        print(f'Trade phase over for {sim.current_player.name}.')
