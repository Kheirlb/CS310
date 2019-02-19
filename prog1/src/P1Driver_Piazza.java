import data_structures.*;

public class P1Driver_Piazza {
    private LinearListADT<Integer> list;
    private LinearListADT<String> list2;
    private LinearListADT<Object> list3;
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public P1Driver_Piazza() {
        list = new ArrayLinearList(10);
        list2 = new ArrayLinearList(10);
        list3 = new ArrayLinearList(10);
        runTests();
    }

    private void runTests() {
        for(int i=1; i <= 10; i++)
            list.addFirst(i);
        System.out.println("Should now print 10 .. 1");
        for(Integer i : list)
            System.out.println(i);
        for(int i=1; i <= 10; i++)
            if(list.removeFirst() == null)
                throw new RuntimeException("ERROR with removeFirst");
        for(Integer i : list)
            System.out.println(i);

        if(!list.isEmpty())
            throw new RuntimeException("ERROR in inEmpty");
        if(list.size() != 0)
            throw new RuntimeException("ERROR in size");

        for(int i=1; i <= 1000; i++) {
            for(int j=1; j <= 7; j++)
                list.addFirst(j);
            for(int j=1; j <= 7; j++)
                list.removeLast();
        }

        for(int i=1; i <= 1000; i++) {
            for(int j=1; j <= 7; j++)
                list.addLast(j);
            for(int j=1; j <= 7; j++)
                list.removeFirst();
        }

        list.addFirst(-1);
        if(list.peekLast() != -1)
            throw new RuntimeException("ERROR in peekLast");
        list.clear();
        list.addLast(-1);
        if(list.peekFirst() != -1)
            throw new RuntimeException("ERROR in peekLast");

        list.clear();
        for(int i=1; i <= 10; i++)
            list.addFirst(i);

        for(int i=1; i <= 10; i++)
            if(list.find(i) != i)
                throw new RuntimeException("ERROR in find");
            else
                System.out.println("FOUND " + i);

        for(int i=1; i <= 10; i++)
            if(!list.contains(i))
                throw new RuntimeException("ERROR in find");

        list.clear();
        for(int i=1; i <= 10; i++)
            list.addFirst(i);

        for(int i=1; i <= 5; i++) {
            Integer tmp = list.remove(i);
            if(tmp == null)
                System.out.println("ERROR in remove");
            else
                System.out.println("removed " + tmp);
        }
        for(Integer i : list)
            System.out.println(i);

        list.clear();
        if(list.peekFirst() != null) print("ERROR in peekFirst");
        if(list.peekLast() != null) print("ERROR in peekLast");
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
        print("Should now print 1 2 3 4 5 6 7 8 9 10");
        for(int i : list)
            print(i);
        print("\n");
        list.remove(3);
        list.remove(8);
        print("The 3 and the 8 have been removed");
        print("Should now print 1 2 4 5 6 7 9 10");
        for(int i : list)
            print(i);
        print("\n");
        if(list.contains(3)) print ("ERROR, contains removed element");
        if(list.contains(8)) print ("ERROR, contains removed element");
        if(list.peekFirst() != 1) print("ERROR in peekFirst");
        if(list.peekLast() != 10) print("ERROR in peekLasts");
        if(list.find(3) != null) print("ERROR in get, found removed element");
        if(list.find(8) != null) print("ERROR in get, found removed element");
        if(list.size() != 8) print("ERROR, size() returns incorrect value");
        list.addLast(3);
        list.addLast(8);
        if(list.addLast(11)) print("ERROR, add to full structure");
        for(int i=1; i <= 10; i++)
            if(list.remove(i) != i) print("ERROR in remove");

        if(list.peekFirst() != null) print("ERROR in peekFirst");
        if(list.peekLast() != null) print("ERROR in peekLast");
        if(list.size() != 0) print("ERROR, size() returns incorrect value");

        for(int i=1; i <= 10; i++)
            list.addFirst(1);
        for(int i=1; i <= 10; i++)
            list.remove(8);
        for(int i=1; i <= 10; i++)
            list.removeLast();
        if(list.peekFirst() != null) print("ERROR in peekFirst");
        if(list.peekLast() != null) print("ERROR in peekLast");
        if(list.size() != 0) print("ERROR, size() returns incorrect value");
        if(list.isEmpty() != true) print("ERROR, elements not removed correctly");

        for(int i=1; i <= 10; i++)
            list.addFirst(1);
        for(int i=1; i <= 10; i++)
            list.remove(8);
        for(int i=1; i <= 5; i++)
            list.removeFirst();
        for(int i=6; i<=10; i++)
            list.removeLast();
        if(list.peekFirst() != null) print("ERROR in peekFirst");
        if(list.peekLast() != null) print("ERROR in peekLast");
        if(list.size() != 0) print("ERROR, size() returns incorrect value");
        if(list.isEmpty() != true) print("ERROR, elements not removed correctly");


        for(int i=1; i <= 10; i++)
            list.addFirst(1);
        for(int i=1; i <= 10; i++)
            list.remove(8);
        for(int i=1; i <= 5; i++) {
            list.removeLast();
            list.removeFirst();
        }
        if(list.peekFirst() != null) print("ERROR in peekFirst");
        if(list.peekLast() != null) print("ERROR in peekLast");
        if(list.size() != 0) print("ERROR, size() returns incorrect value");
        if(list.isEmpty() != true) print("ERROR, elements not removed correctly");

        for(int i=1; i <= 10; i++)
            list.addFirst(1);
        if(list.addFirst(1) != false) print("ERROR, element was added even list is already full");
        if(list.addLast(1) != false) print("ERROR, element was added even list is already full");
        list.removeFirst();
        list.remove(1);
        if(list.size() != 8) print("ERROR, element didn't remove correctly, or size didn't get updated correctly");
        list.addFirst(2);
        list.addLast(3);
        if(list.size() != 10) print("ERROR, element didn't add correctly, or size didn't get updated correctly");
        list.remove(3);
        System.out.println("Should now print 2 1 1 1 1 1 1 1 1");
        for(int i : list)
            print(i);
        print("\n");
        list.clear();
        list2.addFirst("Second");
        list2.addFirst("First");
        System.out.println("Should now print First & Second");
        for(String i : list2)
            print(i);
        print("\n");
        list2.remove("First");
        list2.remove("Something else");

        System.out.println("Should now print Second");
        for(String i : list2)
            print(i);
        print("\n");

        list2.remove("Second");

        System.out.println("Should now show size is zero");
        System.out.println("Size is: " +list2.size());
        list2.ends();
        if (list2.find("S") != null) print("Wrong find result for a empty list");
        
        //test concurrentModification
        list.addLast(6);
        list.addFirst(4);
        list.addLast(7);
        list.addFirst(3);
        for(Integer i : list)
            System.out.println(i);
        //this should throw error in very end
//        for(Integer i : list)
//            list.remove(i);
        list3.addFirst(6);
        list3.addFirst(3.1415);
        System.out.println("List 3");
        list3.ends();
        list3.removeLast();
        list3.ends();
        list3.addFirst(7);
        list3.addLast(7);
        list3.removeLast();
        list3.removeLast();
        list3.removeLast();
        list3.removeLast();
        list3.removeFirst();

        list3.ends();
        for(Object i : list3) {
        	list.ends();
            System.out.println(i);
        }
    }

    private void print(String s) {
        System.out.println(s);
    }

    private void print(int i) {
        System.out.print(" "+i);
    }

    public static void main(String [] args) {
        new P1Driver_Piazza();
    }
}