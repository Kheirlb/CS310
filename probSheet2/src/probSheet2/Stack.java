package probSheet2;

class Stack<E>  {     
	private int head;     
	private int maxSize;     
	private Object [] stack;    
	public Stack(int size) {         
		maxSize = size;         
		head = -1;         
		stack = new Object[maxSize];     
	}    
	public void push(E data) {
		if (isFull()) {
			System.out.println("Stack is Full");
			return;
		}
		head++;
		stack[head] = data; 
		System.out.println("Pushed Element");
	}
	@SuppressWarnings("unchecked")
	public E pop() {
		if (isEmpty()) {
			System.out.println("Stack is Empty");
			return null;
		}
		Object tmp = stack[head];
		stack[head] = null; 
		head--;
		System.out.println("Popped Element");
		return (E) tmp;
	}
	public boolean isFull() {
		return maxSize-1==head;
	}
	public boolean isEmpty() {
		return head<0;
	}
	
	public static void main(String[] args) {
		System.out.println("Hello Karl");
		Stack<String> stack1;
		stack1 = new Stack<String>(4);
		stack1.push("One");
	}

}  

