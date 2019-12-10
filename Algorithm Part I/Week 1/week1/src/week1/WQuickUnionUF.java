package week1;
/*
 * improved quick Union
 * id[i] same as quick union
 * sz[i] count the number of child rooted to i
 * 
 * union time O(lgN)
 * find time O(lgN)
 * initialize O(N)
 * 
 */

public class WQuickUnionUF {
	private int[] id, sz;
	
	public WQuickUnionUF(int N)
	{
		id =new int[N];
		sz=new int[N];
		for(int i=1;i<N;i++)
		{
			id[i]=i;
			sz[i]=1;
		}
	}
	
	//with path compretion
	private int root(int i)
	{
		while(i!=id[i])
		{
			//change i'father to i's grandfather
			id[i]=id[id[i]];
			i=id[i];
		}
		return i;
	}
	
	public boolean connected(int q,int p)
	{
		return root(q)==root(p);
	}
	
	public void union(int q,int p)
	{
		int rootq=root(q);
		int rootp=root(p);
		if (sz[rootq]<sz[rootp]) 
		{
			id[rootq]=rootp;
			sz[rootp]+=sz[rootq];
		}
		else
		{
			id[rootp]=rootq;
			sz[rootq]+=sz[rootp];
		}
		
	}

}
