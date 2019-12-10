import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;
public class Permutation {

	 public static void main(String[] args) {
		 int k=Integer.parseInt(args[0]);
		   String temp;
		   RandomizedQueue<String> rq = new RandomizedQueue<String>();
		   while (!StdIn.isEmpty()) {
			   
			   temp=StdIn.readString();
			   rq.enqueue(temp);  
		   }
		   for(int i=0;i<k;i++) {
			   StdOut.println(rq.dequeue());
		   }

	 }
	 
	 
}