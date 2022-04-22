from Catan import Catan
# from Commands.Trade import *
# from Commands.Invoker import *
# from Commands.Build import *
# from Field import *
# from Observer.Observer import Observer
from sim import *
import pygame
import sys


catan = Catan()
catan.initialize()
catan.main()

# sim1 = sim()
# sim1.initialize()
#
# pygame.init()
# # create the display surface object
# # of specific dimension.
# window = pygame.display.set_mode((1000, 1000))
# # Fill the scree with white color
# window.fill((255, 255, 255))
#
# sim1.surface = window
# for x in sim1.field:
#     # for filled hexagon
#     pygame.draw.polygon(window, x.Resource.color, x.get_coords())
#     # for outline
#     pygame.draw.polygon(window, (0, 0, 0), x.get_coords(), width=3)
#
# pygame.display.flip()
#
# counter = 0
# while 1:
#     for event in pygame.event.get():
#         if event.type == pygame.QUIT:
#             pygame.quit()
#             sys.exit()
#         # elif event.type == pygame.display:
#         #     pygame.display.flip()
#     sim1.playerStart1(counter)
#     pygame.display.flip()
#     pygame.time.wait(500)
#
#     # pygame.event.get()
#     #
# # pygame.quit()
#
# invoker = Invoker()
# # for x in sim1.playerlist:
# #     for y in x.settlement:
# #         print(y.label)
# # invoker = Invoker()
# # build = Build()
# # invoker.set_command(build)
# # # trade = Trade()
# # # invoker.set_command(trade)
# # sim1.set_invoker(invoker)
# # sim1.invoker.execute_command(sim1)
#
# sim1.set_invoker(invoker)
# sim1.invoker.execute_command(sim1)
# """
#
# #player creation test
#
#
#
# #all the possible nodes
# # for x in sim1.possible_settlements:
# #     print(x.label)
#
# #player check
# # for x in sim1.playerlist:
# #     print(x.name, x.color)
#
# #road creation test
# """
# dict2 = {}
# for x in sim1.possible_roads.keys():
#     list1 = []
#     for y in sim1.possible_roads[x]:
#         list1.append(y.label)
#     # print(x.label, list1)
#     dict2[x.label] = list1
# # print(dict2)
# sorted_keys = sorted(dict2.keys())
# # print(sorted_keys)
# for x in sorted_keys:
#     print(x, dict2[x])
# """
# #board creation test
# """
# for x in sim1.field:
#     print(x.name, x.Resource.type, x.value)
# """
# #deck test
# """
# for x in sim1.deck:
#     print(x.name)
# """
# #display
#
# """
# for x in list1:
#     # for filled hexagon
#     pygame.draw.polygon(window, x.Resource.color, x.get_coords())
#     # for outline
#     pygame.draw.polygon(window, (0, 0, 0), x.get_coords(), width=3)
# while 1:
#     pygame.event.get()
#     pygame.display.flip()

# sim1.set_observer(Observer())
# build = Build()
# invoker.set_command(build)

# trade = Trade()
# invoker.set_command(trade)
# sim1.set_invoker(invoker)
# sim1.invoker.execute_command(sim1)

# f1 = Field((500, 300), 50)
# list1 = f1.build()
#
# pygame.init()
# # create the display surface object
# # of specific dimension.
# window = pygame.display.set_mode((1000, 1000))
#
# # Fill the screen with white color
# window.fill((255, 255, 255))
#
#
# pygame.display.update()
#
# for x in list1:
#     # for filled hexagon
#     pygame.draw.polygon(window, x.Resource.color, x.get_coords())
#     # for outline
#     pygame.draw.polygon(window, (0, 0, 0), x.get_coords(), width=3)
# while 1:
#     pygame.event.get()
#     pygame.display.flip()

# for x in list1:
#     print(x.get_nodeNames())
