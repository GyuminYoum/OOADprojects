class Road:
    def __init__(self, coord1, coord2):
        self.color = None
        self.node1 = coord1
        self.node2 = coord2
        self.name = self.node1.label+self.node2.label


