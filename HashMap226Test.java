import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;

public class HashMap226Test extends HashMap226LP<Integer, String> {
    
    private HashMap226LP<Integer, String> table;

    @Before
    public void makeTable() {
        this.table = new HashMap226LP<Integer , String>();
    }
    
    @Test
    public void testNewEmpty() {
        assertEquals(true, table.size() == 0);
    }
    
    @Test
    public void testAdd() {
        this.table.put(5, "Emily Brahma");
        assertEquals(true, table.size() == 1);
        this.table.put(5, "Hello"); // size should stay the same
        assertEquals(true, table.size() == 1);
        String s1 = table.get(5); //what the new value should be
        assertEquals(true, s1 == "Hello"); 
        
        this.table.put(6, "Hello");
        assertEquals(true, table.size() == 2); // size should increase

        
        this.table.put(2, "Apple");
        this.table.put(10, "Bob");
        this.table.put(12, "Cat");
        this.table.put(11, "Dan"); // time for rehash
        assertEquals(true, table.containsKey(10));
        assertEquals(true, table.containsKey(11));
        assertEquals(true, table.containsKey(2));
        assertEquals(true, table.containsKey(5));
        assertEquals(true, table.containsKey(12));
        this.table.put(1, "Entry");
        this.table.put(40, "Test");
        assertEquals(true, table.containsKey(1));
        assertEquals(true, table.containsKey(40));
        
 
    } 
    
    @Test
    public void testSize() {
    	this.table.put(2, "Apple");
    	this.table.put(2, "Emily"); //changes the value
        this.table.put(10, "Bob");
        this.table.put(12, "Cat");
        this.table.put(11, "Dan");
        assertEquals(true, table.size() == 4);
        this.table.put(13, "Hi");
        assertEquals(true, table.size() == 5);
        this.table.put(60, "this");
        this.table.put(20, "is");
        assertEquals(true, table.size() == 7);
    	this.table.remove(2);
    	this.table.remove(10);
    	this.table.remove(12);
    	this.table.remove(11);
    	this.table.remove(13);
    	this.table.remove(60);
    	this.table.remove(20);
    	assertEquals(true, table.size() == 0);
  
    }
    
    @Test
    public void testClear(){
    	this.table.put(5, "Emily Brahma");
    	this.table.put(6, "Apple");
    	this.table.put(20, "Pie");
    	this.table.put(32, "Hello World");
    	assertEquals(true, table.size() == 4);
    	assertEquals(true, table.containsKey(20));
    	this.table.clear();
    	assertEquals(true, this.table.size()==0);
    	assertEquals(false, table.containsKey(20));    	
    }
    
    @Test
    public void testRemove(){
    	this.table.put(5, "Emily Brahma");
    	assertEquals(true, table.size()==1);
    	this.table.put(6, "Hello");
    	this.table.put(2, "Apple");
        this.table.put(10, "Bob");
        this.table.put(12, "Cat");
        this.table.put(11, "Dan");
        this.table.put(25, "hi");
    	this.table.remove(5);
    	assertEquals(false, table.containsKey(5));
    	assertEquals(true, table.containsKey(6));
    	assertEquals(true, table.containsKey(2));
    	assertEquals(true, table.containsKey(10));
    	assertEquals(true, table.containsKey(11));
    	assertEquals(true, table.containsKey(12));
    	assertEquals(true, table.containsKey(25));
    	this.table.remove(25); 
    	assertEquals(false, table.containsKey(25));
    	String s1 = this.table.remove(22); //removing a key that doesn't exist
    	assertEquals(true, s1 == null);
    	
    	
    	
    }
    

    @Test
    public void testContainsKey() {
    	Integer test = new Integer(128); 
    	this.table.put(6, "Hello");
    	assertEquals(true, table.containsKey(6));
    	this.table.remove(6);
    	this.table.put(5, "My");
    	this.table.put(test, "Name");
        assertEquals(false, table.containsKey(6));//a key that has been removed
        assertEquals(false, table.containsKey(3));//a key that was never there
        assertEquals(true, table.containsKey(test));//a key that is there
    }
    
    @Test
    public void testGet(){
    	this.table.put(6, "Hello");
    	String s1 = table.get(6);
    	assertEquals(true, s1.equals("Hello"));
    	this.table.put(10, "World");
    	this.table.remove(10);
    	String s2 = table.get(10); //a string that is no longer in the hashtable
    	assertEquals(true, s2 == null);
    	 	
    }
    
    @Test
    public void testKeySet(){
    	this.table.put(2, "Apple");
    	this.table.put(2, "Emily");
        this.table.put(10, "Bob");
        this.table.put(12, "Cat");
        this.table.put(11, "Dan");
        
        
        Set<Integer> keys = new HashSet<Integer>();
        keys = this.table.keySet();
        assertEquals(true, keys.contains(2));
        assertEquals(true, keys.contains(10));
        assertEquals(true, keys.contains(12));
        assertEquals(true, keys.contains(11));
        assertEquals(true, keys.size()==4);
     
    }
    
    @Test
    public void testValues(){
    	this.table.put(2, "Apple");
    	this.table.put(2, "Emily");
        this.table.put(10, "Bob");
        this.table.put(12, "Cat");
        this.table.put(11, "Dan");
        
        
        Collection<String> vals = new HashSet<String>();
        vals = this.table.values();
        
        assertEquals(true, vals.contains("Emily"));
        assertEquals(true, vals.contains("Bob"));
        assertEquals(true, vals.contains("Cat"));
        assertEquals(true, vals.size()==4);
     
    }
    

}