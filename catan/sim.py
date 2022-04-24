from Commands.Build import *
from Commands.Trade import *
from Commands.Resource import *
from Commands.Invoker import *
from Commands.Command import *
from Player import *
from Field import *
from cardFactory import *
from Road import *

import numpy as np


class sim:
    def __init__(self):
        self.playerlist = []
        self.playercount = 0
        self.current_player = None
        # self.colorlist = ["R", "B", "W", "BK"]
        self.colorlist = [(255, 0, 0), (0, 0, 255), (0, 255, 255), (255, 255, 0)]
        self.deck = []
        self.field = []
        self.invoker = None
        self.observer = None
        self.possible_roads = {}
        self.possible_settlements = []
        self.done=False

        # self.trade = Trade()
        # self.build = Build()

    def initializeGame(self):
        cardFactory1 = cardFactory()
        self.deck = cardFactory1.makeDeck()
        board = Field((500, 300), 50)
        self.field, self.possible_settlements = board.build()
        # self.possible_settlements = board.build()[1]
        self.possible_roads = board.generate_roads(self.field)

    def initializePlayers(self):
        while self.playercount != 3 and self.playercount != 4:
        # while self.playercount < 1:
        
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
            p1.resources['sheep'] = 10
            p1.resources['wood'] = 10
            p1.resources['ore'] = 10
            p1.resources['clay'] = 10
            p1.resources['wheat'] = 10
            # harbor_test_node = Node('B1', 1)
            # p1.settlement.append(harbor_test_node)
            self.playerlist.append(p1)

    def playerStartSettlement(self, player):
        # pygame.draw.circle(self.surface, self.playerlist[0].color, coord, 10)
        done = False
        node1 = None
        while not done:
            print("Select the location for settlement for " + player.name)
            val = input("Possible selections are " + str(self.getSettlementNames()) + "\n")
            if self.AvailableNodeCheck(val, self.possible_settlements):
                node1 = self.getNode(val, self.possible_settlements)
                player.settlement.append(node1)
                self.possible_settlements.remove(node1)
                for x in node1.adj:
                    if x in self.possible_settlements:
                        self.possible_settlements.remove(x)
                # print('playerstart pygame draw')
                # pygame.draw.circle(self.surface, player.color, node1.coord, 10)
                print("Successfully built a settlement at location " + val + " for "+player.name)
                done = True
            else:
                print("Invalid selection. Please enter a valid selection")
        return player, node1



    def playerStartRoad(self,player):
        done1 = False
        nodes = None
        while not done1:
            print("Select the location for road for " + player.name)
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
                print("Road has been built at " + val + " for " + player.name)
                done1 = True
            else:
                print("Invalid Selection")
        return player, nodes


    def AvailableNodeCheck(self, node_name, node_list):
        for x in node_list:
            if x.label == node_name:
                return True
        return False

    def getNode(self, node_name, node_list):
        for x in node_list:
            if x.label == node_name:
                return x
        return None

    def getSettlementNames(self):
        list1 = []
        for x in self.possible_settlements:
            list1.append(x.label)
        return list1

    def isValidRoad(self, s1, s2):
        node1 = self.getNode(s1, self.possible_roads.keys())
        node2 = self.getNode(s2, self.possible_roads.keys())
        if node2 in self.possible_roads[node1]:
            return True
        return False

    def filterPossibleSettlement(self,listofnode):
        list1 = []
        list2 = self.getPossibleSettlementNames()
        print("possible settlements: " + str(list2))
        for node in listofnode:
            if node.label in list2:
                list1.append(node)
        return list1

    def getPossibleSettlementNames(self):
        list1 = []
        for node in self.possible_settlements:
            list1.append(node.label)
        return list1

    def convertNodeListToNameList(self, nodelist):
        list1 = []
        for x in nodelist:
            if x.label not in list1:
                list1.append(x.label)
        return list1

    def roadToString(self, dict1):
        list1 = []
        for key in dict1.keys():
            for adj in dict1[key]:
                list1.append(key.label+adj.label)
        return list1


    def stringToRoad(self, string1):
        s1 = string1[:2]
        s2 = string1[2:4]
        if self.isValidRoad(s1, s2):
            node1 = self.getNode(s1, self.possible_roads.keys())
            node2 = self.getNode(s2, self.possible_roads.keys())
            return node1, node2
        else:
            print("Invalid")
            return None

    def getPossibleRoads(self, user_all_road_list):
        list1 = []
        # print(user_all_road_list)
        for road_name in user_all_road_list:
            parsed = self.stringToRoad(road_name)
            if parsed is not None:
                node1 = parsed[0]
                node2 = parsed[1]
                if node2 in self.possible_roads[node1]:
                    list1.append(road_name)
            # else:
            #     print(str(road_name)+"null object")
        return list1

        # print(self.field)

        # for z in self.deck:
        #     print(z.name)

    def playerAction(self):
        for x in self.playerlist:
            self.current_player = x
        val = self.roll()

        # for hex in self.sim.
        # do resource phase stuff
        self.invoker.set_command(Resource())
        self.invoker.execute_command(self)
        self.invoker.set_command(Build())
        self.invoker.execute_command(self)
        self.invoker.set_command(Trade())
        self.invoker.execute_command(self)

        # check for win condition
        # victory points: len(settlements) + len(cities) + longest road/largest army

    def initialize(self):
        self.initializePlayers()
        self.initializeGame()
        # self.playerStart()
        # self.playerAction()
        # print(self.playercount)

    def set_invoker(self, invoker):
        self.invoker = invoker

    def execute(self):
        self.invoker.execute_command(self)

    def set_observer(self, observer):
        self.observer = observer

    def update(self, message):
        self.observer.update(message)

    def roll(self):
        rng = np.random.randint(low=0, high=12)
        return rng
