/**
 * LinkedList class implements a doubly-linked list.
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType>
{
    /**
     * Construct an empty LinkedList.
     */
    public MyLinkedList( )
    {
        doClear( );
    }
    
    private void clear( )
    {
        doClear( );
    }
    
    /**
     * Change the size of this collection to zero.
     */
    public void doClear( )
    {
        beginMarker = new Node<>( null, null, null );
        endMarker = new Node<>( null, beginMarker, null );
        beginMarker.next = endMarker;
        
        theSize = 0;
        modCount++;
    }
    
    /**
     * Returns the number of items in this collection.
     * @return the number of items in this collection.
     */
    public int size( )
    {
        return theSize;
    }
    
    public boolean isEmpty( )
    {
        return size( ) == 0;
    }
    
    /**
     * Adds an item to this collection, at the end.
     * @param x any object.
     * @return true.
     */
    public boolean add( AnyType x )
    {
        add( size( ), x );   
        return true;         
    }
    
    /**
     * Adds an item to this collection, at specified position.
     * Items at or after that position are slid one position higher.
     * @param x any object.
     * @param idx position to add at.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */
    public void add( int idx, AnyType x )
    {
        addBefore( getNode( idx, 0, size( ) ), x );
    }
    
    /**
     * Adds an item to this collection, at specified position p.
     * Items at or after that position are slid one position higher.
     * @param p Node to add before.
     * @param x any object.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size(), inclusive.
     */    
    private void addBefore( Node<AnyType> p, AnyType x )
    {
        Node<AnyType> newNode = new Node<>( x, p.prev, p );
        newNode.prev.next = newNode;
        p.prev = newNode;         
        theSize++;
        modCount++;
    }   
    
    
    /**
     * Returns the item at position idx.
     * @param idx the index to search in.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType get( int idx )
    {
        return getNode( idx ).data;
    }
        
    /**
     * Changes the item at position idx.
     * @param idx the index to change.
     * @param newVal the new value.
     * @return the old value.
     * @throws IndexOutOfBoundsException if index is out of range.
     */
    public AnyType set( int idx, AnyType newVal )
    {
        Node<AnyType> p = getNode( idx );
        AnyType oldVal = p.data;
        
        p.data = newVal;   
        return oldVal;
    }
    
    /**
     * Gets the Node at position idx, which must range from 0 to size( ) - 1.
     * @param idx index to search at.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between 0 and size( ) - 1, inclusive.
     */
    private Node<AnyType> getNode( int idx )
    {
        return getNode( idx, 0, size( ) - 1 );
    }

    /**
     * Gets the Node at position idx, which must range from lower to upper.
     * @param idx index to search at.
     * @param lower lowest valid index.
     * @param upper highest valid index.
     * @return internal node corresponding to idx.
     * @throws IndexOutOfBoundsException if idx is not between lower and upper, inclusive.
     */    
    private Node<AnyType> getNode( int idx, int lower, int upper )
    {
        Node<AnyType> p;
        
        if( idx < lower || idx > upper )
            throw new IndexOutOfBoundsException( "getNode index: " + idx + "; size: " + size( ) );
            
        if( idx < size( ) / 2 )
        {
            p = beginMarker.next;
            for( int i = 0; i < idx; i++ )
                p = p.next;            
        }
        else
        {
            p = endMarker;
            for( int i = size( ); i > idx; i-- )
                p = p.prev;
        } 
        
        return p;
    }
    
    /**
     * Removes an item from this collection.
     * @param idx the index of the object.
     * @return the item was removed from the collection.
     */
    public AnyType remove( int idx )
    {
        return remove( getNode( idx ) );
    }
    
    /**
     * Removes the object contained in Node p.
     * @param p the Node containing the object.
     * @return the item was removed from the collection.
     */
    private AnyType remove( Node<AnyType> p )
    {
        p.next.prev = p.prev;
        p.prev.next = p.next;
        theSize--;
        modCount++;
        
        return p.data;
    }
    
    /**
     * Returns a String representation of this collection.
     */
    public String toString( )
    {
        StringBuilder sb = new StringBuilder( "[ " );

        for( AnyType x : this )
            sb.append( x + " " );
        sb.append( "]" );

        return new String( sb );
    }
    public void swap(int swap_idx1,int swap_idx2)
    {
    	if (swap_idx1<0||swap_idx1>size()-1) 
    	{
    		System.out.println("Sorry, The index 1 is not in the list");
    		return;
    	}
    	if (swap_idx2<0||swap_idx2>size()-1) 
    	{
    		System.out.println("Sorry, The index 2 is not in the list");
    		return;
    	}
    	
    	AnyType temp,n;
    	AnyType swp1=get(swap_idx1);
    	AnyType swp2=get(swap_idx2);
    	temp=swp1;
    	add(swap_idx1,swp2);
    	add(swap_idx2+1,temp);
    	remove((swap_idx1+1));
    	remove((swap_idx2+1));
    }
    
    public void shift(int shft)
    {
    	if(shft>0)
    	{
    		int temp=0;
    		while(temp!=shft)
    		{
    			AnyType i = get(size()-1);
    			add(0,i);
    			remove(size()-1);
    			temp++;
    		}
    	}
    	else if(shft<0)
    	{
    		int temp=0;
    		while(temp!=shft)
    		{
    			AnyType i = get(0);
    			add(i);
    			remove(0);
    			temp--;
    		}
    	}
    	else 
    	{
    		return;
    	}
    }
    
    public void erase(int idx,int offset) 
    {
    	if(idx<0||idx>size()-1) 
    	{
    		System.out.println("Sorry, index number is invalid.");
    		return;
    	}
    	if((idx+offset)<0||(idx+offset)>size()-1) 
    	{
    		System.out.println("Sorry, cannot remove from "+idx+" up to "+offset+" elements.");
    		return;
    	}
    	if(offset<0)
    	{
    		System.out.println("Cannot execute");
    		return;
    	}
    	int temp=0;
    	while(temp!=offset)
    	{
    		remove(idx);
    		temp++;
    	}
    }
    
    public void insertList( MyLinkedList<AnyType> insert_lst , int idx)
    {   
        int i = 0;
        while( i < insert_lst.size() )
            {
        	
            add( idx,insert_lst.get(0));
            insert_lst.remove(insert_lst.getNode(0));
            idx++;
            }
    }

    /**
     * Obtains an Iterator object used to traverse the collection.
     * @return an iterator positioned prior to the first element.
     */
    public java.util.Iterator<AnyType> iterator( )
    {
        return new LinkedListIterator( );
    }

    /**
     * This is the implementation of the LinkedListIterator.
     * It maintains a notion of a current position and of
     * course the implicit reference to the MyLinkedList.
     */
    private class LinkedListIterator implements java.util.Iterator<AnyType>
    {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;
        
        public boolean hasNext( )
        {
            return current != endMarker;
        }
        
        public AnyType next( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !hasNext( ) )
                throw new java.util.NoSuchElementException( ); 
                   
            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }
        
        public void remove( )
        {
            if( modCount != expectedModCount )
                throw new java.util.ConcurrentModificationException( );
            if( !okToRemove )
                throw new IllegalStateException( );
                
            MyLinkedList.this.remove( current.prev );
            expectedModCount++;
            okToRemove = false;       
        }
    }
    
    /**
     * This is the doubly-linked list node.
     */
    private static class Node<AnyType>
    {
        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
        {
            data = d; prev = p; next = n;
        }
        
        public AnyType data;
        public Node<AnyType>   prev;
        public Node<AnyType>   next;
    }
    
    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;
}

class TestLinkedList
{
    public static void main( String [ ] args )
    {
        MyLinkedList<Integer> lst = new MyLinkedList<>( );

        for( int i = 0; i < 10; i++ ) 
        {
                lst.add( i );
        }
        for( int i = 20; i < 30; i++ )
        {
                lst.add( 0, i );
        }
        System.out.println("The list is \n"+lst);
        lst.swap(0,lst.size()-1);
        System.out.println("After swapping The list becomes \n"+lst );
        lst.shift(-3);
        System.out.println("After shifting The list becomes \n"+lst);
        lst.erase(0,3);
        System.out.println("After erasing The list becomes \n"+ lst );
        MyLinkedList<Integer> lst2 = new MyLinkedList<>( );
        for( int i = 96; i < 101; i++ )
        {
        		lst2.add( i );
        }
        System.out.println("The 2nd list is \n"+lst2);
        lst.insertList(lst2,3);
        System.out.println("After inserting new list the 1st list becomes\n"+lst);
        
    }
}
