package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException; 

public class ArrayLinearList<E> implements LinearListADT<E>, Iterator<E>{
	private E[] listA;
	private int size, frontI, rearI, lastI, max, iterI;
	public ArrayLinearList() {
		this(DEFAULT_MAX_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayLinearList(int i) {
		max = i;
		lastI = max - 1;
		frontI = -1;
		rearI = 0;
		listA = (E []) new Object[max];
		size = 0;
	}

	public void ends() {
		// TODO Auto-generated method stub
		
	}

	public void setSize() {
		if (rearI > frontI) {
			size = rearI - (frontI - 1);
		}
		else if (rearI == frontI) {
			size = 1;
		}
		else {
			size = (max - frontI) + (rearI + 1);
		}
	}
	
	public boolean addFirst(E obj) {
		if (size >= max) {
			return false;
		}
		if (frontI < 0) {
			frontI = 0; //empty matrix check
			rearI = 0;
		}
		else if (size == 1) { //one element
			if (frontI == lastI) {
				rearI = 0; //one element at back of array
			}
			else if (frontI == 0) {
				frontI = lastI; //one element at front of array
			}
			else {
				rearI = frontI + 1; //element in middle of array
			}
		}
		else if (frontI == 0) {
			frontI = lastI;
		}
		else {
			frontI -= 1;
		}
        //System.out.println(frontI);
		listA[frontI] = obj;
		setSize();
        //System.out.println(size);
		return true;
	}

	public boolean addLast(E obj) {
		if (size >= max) {
			return false;
		}
		if (frontI < 0) {  //empty matrix check
			frontI = 0;
			rearI = 0;
		}
		else if (size == 1) { //one element
			if (frontI == lastI) {
				rearI = 0; //one element at back of array
			}
			else if (frontI == 0) {
				rearI += 1; //one element at front of array
			}
			else {
				rearI += 1; //element in middle of array
			}
		}
		else {
			rearI += 1;
		}
        //System.out.println(frontI);
		listA[rearI] = obj;
		setSize();
        //System.out.println(size);
		return true;
	}

	@Override
	public E removeFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E removeLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E remove(E obj) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return 0;
	}

	//@Override
	public Iterator<E> iterator() {
		iterI = frontI;
		System.out.println("frontI: " + frontI);
		System.out.println("rearI: " + rearI);
		return (Iterator<E>) this;
	}
	
	public boolean hasNext() {
		if (iterI <= rearI) {
			//System.out.println("hasNext because iterI <= rearI");
			return true; 
		}
		else if (rearI == iterI) {
			return false;
		}
		else if (rearI < iterI && iterI < max) {
			//System.out.println("hasNext because rearI < iterI && frontI < iterI");
			return true; 
		}
		else {
			//System.out.println("Does NOT have Next");
			return false;
		}
	}

	public E next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		if (iterI < max) {
			return listA[iterI++];
		}
		else if (iterI == max) {
			iterI = 0;
			return listA[iterI];
		}
		else {
			return listA[iterI++];
		}
	}
	
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
