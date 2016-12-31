import javax.swing.JFrame;
import java.util.Scanner;

/**
This class takes input from the user on the total number of steps, calculates the random path of a drunkard (by using the Drunkard class) and visulaizes it with the help of the RandomWalkComponent class.
*/

public class RandomWalkViewer{
    private static final int FRAME_SIZE_X= 400;
    private static final int FRAME_SIZE_Y= 400;
    private static final int X_OFFSET= 200;
    private static final int Y_OFFSET= 200;
    private static final int STEPSIZE =1;
    private static final int STEP2PIXEL =5;
    
    public static void main(String[] args){
	
	//Reading in total num of steps given by user
	Scanner readinput = new Scanner(System.in);
	int num_steps =-1;
	
	while (num_steps <= 0){ //keep asking user for the total num. of steps till he/she enters a valid (>0) value
	    System.out.print("\nPlease enter a valid no. (greater than zero) for the total no. of steps: ");
	    num_steps = readinput.nextInt();
	}

	//Create a new window and set its size, name...
	JFrame frame = new JFrame(); 

	frame.setSize(FRAME_SIZE_X, FRAME_SIZE_Y);
	frame.setTitle("Random Walk Viewer!");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	/*
	//Testing RandomWalkComponent class...
	ImPoint p1 = new ImPoint(30,110);
	ImPoint p2 = new ImPoint(80,110);
	
	RandomWalkComponent Test_draw= new RandomWalkComponent(p1, p2);

	frame.add(Test_draw);
	*/
	
	ImPoint start_pos_pixel = new ImPoint(X_OFFSET, Y_OFFSET);//starting from center of frame
	int step_size_pixel = STEPSIZE*STEP2PIXEL; //step size in pixels
	
	Drunkard new_Drunkard = new Drunkard(start_pos_pixel, step_size_pixel); //Drunkard starts at starting position in the frame
	    
	RandomWalkComponent drunkards_path= new RandomWalkComponent(new_Drunkard, num_steps); //Create new drunkards path and draw it using the RandomWalkComponent class by passing the Drunkard object, num_steps, etc.
	
	frame.add(drunkards_path);

	frame.setVisible(true);
	    
    }
}
