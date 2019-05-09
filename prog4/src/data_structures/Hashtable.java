/**
   *  Program 4
   *  This is the HashTable for O(1) operations
   *  Utilizes a private LinkedListDS class provided by professor kraft
   *  CS310-01
   *  5/7/2019
   *  @author  Karl Parks cssc1506
   */

package data_structures;

import java.util.Iterator;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class Hashtable<K extends Comparable<K>,V> implements DictionaryADT<K,V> {
	private LinkedListDS<HashElement<K,V>> [] list; //provided LinkedListDS
	
	private int currentSize, capacity, tableSize, modCount;
	private static int defaultCapacity = 16; //similar to Java 
	
	//size = num of elements in hashtable
	//capacity = capacity of hashtable before resize (list technically larger)
	//tableSize = actual size of hashtable
	
	public Hashtable() {
		this(defaultCapacity);
	}
	
	@SuppressWarnings("unchecked")
	public Hashtable(int inputSize) {
		capacity = inputSize;
		tableSize = (int) (capacity * 1.3f);
		list = (LinkedListDS<HashElement<K,V>>[]) new LinkedListDS[tableSize];
		for (int i = 0; i < tableSize; i++) {
			list[i] = new LinkedListDS<HashElement<K,V>>();
		}
		currentSize = 0;
		modCount = 0;
	}
	
	@SuppressWarnings("hiding")
	//hashelement or "node" for chaining
	private class HashElement<K,V> implements Comparable<HashElement<K,V>>{
		K key;
		V value;
		
		public HashElement(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@SuppressWarnings("unchecked")
		@Override
		public int compareTo(HashElement<K, V> o) {
			return (((Comparable<K>)this.key).compareTo(o.key));
		}
	}
	
	@Override
	//returns true if it contains the desired key
	public boolean contains(K key) {
		return list[myHash(key.hashCode())].contains(new HashElement<K, V>(key, null));
	}

	@Override
	//adds a key-value pair to the hash
	public boolean add(K key, V value) {
		if (isFull())
			return false;
		if (contains(key)) { //no duplicate value allowed
			return false;
		}
		@SuppressWarnings({ "unchecked", "rawtypes" })
		HashElement<K,V> he = new HashElement(key, value);
		int hashval = myHash(key.hashCode());
		list[hashval].addFirst(he);
		//System.out.println("hashval: " + hashval);
		currentSize++;
		modCount++;
		return true;
	}

	@Override
	public boolean delete(K key) {
		if (isEmpty()) { //check if empty before attempting
			return false;
		}
		if (!contains(key)) { //check if it has the key
			return false;
		}
		int hashval = myHash(key.hashCode());
		list[hashval].removeFirst();
		currentSize--;
		modCount++;
		return true;
	}

	@Override
	//returns the value associated with that key, chains if necessary
	public V getValue(K key) {
		int hashval = myHash(key.hashCode());
		//System.out.println("hashval: " + hashval);
		for (HashElement<K,V> he: list[hashval])
			if (((Comparable<K>)key).compareTo(he.key)==0)
				return he.value;
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	//returns the first key associated with that value
	public K getKey(V value) {
        for (LinkedListDS<Hashtable<K, V>.HashElement<K, V>> mainList: list) {
            for (HashElement<K, V> he : mainList) {
                if (((Comparable<V>) he.value).compareTo(value) == 0)
                    return he.key;
            }
        }
        return null;
	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public boolean isFull() {
		return currentSize >= capacity; //no resize function means there is a max
	}

	@Override
	public boolean isEmpty() {
		return currentSize <= 0;
	}

	@Override
	public void clear() {
		//reset all linked lists
		for (LinkedListDS<HashElement<K,V>> i : list) {
			i.makeEmpty();
		}
		currentSize = 0;
		modCount++;
	}

	@Override
	public Iterator<K> keys() {
		return new KeyIteratorHelper();
	}

	@Override
	public Iterator<V> values() {
		return new ValueIteratorHelper();
	}

	//Source: CS 310 Course Reader pg 195
    private abstract class IteratorHelper<E> implements Iterator<E> {
        HashElement<K, V>[] nodes;
        int idx;
        int expectedMod;

        @SuppressWarnings({ "unchecked", "rawtypes"})
		IteratorHelper() {
            nodes = new HashElement[currentSize];
            idx = 0;
            int j = 0;
            expectedMod = modCount;

            for (int i = 0; i < tableSize; i++) {//fill iterator array, must use entire table
                for (HashElement n : list[i]) {
                	//System.out.println("Node j: " + j + " updated");
                    nodes[j++] = n;
                }
            }
            nodes = shellSort(nodes);
        }
        
        //CS 310 Couse Reader pg. 147
        @SuppressWarnings({ "rawtypes", "unchecked" })
		private HashElement<K, V>[] shellSort(HashElement<K, V>[] n) {
            HashElement<K, V> temp;
            if (n.length < 2) //does not need to sort if only 2 elements
                return n;
            int in, out, h = 1;
            int size = n.length;

            while (h <= size / 3) //calculate gaps in shells
                h = h * 3 + 1;
            while (h > 0) {
                for (out = h; out < size; out++) {
                    temp = n[out];
                    in = out;
                    while (in > h - 1 && ((Comparable) n[in - h]).compareTo(temp) >= 0) {
                        n[in] = n[in - h];
                        in -= h;
                    }
                    n[in] = temp;
                } // end for
                h = (h - 1) / 3;
            } // end while
            return n;
        }

        public boolean hasNext() {
        	if (modCount != expectedMod) { //modification error throw here
		        throw new ConcurrentModificationException("Cannot modify list during enhanced for-loop");
			}
            return idx < currentSize;
        }

        //next method of iterator specific to key and value iterators 
        public abstract E next();

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    class KeyIteratorHelper extends IteratorHelper<K> {
        KeyIteratorHelper() {
            super();
        }

      //Source: CS 310 Course Reader pg 194
        public K next() {
            if (!hasNext()) 
            	throw new NoSuchElementException();
            return (K) nodes[idx++].key;
        }
    }

    class ValueIteratorHelper extends IteratorHelper<V> {
        ValueIteratorHelper() {
            super();
        }

        //Source: CS 310 Course Reader pg 195
        public V next() {
            if (!hasNext()) 
            	throw new NoSuchElementException();
            return (V) nodes[idx++].value;
        }
    }
    
    //helper function
    private int myHash(int hash) {
    	return (hash & 0x7FFFFFFF % tableSize);
    }
    
    //---------------------------------------------------------------------------------
    private class LinkedListDS<E> implements ListADT<E> {
        /////////////////////////////////////////////////////////////////
        class Node<T> {
            T data;
            Node<T> next;
            
            public Node(T obj) {
                data = obj;
                next = null;
                }
            }
        // END CLASS NODE ///////////////////////////////////////////////
        
        /////////////////////////////////////////////////////////////////
        class ListIteratorHelper implements Iterator<E> {        
            Node<E> index;
            
            public ListIteratorHelper() {
                index = head;
                }
                
            public boolean hasNext() {
                return index != null;
                }
                
            public E next() {
                if(!hasNext())
                    throw new NoSuchElementException();
                E tmp = index.data;
                index = index.next;
                return tmp;
                }
                
            public void remove() {
                throw new UnsupportedOperationException();
                }
                
            }
        // END CLASS LIST_ITERATOR_HELPER //////////////////////////////
        
        
        private Node<E> head, tail;
        private int currentSize;
        
        public LinkedListDS() {
            head = tail = null;
            currentSize = 0;
            }

        public void addFirst(E obj) {
            Node<E> newNode = new Node<E>(obj);
            if(isEmpty())
                head = tail = newNode;
            else {
                newNode.next = head;            
                head = newNode;
                }
            currentSize++;
            }
            
        public void addLast(E obj) {
            Node<E> newNode = new Node<E>(obj);
            if(isEmpty())
                head = tail = newNode;
            else {
                tail.next = newNode;
                tail = newNode;
                }
            currentSize++;
            }
            
        public E removeFirst() {
            if(isEmpty())
                return null;
            E tmp = head.data;
            head = head.next;
            if(head == null)
                head = tail = null;
            currentSize--;
            return tmp;
            }
            
        public E removeLast() {
            if(isEmpty())
                return null;
            E tmp = tail.data;
            if(head == tail) // only one element in the list
                head = tail = null;
            else {    
                Node<E> previous = null, current = head;
                while(current != tail) {
                    previous = current;
                    current = current.next;
                    }
                previous.next = null;
                tail = previous;
                }
            
            currentSize--;
            return tmp;
            }
            
        public E peekFirst() {
            if(head == null)
                return null;
            return head.data;
            }
            
        public E peekLast() {
            if(tail == null)
                return null;
            return tail.data;
            }
            
        @SuppressWarnings("unchecked")
		public E find(E obj) {
            if(head == null) return null;
            Node<E> tmp = head;
            while(tmp != null) {
                if(((Comparable<E>)obj).compareTo(tmp.data) == 0)
                    return tmp.data;
                tmp = tmp.next;
                }
            return null;
            }        
            
        @SuppressWarnings("unchecked")
		public boolean remove(E obj) {
            if(isEmpty())
                return false;
            Node<E> previous = null, current = head;
            while(current != null) {
                if( ((Comparable<E>)current.data).compareTo(obj) == 0) {                
                    if(current == head) 
                        removeFirst();
                    else if(current == tail) 
                        removeLast();
                    else {
                        previous.next = current.next;
                        currentSize--;
                        }
                    return true;
                    }
                previous = current;
                current = current.next;
                }
            return false;
            }
         
    // not in the interface.  Removes all instances of the key obj        
        @SuppressWarnings({ "unused", "unchecked" })
		public boolean removeAllInstances(E obj) {
            Node<E> previous = null, current = head;
            boolean found = false;
            while(current != null) {
                if(((Comparable<E>)obj).compareTo(current.data) == 0) {
                    if(previous == null) { // node to remove is head
                        head = head.next;
                        if(head == null) tail = null;
                        }
                    else if(current == tail) {
                        previous.next = null;
                        tail = previous;
                        }
                    else 
                        previous.next = current.next;
                    found = true;
                    currentSize--;
                    current = current.next;
                    }
                else {
                    previous = current;
                    current = current.next;
                    }
                } // end while
            return found;
            }
            
        public void makeEmpty() {
            head = tail = null;
            currentSize = 0;
            }

        @SuppressWarnings("unchecked")
		public boolean contains(E obj) {
            @SuppressWarnings("rawtypes")
			Node current = head;
            while(current != null) {
                if( ((Comparable<E>)current.data).compareTo(obj) == 0)
                    return true;
                current = current.next;
                }
            return false;
            }
                   
        public boolean isEmpty() {
            return head == null;
            }
            
        public boolean isFull() {
            return false;
            }
            
        public int size() {
            return currentSize;
            }
            
        public Iterator<E> iterator() {
            return new ListIteratorHelper();
            }
    }

    interface ListADT<E> extends Iterable<E> {


    //  Adds the Object obj to the beginning of the list
        public void addFirst(E obj);

    //  Adds the Object obj to the end of the list
        public void addLast(E o);

    //  Removes the first Object in the list and returns it.
    //  Returns null if the list is empty.
        public E removeFirst();

    //  Removes the last Object in the list and returns it.
    //  Returns null if the list is empty.
        public E removeLast();

    //  Returns the first Object in the list, but does not remove it.
    //  Returns null if the list is empty.
        public E peekFirst();

    //  Returns the last Object in the list, but does not remove it.
    //  Returns null if the list is empty.
        public E peekLast();
        
    //  Finds and returns the Object obj if it is in the list, otherwise
    //  returns null.  Does not modify the list in any way
        public E find(E obj);

    //  Removes the first instance of thespecific Object obj from the list, if it exists.
    //  Returns true if the Object obj was found and removed, otherwise false
        public boolean remove(E obj);

    //  The list is returned to an empty state.
        public void makeEmpty();

    //  Returns true if the list contains the Object obj, otherwise false
        public boolean contains(E obj);

    //  Returns true if the list is empty, otherwise false
        public boolean isEmpty();

    //  Returns true if the list is full, otherwise false
        public boolean isFull();

    //  Returns the number of Objects currently in the list.
        public int size();

    //  Returns an Iterator of the values in the list, presented in
    //  the same order as the list.
        public Iterator<E> iterator();

    }
}
