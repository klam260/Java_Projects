import java.util.Scanner;
import java.util.Iterator;
import java.util.Comparator;
import java.util.*;
/**
 * COMP 2503 Assignment 5 Main class
 * @author Namrata Khemka-Dolan
 * Modified By: Rafael Angelo Pucut
 * Date: April 4, 2018
 * 
 * This program reads a text file and prints out lists of words
 * that appeared more frequent and less frequent than others, as well as count
 * the total amount of words, stop words and unique words.
 * 
 * It also prints out all the words without the stop words in alphabetical order,
 * prints out statistics for word frequencies as well as word length.
 *  
 * Words from a standard list of stop words are not included.
 */
public class A5 {

	   private String[] stopwords = { 
		         "a", "about",  "all", "am", "an", "and",  "any", "are", "as", "at", 
		         "be", "been", "but", "by", "can", "cannot", "could", "did", "do", "does", 
		         "else", "for", "from", "get", "got", "had", "has", "have", "he", "her", 
		         "hers", "him", "his", "how", "i", "if", "in", "into", "is", "it", 
		         "its", "like", "more", "me", "my", "no", "now", "not", "of", "on", 
		         "one", "or", "our", "out", "said", "say", "says", "she", "so", "some",
		         "than", "that", "thats", "the", "their", "them", "then", "there", "these", "they", "this", 
		         "to", "too", "us", "upon", "was", "we", "were", "what", "with", "when", 
		         "where", "which", "while", "who", "whom", "why", "will", "you", "your", "up", "down", "left", "right",
		         "man", "woman", "would", "should", "dont", "after", "before", "im", "men" };
	
	   private HashMap<String, Word> words = new HashMap<String, Word>();
	   private TreeMap<Word, Word> processWords = new TreeMap<Word, Word>();
	   private TreeMap<Word, Word> mostLeastWords = new TreeMap<Word, Word>(Word.CompFreqDesc);
	   private TreeMap<Word, Word> lengthWords = new TreeMap<Word, Word>(Word.CompWLength);

	   private int totalwordcount = 0;
	   private int stopwordcount = 0;
	   
	   private Scanner inp = new Scanner( System.in);
	   
	   /**
	    * Runs the whole sorting program
	    * @param args
	    */
	   public static void main( String[ ] args) 
	   {	
	      A5 a5 = new A5();
	      a5.run();
	   }

	   /**
	    * Check if the word given is in the stopword list
	    * @param w a word 
	    * @return true if w is a stopword, false otherwise
	    */
	   private boolean stopword( String w) 
	   {
	      boolean found = false;
	      int i = 0;
	      while (i < stopwords.length && !found) 
	      {
	         if ( w.equals( stopwords[i])) 
	            found = true;
	         i++;
	      }
	      return found;
	   }
	   
	   /**
	    * Prints out the output for the lists
	    */
	   private void printResults() 
	   {
		   System.out.println("\n------\n");
		   System.out.println("Total Words: " + totalwordcount);
		   System.out.println("Stop Words: " + stopwordcount);
		   System.out.println("Unique Words: " + words.size());
		   System.out.println("\n------\n");
		   System.out.println("20 Most Frequent");
		   standardPrint(mostLeastWords, 20);
		   System.out.println("\n------\n");
		   System.out.println("Statistics for Word Frequencies");
		   System.out.print("The most frequent word is ");
		   standardPrint(mostLeastWords, 1);
		   System.out.print("The least frequent word is ");
		   leastMostTree(mostLeastWords, 1);
		   System.out.println("The average word frequency is " + frequencyAverageCalc(processWords));
		   System.out.println("\n------\n");
		   System.out.println("Statistics for Word Length");
		   System.out.print("The longest word is ");
		   standardPrint(lengthWords, 1);
		   System.out.print("The shortest word is ");
		   leastMostTree(lengthWords, 1);
		   System.out.println("The average word length is " + lengthAverageCalc(lengthWords));
		   System.out.println("\n------\n");
		   System.out.println("All Words");
		   standardPrint(processWords, processWords.size());
		   System.out.println("\n------\n");
	   }
	   
	   /**
	    * Read the input file and process it.
	    * Input is on standard input and is read one
	    * word at a time -- separated by whitespace.
	    * 
	    * All non alphabetic characters are stripped out 
	    * and words are all converted to lower case.
	    * 
	    * Any non-stopword word is stored in the list of words 
	    * and the number of times it occurred is tracked. 
	    */
	   private void readFile() 
	   {

	      while ( inp.hasNext()) 
	      {
	         String word = inp.next().toLowerCase().trim().replaceAll( "[^a-z]","");

	         if ( word.length() > 0) 
	         { 
	            totalwordcount++;
	            Word w = new Word( word);
	            w.incrCount();
	            if ( w.equals(words.get(word)))
	            {
	               words.get(word).incrCount();
	            }
	            else 
	            {  
	               words.put(word, w);
	            }
	            
	         }
	      }
	   }
	   
