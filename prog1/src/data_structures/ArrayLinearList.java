  /**
   *  Program 1
   *  ArrayLinearList uses interface LinearListADT to create a circular array
   *  with no extra time.
   *  CS310-01
   *  2/16/2019
   *  @author  Karl Parks cssc1506
   */

package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException; 
import java.util.ConcurrentModificationException;

public class ArrayLinearList<E> implements LinearListADT<E>, Iterable<E>{
	private E[] listA;
	private int size, frontI, rearI, lastI, max;
	public ArrayLinearList() {
		this(DEFAULT_MAX_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayLinearList(int i) {
		max = i;
		lastI = max - 1;
		frontI = 0;
		rearI = 0;
		listA = (E []) new Object[max];
		size = 0;
	}
	
	public void ends() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean addFirst(E obj) {
		if (size >= max) {
			return false;
		}
		if (size == 0) {
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
		size++;
        //System.out.println(size);
		return true;
	}

	public boolean addLast(E obj) {
		if (size >= max) {
			return false;
		}
		if (size == 0) {  //empty matrix check
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
		size++;
        //System.out.println(size);
		return true;
	}

	@Override
	public E removeFirst() {
		E temp = listA[frontI];
		listA[frontI] = null;
		if (frontI == max - 1) {
			frontI = 0;
		}
		else {
			frontI += 1;
		}
		size -= 1;
		if (size == 0) {
			frontI = 0;
			rearI = 0;
		}
		return temp;
	}

	@Override
	public E removeLast() {
		E temp = listA[rearI];
		listA[rearI] = null;
		if (rearI == 0) {
			rearI = max - 1;
		}
		else {
			rearI -= 1;
		}
		size -= 1;
		if (size == 0) {
			frontI = 0;
			rearI = 0;
		}
		return temp;
	}
	
	@Override
	public E remove(E obj) {
		int count = 0;
		int count2 = 0;
		for (int i = frontI; count < size; i++) {
			if (i == max) {
				i = 0;
			}
			//System.out.println("Searching at index: " + i);
			if (listA[i] == obj) {
//				System.out.println("i: " + i);
//				System.out.println("count + count2: " + (count + count2));
//				System.out.println("size - 1: " + size);
				if (i == rearI) {
					listA[i] = null;
					if (rearI == 0) {
						rearI = max - 1;
					}
					else {
						rearI -= 1;
					}
					size -= 1;
					return obj;
				}
				for (int j = i; count + count2 < size - 1; j++) {
//					System.out.println("j: " + j);
//					System.out.println("count + count2: " + (count + count2));
					if (j == max - 1) {
						//System.out.println("list:[" + j + "] will be replaced by list[" + 0 + "]");
						listA[j] = listA[0];
						listA[0] = null;
						j = -1;
					}
					else {
						//System.out.println("list:[" + j + "] will be replaced by list[" + (j+1) + "]");
						listA[j] = listA[j + 1];
						listA[j+1] = null;
					}
					count2++;
				}
				if (rearI == 0) {
					rearI = max - 1;
				}
				else {
					rearI -= 1;
				}
				size -= 1;
				return obj;
			}
			count++;
		}
		return null;
	}

	@Override
	public E peekFirst() {
		return listA[frontI];
	}

	@Override
	public E peekLast() {
		return listA[rearI];
	}

	@Override
	public boolean contains(E obj) {
		return obj == find(obj);
	}

	@Override
	public E find(E obj) {
		int count = 0;
		for (int i = frontI; count < size; i++) {
			if (i == max) {
				i = 0;
			}
			if (listA[i] == obj) {
				return listA[i];
			}
			count++;
		}
		return null;
	}

	@Override
	public void clear() {
		size = 0;
		frontI = 0;
		rearI = 0;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean isFull() {
		return size == max;
	}

	@Override
	public int size() {
//		System.out.println("frontI: " + frontI);
//		System.out.println("rearI: " + rearI);
		return size;
	}
	
	//@Override
	public Iterator<E> iterator() {
		return new IteratorHelper();
	}
	
	private class IteratorHelper implements Iterator<E> {
		private int index, count;
		public IteratorHelper() {
			index = frontI;
			count = 0;
		}
		
		public boolean hasNext() {
			return count != size;
		}
	
		public E next() {
			E temp = listA[index++];
			if (index == max) {
				index = 0;
			}
			count++;
			return temp;
		}
		
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

}
