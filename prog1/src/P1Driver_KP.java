import data_structures.*;

public class P1Driver_KP {
    private LinearListADT<Integer> list;
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public P1Driver_KP() {
        list = new ArrayLinearList(10);
        System.out.println("Created Array");
        runTests();
        }
	
	private void runTests() {
		/*
		list.addFirst(5);
		list.addFirst(6);
		list.addFirst(7);
		list.addLast(2);
		list.addLast(3);
        for(Integer i : list)
            System.out.println(i);*/
        list.addFirst(5);
        list.addLast(6);
        list.addFirst(4);
        list.addLast(7);
        list.addFirst(3);
        list.addLast(8); 
        list.addFirst(2);
        list.addLast(9);
        list.addFirst(1);
        list.addLast(10);
        System.out.println("Should now print 1 2 3 4 5 6 7 8 9 10");
        for(Integer i : list)
            System.out.println(i);
        //list.addFirst(4);
	}
	
	public static void main(String[] args) {
		new P1Driver_KP();
	}

}
