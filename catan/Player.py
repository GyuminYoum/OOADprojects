import numpy as np


class Player:
    def __init__(self, name, color):
        self.name = name
        self.roads = []
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

    # def setCommand(self, command):
    #     self.command = command
    #
    # def executeCommand(self):
    #     self.command.execute(self)
