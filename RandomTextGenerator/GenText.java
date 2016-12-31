// Name: Aravind Bhimarasetty
// USC loginid: bhimaras
// CS 455 PA4
// Fall 2015

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.lang.IllegalArgumentException;
import java.lang.System;

/**
   The GenText class generates random text given an input text file, the prefix length, etc. as command line arguments.
   It contains the main method from which helper methods that do error checking of command line arguments, read the input file and generate random text and, finally, write the random text to the given output file.
**/
public class GenText{

    public static void main(String[] args){

	RandomTextGenerator RTG=null;
	
	String input_filename="";
	String output_filename="";
	
	boolean debug=false;
	
	//the offset variable helps to interpret the command line args. correctly. For e.g. if -d option is used, then arg[1] is prefix length, otherwise it corresponds to arg[0]. Similarly, the indices of other args are offset by +1 when -d option is used.
	int offset=0;
	
	int prefixLength;
	int numWords;

	if (args[0].equals("-d")){
	    debug= true;
	    offset=1;
	}
	
	int err_chk_result= error_check(offset, args);

	if(err_chk_result==1){
	    System.exit(1);
	}

	prefixLength = Integer.parseInt(args[offset]);
	numWords = Integer.parseInt(args[offset+1]);

	input_filename= args[offset+2];
	
	RTG= generateRandomText(input_filename, prefixLength, debug, numWords);

	output_filename= args[offset+3];

	writeOutputFile(output_filename, RTG);
	    
    }
    /**
       The error_check method takes offset and command line args. as input and checks for missing command line arguments, that prefixLength & numWords are integers and have correct range of values.
       It returns 1 if there's any error, otherwise 0.
    **/
    private static int error_check(int offset, String[] args){
	
	int prefixLength=0;
	int numWords=-1;
	
	if(args.length < (4+offset)){
	    System.err.println("ERROR: missing command line arguments.");
	    System.err.println("Usage: java GenText [-d] prefixLength numWords sourceFile outFile");
	    return 1;
	}
	else{
	    try{
		prefixLength = Integer.parseInt(args[offset]);
		numWords = Integer.parseInt(args[offset+1]);
	    }
	    catch(NumberFormatException e){
		System.err.println("ERROR: Arguments " + args[offset]+ " and "+ args[offset+1] + " must be integers.");
		System.err.println("Usage: java GenText [-d] prefixLength numWords sourceFile outFile");
		return 1;

	    }

	}
	
	if( (prefixLength<1) || (numWords<0)){
	    System.err.println("ERROR: Invalid input arguments.");
	    System.err.println("Usage: java GenText [-d] prefixLength numWords sourceFile outFile");
	    System.err.println("numWords must be >= 0 and prefixLength must be >= 1");
	    return 1;
	}

	return 0;
    }
    /**
       The generateRandomText method takes the name of the input file, prefixLength, debug mode, and number of words to generate as inputs and returns a RandomTextGenerator object with the random text.
       If it cannot open input file, then it throws a FileNotFoundException.
     **/
    private static RandomTextGenerator generateRandomText(String in_file, int prefixLength, boolean debug, int numWords){

	Scanner sc= null;
	RandomTextGenerator RTG=null;
	
	try{
	    sc = new Scanner(new File(in_file));

	    RTG = new RandomTextGenerator(sc, prefixLength, debug, numWords); 

	    /*
	      System.out.println("Printing list of words...");
	      RTG.printListOfWords();
	    
	      System.out.println("Printing HashMap entries (Key, Value pairs)...");
	      RTG.printHM();
	    */
	    
	    RTG.generateText();
	    
	}
	catch(FileNotFoundException e){
	    System.err.println("ERROR: File not found: "+ in_file);
	}
	catch(IllegalArgumentException e){
	    System.out.println(e.getMessage());
	}
	finally{
	    if (sc != null){
		sc.close();
	    }
	}
	return RTG;
    }
    /**
       The writeOutputFile writes the random words stored in the given RandomTextGenerator object called RTG to the given output file.
       It throws a FileNotFoundException if the output file cannot be opened.
     **/
    private static void writeOutputFile(String output_filename, RandomTextGenerator RTG){

	PrintWriter out= null;
	
	try{
	    out = new PrintWriter(output_filename);
	    
	    if (RTG!=null){
		RTG.writeWordsToFile(out);
	    }
	}
	catch(FileNotFoundException e){
	    System.err.println("ERROR: Could not open file: "+ output_filename);
	}
	finally{
	    if (out != null){
		out.close();
	    }
	}
    }
}
