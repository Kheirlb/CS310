import data_structures.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.lang.Math; 

public class hw4 {
	private LinearListADT_Array<Integer> Array;
	private ArrayList<Integer> JavaArray;
	private LinearListADT<Integer> Linked;
	private LinkedList<Integer> JavaLinked;
	private PriorityQueue<Integer> Queue;

	public hw4() {
		long previousTime;
		long elapsedTime;
		int n1 = 10000;
		int n2 = 100000;
		int n3 = 500000;
		int n4 = 1000000;
		
		int desired = n1;
		
		//create objects of that size
        Array = new ArrayLinearList<Integer>(desired);
        JavaArray = new ArrayList<Integer>(desired);
        Linked = new LinearList<Integer>();
        JavaLinked = new LinkedList<Integer>();
    	Queue = new BinaryHeapPriorityQueue<Integer>(desired);
        
        System.out.println("n=" + desired);
    	System.out.println("Add:");
        previousTime = System.nanoTime();
		for (int i = 0; i < desired; i++) {
//			Array.addLast(i);
//			Array.addFirst(i);
//			JavaArray.add(i);
//			JavaArray.add(0, i);
//			Linked.addLast(i);
//			Linked.addFirst(i);
//			JavaLinked.addLast(i);
//			JavaLinked.addFirst(i);
			Queue.insert(i);
		}
		elapsedTime = System.nanoTime() - previousTime;
		System.out.println("Elapsed Time: " + elapsedTime + " nano seconds");
		System.out.println("Elapsed Time: " + elapsedTime/1000 + " micro seconds");
		System.out.println("Elapsed Time: " + (double)elapsedTime/1000000 + " milli seconds");
		
		System.out.println("\nRemove:");
        previousTime = System.currentTimeMillis();
		for (int i = 0; i < desired; i++) {
//			Array.removeLast();
//			Array.removeFirst();
//			JavaArray.remove(JavaArray.size()-1);
//			JavaArray.remove(0);
//			Linked.removeLast();
//			Linked.removeFirst();
//			JavaLinked.removeLast();
//			JavaLinked.removeFirst();
			Queue.remove();
		}
		elapsedTime = System.currentTimeMillis() - previousTime;
		System.out.println("Elapsed Time: " + elapsedTime + " nano seconds");
		System.out.println("Elapsed Time: " + elapsedTime/1000 + " micro seconds");
		System.out.println("Elapsed Time: " + (double)elapsedTime/1000000 + " milli seconds");
		
//		System.out.println("\nAdd:");
//		int[] nArray = {10000,100000,500000,1000000};
//		for (int nx = 0; nx < nArray.length; nx++) {
//			Array = new ArrayLinearList<Integer>(nArray[nx]);
//			previousTime = System.nanoTime();
//			for (int i = 0; i < nx; i++) {
//				Array.addLast(i);
//			}
//			elapsedTime = System.nanoTime() - previousTime;
//			System.out.println("Elapsed time of " + nArray[nx] + ": " + elapsedTime + " milli seconds");
//		}
		
		System.out.println("End of Code");
	}
	
	public static void main(String[] args) {
		new hw4();
	}

}
