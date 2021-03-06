import edu.princeton.cs.algs4.StdOut;

public class Heap<Key extends Comparable<Key>> {
	int capacity=10;
	//index 0 is empty
	Key[] list;
	int N=0;//total num of item and the last index of element
	
	public Heap(){
		list= (Key[])new Comparable[capacity];
		
	}
	
	public void sink(int i) {
		//at least have one child
		while(2*i<=N) {
			int child=2*i;
			if(child<N&&list[child].compareTo(list[child+1])<0) child++;//have another bigger child
			if(list[i].compareTo(list[child])<0) { swap(i,child);i=child;}
			else break;
		}
		
	}
	
	public void swimup(int i) {
		while(i>1&&list[i].compareTo(list[i/2])>0) {
			swap(i,i/2);
			i=i/2;
		}
	}
	
	public void insert(Key x) {
		list[++N]=x;
		swimup(N);
		if(N>=capacity-1) {
			capacity=capacity*2;
			Key[] temp=(Key[])new Comparable[capacity];
			for(int i=1;i<=N;i++) {
				temp[i]=list[i];
			}
			list=temp;
			temp=null;
		}
	}
	public Key del() {
		swap(1,N);
		Key temp=list[N--];
		sink(1);
		return temp;
	}
	private void swap(int m,int n) {
		Key temp;
		temp=list[m];
		list[m]=list[n];
		list[n]=temp;
		temp=null;
	}
	
	public static void main(String[]args) {
		String[] s= {"a","b","c","d","e","f","g"};
		Heap<String> myheap=new Heap<String>();
		for(int i=0;i<s.length;i++) {
			myheap.insert(s[i]);
		}
		
		for(int i=0;i<s.length;i++) {
			StdOut.println(myheap.del());
		}
		StdOut.println((new Double(1.000).equals(new Double(1.0+1e-20))));
	}
}
