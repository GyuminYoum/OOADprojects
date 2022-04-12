import pygame
import numpy as np
from hex import *
import random
import copy

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
white=(255,255,255)
sheep=(144,238,144)
forest=(0,128,0)
desert=(237,201,175)
ore=(192,192,192)
clay=(107,104,103)
wheat=(255,255,0)
colors=[sheep,sheep,sheep,sheep,forest,forest,forest,forest,ore,ore,ore,clay,clay,clay,wheat,wheat,wheat,wheat]
#pygame.draw.rect(surface,color,pygame.Rect(30,30,60,60))
#pygame.draw.rect(surface,color,pygame.Rect(90,90,180,180))
"""
pygame.draw.polygon(surface,white,hex1.get_coords())
pygame.draw.polygon(surface,wheat,hex2.get_coords())
pygame.draw.polygon(surface,ore,hex3.get_coords())
pygame.draw.polygon(surface,clay,hex4.get_coords())
pygame.draw.polygon(surface,wheat,hex5.get_coords())
pygame.draw.polygon(surface,ore,hex6.get_coords())
pygame.draw.polygon(surface,forest,hex7.get_coords())
"""
pygame.draw.polygon(surface,desert,hex10.get_coords())
temp=copy.deepcopy(colors)
for shape in list1:
    index=random.randint(0,len(temp)-1)
    temp_color=temp[index]
    pygame.draw.polygon(surface,temp_color,shape.get_coords())
    temp.remove(temp[index])


while(1):
    pygame.display.flip()