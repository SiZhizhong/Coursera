package week1;

/*
 * id[i] means the root of i is id[i]
 * 
 * p and q are connected when p and q have the same root
 * 
 * to union q and p,just connected the root to p to the root of q. which means id[root(p)]=root(q)
 * 
 * root time : worst case O(N)
 * union time : worst cast O(N), but average is better than quick union
 * find time: worst case O(N)
 * 
 * defect: trees can be tall, find is too expensive
 * 
 */

public class QuickUnionUF {
	private int[] id;
	
	public QuickUnionUF(int N) {
		// TODO Auto-generated constructor stub
		id=new int[N];
		for (int i=0;i<N;i++)
			id[i]=i;
	}
	
	private int root(int i)
	{
		while(i!=id[i]) i=id[i];
		return i;
	}
	
	public boolean connected(int q,int p)
	{
		return root(p)==root(q);
	}
	
	public void union(int q,int p)
	{
		int rootq=root(q);
		int rootp=root(p);
		id[rootq]=rootp;
	}

}
