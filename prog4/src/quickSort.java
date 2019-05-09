
public class quickSort {
	int n[];
	
	public quickSort() {
		int myArray[] = {8, 6, 4, 10};
		int sorted[] = quickSort(myArray);
		for (int i = 0; i < sorted.length; i++) {
			System.out.print(sorted[i] + " ");
		}
	}
	
	public int[] quickSort(int array[]) {
		n = array;
		quickSort(0, n.length-1);
		return n;
	}
	
	private void quickSort(int left, int right) {
		if (right-left <= 0) return;
		int pivot = n[right];
		int partition = getPartition(left, right, pivot);
		quickSort(left, partition-1);
		quickSort(partition+1, right);
	}
	
	private int getPartition(int left, int right, int pivot) {
		int lPtr = left-1;
		int rPtr = right;
		for (;;) {
			while (n[++lPtr] < pivot);
			while (rPtr > 0 && n[--rPtr] > pivot);
			if (lPtr >= rPtr)
				break;
			else
				swap(lPtr,rPtr);
		}
		swap(lPtr, right);
		return lPtr;
	}
	
	private void swap(int one, int two) {
		int temp = n[one];
		n[one] = n[two];
		n[two] = temp;
	}
	public static void main(String[] args) {
		System.out.println("Hello");
		new quickSort();
	}
}
