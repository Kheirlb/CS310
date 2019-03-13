import data_structures.*;

public class P2Driver
{
	private LinearListADT<String> list;
	private LinearListADT<Integer> list2;
	
	public P2Driver()
	{
		list = new LinearList<String>();
		list2 = new LinearList<Integer>();
		runTests();
	}
	
	public void runTests()
	{
		System.out.println("SIZE: " + list.size());
		System.out.println("EMPTY?: " + list.isEmpty());
		list.addFirst("Hello");
		System.out.println("SIZE: " + list.size());
		System.out.println("EMPTY?: " + list.isEmpty());
		list.addLast("World");
		System.out.println("SIZE: " + list.size());
		list.addFirst("Greetings!");
		System.out.println("SIZE: " + list.size());
		list.removeFirst();
		System.out.println("SIZE: " + list.size());
		list.removeLast();
		System.out.println("SIZE: " + list.size());
		System.out.println(list.peekFirst());
		System.out.println(list.peekLast());
		list.clear();
		for (int i = 1; i <= 10; i++)
			list.addLast("OBJECT");
		for (String i : list)
			System.out.println(i);
		for (int i = 1; i <= 5; i++)
			list2.addLast(i);
		System.out.println("DOES LIST2 CONTAIN 2?: " + list2.contains(2));
		System.out.println("DOES LIST2 CONTAIN 5?: " + list2.contains(5));
		System.out.println("DOES LIST2 CONTAIN 6?: " + list2.contains(6));
		list2.remove(2);
		System.out.println("SIZE: " + list2.size());
		System.out.println("DOES LIST2 CONTAIN 2?: " + list2.contains(2));
		list2.remove(1);
		list2.remove(3);
		list2.remove(5);
		list2.clear();
		System.out.println("SIZE: " + list2.size());
		System.out.println();
		
		for (int i = 1; i <= 5; i++)
			list2.addFirst(i);
		System.out.println("SIZE: " + list2.size());
		for (int i = 2 ; i >= 1; i--)
			list2.remove(i);
		System.out.println("SIZE: " + list2.size());
		list.clear();
		System.out.println();
		int sizeOfTest = 100;
		for (int i = 0; i < sizeOfTest; i++)
		{
			list.addFirst("FIRST:" + i);
			list.addLast("LAST:" + i);
		}
		for (String s : list)
			System.out.println(s);
		System.out.println();
		
		for (int i = 0; i < sizeOfTest; i++)
		{
			System.out.println("FIRST:" + i/3);
			list.remove("FIRST:" + i/3);
			list.remove("LAST:" + i/3);
		}
		for (String s : list)
			System.out.println(s);
		list2.clear();
		System.out.println();
		System.out.println("CLEARED LIST2");
		for (Integer i : list2)
			System.out.println(i);
		list2.removeFirst();
		System.out.println("REMOVED FIRST ELEMENT");		
		for (Integer i : list2)
			System.out.println(i);
	}
	
	public static void main(String[] args)
	{
		new P2Driver();
	}
}