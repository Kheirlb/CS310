  /**
   *  Program 2
   *  LinearList uses interface LinearListADT to create a doubly linked list.
   *  CS310-01
   *  3/13/2019
   *  @author  Karl Parks cssc1506
   */

package data_structures;

import java.util.Iterator;
import java.util.ConcurrentModificationException;

public class LinearList<E extends Comparable<E>> implements LinearListADT<E> {
	private Node<E> head;
	private Node<E> tail;
	private int currentSize, modCount;
	
	//Create node
	@SuppressWarnings("hiding")
	class Node<E> {
		E data;
		Node<E> next;
		Node<E> prev;
		public Node(E obj) {
			data = obj;
			next = null;
			prev = null;
		}
	}
	
	public LinearList() {
		head = null;
		tail = null;
		modCount = 0; //modification counter
		currentSize = 0;
	}
	
	@Override
	public boolean addFirst(E obj) { //adds node at front of list
		Node<E> newNode = new Node<E>(obj);
		if (currentSize > 0) { //sets previous pointer
			head.prev = newNode;
		}
		newNode.next = head; //sets new node next to current head node
		head = newNode; //changes head to new node
		if (currentSize == 0) { //empty matrix check
			tail = head;
		}
		currentSize++; //increment size
		modCount++; //increment modification counter
		return true;
	}

	@Override
	public boolean addLast(E obj) { //similar to addFirst
		Node<E> newNode = new Node<E>(obj);
		if (currentSize == 0) {
			addFirst(obj);
			return true;
		}
		tail.next = newNode;
		newNode.prev = tail;
		tail = newNode;
		currentSize++;
		modCount++;
		return true;
	}

	@Override
	public E removeFirst() { //removes first node
		if (currentSize != 0) {
			E tmp = head.data; //temporary variable for data return
			head = head.next; //change head to next node
			if (currentSize != 1) //change prev to null
				head.prev = null;
			currentSize--;
			modCount++;
			return tmp;
		}
		return null;
	}

	@Override
	public E removeLast() { //similar to removeFirst
		if (currentSize != 0) {
			E tmp = tail.data;
			tail = tail.prev;
			if (currentSize != 1)
				tail.next = null;
			currentSize--;
			modCount++;
			return tmp;
		}
		return null;
	}

	@Override
	public E remove(E obj) { //remove first object found
		Node<E> tmp = head;
		Node<E> tmpDesired = null;
		for (int i = 0; i < currentSize; i++) {
			if (tmp.data.compareTo(obj) == 0) {
				//found node to delete
				tmpDesired = tmp;
				if (i == 0) { //begining of list
					removeFirst();
				}
				else if (i == currentSize - 1) { //end of list
					removeLast();
				}
				else { //anywhere in the imddle
					tmpDesired.prev.next = tmpDesired.next;
					tmpDesired.next.prev = tmpDesired.prev;
					currentSize--;
					modCount++;
				}
				return obj;
			}
			tmp = tmp.next; //how to iterater through list
		}
		return null;
	}

	@Override
	public E peekFirst() { //reveals first data in first node
		return (currentSize == 0)? null : head.data;
	}

	@Override
	public E peekLast() { //reveals last data in last node
		return (currentSize == 0)? null : tail.data;
	}

	@Override
	public boolean contains(E obj) { //returns boolean if object found
		return obj == find(obj); //use find which will return null if no desired value found
	}

	@Override
	public E find(E obj) { //returns object found
		Node<E> tmp = head;
		for (int i = 0; i < currentSize; i++) {
			if (tmp.data == obj) {
				return obj;
			}
			tmp = tmp.next;
		}
		return null;
	}

	@Override
	public void clear() { //sets list to be empty
		head = null;
		tail = null;
		currentSize = 0;
		modCount++;		
	}

	@Override
	public boolean isEmpty() { //checks if empty
		return (currentSize == 0);
	}

	@Override
	public boolean isFull() { //checks if full (cannot be full - not array)
		return false;
	}

	@Override
	public int size() { //returns current size
		//printList();
		return currentSize;
	}

	public void printList() { //used for debugging
		Node<E> tmp = head;
		System.out.println("--- Printing List ---");
		System.out.println("Size: " + currentSize);
		for (int i = 0; i < currentSize; i++) {
			System.out.println(tmp.data);
			tmp = tmp.next;
		}
	}
	
	//@Override
	public Iterator<E> iterator() { //iterator helper method for enhanced for-loop
		return new IteratorHelper();
	}
		
	private class IteratorHelper implements Iterator<E> {
		private int count, expectedMod;
		Node<E> tmp = head;
		public IteratorHelper() {
			expectedMod = modCount; //checks for modifications
			count = 0;
		}
		
		public boolean hasNext() {
			return count != currentSize; //checks if at end of list
		}
	
		public E next() {
			if (modCount != expectedMod) { //modification error throw here
		        throw new ConcurrentModificationException("Cannot modify list during enhanced for-loop");
			}
			E tempData = tmp.data; //data to return
			tmp = tmp.next; //iterator using each tmp.next 
			count++;
			return tempData;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
