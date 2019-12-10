import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdOut;
public class Deque<Item> implements Iterable<Item> {
	private Node first=null;
	private Node last=null;
	private int length=0;
	private class Node{
		Item value;
		Node next=null;
		Node pre=null;
	}

    // construct an empty deque
    public Deque() {
    	
    }

    // is the deque empty?
    public boolean isEmpty() {
    	return this.length==0;
    }

    // return the number of items on the deque
    public int size() {
    	return this.length;
    }

    // add the item to the front
    public void addFirst(Item item) {
    	if(item==null) throw new IllegalArgumentException();
    	if(this.isEmpty()) {
    		this.first=new Node();
    		this.first.value=item;
    		this.last=this.first;
    		length+=1;
    	}
    	else {
    		Node temp=new Node();
    		temp.value=item;
    		temp.next=this.first;
    		this.first.pre=temp;
    		this.first=temp;
    		length+=1;
    	}
    }

    // add the item to the back
    public void addLast(Item item) {
    	if(item==null) throw new IllegalArgumentException();
    	if(this.isEmpty()) {
    		this.first=new Node();
    		this.first.value=item;
    		this.last=this.first;
    		length+=1;
    	}
    	else {
    		Node temp=new Node();
    		temp.value=item;
    		this.last.next=temp;
    		temp.pre=this.last;
    		this.last=temp;
    		length+=1;
    		
    	}
    	
    }

    // remove and return the item from the front
    public Item removeFirst() {
    	if(this.isEmpty())throw new NoSuchElementException();
    	else if(length==1) {
    		Item temp=this.first.value;
    		this.first=null;
    		this.last=null;
    		length-=1;
    		return temp;
    		
    	}
    	else {
    		Item temp=this.first.value;
    		Node next=this.first.next;
        	this.first.next=null;
        	next.pre=null;
        	this.first=next;
        	length-=1;
        	return temp;	
    	}
    	
    	
    }

    // remove and return the item from the back
    public Item removeLast() {
    	if(this.isEmpty())throw new NoSuchElementException();
    	else if(length==1) {
    		Item temp=this.first.value;
    		this.first=null;
    		this.last=null;
    		length-=1;
    		return temp;
    	}
    	else {
    		Item temp=this.last.value;
    		Node pre=this.last.pre;
        	this.last.pre=null;
        	pre.next=null;
        	this.last=pre;
        	length-=1;
        	return temp;	
    	}
    	
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
    	return new DequeIterator();
    	
    }
    
    private class DequeIterator implements Iterator<Item>{
    	private Node current=first;
    	private int size=length;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return size!=0;
		}

		@Override
		public Item next() {
			if(size==0) throw new NoSuchElementException();
			Item temp=current.value;
			current=current.next;
			size-=1;
			// TODO Auto-generated method stub
			return temp;
		}
		public void remove() {
			throw new UnsupportedOperationException ();
		}
    	
    }

    // unit testing (required)
    public static void main(String[] args) {
    	Deque<Integer> de=new Deque<Integer>();
    	de.addFirst(3);
    	de.addFirst(2);
    	de.addFirst(1);
    	de.addLast(4);
    	de.addLast(5);
    	de.removeFirst();
    	de.removeLast();
    	de.addFirst(8);
    	for(Integer temp : de) StdOut.println(temp);
    	de.removeFirst();
    	de.removeFirst();
    	de.removeFirst();
    	de.removeFirst();
    	de.removeFirst();
    		
    	
    	
    }

}