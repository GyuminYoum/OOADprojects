import numpy as np
import copy

class Player:
    def __init__(self, name, color):
        self.name = name
        self.roads = {}
        self.settlement = []
        self.city = []
        self.resources = {}
        self.vp = 0
        self.card = []
        self.color = color
        # self.command = None

    def roll(self):
        rng = np.random.randint(low=0, high=12)
        return rng

    # def trade(self):
        # trade logic
        # print("halp")

    # def build(self):
        # build logic
        # print("worry bout it later")
    def number_of_roads(self):
        return len(self.roads)

    def number_of_settlements(self):
        return len(self.settlement)

    def number_of_cities(self):
        return len(self.city)

    def generateRoadNameList(self):
        road_list=[]
        for node in self.settlement:
            for adj in node.adj:
                road_list.append(node.label + adj.label)
        for node in self.city:
            for adj in node.adj:
                road_list.append(node.label+adj.label)

        dict2=copy.deepcopy(self.roads)
        for road_key in dict2.keys():
            for adj_nodes in dict2[road_key]:
                road_name=road_key.label+adj_nodes.label
                road_list.append(road_name)
                dict2[road_key].remove(adj_nodes)
                dict2[adj_nodes].remove(road_key)

        return road_list

    # def setCommand(self, command):
    #     self.command = command
    #
    # def executeCommand(self):
    #     self.command.execute(self)
