/**
   *  Program 3
   *  This is a Prioity Queue Structure using a Binary Min Heap Implementation. 
   *  All elements start at index 1 for improved reability and math operations. 
   *  CS310-01
   *  4/10/2019
   *  @author  Karl Parks cssc1506
   */

package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

public class BinaryHeapPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E> {
	private int sequenceNumber, size, modCount, capacity;
	private Wrapper<E>[] binaryHeap;

	@SuppressWarnings("hiding")
	protected class Wrapper<E> implements Comparable<Wrapper<E>> {
		int num; //each element will have a priority assigned, this enables stability
		E data; //object data variable
		
		public Wrapper (E d){
			num = sequenceNumber++; //sequenceNumber will always increase
			data = d;
		}
		
		@SuppressWarnings("unchecked")
		public int compareTo(Wrapper<E> o){
			if(((Comparable<E>)data).compareTo(o.data) == 0) {
				//System.out.println("They are equal so compares priority next");
				return (int) (num - o.num);
			}
			//System.out.println("They are not equal... but which is bigger?");
			return ((Comparable<E>)data).compareTo(o.data);
		}
	}
	
	public BinaryHeapPriorityQueue() {
		this(DEFAULT_MAX_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public BinaryHeapPriorityQueue(int maxSize) {
		this.binaryHeap = new Wrapper[maxSize+1]; //maxSize + 1 is required for starting at index 1
		capacity = maxSize;
	}
	
	public void trickleUp(int n) {
		//System.out.println("trickleUp");
		//parent is n/2 (n >> 1) of binary heap... current is n,
	    //initially, n = size... which is good to start... but we need to do this recursively to trickle up the entire tree
		if ((binaryHeap[(n >> 1)]).compareTo((binaryHeap[n])) > 0) {
			//System.out.println("Parent: " + binaryHeap[n/2].data + " > " + binaryHeap[n].data + " (the Child)");
			swap(n); //simply swap function
			if (n > 3) { //checks if already checked root node
				trickleUp((n >> 1)); //recursive call
			}
		}
	}
	
	public void trickleDown(int index) {
		//used riggins text
		//System.out.println("trickleDown");
		int current = index; //my heap starts at 1 instead of 0
		int child = getNextChild(current);
		while (child != -1 && (binaryHeap[current].compareTo(binaryHeap[child]) < 0) && (binaryHeap[child].compareTo(binaryHeap[size]) < 0)) {
			//System.out.println("Child Index: binaryHeap[" + child + "] Child Value: " + binaryHeap[child].data);
			binaryHeap[current] = binaryHeap[child];
			current = child;
			child = getNextChild(current);
		}
		binaryHeap[current] = binaryHeap[size];
		
		//trickleDown explanation in pseudocode:
		//first index now equals data of last index
		//from "parent", look at left child, right child, determine which is smallest (min-heap)
		//check if smaller child is less than parent
		//if true -> swap smaller child and parent
		//do it again
		//stop when no more children exist or children are larger than parent
		//some conditions to avoid are no child, single child, double child cases
	}
	
	public int getNextChild(int current) {
		//used riggins text
		//this method checks which child is smaller, or if there even is a child
		int left = (current << 1); //array starts at 1 instead of 0 for easy math
		int right = left+1;
		if (right < size) { //checks for two children
			if(binaryHeap[left].compareTo(binaryHeap[right]) < 0 ) {
				return left;
			}
			return right;
		}
		if (left < size) {
			return left;
		}
		return -1; //no children	
	}
	
	public void swap(int i) {
		//simple swap method
		Wrapper<E> tmp = binaryHeap[(i >> 1)];
		binaryHeap[(i >> 1)] = binaryHeap[i];
		binaryHeap[i] = tmp;
	}
	
	@Override
	public boolean insert(E object) {
		if (isFull()) { //quick check if array is full
			return false;
		}
		Wrapper<E> newObj = new Wrapper<E>(object);
		binaryHeap[size+1] = newObj; //add new wrapper object to array
		size++; //increse size
		if (size > 1) {
			trickleUp(size); //trickleUp and reheapify
		}
		modCount++; //we modified something so we increase the modification number
		return true;
	}

	@Override
	public E remove() {
		//simple to insert
		if (isEmpty()) { //quick check if array is empty
			return null;
		}
		E tmp = binaryHeap[1].data;
		trickleDown(1);
		size--;
		modCount++;
		return tmp;
	}

	@Override
	public boolean delete(E obj) {
		boolean found = false;
		//search array
		for (int i = 1; i <= size; i++) { //repeat if necessary
			if (obj.compareTo(binaryHeap[i].data) == 0) { 		//if found, remove and trickle down from that index
				//System.out.println("Found Something");
				trickleDown(i);
				size--;
				modCount++;
				found = true; //return true if something was found
				delete(obj); //recursive call, necessary because of changing size after trickleDown
			}
		}
		//return true if something was found
		return found;
	}

	@Override
	public E peek() {
		if (isEmpty()) { //quick check if array is empty
			return null;
		}
		return binaryHeap[1].data;
	}

	@Override
	public boolean contains(E obj) {
		boolean found = false;
		//loops through array
		for (int i = 1; i <= size; i++) { //repeat if necessary
			if (obj.compareTo(binaryHeap[i].data) == 0) { 
				//System.out.println("Found Something");
				found = true; //return true if something was found
			}
		}
		return found;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		modCount++; //clearing is a modification
		size = 0; //sets size to 0 but doesn't change anything else. This affects peek.
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean isFull() {
		return (size >= capacity);
	}
	
	public void debugger() {
		//my debugger method for printing things without the iterator
		System.out.println("\n--- Debugger Method ---");
		System.out.println("size:           " + size);
		System.out.println("sequenceNumber: " + sequenceNumber);
		for (int i = 1; i <= size; i++) {
			System.out.println("idx: " + i + "\tnum: " + binaryHeap[i].num + "\tdata: " + binaryHeap[i].data);
		}
		System.out.println("");
	}
	
	public void printSort() {
		//if you would like a sorted array
		//this method uses remove in a normal for-loop with the original size
		Wrapper<E>[] duplicate = binaryHeap.clone();
		int startSize = size;
		System.out.print("\nSorted: \t");
		for (int i = 0; i < startSize; i++) {
			System.out.print(remove() + " ");
		}
		size = startSize;
		binaryHeap = duplicate;
	}
	
	@Override
	//this iterator only returns an iterator of the objects in the PQ, in no particular order
	//if you wanted a sorted array printed, use the above printSort method
	public Iterator<E> iterator() { //iterator helper method for enhanced for-loop
		return new IteratorHelper();
	}
		
	private class IteratorHelper implements Iterator<E> {
		private int count, expectedMod;
		
		public IteratorHelper() {
			expectedMod = modCount; //checks for modifications
			count = 0;
		}
		
		public boolean hasNext() {
			return count != size; //checks if at end of list
		}
	
		public E next() {
			if (modCount != expectedMod) { //modification error throw here
		        throw new ConcurrentModificationException("Cannot modify list during enhanced for-loop");
			}
			count++;
			return binaryHeap[count].data;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
