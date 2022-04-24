import sim
from sim import *
from Commands.Build import *
from Commands.Invoker import *
from Commands.Trade import *
from Field import *
from Observer.Observer import Observer
import tkinter as tk
import os
from PIL import Image
import pygame
import sys
from pygame.locals import *

import os
from PIL import Image


class Catan:
    def __init__(self):
        self.sim = sim()
        self.board =0

    def initialize(self):
        self.sim.initialize()
        pygame.init()
        # create the display surface object
        # of specific dimension.
        window = pygame.display.set_mode((1000, 1000))
        # Fill the scree with white color
        pygame.display.set_caption('Settlers of Catan')
        window.fill((255, 255, 255))
        self.board = window
        self.sim.initialize()
        pygame.font.init()
        default_font=pygame.font.get_default_font()
        font_renderer=pygame.font.Font(default_font, 20)

        for x in self.sim.field:
            # for filled hexagon
            pygame.draw.polygon(self.board, x.Resource.color, x.get_coords())
            # for outline
            pygame.draw.polygon(self.board, (0, 0, 0), x.get_coords(), width=3)
            pygame.display.flip()
            label= font_renderer.render(str(x.value),1,(0,0,0))
            self.board.blit(label,(x.p1.coord[0]-5,((x.p1.coord[1]+x.p4.coord[1])/2)-10))
        font_renderer = pygame.font.Font(default_font,17)
        for node in self.sim.possible_settlements:
            label = font_renderer.render(str(node.label), 1, (0, 0, 0))
            self.board.blit(label,(node.coord[0]-10,node.coord[1]-20))


        pygame.image.save(window, 'game.jpg')
        pygame.quit()
        script_dir= os.path.dirname(__file__)
        rel_path= "game.jpg"
        relative_path=os.path.join(script_dir, rel_path)
        im= Image.open(relative_path)
        im.show()
        os.remove(relative_path)


        # invoker = Invoker()
        # invoker.set_command(Build())
        # # invoker.set_command(Trade())
        # self.sim.set_invoker(invoker)
        # self.sim.set_observer(Observer())

    def main(self):

        for player in self.sim.playerlist:
            print("current_player: " + player.name)
            self.sim.playerStartSettlement(player)
            # pygame.draw.circle(self.board,p1.color,node.coord,10)
            # pygame.display.flip()
            self.sim.playerStartRoad(player)
            # pygame.draw.line(self.board,p2.color,n1.coord,n2.coord,5)
            # pygame.display.flip()

        reverselist=list(reversed(self.sim.playerlist))

        for player in reverselist:
            self.sim.playerStartSettlement(player)
            # # pygame.draw.circle(self.board,p1.color,node.coord,10)
            # # pygame.display.flip()
            self.sim.playerStartRoad(player)
            # # pygame.draw.line(self.board,p2.color,n1.coord,n2.coord,5)
            # # pygame.display.flip()

       
        while self.sim.done==False:
            # TODO: vars or loops to go through players in order 12344321 123321
                #self.sim1.playerAction
                # check for victory points here probably
                # we can do resource build trade for one player before updating display tbh


            self.sim.playerAction()


