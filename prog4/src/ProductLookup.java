/**
   *  Program 4
   *  This is the ProductLookup class which uses a Hashtable and 
   *  StockItems class in order to create a store's inventory database.
   *  No duplicate items with identical SKU's are allowed.
   *  CS310-01
   *  5/7/2019
   *  @author  Karl Parks cssc1506
   */

import data_structures.*;
import java.util.Iterator;

public class ProductLookup {	
	private DictionaryADT<String,StockItem> inventory;
	//private DictionaryADT<String,StockItem> inventory;
	//private DictionaryADT<String,StockItem> inventory;
	
	// Constructor. There is no argument-less constructor, or default size
	public ProductLookup(int maxSize) {
		inventory = new Hashtable<> (maxSize);
		//inventory = new BinarySearchTree<>();
		//inventory = new BalancedTree<>();
	}
	// Adds a new StockItem to the dictionary
	@SuppressWarnings("unused")
	public void addItem(String SKU, StockItem item) {
		boolean success = inventory.add(SKU, item);
		//if (success) System.out.println("\tStocked Item Succesfully");
		//else System.out.println("\tCannot Stock Item");
	}
	// Returns the StockItem associated with the given SKU, if it is
	// in the ProductLookup, null if it is not.
	public StockItem getItem(String SKU) {
		return inventory.getValue(SKU);
	}
	// Returns the retail price associated with the given SKU value.
	// -.01 if the item is not in the dictionary
	public float getRetail(String SKU) {
		if (inventory.contains(SKU)) return inventory.getValue(SKU).retail;
		return (-0.1f);
	}
	// Returns the cost price associated with the given SKU value.
	// -.01 if the item is not in the dictionary
	public float getCost(String SKU) {
		if (inventory.contains(SKU)) return inventory.getValue(SKU).cost;
		return (-0.1f);
	}
	// Returns the description of the item, null if not in the dictionary.
	public String getDescription(String SKU) {
		if (inventory.contains(SKU)) return inventory.getValue(SKU).description;
		return null;
	}
	// Deletes the StockItem associated with the SKU if it is
	// in the ProductLookup. Returns true if it was found and
	// deleted, otherwise false.
	public boolean deleteItem(String SKU) {
		if (inventory.contains(SKU)) {
			//System.out.println("Removing Item... (true)");
			return inventory.delete(SKU);
		}
		//System.out.println("No Item Removed... (false)");
		return false;
	}
	// Prints a directory of all StockItems with their associated
	// price, in sorted order (ordered by SKU).
	public void printAll() {
		Iterator<StockItem> itemI = values();
    	while(itemI.hasNext()){
    		System.out.println(itemI.next());
    	}
	}
	// Prints a directory of all StockItems from the given vendor,
	// in sorted order (ordered by SKU).
	public void print(String vendor) {
		Iterator<StockItem> itemI = values();
    	while(itemI.hasNext()){
    		StockItem currentItem = itemI.next();
    		//case in-sensitive comparison for equals ignore
    		if(currentItem.getVendor().equalsIgnoreCase(vendor))
    			System.out.println(currentItem);
    	}
	}
	// An iterator of the SKU keys.
	public Iterator<String> keys() {
		return inventory.keys();
	}
	// An iterator of the StockItem values.
	public Iterator<StockItem> values() {
		return inventory.values();
	}
}