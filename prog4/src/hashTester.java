import data_structures.*;

public class hashTester {
	private Hashtable<String, String> hash;
	
	public hashTester() {
		System.out.println("Hello Karl and the other guy (his name is Jerald)");
		hash = new Hashtable<String, String>(11);
		//System.out.println(hash.hashCode("ABC000"));
		String prim = "ABC000";
		hash.add(prim, "Go Fuck Yourself - Item 0");
		System.out.println(hash.getValue(prim));
		hash.delete("ABC000");
		System.out.println(hash.getValue(prim));
		hash.add("ABC000", "Go Fuck Yourself - Item 0");
		hash.add("ABC001", "Go Fuck Yourself - Item 1");
		hash.add("ABC002", "Go Fuck Yourself - Item 2");
		hash.add("ABC003", "Go Fuck Yourself - Item 3");
		hash.add("ABC004", "Go Fuck Yourself - Item 4");
		hash.add("ABC005", "Go Fuck Yourself - Item 5");
		hash.add("ABC006", "Go Fuck Yourself - Item 6");
		hash.add("ABC007", "Go Fuck Yourself - Item 7");
		hash.add("ABC008", "Go Fuck Yourself - Item 8");
		hash.add("ABC009", "Go Fuck Yourself - Item 9");
		hash.add("ABC010", "Go Fuck Yourself - Item 10");
		System.out.println(hash.getValue("ABC005"));
		System.out.println(hash.getValue("ABC006"));
		System.out.println(hash.getValue("ABC007"));
		System.out.println(hash.getValue("ABC008"));
		System.out.println(hash.getValue("ABC009"));
		System.out.println(hash.getValue("ABC010"));
		System.out.println("Contains ABC006: " + hash.contains("ABC006"));
		System.out.println("Contains ABC007: " + hash.contains("ABC007"));
		System.out.println("Contains ABC008: " + hash.contains("ABC008"));
		System.out.println("Contains ABC009: " + hash.contains("ABC009"));
		System.out.println("Contains ABC010: " + hash.contains("ABC010"));
		System.out.println("Contains ABC011: " + hash.contains("ABC011"));

	}

	public static void main(String[] args) {
		new hashTester();
	}

}
