import java.util.Iterator;
import java.util.Comparator;
/** 
 * COMP 2503 Assignment 4 BST Class
 * 
 * A BST object represents a list of a created Binary Search Tree 
*/
public class BST<T extends Comparable<T>> 
{
   class BSTNode implements Comparable<BSTNode>
   {
      private T data;
      private BSTNode left; 
      private BSTNode right; 
     
      public BSTNode( T d) 
      {
         setLeft( null);
         setRight( null);
         setData( d);
      }

      public T getData() { return data; }
      public void setData( T d) { data = d; }

      public void setLeft( BSTNode l) { left = l;}
      public void setRight( BSTNode r) { right = r;}
      public BSTNode getLeft()  { return left;}
      public BSTNode getRight()  { return right;}
      public boolean isLeaf() 
      { 
         return ( getLeft() == null) && ( getRight() == null);
      }

      public int compareTo( BSTNode o) 
      {
         return this.getData().compareTo( o.getData());
      }
      
   }

   // The different traversal types. 
   public static final int INORDER = 0;
   public static final int PREORDER = 1;
   public static final int POSTORDER = 2;
   public static final int LEVELORDER = 3;

   private BSTNode root;
   private int size; 

   public BST()
   {
      root = null;
      size = 0;
   }

   /** Return true if element d is present in the tree. 
   */
   public T find( T d) 
   {
      return find( d, root); 
   }

   /** Add element d to the tree. 
   */
   public void add( T d) 
   {
      BSTNode n = new BSTNode( d); 
      if ( root == null) 
      {
         size++;
         root = n; 
      }
      else 
      {
         add( root, n); 
      }
   }

   /** Return the height of the tree. 
    */
   public int height() 
   {
      return height( root);
   }

   /** Return the number of nodes in the tree. 
    */
   public int size() { 
      return size;
   }
   
   public void inOrder() 
   {
      traverse( root, INORDER);
   }

   public void postOrder() 
   {
      traverse( root, POSTORDER);
   }

   public void preOrder() 
   {
      traverse( root, PREORDER);
   }

   public void levelOrder() 
   {
      traverse( root, LEVELORDER);
   }
   
   /**
    * Deletes the passed in data
    * and decreases the size of the BST
    * @param data is the data to be deleted
    */
   public void delete(T data)
   {
	   root = delete(root, data);
	   size--;
   }
   
   /**
    * Finds the minimum value of the passed in
    * Binary Search Tree
    * @param node is the root passed in by the BST
    * @return node is the minimum value of the root passed in for the BST
    */
   public BSTNode findMinValue(BSTNode node)
   {
	   while(node.getLeft() != null)
	   {
		   node = node.getLeft();
	   }
	   return node;
   }
   
   /**
    * A function that will return the iterator 
    * to be used by another class
    * @return BSTIterator is the iterator to be utilized
    */
   public Iterator<T> iterator() 
   {
       return new BSTIterator();
   }
   
   // Private methods. 

   /**
    * Looks for the passed in data within the 
    * BST selected through its root 
    * @param d is the data needed to be found
    * @param r is the root of the BST
    * @return data found in the BST
    */
   private T find( T d, BSTNode r) 
   {
      if ( r == null) 
         return null;
      int c = d.compareTo( r.getData()); 
      if ( c == 0) 
         return r.getData();
      else if ( c < 0) 
         return find( d, r.getLeft());
      else 
         return find( d, r.getRight());
   } 

   /**
    * Adds words in the most to least order
    * @param d is the data to be added
    * @param compare is the comparator to order it in most to least order
    */
   public void addFreqOrder(T d, Comparator<T> compare)
   {
	   BSTNode toAdd = new BSTNode(d);
	   if(root ==  null)
	   {
		   size++;
		   root = toAdd;
	   }
	   else
	   {
		   addFreqOrder(root, toAdd, compare);
	   }
   }
   
   /**
    * Do the actual add of node r to tree rooted at n
    * @param r is the root node
    * @param n is the node to be added
    */
   private void add( BSTNode r, BSTNode n) 
   {
      int c = n.compareTo( r);
      if ( c < 0) 
      {
         // the element to be added is less than the root 
         if ( r.getLeft() == null) 
         {
            // there is no left node so 
            // we can add it here
            r.setLeft( n);
            size++;
         }
         else 
         {
            // add it to the left sub-tree
            add( r.getLeft(), n);
         }
      }
      else  if ( c > 0) 
      {
         // the element to be added is greater than the root 
         if ( r.getRight() == null) 
         {
            // there is no right node so 
            // we can add it here
            r.setRight( n);
            size++;
         }
         else 
         {
            // add it to the right sub-tree
            add( r.getRight(), n);
         }
      }
   }
   
