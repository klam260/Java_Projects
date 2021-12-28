public class Stack <T extends Comparable<T>> extends SLL<T>
{
    /**
     * Adds an element to the Stack
     * @param data is the element being added
     */
    public void push (T data)
    {
        addHead(data);
    }
    
    /**
     * Remove the top element, without popping it
     * @return the removed/popped data
     */
    public T pop()
    {
        return deleteHead();
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
    
    /**
     * Acquires the size of the stack
     * @return stackSize is the size value of the stack
     */
    public int getSize()
    {
    	int stackSize = size();
    	return stackSize;
    }
}