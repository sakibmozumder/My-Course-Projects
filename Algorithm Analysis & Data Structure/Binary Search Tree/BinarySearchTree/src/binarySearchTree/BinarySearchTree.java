package binarySearchTree;
import java.util.*;


import java.lang.*;
@SuppressWarnings("unused")
public class BinarySearchTree <AnyType extends Comparable<? super AnyType>>{
	
	
	 public BinarySearchTree( )
	    {
	        root = null;
	    }

	    /**
	     * Insert into the tree; duplicates are ignored.
	     * @param x the item to insert.
	     */
	    public void insert( AnyType x )
	    {
	        root = insert( x, root );
	    }

	    /**
	     * Remove from the tree. Nothing is done if x is not found.
	     * @param x the item to remove.
	     */
	    public void remove( AnyType x )
	    {
	        root = remove( x, root );
	    }

	    /**
	     * Find the smallest item in the tree.
	     * @return smallest item or null if empty.
	     * @throws UnderflowException 
	     */
	    public AnyType findMin( ) throws UnderflowException
	    {
	        if( isEmpty( ) )
	            throw new UnderflowException( );
	        return findMin( root ).element;
	    }

	    /**
	     * Find the largest item in the tree.
	     * @return the largest item of null if empty.
	     * @throws UnderflowException 
	     */
	    public AnyType findMax( ) throws UnderflowException
	    {
	        if( isEmpty( ) )
	            throw new UnderflowException( );
	        return findMax( root ).element;
	    }

	    /**
	     * Find an item in the tree.
	     * @param x the item to search for.
	     * @return true if not found.
	     */
	    public boolean contains( AnyType x )
	    {
	        return contains( x, root );
	    }

	    /**
	     * Make the tree logically empty.
	     */
	    public void makeEmpty( )
	    {
	        root = null;
	    }

	    /**
	     * Test if the tree is logically empty.
	     * @return true if empty, false otherwise.
	     */
	    public boolean isEmpty( )
	    {
	        return root == null;
	    }

	    /**
	     * Print the tree contents in sorted order.
	     */
	    public void printTree( )
	    {
	        if( isEmpty( ) )
	            System.out.println( "Empty tree" );
	        else
	            printTree( root );
	    }
	    
	    
	    public int nodeCount( )
	    {
	    	 if( isEmpty( ) )
	    	 {
		            return 0;
	    	 }
		     else
	    	 {
		        return nodeCount( root );
	    	 }
	    }
	    
	    
	    
	    public boolean isFull()
	    {
	    	return isFull(root);
	    }
	    
	    public boolean compareStructure(BinarySearchTree<AnyType> t)
	    {
	        if(root == null && t == null)
	        {
	            return true;
	        }
	        else
	        {
	            return compareStructure(root, t.root);
	        }
	    }
	    
	    
	    public boolean equals(BinarySearchTree<AnyType> t)
	    {
	        if(root == null && t == null)
	        {
	            return true;
	        }
	        else
	        {
	            return equals(root, t.root);
	        }
	    }
	    
	    public BinarySearchTree<AnyType> copy()
	    {
	        BinarySearchTree<AnyType> t = new BinarySearchTree<>();
	        t.root = copy(root);
	        return t;
	    }
	    
