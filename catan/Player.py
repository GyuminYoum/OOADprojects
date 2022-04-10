import numpy as np
import random

class Player:
    def __init__(self,name, color):
        self.name=name
        self.roads=[]
        self.settlement=[]
        self.city=[]
        self.resources={}
        self.vp=0
        self.card=[]
        self.color=color

    def Roll(self):
        rng=random.randint(0,12)
        return rng

    def trade(self):
        #trade logic
        print("halp")

    def build(self):
        #build logic
        print("worry bout it later")
