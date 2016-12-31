// Name: Aravind Bhimarasetty
// USC loginid: bhimaras
// CS 455 PA3
// Fall 2015


import java.util.LinkedList;
import java.util.ListIterator;


/**
   Maze class

   Stores information about a maze and can find a path through the maze
   (if there is one).

   Assumptions about structure of the mazeData (parameter to constructor), and the
   path:
   -- no outer walls given in mazeData -- search assumes there is a virtual 
   border around the maze (i.e., the maze path can't go outside of the maze
   boundaries)
   -- start location for a path is maze coordinate (START_SEARCH_ROW,
   START_SEARCH_COL) (constants defined below)
   -- exit loc is maze coordinate (numRows()-1, numCols()-1) 
   (methods defined below)
   -- mazeData input 2D array of booleans, where true means there is a wall
   at that location, and false means there isn't (see public FREE / WALL 
   constants below) 
   -- in mazeData the first index indicates the row. e.g., mazeData[row][col]
   -- only travel in 4 compass directions (no diagonal paths)
   -- can't travel through walls
*/

public class Maze {
   
    public static final int START_SEARCH_COL = 0;
    public static final int START_SEARCH_ROW = 0;

    public static final int NUM_NEIGHBOURS=4;
    public static final int EAST =0;
    public static final int WEST =1;
    public static final int NORTH=2;
    public static final int SOUTH=3;
   
    public static final boolean FREE = false;
    public static final boolean WALL = true;

    /**Representation invariant
    Maze is represented by a boolean 2D array called maze structure. 
    If there's a wall at a particular row, column, then the value of the maze structure is TRUE, else it is FALSE.
    It is NOT padded by any virtual walls around the boundary of the maze. 
    Therefore, it is identical to mazeData input.
    */

    private boolean[][] maze_structure;
    private int maze_rows;
    private int maze_cols;
    private boolean search_performed;
    private boolean search_result;
    private boolean[][] visited;

    private LinkedList<MazeCoord> exit_path;

    /**
       Constructs a maze.
       @param mazeData the maze to search.  See general Maze comments for what
       goes in this array.

    */
    public Maze(boolean[][] mazeData)
    {
	maze_rows = mazeData.length;
	maze_cols = mazeData[0].length;

	maze_structure= new boolean[maze_rows][maze_cols];
	visited = new boolean[maze_rows][maze_cols];

	//See rep. invariant comments above
	for (int i=0; i<maze_rows;i++){
	    for(int j=0; j<maze_cols; j++){
		maze_structure[i][j]= mazeData[i][j];
		visited[i][j]=false;
	    }
	}

	exit_path = new LinkedList<MazeCoord>();

	search_performed= false;
	search_result = false;
      
    }


    /**
       Returns the number of rows in the maze
       @return number of rows
    */
    public int numRows() {
	return maze_rows; 
    }

    /**
       Returns the number of columns in the maze
       @return number of columns
    */   
    public int numCols() {
	return maze_cols; 
    } 
 
   
    /**
       Returns true iff there is a wall at this location
       @param loc the location in maze coordinates
       @return whether there is a wall here
       PRE: 0 <= loc.getRow() < numRows() and 0 <= loc.getCol() < numCols()
    */
    public boolean hasWallAt(MazeCoord loc) {
	return maze_structure[loc.getRow()][loc.getCol()];
    }

   
    /**
       Returns path through the maze. First element is starting location, and
       last element is exit location.  If there was not a path, or if this is called
       before search, returns empty list.

       @return the maze path
    */
    public LinkedList<MazeCoord> getPath() {

	return exit_path; 

    }


    /**
       Find a path through the maze if there is one.
       @return whether path was found.
    */
    public boolean search()  {      
      
	if(!search_performed){//perform search only if not performed earlier
	    search_result= rec_search(new MazeCoord(START_SEARCH_ROW, START_SEARCH_COL));
	    search_performed= true;
	    //print_path();
	    
	}

	return search_result;
      
    }
    /**
       Recursively searches for the path in the maze.
       @param Loc. from where the recursive search is started.
       @return whether path was found.
    */
    private boolean rec_search(MazeCoord currLoc){
	//System.out.println("DEBUG: Started recursive search...");

	MazeCoord[] neighbours = new MazeCoord[NUM_NEIGHBOURS];

	int r= currLoc.getRow();
	int c= currLoc.getCol();
	
	if ( (r<0) || (r> (maze_rows-1))){
	    return false;//return false if currLoc falls outside the maze
	}
	if ((c<0) || (c> (maze_cols-1))){
	    return false;//return false if currLoc falls outside the maze
	}
	if (hasWallAt(new MazeCoord(r,c))){
	    return false;//return false if there's a wall at currLoc
	}
	if (visited[r][c]){
	    return false;//return false if currLoc has already been visited earlier
	}
	if ((r==(maze_rows-1)) && (c== (maze_cols-1))){
	    exit_path.addFirst(currLoc);
	    //System.out.println("DEBUG: Location added to exit_path: "+currLoc);
	    return true;//return true if currLoc matches the exit loc.
	}
    
	//If none of the above, then perform rec. search from the 4 adjacent loc.
	visited[r][c]= true;
	
	neighbours[EAST]= new MazeCoord(r, c+1);
	neighbours[WEST]= new MazeCoord(r, c-1);
	neighbours[NORTH]= new MazeCoord(r-1, c);
	neighbours[SOUTH]= new MazeCoord(r+1, c);

	for (int i=0;i<NUM_NEIGHBOURS;i++){

	    //System.out.println("DEBUG: Searching neighbours...");
	    
	    if(rec_search(neighbours[i])){
		    
		exit_path.addFirst(currLoc);
		//System.out.println("DEBUG: Location added to exit_path: "+currLoc);
		return true;
	    }
		
	}
	return false;//return false if no path is found
    }

    /**
       Prints the exit path if one exists. It's a helper method used for testing.
    */
    private void print_path(){
	ListIterator<MazeCoord> iter = exit_path.listIterator();

	System.out.println("DEBUG: Printing points on the exit path...");
	
	while(iter.hasNext()){
	    System.out.println(iter.next());
	}
    }
	

}
