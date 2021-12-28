import java.util.Comparator;
/** 
 * COMP 2503 Assignment 5 Word Class
 * 
 * A Word object represents a word (string) and how many times it 
 * has occured in a given text. 
*/

public class Word implements Comparable<Word> {

   // The word!
   private String word;
   // How many times it has occurred
   private int count; 
   
   /** Constructor. Set the string and initialize the count to 0.
    *   @param w the string representing this word.
    */
   public Word(String w) 
   {
      this.word = w;
      this.count = 0;
   }

   /**
    * Comparator to sort from high to low frequency (count).
    */
   public static Comparator<Word> CompFreqDesc = new Comparator<Word>() 
   {
	  /**
	   * Compares the words occurrence values
	   * @return -1,0,1 which orders them in a descending fashion.
	   */
      public int compare( Word w1, Word w2) 
      {
         int f1 = w1.getCount();
         int f2 = w2.getCount();
         if (f2-f1 == 0) 
            return w1.compareTo( w2);
         else
            return f2 - f1; 
      }
   };

   /**
    * Comparator to sort from low to high frequency (count).
    */
   public static Comparator<Word> CompFreqAsc = new Comparator<Word>() 
   {
	   /**
		 * Compares the words occurrence values
		 * @return -1,0,1 which orders them in a ascending fashion.
		 */
       public int compare( Word w1, Word w2) 
       {

         int f1 = w1.getCount();
         int f2 = w2.getCount();

         if (f1 - f2 == 0) 
            return w1.compareTo( w2);
         else
            return f1 - f2; 
      }
   };
   
   /**
    * Comparator to sort length of words from high to low
    */
   public static Comparator<Word> CompWLength = new Comparator<Word>()
   {
      public int compare( Word w1, Word w2) 
         {

            int f1 = w1.getWord().length();
            int f2 = w2.getWord().length();

            if (f2 - f1 == 0) 
               return w1.compareTo(w2);
            else
               return f2 - f1; 
         }
   };
   
   /**
    * GETTER
    * @return word is the name of the word being acquired
    */
   public String getWord() 
   { 
      return word;
   }

   /**
    * GETTER
    * @return count is the occurrence value of the word
    */
   public int getCount() 
   { 
      return count;
   }

   /**
    * Increases the number of times a word has appeared in the txt file
    */
   public void incrCount() 
   { 
      count++; 
   }

   /**
    * Returns a formatted String which shows
    * the word and its occurrence value 
    * @return String is formatted data
    */
   public String toString() 
   { 
      return getWord() + " : " + getWord().length() + " : " +getCount();
   }

   /** 
    *  Compares two objects and validates whether they
    *  are similar or not.
    *  @return true if both objects compared are equal
    *  and false otherwise. 
    */
   public boolean equals( Object other) 
   {
      if ( other == this) return true;
      if ( other == null) return false;
      if ( this.getClass() != other.getClass()) return false;
      Word p = (Word)other;
      return this.getWord().equals( p.getWord());
   }
  
   /** Compare two words. This will order 
    *  Words alphabetically. 
    *  @return -1,0,1 which are the values that arranges the Words
    */
   public int compareTo( Word o) 
   {
      if ( this.equals( o))
         return 0;
      else
         return this.getWord().compareTo( o.getWord()); 
   }
}