import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;



public class BruteCollinearPoints {
	private int num=0;
	private int capacity=20;
	private LineSegment[] ls;
	private LineSegment[] temp;
	private Point [] points;
	 // finds all line segments containing 4 points
	public BruteCollinearPoints(Point[] points) {
		if(points==null) throw new IllegalArgumentException();
		else {
			this.points=new Point[points.length];
			for(int i=0;i<points.length;i++)
			{
				if(points[i]==null) throw new IllegalArgumentException(); 
				else this.points[i]=points[i];
			}
		}
		double s1,s2,s3;
		Arrays.sort(points);
		for(int i=1;i<points.length;i++) {
			if (points[i].slopeTo(points[i-1])==Double.NEGATIVE_INFINITY)
				throw new IllegalArgumentException(); 
		}
		ls=new LineSegment[capacity];
		int len=points.length;
		for(int i=0;i<len-3;i++) {
			if(points[i]==null) throw new IllegalArgumentException(); 
			for(int j=i+1;j<len-2;j++) {
				if(points[j]==null) throw new IllegalArgumentException();
				for(int m=j+1;m<len-1;m++) {
					if(points[m]==null) throw new IllegalArgumentException();
					for(int n=m+1;n<len;n++) {
						if(points[n]==null) throw new IllegalArgumentException();
						s1=points[i].slopeTo(points[j]);
						s2=points[j].slopeTo(points[m]);
						s3=points[m].slopeTo(points[n]);
						if(s1==Double.NEGATIVE_INFINITY || s2==Double.NEGATIVE_INFINITY ||s3==Double.NEGATIVE_INFINITY )
							throw new IllegalArgumentException();
						if(s1==s2 &&s2==s3) {
							num+=1;
							if(num>capacity) {
								capacity+=capacity;
								temp=new LineSegment[capacity];
								for(int k=0;k<ls.length;k++)
									temp[k]=ls[k];
								ls=temp;
								temp=null;
							}
							ls[num-1]=new LineSegment(points[i],points[n]);
						}
						
						
					}
				}
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
   // the number of line segments
	public int numberOfSegments() {
		return num;
	}
   		// the line segments
	public LineSegment[] segments() {
		return ls;

	}
	
	public static void main(String[] args) {

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
	    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
	    for (LineSegment segment : collinear.segments()) {
	        StdOut.println(segment);
	        segment.draw();
	    }
	    StdDraw.show();
	}
}