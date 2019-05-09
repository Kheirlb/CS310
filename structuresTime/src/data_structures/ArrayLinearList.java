  /**
   *  Program 1
   *  ArrayLinearList uses interface LinearListADT to create a circular array
   *  with an O(1) growth for almost all actions. 
   *  CS310-01
   *  2/16/2019
   *  @author  Karl Parks cssc1506
   */

package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException; 
import java.util.ConcurrentModificationException;

@SuppressWarnings("unused")
public class ArrayLinearList<E> implements LinearListADT_Array<E>, Iterable<E>{
	private E[] listA; //object array in class
	private int size, frontI, rearI, lastI, max, modCount;
	public ArrayLinearList() {
		this(DEFAULT_MAX_CAPACITY);
	}
	
	@SuppressWarnings("unchecked")
	public ArrayLinearList(int i) {
		max = i; //max capacity
		lastI = max - 1; //last index
		frontI = 0; //front index
		rearI = 0; //rear index
		modCount = 0; //modification counter
		listA = (E []) new Object[max];
		size = 0; //size (number of elements)
	}
	
	public void ends() {
		System.out.println("Front: " + frontI + " Rear: " + rearI);
		//System.out.println("Size: " + size);
	}
	
	public boolean addFirst(E obj) {
		if (size >= max) {
			return false; //check if full
		}
		if (size == 0) {
			frontI = 0; //empty matrix check
			rearI = 0;
		}
		else if (size == 1) { //one element
			if (frontI == 0) {
				frontI = lastI; //wrap for one element at front of array
			}
			else {
				frontI--; //element in middle of array
			}
		}
		else if (frontI == 0) {
			frontI = lastI; //wrap for circular array
		}
		else {
			frontI--; //addFirst drops front index
		}
		listA[frontI] = obj; //sets value
		size++; //increase size
		modCount++; //made modification, increment by 1
		return true;
	}

	public boolean addLast(E obj) {
		if (size >= max) {
			return false; //check if full
		}
		if (size == 0) {  //empty matrix check
			frontI = 0;
			rearI = 0;
		}
		else if (size == 1) { //one element
			if (frontI == lastI) {
				rearI = 0; //wrap for one element at back of array
			}
			else {
				rearI++; //element in middle of array
			}
		}
		else if (rearI == max-1) {
			rearI = 0; //wrap for circular array
		}
		else {
			rearI++; //addLast increases rear index 
		}
		listA[rearI] = obj;
		size++;
		modCount++;
		return true;
	}

	@Override
	public E removeFirst() {
		if (size == 0) {
			return null;
		}
		E temp = listA[frontI]; //stores temporary element
		listA[frontI] = null; //sets index to null
		if (frontI == max - 1) {
			frontI = 0; //wraps
		}
		else {
			frontI++; //increases frontI when removing front
		}
		size--; //decreases size by 1
		if (size == 0) {
			frontI = 0; 
			rearI = 0;
		}
		modCount++;
		return temp;
	}

	@Override
	public E removeLast() { //similar to removeFirst
		if (size == 0) {
			return null;
		}
		E temp = listA[rearI];
		listA[rearI] = null;
		if (rearI == 0) {
			rearI = max - 1;
		}
		else {
			rearI--;
		}
		size--;
		if (size == 0) {
			frontI = 0;
			rearI = 0;
		}
		modCount++;
		return temp;
	}
	
	@Override
	public E remove(E obj) {
		int count = 0; //first counter for finding variable
		int count2 = 0; //second counter for after finding variable for code debugging
		for (int i = frontI; count < size; i++) {
			if (i == max) {
				i = 0; //wraps
			}
			//System.out.println("Searching at index: " + i);
			if (listA[i] == obj) {
//				System.out.println("i: " + i);
//				System.out.println("count + count2: " + (count + count2));
//				System.out.println("size - 1: " + size);
				//once found, iterate through and move elements so no empty space inbetween frontI and rearI
				for (int j = i; count + count2 < size - 1; j++) {
//					System.out.println("j: " + j);
//					System.out.println("count + count2: " + (count + count2));
					if (j == max - 1) { //wrap
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
					count2++; //count until complete
				}
				if (rearI == 0) {
					rearI = max - 1; //move rear index if at 0
				}
				else {
					rearI--; //substracts rear index when removing element
				}
				size--;
				modCount++;
				if (size == 0) {
					frontI = 0; 
					rearI = 0;
				}
				return obj;
			}
			count++;
		}
		return null;
	}

	@Override
	public E peekFirst() { //peek at first element
		return listA[frontI];
	}

	@Override
	public E peekLast() { //peek at last element
		return listA[rearI];
	}

	@Override
	public boolean contains(E obj) { //does it contain that value
		return obj == find(obj); //use find which will return null if no desired value found
	}

	@Override
	public E find(E obj) { //finds desired element
		int count = 0;
		for (int i = frontI; count < size; i++) {
			if (i == max) {
				i = 0; //wrap
			}
			if (listA[i] == obj) {
				return listA[i]; //does not want to return index for some reason
			}
			count++;
		}
		return null;
	}

	@Override
	public void clear() { //sets size to 0 meaning the array is "empty"
		size = 0;
		frontI = 0;
		rearI = 0;
		listA[0] = null; //this must be done to avoid peekFirst/peekLast errors
		modCount++;
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
		return size;
	}
	
	//@Override
	public Iterator<E> iterator() { //iterator helper method for enhanced for-loop
		return new IteratorHelper();
	}
	
	private class IteratorHelper implements Iterator<E> {
		private int index, count, expectedMod;
		public IteratorHelper() {
			index = frontI;
			expectedMod = modCount; //checks for modifications
			count = 0;
		}
		
		public boolean hasNext() {
			return count != size;
		}
	
		public E next() {
			if (modCount != expectedMod) { //modification error throw here
		        throw new ConcurrentModificationException("Cannot modify list during enhanced for-loop");
			}
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
