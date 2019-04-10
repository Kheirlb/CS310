import data_structures.*;

public class prog3driver {
	private BinaryHeapPriorityQueue<Integer> jerald;
	private BinaryHeapPriorityQueue<String> kyle;
	
	public void runTests() {
		kyle = new BinaryHeapPriorityQueue<String>();
		kyle.insert("5");
		kyle.insert("3");
		kyle.insert("5");
		kyle.debugger();
		
		jerald = new BinaryHeapPriorityQueue<Integer>(10);
		jerald.insert(6);
		jerald.insert(17);
		jerald.insert(27);
		jerald.insert(22);
		jerald.insert(19);
		jerald.insert(30);
		jerald.insert(63);
		jerald.insert(74);
		jerald.insert(29);
		jerald.insert(7);
		jerald.insert(1000);
		jerald.debugger();
//		int startSize = jerald.size();
//		System.out.print("Sorted binaryHeap: ");
//		for (int i = 0; i < startSize; i++) {
//			System.out.print(jerald.remove() + " ");
//		}
//		jerald.remove();
//		jerald.remove();
//		jerald.remove();
//		jerald.debugger();
		jerald.remove();
		jerald.insert(74);
		jerald.debugger();
		jerald.delete(5);
		jerald.debugger();
		System.out.println("Peek: " + jerald.peek());
		System.out.println("Contains a 7: " + jerald.contains(7));
		System.out.println("Contains a 8: " + jerald.contains(8));
		System.out.println("Size: " + jerald.size());
		System.out.println("Should NOT be Empty: Empty?" + jerald.isEmpty());
		System.out.println("Clear binaryHeap");
		jerald.clear();
		System.out.println("Should be Empty: Empty?" + jerald.isEmpty());
		jerald.insert(6);
		jerald.insert(17);
		jerald.insert(27);
		jerald.insert(22);
		jerald.insert(19);
		jerald.insert(30);
		jerald.insert(63);
		jerald.insert(74);
		jerald.insert(29);
		jerald.insert(7);
		jerald.debugger();
		System.out.print("Unsorted:\t");
		for (Integer i : jerald) {
			System.out.print(i + " ");
		}
		jerald.printSort();
		jerald.debugger();
		
	}
	
	public static void main(String[] args) {
		System.out.println("Hello Karl, jerald, and test object");
		prog3driver test = new prog3driver();
		test.runTests();
	}
}
