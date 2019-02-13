package data_structures;

import java.util.Iterator;

@SuppressWarnings("rawtypes")
public class ArrayLinearList<E extends Comparable<E>> implements LinearListADT {
	Object[] storage;
	int size = 0;
	public ArrayLinearList() {
		// TODO Auto-generated constructor stub
		this(DEFAULT_MAX_CAPACITY);
	}
	
	public ArrayLinearList(int i) {
		// TODO Auto-generated constructor stub
		E[] storage = (E []) new Object[i];
		size = 0;
	}

	@Override
	public void ends() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addFirst(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addLast(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object removeFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object removeLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object remove(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object peekFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object peekLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean contains(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object find(Object obj) {
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
		return size;
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
