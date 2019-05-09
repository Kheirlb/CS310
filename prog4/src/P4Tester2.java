import java.util.Iterator;

import data_structures.*;

public class P4Tester2 {
	private DictionaryADT<Integer,Integer> HASH;
	private DictionaryADT<Integer,Integer> BST;
	private DictionaryADT<Integer,Integer> BT;
	
	public P4Tester2() {
		System.out.println("Creating Structures");
		HASH = new Hashtable<>(4);
		BST = new BinarySearchTree<>();
		BT = new BalancedTree<>();
		
		System.out.println("\n---HASH---");
		HASH.add(20, 200);
		HASH.add(15, 150);
		HASH.add(42, 420);
		HASH.add(42, 420); //should not add
		System.out.println("Delete (true): " + HASH.delete(42));
		System.out.println("Delete (false): " + HASH.delete(42));
		System.out.println("Contains (true): " + HASH.contains(15));
		System.out.println("Contains (false): " + HASH.contains(42));
		System.out.println("getValue (200): " + HASH.getValue(20));
		System.out.println("getValue (null): " + HASH.getValue(42));
		System.out.println("getKey (20): " + HASH.getKey(200));
		System.out.println("getKey (null): " + HASH.getKey(420));
		System.out.println("isFull (false): " + HASH.isFull()); //check full
		HASH.add(33, 330);
		HASH.add(34, 340);
		System.out.println("isFull (true): " + HASH.isFull()); //check full
		System.out.println("size (4): " + HASH.size());
		System.out.println("isEmpty (false): " + HASH.isEmpty()); //check empty
		System.out.println("Clear table");
		HASH.clear();
		System.out.println("size (0): " + HASH.size());
		System.out.println("isEmpty (true): " + HASH.isEmpty()); //check empty
		System.out.println("Re-Add Elements");
		HASH.add(20, 200);
		HASH.add(15, 150);
		HASH.add(42, 420);
		
		System.out.println("\n---BST---");
		BST.add(20, 200);
		BST.add(15, 150);
		BST.add(42, 420);
		BST.add(42, 420); //should not add
		System.out.println("Delete (true): " + BST.delete(42));
		System.out.println("Delete (false): " + BST.delete(42));
		System.out.println("Contains (true): " + BST.contains(15));
		System.out.println("Contains (false): " + BST.contains(42));
		System.out.println("getValue (200): " + BST.getValue(20));
		System.out.println("getValue (null): " + BST.getValue(42));
		System.out.println("getKey (20): " + BST.getKey(200));
		System.out.println("getKey (null): " + BST.getKey(420));
		System.out.println("isFull (false): " + BST.isFull()); //check full
		BST.add(33, 330);
		BST.add(34, 340);
		System.out.println("isFull (false): " + BST.isFull()); //check full
		System.out.println("size (4): " + BST.size());
		System.out.println("isEmpty (false): " + BST.isEmpty()); //check empty
		System.out.println("Clear table");
		BST.clear();
		System.out.println("size (0): " + BST.size());
		System.out.println("isEmpty (true): " + BST.isEmpty()); //check empty
		System.out.println("Re-Add Elements");
		BST.add(20, 200);
		BST.add(15, 150);
		BST.add(42, 420);
		
		System.out.println("\n---BT---");
		BT.add(20, 200);
		BT.add(15, 150);
		BT.add(42, 420);
		BT.add(42, 420); //should not add
		System.out.println("Delete (true): " + BT.delete(42));
		System.out.println("Delete (false): " + BT.delete(42));
		System.out.println("Contains (true): " + BT.contains(15));
		System.out.println("Contains (false): " + BT.contains(42));
		System.out.println("getValue (200): " + BT.getValue(20));
		System.out.println("getValue (null): " + BT.getValue(42));
		System.out.println("getKey (20): " + BT.getKey(200));
		System.out.println("getKey (null): " + BT.getKey(420));
		System.out.println("isFull (false): " + BT.isFull()); //check full
		BT.add(33, 330);
		BT.add(34, 340);
		System.out.println("isFull (false): " + BT.isFull()); //check full
		System.out.println("size (4): " + BT.size());
		System.out.println("isEmpty (false): " + BT.isEmpty()); //check empty
		System.out.println("Clear table");
		BT.clear();
		System.out.println("size (0): " + BT.size());
		System.out.println("isEmpty (true): " + BT.isEmpty()); //check empty
		System.out.println("Re-Add Elements");
		BT.add(20, 200);
		BT.add(15, 150);
		BT.add(42, 420);
		
		System.out.println("\nKey Iterators: 15, 20, 42");
		
		System.out.println("---HASH---");
		Iterator<Integer> HASHIterator = HASH.keys();
		while(HASHIterator.hasNext()) {
			System.out.println(HASHIterator.next());
		}
		
		System.out.println("---BST---");
		Iterator<Integer> BSTIterator = BST.keys();
		while(BSTIterator.hasNext()) {
			System.out.println(BSTIterator.next());
		}
		
		System.out.println("---BT---");
		Iterator<Integer> BTIterator = BT.keys();
		while(BTIterator.hasNext()) {
			System.out.println(BTIterator.next());
		}
		
		System.out.println("\nValue Iterators: 150, 200, 420");
		
		System.out.println("---HASH---");
		Iterator<Integer> HASHIterator1 = HASH.values();
		while(HASHIterator1.hasNext()) {
			System.out.println(HASHIterator1.next());
		}
		
		System.out.println("---BST---");
		Iterator<Integer> BSTIterator1 = BST.values();
		while(BSTIterator1.hasNext()) {
			System.out.println(BSTIterator1.next());
		}
		
		System.out.println("---BT---");
		Iterator<Integer> BTIterator1 = BT.values();
		while(BTIterator1.hasNext()) {
			System.out.println(BTIterator1.next());
		}
	}

	public static void main(String[] args) {
		new P4Tester2();

	}

}
