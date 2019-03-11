package probSheet2;

public class LinkedList<E> {
	@SuppressWarnings("hiding")
	class Node<E> {
		E data;
		Node<E> next;
		public Node(E obj) {
			data = obj;
			next = null;
		}
	}
	
	private Node<E> head;
	private int currentSize;
	
	public LinkedList() {
		head = null;
		currentSize = 0;
	}
	public void insertFirst(E obj) {
		Node<E> newNode = new Node<E>(obj);
		newNode.next = head; 
		head = newNode;
		currentSize++;
	}
	public void insertLast(E obj) {
		Node<E> newNode = new Node<E>(obj);
		Node<E> tmp = head;
		if (head == null) {
			head = newNode;
			currentSize++;
			return;
		}
		while (tmp.next != null) {
			tmp = tmp.next;
		}
		tmp.next = newNode;
		currentSize++;
	}
	public void insertAfter(E key1, E key2) {
		Node<E> newNode = new Node<E>(key1);
		Node<E> tmp = head;
		Node<E> tmpDesired = null;
		boolean found = false;
		for (int i = 0; i < currentSize; i++) {
			if (tmp.data == key2) {
				tmpDesired = tmp;
				found = true;
			}
			tmp = tmp.next;
		}
		if (!found) {
			insertLast(key1);
			return;
		}
		newNode.next = tmpDesired.next;
		tmpDesired.next = newNode;
		currentSize++;
	}
	
	public void delete(E key) {
		Node<E> tmp = head;
		Node<E> tmpDesired = null;
		Node<E> beforeNode = null;
		boolean found = false;
		for (int i = 0; i < currentSize; i++) {
			if (tmp.data == key) {
				tmpDesired = tmp;
				found = true;
				System.out.println("Found Node to Delete");
			}
			if (i == 0) {
				beforeNode = head;
				if (found) {
					head = tmpDesired.next;
					currentSize--;
					return;
				}
			}
			else if (!found) {
				beforeNode = beforeNode.next;
			}
			tmp = tmp.next;
		}
		if (!found) {
			return;
		}
		beforeNode.next = tmpDesired.next;
		currentSize--;
	}
	public void deleteFirst() {
		if (currentSize != 0) {
			head = head.next;
			currentSize--;
		}
		return;
	}
	public void deleteLast() {
		if (currentSize != 0) {
			Node<E> tmp = head;
			for (int i = 0; i < currentSize; i++) {
				tmp = tmp.next;
			}
			tmp = null;
			currentSize--;
		}
		return;
	}
	public void deleteLastInstace(E key) {
		delete(key);
	}
	public void reverseList() {
		Node<E> tmp = head;
		Node<E> tmpHead = head;
		Node<E> beforeNode = null;
 		System.out.println("--- Reversing List ---");
		for (int i = 0; i < currentSize; i++) {
			if (i == 0) {
				beforeNode = head;
			}
			else if (i > 0) {
				beforeNode = beforeNode.next;
			}
			if (i < currentSize) {
				head = tmp;
			}
			tmp = tmp.next;
			head.next = beforeNode;
		}
	}
	
	public void printList() {
		Node<E> tmp = head;
		System.out.println("--- Printing List ---");
		for (int i = 0; i < currentSize; i++) {
			//System.out.println("Confused, i: " +  i);
			System.out.println(tmp.data);
			tmp = tmp.next;
		}
	}
	
	public static void main(String[] args) {
		LinkedList<String> list1;
		LinkedList<Integer> list2;
		LinkedList<Integer> list3;
		LinkedList<String> list4;

		list1 = new LinkedList<String>();
		list2 = new LinkedList<Integer>();
		list3 = new LinkedList<Integer>();
		list4 = new LinkedList<String>();

		list1.insertFirst("third");
		list1.insertFirst("second");
		list1.insertFirst("first");
		list1.insertLast("last");
		list1.insertLast("lastlast");
		list1.insertFirst("beginning");
		list1.insertFirst("a long time ago");
		System.out.println("");
		//System.out.println(list1.head.next.next.data);
		list1.printList();
//		for (int i = 0; i < list1.currentSize; i++) {
//			System.out.println("Confused, i: " +  i);
//			//list1.head.data;
//		}
		System.out.println("");
		list2.insertLast(1);
		list2.insertLast(2);
		list2.insertLast(4);
		list2.insertLast(5);
		list2.printList();
		System.out.println("");
		list2.insertAfter(3, 2);
		System.out.println("");
		list2.printList();
		list2.delete(4);
		list2.printList();
		list2.deleteLastInstace(8);
		list2.printList();
		list2.deleteFirst();
		list2.deleteFirst();
		//list2.deleteFirst();
		//list2.deleteFirst();
		list2.printList();
		list2.deleteLast();
		//list2.deleteLast();
		list2.printList();

		list3.insertAfter(3, 0);
		list3.insertAfter(3, 3);
		System.out.println("");
		list3.printList();
	
		list4.insertLast("A");
		list4.insertLast("B");
		list4.insertLast("C");
		list4.insertLast("D");
		list4.printList();
		list4.reverseList();
		list4.printList();
	}
}
