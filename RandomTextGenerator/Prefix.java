// Name: Aravind Bhimarasetty
// USC loginid: bhimaras
// CS 455 PA4
// Fall 2015

/**
   The Prefix class is a multi-word container. The class is also immutable.
   Equals and hashCode methods have been overriden. 
**/
public class Prefix{
    /*
      A string array datastrucuture is used to store the words of the Prefix object in order.
     */
    private String[] words;
    private int prefix_len;
    /*
      The contructor takes as input a string array of words. The prefix length is then equal to the length of the this input array.
     */
    public Prefix(String[] words_in){

	prefix_len = words_in.length;
	words = new String[prefix_len];

	for (int i=0; i<prefix_len;i++){
	    words[i]= words_in[i];
	}
	

    }

    public String[] getStringArr(){
	return words;
    }
    /**
       The shiftin method pushes the words to the left and inserts the given word at the end. 
       It returns a new Prefix object of the same size with the new sequence of words.
    **/
    public Prefix shiftin(String new_word){

	String[] new_words= new String[prefix_len];

	for(int i=0; i<prefix_len-1;i++){
	    new_words[i]= words[i+1];
	}

	new_words[prefix_len-1]= new_word;

	Prefix new_prefix = new Prefix(new_words);

	return new_prefix;//by returning a new prefix, the prefix class is made immutable
    }
    /**
       The toString method prints out the words in the prefix in order, separated by commas.
    **/
    @Override
    public String toString(){

	String result="";

	for (int i=0; i<prefix_len-1;i++){
	    result+= words[i]+ ", ";
	}

	result+= words[prefix_len-1];

	return result;
    }
    /**
       The equals method is overriden here. Two prefixes are equal if and only if they contain the same sequence of words.
     **/
    @Override
    public boolean equals(Object o){

	if (!(o instanceof Prefix)){
	    return false;//return false if the object that's being compared is not a prefix object type
	}
	
	Prefix b = (Prefix)o;

	String[] b_words = b.getStringArr();

	if (prefix_len != b_words.length){
	    return false; //return false if the prefix lengths are different
	}
	for (int i=0; i<prefix_len;i++){
	    if (!(words[i].equals(b_words[i]))){
		return false;//return false if the ith word of the current prefix does not equal the ith word of the prefix that's being compared to
	    }
	    
	}
	return true;
    }

    /**
       The hashCode method is overidden here. The hashCode for a Prefix object corresponds to the hashCode of the string formed by concatenating the words of the prefix.
     **/
    @Override
    public int hashCode(){

	String concatWords= "";

	for (int i=0; i< prefix_len; i++){

	    concatWords += words[i];
	}

	return concatWords.hashCode();
    }

}


	

	
