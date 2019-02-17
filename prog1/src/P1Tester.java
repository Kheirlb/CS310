/*  Here is an additional test program for your first project.  Look at and try to
    understand what the tester is doing and why.  Be sure to add additional tests
    on your own.  The fact that your program may work with this tester without error
    does not mean your code is error free.
    
    Patty Kraft
    CS310 Spring 2019
*/    

import data_structures.*;

public class P1Tester {
    private LinearListADT<Integer> list;
    
    public P1Tester() {
        list = new ArrayLinearList<Integer>(10);
        runTests();
        }
        
    private void runTests() {
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
        list.remove(3);;        
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
        System.out.println("P1Tester.java RUNS!!!!!!!!");           
        System.out.println("NO ERRORS!!!!!!!!");                                 
       }
       
    private void print(String s) {
        System.out.println(s);
        }
        
    private void print(int i) {
        System.out.print(" "+i);
        }        
        
    public static void main(String [] args) {
        new P1Tester();
        }
    }