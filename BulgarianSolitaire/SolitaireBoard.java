// Name: Aravind Bhimarasetty
// USC loginid: 1991939192
// CSCI455 PA2
// Fall 2015



/*
   class SolitaireBoard
   The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
   by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
   for CARD_TOTAL that result in a game that terminates.
   (See comments below next to named constant declarations for more details on this.)
 */

import java.util.Random;
import java.util.Scanner;

public class SolitaireBoard {
   
   public static final int NUM_FINAL_PILES = 9;
   // number of piles in a final configuration
   // (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)
   
   public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
   // bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
   // see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
   // the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES
   
   
   /**
      Representation invariants:
      
      1. Number of piles should be greater than zero.
      2. Card piles should be strictly positive integers.
      3. Sum of all cards in all piles should always be equal to CARD_TOTAL.

    */
   
    /**
       Two instance variables are used:
       1. card_piles: An array of max. size = CARD_TOTAL+1 to store the various card piles.
       2. num_piles: Number of non-zero card piles on the board

     */
    
    int[] card_piles = new int[CARD_TOTAL+1];
    int num_piles=0;
 
   /**
     Initialize the board with the given configuration.
     PRE: SolitaireBoard.isValidConfigString(numberString)
   */
   public SolitaireBoard(String numberString) {

       assert isValidConfigString(numberString);

       int i=0;
       Scanner sc = new Scanner(numberString);

       while(sc.hasNextInt()){

	   card_piles[i]= sc.nextInt();//integer values corresponding to the card piles being extracted from the string config.
	   i++;
		 
       }
       num_piles=i;
       
       assert isValidSolitaireBoard();  

       System.out.println("Initial configuration: "+configString());

   }
 
   
   /**
      Create a random initial configuration.
   */
   public SolitaireBoard() {

       Random rg_for_num_piles= new Random();
       int sum_cards=0;
       int i=0;
       
       while(sum_cards != CARD_TOTAL)
	   {

	       card_piles[i]= rg_for_num_piles.nextInt(CARD_TOTAL- sum_cards)+1;

	       sum_cards+= card_piles[i];

	       i++;

	   }
       num_piles =i;

       assert isValidSolitaireBoard();
       
       System.out.println("Initial configuration: "+configString());

   }
  
   
   /**
      Play one round of Bulgarian solitaire.  Updates the configuration according to the rules of Bulgarian
      solitaire: Takes one card from each pile, and puts them all together in a new pile.
    */
   public void playRound() {

       assert isValidSolitaireBoard();

       for(int i=0;i<num_piles;i++){
	   card_piles[i]--;//reduce no. of cards by one from each of the existing piles
       }
       
       card_piles[num_piles]=num_piles;//add the total cards removed from the piles at the end
       num_piles++;

       removeZeros();
       //System.out.println("DEBUG: Config. after playing 1 round: "+ configString());
       assert isValidSolitaireBoard();
   }
   
   /**
      Return true iff the current board is at the end of the game.  That is, there are NUM_FINAL_PILES
      piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES, in any order.
    */
   
   public boolean isDone() {

       if(num_piles==NUM_FINAL_PILES){//num. of piles should match final no. of piles
	   boolean[] isNumPresent = new boolean[NUM_FINAL_PILES];

	   for(int i=0; i<num_piles; i++){
	       if(card_piles[i]>NUM_FINAL_PILES){//no good if the value is greater than the largest final piles value (=NUM_FINAL_PILES)
		   return false;
	       }
	       else{
		   if(isNumPresent[card_piles[i]-1]){//no good if any value is repeated as final piles values are all unique
		       return false;
		   }
		   else{
		       isNumPresent[card_piles[i]-1]=true;//if we are seeing this number for the first time then set the corresponding entry as true
		      
		   }
	       }
	   }
	   return true; //if it passed all the above checks, then we have the final pile!

	   
       }
       else{//no good if num of piles is not equal to NUM_FINAL_PILES
	   return false;
       }
      
   }

   
   /**
      Returns current board configuration as a string with the format of
      a space-separated list of numbers with no leading or trailing spaces.
      The numbers represent the number of cards in each non-empty pile.
    */
   public String configString() {

	int sum_cards=0;
	String board_config="";
	
	for(int i=0; i<num_piles;i++){

	    board_config+= card_piles[i]+" ";
	    
	    sum_cards+= card_piles[i];
	}

	board_config = board_config.trim();

	/*
	System.out.println("\nDEBUG:Sum of cards= "+ sum_cards);

	System.out.print("\nDEBUG: card_piles_array: ");
	
	for(int i=0; i<CARD_TOTAL;i++){

	    System.out.print(card_piles[i]+ " ");
	    
	}	
	*/
	
	return board_config;  
   }
   
   
   /**
      Returns true iff configString is a space-separated list of numbers that
      is a valid Bulgarian solitaire board assuming the card total SolitaireBoard.CARD_TOTAL
   */
   public static boolean isValidConfigString(String configString) {

       Scanner sc = new Scanner(configString);
       int[] config_piles = new int[SolitaireBoard.CARD_TOTAL];
       int i=0;
       int num_config_piles=0;
       
       while(sc.hasNext()){
	   if(sc.hasNextInt()){
	       config_piles[i]= sc.nextInt();
	       i++;
	   }
	   else{
	       return false; //the pile has non-integer value
	   }
				 
       }

       num_config_piles=i;

       if (isNumPilesValid(num_config_piles)&& isEachCardPileValid(config_piles, num_config_piles) && isSumCardsCorrect(config_piles, num_config_piles, SolitaireBoard.CARD_TOTAL)){
	   return true;//the config is valid only when all rep. inv. criteria are met
       }
       else {
	   return false;  
       }
       
   }