	   /**
	    * Removes the stop words in the tree as well
	    * as count the number of unique stop words
	    */
	   private void removeStopWords()
	   {
		   int counter = 0;
		   while(counter < stopwords.length)
		   {
			   Word w = new Word(stopwords[counter]);
			   if (w.equals(words.get(stopwords[counter])))
			   {
				   words.remove(stopwords[counter]);
				   stopwordcount++;
			   }
			   
			   counter++;
		   }
	   }
	   
	   /**
	    * Takes in a TreeMap and prints the elements within it
	    * @param print is the TreeMap chosen to print
	    * @param printingSize is the amount of elements to be printed
	    */
	   private void standardPrint(TreeMap<Word,Word> print, int printingSize)
	   {
		   Set pSet = print.entrySet();
		   Iterator itr = pSet.iterator();
		   int counter = 0;
		      while (itr.hasNext() && counter < printingSize)
		      {
		    	  Map.Entry stanPrint = (Map.Entry)itr.next();
		    	  System.out.println(stanPrint.getValue());
		    	  counter++;
		      }
	   }
	   
	   /**
	    * This method sets up the three trees that need to 
	    * be created in order to place the values from the hashMap
	    * onto them and manipulate them through these trees
	    */
	   private void setUpTrees() 
	   {
		   Iterator<Word> itr = words.values().iterator();
		   
		   while(itr.hasNext())
		   {
			   //To use the comparator of the Word class,
			   //the key of the trees must also be of Word type in order to function
			   Word data = itr.next();
			   Word position = data;
			   processWords.put(position, data);
			   mostLeastWords.put(position, data);
			   lengthWords.put(position, data);
		   }
	   }
	   
	   /**
	    * Rearranges the printing of a selected tree into a 
	    * reverse order through the use of a stack
	    * @param least is the tree selected to be rearranged
	    * @param amountPrinted is the amount of words to be printed
	    */
	   private void leastMostTree(TreeMap<Word, Word> least, int amountPrinted)
	   {
		   Stack<Word> transfer = new Stack<Word>();
		   Iterator<Word> leastItr = least.values().iterator();
		   int counter = 0;
		   //first loop pushes words into the stack
		   while(leastItr.hasNext())
		   {
			   Word toStack = leastItr.next();
			   transfer.push(toStack);
		   }
		   //second loop prints out the values in the stack by
		   //popping them resulting in a reverse order
		   while(!transfer.isEmpty() && counter < amountPrinted)
		   {
			   System.out.println(transfer.pop());
			   counter++;
		   }
	   }
	   
	   /**
	    * Calculates the average frequency of words within the tree
	    * @param treeCalc is the tree selected to determine avg word frequency
	    * @return the average word frequency of the selected tree
	    */
	   private int frequencyAverageCalc(TreeMap<Word, Word> treeCalc)
	   {
		   Iterator<Word> freqItr = treeCalc.values().iterator();
		   int totalFrequency = 0; 
		   
		   if (treeCalc.size() == 0)
		   {
			   return 0;
		   }
		   while(freqItr.hasNext())
		   {
			   //sums up the total word frequency of the tree by
			   //incrementing each word's frequency to the totalFrequency variable
			   Word following = freqItr.next();
			   totalFrequency += following.getCount();
		   }
		   return totalFrequency/treeCalc.size();
	   }
	   
	   /**
	    * Calculates the average length of words within the tree
	    * @param treeCalc is the tree selected to determine avg word length
	    * @return the average word length of the selected tree
	    */
	   private int lengthAverageCalc(TreeMap<Word, Word> treeCalc)
	   {
		   Iterator<Word> lengthItr = treeCalc.values().iterator();
		   int totalLength = 0; 
		   
		   if (treeCalc.size() == 0)
		   {
			   return 0;
		   }
		   while(lengthItr.hasNext())
		   {
			   //sums up the total word length of the tree by
			   //incrementing each word's length to the totalLength variable
			   Word following = lengthItr.next();
			   totalLength += following.getWord().length();
		   }
		   return totalLength/treeCalc.size();
	   }
	   
	   /**
	    * Runs the program. Reads the file, removes the stop words, sets up the three trees needed and then prints the results.
	    */
	   public void run() 
	   {
	      readFile();
	      removeStopWords();
	      setUpTrees();
	      printResults();
	   }
}
