package probSheet2;

public class Queue<E> {     
	private int maxSize;     
	private int currentSize;     
	private Object [] storage;     
	private int front, rear; 
    @SuppressWarnings("unchecked")
	public Queue(int size) {         
    	maxSize = size;         
    	currentSize = 0;         
    	storage = (E[]) new Object[maxSize];         
    	front = rear = 0;     
    } 
    public void enqueue(E obj) {
    	//insert in back
    	if (isFull()) {
			System.out.println("Queue is Full");
			return;
		}
    	storage[rear] = obj;
    	rear = (rear + 1) % maxSize;
    	currentSize++;
    	System.out.println("Queued Element");
    }
    @SuppressWarnings("unchecked")
	public E dequeue() {
    	//grab from front
    	if (isEmpty()) {
			System.out.println("Queue is Empty");
			return null;
		}
    	Object tmp = storage[front];
    	storage[front] = null; 
    	front = (front + 1) % maxSize;
    	currentSize--;
		System.out.println("Dequeue Element");
		return (E) tmp;
    	
    }
    public boolean isFull() {
		return currentSize==maxSize;
    	
    }
    public boolean isEmpty()  {
		return currentSize==0;
    	
    }
    public void printVals() {
    	System.out.println("currentSize: " + currentSize);
    	System.out.println("front: " + front);
    	System.out.println("rear: " + rear);
    	int count = 0;
    	for (int i = front; count < currentSize; count++) {
    		System.out.println("storage:[" + i + "]: " + storage[i]);
    		i = (i + 1) % maxSize;
    	}
    }
	public static void main(String[] args) {
		System.out.println("Hello Karl");
		Queue<String> queue1;
		queue1 = new Queue<String>(4);
		queue1.enqueue("first");
	}
}
