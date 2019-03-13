package data_structures;

import java.util.Iterator;
import java.util.ConcurrentModificationException;

public class LinearList<E extends Comparable<E>> implements LinearListADT<E> {
	private Node<E> head;
	private Node<E> tail;
	private int currentSize, modCount;
	
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
	
	public void LinkedList() {
		head = null;
		tail = null;
		modCount = 0; //modification counter
		currentSize = 0;
	}
	
	@Override
	public boolean addFirst(E obj) {
		Node<E> newNode = new Node<E>(obj);
		if (currentSize > 0) {
			head.prev = newNode;
		}
		newNode.next = head; 
		head = newNode;
		if (currentSize == 0) {
			tail = head;
		}
		currentSize++;
		modCount++;
		return true;
	}

	@Override
	public boolean addLast(E obj) {
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
	public E removeFirst() {
		if (currentSize != 0) {
			E tmp = head.data;
			head = head.next;
			if (currentSize != 1)
				head.prev = null;
			currentSize--;
			modCount++;
			return tmp;
		}
		return null;
	}

	@Override
	public E removeLast() {
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
	public E remove(E obj) {
		Node<E> tmp = head;
		Node<E> tmpDesired = null;
		for (int i = 0; i < currentSize; i++) {
//			System.out.println(tmp.data);
//			System.out.println(obj);
			if (tmp.data.compareTo(obj) == 0) {
				tmpDesired = tmp;
				//System.out.println("Found Node to Delete");
				if (i == 0) {
					removeFirst();
				}
				else if (i == currentSize - 1) {
					removeLast();
				}
				else {
					tmpDesired.prev.next = tmpDesired.next;
					tmpDesired.next.prev = tmpDesired.prev;
					currentSize--;
					modCount++;
				}
				return obj;
			}
			tmp = tmp.next;
		}
		return null;
	}

	@Override
	public E peekFirst() {
		return (currentSize == 0)? null : head.data;
	}

	@Override
	public E peekLast() {
		return (currentSize == 0)? null : tail.data;
	}

	@Override
	public boolean contains(E obj) {
		return obj == find(obj); //use find which will return null if no desired value found
	}

	@Override
	public E find(E obj) {
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
	public void clear() {
		head = null;
		tail = null;
		currentSize = 0;
		modCount++;		
	}

	@Override
	public boolean isEmpty() {
		return (currentSize == 0);
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public int size() {
		//printList();
		return currentSize;
	}

	public void printList() {
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
			return count != currentSize;
		}
	
		public E next() {
			if (modCount != expectedMod) { //modification error throw here
		        throw new ConcurrentModificationException("Cannot modify list during enhanced for-loop");
			}
			E tempData = tmp.data;
			tmp = tmp.next;
			count++;
			return tempData;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
