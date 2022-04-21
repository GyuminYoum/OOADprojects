from sim import *
from Commands.Build import *
from Commands.Invoker import *
from Commands.Trade import *
from Field import *
from Observer.Observer import Observer

import pygame
import sys


class Catan:

    def __init__(self):
        self.sim = sim()

    def initialize(self):
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

        self.sim.initialize()
        invoker = Invoker()
        invoker.set_command(Build())
        # invoker.set_command(Trade())
        self.sim.set_invoker(invoker)
        self.sim.set_observer(Observer())

        pygame.display.flip()

    def main(self):

        while 1:
            # TODO: vars or loops to go through players in order 12344321 123321
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    sys.exit()
                # elif event.type == pygame.display:
                #     pygame.display.flip()
            self.sim1.playerStart1()
            pygame.display.flip()
            pygame.time.wait(500)

        while 1:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    pygame.quit()
                    sys.exit()
                # elif event.type == pygame.display:
                #     pygame.display.flip()
            # self.sim1.playerAction
            # check for victory points here probably
            # we can do resource build trade for one player before updating display tbh
            pygame.display.flip()
            pygame.time.wait(500)
