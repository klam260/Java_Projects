import java.util.Scanner;
import java.util.Iterator;
/**
 * COMP 2503 Assignment 4 Main class
 * @author Namrata Khemka-Dolan
 * Modified By: Rafael Angelo Pucut
 * Date: March 28, 2018
 * 
 * This program reads a text file and prints out lists of words
 * that appeared more frequent and less frequent than others, as well as count
 * the total amount of words, stop words and unique words.
 * 
 * It also prints out all the words without the stop words in alphabetical order,
 * determines the longest word, word length average and the actual and optimal heights 
 * of the trees created in the program.
 *  
 * Words from a standard list of stop words are not included.
 */
public class A4 {

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
		         "man", "woman", "would", "should", "dont", "after", "before", "im", "men"
	};
	
	   private BST<Word> words = new BST<Word>();
	   private BST<Word> freqWords = new BST<Word>();
	   private BST<Word> lengthWords = new BST<Word>();

	   private int totalwordcount = 0;

	   private int stopwordcount = 0;

	   private int averageLength = 0;
	   
	   private Scanner inp = new Scanner( System.in);
	   
	   /**
	    * Runs the whole sorting program
	    * @param args
	    */
	   public static void main( String[ ] args) 
	   {	
	      A4 a4 = new A4();
	      a4.run();
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
		   removeStopWords();
		   System.out.println("Stop Words: " + stopwordcount);
		   System.out.println("Unique Words: " + words.size());
		   System.out.println("\n------\n");
		   System.out.println("20 Most Frequent");
		   BSTFrequency();
		   freqPrint(freqWords);
		   System.out.println("\n------\n");
		   System.out.println("20 Least Frequent");
		   printBSTLeast(freqWords);
		   System.out.println("\n------\n");
		   System.out.print("The longest word is ");
		   BSTLength();
		   if (lengthWords.size() == 0)
		   {
			   System.out.println("null");
		   }
		   else
		   {
			   standardPrint(lengthWords, 1);
		   }
		   System.out.println("The average word length is " + averageLength);
		   System.out.println("\n------\n");
		   System.out.println("All Words");   
		   standardPrint(words, words.size());
		   System.out.println("\n------\n");
		   System.out.println("Alphabetic Tree: ( Optimum Height: " + calcLog(words) +") ( Actual Height: " + words.height() + ")");
		   System.out.println("Frequency Tree: ( Optimum Height: " + calcLog(freqWords) + ") ( Actual Height: " +freqWords.height() + ")");
		   System.out.println("Length Tree: ( Optimum Height: " + calcLog(lengthWords) +") ( Actual Height: " + lengthWords.height() +")");
		   System.out.println("\n------\n");
	   }
	   
	   /**
	    * Calculates the optimum height of the BST
	    * @param calc is the selected BST
	    * @return logAnswer is the optimum height of the selected BST
	    */
	   public int calcLog(BST calc)
	   {
		   int logAnswer = (int)Math.round((Math.log(calc.size())/Math.log(2)));
		   if(logAnswer < 0)
		   {
			   logAnswer = 0;
		   }
		   return logAnswer;
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
	            if ( w.equals(words.find(w)))
	            {
	               words.find(w).incrCount();
	            }
	            else 
	            {  
	               words.add(w);
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
			   if (w.equals(words.find(w)))
			   {
				   words.delete(w);
				   stopwordcount++;
			   }
			   
			   counter++;
		   }
	   }
	   
	   /**
	    * Takes in a BST and prints the elements within it
	    * @param print is the BST chosen to print
	    * @param printingSize is the amount of elements to be printed
	    */
	   public void standardPrint(BST print, int printingSize)
	   {
		   Iterator<Word> itr = print.iterator();
		   int counter = 0;
		      while (itr.hasNext() && counter < printingSize)
		      {
		    	  Word element = itr.next();
		    	  System.out.println(element);
		    	  counter++;
		      }
	   }
	   
	   /**
	    * Takes in words from the words BST 
	    * and orders them according to frequency using 
	    * a comparator
	    */
	   public void BSTFrequency()
	   {
		   Iterator<Word> fItr = words.iterator();
		   while(fItr.hasNext())
		   {
			   Word freqAdd = fItr.next();
			   if (freqAdd.getCount() > 2)
			   {
				   freqWords.addFreqOrder(freqAdd, freqAdd.CompFreqDesc);
			   }
		   }
	   }
	   
	   /**
	    * Takes in words from the words BST
	    * and orders them according to the length
	    * of the words using a comparator
	    * and also calculates the average length
	    * of all the words taken in by the new BST
	    */
	   public void BSTLength()
	   {
		   Iterator<Word> lItr = words.iterator();
		   while(lItr.hasNext())
		   {
			   Word lengthAdd = lItr.next();
			   lengthWords.addFreqOrder(lengthAdd, lengthAdd.CompWLength);
			   int length = lengthAdd.getWord().length();
			   averageLength += length;
		   }
		   if(averageLength > 0)
		   {
			   averageLength /= lengthWords.size();
		   }
	   }
	   
	   /**
	    * Prints out the most frequent word list
	    * @param print is the BST containing words with the most frequent ordering
	    */
	   public void freqPrint(BST print)
	   {
		   Iterator<Word> itr = print.iterator();
		   int counter = 0;
		      while (itr.hasNext() && counter < 20)
		      {
		    	  Word element = itr.next();
		    	  System.out.println(element);
		    	  counter++;
		      }
	   }
	   
	   /**
	    * Prints out the least frequent word list
	    * through the use of a Stack to manipulate 
	    * the most frequent BST 
	    * @param leastTree is the selected frequency BST list
	    */
	   public void printBSTLeast(BST leastTree)
	   {
		   Stack<Word> stack = new Stack<Word>();
		   Iterator<Word> leastItr = leastTree.iterator();
		   int count = 0;
		   while(leastItr.hasNext())
		   {
			   Word temp = leastItr.next();
			   stack.push(temp);
		   }
		   while(!stack.isEmpty() && count < 20)
		   {
			   System.out.println(stack.pop());
			   count++;
		   }
	   }
	   
	   /**
	    * Runs the program. Reads the file, then prints the results.
	    */
	   public void run() 
	   {
	      readFile();
	      printResults();
	   }
}
