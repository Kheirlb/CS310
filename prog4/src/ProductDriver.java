import java.util.Iterator;

public class ProductDriver {
	public ProductDriver() {
		System.out.println("Created Stores 1 & 2");
		ProductLookup store1 = new ProductLookup(10);
		ProductLookup store2 = new ProductLookup(20);
		System.out.println("Created Items");
		StockItem item1 = new StockItem("W32TS618", "18oz Water Bottole", "Hydroflask", 35, 40);
		StockItem item2 = new StockItem("555CS20016", "Phone Case Galaxy s7", "Spigen", 20, 32);
		StockItem item3 = new StockItem("12546011112", "Gum Tropical Twist", "Trident", 10, 15);
		StockItem item4 = new StockItem("e7", "Over-ear Headphones", "COWIN", 45, 60);
		StockItem item5 = new StockItem("042CS20454", "Phone Case Iphone 8", "Spigen", 16, 33);
		
		System.out.println("---Items 1-5---");
		System.out.println("\t1: " + item1.toString());
		System.out.println("\t2: " + item2.toString());
		System.out.println("\t3: " + item3.toString());
		System.out.println("\t4: " + item4.toString());
		System.out.println("\t5: " + item5.toString());
		
		System.out.println("---Stocking Store 1---");
		store1.addItem(item1.SKU, item1);
			//Stocked Item Succesfully 
		store1.addItem(item1.SKU, item1); //no identical keys are allowed
			//Cannot stock item with same key
		store1.addItem(item2.SKU, item2);
		store1.addItem(item3.SKU, item3);
		store1.addItem(item4.SKU, item4);
		store1.addItem(item5.SKU, item5);
		
		System.out.println("---Stocking Store 2---");
		store2.addItem(item3.SKU, item3);
		
		System.out.println("---Test Getters---");
		System.out.println("Get Item 12546011112:\n\t" + store1.getItem("12546011112"));
		System.out.println("Get Retail 12546011112:\n\t" + store1.getRetail("12546011112"));
		System.out.println("Get Cost 12546011112:\n\t" + store1.getCost("12546011112"));
		System.out.println("Get Description 12546011112:\n\t" + store1.getDescription("12546011112"));
		
		System.out.println("Deleted 12546011112 (The Gum):\n\t" + store1.deleteItem("12546011112"));
		System.out.println("Check Retail Price of Missing Gum:\n\t" + store1.getRetail("12546011112"));
		
		System.out.println("\n---Print All---");
		store1.printAll();
		System.out.println("\n---Print Vendor Spigen---");
		store1.print("Spigen");
		
		System.out.println("\n---Use Key Iterator---");
		Iterator<String> theIterator = store1.keys();
		while(theIterator.hasNext()) {
			System.out.println(theIterator.next());
		}
		
		System.out.println("\n---Use Value Iterator---");
		Iterator<StockItem> theValIterator = store1.values();
		while(theValIterator.hasNext()) {
			System.out.println(theValIterator.next());
		}


	}

	public static void main(String[] args) {
		new ProductDriver();
	}

}
