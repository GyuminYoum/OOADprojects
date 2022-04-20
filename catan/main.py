from sim import *
from Commands.Trade import *
from Commands.Invoker import *
from Observer.Observer import Observer
from Field import *
import pygame


sim1 = sim()
sim1.initialize()

invoker = Invoker()
sim1.set_observer(Observer())
# build = Build()
# invoker.set_command(build)
trade = Trade()
invoker.set_command(trade)
sim1.set_invoker(invoker)
sim1.invoker.execute_command(sim1)

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
