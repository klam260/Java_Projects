import java.util.Comparator;
public class SLL <T extends Comparable<T>>{

	private Node<T> head;
	
	/**
	 * The general constructor for the SLL class.
	 * Sets head to null.
	 */
	public SLL() {
		head = null;
	}
	
	/**
	 * Adds the data by creating a node 
	 * to the head/start position of the list
	 * @param n the type of data being added onto the list
	 */
	public void addHead( T n) 
	   {
		Node<T> nodeToHead = new Node(n);
	       if (isEmpty())
	       {
	           head = nodeToHead;
	       }
	       else
	       {
	           Node<T> current = head;
	           
	           head = nodeToHead;
	           
	           head.setNext(current);
	       }
	   }
	
	/**
	 * Adds the data by creating a node 
	 * to the tail/end position of the list
	 * @param toTail the type of data being added onto the list
	 */
	public void addTail(T toTail)
	{
		Node<T> addToTail = new Node(toTail);
		if (head != null)
		{
			Node<T> current = head;
			while (current.getNext() != null)
			{
				current = current.getNext();
			}
			current.setNext(addToTail);
		}
		else
		{
			head = addToTail;
		}
	}
	
	/**
	 * Adds the nodes in alphabetical order.
	 * @param nodeAdd the word node being added
	 */
	public void addInOrder(Node<T> nodeAdd)
	{
		if(head == null)
		{
			head = nodeAdd;
		}
		else 
		{
			//The conditional adds the new node as the new head if its object value 
			//is even less than the object value of the current head
			if (nodeAdd.getData().compareTo(head.getData()) <= 0)
			{
				addHead(nodeAdd.getData());
			}
			else
			{
				Node<T> current = head;
				//The loop traverses through the list and compares the new node 
				//object value as long as it is still not greater than the nodes
				//within the list and once it is greater, it stops and is added at 
				//that specific position
				while (current.getNext() != null && nodeAdd.getData().compareTo(current.getNext().getData()) > 0)
				{
					current = current.getNext();
				}
				if (current.getNext() == null)
				{
					addTail(nodeAdd.getData());
				}
				else
				{
					nodeAdd.setNext(current.getNext());
					current.setNext(nodeAdd);
				}
				
			}
		}
	}
	
	/**
	 * Acquires the data of a node at
	 * a specific position
	 * @param index position of the data needed within the list
	 * @return data is the content of the node requested
	 */
	public T get(int index)
	{
		T data = null;
		int length = size();
		Node<T> current = head;
		
		if (index <= length && index >= 0)
		{
			current = head;
			for (int count = 0; count < index; count++)
			{
				current = current.getNext();
			}
			data = current.getData();
		}
		return data;
	}
	
	/**
	 * Counts the number of words within the linked list
	 * @return length is the overall number of words within the list
	 */
	public int size()
	{
		Node<T> current = head;
		int length = 0;
		
		while (current != null)
		{
			length++;
			current = current.getNext();
		}
		
		return length;
	}
	
	/**
	 * Confirms whether or not a word exists multiple times in the list
	 * @param wordChecked is the node used to verify its repeated existence 
	 * @return true if another similar word exists within the list and 
	 * false if it is a unique word
	 */
	public boolean contains( Node<Word> wordChecked) 
	{
		return indexOf(wordChecked) != -1;
	}
	
	/**
	 * Looks for the index position of a specific node and returns it
	 * @param find is the node selected to acquire its index position in the list
	 * @return index is the position of the node passed in and returns -1 
	 * when the node is not found in the list
	 */
	public int indexOf(Node<Word> find) 
	{
		int index   = 0;
		Node<T> current = head;
		
		
		while (current != null)
		{
			if (find.getData().equals(current.getData()))
			{
				return index;
			}
			index++;
			current = current.getNext();
		}
		
		return -1; 
	}
	
	/**
	 * Verifies if the linked list has content or not
	 * @return true if there is no content within the list 
	 * or false if there is content in the list
	 */
	public boolean isEmpty(){return (head == null);}
	
	/**
	 * Adds and arranges the word nodes into a list 
	 * from the least number of appearances
	 * to the most number of appearances
	 * @param dataAsc is the content being added 
	 * onto this arranged list 
	 */
	public void ascendSort(T dataAsc)
	{
		Node<T> ascendAdd = new Node<T>(dataAsc);
		
		if(head == null)
		{
			head = ascendAdd;
		}
		else
		{
			int ascendFreq = ((Word)ascendAdd.getData()).getCount();
			int headFreq = ((Word)head.getData()).getCount();
			Node<T> current = head;
			//It initially compares the occurrence value of the added word
			//to the occurrence value of the first node in the list
			//and adds it to be the first in the list if its occurrence value is smaller
			//than the current head's occurrence value
			if(ascendFreq < headFreq)
			{
				addHead(ascendAdd.getData());
			}
			else
			{
				current = head;
				//The loop traverses through the list while comparing the occurrences of the word to be added
				//to the occurrences of the next words within the list and keeps comparing as long as
				//the occurrences of the added word is greater than or equal to the other words in the list
				//This is to make sure the list appears in an ascending order
				while (current.getNext() != null && ascendFreq >= ((Word)current.getNext().getData()).getCount())
				{
					current = current.getNext();
				}
				if(current.getNext() == null)
				{
					addTail(ascendAdd.getData());
				}
				else
				{
					ascendAdd.setNext(current.getNext());
					current.setNext(ascendAdd);
				}
			}
		}
	}
	
	/**
	 * Adds and arranges the word nodes into a list 
	 * from the most number of appearances
	 * to the least number of appearances
	 * @param dataDsc is the content being added 
	 * onto this arranged list 
	 */
	public void descendSort(T dataDsc)
	{
		Node<T> descendAdd = new Node(dataDsc);
		if (head == null)
		{
			head = descendAdd;
		}
		else
		{
			int descendFreq = ((Word)descendAdd.getData()).getCount();
			int headFreq = ((Word)head.getData()).getCount();
			Node<T> current = head;
			
			//It initially compares the occurrence value of the added word
			//to the occurrence value of the first node in the list
			//and adds it to be the first in the list if its occurrence value is greater
			//than the current head's occurrence value
			if(descendFreq > headFreq)
			{
				addHead(descendAdd.getData());
			}
			else
			{
				current = head;
				//The loop traverses through the list while comparing the occurrences of the word to be added
				//to the occurrences of the next words within the list and keeps comparing as long as
				//the occurrences of the added word is less than or equal to the other words in the list
				//This is to make sure the list appears in an descending order
				while(current.getNext() != null && descendFreq <= ((Word)current.getNext().getData()).getCount())
				{
					current = current.getNext();
				}
				if (current.getNext() == null)
				{
					addTail(descendAdd.getData());
				}
				else
				{
					descendAdd.setNext(current.getNext());
					current.setNext(descendAdd);
				}
			}
			
		}
	}
	
}