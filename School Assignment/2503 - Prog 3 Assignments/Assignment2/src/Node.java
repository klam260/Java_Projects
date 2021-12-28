
public class Node<T extends Comparable<T>> {

	private T data;
    private Node<T> next;
    
    /**
     * Constructor for objects of class Node.
     * Sets data to passed parameter dataCreate
     * and next to null.
     * @param dataCreate is the type of content which
     * sets the data.
     */
    public Node(T dataCreate)
    {
       data = dataCreate;
       next = null;
    }

    /**
     * GETTER
     * @return data is the specific type of 
     * data within the node.
     */
    public T getData() { return data;}
    
    /**
     * SETTER
     * @param dataSet is the data used to 
     * set the node's value.
     */
    public void setData(T dataSet) { data = dataSet;}
    
    /**
     * GETTER
     * @return next is the node being returned.
     */
    public Node<T> getNext() { return next;}
    
    /**
     * SETTER
     * @param dataNext is the data used to 
     * set the node's value.
     */
    public void setNext(Node<T> dataNext) { next = dataNext;}
    
    /**
     * Formats the way a node is printed.
     * @return String is the formatted text being 
     * returned for the node.
     */
    public String toString() 
    {
        return "Node: " + getData().toString();
    }
}
