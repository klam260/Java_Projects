import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.io.InputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
/*
 * COMP 2503 Assignment 1 Main Class
 * @author Rafael Angelo Pucut
 * Date: January 15, 2018
 */
public class A1 {
	
	public static void main (String[] args) throws FileNotFoundException
	{
		A1 running = new A1();
		running.processingList();
	}
	
	/**
	 * This 
	 */
	public static void processingList()  throws FileNotFoundException
	{
		InputStream inStream = new FileInputStream(new File("inp2.txt"));
		Scanner inp = new Scanner(inStream);
		String [] stopWords = {"a","about","all","am","an","and","any","are","as","at","be", 
				"been","but","by","can","cannot","could","did","do", "does", "else","for",
				"from","get","got","had","has","have","he","her", 
				"hers","him","his","how","i","if","in","into","is","it","its","like", 
				"more","me","my","no","now","not","of","on","one","or","our","out",  
				"said","say","says","she","so","some","than","that","the","their", 
				"them","then","there","these","they","this","to","too","us","upon",  
				"was","we","were","what","with","when","where","which","while","who",
				"whom","why","will","you","your"};
		ArrayList<Word> listWords = new ArrayList<Word>(); 
		int stopCounter = 0, 
			totalCounter = 0, 
			uniqueCounter = 0;
		String userInput = "";
		
		while (inp.hasNext()) 
		{
			userInput = inp.next().trim().toLowerCase().replaceAll("[^a-zA-Z]","");
			if (confirmWords(userInput)) 
			{
				totalCounter++;
			}
			if (stopCheck(stopWords, userInput)) 
			{
				stopCounter++;
			}
			else if (!similarityCheck(listWords, userInput) && !userInput.equals(""))
			{    
				Word unique = new Word(userInput);
				listWords.add(unique);
				unique.increaseCount();
				uniqueCounter++;
			}
		}
		printing(totalCounter, uniqueCounter, stopCounter, listWords);
	}

	public static boolean confirmWords(String fileWord) 
	{
		String confirmer = "[a-z]+";	
		
		return fileWord.matches(confirmer);
	}
	
	public static boolean stopCheck(String [] stop, String inputWord) 
	{
		boolean checking = false;
		for(int i = 0; i < stop.length; i++) 
		{
			if (inputWord.equals(stop[i])) 
			{
				checking = true;
			}
		}
		return checking;
	}
	
	public static boolean similarityCheck(ArrayList<Word> list, String input) 
	{
		boolean similarCheck = false;
		for (Word w: list) 
		{
			if(input.equals(w.getWord())) 
			{
				similarCheck = true;
				w.increaseCount();
			}
		}
		return similarCheck; 
	}
	
	public static void printing(int total, int unique, int stopping, ArrayList<Word> printVersion) 
	{
		if (printVersion.isEmpty()) 
		{
			System.out.println("The text file being read is empty. Please load another text file with content.");
		}
		else 
		{
			System.out.println("Total Words: " + total);
			System.out.println("Unique Words: " + unique);
			System.out.println("Stop Words: " + stopping + "\n");
			System.out.println("10 Most Frequent");
			Collections.sort(printVersion, Word.mostFrequent);
			mostLeastPrint(printVersion);
			System.out.println();
			System.out.println("10 Least Frequent");
			Collections.sort(printVersion, Word.leastFrequent);
			mostLeastPrint(printVersion);
			System.out.println();
			System.out.println("All");
			Collections.sort(printVersion);
			for (Word word: printVersion) 
			{
				System.out.println(word.toString());
			}
		}
	}
	
	public static void mostLeastPrint(ArrayList<Word> mostLeast) 
	{
		if (mostLeast.size() < 10)
		{
			for (int lessPrint = 0; lessPrint < mostLeast.size(); lessPrint++) 
			{
				System.out.println(mostLeast.get(lessPrint));
			}
		}
		else 
		{
			for (int print = 0; print < 10; print++) 
			{
				System.out.println(mostLeast.get(print));
			}
		}
	}
}
