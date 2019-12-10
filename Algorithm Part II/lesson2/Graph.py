import numpy as np


class Graph:

    def __init__(self, vertices, representation):
        self.V = None
        self.Rep = None
        self.E = 0
        if representation == "Matrix":
            self.Rep = 0
            self.V = vertices
            self.matrix = np.zeros((self.V, self.V))
        elif representation == "List":
            self.V = vertices
            self.Rep = 1
            self.list = []
            for i in range(self.V):
                self.list.append(set())
        else:
            print("Not allowed representation model")

    def add_edge(self, n1, n2):
        if self.Rep == 0:
            self.matrix[n1][n2] = 1
            self.matrix[n2][n1] = 1
            self.E += 1
        elif self.Rep == 1:  # adjacent list
            self.list[n1].add(n2)
            self.list[n2].add(n1)
            self.E += 1

    def print_graph(self):
        if self.Rep == 0:
            for i in range(self.V):
                for j in range(self.V):
                    if self.matrix[i][j] == 1:
                        print(i, '---', j)
        elif self.Rep == 1:
            for i in range(self.V):
                if len(self.list[i]) != 0:
                    for item in self.list[i]:
                        print(i, '---', item)

    def edge(self):
        return self.E

    def vertices(self):
        return self.V

    def degree(self, n1):
        temp = 0
        if self.Rep == 0:  # matrix
            for i in range(self.V):
                temp += self.matrix[n1][i]
            temp = int(temp)
        elif self.Rep == 1:  # list
            temp = len(self.list[n1])
        return temp

    def max_degree(self):
        maxdegree = 0
        for i in range(self.V):
            temp = self.degree(i)
            if temp > maxdegree:
                maxdegree = temp
        return maxdegree


if __name__ == "__main__":
    a = Graph(5, 'Matrix')
    b = Graph(5, "List")

    a.add_edge(0, 1)
    b.add_edge(0, 1)
    a.add_edge(1, 2)
    b.add_edge(1, 2)

    a.print_graph()
    b.print_graph()

    print(a.degree(0))
    print(b.degree(1))
    print(a.edge())