	    public BinarySearchTree<AnyType> mirror()
	    {
	        BinarySearchTree<AnyType> t = new BinarySearchTree<>();
	        t.root = mirror(root);
	        return t;
	    }
	    public boolean isMirror(BinarySearchTree<AnyType> t)
	    {
	        if(this.root == null && t.root == null)
	            return true;
	        else
	            return isMirror(this.root, t.root);
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    public void rotateLeft( AnyType t )throws UnderflowException
	    {
	    	if( isEmpty( ) )
	            throw new UnderflowException( );
	    	rotatewithLeft(t,root);
	        return;
	    }
	    
	    public void rotateright( AnyType t )throws UnderflowException
	    {
	    	if( isEmpty( ) )
	            throw new UnderflowException( );
	    	rotatewithRight(t,root);
	        return;
	    }

	    /**
	     * Internal method to insert into a subtree.
	     * @param x the item to insert.
	     * @param t the node that roots the subtree.
	     * @return the new root of the subtree.
	     */
	    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
	    {
	        if( t == null )
	            return new BinaryNode<>( x, null, null );
	        
	        int compareResult = x.compareTo( t.element );
	            
	        if( compareResult < 0 )
	            t.left = insert( x, t.left );
	        else if( compareResult > 0 )
	            t.right = insert( x, t.right );
	        else
	            ;  // Duplicate; do nothing
	        return t;
	    }

	    /**
	     * Internal method to remove from a subtree.
	     * @param x the item to remove.
	     * @param t the node that roots the subtree.
	     * @return the new root of the subtree.
	     */
	    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
	    {
	        if( t == null )
	            return t;   // Item not found; do nothing
	            
	        int compareResult = x.compareTo( t.element );
	            
	        if( compareResult < 0 )
	            t.left = remove( x, t.left );
	        else if( compareResult > 0 )
	            t.right = remove( x, t.right );
	        else if( t.left != null && t.right != null ) // Two children
	        {
	            t.element = findMin( t.right ).element;
	            t.right = remove( t.element, t.right );
	        }
	        else
	            t = ( t.left != null ) ? t.left : t.right;
	        return t;
	    }

	    /**
	     * Internal method to find the smallest item in a subtree.
	     * @param t the node that roots the subtree.
	     * @return node containing the smallest item.
	     */
	    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
	    {
	        if( t == null )
	            return null;
	        else if( t.left == null )
	            return t;
	        return findMin( t.left );
	    }

	    /**
	     * Internal method to find the largest item in a subtree.
	     * @param t the node that roots the subtree.
	     * @return node containing the largest item.
	     */
	    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
	    {
	        if( t != null )
	            while( t.right != null )
	                t = t.right;

	        return t;
	    }

	    /**
	     * Internal method to find an item in a subtree.
	     * @param x is item to search for.
	     * @param t the node that roots the subtree.
	     * @return node containing the matched item.
	     */
	    private boolean contains( AnyType x, BinaryNode<AnyType> t )
	    {
	        if( t == null )
	            return false;
	            
	        int compareResult = x.compareTo( t.element );
	            
	        if( compareResult < 0 )
	            return contains( x, t.left );
	        else if( compareResult > 0 )
	            return contains( x, t.right );
	        else
	            return true;    // Match
	    }

	    /**
	     * Internal method to print a subtree in sorted order.
	     * @param t the node that roots the subtree.
	     */
	    private void printTree( BinaryNode<AnyType> t )
	    {
	        if( t != null )
	        {
	            printTree( t.left );
	            System.out.println( t.element );
	            printTree( t.right );
	        }
	    }

	    /**
	     * Internal method to compute height of a subtree.
	     * @param t the node that roots the subtree.
	     */
	    private int height( BinaryNode<AnyType> t )
	    {
	        if( t == null )
	            return -1;
	        else
	            return 1 + Math.max( height( t.left ), height( t.right ) );    
	    }
	    
	    private int nodeCount (BinaryNode<AnyType> t)
	    {
	    	if (t==null)
	    	{
	    		return 0;
	    	}
	    	else return 1 + nodeCount(t.left) + nodeCount(t.right) ;
	    }
	    
	    private boolean isFull (BinaryNode<AnyType> t)
	    {
	    	
	    	if (t.right==null && t.left==null)
	    	{
	    		return true;
	    	}
	    	else if(t.left != null && t.right != null)
	    	{
	            return isFull(t.left) && isFull(t.right);
	    	}
	    	else
	    	{
	    		return false;
	    	}
	    }
	    
	    private boolean compareStructure (BinaryNode<AnyType> t1, BinaryNode<AnyType> t2)
	    {
	    	
	    	{
	            if(t1 == null && t2 == null)
	            {
	                return true;
	            }
	            else if(t1 != null && t2 != null)
	            {
	                return compareStructure(t1.left, t2.left) && compareStructure(t1.right, t2.right);
	            }
	            else
	            {
	                return false;
	            }
	        }
	    }
	    
	    
	    
	    private boolean equals(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2)
	    {
	        if(t1 == null && t2 == null)
	        {
	            return true;
	        }
	        else if(t1 != null && t2 != null)
	        {
	            return (t1.element == t2.element && equals(t1.left, t2.left) && equals(t1.right, t2.right));
	        }
	        else
	        {
	            return false;
	        }
	    }
	    
	    
	    
	    private BinaryNode<AnyType> copy(BinaryNode<AnyType> t)
	    {
	        if(t == null)
	        {
	            return null;
	        }
	        else
	        {
	            return new BinaryNode<AnyType>(t.element, copy(t.left), copy(t.right));
	        }
	    }
	    
	    
	   
	    private BinaryNode<AnyType> mirror(BinaryNode<AnyType> t)
	    {
	        if(t == null)
	        {
	            return null;
	        }
	        else
	        {
	            return new BinaryNode<AnyType>(t.element, mirror(t.right), mirror(t.left));
	        }
	    }
	    
	    
	    private boolean isMirror(BinaryNode<AnyType> t1, BinaryNode<AnyType> t2)
	    {
	        if(t1 == null && t2 == null)
	            return true;
	        else if(t1 != null && t2 != null)
	            return (isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left));
	        else
	            return false;
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    private BinaryNode<AnyType> rotatewithLeft( AnyType t,BinaryNode<AnyType> k2 )
	    {
	        if (contains(t,k2))
	        {
	        	BinaryNode<AnyType> k1 = k2.right;
	        	k2.right = k1.left;
	        	k1.left = k2;
	        	return k1;
	        }
	        else return null;
	    }
	    
	    private BinaryNode<AnyType> rotatewithRight( AnyType t, BinaryNode<AnyType> k1 )
	    {
	    	if (contains(t,k1))
	    	{
	    		BinaryNode<AnyType> k2 = k1.left;
	        	k1.left = k2.right;
	        	k2.right = k1;
	        	return k2;
	    	}
	    	else 
	    		return null;
	    }
	    
	    // Basic node stored in unbalanced binary search trees
	    private static class BinaryNode<AnyType>
	    {
	            // Constructors
	        BinaryNode( AnyType theElement )
	        {
	            this( theElement, null, null );
	        }

	        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
	        {
	            element  = theElement;
	            left     = lt;
	            right    = rt;
	        }

	        AnyType element;            // The data in the node
	        BinaryNode<AnyType> left;   // Left child
	        BinaryNode<AnyType> right;  // Right child
	    }


	      /** The tree root. */
	    private BinaryNode<AnyType> root;


	        // Test program
	    public static void main( String [ ] args ) throws UnderflowException
	    {
	        BinarySearchTree<Integer> t1 = new BinarySearchTree<>( );
	        BinarySearchTree<Integer> t2 = new BinarySearchTree<>( );
	        BinarySearchTree<Integer> t3 = new BinarySearchTree<>( );
	        BinarySearchTree<Integer> t4 = new BinarySearchTree<>( );
	        final int NUMS = 39;
	        final int GAP  =   37;
	        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
	            {
	        		t1.insert( i );
	            }
	        
	        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
            {
        		t2.insert( i );
            }
	        
	        for(int i=0;i<38;i++)
	        {
	        	t3.insert(i+1);
	        }
	     
	        int node_num= t1.nodeCount();
	        
	        System.out.println("The number of node is: "+node_num);
	        
	        if(t1.isFull())
	        {
	        	System.out.println("B-Tree is Full");
	        }
	        else
	        {
	        	System.out.println("B-Tree is not Full");
	        }
	        
	        if(t1.compareStructure(t2))
	        {
	        	System.out.println("B-Trees have the same structure");
	        }
	        else
	        {
	        	System.out.println("B-Trees do not have the same structure");
	        }
	        if(t1.compareStructure(t3))
	        {
	        	System.out.println("B-Trees have the same structure");
	        }
	        else
	        {
	        	System.out.println("B-Trees do not have the same structure");
	        }
	        
	        if(t1.equals(t2))
	        {
	        	System.out.println("B-Trees are equal");
	        }
	        else
	        {
	        	System.out.println("B-Trees are not equal");
	        }
	        
	        
	        if(t1.equals(t3))
	        {
	        	System.out.println("B-Trees are equal");
	        }
	        else
	        {
	        	System.out.println("B-Trees are not equal");
	        }
	        t4=t1.copy();
	        if(t1.equals(t4))
	        {
	        	System.out.println("B-Trees are equal");
	        }
	        else
	        {
	        	System.out.println("B-Trees are not equal");
	        }
	        BinarySearchTree<Integer> t5 = new BinarySearchTree<>( );
	        t5=t1.mirror();
	        if (t1.isMirror(t5))
	        	System.out.println("These two are Mirrors");
	        else
	        	System.out.println("Not Mirrors");
	        	
	        t1.rotateLeft(30);
	        t1.printTree();
	        t1.rotateright(30);
	        
	       
	        
	        

	        /*System.out.println( "Checking... (no more output means success)" );

	        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS )
	            t.insert( i );

	        for( int i = 1; i < NUMS; i+= 2 )
	            t.remove( i );

	        if( NUMS < 40 )
	            t.printTree( );
	        if( t.findMin( ) != 2 || t.findMax( ) != NUMS - 2 )
	            System.out.println( "FindMin or FindMax error!" );

	        for( int i = 2; i < NUMS; i+=2 )
	             if( !t.contains( i ) )
	                 System.out.println( "Find error1!" );

	        for( int i = 1; i < NUMS; i+=2 )
	        {
	            if( t.contains( i ) )
	                System.out.println( "Find error2!" );
	        }*/
	    }
	

}
