/******************************************************************************
 *  Compilation:  javac KdTreeVisualizer.java
 *  Execution:    java KdTreeVisualizer
 *  Dependencies: KdTree.java
 *
 *  Add the points that the user clicks in the standard draw window
 *  to a kd-tree and draw the resulting kd-tree.
 *
 ******************************************************************************/

//test for git
//test
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
public class KdTreeVisualizer {

    public static void main(String[] args) {
        RectHV rect = new RectHV(0.5, 0.5, 1.5, 1.5);
        StdDraw.enableDoubleBuffering();
        KdTree kdtree = new KdTree();
        StdDraw.setXscale(0, 2);
		StdDraw.setYscale(0, 2);
		
		
        while (true) {
        	
            if (StdDraw.isMousePressed()) {
                double x = StdDraw.mouseX();
                double y = StdDraw.mouseY();
                
                StdOut.printf("%8.6f %8.6f\n", x, y);
                Point2D p = new Point2D(x, y);
                if (rect.contains(p)) {
                    StdOut.printf("contains\n%8.6f %8.6f\n", x, y);
                    kdtree.insert(p);
                    StdDraw.clear();
                    kdtree.draw();
                    StdDraw.show();
                }
            }
            rect.draw();
            StdDraw.show();
            StdDraw.pause(20);
        }

    }
}