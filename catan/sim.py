from Commands.Build import *
from Commands.Trade import *
from Commands.ResourceCom import *
from Commands.Invoker import *
from Commands.useCard import *
from Commands.buyCard import *
from Player import *
from Field import *
from cardFactory import *
from Observer.Observer import *

import numpy as np
import random


class sim:
    def __init__(self):
        self.playerlist = []
        self.playercount = 0
        self.current_player = None
        self.colorlist = [(255, 0, 0), (0, 0, 255), (0, 255, 255), (255, 255, 0)]
        self.deck = []
        self.field = []
        self.invoker = None
        self.observer = None
        self.possible_roads = {}
        self.possible_settlements = []
        self.done = False
        self.largestarmycount = 3
        self.longestroadcount = 4
        self.turn=0


        # self.trade = Trade()
        # self.build = Build()

    # function initializeGame
    # usage: Initiating stage
    # args: N/A
    # generate deck of DevelopmentCards, generate field, possible_settlements,roads and set invoker
    # returns: N/A
    def initializeGame(self):
        cardFactory1 = cardFactory()
        self.deck = cardFactory1.makeDeck()
        board = Field((500, 300), 50)
        self.field, self.possible_settlements = board.build()
        # self.possible_settlements = board.build()[1]
        # self.field[3].Robber=True
        self.possible_roads = board.generate_roads(self.field)
        invoker = Invoker()
        self.set_observer(Observer())
        self.invoker = invoker

    # function initializePlayers
    # usage: Initiating stage
    # args: N/A
    # prompt user for # of players, and their names, and assign colors
    # default resources to 0
    # returns: N/A
    def initializePlayers(self):
        while self.playercount != 3 and self.playercount != 4:
            try:
                num = int(input("Input the number of players (3-4) \n"))
                self.playercount = num
            except ValueError:
                print("Please provide integer value only")
        for x in range(self.playercount):
            val = input("Provide name for Player" + str(x + 1) + ": \n")
            # randomize color, no duplicate color
            temp = np.random.randint(0, len(self.colorlist) - 1)
            color = self.colorlist[temp]
            self.colorlist.pop(temp)

            # generate player object
            # default resources to 0
            p1 = Player(val, color)
            p1.resources['sheep'] = 1
            p1.resources['wood'] = 1
            p1.resources['ore'] = 1
            p1.resources['clay'] = 1
            p1.resources['wheat'] = 1
            # harbor_test_node = Node('B1', 1)
            # p1.settlement.append(harbor_test_node)

            # useCardTest
            '''
            if x == 0:
                p1.card.append(self.deck[11])
                p1.card.append(self.deck[12])
                p1.card.append(self.deck[13])
                p1.card.append(self.deck[14])
                p1.settlement.append(self.getNode("D3",self.possible_settlements))
                p1.settlement.append(self.getNode("D5", self.possible_settlements))
                p1.settlement.append(self.getNode("A1", self.possible_settlements))
                p1.settlement.append(self.getNode("B1", self.possible_settlements))
                p1.settlement.append(self.getNode("C1", self.possible_settlements))
                p1.settlement.append(self.getNode("C4", self.possible_settlements))
                p1.settlement.append(self.getNode("K3", self.possible_settlements))
                p1.settlement.append(self.getNode("O3", self.possible_settlements))
                p1.settlement.append(self.getNode("S3", self.possible_settlements))
                p1.roads[self.getNode("S3", self.possible_settlements)]= [self.getNode("S4", self.possible_settlements)]
                p1.roads[self.getNode("S4", self.possible_settlements)] = [self.getNode("S3", self.possible_settlements)]
                p1.roads[self.getNode("S4", self.possible_settlements)].append(self.getNode("R3", self.possible_settlements))
                p1.roads[self.getNode("R3", self.possible_settlements)] = [self.getNode("S4", self.possible_settlements)]

            elif x == 1:
                p1.card.append(self.deck[0])
                p1.card.append(self.deck[1])
                p1.card.append(self.deck[2])
                p1.card.append(self.deck[15])
                p1.card.append(self.deck[17])
                p1.card.append(self.deck[19])
                p1.settlement.append(self.getNode("D3", self.possible_settlements))
            elif x == 2:
                p1.card.append(self.deck[3])
                p1.card.append(self.deck[4])
                p1.card.append(self.deck[5])
                p1.card.append(self.deck[6])
                p1.card.append(self.deck[18])
                p1.card.append(self.deck[20])
                p1.settlement.append(self.getNode("D4", self.possible_settlements))
            '''

            self.playerlist.append(p1)

    def initialize(self):
        self.initializeGame()
        self.initializePlayers()

    # function playerStartSettlement
    # usage: Initiating stage: building settlement for players
    # args: player:Player
    # prompts user where he wants to build his settlement and builds if it's available
    # the settlement location is taken out from possiblesettlement list and added to user's settlement
    # re-prompt upon invalid request/choice
    # returns: N/A
    def playerStartSettlement(self, player):
        done = False
        node1 = None
        while not done:
            print(f'Select the location for settlement for  {player.name}')
            val = input("Possible selections are " + str(self.getPossibleSettlementNames()) + "\n")
            if self.AvailableNodeCheck(val, self.possible_settlements):
                node1 = self.getNode(val, self.possible_settlements)
                player.settlement.append(node1)
                self.possible_settlements.remove(node1)
                for x in node1.adj:
                    if x in self.possible_settlements:
                        self.possible_settlements.remove(x)

                print(f'Successfully built a settlement at location {val} for {player.name}.')
                done = True
            else:
                print("Invalid selection. Please enter a valid selection")

    # function playerStartRoad
    # usage: Initiating stage: building road for players
    # args: player:Player
    # prompts user where he wants to build his road based on his settlement and builds if it's available
    # the road dict etc. {A: B} as well as {B: A} are taken out of possible road_list and added to user's road
    # re-prompt upon invalid request/choice
    # returns: N/A
    def playerStartRoad(self, player):
        done1 = False
        nodes = None
        while not done1:
            print(f'Select the location for road for {player.name}')
            road_list = self.getPossibleRoads(player.generateRoadNameList())
            val = input("Possible selections are " + str(road_list) + "\n")
            if val in road_list:
                nodes = self.stringToRoad(val)
                self.possible_roads[nodes[0]].remove(nodes[1])
                self.possible_roads[nodes[1]].remove(nodes[0])
                if nodes[0] not in player.roads.keys():
                    player.roads[nodes[0]] = [nodes[1]]

                else:
                    player.roads[nodes[0]].append(nodes[1])
                if nodes[1] not in player.roads.keys():
                    player.roads[nodes[1]] = [nodes[0]]
                else:
                    player.roads[nodes[1]].append(nodes[0])
                print(f'Road has been built at {val} for {player.name}.')
                done1 = True
            else:
                print("Invalid Selection")

    # Checks

    # function AvailableNodeCheck
    # usage: Build Command, playerStartSettlement
    # args: node-name: str, node_list: list<Node>
    # true if the node with node_name is available, false if else
    # returns: N/A
    def AvailableNodeCheck(self, node_name, node_list):
        for x in node_list:
            if x.label == node_name:
                return True
        return False

    # function isValidRoad
    # usage: Build Command, playerStartRoad
    # args: s1:str, s2:str
    # true if the connection between nodes named s1 and s2 is possible, false if else
    # returns: N/A
    def isValidRoad(self, s1, s2):
        node1 = self.getNode(s1, self.possible_roads.keys())
        node2 = self.getNode(s2, self.possible_roads.keys())
        if node2 in self.possible_roads[node1] and node1 in self.possible_roads[node2]:
            return True
        return False

    ####################
    ##Helper functions##
    ####################

    # function getNode
    # usage: general purpose
    # args: node_name: str, node_list: list<Node>
    # return Node with label node_name from node_list
    # returns: Node/ None
    def getNode(self, node_name, node_list):
        for x in node_list:
            if x.label == node_name:
                return x
        return None

    # function stringToRoad
    # usage: parsing userinput for road in to connection
    # args: string1: str(user_input)
    # parse the userinput and after validating it, return the two nodes or return None
    # returns: (Node,Node)/ None
    def stringToRoad(self, string1):
        s1 = string1[:2]
        s2 = string1[2:4]
        if self.isValidRoad(s1, s2):
            node1 = self.getNode(s1, self.possible_roads.keys())
            node2 = self.getNode(s2, self.possible_roads.keys())
            return node1, node2
        else:
            # print("Invalid")
            return None

    def endgame(self, player):
        self.update(f'{player.name} wins with {str(player.vp)} points\n')
        self.update(f'{player.vp} points from: {len(player.settlement)} settlements, {len(player.city)}'
                    f' cities, {player.getVPCount()} VP cards, {2 * player.longestroad} points from having '
                    f'the longest road, {2 * player.largestarmy} points from having the largest army.\n')
        quit()

    # function playerAction
    # usage: plays through a player's entire turn, including Resource, Build, and Trade phase
    def playerAction(self):
        for p in self.playerlist:
            self.current_player = p
            done = False
            print(f'{p.name}\'s turn')
            if p.name == self.playerlist[0].name:
                self.turn = self.turn + 1
                print("turn: "+str(self.turn))
            choice=-1
            while not done:
                while choice != '1' and choice != '2' and choice != '3' and choice != '4' and choice != '5' \
                        and choice != '0':
                    choice = input(f'What would {self.current_player.name} like to do? '
                                   f'(1:use Dev Card, 2: Roll, 3: Build, 4: Trade, 5: Buy Dev Card, 0: end turn)\n')
                    if choice != '0' and choice != '1' and choice != '2' and choice != '3' and choice != '4' \
                            and choice != '5':
                        print("Invalid Input")
                if choice == '1':
                    self.invoker.set_command(useCard())
                    self.invoker.execute_command(self)
                    if self.done:
                        self.endgame(self.current_player)
                elif choice == '2':
                    self.invoker.set_command(ResourceCom())
                    self.invoker.execute_command(self)
                elif choice == '3':
                    self.invoker.set_command(Build())
                    self.invoker.execute_command(self)
                    if self.done:
                        self.endgame(self.current_player)
                elif choice == '4':
                    self.invoker.set_command(Trade())
                    self.invoker.execute_command(self)
                elif choice == '5':
                    self.invoker.set_command(buyCard())
                    self.invoker.execute_command(self)
                    if self.done:
                        self.endgame(self.current_player)
                elif choice == '0':
                    if not self.current_player.rolled:
                        print(f'{self.current_player.name} did not roll yet')
                    else:
                        self.current_player.rolled=False
                        done = True
                choice = -1
            # self.invoker.set_command(ResourceCom())
            # self.invoker.execute_command(self)
            # self.invoker.set_command(Build())
            # self.invoker.execute_command(self)
            # self.longestRoadCheck()
            # if self.done:
            #     self.endgame(self.current_player)
            #
            # self.invoker.set_command(Trade())
            # self.invoker.execute_command(self)
            # self.invoker.set_command(useCard())
            # self.invoker.execute_command(self)
            # self.longestRoadCheck()
            # if self.done:
            #     self.endgame(self.current_player)
            #
            # self.invoker.set_command(buyCard())
            # self.invoker.execute_command(self)
            # if self.done:
            #     self.endgame(self.current_player)

    def set_invoker(self, invoker):
        self.invoker = invoker

    def execute(self):
        self.invoker.execute_command(self)

    def set_observer(self, observer):
        self.observer = observer

    def update(self, message):
        self.observer.update(message)

    def roll(self):
        rng = np.random.randint(low=2, high=12)
        return rng

    # function hexNameList
    # usage: general purpose
    # args: N/A
    # generate list of names of each hex
    # returns: list<Str>
    def hexNameList(self):
        list1 = []
        for x in self.field:
            if x.name not in list1:
                list1.append(x.name)
        return list1

    # function resourceNameList
    # usage: general purpose
    # args: N/A
    # generate list of names of each resources
    # returns: list<Str>
    def resourceNameList(self):
        list1 = []
        for x in self.playerlist[0].resources.keys():
            if x not in list1:
                list1.append(x)
        return list1

    # function getPossibleSettlementNames
    # usage: general purpose
    # args: N/A
    # generate list of names of every possible settlement
    # returns: list<Str>
    def getPossibleSettlementNames(self):
        list1 = []
        for node in self.possible_settlements:
            list1.append(node.label)
        return list1

    # function getPossibleRoads
    # usage: general purpose
    # args: user_all_road_list:list<string>
    # given list of all the roads of a player, validate each string and see if the road can still be constructed
    # returns: list<Str> list of name of roads that can still be built
    def getPossibleRoads(self, user_all_road_list):
        list1 = []
        # print(user_all_road_l  ist)
        for road_name in user_all_road_list:
            parsed = self.stringToRoad(road_name)
            if parsed is not None:
                node1 = parsed[0]
                node2 = parsed[1]
                if node2 in self.possible_roads[node1]:
                    list1.append(road_name)
        return list1

    # function filterPossibleSettlement
    # usage: build command
    # args: listofnode: list<Node>
    # given list of nodes, validate which node locations are still available for settlement
    # returns: list<Node>
    def filterPossibleSettlement(self, listofnode):
        list1 = []
        list2 = self.getPossibleSettlementNames()
        # print("possible settlements: "+ str(list2))
        for node in listofnode:
            if node.label in list2:
                list1.append(node)
        return list1

    # function convertNodeListToNameList
    # usage: general purpose
    # args: nodelist: List<Node>
    # given a list of nodes, convert to list of node.label
    # returns: list<Str>
    def convertNodeListToNameList(self, nodelist):
        list1 = []
        for x in nodelist:
            if x.label not in list1:
                list1.append(x.label)
        return list1

    # function roadToString
    # usage: build command
    # args: dict1: Dict<Node,Node>
    # given a dictionary of roads, return list of roadnames
    # returns: list<Str>
    def roadToString(self, dict1):
        list1 = []
        for key in dict1.keys():
            for adj in dict1[key]:
                list1.append(key.label + adj.label)
        return list1

    ###############################
    # Functions for useCard Command#
    ##############################

    # function resetRobber
    # usage: resets robber location across all the hexes
    def resetRobber(self):
        for x in self.field:
            x.Robber = False

    # function setRobber
    # usage: sets robber location to a certain hex
    # args: name: Str
    # move the robber to the hexagon with the given name
    def setRobber(self, name):
        for x in self.field:
            if x.name == name:
                x.Robber = True

    # function monopolize
    # usage: useCard for monopoly
    # args: player:Player, resourcename:str
    # takes all the resources from other user and gives it to the specified user
    # returns: N/A
    def monopolize(self, player, resourcename):
        val = 0
        for x in self.playerlist:
            if x.name is not player.name:
                val += x.resources[resourcename]
                x.resources[resourcename] = 0
        for x in self.playerlist:
            if x.name is player.name:
                x.resources[resourcename] += val

    # function knight
    # usage: useCard for knight
    # args: hexname:str , player: Player
    # when knight is used to move robber from a hex, the owner of the knight card takes away
    # n(# of settlement+city) random resources from the players who had settlement/city in that hex.
    # Player.countDonation(hexname) returns the count of settlements/cities in the Hex with hexname.
    # function then, randomly determines which non-depleted resource to take from the afflicted player
    # and adds it to the owner of the knight card
    # returns: N/A
    def knight(self, hexname, player):
        list1 = []
        resourcetype = None
        for x in self.playerlist:
            count = 0
            if x.name is not player.name:
                donationcount = x.countDonation(hexname)
                while count < 1:
                    rand = random.randint(0, 4)
                    rs = self.resourceNameList()
                    resourcetype = rs[rand]
                    count = x.resources[resourcetype]
                x.resources[resourcetype] -= donationcount
                player.resources[resourcetype] += donationcount
                list1.append((resourcetype, donationcount, x.name))
        return list1

    # function findRobber
    # usage: useCard for knight
    # args: N/A
    # finds name of the hex that has Robber
    # returns: str/None
    def findRobber(self):
        for x in self.field:
            # print(x.name, x.Robber)
            if x.Robber:
                return x.name
        return None

    # function buyCard
    # usage: buyCard command for knight
    # args: player: Player
    # if there is still card in the deck, pick 1 randomly and assign it to the player
    # subtract appropriate resources from the player and exclude that card from the deck
    # returns: Card/None
    def buyCard(self, player):
        if len(self.deck) == 0:
            return None
        rng = random.randint(0, len(self.deck) - 1)
        card = self.deck[rng]
        card.turn=self.turn
        player.card.append(card)
        self.deck.remove(card)
        player.resources['ore'] -= 1
        player.resources['sheep'] -= 1
        player.resources['wheat'] -= 1
        return card

    # function resetLargestArmy
    # usage: useCard for knight
    # args:
    # reset all player's largestarmy attribute to False
    # returns: N/A
    def resetLargestArmy(self):
        for x in self.playerlist:
            x.largestarmy = False

    # function longestRoadCheck
    # usage: checks if the current_player has both the longest road, and a long enough road to earn the
    #        Longest Road status
    def longestRoadCheck(self):
        player = self.current_player
        maxroad = 0
        if not player.roads:
            return
        for key in player.roads.keys():
            maxroad = max(maxroad, self.longestRoadDFS(player, key, [key], 0))

        if maxroad > self.longestroadcount:
            for player1 in self.playerlist:
                player1.longestroad = False
            player.longestroad = True
            self.longestroadcount = maxroad
            self.update(f'{self.current_player.name} now has the longest road with count of '+str(self.longestroadcount))
            if player.checkifWin():
                self.done = True

    # function longestRoadDFS
    # usage: helper method for longestRoadCheck that performs a recursive Depth-First Search (DFS) to find the
    #        current_player's longest road length
    # args: player: the current player
    #       start: the node at which to start DFS
    #       explored: a list of the nodes that have been explored
    #       count: the number of roads that exist consecutively on the currently-searched path
    # returns: length of the current_player's longest consecutive road
    def longestRoadDFS(self, player, start, explored, count):
        maxcount = count
        for nextnode in player.roads[start]:
            if nextnode not in explored:
                explored.append(nextnode)
                maxcount = max(maxcount, self.longestRoadDFS(player, nextnode, explored, count + 1))
        # print(maxcount)
        return maxcount
