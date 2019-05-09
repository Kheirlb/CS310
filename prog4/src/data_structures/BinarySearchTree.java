/**
   *  Program 4
   *  This is the BST for in order traversal benefits
   *  CS310-01
   *  5/7/2019
   *  @author  Karl Parks cssc1506
   */

package data_structures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BinarySearchTree<K extends Comparable<K>,V> implements DictionaryADT<K,V> {
	private Node<K, V> root;
	private int currentSize, modCount;
	private boolean found;
	
	@SuppressWarnings("hiding")
	//node for each element of tree
	private class Node<K, V> {
		private K key;
		private V value;
		private Node<K, V> leftChild;
		private Node<K, V> rightChild;
		
		public Node(K k, V v) {
			key = k;
			value = v;
			leftChild = rightChild = null;
		}
	}
	
	@Override
	//returns true if it contains that key
	public boolean contains(K key) {
		return getValue(key) != null;
	}

	@Override
	//adds a node to the tree
	public boolean add(K key, V value) {
		if (contains(key)) { //cannot add duplicate values
			return false;
		}
		if (root == null) //check if at root node first
			root = new Node<K,V>(key, value);
		else //if not at root node, insert
			insert(key,value,root,null,false);
		currentSize++;
		modCount++;
		return true;
	}
	
	//recursive insert function to traverse through tree
	private void insert(K k, V v, Node<K, V> n, Node<K, V> parent, boolean wasLeft) {
		if(n == null) {
			if (wasLeft) {
				parent.leftChild = new Node<K, V> (k,v);
			}
			else {
				parent.rightChild = new Node<K, V> (k,v);
			}
		}
		else if (((Comparable<K>)k).compareTo((K)n.key) < 0) {
			insert(k, v, n.leftChild, n, true); // thats a left turn fella
		}
		else {
			insert(k, v, n.rightChild, n, false); // thats a right turn
		}
	}

	@Override
	//deletes a specific key and returns tree if key exists
	public boolean delete(K key) {
		if (deleteNode(key, root, null, false)) {
			currentSize--;
            modCount++;
            return true;
        }
		return false;
	}
	
	//recursive delete function, very intense coding here for all cases
	private boolean deleteNode(K k, Node<K, V> n, Node<K, V> parent, boolean wasLeft) {
		//check for empty list
		if (root == null) {
			found = false;
			return found;
		}
		//check if root is the only element
		if (currentSize == 1) {
			root = null;
			found = true;
			return found;
		}
		
		//check if key exists
		if (n == null) {
			found = false;
			return found;
		}
		
		//go left or right
    	if (((Comparable<K>)k).compareTo((K)n.key) < 0) { //go left
        	//System.out.println(k + " < " + n.key);
        	deleteNode(k, n.leftChild, n, true);
        } 
        else if (((Comparable<K>)k).compareTo((K)n.key) > 0) { //go right
        	//System.out.println(k + " > " + n.key);
        	deleteNode(k, n.rightChild, n, false);
        }
        else { //found something
        	//check if no children
        	if (n.leftChild == null && n.rightChild == null) {
	        	if (wasLeft) { //which parent check
	        		parent.leftChild = null;
	        	}
        		if (!wasLeft) {
            		parent.rightChild = null;        			
        		}
	        }
        	// check for tree with two children
        	else if (n.leftChild != null && n.rightChild != null) {
        		Node<K, V> tmp = n; //temp variable
                Node<K, V> minRight = minimumElement(tmp.rightChild); //minimum right variable
                n = minRight; //store as new variable
                deleteNode(minRight.key, tmp.rightChild, tmp, false); //remove the minimum right variable
                if (tmp.key != root.key) { //if not at root node
	                if (wasLeft) {
		        		parent.leftChild = n;
		        	}
	        		if (!wasLeft) {
	            		parent.rightChild = n;        			
	        		}
                }
                else { //if removing root node
                	root = n;
                } //reset children
        		n.leftChild = tmp.leftChild;
        		n.rightChild = tmp.rightChild;
        	}
        	else if (n.leftChild != null && n.rightChild == null) { //just left child
        		if (n.key == root.key) { //root node check
        			root = n.leftChild;
        			return true;
        		}
	        	if (wasLeft) {
	        		parent.leftChild = n.leftChild;
	        	}
        		if (!wasLeft) {
            		parent.rightChild = n.leftChild;        			
        		}
	        }
        	else if (n.leftChild == null && n.rightChild != null) { //just right child
        		if (n.key == root.key) { //root node check
        			root = n.rightChild;
        			return true;
        		}
	        	if (wasLeft) {
	        		parent.leftChild = n.rightChild;
	        	}
        		if (!wasLeft) {
            		parent.rightChild = n.rightChild;        			
        		}
	        }
        	found = true;
        }
        //System.out.println("Call Last Return");
    	return found;
	}
	
	//find minimum element of right subtree, successor
    private Node<K, V> minimumElement(Node<K, V> root) {
        if (root.leftChild == null)
            return root;
        else {
            return minimumElement(root.leftChild);
        }
    }

	@Override
	//returns value of found key
	public V getValue(K key) {
		return getValue(key, root);
	}
	
	//recursive search starting at root node
	public V getValue(K k, Node<K,V> n) {
		if(n == null)
			return null;
		if (((Comparable<K>)k).compareTo((K)n.key) == 0)
			return n.value;
		if (((Comparable<K>)k).compareTo((K)n.key) < 0)
			return getValue(k, n.leftChild);
		return getValue(k, n.rightChild);
	}

	@Override
	//returns key of first value found
	public K getKey(V value) {
		return inOrderGetKey(root, value);
	}

	@Override
	public int size() {
		return currentSize;
	}
	
	@SuppressWarnings("unchecked")
	//recursive in order traversal to get a key of a specified value
	private K inOrderGetKey(Node<K, V> n, V valueDesired) {
		if (n != null) {
			//System.out.println("Searching at: " + n.key);
			if (((Comparable<V>)valueDesired).compareTo((V)n.value) == 0) {
				return n.key;
			}
			if ((inOrderGetKey(n.leftChild, valueDesired)) != null) {
				return (inOrderGetKey(n.leftChild, valueDesired));
			}
			if ((inOrderGetKey(n.rightChild, valueDesired)) != null) { 
				return (inOrderGetKey(n.rightChild, valueDesired));
			};
		}
		return null;
	}
	
	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return (currentSize <= 0);
	}

	@Override
	public void clear() {
		currentSize = 0;
		root = null;
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
	
	private abstract class IteratorHelper<E> implements Iterator<E> {
        Node<K, V>[] nodes;
        int idx;
        int expectedMod;

        @SuppressWarnings("unchecked")
		IteratorHelper() {
            nodes = new Node[currentSize];
            inOrderStore(root); //recursive store of nodes array for in order sorting
            idx = 0;
            expectedMod = modCount;
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
        
    	private void inOrderStore(Node<K, V> n) {
    		if (n == null) return;
    		inOrderStore(n.leftChild);
			nodes[idx++] = n;
			inOrderStore(n.rightChild);
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

}
