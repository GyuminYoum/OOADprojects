from Commands.Command import Command

class useCard(Command):
    def __init__(self):
        super().__init__()

    def execute(self, sim):
        player = sim.current_player
        done = False
        # set lists
        hexnamelist = sim.hexNameList()
        resourcenamelist = sim.resourceNameList()

        while not done:
            # initial value
            hexname = "Z"
            resourcename = "A"
            resourcename1 = "A"
            resourcename2 = "A"

            # if user doesn't have unused cards, print it and break out of useCard
            # print(player.deckSummary())
            print('Used: ')
            print(player.deckSummary()[0])
            print('Unused: ')
            print(player.deckSummary()[1])
            if len(player.getUnusedCards(sim.turn)) == 0:
                print(player.name + " doesn't have any usable development cards.")
                done = True
                break
            else:
                # list unused card names and their counts
                unused_cards = player.getUnusedCards(sim.turn)
                print(player.name + " has " + str(len(unused_cards)) + " unused cards.")
                print(player.name + "'s cards are: ")
                deckDict = player.AvailableCardSummary(sim.turn)
                print(deckDict)
                print("What card would "+player.    name+" like to use?")
                val=input("Enter 0 for Knight, 1 for Monopoly, 2 for Year Of Plenty, 3 for Road Building, 4 for Exit: \n")
                if val=="0":
                    if player.hasKnight(sim.turn):
                        # previous available card map and resources check
                        # print("prev")
                        for x in sim.playerlist:
                            print(x.name, x.AvailableCardSummary(sim.turn))
                        for x in sim.playerlist:
                            print(x.name,x.resources)
                        # save current robber location to use for random resource collection when moving
                        curr_rob=sim.findRobber()
                        # reset robber
                        sim.resetRobber()
                        # error catching for robber
                        if curr_rob is not None:
                            print("Robber is currently at "+curr_rob)
                        else:
                            print("Currently Robber isn't placed anywhere")

                        while hexname not in hexnamelist:
                            hexname = input("Where would you like to place the robber? (A-S) \n")
                            if hexname not in hexnamelist:
                                print("Invalid location")
                        # set the robber to new hex
                        sim.setRobber(hexname)

                        sim.update(f'{player.name} successfully used a Knight card and moved the robber to Hex {hexname}')
                        robberlist = sim.knight(curr_rob, player)
                        if len(robberlist)==0:
                            sim.update( f'{player.name} moved the robber and received nothing')
                        elif len(robberlist)==1:
                            (rs1, count1, name1)=robberlist[0]
                            sim.update(f'{player.name} received {count1} {rs1} from {name1}')
                        elif  len(robberlist)==2:
                            (rs1, count1, name1) = robberlist[0]
                            (rs2, count2, name2) = robberlist[1]
                            sim.update(f'{player.name} received {count1} {rs1} from {name1} and {count2} {rs2} from {name2}')
                        elif len(robberlist)==3:
                            (rs1, count1, name1) = robberlist[0]
                            (rs2, count2, name2) = robberlist[1]
                            (rs3, count3, name3) = robberlist[2]
                            sim.update(f'{player.name} received {count1} {rs1} from {name1} and {count2} {rs2} from {name2} and {count3} {rs3} from {name3}')

                        # set 1 knight card to Used=True
                        player.useCard("Knight",sim.turn)
                        #if after using knight, player's amount of knight exceeds largestarmycount,
                        #reset largest army for all, set player's largestarmy to true, print
                        if sim.largestarmycount <= player.countKnights():
                            sim.resetLargestArmy()
                            player.largestarmy=True
                            print("")
                            sim.update(f'{player.name} now has the largest army with count of {player.countKnights()}.')
                            print("")

                        if (player.checkifWin()):
                            sim.done = True
                            break

                        #After resource, card check
                        print("After")
                        for x in sim.playerlist:
                            print(x.name, x.AvailableCardSummary(sim.turn))
                        for x in sim.playerlist:
                            print(x.name, x.resources)
                    else:
                        print(player.name + " doesn't have a Knight")
                elif val=="1":
                    if player.hasMonopoly(sim.turn):

                        #previous card,resource check
                        print("prev")
                        for x in sim.playerlist:
                            print(x.name, x.AvailableCardSummary(sim.turn))
                        for x in sim.playerlist:
                            print(x.name, x.resources)


                        while (resourcename not in resourcenamelist):
                            resourcename = input("What resource would you like to monopolize? ('sheep','clay','tree','ore','wheat') \n")
                            if (resourcename not in resourcenamelist):
                                print("Invalid Input")
                        sim.monopolize(player,resourcename)
                        player.useCard("Monopoly", sim.turn)
                        sim.update(f'{player.name} has successfully monopolized {resourcename}')

                        # After card, resource check
                        print("After")
                        for x in sim.playerlist:
                            print(x.name, x.AvailableCardSummary(sim.turn))
                        for x in sim.playerlist:
                            print(x.name, x.resources)
                    else:
                        print(player.name + " doesn't have a Monopoly Card")
                elif val=="2":
                    if player.hasYearOfPlenty(sim.turn):
                        # previous card, resource check
                        print("prev")
                        for x in sim.playerlist:
                            print(x.name, x.AvailableCardSummary(sim.turn))
                        for x in sim.playerlist:
                            print(x.name, x.resources)

                        while resourcename1 not in resourcenamelist or resourcename2 not in resourcenamelist:
                            print("What 2 resources would you like to get? ('sheep','clay','tree','ore','wheat') \n")
                            resourcename1 = input("Enter First Resource: \n")
                            resourcename2 = input("Enter Second Resource: \n")
                            if resourcename1 not in resourcenamelist:
                                print("Invalid Input for first resource")
                            if resourcename2 not in resourcenamelist:
                                print("Invalid Input for second resource")

                        # increment values by 1 for each resource
                        player.resources[resourcename1]+=1
                        player.resources[resourcename2]+=1
                        sim.update(f'{player.name} used Year of Plenty and has received {resourcename1} and '
                                   f'{resourcename2}.')
                        # turn 1 card to used=True
                        player.useCard("Year of Plenty", sim.turn)

                        # After card, resource check
                        print("After")
                        for x in sim.playerlist:
                            print(x.name, x.AvailableCardSummary(sim.turn))
                        for x in sim.playerlist:
                            print(x.name, x.resources)
                    else:
                        print(player.name+" doesn't have a Year Of Plenty Card")
                elif val=="3":
                    if player.hasRoadBuilding(sim.turn):
                        done1 = False
                        while not done1:
                            print('settlement')
                            print("curr_player " + player.name)
                            print("curr_roads: " + str(player.get_roadNames()))
                            print("curr_settlements: " + str(player.get_settlementNames()))
                            print("curr_cities: " + str(player.get_cityNames()))
                            print("Select the location for road for " + player.name)
                            road_list = sim.getPossibleRoads(player.generateRoadNameList())
                            val = input("Possible selections are " + str(road_list) + "\n")

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
                                sim.update(f'{player.name} used road building card and built a road at {val}.')
                                done1 = True
                            else:
                                print("Invalid Selection")

                        done1 = False
                        while not done1:
                            print('settlement')
                            print("curr_player " + player.name)
                            print("curr_roads: " + str(player.get_roadNames()))
                            print("curr_settlements: " + str(player.get_settlementNames()))
                            print("curr_cities: " + str(player.get_cityNames()))
                            print("Select the location for road for " + player.name)
                            road_list = sim.getPossibleRoads(player.generateRoadNameList())
                            val = input("Possible selections are " + str(road_list) + "\n")

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
                                sim.update(f'{player.name} used road building card and built a road at {val}.')
                                done1 = True
                            else:
                                print("Invalid Selection")

                        sim.longestRoadCheck()
                        player.useCard("Road Building", sim.turn)
                    else:
                        print(player.name+" doesn't have a Road Building Card")
                elif val=="4":
                    print(player.name+" exited")
                    break
                else:
                    print("Invalid input")