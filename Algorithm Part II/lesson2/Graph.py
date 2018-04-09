import numpy as np
class Graph:

    def GraphEdgeset(self, vertices, representation):
        self.V = None
        self.Rep = None
        if representation == "Matrix":
            self.Rep = 0
            self.V = vertices
            self.matrix=np.zeros((self.V,self.V))
        elif representation == "list":
            self.V = vertices
            self.Rep = 1
            self.list=[[]]*self.V
        else:
            print("Not allowed representation model")

    def addedge(self, n1, n2):
        if self.Rep == 0:
        elif self.Rep==1:
            self.

