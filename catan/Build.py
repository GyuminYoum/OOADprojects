

class Build(Command):
    def __init__(self):
        pass

    def execute(self, sim):
        # while user still wants to build
        while 1:
            print('Do you want to build anything this turn? ')
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
            elif build_action == 2:
                print('settlement')
                # TODO: prompt user to build road somewhere
            elif build_action == 3:
                print('city')
                # TODO: prompt user to build road somewhere

            print('Do you want to build anything else? ')
            build = bool(input('(1: Yes, 0: No): '))

            if build:
                continue
            else:
                break
