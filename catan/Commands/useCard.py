from Commands.Command import Command

class useCard(Command):
    def __init__(self):
        super().__init__()

    def execute(self, sim):
        player=sim.current_player
        done=False
        #set lists
        hexnamelist = sim.hexNameList()
        resourcenamelist = sim.resourceNameList()

        while(done==False):
            #initial value
            hexname = "Z"
            resourcename = "A"
            resourcename1 = "A"
            resourcename2 = "A"

            #if user doesn't have unused cards, print it and break out of useCard
            if len(player.getUnusedCards()) == 0:
                print(player.name + " doesn't have any development cards")
                break
            else:
                #list unused card names and their counts
                unused_cards = player.getUnusedCards()
                print(player.name + " has " + str(len(unused_cards)) + " unused cards")
                print(player.name + "'s cards are: ")
                deckDict = player.AvailableCardSummary()
                print(deckDict)
                print("What card would you like to use?")
                val=input("Enter 0 for Knight, 1 for Monopoly, 2 for Year Of Plenty, 3 for Road Building, 4 for Exit: \n")
                if val=="0":
                    if player.hasKnight():
                        #previous available card map and resources check
                        print("prev")
                        for x in sim.playerlist:
                            print(x.name, x.AvailableCardSummary())
                        for x in sim.playerlist:
                            print(x.name,x.resources)
                        #save current robber location to use for random resource collection when moving
                        curr_rob=sim.findRobber()
                        #reset robber
                        sim.resetRobber()
                        #error catching for robber
                        if curr_rob is not None:
                            print("Robber is currently at "+curr_rob)
                        else:
                            print("Currently Robber isn't placed anywhere")

                        while(hexname not in hexnamelist):
                            hexname=input("Where would you like to place the robber? (A-S) \n")
                            if(hexname not in hexnamelist):
                                print("Invalid location")
                        #set the robber to new hex
                        sim.setRobber(hexname)
                        print(player.name+" successfully used a Knight card and moved the robber to Hex "+hexname)
                        (rs1,count1,name1),(rs2,count2,name2)=sim.knight(curr_rob,player)
                        print(player.name+" received "+str(count1)+" "+ rs1+" from "+name1+" and "+str(count2)+" "+ rs2+ " from "+name2)
                        #set 1 knight card to Used=True
                        player.useCard("Knight")
                        #if after using knight, player's amount of knight exceeds largestarmycount,
                        #reset largest army for all, set player's largestarmy to true, print
                        if sim.largestarmycount <= player.countKnights():
                            sim.resetLargestArmy()
                            player.largestarmy=True
                            print("")
                            print(player.name+" now has the largest army with count of "+str(player.countKnights()))
                            print("")

                        #After resource, card check
                        print("After")
                        for x in sim.playerlist:
                            print(x.name, x.AvailableCardSummary())
                        for x in sim.playerlist:
                            print(x.name, x.resources)
                    else:
                        print(player.name + " doesn't have a Knight")
                elif val=="1":
                    if player.hasMonopoly():

                        #previous card,resource check
                        print("prev")
                        for x in sim.playerlist:
                            print(x.name, x.AvailableCardSummary())
                        for x in sim.playerlist:
                            print(x.name, x.resources)


                        while (resourcename not in resourcenamelist):
                            resourcename = input("What resource would you like to monopolize? ('sheep','clay','tree','ore','wheat') \n")
                            if (resourcename not in resourcenamelist):
                                print("Invalid Input")
                        sim.monopolize(player,resourcename)
                        player.useCard("Monopoly")
                        print(player.name+ " has successfully monopolized "+ resourcename)

                        #After card, resource check
                        print("After")
                        for x in sim.playerlist:
                            print(x.name, x.AvailableCardSummary())
                        for x in sim.playerlist:
                            print(x.name, x.resources)
                    else:
                        print(player.name + " doesn't have a Monopoly Card")
                elif val=="2":
                    if player.hasYearOfPlenty():
                        # previous card, resource check
                        print("prev")
                        for x in sim.playerlist:
                            print(x.name, x.AvailableCardSummary())
                        for x in sim.playerlist:
                            print(x.name, x.resources)

                        while (resourcename1 not in resourcenamelist or resourcename2 not in resourcenamelist):
                            print("What 2 resources would you like to get? ('sheep','clay','tree','ore','wheat') \n")
                            resourcename1=input("Enter First Resource: \n")
                            resourcename2=input("Enter Second Resource: \n")
                            if (resourcename1 not in resourcenamelist):
                                print("Invalid Input for first resource")
                            if (resourcename2 not in resourcenamelist):
                                print("Invalid Input for second resource")

                        #increment values by 1 for each resource
                        player.resources[resourcename1]+=1
                        player.resources[resourcename2]+=1
                        print(player.name+" has received "+resourcename1+" and "+ resourcename2)
                        #turn 1 card to used=True
                        player.useCard("Year of Plenty")

                        # After card, resource check
                        print("After")
                        for x in sim.playerlist:
                            print(x.name, x.AvailableCardSummary())
                        for x in sim.playerlist:
                            print(x.name, x.resources)
                    else:
                        print(player.name+" doesn't have a Year Of Plenty Card")
                elif val=="3":
                    if player.hasRoadBuilding():
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
                                print(player.name + " built a road at " + val)
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
                                print(player.name + " built a road at " + val)
                                done1 = True
                            else:
                                print("Invalid Selection")

                        player.useCard("Road Building")

                    else:
                        print(player.name+" doesn't have a Road Building Card")
                elif val=="4":
                    print(player.name+" exited")
                    break
                else:
                    print("Invalid input")