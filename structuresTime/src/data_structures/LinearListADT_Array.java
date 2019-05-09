package data_structures;

import java.util.Iterator; 
import java.util.NoSuchElementException; 

@SuppressWarnings("unused")
public interface LinearListADT_Array<E> extends Iterable<E> { 
	public static final int DEFAULT_MAX_CAPACITY = 100;
	public void ends();
	public boolean addFirst(E obj);
	public boolean addLast(E obj);
	public E removeFirst();
	public E removeLast(); 
	public E remove(E obj); 
	public E peekFirst();
	public E peekLast(); 
	public boolean contains(E obj);
	public E find(E obj); 
	public void clear(); 
	public boolean isEmpty();
	public boolean isFull();
	public int size();
	public Iterator<E> iterator();

}
