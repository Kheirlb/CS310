import data_structures.*;

public class P1Driver_manipulated {
    private LinearListADT<Integer> list;
    
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public P1Driver_manipulated() {
        list = new ArrayLinearList(10);
        System.out.println("Created Array");
        //System.out.println("size: " + list.size());
        runTests();
        }
        
    private void runTests() {
        for(int i=1; i <= 10; i++) 
            list.addFirst(i);
        System.out.println("Should now print 10 .. 1");
        //System.out.println("size: " + list.size());
        for(Integer i : list)
            System.out.println(i);
        for(int i=1; i <= 10; i++) {
        	//System.out.println("Success");
            if(list.removeLast() == null)
                throw new RuntimeException("ERROR with removeFirst");
        }
        //System.out.println("size: " + list.size());
        for(Integer i : list)
            System.out.println(i); 
        if(list.isEmpty()) {
        	System.out.println("Empty!"); 
        }
        if(!list.isEmpty())
            throw new RuntimeException("ERROR in inEmpty");  
        //*
        if(list.size() != 0)
            throw new RuntimeException("ERROR in size");
        //*    
        for(int i=1; i <= 1000; i++) {
            //System.out.println("i:" + i); 
            for(int j=1; j <= 7; j++) {
                list.addFirst(j);
                //System.out.println("j1:" + j); 
            }
            for(int j=1; j <= 7; j++) {           
                list.removeLast();
                //System.out.println("j2:" + j); 
            }
        }
        if(list.isEmpty()) {
        	System.out.println("Empty!"); 
        }
        //*   
        for(int i=1; i <= 1000; i++) {
        	//System.out.println("i:" + i); 
            for(int j=1; j <= 7; j++) {
                list.addLast(j);
            	//System.out.println("j1:" + j); 
            }
            for(int j=1; j <= 7; j++) {     
                list.removeFirst();
            	//System.out.println("j2:" + j); 
            }
        }
        if(list.isEmpty()) {
        	System.out.println("Empty!"); 
        }
        list.addFirst(-1);
        //System.out.println(list.size()); 
        if(list.peekLast() != -1)   
            throw new RuntimeException("ERROR in peekLast");//*/
        list.clear();
        list.addLast(-1);
        //System.out.println("size: " + list.size());
        if(list.peekFirst() != -1)   
            throw new RuntimeException("ERROR in peekLast"); 
        //*    
        list.clear();
        //*
        for(int i=1; i <= 10; i++)
            list.addFirst(i);
        //*    
        for(int i=1; i <= 10; i++)
            if(list.find(i) != i)
                throw new RuntimeException("ERROR in find");
            else
                System.out.println("FOUND " + i); 
        //*        
        for(int i=1; i <= 10; i++)
            if(!list.contains(i))
                throw new RuntimeException("ERROR in find");                           
        //*
        list.clear();
        for(int i=1; i <= 10; i++)
            list.addFirst(i);
        for(Integer i : list)
            System.out.println(i);
        System.out.println("size: " + list.size());
        //System.out.println("Removed: " + list.remove(4));
        for(Integer i : list)
           System.out.println(i);
        //*   
        for(int i=1; i <= 5; i++) {
            Integer tmp = list.remove(i);
            if(tmp == null)
                System.out.println("ERROR in remove");
            else
                System.out.println("removed " + tmp);
           }
        //*
        for(Integer i : list)
            System.out.println(i); //*/
       }
        
    public static void main(String [] args) {
        new P1Driver_manipulated();
        }
    }
