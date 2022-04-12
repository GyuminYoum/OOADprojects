from Player import *
from Field import *
from cardFactory import *

import numpy as np

"""
board=Field((500,300),50)

board.build()

pygame.init()
surface = pygame.display.set_mode((1000, 1000))

for x in board.hexlist:
    pygame.draw.polygon(surface, x.Resource.color, x.get_coords())
    # print(x.name, x.Resource.type)


points = 0
while points < 10:
    pygame.event.get()
    pygame.display.flip()
    # points+=1
points=0
while(points<10):
    
    pygame.event.get()
    pygame.display.flip()
    #points+=1

"""


class sim:
    def __init__(self):
        self.playerlist = []
        self.playercount = 0
        self.current_player = None
        self.colorlist = ["R", "B", "W", "BK"]
        cardFactory1 = cardFactory()
        self.deck = cardFactory1.makeDeck()
        board = Field((500,300),50)
        board.build()
        self.field = board.hexlist
        self.invoker = None
        self.observer = None
        # self.trade = Trade()
        # self.build = Build()

    def initializePlayers(self):
        while self.playercount < 2 or self.playercount > 4:
            try:
                num = int(input("Input the number of players (2-4) \n"))
                self.playercount = num
            except ValueError:
                print("Please provide integer value only")
        for x in range(self.playercount):
            val = input("Provide name for Player"+str(x+1)+": \n")
            temp = np.random.randint(0, len(self.colorlist)-1)
            color = self.colorlist[temp]
            self.colorlist.pop(temp)

            p1 = Player(val, color)
            p1.resources['sheep'] = 0
            p1.resources['wood'] = 0
            p1.resources['ore'] = 0
            p1.resources['clay'] = 0
            p1.resources['wheat'] = 0
            self.playerlist.append(p1)

            # changing resources of players to test Build and Trade
            # TODO: remove code below when done debugging
            if x == 0:
                self.current_player = p1
                p1.resources['sheep'] = 3
                p1.resources['wood'] = 2
                p1.resources['ore'] = 4
            if x == 1:
                p1.resources['sheep'] = 2
                p1.resources['wood'] = 5

        for x in self.playerlist:
            print(x.name, x.color)

        for y in self.field:
            print(y.name, y.value, y.Resource.type)

        # print(self.field)

    def playerAction(self):
        for x in self.playerlist:
            val = x.roll()
            # loop through all players
            # loop through each players settlement/city
            # loop through each settlement/city's adjacentlist
            # if the hex in adjacentlist contains value equal to val
            # distribute resources to the player

            # x.Trade()
            # x.Build()

            # trade = Trade()
            # self.invoker.set_command(trade)
            # self.invoker.execute_command(self) OR execute()

            # build = Build()
            # self.invoker.set_command(build)
            # self.invoker.execute_command(self) OR execute()

    def initialize(self):
        self.initializePlayers()
        # print(self.playercount)

    def set_invoker(self, invoker):
        self.invoker = invoker

    def execute(self):
        self.invoker.execute_command(self)

    def set_observer(self, observer):
        self.observer = observer

    def update(self, message):
        self.observer.update(message)
