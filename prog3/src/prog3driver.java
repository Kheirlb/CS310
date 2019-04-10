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
		
		jerald = new BinaryHeapPriorityQueue<Integer>();
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
		int startSize = jerald.size();
		System.out.print("Sorted binaryHeap: ");
		for (int i = 0; i < startSize; i++) {
			System.out.print(jerald.remove() + " ");
		}
//		jerald.remove();
//		jerald.remove();
//		jerald.remove();
//		jerald.debugger();
	}
	
	public static void main(String[] args) {
		System.out.println("Hello Karl, jerald, and test object");
		prog3driver test = new prog3driver();
		test.runTests();
	}
}
