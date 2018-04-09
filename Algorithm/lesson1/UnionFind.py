# Lecture 1: Union Find
import time
import random

# Method 1: quick-find
'''
In this method, we have an list of length N id[], each element represent a Node,
p and q are connected iff they have same id
'''


class QuickFind:

    def __init__(self, N):
        self.N = N
        self.id=[]
        for i in range(N):
            self.id.append(i)

    # check whether p and q are connected
    def connected(self, p, q):
        if p < self.N and q < self.N:
            return self.id[p] == self.id[q]

    # connect p and q,change all id[] same with id[p] to id[q]
    def union(self, p, q):
        if p < self.N and q < self.N:
            idp = self.id[p]
            idq = self.id[q]
            for i in range(self.N):
                if self.id[i] == idp:
                    self.id[i] = idq


'''
id[p] is the parent of p
'''


class QuickUnion:

    def __init__(self, N):
        self.N = N
        self.id=[]
        for i in range(N):
            self.id.append(i)

    def root(self, p):
        if p < self.N:
            while (self.id[p] != p):
                p = self.id[p]
            return p

    # connet p's root to q's root
    def union(self, p, q):
        if p < self.N and q < self.N:
            proot = self.root(p)
            qroot = self.root(q)
            self.id[proot] = qroot

    # check whether p and q have the same root
    def connected(self, p, q):
        if p < self.N and q < self.N:
            return self.root(p) == self.root(q)


'''
base ond quick union
balance tree: add a array sz[i] is the size of sub-tree with parent i
path compression: compression the path 
'''


class QuickUnionImproved:

    def __init__(self, N):
        self.N = N
        self.id=[]
        self.sz=[]
        for i in range(N):
            self.id.append(i)
        for i in range(N):
            self.sz.append(1)

    # add path compression
    def root(self, p):
        if p < self.N:
            while (self.id[p] != p):
                self.id[p] = self.id[self.id[p]]
                p = self.id[p]
            return p

    # connet p's root to q's root, balance tree
    def union(self, p, q):
        if p < self.N and q < self.N:
            proot = self.root(p)
            qroot = self.root(q)
            if proot == qroot:
                return
            if (self.sz[proot] <= self.sz[qroot]):
                self.id[proot] = qroot
                self.sz[qroot] += self.sz[proot]

            else:
                self.id[qroot] = proot
                self.sz[proot] += self.sz[qroot]

    # check whether p and q have the same root
    def connected(self, p, q):
        if p < self.N and q < self.N:
            return self.root(p) == self.root(q)


# test mode
if __name__ == "__main__":
    N = 10000
    Link = N
    union = []
    check = []
    for i in range(Link):
        p = random.randint(0, N - 1)
        q = random.randint(0, N - 1)
        union.append((p, q))
    for i in range(1000):
        p = random.randint(0, N - 1)
        q = random.randint(0, N - 1)
        check.append((p, q))

    t1 =time.time()
    qf=QuickFind(N)
    for i in range(Link):
        qf.union(union[i][0],union[i][1])
    print("result of QuickFind is: ")
    for i in range(1000):
        qf.connected(check[i][0],check[i][1])
    t2=time.time()
    print("time is ",t2-t1,"s")

    t1 = time.time()
    qu = QuickUnion(N)
    for i in range(Link):
        qu.union(union[i][0], union[i][1])
    print("result of QuickUnion is: ")
    for i in range(1000):
        qu.connected(check[i][0], check[i][1])
    t2 = time.time()
    print("time is ", t2 - t1, "s")

    t1 = time.time()
    qui = QuickUnionImproved(N)
    for i in range(Link):
        qui.union(union[i][0], union[i][1])
    print("result of QuickFind is: ")
    for i in range(1000):
        qui.connected(check[i][0], check[i][1])
    t2 = time.time()
    print("time is ", t2 - t1, "s")
