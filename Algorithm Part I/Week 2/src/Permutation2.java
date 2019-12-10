import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {

	 public static void main(String[] args) {
		 int k=Integer.parseInt(args[0]);
		   String temp;
		   RandomizedQueue<String> rq = new RandomizedQueue<String>();
		   for (int i=0;i<k;i++) {
			   
			   temp=StdIn.readString();
			   rq.enqueue(temp);  
		   }
		   
		   for (String temp1 : rq) StdOut.println(temp1);   

	    }

}