/**
   *  Program 4
   *  This is the StockItem class for creating stock items with many values.
   *  CS310-01
   *  5/7/2019
   *  @author  Karl Parks cssc1506
   */

//import java.util.Iterator; note required
//import data_structures.*; not required

public class StockItem implements Comparable<StockItem> {
	String SKU;
	String description;
	String vendor;
	float cost;
	float retail;
		
	// Constructor. Creates a new StockItem instance.
	public StockItem(String SKU, String description, String vendor, float cost, float retail) {
		this.SKU = SKU;
		this.description = description;
		this.vendor = vendor;
		this.cost = cost;
		this.retail = retail;
	}
	// Follows the specifications of the Comparable Interface.
	// The SKU is always used for comparisons, in dictionary order.
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int compareTo(StockItem n) {
		return (((Comparable)this.SKU).compareTo(n.SKU));
	}
	// Returns an int representing the hashCode of the SKU.
	public int hashCode() {
		return SKU.hashCode();
	}
	// standard get methods
	public String getDescription() {
		return description;
	}
	public String getVendor() {
		return vendor;
	}
	public float getCost() {
		return cost;
	}
	public float getRetail() {
		return retail;
	}
	// All fields in one line, in order
	public String toString() {
		return SKU + " " + description + " " + vendor + " " + Float.toString(cost) + " " + Float.toString(retail);
	}
}