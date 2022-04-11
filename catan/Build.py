from Command import *


class Build(Command):
    def __init__(self):
        pass

    def execute(self, sim):
        print('Do you want to build anything this turn? ')
        build = True

        # while user still wants to build
        while build:
            print(f'{sim.current_player.name}\'s resources: ')
            for key, value in sim.current_player.resources.items():
                print(f'{key}: {value}')
            build_action = input('Enter a number (0: None, 1: Road, 2: Settlement, 3: City): ')

            if build_action == 0:
                print(f'{sim.current_player.name} ended their build phase. ')
                break

            elif build_action == 1:
                print('road')
                # TODO: prompt user to build road somewhere
                # create road between 2 nodes
                # sim.current_player.roads.append( new road )

            elif build_action == 2:
                print('settlement')
                # TODO: prompt user to build settlement somewhere
                # create settlement at 1 node
                # sim.current_player.settlement.append( new settlement )

            elif build_action == 3:
                print('city')
                # TODO: prompt user to build city somewhere
                # allow user to build city from any settlements in sim.current_player.settlement

            print('Do you want to build anything else? ')
            build = bool(int(input('(1: Yes, 0: No): ')))
