import java.util.ArrayList;
import java.util.Comparator;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;


public class KdTree {
	private Node root=null;
	private int size=0;
	
	public KdTree() {
		// construct an empty set of points 
	}
	
	public class Node{
		private Point2D p=null;
		private Node left=null;
		private Node right=null;
		private Comparator<Point2D> comp=Point2D.Y_ORDER;//decide by y
		public Node(Point2D p,Comparator<Point2D> comp) {
			this.p=p;
			this.comp=comp;
		}
	}
	public boolean isEmpty() {
		// is the set empty? 
		return size==0;
	}
	public int size() {
		// number of points in the set 
		return size;
	}
	public void insert(Point2D p) {
		// add the point to the set (if it is not already in the set)
		root=this.put(p, root, Point2D.Y_ORDER);
		this.size++;
		
	}
	private Node put(Point2D p,Node node,Comparator<Point2D> childcomp) {
		if(node==null) {
			return new Node(p,childcomp);
		}
		else {
			if(node.comp==Point2D.Y_ORDER) childcomp=Point2D.X_ORDER;
			else childcomp=Point2D.Y_ORDER;
			//less root, go left, biger or equal root, go right;
			if(node.comp.compare(p, node.p)<0) {
				node.left=put(p,node.left,childcomp);					
			}
			else {
				node.right=put(p,node.right,childcomp);
			}
		return node;
		}
				
	}
	public boolean contains(Point2D p) {
		// does the set contain point p? 
		return contains(p,root);
	}
	private boolean contains(Point2D p,Node node) {
		if(node==null) return false;
		else {
			if(node.p.equals(p)) return true;
			else {
				if(node.comp.compare(p,node.p)<0) return contains(p,node.left);
				else return contains(p, node.right);
			}
		}
	}
	public void draw() {
		// draw all points to standard draw 
		draw(root);
		
	}
	private void draw(Node node) {
		if (node==null) return;
		else {
			node.p.draw();
			draw(node.left);
			draw(node.right);
		}
	}
	public Iterable<Point2D> range(RectHV rect){
		// all points that are inside the rectangle (or on the boundary) 
		ArrayList<Point2D> list=new ArrayList<Point2D>();
		range(rect,list,root);
		return list;
	}
	private void range(RectHV rect, ArrayList<Point2D> list, Node node) {
		if(node==null)return;
		else{
			if(rect.contains(node.p))list.add(node.p);
			Point2D pleft=null,pright=null;
			if(node.comp.equals(Point2D.X_ORDER)) {
				pleft=new Point2D(rect.xmin(), 0);
				pright= new Point2D(rect.xmax(), 0);
			}
			else {
				pleft=new Point2D(0, rect.ymin());
				pright= new Point2D(0, rect.ymax());
			}
			//only go left
			if(node.comp.compare(pright, node.p)<0) {
				range(rect, list, node.left);
			}
			else if(node.comp.compare(pleft, node.p)>=0) {
				range(rect, list, node.right);
			}
			else {
				range(rect, list, node.left);
				range(rect, list, node.right);
			}
			
		}
	}
	public Point2D nearest(Point2D p) {
		// a nearest neighbor in the set to point p; null if the set is empty 
		double [] mindistance= {Double.MAX_VALUE};
		Point2D[] candidate= {null};
		nearest(p,root,mindistance,candidate);
		return candidate[0];
	}
	
	private void nearest(Point2D target,Node node,double[] mindistance,Point2D[] candidate) {
		if(node==null) return;
		else {
			double temp=target.distanceTo(node.p);
			if(temp<mindistance[0]) {
				mindistance[0]=temp;
				candidate[0]=node.p;
			}
			//go left
			double per;
			if(node.comp.equals(Point2D.X_ORDER)) per=Math.abs(target.x()-node.p.x());
			else per=Math.abs(target.y()-node.p.y());
			
			if(node.comp.compare(target, node.p)<0) {
				nearest(target,node.left,mindistance,candidate);
				if(mindistance[0]>per) nearest(target,node.right,mindistance,candidate);
			}
			else {
				nearest(target,node.right,mindistance,candidate);
				if(mindistance[0]>per) nearest(target,node.left,mindistance,candidate);
			}
			
		}
	}
	
	

	public static void main(String[] args) {
		// unit testing of the methods (optional) 
		KdTree kt=new KdTree();
		kt.insert(new Point2D(10, 10));
		kt.insert(new Point2D(10, 0));
		kt.insert(new Point2D(10, 20));
		kt.insert(new Point2D(0, 0));
		kt.insert(new Point2D(20, 0));
		kt.insert(new Point2D(0, 20));
		kt.insert(new Point2D(20, 20));
		
		StdOut.println(kt.contains(new Point2D(20, 20)));
		StdOut.println(kt.contains(new Point2D(20, 21)));
		
		StdOut.println(kt.nearest(new Point2D(20, 15)).x());
		StdOut.println(kt.nearest(new Point2D(20, 15)).y());
	}
}