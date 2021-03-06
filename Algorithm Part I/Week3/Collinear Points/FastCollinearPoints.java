import java.util.Arrays;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	private int num=0;
	private LineSegment[] ls;
	private LineSegment[] temp;
	private Point[] points;
	private int capacity=20;
   public FastCollinearPoints(Point[] points) {
	   // finds all line segments containing 4 or more points
	   if(points==null) throw new IllegalArgumentException();
	   else {
			this.points=new Point[points.length];
			for(int i=0;i<points.length;i++)
			{
				if(points[i]==null) throw new IllegalArgumentException(); 
				else this.points[i]=points[i];
			}
		}
	   
	   Point [] ysort=new Point[points.length];
	   for (int i=0;i<ysort.length;i++) ysort[i]=points[i];
	   Arrays.sort(ysort);
	   for(int i=1;i<ysort.length;i++) {
			if (ysort[i].slopeTo(ysort[i-1])==Double.NEGATIVE_INFINITY)
				throw new IllegalArgumentException(); 
		}
	   ls=new LineSegment[capacity];
	   for(int i=0;i<points.length;i++) {
		   for (int k=0;k<ysort.length;k++) points[k]=ysort[k];
		   Arrays.sort(points,points[i].slopeOrder());
		   int same=1;
		   if(i+1<points.length&&points[i].slopeTo(points[i+1])==Double.NEGATIVE_INFINITY) 
			   throw new IllegalArgumentException();
		   for(int j=2;j<points.length;j++) {
			   if (points[0].slopeTo(points[j-1])==points[0].slopeTo(points[j])) {
				   same++;
			   }
			   
			   else {
				   if(same>=3&&points[0].compareTo(points[j-same])<=-1) {
					   num++;
					   if(num>capacity) {
							capacity+=capacity;
							temp=new LineSegment[capacity];
							for(int k=0;k<ls.length;k++)
								temp[k]=ls[k];
							ls=temp;
							temp=null;
						}
					   ls[num-1]=new LineSegment(points[0],points[j-1]);
					   
				   }
				   same=1;
			   }
		   }
		   if(same>=3&&points[0].compareTo(points[points.length-same])<=-1) {
			   num++;
			   if(num>capacity) {
					capacity+=capacity;
					temp=new LineSegment[capacity];
					for(int k=0;k<ls.length;k++)
						temp[k]=ls[k];
					ls=temp;
					temp=null;
				}
			   ls[num-1]=new LineSegment(points[0],points[points.length-1]);
			   
		   }
	   }
	   if(capacity!=num) {
			capacity=num;
			temp=new LineSegment[num];
			for(int k=0;k<num;k++)
				temp[k]=ls[k];
			ls=temp;
			temp=null;
	   }
	   
   }
   public int numberOfSegments() {
	   // the number of line segments
	   return num;
   }
   public LineSegment[] segments() {
	   // the line segments
	  
		return ls;
   }
   
	public static void main(String[] args) {
		//StdOut.print(Double.POSITIVE_INFINITY==Double.POSITIVE_INFINITY);

	    // read the n points from a file
	    In in = new In(args[0]);
	    int n = in.readInt();
	    Point[] points = new Point[n];
	    for (int i = 0; i < n; i++) {
	        int x = in.readInt();
	        int y = in.readInt();
	        points[i] = new Point(x, y);
	    }

	    // draw the points
	    StdDraw.enableDoubleBuffering();
	    StdDraw.setXscale(0, 32768);
	    StdDraw.setYscale(0, 32768);
	    for (Point p : points) {
	        p.draw();
	    }
	    StdDraw.show();

	    // print and draw the line segments
	    FastCollinearPoints collinear = new FastCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	}
}