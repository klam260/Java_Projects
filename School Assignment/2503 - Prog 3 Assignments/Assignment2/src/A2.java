import java.util.*;import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

/** 
 * COMP 2503 Winter 2018 Assignment 2 
 * @author Namrata Khemka-Dolan
 * Modified By: Rafael Angelo Pucut
 * 
 * This program reads a text file and compiles a list of the 
 * words together with how many each word appears. 
 *
 * Words from a standard list of stop words are not included.
*/
public class A2 {

	   private SLL<Word> words = new SLL<Word>();
	   private SLL<Word> ascendingWords = new SLL<Word>();
	   private SLL<Word> descendingWords = new SLL<Word>();
	
	   private String[] stopwords = { "a", "about", "all", "am", "an", 
	         "and", "any", "are", "as", "at", "be", "been", "but", "by", "can", 
	         "cannot", "could", "did", "do", "does", "else", "for", "from", 
	         "get", "got", "had", "has", "have", "he", "her", "hers", "him", 
	         "his", "how", "i", "if", "in", "into", "is", "it", "its", "like", 
	         "more", "me", "my", "no", "now", "not", "of", "on", "one", 
	         "or", "our", "out", "said", "say", "says", "she", "so", "some",
	         "than", "that", "the", "their", "them", "then", "there", "these", 
	         "they", "this", "to", "too", "us", "upon", "was", "we", "were", 
	         "what", "with", "when", "where", "which", "while", "who", 
	          "whom", "why", 
	         "will", "you", "your"};

	   private int totalwordcount = 0;

	   private int stopwordcount = 0;

	   private Scanner inp = new Scanner( System.in);
	   
	   /**
	    * Runs the whole sorting program
	    * @param args
	    */
	   public static void main( String[ ] args) 
	   {	
	      A2 a2 = new A2();
	      a2.run();
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
	    * List the first n words in the list of words.
	    * @param printing the linked list selected to print
	    * @param n the number of words to list
	    */
	   private void printWords(SLL<Word> printing, int n) 
	   {
	      int i = 0;
	      while ( i < printing.size() && i < n) 
	      {
	         System.out.println( printing.get(i));
	         i++;
	      }
	   }
	   
	   /**
	    * Adds the words in 2 linked list and arranges them in
	    * the most frequent and less frequent order. 
	    */
	   public void mostLeastLists()
	   {
		   for(int count = 0; count < words.size(); count++)
		   {
			   ascendingWords.ascendSort(words.get(count));
			   descendingWords.descendSort(words.get(count));
		   }
	   }
	   
	   /**
	    * Prints out the output for the lists
	    */
	   private void printResults() 
	   {
		   mostLeastLists();
	       System.out.println( "Total Words: " + totalwordcount);
	       System.out.println( "Unique Words: " + words.size()); 
	       System.out.println( "Stop Words: " + stopwordcount);
	       System.out.println();
	       System.out.println( "10 Most Frequent");
	       printWords(descendingWords,10); 
	       System.out.println();
	       System.out.println( "10 Least Frequent"); 
	       printWords(ascendingWords,10);
	       System.out.println();
	       System.out.println( "All");
	       printWords( words,words.size());
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
	            if (stopword( word)) 
	            {
	               stopwordcount++;
	            }
	            else
	            {
	               Word w = new Word( word);
	               w.incrCount();
	               Node<Word> sweep = new Node(w);
	               if ( words.contains(sweep))
	               {
	                  words.get( words.indexOf(sweep)).incrCount();
	               }
	               else 
	               {  
	                  words.addInOrder(sweep);
	               }
	            }
	         }
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