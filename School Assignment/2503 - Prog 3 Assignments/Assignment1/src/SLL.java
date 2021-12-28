
public class SLL <T extends Comparable<T>>{

	private Node<T> head;
	
	public SLL() {
		head = null;
	}
	
	public void addHead( Node<T> n) 
	   {
	       if (isEmpty())
	       {
	           head = n;
	       }
	       else
	       {
	           Node<T> current = head;
	           
	           head = n;
	           
	           head.setNext(current);
	       }
	   }
	
	public void addTail(Node<T> toTail)
	{
		if (head != null)
		{
			Node<T> current = head;
			while (current.getNext() != null)
			{
				current = current.getNext();
			}
			current.setNext(toTail);
		}
		else
		{
			head = toTail;
		}
	}
	
	public void addInOrder(Node<T> n)
	{
		if(head == null)
		{
			head = n;
		}
		else 
		{
			if (n.getData().compareTo(head.getData()) <= 0)
			{
				addHead(n);
			}
			else
			{
				Node<T> current = head;
				while (current.getNext() != null && n.getData().compareTo(current.getNext().getData()) > 0)
				{
					current = current.getNext();
				}
				if (current.getNext() == null)
				{
					addTail(n);
				}
				else
				{
					n.setNext(current.getNext());
					current.setNext(n);
				}
				
			}
		}
	}
	
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
	
	public boolean contains( Word w) 
	{
		boolean found = false;
		int length = size();
		Node<T> current = head;
		
		for (int count = 0; count < length; count++)
		{
			if(w.equals(current.getData()))
			{
				found = true;
			}
			else
			{
				current.getNext();
			}
		}
//		while (current.getNext() != null)
//		{
//			if (w.equals(current.getData()))
//			{
//				found = true;
//			}
//			else
//			{
//				found = false;
//				current.getNext();
//			}
//		}
		
		return found;
	}
	
	public int indexOf(Word find) 
	{
		int counter = 0,
			index   = 0;
		Node<T> current = head;
		while (current.getNext() != null)
		{
			counter++;
			if (find.equals(current.getData()))
			{
				index = counter - 1;
			}
			else
			{
				current.getNext();
			}
		}
		
		return index; 
	}
	
	public boolean isEmpty(){return (head == null);}
	
}
