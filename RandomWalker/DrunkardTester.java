/**
   DrunkardTester class
      This class tests the Drunkard class.
*/
import java.lang.Math;

public class DrunkardTester {
    
    public static void main(String[] args){
	
	int stepsize =1;
	ImPoint CurrLoc = new ImPoint(0,0);
	ImPoint NewLoc = new ImPoint(0,0);
	ImPoint Origin = new ImPoint(0,0);

	boolean valid_step;
	
 	Drunkard new_Drunkard = new Drunkard(Origin, stepsize); //Creating a new instance of Drunkard class with starting loc at origin & step size=1
	
	System.out.println();
	System.out.println("Initialized new Drunkard instance at "+ Origin+ " and stepsize= "+ stepsize +"\n");

	//Test #1
	System.out.println("Test #1: Testing getCurrentLoc method...");
	System.out.println("Current Location of drunkard (Expected: 0, 0): "+ new_Drunkard.getCurrentLoc());
	
	//Test #2
	System.out.println();
	System.out.println("Test #2: Testing takeStep method with 5 random steps...\n");

	for (int i=0;i<5;i++){

	    CurrLoc = new_Drunkard.getCurrentLoc();
	    
	    new_Drunkard.takeStep();

	    NewLoc = new_Drunkard.getCurrentLoc();

	    System.out.println("Location of drunkard after taking a random step of size, "+stepsize+ ":"+ NewLoc);

	    valid_step = check_takeStep(CurrLoc, NewLoc, stepsize);//check if the new location is valid
	    
	    if (valid_step){
		System.out.println("It is a VALID step!");
	    }
	    else{
		    System.out.println("It is an INVALID step!!!");
	    }
	}

	int stepsize2 = 2;
	Drunkard new_Drunkard2 = new Drunkard(Origin, stepsize2);
	
	System.out.println();
	System.out.println("Initialized new Drunkard instance at "+ Origin+ " and stepsize= "+ stepsize2 +"\n");
	
	//Test #3
	System.out.println("Test #3: This test is similar to Test #2, but with a new Drunkard instance started at (0,0), AND stepsize =2...\n");

	for (int i=0;i<5;i++){

	    CurrLoc = new_Drunkard2.getCurrentLoc();
	    
	    new_Drunkard2.takeStep();

	    NewLoc = new_Drunkard2.getCurrentLoc();
	    
	    System.out.println("Location of drunkard after taking a random step of size, "+stepsize2+ ":"+ NewLoc);

	    valid_step = check_takeStep(CurrLoc, NewLoc, stepsize2);//check if the new location is valid
	    
	    if (valid_step){
		System.out.println("It is a VALID step!");
	    }
	    else{
		    System.out.println("It is an INVALID step!!!");
	    }
	}

	/*
	//To check the check_takeStep method by giving some invalid steps...

	valid_step = check_takeStep(new ImPoint(0,1), new ImPoint(1,1), stepsize2);
	System.out.println();
	
	if (valid_step){
	    System.out.println("It is a VALID step!");
	}
	else{
	    System.out.println("It is an INVALID step!!!");
	}
	*/
	
    }

    private static boolean check_takeStep(ImPoint CurrLoc, ImPoint NewLoc, int stepsize){

	int deltax = Math.abs(CurrLoc.getX() - NewLoc.getX());
	int deltay = Math.abs(CurrLoc.getY() - NewLoc.getY());

	if ((deltax+deltay)==stepsize){//the sum of difference in co-ords. must be equal to the stepsize for a valid step.
	    return true;
	}
	else{
	    return false;
	}
	
    }

}
