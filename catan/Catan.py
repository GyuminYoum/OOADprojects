import sim
from sim import *
from Observer.Observer import Observer
import pygame
import os
from PIL import Image


class Catan:
    def __init__(self):
        self.sim = sim()
        self.board = 0

    # function initialize
    # usage: creates a randomized Catan board with constant desert, robber, and harbor locations
    def initialize(self):
        self.sim.initialize()
        pygame.init()
        # create the display surface object
        # of specific dimension.
        window = pygame.display.set_mode((1000, 1000))
        # Fill the screen with white color
        pygame.display.set_caption('Settlers of Catan')
        window.fill((255, 255, 255))
        self.board = window
        pygame.font.init()
        default_font = pygame.font.get_default_font()
        font_renderer = pygame.font.Font(default_font, 20)

        # drawing harbors and labels
        pygame.draw.polygon(self.board, (0, 0, 0), [(370, 325), (375, 322), (360, 302), (355, 305)], width=1)  # A6
        pygame.draw.polygon(self.board, (0, 0, 0), [(410, 303), (415, 301), (390, 286), (385, 288)], width=1)  # A1
        pygame.draw.polygon(self.board, (255, 255, 0), [(500, 300), (505, 303), (525, 285), (520, 278)])  # B1
        pygame.draw.rect(self.board, (255, 255, 0), pygame.Rect(540, 290, 8, 40))  # B2
        pygame.draw.polygon(self.board, (0, 0, 0), [(500, 300), (505, 303), (525, 285), (520, 278)], width=1)  # B1
        pygame.draw.rect(self.board, (0, 0, 0), pygame.Rect(540, 290, 8, 40), width=1)  # B2
        pygame.draw.polygon(self.board, (192, 192, 192), [(625, 375), (630, 380), (655, 355), (650, 350)])  # C3
        pygame.draw.polygon(self.board, (192, 192, 192), [(665, 395), (670, 400), (680, 370), (675, 365)])  # G2
        pygame.draw.polygon(self.board, (0, 128, 0), [(325, 400), (325, 405), (300, 415), (300, 410)])  # D6
        pygame.draw.polygon(self.board, (0, 128, 0), [(325, 445), (325, 450), (300, 440), (300, 435)])  # D5
        pygame.draw.polygon(self.board, (107, 104, 103), [(325, 550), (325, 555), (300, 565), (300, 560)])  # H4
        pygame.draw.polygon(self.board, (107, 104, 103), [(325, 595), (325, 600), (300, 590), (300, 585)])  # M5
        pygame.draw.polygon(self.board, (0, 0, 0), [(715, 480), (715, 475), (740, 490), (735, 495)], width=1)  # L2
        pygame.draw.polygon(self.board, (0, 0, 0), [(715, 520), (715, 525), (740, 515), (735, 510)], width=1)  # L3
        pygame.draw.polygon(self.board, (144, 238, 144), [(675, 600), (670, 603), (675, 630), (680, 625)])  # P3
        pygame.draw.polygon(self.board, (144, 238, 144), [(630, 625), (635, 620), (655, 640), (650, 645)])  # P4
        pygame.draw.polygon(self.board, (0, 0, 0), [(675, 600), (670, 603), (675, 630), (680, 625)], width=1)  # P3
        pygame.draw.polygon(self.board, (0, 0, 0), [(630, 625), (635, 620), (655, 640), (650, 645)], width=1)  # P4
        pygame.draw.polygon(self.board, (0, 0, 0), [(545, 675), (540, 678), (545, 705), (550, 700)], width=1)  # R3
        pygame.draw.polygon(self.board, (0, 0, 0), [(500, 700), (505, 695), (525, 715), (520, 720)], width=1)  # R4
        pygame.draw.polygon(self.board, (0, 0, 0), [(375, 675), (370, 678), (370, 705), (375, 700)], width=1)  # Q5
        pygame.draw.polygon(self.board, (0, 0, 0), [(405, 695), (410, 700), (400, 720), (395, 715)], width=1)  # Q4
        label = font_renderer.render('3:1', 1, (0, 0, 0))
        self.board.blit(label, (350, 270))
        self.board.blit(label, (745, 495))
        self.board.blit(label, (365, 720))
        self.board.blit(label, (530, 720))
        label = font_renderer.render('2:1', 1, (0, 0, 0))
        self.board.blit(label, (525, 270))
        self.board.blit(label, (660, 340))
        self.board.blit(label, (660, 640))
        self.board.blit(label, (265, 415))
        self.board.blit(label, (265, 565))

        # drawing hexagons and nodes on board
        for x in self.sim.field:
            # for filled hexagon
            pygame.draw.polygon(self.board, x.Resource.color, x.get_coords())
            # for outline
            pygame.draw.polygon(self.board, (0, 0, 0), x.get_coords(), width=3)
            pygame.display.flip()
            label = font_renderer.render(str(x.value), 1, (0, 0, 0))
            label1 = font_renderer.render(str(x.name), 1, (0, 0, 0))
            self.board.blit(label1, (x.p1.coord[0] - 5, ((x.p1.coord[1] + x.p4.coord[1]) / 2)-20))
            self.board.blit(label, (x.p1.coord[0]-5, ((x.p1.coord[1]+x.p4.coord[1])/2)))
        font_renderer = pygame.font.Font(default_font, 17)
        for node in self.sim.possible_settlements:
            label = font_renderer.render(str(node.label), 1, (0, 0, 0))
            self.board.blit(label, (node.coord[0]-10, node.coord[1]-20))

        # saves the randomized board as a jpg image, and then opens the image on the user's computer
        # the image is deleted after opening, so a user can view the board without the image taking up storage
        # as long as the image pop-up is not closed
        pygame.image.save(window, 'game.jpg')
        pygame.quit()
        script_dir = os.path.dirname(__file__)
        rel_path = "game.jpg"
        relative_path = os.path.join(script_dir, rel_path)
        im = Image.open(relative_path)
        im.show()
        os.remove(relative_path)

        self.sim.set_observer(Observer())

    # function main
    # usage: runs the game of Catan
    def main(self):

        # allows player to set up their initial roads and settlements
        for player in self.sim.playerlist:
            print("Current player: " + player.name)
            self.sim.playerStartSettlement(player)
            self.sim.playerStartRoad(player)

        reverselist = list(reversed(self.sim.playerlist))
        for player in reverselist:
            self.sim.playerStartSettlement(player)
            self.sim.playerStartRoad(player)

        while not self.sim.done:
            self.sim.playerAction()