   /**
    * Deletes a word from the BST
    * @param root is the root of the BST
    * @param data is the selected word to be deleted
    * @return node to be deleted
    */
   private BSTNode delete(BSTNode root, T data)
   {
	   BSTNode current = root;
	   if(current == null) return root;
	   
	   //Figures out which side of the tree to travel to
	   int travelNbr = data.compareTo(current.getData());
	   
	   if(travelNbr < 0)
	   {
			current.setLeft(delete(current.getLeft(), data));
	   }
	   else if (travelNbr > 0)
	   {
			current.setRight(delete(current.getRight(), data));
			//getting past these steps mean that the 
			//data to be deleted is found
	   }
	   else
	   {
		   //CASES TO TAKE INTO ACCOUNT:
		   //There are 2 children, only 1 child which is on the left side,
		   //1 child which is on the right side and if there are no children at all
		   if(current.getLeft() != null && current.getRight() != null)
		   {
			   BSTNode tempo = findMinValue(current.getRight());
			   current.setData(tempo.getData());
			   current.setRight(delete(current.getRight(), tempo.getData()));
		   }
		   else if(current.getLeft() != null)
		   {
			   current = current.getLeft();
		   }
		   else if(current.getRight() != null)
		   {
			   current = current.getRight();
		   }else
		   {
			   current = null;
		   }   
	   }
	   return current;
   }
   
   /**
    * BSTIterator class 
    * This class contains the iterator functions 
    * needed in order to properly print all
    * the BSTs
    */
   class BSTIterator implements Iterator<T> 
   {
       Stack<BSTNode> bstStack = new Stack<BSTNode>();
       /**
        * Generates an Iterator
        */
       public BSTIterator()
       {
           BSTNode itNode = root;
           while(itNode != null) 
           {
               bstStack.push( itNode);
               itNode = itNode.getLeft();
           }
       }
       
       /**
        * Figures out if the stack is not empty
        * @return true if not empty and false otherwise
        */
       public boolean hasNext() {
           return !bstStack.isEmpty();
       }
       
       /**
        * Acquires the next element in the BST
        * @return data which is the next element
        */
       public T next() {
           BSTNode nextNode = bstStack.pop();
           T data = nextNode.getData();
           if( nextNode.getRight() != null)
           {
               nextNode = nextNode.getRight();
               while ( nextNode != null)
               {
                   bstStack.push( nextNode);
                   nextNode = nextNode.getLeft();
               }
           }
           return data;
       }
   }
   
   /**
    * Adds words in a frequency order depending on the
    * Comparator utilized
    * @param root is the root of the tree
    * @param adding is the data to be added to the tree
    * @param compare is the comparator selected that will order the words
    */
   private void addFreqOrder(BSTNode root, BSTNode adding, Comparator<T> compare)
   {
	   int order = compare.compare(root.getData(), adding.getData());
	   //figures out whether the word will come earlier or later
	   if (order > 0)
	   {
		   if(root.getLeft() ==  null)
		   {
			   root.setLeft(adding);
			   size++;
		   }
		   else
		   {
			   addFreqOrder(root.getLeft(), adding, compare);
		   }
	   }
	   else if (order < 0)
	   {
		   if(root.getRight() == null)
		   {
			   root.setRight(adding);
			   size++;
		   }
		   else
		   {
			   addFreqOrder(root.getRight(), adding, compare);
		   }
	   }
   }
   
   /* Implement a height method. */
   private int height( BSTNode r) 
   {
      int h = 0;
      if ( r != null) 
      {
         int rh = height( r.getRight());
         int lh = height( r.getLeft());
         h = ( rh > lh ? 1 + rh : 1 + lh);
      }
      return h;
   } 
   
   /* Traverse the tree.  travtype determines the type of 
      traversal to perform. */
   private void traverse( BSTNode r, int travType) 
   {
      if ( r != null) 
      {
         switch ( travType) 
         {
            case INORDER: 
                     traverse( r.getLeft(), travType);
                     visit( r); 
                     traverse( r.getRight(), travType);
                     break;
            case PREORDER: 
                     visit( r); 
                     traverse( r.getLeft(), travType);
                     traverse( r.getRight(), travType);
                     break;
            case POSTORDER: 
                     traverse( r.getLeft(), travType);
                     traverse( r.getRight(), travType);
                     visit( r); 
                     break;
            case LEVELORDER:
                     levelOrder( r);
                     break;
         }
      }
   }
   

   private void visit( BSTNode r) 
   {
       if ( r != null) System.out.println( r.getData());
   }


   /* traverse the subtree r using level order. */
   private void levelOrder( BSTNode r) 
   {
      BSTNode currNode = r;
      Queue<BSTNode> q = new Queue<BSTNode>();

      q.enqueue( currNode);

      while ( !q.isEmpty())
      {
         currNode = q.dequeue();
         visit( currNode);
         if ( currNode.getLeft() != null) 
            q.enqueue( currNode.getLeft());
         if ( currNode.getRight() != null) 
            q.enqueue( currNode.getRight());
      }
   }
}