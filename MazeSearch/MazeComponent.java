// Name: Aravind Bhimarasetty
// USC loginid: bhimaras
// CS 455 PA3
// Fall 2015

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import java.util.LinkedList;
import java.util.ListIterator;
/**
   MazeComponent class
   
   A component that displays the maze and a path through it if one has been found.
*/
public class MazeComponent extends JComponent
{
    private Maze maze;
   
    private static final int START_X = 10; // where to start drawing maze in frame
    private static final int START_Y = 10;
   
    private static final int BOX_WIDTH = 20;  // width and height of one maze unit
    private static final int BOX_HEIGHT = 20;
   

    /**
       Constructs the component.
       @param maze   the maze to display
    */
    public MazeComponent(Maze maze) 
    {
	this.maze = maze;       
    }

   
    /**
       Draws the current state of maze including a path through it if one has
       been found.
       @param g the graphics context
    */
    public void paintComponent(Graphics g)
    {
	Graphics2D g2 = (Graphics2D) g;

	/**
	   The below section is for drawing the maze and exit location
	*/
	MazeCoord MazeCoord_obj;

	Rectangle rect = new Rectangle(START_X, START_Y, BOX_WIDTH, BOX_HEIGHT);
	
	for (int i=0; i< maze.numRows();i++){
	    for (int j=0; j< maze.numCols(); j++){

		rect.setLocation(START_X + j*BOX_WIDTH, START_Y+ i*BOX_HEIGHT);

		MazeCoord_obj = new MazeCoord(i, j);
	
		g2.setColor(Color.BLACK);
	       
		if(maze.hasWallAt(MazeCoord_obj)){
		    //System.out.println("DEBUG: Maze has wall at ("+i+", "+j+")");;
		    g2.fill(rect);// indicate a wall with a black box
		}
	    }
	    
	}
	
	g2.setColor(Color.GREEN);//exit location in green
	g2.fill(rect);

	/**
	   The below section is for drawing the border for the maze
	*/

	Rectangle border = new Rectangle(START_X, START_Y,  maze.numCols()*BOX_WIDTH, maze.numRows()*BOX_HEIGHT);
	g2.setColor(Color.BLACK);
	g2.draw(border);

	/**
	   Finally, the below section is for drawing the path.
	*/
	LinkedList<MazeCoord> path = maze.getPath();

	if (!path.isEmpty()){//draw the path only if an exit path exists
	    
	    ListIterator<MazeCoord> iter = path.listIterator();//to iterate on the points in the path

	    MazeCoord from_loc = iter.next();//read the first point in the path, namely the start loc.
	    MazeCoord to_loc;
	    

	    Point2D.Double from = new Point2D.Double(START_X+ (from_loc.getCol()+1)*(BOX_WIDTH/2), START_Y+ (from_loc.getRow()+1)*(BOX_HEIGHT/2)); //construct a point at the middle of the box
	    
	    while(iter.hasNext()){
		to_loc = iter.next();
		
		Point2D.Double to = new Point2D.Double(from.getX()+ (to_loc.getCol()-from_loc.getCol())*BOX_WIDTH, from.getY()+ (to_loc.getRow()- from_loc.getRow())*BOX_HEIGHT);  //construct a point at the middle of the box corresponding to the next element in the path

		Line2D.Double path_segment = new Line2D.Double(from, to);//construct a line segments connecting two consecutive points in the path
		g2.setColor(Color.BLUE);
		g2.draw(path_segment);

		from_loc= to_loc;
		from =to;
	    }
	}
		
	    
    }
   
}



