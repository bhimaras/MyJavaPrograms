// Name: Aravind Bhimarasetty
// USC loginid: 1991939192
// CSCI455 PA2
// Fall 2015


/**
   This program does a Bulgarian Solitaire Simulation.
   It has two modes (-u and -s) as well:

   -u
   Prompts for the initial configuration from the user, instead of generating a random configuration.

   -s
   Stops between every round of the game. The game only continues when the user hits enter (a.k.a., return). 

   Usage (after compiling): 
   1. >> java BulgarianSolitaire -u
   2. >> java BulgarianSolitaire -s
   3. >> java BulgarianSolitaire -u -s
   1. >> java BulgarianSolitaire

 */

import java.util.Scanner;

public class BulgarianSolitaireSimulator {

   public static void main(String[] args) {
     
      boolean singleStep = false;
      boolean userConfig = false;
      SolitaireBoard SB_obj;
      int num_rounds=0;
      Scanner sc = new Scanner(System.in);
      String newline_string="";
      
      for (int i = 0; i < args.length; i++) {
         if (args[i].equals("-u")) {
            userConfig = true;
         }
         else if (args[i].equals("-s")) {
            singleStep = true;
         }
      }

      // <add code here>
      
      SB_obj = createNewBoard(userConfig);// create new instance of the board

      while(!SB_obj.isDone()){
	  SB_obj.playRound(); //play rounds
	  System.out.println("["+(num_rounds+1)+"]"+" Current configuration: "+SB_obj.configString());
	  num_rounds++;

	  if (singleStep){//check if program is run using the -s cmd line arg.

	      newline_string="notempty";
	      
	      while(newline_string.length() !=0){
		  if(sc.hasNextLine()){
		      newline_string= sc.nextLine();
		  }

	      }
	  }
			  
      }
      System.out.println("Done!");
   }



    
   
    // <add private static methods here>
    /**
    This function prompts user for initial configuration until a valid configuration is given and returns the same.
    */
    private static String validUserInput(){

	String config_string_line = "";
	boolean invalid_user_input= true;

	System.out.println("Total number of cards: "+SolitaireBoard.CARD_TOTAL);
	System.out.println("You will be entering the initial configuration of the cards (i.e., how many in each pile).");
	  System.out.println("Please enter a space-separated list of positive integers followed by newline:");
	  
	Scanner sc = new Scanner(System.in);

	if(sc.hasNextLine()){
	    
	    config_string_line= sc.nextLine();//reads user input for the first time
	}

	invalid_user_input = !SolitaireBoard.isValidConfigString(config_string_line);

	while(invalid_user_input){//user is prompted till a correct initial config. is given

	    System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be "+SolitaireBoard.CARD_TOTAL);
	    System.out.println("Please enter a space-separated list of positive integers followed by newline:");

	    Scanner sc1 = new Scanner(System.in);
	      
	    if(sc1.hasNextLine()){
		
		config_string_line= sc1.nextLine();
	    }

	    invalid_user_input = !SolitaireBoard.isValidConfigString(config_string_line);

	}
	return config_string_line;
    }

    /**
    The createNewBoard function creates a new SolitaireBoard object and returns its pointer based on the userConfig flag.
     */
    private static SolitaireBoard createNewBoard(boolean userConfig_flag){

	String valid_user_config_string = "";
	SolitaireBoard SB;
	
	if (userConfig_flag){//if the program is called with -u cmd line arg. or not

	    valid_user_config_string = validUserInput();
	    SB = new SolitaireBoard(valid_user_config_string);	  
	}
	else{
	    
	    SB = new SolitaireBoard();//if program is not called with -u cmd line arg. then create SB with random initial config.
	}

	return SB;
	
    }
  
}
