// Name: Aravind Bhimarasetty
// USC loginid: bhimaras
// CS 455 PA1
// Fall 2015

/**
   Drunkard class
       Represents a "drunkard" doing a random walk on a grid.
*/

import java.util.Random;

public class Drunkard {

    /**
       Creates drunkard with given starting location and distance
       to move in a single step.
       @param startLoc starting location of drunkard
       @param theStepSize size of one step in the random walk
    */
    public Drunkard(ImPoint startLoc, int theStepSize) {

	this.CurrentLoc = startLoc;
	this.Stepsize = theStepSize;
	this.RandomStep = new Random();
    }


    /**
       Takes a step of length step-size (see constructor) in one of
       the four compass directions.  Changes the current location of the
       drunkard.
    */
    public void takeStep() {
	
	dir = RandomStep.nextInt(4); //direction of the drunkard's step is generated randomly
	//System.out.println("DEBUG: dir= "+dir);

        //Updating the current location of drunkard (depending on the direction of the step) below

	switch(dir){
	    
	case 0: 
	    CurrentLoc=CurrentLoc.translate(Stepsize,0); //positive x dir./ East
	    break;
	case 1: 
	    CurrentLoc=CurrentLoc.translate(0, Stepsize); //positive y dir./South
	    break;
	case 2: 
	    CurrentLoc=CurrentLoc.translate(-Stepsize, 0); //neg. x dir./ West
	    break;
	case 3: 
	    CurrentLoc=CurrentLoc.translate(0, -Stepsize); //neg. y dir./ North
	    break;
	}
	return;
    }


    /**
       gets the current location of the drunkard.
       @return an ImPoint object representing drunkard's current location
    */
    public ImPoint getCurrentLoc() {
	return CurrentLoc;
    }

    private ImPoint CurrentLoc;
    private int Stepsize;
    private Random RandomStep;
    private int dir;
}
