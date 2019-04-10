package data_structures;

import java.util.Iterator;

public class BinaryHeapPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E> {
	private int sequenceNumber, size;
	private Wrapper<E>[] binaryHeap;

	protected class Wrapper<E> implements Comparable<Wrapper<E>> {
		int num;
		E data;
		
		public Wrapper (E d){
			num = sequenceNumber++;
			data = d;
		}
		
		public int compareTo(Wrapper<E> o){
			if(((Comparable<E>)data).compareTo(o.data) == 0) {
				//System.out.println("They are equal so compares numbers");
				return (int) (num - o.num);
			}
			//System.out.println("They are not equal... but which is bigger?");
			return ((Comparable<E>)data).compareTo(o.data);
		}
	}
	
	public BinaryHeapPriorityQueue() {
		this(DEFAULT_MAX_CAPACITY);
	}
	
	public BinaryHeapPriorityQueue(int maxSize) {
		this.binaryHeap = new Wrapper[maxSize];
	}
	
	public void trickleUp(int n) {
		//System.out.println("Heap Stuff");
		//is data of parent less than data of current child?
		//parent is n/2 of binary heap... current is n,
	    //initially, n = size... which is good to start... but we need to do this recursively

		if ((binaryHeap[(n >> 1)]).compareTo((binaryHeap[n])) > 0) {
			//System.out.println("Parent: " + binaryHeap[n/2].data + " > " + binaryHeap[n].data + " (the Child)");
			swap(n);
			if (n > 3) { //checks if already checked root node
				trickleUp((n >> 1)); //recursive call
			}
		}
	}
	
	public void trickleDown(int index) {
		//System.out.println("trickleDown");
		int current = 1; //my heap starts at 1 instead of 0
		int child = getNextChild(current);
		while (child != -1 && (binaryHeap[current].compareTo(binaryHeap[child]) < 0) && (binaryHeap[child].compareTo(binaryHeap[size]) < 0)) {
			//System.out.println("Ran While Loop");
			//System.out.println("Child Index: binaryHeap[" + child + "] Child Value: " + binaryHeap[child].data);
			binaryHeap[current] = binaryHeap[child];
			current = child;
			child = getNextChild(current);
		}
		binaryHeap[current] = binaryHeap[size];
		//first index now equals data of last index
		//from "parent", look at left child, right child, determine which is smallest (min-heap)
		//check if smaller child is less than parent
		//if true -> swap smaller child and parent
		//do it again
		//stop when no more children exist or children are larger than parent
		//some conditions to avoid are no child, single child, double child cases
	}
	
	public int getNextChild(int current) {
		int left = (current << 1);
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
		//System.out.println("ssssswwwWWWAAAAAAAping SHIT");
		Wrapper<E> tmp = binaryHeap[(i >> 1)];
		binaryHeap[(i >> 1)] = binaryHeap[i];
		binaryHeap[i] = tmp;
	}
	
	@Override
	public boolean insert(E object) {
		Wrapper<E> newObj = new Wrapper<E>(object);
		binaryHeap[size+1] = newObj; 
		size++;
		if (size > 1) {
			trickleUp(size);
		}
		return true;
	}

	@Override
	public E remove() {
		if (size <= 0) {
			return null;
		}
		E tmp = binaryHeap[1].data;
		trickleDown(4);
		size--;
		return tmp;
	}

	@Override
	public boolean delete(E obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(E obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		return size;
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
	
	public void debugger() {
		System.out.println("\n--- Debugger Method ---");
		System.out.println("size:           " + size);
		System.out.println("sequenceNumber: " + sequenceNumber);
		for (int i = 1; i < size+1; i++) {
			System.out.println("idx: " + i + "\tnum: " + binaryHeap[i].num + "\tdata: " + binaryHeap[i].data);
		}
		System.out.println("");
	}
	
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
}
