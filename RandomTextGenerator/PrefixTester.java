// Name: Aravind Bhimarasetty
// USC loginid: bhimaras
// CS 455 PA4
// Fall 2015

import java.util.HashMap;
import java.util.ArrayList;

/**
   This class was used to mainly test the shiftin, equals, and hashCode methods, of the prefix class in order to check immutability and HashMap implementation with prefix objects and list of words as key, value pairs.
**/
public class PrefixTester{

    public static void main(String[] args){

	String[] init_str= new String[]{"", ""};

	Prefix pre1= new Prefix(init_str);

	/**
	   Testing the shiftin method
	*/

	System.out.println("Testing shiftin method...\n");

	System.out.println("PRE1: "+pre1);

	pre1= pre1.shiftin("the");

	System.out.println("PRE1 <- 'the': "+pre1);

	pre1=pre1.shiftin("big");

	System.out.println("PRE1 <- 'big': "+pre1);

	pre1=pre1.shiftin("blue");

	System.out.println("PRE1 <- 'blue': "+pre1);
	
	/**
	   Testing the equals method
	*/
	System.out.println("\nTesting equals method...\n");

	Prefix pre2= new Prefix(new String[]{"the", "big"});

	System.out.println("PRE2: "+pre2);

	System.out.println("PRE1==PRE2: "+(pre1==pre2));

	System.out.println("PRE1.equals(PRE2): "+ pre1.equals(pre2));

	Prefix pre3= new Prefix(new String[]{"big", "blue"});

	System.out.println("PRE3: "+pre3);

	System.out.println("PRE1.equals(PRE3): "+ pre1.equals(pre3));

	/**
	   Testing the HashMap implementation
	*/
	System.out.println("\nTesting HashMap<Prefix, Arraylist> implemtation...\n");

	HashMap<Prefix, ArrayList<String>> hm= new HashMap<Prefix, ArrayList<String>>();

	ArrayList<String> pre1_vals= new ArrayList<String>();
	ArrayList<String> pre2_vals= new ArrayList<String>();

	pre1_vals.add("hat");
	pre2_vals.add("bat");

	System.out.println("PRE1's hashcode: "+ pre1.hashCode());
	System.out.println("PRE2's hashcode: "+ pre2.hashCode());
	System.out.println("PRE3's hashcode: "+ pre3.hashCode());

	
	hm.put(pre1, pre1_vals);
	hm.put(pre2, pre2_vals);

	System.out.println("PRE1 values: "+hm.get(pre1));
	System.out.println("PRE2 values: "+hm.get(pre2));

	hm.get(pre1).add("cat");

	System.out.println("PRE1 values+ 'cat': "+hm.get(pre1));

	System.out.println("Searching HM using PRE3: "+ hm.get(pre3));
	

    }

}
