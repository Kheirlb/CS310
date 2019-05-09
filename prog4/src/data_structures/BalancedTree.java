/**
   *  Program 4
   *  This is using the Java API TreeMap for a well balanced tree.
   *  CS310-01s
   *  5/7/2019
   *  @author  Karl Parks cssc1506
   */

package data_structures;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class BalancedTree<K extends Comparable<K>,V> implements DictionaryADT<K,V> {
	private TreeMap<K, V> map = new TreeMap<>();
	
	@Override
	//returns true if it contains that key
	public boolean contains(K key) {
		return map.containsKey(key);
	}

	@Override
	//adds a node and returns true or false if successful
	public boolean add(K key, V value) {
		return map.put(key, value) != null;
	}

	@Override
	//deletes a node and returns true or false if successful
	public boolean delete(K key) {
		return map.remove(key) != null;
	}

	@Override
	//returns the value of a specific key
	public V getValue(K key) {
		return map.get(key);
	}

	@SuppressWarnings("unchecked")
	@Override
	//searchs through entryset and returns a key for the desired value
	public K getKey(V value) {
		if (map.containsValue(value)) {
			for (Map.Entry<K, V> entry : map.entrySet()) {
				if (((Comparable<V>) entry.getValue()).compareTo(value) == 0) {
		            return entry.getKey();
		        }
		    }
		}
		return null;
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Iterator<K> keys() {
        return map.keySet().iterator();
	}

	@Override
	public Iterator<V> values() {
		return map.values().iterator();
	}
}
