# double ended queue. use list as in c.
class Deque:
    class Node:
        def __init__(self, value, next=None, prev=None):
            self.value = value
            self.next = next
            self.prev = prev

    def __init__(self):
        self.size = 0
        self.first = None  # type:Deque.Node
        self.last = None  # type:Deque.Node

    def isEmpyt(self):
        return self.size == 0

    def __sizeof__(self):
        return self.size

    def addFirst(self, item):
        if self.size == 0:
            temp = self.Node(item)
            self.first = temp
            self.last = temp
        else:
            temp = self.Node(item, next=self.first)
            self.first = temp
            self.first.prev=temp
            self.first=temp
        self.size += 1

    def addLast(self, item):
        if self.size == 0:
            temp = self.Node(item)
            self.first = temp
            self.last = temp
        else:
            temp = self.Node(item, prev=self.last)
            self.last.next = temp
            self.last=temp
        self.size += 1

    def removeFirst(self):
        if self.size == 0:
            print("the queue is empty")
        else:
            self.first = self.first.next
            self.size -= 1

    def removeLast(self):
        if self.size == 0:
            print("the queue is empty")
        else:
            self.last = self.last.prev
            self.size -= 1

    def __iter__(self):
        return self

    def __next__(self):
        if self.size == 0:
            raise StopIteration
        else:
            value = self.first.value
            self.first = self.first.next
            self.size -= 1
            return value


if __name__ == "__main__":
    myDeque = Deque()
    for i in range(10):
        myDeque.addFirst(i)
    for i in range(10, 20):
        myDeque.addLast(i)

    for each in myDeque:
        print(each)
