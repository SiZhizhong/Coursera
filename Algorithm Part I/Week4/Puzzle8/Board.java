import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.StdOut;

/**
 * 
 * @author ˹
 * 
 * 
 *
 */


public class Board {
	private final int [][] tiles;
	private final int n;
	private int ham;
	private boolean hamed=false;
	private int man;
	private boolean maned=false;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {
    	this.n=tiles.length;
    	this.tiles=new int[n][n];
    	for(int i=0;i<n;i++) {
    		for(int j=0;j<n;j++)
    			this.tiles[i][j]=tiles[i][j];
    	}
    }
                                           
    // string representation of this board
    public String toString() {
    	StringBuffer sb=new StringBuffer();
    	sb.append(Integer.toString(n));
    	for(int i=0;i<n;i++) {
    		sb.append('\n');
    		for(int j=0;j<n;j++) {
    			sb.append(String.format("%2d", this.tiles[i][j]));
    			sb.append(" ");
    		}
    	}
    	return new String(sb);
    }

    // board dimension n
    public int dimension() {
    	return n;
    }

    // number of tiles out of place
    public int hamming() {
    	if(hamed) return this.ham;
    	int sum=0;
    	for(int i=0;i<n;i++) {
    		for(int j=0;j<n;j++) {
    			if(this.tiles[i][j]!=0&&this.tiles[i][j]!=n*i+j+1) sum++;
    		}
    	}
    	hamed=true;
    	this.ham=sum;
    	return sum;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
    	if(maned) return this.man;
    	int man=0;
    	int x,y,tile;
    	for (int i=0;i<n;i++) {
    		for(int j=0;j<n;j++) {
    			tile=this.tiles[i][j];
    			if(tile!=0) {
    				man+=Math.abs(i-(tile-1)/n)+Math.abs(j-(tile-1)%n);
    			}
    		}
    	}
    	maned=true;
    	this.man=man;
    	return man;
    	
    }
    
 

    // is this board the goal board?
    public boolean isGoal() {
    	return this.hamming()==0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
    	if(this==y) return true;
    	if(y instanceof Board) {
    		String stry=y.toString();
    		String strx=this.toString();
    		return(strx.equals(stry));
    	}
    	return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors(){
    	int temp;
    	List<Board> listboard=new ArrayList<Board>();
    	int i0=0,j0=0;
    	for(int i=0;i<n;i++) {
    		for(int j=0;j<n;j++) {
    			if(this.tiles[i][j]==0) {
    				i0=i;
    				j0=j;
    				break;
    			}
    		}
    		if(this.tiles[i0][j0]==0) break;
    	}
    	if(i0<n-1) {
    		temp=this.tiles[i0+1][j0];
    		this.tiles[i0+1][j0]=this.tiles[i0][j0];
    		this.tiles[i0][j0]=temp;
    		
    		listboard.add(new Board(this.tiles));
    		
    		temp=this.tiles[i0+1][j0];
    		this.tiles[i0+1][j0]=this.tiles[i0][j0];
    		this.tiles[i0][j0]=temp;
    	}
    	if(j0<n-1) {
    		temp=this.tiles[i0][j0+1];
    		this.tiles[i0][j0+1]=this.tiles[i0][j0];
    		this.tiles[i0][j0]=temp;
    		
    		listboard.add(new Board(this.tiles));
    		
    		temp=this.tiles[i0][j0+1];
    		this.tiles[i0][j0+1]=this.tiles[i0][j0];
    		this.tiles[i0][j0]=temp;
    	}
    	if(i0>0) {
    		temp=this.tiles[i0-1][j0];
    		this.tiles[i0-1][j0]=this.tiles[i0][j0];
    		this.tiles[i0][j0]=temp;
    		
    		listboard.add(new Board(this.tiles));
    		
    		temp=this.tiles[i0-1][j0];
    		this.tiles[i0-1][j0]=this.tiles[i0][j0];
    		this.tiles[i0][j0]=temp;
    		
    		
    	}
    	
    	if(j0>0) {
    		temp=this.tiles[i0][j0-1];
    		this.tiles[i0][j0-1]=this.tiles[i0][j0];
    		this.tiles[i0][j0]=temp;
    		
    		listboard.add(new Board(this.tiles));
    		
    		temp=this.tiles[i0][j0-1];
    		this.tiles[i0][j0-1]=this.tiles[i0][j0];
    		this.tiles[i0][j0]=temp;
    	}
    	
    	
    	return listboard;
    }
   
    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
    	int temp;
    	for(int i=0;i<n;i++) {
    		if(this.tiles[i][0]!=0&&this.tiles[i][1]!=0) {
    			temp=this.tiles[i][0];
    			this.tiles[i][0]=this.tiles[i][1];
    			this.tiles[i][1]=temp;
    			Board b=new Board(this.tiles);
    			temp=this.tiles[i][0];
    			this.tiles[i][0]=this.tiles[i][1];
    			this.tiles[i][1]=temp;
    			return b;
    		}
    	}
    	return null;
		
    	
    }
    
    

    // unit testing (not graded)
    public static void main(String[] args) {
    	int[][]tiles= {{1,0,3},{4,2,6},{7,8,5}};
    	Board b =new Board(tiles);
    	
    	for(Board each:b.neighbors()) {
    		StdOut.println(each.toString());
    	}
    	
    	
    }

}
