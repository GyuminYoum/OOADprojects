from Commands.Command import Command


class Build(Command):
    def __init__(self):
        super().__init__()

    # TODO: use sim.observer.update(message) to send messages from Build to the observer
    # MVC
    def execute(self, sim):
        print('Do you want to build anything this turn? ')
        build = True

        # while user still wants to build
        while build:
            print(f'{sim.current_player.name}\'s resources: ')
            for key, value in sim.current_player.resources.items():
                print(f'{key}: {value}')
            build_action = input('Enter a number (0: None, 1: Road, 2: Settlement, 3: City): ')
            print(type(build_action))

            if build_action == '0':
                print(f'{sim.current_player.name} ended their build phase. ')
                break

            elif build_action == '1':
                selections={}
                choice=""
                userchoice=[]
                user_input_road=1
                #print("BUILDING ROAD")
                while (user_input_road != 0 or user_input_road not in userchoice):
                    for x in sim.current_player.roads.keys():
                        selections[x] = []
                        for y in x.adj:
                            if y in sim.possible_roads[x]:
                                selections[x].append(y)

                    for x in selections.keys():
                        for y in selections[x]:
                            choice = x.label + y.label
                            userchoice.append(choice)

                    while (user_input_road != 0 and user_input_road not in userchoice):
                        user_input_road = input(
                            'Where would you like to build road? /n possible road locations are: ' + userchoice + ". enter 0 to exit")
                        if (user_input_road != 0 and user_input_road not in userchoice):
                            print("Invalid selection")
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
