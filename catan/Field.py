# import pygame
import numpy as np
from Hex import *
import random
# import copy

from Resource import *
from Node import *


class Field:
    def __init__(self, coord, sidelength):
        self.start = coord
        # (500,450) center
        self.length = sidelength
        self.hexlist = []

    def build(self):
        lateral = self.length*np.sqrt(3)/2
        height = self.length/2
        node_list = []
        Hexlist = []
        n1 = Node("A1", (self.start[0]-2*lateral, self.start[1]))
        n2 = Node("A2", (n1.coord[0]+lateral, n1.coord[1]+height))
        n3 = Node("A3", (n2.coord[0], n2.coord[1]+self.length))
        n4 = Node("A4", (n1.coord[0], n1.coord[1]+2*self.length))
        n5 = Node("A5", (n3.coord[0]-2*lateral, n3.coord[1]))
        n6 = Node("A6", (n1.coord[0]-lateral, n2.coord[1]))

        n7 = Node("B1", self.start)
        n8 = Node("B2", (n7.coord[0]+lateral, n7.coord[1]+height))
        n9 = Node("B3", (n8.coord[0], n8.coord[1] + self.length))
        n10 = Node("B4", (n7.coord[0], n7.coord[1] + 2 * self.length))
        # B5=A3 n3
        # B6=A2 n2

        n11 = Node("C1", (self.start[0]+2*lateral, self.start[1]))
        n12 = Node("C2", (n11.coord[0] + lateral, n11.coord[1] + height))
        n13 = Node("C3", (n12.coord[0], n12.coord[1] + self.length))
        n14 = Node("C4", (n11.coord[0], n11.coord[1] + 2 * self.length))
        # C5=B3 n9
        # C6=B2 n8

        # D1=A5 n5
        # D2=A4 n4
        n15 = Node("D3", (n4.coord[0], n4.coord[1]+self.length))
        n16 = Node("D4", (n5.coord[0], n5.coord[1]+2*self.length))
        n17 = Node("D5", (n15.coord[0]-2*lateral, n15.coord[1]))
        n18 = Node("D6", (n5.coord[0]-lateral, n4.coord[1]))

        # E1=A3 n3
        # E2=B4 n10
        # E5=D3 n15
        # E6=A4 n4
        n19 = Node("E3", (n10.coord[0], n10.coord[1]+self.length))
        n20 = Node("E4", (n3.coord[0], n3.coord[1]+2*self.length))

        # F1=B3 n9
        # F2=C4 n14
        # F5=E3 n19
        # F6=B4 n10
        n21 = Node("F3", (n14.coord[0], n14.coord[1]+self.length))
        n22 = Node("F4", (n9.coord[0], n9.coord[1]+2*self.length))

        # G1=C3 n13
        # G5=F3 n21
        # G6=C4 n14
        n23 = Node("G2", (n13.coord[0] + lateral, n13.coord[1] + height))
        n24 = Node("G3", (n23.coord[0], n23.coord[1]+ self.length))
        n25 = Node("G4", (n13.coord[0], n13.coord[1]+2*self.length))

        # H1=D5 n17
        # H2=D4 n16
        n26 = Node("H3", (n16.coord[0], n16.coord[1]+self.length))
        n27 = Node("H4", (n17.coord[0], n17.coord[1]+2*self.length))
        n28 = Node("H5", (n26.coord[0] - 2 * lateral, n26.coord[1]))
        n29 = Node("H6", (n17.coord[0] - lateral, n16.coord[1]))

        # I1=D3 n15
        # I2=E4 n20
        # I5=H3 n26
        # I6=D4 n16
        n30 = Node("I3", (n20.coord[0], n20.coord[1]+self.length))
        n31 = Node("I4", (n15.coord[0], n15.coord[1]+2*self.length))

        # J1=E3 n19
        # J2=F4 n22
        # J5=I3 n30
        # J6 E4 n20
        n32 = Node("J3", (n22.coord[0], n22.coord[1] + self.length))
        n33 = Node("J4", (n19.coord[0], n19.coord[1] + 2 * self.length))

        # K1=F3 n21
        # K2=G4 n25
        # k5=J3 n32
        # K6=F4 n22
        n34 = Node("K3", (n25.coord[0], n25.coord[1] + self.length))
        n35 = Node("K4", (n21.coord[0], n21.coord[1] + 2 * self.length))

        # L1=G3 n24
        # L5=K3 n34
        # L6=G4 n25
        n36 = Node("L2", (n24.coord[0] + lateral, n24.coord[1] + height))
        n37 = Node("L3", (n36.coord[0], n36.coord[1] + self.length))
        n38 = Node("L4", (n24.coord[0], n24.coord[1] + 2 * self.length))

        # M1=H3 n26
        # M2=I4 n31
        # M6=H4 n27
        n39 = Node("M3", (n31.coord[0], n31.coord[1] + self.length))
        n40 = Node("M4", (n26.coord[0], n26.coord[1] + 2 * self.length))
        n41 = Node("M5", (n39.coord[0] - 2 * lateral, n39.coord[1]))

        # N1=I3 n30
        # N2=J4 n33
        # N5=M3 n39
        # N6=I4 n31
        n42 = Node("N3", (n33.coord[0], n33.coord[1] + self.length))
        n43 = Node("N4", (n30.coord[0], n30.coord[1] + 2 * self.length))

        # O1=J3 n32
        # O2=K4 n35
        # O5=N3 n42
        # O6=J4 n33
        n44 = Node("O3", (n35.coord[0], n35.coord[1] + self.length))
        n45 = Node("O4", (n32.coord[0], n32.coord[1] + 2 * self.length))

        # P1=K3 n34
        # P2=L4 n38
        # P5=O3 n44
        # P6=K4 n35
        n46 = Node("P3", (n38.coord[0], n38.coord[1] + self.length))
        n47 = Node("P4", (n34.coord[0], n34.coord[1] + 2 * self.length))

        # Q1=M3 n39
        # Q2=N4 n43
        # Q6=M4 n40
        n48 = Node("Q3", (n43.coord[0], n43.coord[1] + self.length))
        n49 = Node("Q4", (n39.coord[0], n39.coord[1] + 2 * self.length))
        n50 = Node("Q5", (n48.coord[0] - 2 * lateral, n48.coord[1]))

        # R1=N3 n42
        # R2=O4 n45
        # R5=Q3 n48
        # R6=N4 n43
        n51 = Node("R3", (n45.coord[0], n45.coord[1] + self.length))
        n52 = Node("R4", (n42.coord[0], n42.coord[1] + 2 * self.length))

        # S1=O3 n44
        # S2=P4 n47
        # S5=R3 n51
        # S6=O4 n45
        n53 = Node("S3", (n47.coord[0], n47.coord[1] + self.length))
        n54 = Node("S4", (n44.coord[0], n44.coord[1] + 2 * self.length))

        n1.adj = [n2, n6]
        n2.adj = [n1, n3, n7]
        n3.adj = [n2, n4, n10]
        n4.adj = [n3, n5, n15]
        n5.adj = [n4, n6, n18]
        n6.adj = [n1, n5]
        n7.adj = [n2, n8]
        n8.adj = [n7, n9, n11]
        n9.adj = [n8, n10, n14]
        n10.adj = [n3, n9, n19]
        n11.adj = [n8, n12]
        n12.adj = [n11, n13]
        n13.adj = [n12, n14, n23]
        n14.adj = [n9, n13, n21]
        n15.adj = [n4, n16, n20]
        n16.adj = [n15, n17, n26]
        n17.adj = [n16, n29, n18]
        n18.adj = [n5, n17]
        n19.adj = [n10, n20, n22]
        n20.adj = [n15, n19, n20]
        n21.adj = [n14, n22, n25]
        n22.adj = [n19, n21,n32]
        n23.adj = [n13, n24]
        n24.adj = [n23, n25, n36]
        n25.adj = [n21, n24, n34]
        n26.adj = [n16, n27, n31]
        n27.adj = [n26, n28, n41]
        n28.adj = [n27, n29]
        n29.adj = [n17, n28]
        n30.adj = [n20, n31, n33]
        n31.adj = [n26, n30, n39]
        n32.adj = [n22, n33, n35]
        n33.adj = [n30, n32, n42]
        n34.adj = [n25, n35, n38]
        n35.adj = [n32, n34, n44]
        n36.adj = [n24, n37]
        n37.adj = [n36, n38]
        n38.adj = [n34, n37, n46]
        n39.adj = [n31, n40, n43]
        n40.adj = [n39, n41, n50]
        n41.adj = [n27, n40]
        n42.adj = [n33, n43, n45]
        n43.adj = [n39, n42, n48]
        n44.adj = [n35, n45, n47]
        n45.adj = [n42, n44, n51]
        n46.adj = [n38, n47]
        n47.adj = [n44, n46, n53]
        n48.adj = [n43, n49, n52]
        n49.adj = [n48, n50]
        n50.adj = [n40, n49]
        n51.adj = [n45, n52, n54]
        n52.adj = [n48, n51]
        n53.adj = [n47, n54]
        n54.adj = [n51, n53]

        h1 = [n1, n2, n3, n4, n5, n6]
        Hex1 = Hex("A", h1)
        h2 = [n7, n8, n9, n10, n3, n2]
        Hex2 = Hex("B", h2)
        h3 = [n11, n12, n13, n14, n9, n8]
        Hex3 = Hex("C", h3)
        h4 = [n5, n4, n15, n16, n17, n18]
        Hex4 = Hex("D", h4)
        h5 = [n3, n10, n19, n20, n15, n4]
        Hex5 = Hex("E", h5)
        h6 = [n9, n14, n21, n22, n19, n10]
        Hex6 = Hex("F", h6)
        h7 = [n13, n23, n24, n25, n21, n14]
        Hex7 = Hex("G", h7)
        h8 = [n17, n16, n26, n27, n28, n29]
        Hex8 = Hex("H", h8)
        h9 = [n15, n20, n30, n31, n26, n16]
        Hex9 = Hex("I", h9)
        h10 = [n19, n22, n32, n33, n30, n20]
        Hex10 = Hex("J", h10)
        h11 = [n21, n25, n34, n35, n32, n22]
        Hex11 = Hex("K", h11)
        h12 = [n24, n36, n37, n38, n34, n25]
        Hex12 = Hex("L", h12)
        h13 = [n26, n31, n39, n40, n41, n27]
        Hex13 = Hex("M", h13)
        h14 = [n30, n33, n42, n43, n39, n31]
        Hex14 = Hex("N", h14)
        h15 = [n32, n35, n44, n45, n42, n33]
        Hex15 = Hex("O", h15)
        h16 = [n34, n38, n46, n47, n44, n35]
        Hex16 = Hex("P", h16)
        h17 = [n39, n43, n48, n49, n50, n40]
        Hex17 = Hex("Q", h17)
        h18 = [n42, n45, n51, n52, n48, n43]
        Hex18 = Hex("R", h18)
        h19 = [n44, n47, n53, n54, n51, n45]
        Hex19 = Hex("S", h19)

        hex_list = [Hex1, Hex2, Hex3, Hex4, Hex5, Hex6, Hex7, Hex8, Hex9,
                    Hex11, Hex12, Hex13, Hex14, Hex15, Hex16, Hex17, Hex18, Hex19]

        node_list= [n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12, n13, n14, n15, n16, n17, n18, n19, n20, n21, n22, n23, n24, n25, n26, n27, n28, n29, n30, n31, n32, n33, n34, n35, n36, n37, n38, n39, n40, n41, n42, n43, n44, n45, n46, n47, n48, n49, n50, n51, n52, n53, n54]

        # define resources
        sheep = Resource("Sheep", (144, 238, 144))
        forest = Resource("Forest", (0, 128, 0))
        desert = Resource("Desert", (237, 201, 175))
        ore = Resource("Ore", (192, 192, 192))
        clay = Resource("Clay", (107, 104, 103))
        wheat = Resource("Wheat", (255, 255, 0))
        # list of all possible colors and rolls to be distributed
        colors = [sheep, sheep, sheep, sheep, forest, forest, forest, forest, ore, ore, ore, clay, clay, clay, wheat,
                  wheat, wheat, wheat]
        roll_list = [2, 3, 3, 4, 4, 5, 5, 6, 6, 8, 8, 9, 9, 10, 10, 11, 11, 12]

        for shape in hex_list:
            index = random.randint(0, len(colors) - 1)
            temp_color = colors[index]
            shape.Resource = temp_color
            # pygame.draw.polygon(surface, temp_color, shape.get_coords())
            colors.remove(temp_color)
            index = random.randint(0, len(roll_list)-1)
            number = roll_list[index]
            shape.value = number
            roll_list.remove(number)

        # set desert
        Hex10.Resource = desert
        Hex10.value = "R"
        hex_list.append(Hex10)

        for shape in hex_list:
            nodelist = shape.get_nodes()
            for x in nodelist:
                x.hex.append(shape)

        return hex_list, node_list

    def generate_roads(self, hex_list1):
        road_dict = {}
        for hex in hex_list1:
            # print(hex.name)
            nodelist = hex.get_nodes()
            for node in nodelist:
                for adjacent_node in node.adj:
                    if node in road_dict.keys():
                        if adjacent_node not in road_dict[node]:
                            road_dict[node].append(adjacent_node)
                    else:
                        road_dict[node] = [adjacent_node]

                    if adjacent_node in road_dict.keys():
                        if node not in road_dict[adjacent_node]:
                            road_dict[adjacent_node].append(node)
                    else:
                        road_dict[adjacent_node]=[node]
        return road_dict

