import java.io.*;
/** 
 * WORKING CODE
 * COMP 2503 Winter 2018 Assignment A3a 
 * @author Rafael Angelo Pucut
 * @date 3/26/2018
 * 
 * This program reads a text file and checks whether the 
 * word sent into the program is a palindrome or not.
 *
 */
public class A3a {

	private BufferedReader inp = new BufferedReader(new InputStreamReader(System.in));
	
	private Stack<Character> oppositeOrder = new Stack();
	private Stack<Character> revTwo = new Stack();
	private Stack<Character> original = new Stack();
	
	private Stack<Character> toPrint = new Stack();
	
	private String approve = ": Yes";
	private String deny = ": No";
	
	/**
	 * Runs the whole program
	 * @param args
	 * @throws IOException
	 */
	public static void main (String[] args) throws IOException
	{
		A3a start = new A3a();
		start.processing();
	}
	
	/**
	 * Reads the characters of each word and places them 
	 * into a Stack. 
	 * @throws IOException
	 */
	public void processing() throws IOException
	{
		int check;
		
		while ( (check = inp.read()) != -1 )
		{
				
				if (!whiteSpaceCheck(check))
				{
					char adding = (char)check;
					oppositeOrder.push(adding);
					revTwo.push(adding);
				}
				else
				{
					while (!revTwo.isEmpty())
					{
						original.push(revTwo.pop());
					}
					printStack(original);
					if (palindromeTest(original, oppositeOrder))
					{
						System.out.println(approve);
					}
					else
					{
						System.out.println(deny);
					}
					
					oppositeOrder.empty();
					revTwo.empty();
					original.empty();
					
					while (whiteSpaceCheck(check))
					{
						check = inp.read();
						if (!whiteSpaceCheck(check))
						{
							char adding = (char)check;
							oppositeOrder.push(adding);
							revTwo.push(adding);
						}
					}
				}
				
		}
	}
	
	/**
	 * Checks whether or not there are any white spaces
	 * being received as input.
	 * @param space is the integer character being checked
	 * @return true if there are white spaces and false otherwise
	 */
	public boolean whiteSpaceCheck(int space)
	{
		if (space != 9 && space != 10 && space != 11 && space != 12 && space != 13 && space != 32)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/**
	 * Checks the word if it is a palindrome or not
	 * @param proper is the stack with the right word spelling
	 * @param reverse is the stack with the reverse word spelling
	 * @return true if the word is a palindrome and false otherwise
	 */
	public boolean palindromeTest(Stack<Character> proper, Stack<Character> reverse)
	{
		boolean similar = true;
		
		while(similar && !(proper.isEmpty()))
		{
			char right = proper.pop();
			char wrong = reverse.pop();
			if (right != wrong)
			{
				similar = false;
			}
		}
		return similar;
	}
	
	/**
	 * Prints the word from the Stack
	 * @param printing is the stack of characters which contain the word
	 */
	public void printStack(Stack<Character> printing)
	{
		Stack<Character> redeem = new Stack();
		while (!printing.isEmpty())
		{
			char toPrint = printing.pop();
			System.out.print(toPrint);
			redeem.push(toPrint);
		}
		
		while(!redeem.isEmpty())
		{
			printing.push(redeem.pop());
		}
	}
}
