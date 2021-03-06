import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
public class Sorter {
	
	private int [] list;
	
	public Sorter(int [] list) {
		if(list==null) throw new IllegalArgumentException();
		this.list=new int[list.length];
		this.list[0]=list[0];
		
		for(int i=1,index=0,temp=0;i<list.length;i++) {
			index=StdRandom.uniform(0,i+1);
			this.list[i]=list[i];
			temp=this.list[index];
			this.list[index]=this.list[i];
			this.list[i]=temp;	
		}
		
	}
	
	public int [] quicksort() {
		this.quicksort(0,this.list.length-1);
		return this.list;
		
	}
	private void quicksort(int low,int end) {
		if(low>=end) return;
		else {
			int mid=this.partion(low, end);
			this.quicksort(low, mid-1);
			this.quicksort(mid+1,end);	
		}
	}
	private int partion(int low,int high) {
		int i=low+1,j=high,temp;
		int pivot=this.list[low];
		while(true) {
			while((i<=high)&&this.list[i]<=pivot) i++;
			while((j>=low)&&this.list[j]>pivot) j--;
			if(i>j) break;
			temp=this.list[i];
			this.list[i]=this.list[j];
			this.list[j]=temp;
		}
		this.list[low]=this.list[j];
		this.list[j]=pivot;
		return j;
	}
	//return the kth smallest element ,k=0,return min,k=N-1,return max
		public int selection(int k) {
			int lo=0,lh=this.list.length-1,index;
			index=partion(lo,lh);
			while(index!=k) {
				//StdOut.println(index);
				if(index<k) {
					lo=index+1;
					index=partion(lo,lh);
				}
				else {
					lh=index-1;
					index=partion(lo,lh);
				}
			}
			return this.list[index];
		}
		
	public static void main(String[]args) {
		int N=20;
		int [] list=new int[N];
		for(int i=0;i<N;i++) {
			list[i]=StdRandom.uniform(2*N);
		}
		Sorter s=new Sorter(list);
		int kth=s.selection(10);
		StdOut.println(kth);
		int [] result=s.quicksort();
		for(int i=0;i<N;i++) {
			StdOut.println(result[i]);
		}
	}
	

}
