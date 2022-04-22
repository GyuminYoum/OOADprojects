# import numpy as np


class Hex:
    def __init__(self, name, nodelist):
        self.p1 = nodelist[0]
        # self.lateral=length*np.sqrt(3)/2
        # self.height=length/2
        self.p2 = nodelist[1]
        # (origin[0]+self.lateral,origin[1]+self.height)
        self.p3 = nodelist[2]
        # (self.p2[0],self.p2[1]+length)
        self.p4 = nodelist[3]
        # (origin[0],origin[1]+2*length)
        self.p5 = nodelist[4]
        # (self.p3[0]-2*self.lateral,self.p3[1])
        self.p6 = nodelist[5]
        # (origin[0]-self.lateral,self.p2[1])
        self.Resource = ""
        self.name = name
        self.value = 0
        self.Robber = False

    def get_coords(self):
        return self.p1.coord, self.p2.coord, self.p3.coord, self.p4.coord, self.p5.coord, self.p6.coord

    def get_nodes(self):
        return [self.p1, self.p2, self.p3, self.p4, self.p5, self.p6]

    def get_nodeNames(self):
        list1 = self.get_nodes()
        list2 = []
        for x in list1:
            list2.append(x.label)
        return list2
