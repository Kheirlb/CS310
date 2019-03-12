package data_structures;

import java.util.Iterator;

public class LinearList<E extends Comparable<E>> implements LinearListADT<E> {
	private Node<E> head;
	private Node<E> tail;
	private int currentSize;
	
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
		return true;
	}

	@Override
	public E removeFirst() {
		//System.out.println("error");
		if (currentSize != 0) {
			E tmp = head.data;
			head = head.next;
			if (currentSize != 1)
				head.prev = null;
			currentSize--;
			return tmp;
		}
		return null;
	}

	@Override
	public E removeLast() {
		//System.out.println("error");
		if (currentSize != 0) {
			E tmp = tail.data;
			tail = tail.prev;
			if (currentSize != 1)
				tail.next = null;
			currentSize--;
			return tmp;
		}
		return null;
	}

	@Override
	public E remove(E obj) {
		Node<E> tmp = head;
		Node<E> tmpDesired = null;
		for (int i = 0; i < currentSize; i++) {
			if (tmp.data == obj) {
				tmpDesired = tmp;
				System.out.println("Found Node to Delete");
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
				}
				return obj;
			}
			tmp = tmp.next;
		}
		return null;
	}

	@Override
	public E peekFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peekLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(E obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E find(E obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		printList();
		return currentSize;
	}

	public void printList() {
		Node<E> tmp = head;
		System.out.println("--- Printing List ---");
		for (int i = 0; i < currentSize; i++) {
			//System.out.println("Confused, i: " +  i);
			System.out.println(tmp.data);
			tmp = tmp.next;
		}
	}
	
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
