import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
public class Solver {
	private final MinPQ<SearchNode> pq;
	private final MinPQ<SearchNode> pqtwin;

	private int steps;
	private final List<Board> sol;
	private boolean solved=false;
	private boolean solvedtwin=false;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
		if(initial==null) throw new IllegalArgumentException();
		pq= new MinPQ<Solver.SearchNode>();
		sol=new ArrayList<Board>();
		pq.insert(new SearchNode(0, initial, null));
		SearchNode pointer;
		
		Board twin=initial.twin();
		pqtwin=new MinPQ<Solver.SearchNode>();
		pqtwin.insert(new SearchNode(0, twin, null));
		SearchNode pointertwin;
		
		while(!(solved ||solvedtwin)) {
			pointer=pq.delMin();
			//twin
			pointertwin=pqtwin.delMin();
			if(pointer.b.isGoal()) {
				this.solved=true;
				this.steps=pointer.mov;
				while(pointer!=null) {
					sol.add(0, pointer.b);
					pointer=pointer.pre;
				}
				break;
			}
			
			//twin
			if(pointertwin.b.isGoal()){
				solvedtwin=true;
				break;
			}
			else {
				for(Board nextboard:pointer.b.neighbors()) {
					//same with previouse board
					if(pointer.pre==null) {
						SearchNode nextstep=new SearchNode(pointer.mov+1, nextboard, pointer);
						pq.insert(nextstep);
					}
					else {
						if(!nextboard.equals(pointer.pre.b)) {
							SearchNode nextstep=new SearchNode(pointer.mov+1, nextboard, pointer);
							pq.insert(nextstep);
						}
					}
					
					
				}
				
				//twin
				for(Board nextboard:pointertwin.b.neighbors()) {
					//same with previouse board
					if(pointertwin.pre==null) {
						SearchNode nextstep=new SearchNode(pointertwin.mov+1, nextboard, pointertwin);
						pqtwin.insert(nextstep);
					}
					else {
						if(!nextboard.equals(pointertwin.pre.b)) {
							SearchNode nextstep=new SearchNode(pointertwin.mov+1, nextboard, pointertwin);
							pqtwin.insert(nextstep);
						}
					}
					
				}
			}
		}
    	
    }
    
    private class SearchNode implements Comparable<SearchNode>{
    	private int mov=0;
    	private Board b;
    	private SearchNode pre=null;
    	private int priority;
    	public SearchNode(int mov,Board b,SearchNode pre) {
			// TODO Auto-generated constructor stub
    		this.mov=mov;
    		this.b=b;
    		this.pre=pre;
    		this.priority=this.mov+this.b.manhattan();
		}
    	

		@Override
		public int compareTo(SearchNode arg0) {
			// TODO Auto-generated method stub
			return this.priority-arg0.priority;
		}
    	
    }
	

    // is the initial board solvable? (see below)
    public boolean isSolvable() {
    	return solved;
    }

    // min number of moves to solve initial board
    public int moves() {
    	if(solved)
    		return steps;
    	else return -1;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution(){
    	if(solved)
    		return sol;
    	else return null;
    }

    // test client (see below) 
    public static void main(String[] args) {

        // create initial board from file
        In in = new In("testdata/"+args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}