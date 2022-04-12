import numpy as np
class Hex:
    def __init__(self,name,origin,length):
        self.p1=origin
        self.lateral=length*np.sqrt(3)/2
        self.height=length/2
        self.p2=(origin[0]+self.lateral,origin[1]+self.height)
        self.p3=(self.p2[0],self.p2[1]+length)
        self.p4=(origin[0],origin[1]+2*length)
        self.p5=(self.p3[0]-2*self.lateral,self.p3[1])
        self.p6=(origin[0]-self.lateral,self.p2[1])
        self.Resource=""
        self.name=name
        self.value=0
        self.Robber= False

    def get_coords(self):
        return (self.p1,self.p2,self.p3,self.p4,self.p5,self.p6)
