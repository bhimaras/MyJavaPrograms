import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D;
import java.awt.Rectangle;
import java.awt.Color;

/* A component that creates line segments to visualize the drunkard's path.
 */

public class RandomWalkComponent extends JComponent{

    private Drunkard new_Drunkard;
    private int num_steps;


    private ImPoint CurrLoc_pixel = new ImPoint(0,0);//CurrLoc_frame contains the position before step is taken with respect to origin at X_OFFSET and Y_OFFSET 
    private ImPoint NewLoc_pixel = new ImPoint(0,0);;//NewLoc_frame contains the position before step is taken with respect to origin at X_OFFSET and Y_OFFSET 

    
    
    public RandomWalkComponent(Drunkard new_Drunkard, int num_steps){

	this.new_Drunkard = new_Drunkard;
	this.num_steps= num_steps;

    }
    
    public void paintComponent(Graphics g){
	
	Graphics2D g2 = (Graphics2D) g;

	/*
	//Test 1: Draw rectangle
	Rectangle rect = new Rectangle(25, 70, 15, 15);
	g2.setColor(Color.RED);
	g2.draw(rect);

	//Test 2: Draw text
	g2.drawString("Message", 50, 100);
	
	//Test 3: Draw line
	ImPoint origin = new ImPoint(30,110);
	ImPoint point1 = new ImPoint(80,110);
	
	Line2D.Double segment = new Line2D.Double(origin.getPoint2D(), point1.getPoint2D());
	*/
	
	for (int i=0; i< num_steps; i++){
	    
	    CurrLoc_pixel = new_Drunkard.getCurrentLoc();//get drunkard's location before taking the step
	    System.out.println("Current loc. (in pixels): "+CurrLoc_pixel);
	    
	    new_Drunkard.takeStep();//new step is taken

	    NewLoc_pixel = new_Drunkard.getCurrentLoc();//get drunkard's location after taking the step
	    System.out.println("New loc. (in pixels): "+NewLoc_pixel);
	
	    Line2D.Double step = new Line2D.Double(CurrLoc_pixel.getPoint2D(), NewLoc_pixel.getPoint2D());
	    
	    //Draw the line segment
	    g2.draw(step);

	}

    }
}
