import java.util.Comparator;
public class Word implements Comparable<Word> {
	private String word;
	private int numberAppearance;
	
	/**
	 * Creates a Word object when called upon
	 * @param create - Actual word from txt fileused to generate object
	 */
	public Word (String create) 
	{
		word = create;
	}
	
	/**
	 * GETTER FOR WORD STRING
	 * @return word in order to acquire specific word to print or detect
	 */
	public String getWord() 
	{
		return word;
	}
	
	/**
	 * GETTER FOR NUMBERAPPEARANCE INTEGER
	 * @return numberAppearance in order to acquire number of times word has been used
	 */
	public int getNumberAppearance() 
	{
		return numberAppearance;
	}
	
	/**
	 * SETTER FOR WORD INTEGER
	 * @param settingWord - word that is to be set manually
	 */
	public void setWord(String settingWord) 
	{
		word = settingWord;
	}
	
	/**
	 * SETTER FOR NUMBERAPPEARANCE INTEGER
	 * @param number - number that is to be set manually
	 */
	public void setNumberAppearance(int number) 
	{
		numberAppearance = number;
	}
	
	/**
	 * Increases the number of times a word has appeared
	 */
	public void increaseCount() 
	{
		numberAppearance++;
	}
	
	/**
	 * Compares two Word objects whether it is similar or not.
	 * @param actual - word object that is used to compare with another word object
	 * @return true/false - if the word objects are similar, it will return true, 
	 * otherwise it will return false
	 */
	public boolean equals(Object actual) 
	{
		if (actual == this) 
		{
			return true;
		}
		else if (actual == null)
		{
			return false;
		}
		Word w = (Word)actual;
		return this.getWord().equals(w.getWord());
	}
	
	/**
	 * A comparable interface method used to order words
	 * @param order - word object used to compare with another word object
	 * @return comparison - integer that will be either -1,0,or 1 which 
	 * will be used as a basis to order a list of words
	 */
	public int compareTo(Word order) 
	{
		int result=this.word.compareTo(order.getWord());
		int comparison = 0;
		if (result < 0) 
		{
			comparison = -1;
		}
		else if (result > 0) 
		{
			comparison = 1;
		}
		return comparison;
	}
	
	/**
	 * A comparator interface method used to order words from the least
	 * frequent word to the most frequent word.
	 * @param t1 - first word used to compare in regards to the determined order
	 * @param t2 - second word used to compare in regards to the determined order
	 * @return -1,0-, or 1 
	 */
	public static Comparator<Word> leastFrequent = new Comparator<Word>() 
	{
		 
		public int compare(Word t1, Word t2) 
		{
			if (t1.getNumberAppearance() > t2.getNumberAppearance()) 
			{
				return 1;
			}
			else if (t1.getNumberAppearance() < t2.getNumberAppearance()) 
			{
				return -1;
			}
			else {return t1.compareTo(t2);}
		}
	};
	
	/**
	 * A comparator interface method used to order words from the most
	 * frequent word to the least frequent word.
	 * @param w1 - first word used to compare in regards to the determined order
	 * @param w2 - second word used to compare in regards to the determined order
	 * @return -1,0-, or 1 
	 */
	public static Comparator<Word> mostFrequent = new Comparator<Word>() 
	{
		 
		public int compare(Word w1, Word w2) 
		{
			if (w1.getNumberAppearance() > w2.getNumberAppearance()) 
			{
				return -1;
			}
			else if (w1.getNumberAppearance() < w2.getNumberAppearance()) 
			{
				return 1;
			}
			else {return w1.compareTo(w2);}
		}
	};
	
	/**
	 * Prints out a formatted line showing the word and 
	 * how many times it has appeared
	 */
	public String toString() 
	{
		return word + " : " + numberAppearance;
	}
}
