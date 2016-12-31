//Name: Aravind Bhimarasetty
//USC loginid: bhimaras
//CS 455 PA3
//Fall 2015

import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFrame;
import java.util.Scanner;
import java.io.File;

/**
 * MazeViewer class
 * 
 * Program to read in and display a maze and a path through the maze. At user
 * command displays a path through the maze if there is one.
 * 
 * How to call it from the command line:
 * 
 *      java MazeViewer mazeFile
 * 
 * where mazeFile is a text file of the maze. The format is the number of rows
 * and number of columns, followed by one line per row. Each maze location is
 * either a wall (1) or free (0). Here is an example of contents of a file for
 * a 3x4 maze:
 * 
 * 3 4 
 * 0111
 * 0000
 * 1110
 * 0010
 * 
 * The top left is the maze entrance, and the bottom right is the maze exit.
 * 
 */

public class MazeViewer {
   
    public static void main(String[] args)  {

	String fileName = "";

	try {

	    if (args.length < 1) {
		System.out.println("ERROR: missing file name command line argument");
	    }
	    else {
		fileName = args[0];

		boolean[][] mazeData = readMazeFile(fileName);

		JFrame frame = new MazeFrame(mazeData);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);
	    }

	}
	catch (FileNotFoundException exc) {
	    System.out.println("File not found: " + fileName);
	}
	catch (IOException exc) {
	    exc.printStackTrace();
	}
    }

    /**
       readMazeFile reads in and returns a maze from the file whose name is
       String given. The file format is shown in the MazeViewer class comments.
   
       @param fileName
       the name of a file to read from
       @returns 
       the array with maze contents. false at a location means there is no wall
       (0 in the file) and true means there is a wall (1 in the file).
       The first dimension is which row, and the second is which column. E.g. if the file
       started with 3 10, it would mean the array returned would have
       3 rows, and 10 columns.
       @throws FileNotFoundException
       if there's no such file (subclass of IOException)
       @throws IOException
       (hook given in case you want to do more error-checking.
       that would also involve changing main to catch other exceptions)
    */
    private static boolean[][] readMazeFile(String fileName) throws IOException {

	File inFile = new File(fileName);
	Scanner in = new Scanner(inFile);

	int rows= in.nextInt();//read the first integer in the file...this corresponds to the number of rows

	//      System.out.println("DEBUG: rows read= "+ rows);
      
	int cols= in.nextInt();//read the second integer in the file...this corresponds to the number of cols

	//      System.out.println("DEBUG: columns read= "+ cols);
      
	boolean[][] maze_data = new boolean[rows][cols];

	String nextline;

	nextline = in.nextLine();//go to the next line where the maze info starts
      
	for (int i=0; i<rows; i++){//num. of maze info lines should be equal to the number of rows

	    nextline = in.nextLine();
	  
	    //	  System.out.println("DEBUG: nextline= "+nextline);
	    //	  System.out.println("DEBUG: length of nextline= "+nextline.length());
	  
	    for (int j=0; j<cols; j++){
	      
		if (nextline.charAt(j)=='1'){
		    maze_data[i][j]= true;//a 1 is represented as 'true', i.e. wall present
		}
		else if (nextline.charAt(j)=='0'){
		    maze_data[i][j]= false;// a 0 is represented as 'false', wall is not present
		    //Note that no other input checking is performed on the given file or the values contained therein
		}
	      
		//	      System.out.println("DEBUG: maze_data[" +i+"]["+j+"]= "+maze_data[i][j]);
	    }
	}
      
	return maze_data;

    }

}

