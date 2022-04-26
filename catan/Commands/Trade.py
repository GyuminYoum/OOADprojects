from Commands.Command import Command


class Trade(Command):
    def __init__(self):
        super().__init__()
        self.RESOURCES = ['sheep', 'wood', 'clay', 'ore', 'wheat']

    # function execute
    # usage: handles all possible trading interactions for the current_player
    def execute(self, sim):
        done=False
        trade=False

        print(f'{sim.current_player.name}, would you like to trade on this turn?')

        trade = input('(1: Yes, 0: No): ')
        while trade != '0' and trade != '1':
            trade = input("(1: Yes, 0: No): ")
        trade = bool(int(trade))

        if not trade:
            sim.update(f'Trade phase over for {sim.current_player.name}, no trades made.')
            return

        while trade:
            # print out the current player's resources
            print(f'{sim.current_player.name}\'s resources: ')
            for key, value in sim.current_player.resources.items():
                print(f'{key}: {value} / ', end='')
            print('\nWould you like to trade with other players? ')
            temp = input('(1: Yes, 0: No): ')
            while temp != '0' and temp != '1':
                temp = input("(1: Yes, 0: No): ")
            player_trade = bool(int(temp))

            if player_trade:
                print('What resource are you looking to trade for with another player?')
                wants = input('Enter text as shown (sheep, wood, ore, clay, wheat): ')
                while wants not in self.RESOURCES:
                    print('Enter a valid resource. ')
                    wants = input('Enter text as shown (sheep, wood, ore, clay, wheat): ')
                wants_number = input('How many of this resource do you want?: ')
                while not wants_number.isnumeric():
                    print('Enter a number.')
                    wants_number = input('How many of this resource do you want?: ')
                wants_number = int(wants_number)
                print('What resource are you offering?')
                offers = input('Enter text as shown (sheep, wood, ore, clay, wheat): ')
                while offers not in self.RESOURCES:
                    print('Enter a valid resource. ')
                    offers = input('Enter text as shown (sheep, wood, ore, clay, wheat): ')
                offers_number = input('How many of this resource are you offering?: ')
                while not offers_number.isnumeric():
                    print('Enter a number.')
                    offers_number = input('How many of this resource do you want?: ')
                offers_number = int(offers_number)
                # if current_player has enough resources to give
                while sim.current_player.resources[offers] < offers_number:
                    print('Not enough resources. ')
                    offers_number = input('How many of this resource are you offering?: ')
                    while not offers_number.isnumeric():
                        print('Enter a number.')
                        offers_number = input('How many of this resource do you want?: ')
                    offers_number = int(offers_number)

                # if sim.current_player.resources[offers] >= offers_number:
                # for players in the game
                count = 0
                for player in sim.playerlist:
                    # if not the player initiating the trade
                    if player.name != sim.current_player.name:
                        # check if player has enough of the wanted resources
                        if player.resources[wants] >= wants_number:
                            count += 1
                            print(f'{player.name}, would you like to trade {wants_number} {wants} '
                                  f'for {offers_number} {offers}?')

                            print(f'{player.name}\'s resources: ')
                            for key, value in player.resources.items():
                                print(f'{key}: {value} / ', end='')

                            temp = input("\n(1: Yes, 0: No): ")
                            while temp != '0' and temp != '1':
                                temp = input("(1: Yes, 0: No): ")
                            trade_accepted = bool(int(temp))
                            # if player accepts trade
                            if trade_accepted:
                                sim.update(f'{sim.current_player.name} traded {offers_number} {offers}'
                                           f' for {wants_number} {wants} from {player.name}')
                                sim.current_player.resources[offers] -= offers_number
                                player.resources[offers] += offers_number
                                sim.current_player.resources[wants] += wants_number
                                player.resources[wants] -= wants_number
                                # # print resources of both players after trade
                                print(f'{sim.current_player.name}\'s resources: ')
                                for key, value in sim.current_player.resources.items():
                                    print(f'{key}: {value} / ', end='')
                                print(f'\n{player.name}\'s resources: ')
                                for key, value in player.resources.items():
                                    print(f'{key}: {value} / ', end='')
                                print()
                                break
                            else:
                                print('Trade rejected. ')
                if count == 0:
                    print(f'Nobody has {wants_number} {wants} to trade.')

                print('Would you like to trade with another player? ')
                temp = input("(1: Yes, 0: No): ")
                while temp != '0' and temp != '1':
                    temp = input("(1: Yes, 0: No): ")
                trade_again = bool(int(temp))
                if trade_again:
                    continue

            # ask user if they would like to trade with a harbor
            # possibly make trade strategy
            print('Would you like to check if you can trade with any harbors? ')
            print('(This includes 4:1 trading)')
            temp = input("(1: Yes, 0: No): ")
            while temp != '0' and temp != '1':
                temp = input("(1: Yes, 0: No): ")
            harbor_trade = bool(int(temp))

            if harbor_trade:
                self.harbor_trade(sim)

            # check if player wants to make more trades
            print('Would you like to make any more trades? ')
            temp = input("(1: Yes, 0: No): ")
            while temp != '0' and temp != '1':
                temp = input("(1: Yes, 0: No): ")
            trade = bool(int(temp))

        print(f'Trade phase over for {sim.current_player.name}.')

    # function generic_trade
    # usage: allows the current_player to perform 3:1 or 4:1 trading
    def generic_trade(self, sim, quantity_needed):
        print(f'You can trade {quantity_needed} of the same resource for 1 resource of your choice at a harbor. ')
        print(f'{sim.current_player.name}\'s resources: ')
        for key, value in sim.current_player.resources.items():
            print(f'{key}: {value} / ', end='')
        print('\nWhich resource will you trade in? ')
        resource = input('(sheep, wood, ore, clay, wheat, none): ')
        while resource not in self.RESOURCES and resource != 'none':
            print('Enter a valid resource. ')
            resource = input('(sheep, wood, ore, clay, wheat, none): ')
        if resource == 'none':
            return
        elif sim.current_player.resources[resource] < quantity_needed:
            print('Insufficient resources. ')
        else:
            print('Which resource do you want in return? ')
            resource1 = input('(sheep, wood, ore, clay, wheat): ')
            while resource1 not in self.RESOURCES:
                print('Enter a valid resource. ')
                resource1 = input('(sheep, wood, ore, clay, wheat): ')
            sim.current_player.resources[resource] -= quantity_needed
            sim.current_player.resources[resource1] += 1
            sim.update(f'{sim.current_player.name} traded 4 {resource} for 1 {resource1}')
            print(f'{sim.current_player.name}\'s resources: ')
            for key, value in sim.current_player.resources.items():
                print(f'{key}: {value}')

    # function harbor_trade
    # usage: allows the current_player to perform 2:1 trading for all harbors that they are located next to
    def harbor_trade(self, sim):
        harbor_resource = None
        resource_traded = {'wheat': False, 'ore': False, 'wood': False, 'clay': False, 'sheep': False}
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

            if harbor_resource is not None and resource_traded[harbor_resource] is False:
                harbor_trade = True
                while harbor_trade:
                    print(f'You can trade in 2 of any resource for 1 {harbor_resource}.')
                    print(f'{sim.current_player.name}\'s resources: ')
                    for key, value in sim.current_player.resources.items():
                        print(f'{key}: {value} / ', end='')
                    print('\nWhich resource will you trade in? ')
                    resource = input('(sheep, wood, ore, clay, wheat, none): ')
                    while resource not in self.RESOURCES and resource != 'none':
                        print('Enter a valid resource. ')
                        resource = input('(sheep, wood, ore, clay, wheat, none): ')
                    if resource == 'none':
                        break
                    elif sim.current_player.resources[resource] < 2:
                        print('Insufficient resources. ')
                        continue
                    else:
                        sim.current_player.resources[resource] -= 2
                        sim.current_player.resources[harbor_resource] += 1
                        sim.update(f'{sim.current_player.name} traded 2 {resource} for 1 {harbor_resource}')
                        print(f'{sim.current_player.name}\'s resources: ')
                        for key, value in sim.current_player.resources.items():
                            print(f'{key}: {value} / ', end='')
                        print()
                        break

        self.generic_trade(sim, 4)
