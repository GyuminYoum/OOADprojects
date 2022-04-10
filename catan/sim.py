# each turn
# each player rolls
# town/base check
# adjust player resources
# give player option to build proximity to his town

from Field import *

board = Field((500, 300), 50)
board.build()

pygame.init()
surface = pygame.display.set_mode((1000,1000))

for x in board.hexlist:
    pygame.draw.polygon(surface, x.Resource.color, x.get_coords())
    # print(x.name, x.Resource.type)

points=0
while points < 10:
    pygame.event.get()
    pygame.display.flip()
    # points+=1
