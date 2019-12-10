
/*
 * id [i] 
 * 
 * when q and p is connected when id[q]=id[p]
 * 
 * to union q and p , just change id[q] and others with the same id to id[q]
 * 
 * find connected time is O(1)
 * union time is O(N)
 * initialize time is O(N£©
 * 
 * union is too expensive
 * trees is flat, but it's too expensive to keep it flat
 */
public class QuickFindUF {
	private int [] id;
	
	public QuickFindUF(int N)
	{
		id= new int[N];
		for(int i=0;i<N;i++)
		{
			id[i]=i;
		}
	}
	
	public boolean connected(int q, int p)
	{
		return (id[q]==id[p]);
	}
	
	public void union(int q,int p)
	{
		int qid=id[q];
		int pid=id[p];
		
		for(int i=0;i<id.length;i++)
		{
			if(id[i]==qid) id[i]=pid;
		}
	}
}
