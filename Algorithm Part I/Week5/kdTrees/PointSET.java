import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

/*
 * public class Point2D implements Comparable<Point2D> {
   public Point2D(double x, double y)              // construct the point (x, y)
   public  double x()                              // x-coordinate 
   public  double y()                              // y-coordinate 
   public  double distanceTo(Point2D that)         // Euclidean distance between two points 
   public  double distanceSquaredTo(Point2D that)  // square of Euclidean distance between two points 
   public     int compareTo(Point2D that)          // for use in an ordered symbol table 
   public boolean equals(Object that)              // does this point equal that object? 
   public    void draw()                           // draw to standard draw 
   public  String toString()                       // string representation 
}

public class RectHV {
   public    RectHV(double xmin, double ymin,      // construct the rectangle [xmin, xmax] x [ymin, ymax] 
                    double xmax, double ymax)      // throw an IllegalArgumentException if (xmin > xmax) or (ymin > ymax)
   public  double xmin()                           // minimum x-coordinate of rectangle 
   public  double ymin()                           // minimum y-coordinate of rectangle 
   public  double xmax()                           // maximum x-coordinate of rectangle 
   public  double ymax()                           // maximum y-coordinate of rectangle 
   public boolean contains(Point2D p)              // does this rectangle contain the point p (either inside or on boundary)? 
   public boolean intersects(RectHV that)          // does this rectangle intersect that rectangle (at one or more points)? 
   public  double distanceTo(Point2D p)            // Euclidean distance from point p to closest point in rectangle 
   public  double distanceSquaredTo(Point2D p)     // square of Euclidean distance from point p to closest point in rectangle 
   public boolean equals(Object that)              // does this rectangle equal that object? 
   public    void draw()                           // draw to standard draw 
   public  String toString()                       // string representation 
}

Implementation requirements.  You must use either SET or java.util.TreeSet; do not implement your own red¨Cblack BST.
Corner cases.  Throw an IllegalArgumentException if any argument is null. Performance requirements.  
Your implementation should support insert() and contains() in time proportional to the logarithm
 of the number of points in the set in the worst case; it should support nearest() and range() 
 in time proportional to the number of points in the set.

 */
public class PointSET {
	private TreeSet<Point2D> points;
	
	public PointSET() {
	   // construct an empty set of points 
		points=new TreeSet<Point2D>();
	}
	
	public boolean isEmpty() {
	   // is the set empty? 
	   return points.isEmpty();
	   
	}
	public int size() {
	   // number of points in the set 
	   return points.size();
	}
	public void insert(Point2D p) {
	   // add the point to the set (if it is not already in the set)
		if(!points.contains(p))points.add(p);
	}
	public boolean contains(Point2D p) {
	   // does the set contain point p? 
	   return points.contains(p);
	}
	public void draw() {
	   // draw all points to standard draw 
		for(Point2D p:points) {
			p.draw();
		}
	   
	}
	public Iterable<Point2D> range(RectHV rect){
	   // all points that are inside the rectangle (or on the boundary)
		List<Point2D> inpoints=new ArrayList<Point2D>();
	   return null;
	}
	public Point2D nearest(Point2D p) {
	   // a nearest neighbor in the set to point p; null if the set is empty 
	   return null;
	}

	public static void main(String[] args) {
	   // unit testing of the methods (optional) 
	}
}