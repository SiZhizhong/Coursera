import time
import random
import copy


class Sort:
    def __init__(self, list):
        self.time = None
        self.list = copy.deepcopy(list)#type:list
        self.size = len(self.list)
        self.aux=copy.deepcopy(list)
        self.list2=[None]*self.size

    def reset(self):
        for i in range(self.size):
            self.list[i]=self.aux[i]


    def selection_sort(self):
        start_time = time.time()  # seconds
        for i in range(self.size - 1):
            min_index = i
            for j in range(i + 1, self.size):
                if self.list[j] < self.list[min_index]:
                    min_index = j
            if min_index != i:
                temp = self.list[i]
                self.list[i] = self.list[min_index]
                self.list[min_index] = temp
        end_time = time.time()
        self.time = end_time - start_time

    def insertion_sort(self):
        start_time=time.time()
        for i in range(0,self.size):
            for j in range(i,0,-1):
                if self.list[j]<self.list[j-1]:
                    temp=self.list[j]
                    self.list[j]=self.list[j-1]
                    self.list[j-1]=temp
        end_time=time.time()
        self.time=end_time-start_time

    def shell_sort(self):
        start_time=time.time()
        h=1
        while(h<self.size/3):
            h=3*h+1
        while(h>=1):
            for i in range(h,self.size,h):
                for j in range(i,h-1,-h):
                    if self.list[j]<self.list[j-h]:
                        temp=self.list[j]
                        self.list[j]=self.list[j-h]
                        self.list[j-h]=temp
            h=int(h/3)

        end_time=time.time()
        self.time=end_time-start_time

    def merge(self,start,mid,end):
        for i in range(start,end+1):
            self.list2[i]=self.list[i]
        i=start
        j=mid+1
        for k in range(start,end+1):
            if i>mid:
                self.list[k]=self.list2[j]
                j+=1
            elif j>end:
                self.list[k]=self.list2[i]
                i+=1
            elif self.list2[i]<self.list2[j]:
                self.list[k]=self.list2[i]
                i+=1
            else:
                self.list[k]=self.list2[j]
                j+=1

    def merge_sort_inner(self,start,end):
        if end<=start:
            return
        else:
            mid=int((start+end)/2)
            self.merge_sort_inner(start,mid)
            self.merge_sort_inner(mid+1,end)
            self.merge(start,mid,end)

    def merge_sort(self):
        start_time=time.time()
        self.merge_sort_inner(0,self.size-1)
        end_time=time.time()
        self.time=end_time-start_time







if __name__=="__main__":
    """
    N=2000
    mylist=[]
    for i in range(N):
        mylist.append(random.randint(1,N))
    mysort1=Sort(mylist)
    mysort2 = Sort(mylist)
    mysort3 = Sort(mylist)
    mysort1.selection_sort()
    mysort2.insertion_sort()
    mysort3.shell_sort()
    print(mysort1.time)
    print(mysort2.time)
    print(mysort3.time)
    """
    size=[]
    for i in range(100):
        size.append(random.randint(1000,100000))
    times=[]
    for each in size:
        mylist=[]
        for i in range(each):
            mylist.append(random.randint(0,each))
        mysort=Sort(mylist)
        mysort.merge_sort()
        times.append(mysort.time)
    print(size)
    print(times)

