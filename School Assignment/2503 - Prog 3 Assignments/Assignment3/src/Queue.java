
public class Queue <T extends Comparable<T>> extends SLL<T>{

	/**
	 * Removes and returns first item added to the queue
	 * @return first item added to the queue
	 */
	public T dequeue()
	{
		return deleteHead();
	}
	
	/**
	 * Adds an item to the stack queue, at the end of the queue
	 * @param data is the item being added
	 */
	public void enqueue(T data)
	{
		addTail(data);
	}
	/**
     * View the top most data within the stack
     * @return the data at the top of the stack
     */
    public T peek()
    {
        return get(0);
    }
    
    /**
     * Confirms if the stack is empty
     * @return true if stack is empty and false otherwise
     */
    public boolean isEmpty()
    {
        return size() == 0;
    }
    
    /**
     * Empties the stack
     */
    public void empty()
    {
    	emptyList();
    }
}