   /**
      Returns true iff the solitaire board data is in a valid state
      (See representation invariant comment for more details.)
    */
   private boolean isValidSolitaireBoard() {

       if (isNumPilesValid(num_piles)&& isEachCardPileValid(card_piles, num_piles) && isSumCardsCorrect(card_piles, num_piles, CARD_TOTAL)){
	   return true;//the config is valid only when all rep. inv. criteria are met
       }
       else {
	   return false;
       }

   }
   

    // <add any additional private methods here>
    
    /**
    The isNumPilesValid function checks if total num of non-zero-card piles > 0 (see rep. invariant #1 at the top).
    */
    private static boolean isNumPilesValid(int num_piles){

	if (num_piles>0) {
	    //	    System.out.print("\nDEBUG: isNumPilesValid is TRUE.");
	    return true;
	}
	else {
	    //	    System.out.print("\nDEBUG: isNumPilesValid is FALSE!!");
	    return false;
	}

    }

    /**
    The isEachCardPileValid funtion checks if each pile in the given array of piles has positive no. of cards (see rep. invariant #2 at the top).
    */
    private static boolean isEachCardPileValid(int[] array_piles, int size){

	for (int i=0; i<size; i++){
	    if (array_piles[i] <= 0){
		//		System.out.print("\nDEBUG: isEachCardPileValid is FALSE!!");
		return false;
	    }
	}
	//	System.out.print("\nDEBUG: isEachCardPileValid is TRUE.");
	return true;
    }

    /**
    The isSumCardsCorrect function checks if the sum of cards in the given array of piles is equal to CARD_TOTAL (see rep. invariant #3 at the top).
    */
    private static boolean isSumCardsCorrect(int[] array_piles, int size, int correct_sum){

       int sum_cards= 0;
       
       for(int i=0;i<size;i++) {

	   sum_cards+= array_piles[i];
       }

       if (sum_cards==correct_sum) {
	   //	   System.out.print("\nDEBUG: isSumCardsCorrect is TRUE.");
	   return true;
       }
       else {
	   //	   System.out.print("\nDEBUG: isSumCardsCorrect is FALSE!!");
	   return false; 
       }
    }

    /**
    The shiftLeft function shifts the elements of the card_piles array to the left from a given starting position and also decrements the number of piles.
    */

    private void shiftLeft(int start_pos){

	for(int i=start_pos;i<num_piles;i++){
	    card_piles[i]=card_piles[i+1];
	}
	num_piles--;
    }

    /**
    The scanZeros function looks into the card_piles array starting from a given position and returns the position of the first in-between zero that it finds.
    It returns -1 if could not find any zeros.
    */

    private int scanZeros(int start_pos){

	for(int i=start_pos; i<num_piles;i++){
	    if(card_piles[i]==0){
		return i;
	    }
	}
	return -1;
		
    }
    /**
    The removeZeros function removes in-between zeros in card_piles with the help of the shiftLeft & scanZeros functions. 
    */
    private void removeZeros(){

	int starting_pos_zero=scanZeros(0);//start by checking from 1st elem.
	
	while(starting_pos_zero != -1){
	    shiftLeft(starting_pos_zero);
	    starting_pos_zero= scanZeros(starting_pos_zero);//check for zeros from the position where the last zero was seen. 
	}
    }
    
}
