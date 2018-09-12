package basics;

import java.util.HashMap;
import java.util.Iterator;

public final class ImmutableClassII {
	
	private final int id;
	private final String name;
	private final HashMap<String, String> testMap;
	
	public int getId() {
		
		return id;
	}
	
	public String getName() {
		
		return name;
	}
	
	//Accessor function for mutable objects
	public HashMap<String, String> getTestMap() {
		
		//return testMap;
		return (HashMap<String, String>)testMap.clone();
	}
	
	
	//constructor performing deep copy
	//@param i
	//@param n
	//@param hm
	
	public ImmutableClassII(int i, String n, HashMap<String, String>hm) {
		
		System.out.println("performing deep copy for the object initialization");
		this.id = i;
		this.name = n;
		HashMap<String, String>tempMap = new HashMap<String, String>();
		String key;
		Iterator<String> it = hm.keySet().iterator();
		while(it.hasNext()){
			key = it.next();
			tempMap.put(key, hm.get(key));
		}
		this.testMap = tempMap;
	}
	
	
	//Constructor Performing shallow copy
	
	/*public ImmutableClassII(int i, String n, HashMap<String, String>hm){
		System.out.println("Performing shallow copy for objec initialization");
		this.id = i;
		this.name = n;
		this.testMap = hm;
	}*/
	
	//to test the consequences of shallow copy and how to avoid it with deep copy 
	//for creating in immutable classses
	
	public static void main(String[] args) {
		
		HashMap<String, String> h1 = new HashMap<String, String>();
		h1.put("1", "first");
		h1.put("2", "second");
		
		String s = "original";
		
		int i = 10;
		
		ImmutableClassII ic = new ImmutableClassII(i, s, h1);
		
		//lets see whether its copy by field or reference
		System.out.println(s == ic.getName());
		System.out.println(h1 == ic.getTestMap());
		
		//print the ic values
		System.out.println("ic id:");
		System.out.println("ic name:" + ic.getName());
		System.out.println("ic testMap:"+ ic.getTestMap());
		
		//change the local variable values
		i=20;
		s="modified";
		h1.put("3", "third");
		//print the values again
		System.out.println("ic id after local variable change:"+ic.getId());
		System.out.println("ic name after local variable change:"+ic.getName());
		System.out.println("ic testMap after local variable change:"+ic.getTestMap());
		
		HashMap<String, String> hmTest = ic.getTestMap();
		hmTest.put("4", "new");
		
		System.out.println("ic testMap after changing variable from acicssor methods:"+ic.getTestMap());
		
	}

}
