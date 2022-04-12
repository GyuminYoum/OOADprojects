import pygame
import numpy as np
from Hex import *
import random
import copy
from Resource import *

class Field:
    def __init__(self,coord,sidelength):
        self.start=coord
        #(500,450) center
        self.length=sidelength
        self.hexlist=[]

    def buildLeft(self,name,hex2):
        hex1=Hex(name,(hex2.p1[0]-2*hex2.lateral,hex2.p1[1]),self.length)
        self.hexlist.append(hex1)
        return hex1

    def buildRight(self,name,hex2):
        hex1=Hex(name,(hex2.p1[0]+2*hex2.lateral,hex2.p1[1]),self.length)
        self.hexlist.append(hex1)
        return hex1

    def buildBotLeft(self,name,hex2):
        hex1=Hex(name,hex2.p5,self.length)
        self.hexlist.append(hex1)
        return hex1

    def buildBotRight(self,name,hex2):
        hex1=Hex(name,hex2.p3,self.length)
        self.hexlist.append(hex1)
        return hex1

    def build(self):
        hex1=Hex("B",self.start,self.length)
        self.hexlist.append(hex1)
        hex2=self.buildLeft("A", hex1)
        hex3=self.buildRight("C", hex1)
        hex4=self.buildBotLeft("D",hex2)
        hex5=self.buildRight("E",hex4)
        hex6 = self.buildRight("F", hex5)
        hex7 = self.buildRight("G", hex6)
        hex8= self.buildBotLeft("H",hex4)
        hex9= self.buildRight("I", hex8)
        #hex10 = self.buildRight("J", hex9)
        hex11 = self.buildBotRight("K", hex6)
        hex12 = self.buildRight("L", hex11)
        hex13 = self.buildBotRight("M",hex8)
        hex14= self.buildRight("N",hex13)
        hex15 = self.buildRight("O", hex14)
        hex16 = self.buildRight("P", hex15)
        hex17= self.buildBotRight("Q",hex13)
        hex18= self.buildRight("R",hex17)
        hex19= self.buildRight("S",hex18)

        sheep = Resource("Sheep",(144, 238, 144))
        forest = Resource("Forest",(0, 128, 0))
        desert = Resource("Desert",(237, 201, 175))
        ore = Resource("Ore",(192, 192, 192))
        clay = Resource("Clay",(107, 104, 103))
        wheat = Resource("Wheat",(255, 255, 0))
        colors = [sheep, sheep, sheep, sheep, forest, forest, forest, forest, ore, ore, ore, clay, clay, clay, wheat,
                  wheat, wheat, wheat]
        roll_list = [2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12]
        #assign resources to each hex
        for shape in self.hexlist:
            index = random.randint(0, len(colors) - 1)
            temp_color = colors[index]
            shape.Resource = temp_color
            #pygame.draw.polygon(surface, temp_color, shape.get_coords())
            colors.remove(temp_color)
            index = random.randint(0,len(roll_list)-1)
            number= roll_list[index]
            shape.value=number
            roll_list.remove(number)

        #set desert
        hex10 = self.buildRight("J", hex9)
        hex10.Resource= desert

