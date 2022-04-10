from numpy import random


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
