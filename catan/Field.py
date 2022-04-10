import pygame
import numpy as np
from Hex import *
import random
import copy
from Resource import *


class Field:
    def __init__(self, coord, sidelength):
        self.start = coord
        # (500,450) center
        self.length = sidelength
        self.hexlist = []

    def buildLeft(self, name, hex2):
        hex1 = Hex(name, (hex2.p1[0]-2*hex2.lateral, hex2.p1[1]), self.length)
        self.hexlist.append(hex1)
        return hex1

    def buildRight(self, name, hex2):
        hex1 = Hex(name, (hex2.p1[0]+2*hex2.lateral, hex2.p1[1]), self.length)
        self.hexlist.append(hex1)
        return hex1

    def buildBotLeft(self, name, hex2):
        hex1 = Hex(name, hex2.p5, self.length)
        self.hexlist.append(hex1)
        return hex1

    def buildBotRight(self, name, hex2):
        hex1 = Hex(name, hex2.p3, self.length)
        self.hexlist.append(hex1)
        return hex1

    def build(self):
        hex1 = Hex("B", self.start, self.length)
        self.hexlist.append(hex1)
        hex2 = self.buildLeft("A", hex1)
        hex3 = self.buildRight("C", hex1)
        hex4 = self.buildBotLeft("D", hex2)
        hex5 = self.buildRight("E", hex4)
        hex6 = self.buildRight("F", hex5)
        hex7 = self.buildRight("G", hex6)
        hex8 = self.buildBotLeft("H", hex4)
        hex9 = self.buildRight("I", hex8)
        # hex10 = self.buildRight("J", hex9)
        hex11 = self.buildBotRight("K", hex6)
        hex12 = self.buildRight("L", hex11)
        hex13 = self.buildBotRight("M", hex8)
        hex14 = self.buildRight("N", hex13)
        hex15 = self.buildRight("O", hex14)
        hex16 = self.buildRight("P", hex15)
        hex17 = self.buildBotRight("Q", hex13)
        hex18 = self.buildRight("R", hex17)
        hex19 = self.buildRight("S", hex18)

        sheep = Resource("Sheep", (144, 238, 144))
        forest = Resource("Forest", (0, 128, 0))
        desert = Resource("Desert", (237, 201, 175))
        ore = Resource("Ore", (192, 192, 192))
        clay = Resource("Clay", (107, 104, 103))
        wheat = Resource("Wheat", (255, 255, 0))
        colors = [sheep, sheep, sheep, sheep, forest, forest, forest, forest, ore, ore, ore, clay, clay, clay, wheat,
                  wheat, wheat, wheat]

        # assigns a random resource to each hexagon
        for shape in self.hexlist:
            index = random.randint(0, len(colors) - 1)
            temp_color = colors[index]
            shape.Resource = temp_color
            # pygame.draw.polygon(surface, temp_color, shape.get_coords())
            colors.remove(temp_color)
        hex10 = self.buildRight("J", hex9)
        hex10.Resource= desert













"""
hex2=hex((500,300),50)
hex1=hex((hex2.p1[0]-2*hex2.lateral,hex2.p1[1]),50)
hex3=hex((hex2.p1[0]+2*hex2.lateral,hex2.p1[1]),50)
hex4=hex((hex1.p5),50)
hex5=hex((hex1.p3),50)
hex6=hex((hex2.p3),50)
hex7=hex((hex3.p3),50)

hex8=hex((hex4.p5),50)
hex9=hex((hex4.p3),50)
hex10=hex((hex5.p3),50)
print(hex10.get_coords())
hex11=hex((hex6.p3),50)
hex12=hex((hex7.p3),50)

hex13=hex((hex8.p3),50)
hex14=hex((hex9.p3),50)
hex15=hex((hex10.p3),50)
hex16=hex((hex11.p3),50)

hex17=hex((hex13.p3),50)
hex18=hex((hex14.p3),50)
hex19=hex((hex15.p3),50)

list1=[hex1,hex2,hex3,hex4,hex5,hex6,hex7,hex8,hex9,hex11,hex12,hex13,hex14,hex15,hex16,hex17,hex18,hex19]

#hex3=hex(hex1.p3,50)
pygame.init()
surface=pygame.display.set_mode((1000,1000))

#pygame.draw.rect(surface,color,pygame.Rect(30,30,60,60))
#pygame.draw.rect(surface,color,pygame.Rect(90,90,180,180))
"""
"""
pygame.draw.polygon(surface,white,hex1.get_coords())
pygame.draw.polygon(surface,wheat,hex2.get_coords())
pygame.draw.polygon(surface,ore,hex3.get_coords())
pygame.draw.polygon(surface,clay,hex4.get_coords())
pygame.draw.polygon(surface,wheat,hex5.get_coords())
pygame.draw.polygon(surface,ore,hex6.get_coords())
pygame.draw.polygon(surface,forest,hex7.get_coords())
"""
"""
pygame.draw.polygon(surface,desert,hex10.get_coords())
temp=copy.deepcopy(colors)



while(1):
    pygame.event.get()
    pygame.display.flip()
"""