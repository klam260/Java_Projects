import java.util.*;
/** 
 * CODE NOT WORKING
 * 
 * WORKING CODE:
 * digitsRead method, printDigits method
 * 
 * PARTIALLY WORKING CODE:
 * addition method, and addToStack method
 * When sum is being printed, there are extra 
 * zeros printed before the right answer
 * which is because of the addToStack method 
 * where zeros are added to make the addition
 * function. Also the formatting is not the 
 * desired way needed in the output txt file.
 * 
 * A possible way to improve it is to make a method 
 * that can take out the zeros through manipulating stacks
 * by pushing and popping the right values into a newer stack 
 * to print it properly 
 * 
 * COMP 2503 Winter 2018 Assignment A3c 
 * @author Rafael Angelo Pucut
 * @date 3/26/2018
 * 
 * This program reads a text file which contains
 * a series of addition problems to solve
 */
public class A3b {

	private Scanner inp = new Scanner(System.in);
	private String num1 = "";
	private String num2 = "";
	
	private int digit1Size = 0;
	private int digit2Size = 0;
	private int difference = 0;
	
	private Stack<Integer> digit1 = new Stack();
	private Stack<Integer> digit2 = new Stack();
	private Stack<Integer> sum = new Stack();
	
	/**
	 * Runs the whole program
	 * @param args
	 */
	public static void main(String[] args)
	{
		A3b start = new A3b();
		start.digitsRead();
	}
	
	/**
	 * Reads the digits from file and 
	 * adds them into separate stacks to differentiate
	 * between the first digit and second digit
	 */
	public void digitsRead()
	{
		while (inp.hasNext())
		{
			num1 = inp.next();
			num2 = inp.next();
			addToStack(num1, digit1, digit2);
			addToStack(num2, digit2, digit1);
			addition(digit1, digit2);
			System.out.print("  " + num1 + " \n+ " + num2 + " \n= ");
			digit1.empty();
			digit2.empty();
			printDigits(sum);
			sum.empty();
			System.out.println();
		}
	}
	
	/**
	 * Adds numbers to the stack individually
	 * @param value is the whole number being added to the Stack
	 * @param numberAdd is the stack the value is being pushed into
	 * @param adjuster is the stack of the second value
	 */
	public void addToStack(String value, Stack<Integer> numberAdd, Stack<Integer> adjuster)
	{
		digit1Size = numberAdd.getSize();
		digit2Size = adjuster.getSize();
		int result = 0;
		int stackLength = value.length();
		if (digit1Size < digit2Size)
		{
			difference = digit2Size - digit1Size;
		}
		//this condition makes sure that the 
		//length of both numbers being added are the same
		//through pushing zero so adding them will function
		if (difference > 0)
		{
			int adder = 0;
			while (adder < difference)
			{
				numberAdd.push(0); 
				adder++;
			}
		}
		for (int count = 0; count < stackLength; count++)
		{
			result = value.charAt(count) - '0';
			numberAdd.push(result);
		}
	}
	
	/**
	 * Adds the two numbers from the two different stacks together
	 * @param first is the stack with the first digit
	 * @param second is the stack with the second digit
	 */
	public void addition(Stack<Integer> first, Stack<Integer> second)
	{
		int carry = 0;
		
		while (!first.isEmpty() && !second.isEmpty())
		{
			int indiv1 = first.pop();
			int indiv2 = second.pop();
			
			
			int result = indiv1 + indiv2 + carry;
			//the conditional below is to make sure
			//a carry value of one is accounted for
			//when adding the numbers
			if (result > 9)
			{
				carry = 1;
				result = result % 10;
			}
			else
			{
				carry = 0;
			}
			sum.push(result);
		}
	}
	
	/**
	 * Prints the sum of the addition problem
	 * @param result is the stack which contains the sum
	 */
	public void printDigits(Stack<Integer> result)
	{	
		while (!result.isEmpty())
		{
			System.out.print(result.pop());
		}	
	}
}
