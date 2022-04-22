import sim
from sim import *
from Commands.Build import *
from Commands.Invoker import *
from Commands.Trade import *
from Field import *
from Observer.Observer import Observer

import pygame
import sys

import os
from PIL import Image

class Catan:

    def __init__(self):
        self.sim = sim()

    def initialize(self):
        self.sim.initialize()
        pygame.init()
        # create the display surface object
        # of specific dimension.
        window = pygame.display.set_mode((1000, 1000))
        # Fill the scree with white color
        window.fill((255, 255, 255))
        self.sim.surface = window

        for x in self.sim.field:
            # for filled hexagon
            pygame.draw.polygon(window, x.Resource.color, x.get_coords())
            # for outline
            pygame.draw.polygon(window, (0, 0, 0), x.get_coords(), width=3)
            pygame.display.flip()

        pygame.font.init()
        myfont = pygame.font.SysFont('Comic Sans MS', 30)
        text_surface = (myfont.render('A', False, (0, 0, 0)))
        window.blit(text_surface, (400, 325))
        text_surface = (myfont.render('B', False, (0, 0, 0)))
        window.blit(text_surface, (490, 325))
        text_surface = (myfont.render('C', False, (0, 0, 0)))
        window.blit(text_surface, (580, 325))

        pygame.image.save(window, 'game.jpg')
        pygame.quit()
        script_dir = os.path.dirname(__file__)
        rel_path = "game.jpg"
        relative_path = os.path.join(script_dir, rel_path)
        im = Image.open(relative_path)
        im.show()
        os.remove(relative_path)

    def main(self):
        # invoker.set_command(Trade())
        self.sim.set_invoker(Invoker().set_command(Build()))
        self.sim.set_observer(Observer())
        return
