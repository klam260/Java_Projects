import java.util.Comparator;

public class SLL<T extends Comparable<T>> 
{

   /* Inner class Node for the linked list.  
    */
   class Node<T extends Comparable<T>>
   {
   
      private T data;
      private Node<T> next;
    
      /*
       * Constructor for objects of class Node
       */
      public Node(T d)
      {
         data = d;
         next = null;
      }

      public T getData() { return data;}
      public void setData( T o) { data = o;}
    
      public Node<T> getNext() { return next;}
      public void setNext( Node<T> n) { next = n;}

      public String toString() { return "Node: " + getData().toString();}

   } // Node


   private Node<T> head, tail; 
   private int size;


   public SLL()
   {
      head = null;
      tail = null;
      size = 0;
   }

   // Public Methods

   /** Return the number of elements in the list. 
    *  @return int number of elements  in the list. 
    */
   public int size()
   {
      return size;
   }
   
   /** Empty the list. 
    */
   public void emptyList()
   {
       head = null;
       tail = null;
       size = 0;
   }

   /** Add a new object t to the head of the list. 
    *  @param t the object to add. 
    */
   public void addHead( T t)
   {
      addHead( new Node<T>( t));
   }

   /** Add a new object i to the tail of the list. 
    *  @param t the object to add. 
    */
   public void addTail( T t)
   {
      addTail( new Node<T>( t));
   }   

   /** Delete the element at the head of the list. 
    *  @return the deleted element. 
    */
   public T deleteHead() 
   {
      Node<T> n = delHead();
      if ( n == null) 
         return null;
      else 
         return n.getData(); 
   }

   /** Return the ith element of the list.
    *  @param i the element to return
    *  @return the ith element, null if there isnt one. 
    */
   public T get( int i)
   {
      Node<T> curr = head;
      int j = 0;
      while ( curr != null && j < i)
      {
         curr = curr.getNext();
         j++;
      }
      if ( curr != null) 
         return curr.getData();
      else 
         return null;
   }
 
   // Private methods
   
   /* Add a new Node to the head of the list. 
    */
   private void addHead( Node<T> n) 
   {
      if (head == null) // empty list 
      {
         head = n; 
         tail = n;
      }
      else 
      {
         n.setNext(head);
         head = n;
      }
      size++;
   }

   /* Add a new Node to the tail of the list. 
    */
   private void addTail( Node<T> n) 
   {
      if (tail == null) // list is empty
      {
         head = n; 
         tail = n;
      }
      else 
      {
         tail.setNext(n);
         tail = n;
      }
      size++;
   }
   
   /* Delete the node at the head of the list and return a pointer to it. 
    */
   private Node<T> delHead() 
   {
      Node<T> n = null;
      if ( head != null) 
      {
         n = head;
         if ( head == tail) 
            tail = head.getNext();
         head = head.getNext();
         size--;
      }
      return n;
   }
 
}
