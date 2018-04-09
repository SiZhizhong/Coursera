from UnionFind import QuickUnionImproved  ##add an dot in front of file name
import numpy as np
import random


class Percolation:

    def __init__(self, n):
        self.n = n
        self.opennum = 0

        self.sitemap = np.zeros((n, n))
        self.qu = QuickUnionImproved(n * n + 2)  # add 2 site:0 and 1 ,all top row connect with 0 site.
        for i in range(n):
            self.qu.union(0, self.id(0, i))
            self.qu.union(1, self.id(n - 1, i))

    def id(self, row, col):  # row and col:from 0 to n-1
        return row * self.n + col + 2

    def open(self, row, col):
        if row < self.n and col < self.n and row >= 0 and col >= 0:
            if self.sitemap[row][col] == 0:
                self.sitemap[row][col] = 1
                self.opennum += 1
                if row > 0:
                    if self.sitemap[row - 1][col] == 1:
                        self.qu.union(self.id(row, col), self.id(row - 1, col))
                if row + 1 < self.n:
                    if self.sitemap[row + 1][col] == 1:
                        self.qu.union(self.id(row + 1, col), self.id(row, col))
                if col > 0:
                    if self.sitemap[row][col - 1] == 1:
                        self.qu.union(self.id(row, col), self.id(row, col - 1))
                if col + 1 < self.n:
                    if self.sitemap[row][col + 1] == 1:
                        self.qu.union(self.id(row, col + 1), self.id(row, col))

    def isOpen(self, row, col):
        if row >= 0 and row < self.n and col >= 0 and col < self.n:
            return self.sitemap[row][col] == 1

    def isFull(self, row, col):
        if row >= 0 and row < self.n and col >= 0 and col < self.n:
            return self.qu.connected(0, self.id(row, col))

    def numberOfOpenSites(self):
        return self.opennum

    def percolates(self):
        return self.qu.connected(0, 1)

    def printmap(self):
        for i in range(self.n):
            for j in range(self.n):
                print("%d" % (self.sitemap[i][j]), end=" ")
            print("")


class PercolationStats:

    def __init__(self, n, trials):
        self.n = n
        self.percolation_prob=[]
        self.mean_prob=None


        for i in range(trials):
            perco = Percolation(n)  # type:Percolation
            while not perco.percolates():
                row = random.randint(0, n - 1)
                col = random.randint(0, n - 1)
                perco.open(row, col)
            self.percolation_prob.append(perco.opennum / (n * n))

    def mean(self):
        sum = 0
        for each in self.percolation_prob:
            sum += each
        self.mean_prob = sum / len(self.percolation_prob)
        return self.mean_prob

    def stddev(self):
        sum = 0
        for each in self.percolation_prob:
            sum += (each - self.mean_prob) ** 2
        self.stddev_num = (sum / (len(self.percolation_prob) - 1)) ** (1 / 2)
        return self.stddev_num

    def confidenceLo(self):
        return self.mean_prob - 1.96 * self.stddev_num / (len(self.percolation_prob) ** 0.5)

    def confidenceHo(self):
        return self.mean_prob + 1.96 * self.stddev_num / (len(self.percolation_prob) ** 0.5)


if __name__ == "__main__":
    per = PercolationStats(100, 100)
    print(per.mean())
    print(per.stddev())
