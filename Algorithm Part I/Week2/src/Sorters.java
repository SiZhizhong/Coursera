import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
public class Sorters {
	
	public void insertionSort(int[] list) {
		int temp;
		for (int i=0;i<list.length-1;i++) {
			int minindex=i;
			for(int j=i+1;j<list.length;j++) {
				if (list[j]<list[minindex]) minindex=j; 
			}
			temp=list[i];
			list[i]=list[minindex];
			list[minindex]=temp;
			
		}
	}
	
	public void selectionSort(int[] list) {
		int temp;
		for(int i=1;i<list.length;i++) {
			int j=i;
			while(j>0 && list[j]<list[j-1]) {
				temp=list[j-1];
				list[j-1]=list[j];
				list[j]=temp;
				j--;
			}
		}
	}
	
	public void shellSort(int[] list) {
		int length=list.length;
		int h=1;
		int temp;
		while(h<length/3) h=h*3+1;
		while(h>0) {
			for(int i=h;i<list.length;i++) {
				int j=i;
				while(j-h>=0&&list[j]<list[j-h]) {
					temp=list[j-h];
					list[j-h]=list[j];
					list[j]=temp;
					j-=h;
				}
			}
			h=h/3;
		}
	}
	
	public void mergeSort(int[]list) {
		int[] aux=new int[list.length];
		for(int i=0;i<list.length;i++) {
			aux[i]=list[i];
		}
		mergeSort_(list, aux, 0, list.length-1);
	}
	// begin and end are both included
	private void mergeSort_(int[]list,int[] aux,int begin,int end) {
		if(begin>=end) return;
		else {
			int mid=(begin+end)/2;
			mergeSort_(aux,list, begin, mid);
			mergeSort_(aux,list, mid+1, end);
			int i=begin,j=mid+1;
			for(int k=begin;k<=end;k++)
			{
				if(i>mid) list[k]=aux[j++];
				else if(j>end) list[k]=aux[i++];
				else if(aux[i]>aux[j]) list[k]=aux[j++];
				else list[k]=aux[i++];
			}
		}
	}
	/*
	
	private void upmergesort(int[]list) {
		int size=1;
		int n=list.length;
		int power=(int) (Math.log(n)/Math.log(2));
		int[]aux=new int[n];
		for(int i=0;i<n;i++)
		{
			aux[i]=list[i];
		}
		int time=0;
		int size=1;
		while(time<=power)
		{
			for(i=0;i<n;i+=Math)
			merge(aux,list,)
		}
		
	}
	
	public void merge(int[]list,int[]aux,int begin,int mid,int end) {
		int i=begin,j=mid+1;
		for(int k=begin;k<=end;k++)
		{
			if(i>mid) list[k]=aux[j++];
			else if(j>end) list[k]=aux[i++];
			else if(aux[i]>aux[j]) list[k]=aux[j++];
			else list[k]=aux[i++];
		}
	}
	*/
	public void shuffling(int[] list) {
		int random;
		int temp;
		for (int i=1;i<list.length;i++) {
			random=StdRandom.uniform(i+1);
			temp=list[i];
			list[i]=list[random];
			list[random]=temp;
		}
			
		
	}
	public static void main(String[]args) {
		int [] length= {500000,1000000,2000000,3000000,4000000,5000000,6000000,7000000};
		int N;
		double time;
		
		for(int i=0;i<length.length;i++) {
			N=length[i];
			int [] list=new int[N];
			for(int j=0;j<N;j++) list[j]=StdRandom.uniform(2*N);
			Sorters sort=new Sorters();
			Stopwatch sw=new Stopwatch();
			sort.mergeSort(list);
			time=sw.elapsedTime();
			StdOut.println(time);
			
		}

		
		
	}

}
