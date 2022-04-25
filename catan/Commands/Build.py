from Commands.Command import Command


class Build(Command):
    def __init__(self):
        super().__init__()

    # TODO: use sim.observer.update(message) to send messages from Build to the observer
    # MVC
    def execute(self, sim):
        build=True
        response=3
        # while user still wants to build
        while(build):
            print('Does ' + sim.current_player.name + " want to build anything? ")
            while response!='1' and response!='0':
                response = input('(1: Yes, 0: No): ')
                if response!='1' and response!='0':
                    print("Invalid input")
            if response=='0':
                build=False
                break
            print(f'{sim.current_player.name}\'s resources: ')
            for key, value in sim.current_player.resources.items():
                print(f'{key}: {value}')
            build_action = input('Enter a number (0: None, 1: Road, 2: Settlement, 3: City): ')
            #print(type(build_action))
            player=sim.current_player

            if build_action == '0':
                print(f'{sim.current_player.name} ended their build phase. ')
                return None, None

            elif build_action == '1':
                if player.canBuildRoad():
                    done1 = False
                    while not done1:
                        print('settlement')
                        print("curr_player " + player.name)
                        print("curr_roads: " + str(player.get_roadNames()))
                        print("curr_settlements: " + str(player.get_settlementNames()))
                        print("curr_cities: " + str(player.get_cityNames()))
                        print("Select the location for road for " + player.name + " or enter 0 to exit building road")
                        road_list = sim.getPossibleRoads(player.generateRoadNameList())
                        val = input("Possible selections are " + str(road_list) + "\n")
                        if val=="0":
                            break
                        if val in road_list:
                            nodes = sim.stringToRoad(val)
                            sim.possible_roads[nodes[0]].remove(nodes[1])
                            sim.possible_roads[nodes[1]].remove(nodes[0])
                            if nodes[0] not in player.roads.keys():
                                player.roads[nodes[0]] = [nodes[1]]
                            else:
                                player.roads[nodes[0]].append(nodes[1])
                            if nodes[1] not in player.roads.keys():
                                player.roads[nodes[1]] = [nodes[0]]
                            else:
                                player.roads[nodes[1]].append(nodes[0])
                            player.resources["clay"]= player.resources["clay"]-1
                            player.resources["wood"] = player.resources["wood"]-1
                            sim.update(player.name+" built a road at " + val)
                            done1 = True
                        else:
                            print("Invalid Selection")
                else:
                    print(player.name +" doesn't have enough resources to build a road")

                # TODO: prompt user to build road somewhere
                # create road between 2 nodes

                # sim.current_player.roads.append( new road )

            elif build_action == '2':
                print('settlement')
                print("curr_player " + player.name)
                print("curr_roads: " + str(player.get_roadNames()))
                print("curr_settlements: " + str(player.get_settlementNames()))
                print("curr_cities: " + str(player.get_cityNames()))
                # TODO: prompt user to build settlement somewhere
                # create settlement at 1 node
                # sim.current_player.settlement.append( new settlement )
                # pygame.draw.circle(self.surface, self.playerlist[0].color, coord, 10)
                if player.canBuildSettlement():
                    done = False
                    while not done:
                        #resource check: 1 brick, 1 lumber, 1 wool, 1 grain
                        print("Select the location for settlement for " + player.name+ " or enter 0 to exit building settlement")
                        settlement_list=sim.filterPossibleSettlement(player.generatePossibleSettlements())
                        #list1=player.generateSettlementNameList()
                        #print("possible settlements from user: "+str(list1))
                        settlement_names=sim.convertNodeListToNameList(settlement_list)
                        if(len(settlement_names)==0):
                            print("No possible location available to build a settlement")
                            break
                        val = input("Possible locations to build a settlement are: " + str(settlement_names) + "\n")
                        if (val=="0"):
                            break
                        if sim.AvailableNodeCheck(val, sim.possible_settlements):
                            node1 = sim.getNode(val, sim.possible_settlements)
                            player.settlement.append(node1)
                            sim.possible_settlements.remove(node1)
                            for x in node1.adj:
                                if x in sim.possible_settlements:
                                    sim.possible_settlements.remove(x)
                            # print('playerstart pygame draw')
                            # pygame.draw.circle(self.surface, player.color, node1.coord, 10)
                            player.resources["clay"] = player.resources["clay"] - 1
                            player.resources["wood"] = player.resources["wood"] - 1
                            player.resources["sheep"] = player.resources["sheep"] - 1
                            player.resources["wheat"] = player.resources["wheat"] - 1
                            sim.update("Successfully built a settlement at location " + val + " for " + player.name)
                            done = True
                            if (player.checkifWin()):
                                sim.done=True
                                build=False
                                break
                        else:
                            print("Invalid selection. Please enter a valid selection")
                else:
                    print(player.name+" doesn't have enough resources to build a settlement")

            elif build_action == '3':
                #print('city')
                print("curr_player " + player.name)
                print("curr_roads: " + str(player.get_roadNames()))
                print("curr_settlements: " + str(player.get_settlementNames()))
                print("curr_cities: " + str(player.get_cityNames()))
                # TODO: prompt user to build city somewhere
                # allow user to build city from any settlements in sim.current_player.settlement
                if(len(player.settlement)==0):
                    print(player.name+ " doesn't have any settlements to upgrade to city.")

                elif player.canBuildCity():
                    done = False
                    while not done:
                        print("Select the location to build a city for " + player.name)
                        settlement_names=player.getSettlementName()
                        val = input("Possible locations to build a city are " + str(settlement_names) + " or enter 0 to exit \n")
                        if val=="0":
                            break
                        if val in settlement_names:
                            node1 = sim.getNode(val, player.settlement)
                            player.city.append(node1)
                            player.settlement.remove(node1)
                            # print('playerstart pygame draw')
                            # pygame.draw.circle(self.surface, player.color, node1.coord, 10)
                            sim.update(player.name + " successfully built a city at location " + val)
                            done = True
                            player.resources["ore"] = player.resources["ore"] - 3
                            player.resources["wheat"] = player.resources["wheat"] - 2
                            if (player.checkifWin()):
                                sim.done=True
                                build=False
                                break
                        else:
                            print("Invalid selection. Please enter a valid selection")
                else:
                    print(player.name+" doesn't have enough resources to build a city")
                

        return sim.current_player  # , node
