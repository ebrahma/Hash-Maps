import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
//import java.lang.Math;

/**
 * An implementation of HashMap 226 with method definitions for a hash map.
 *
 * @author Emily Brahma
 * 
 * @param <K>
 *            the type of keys stored in the hash map
 * @param <V>
 *            the type of the associated data stored in the map
 *
 */
public class HashMap226LP<K, V> implements HashMap226<K, V> {

    /**
     * Using in the prime finder.
     */
    private static final int PRIMENUM = 3;
    
    /**
     * The hash table that stores all the data.
     */
    private Entry[] hashTable;
    
    /**
     * Keeps track of the array size of the hash table.
     */
    private int arraySize;
    
    /**
     * Keeps track of how many data elements are in the hash table.
     */
    private int slotsFilled;
    
    /**
     * Ratio that the array can be filled before it has to be rehashed.
     */
    private double loadFactor;
    
    /**
     * The default starting size of the array.
     */
    private final int startSize = 11;
    
    /**
     * The default loadFactor.
     */
    private final double ratio = 0.5; 

    /**
     * The default hash table constructor.
     */
    public HashMap226LP() {
        this.arraySize = this.startSize;
        this.loadFactor = this.ratio;
        this.slotsFilled = 0;
        this.hashTable = (Entry[]) java.lang.reflect.Array.newInstance(
                Entry.class, this.arraySize);

    }

    /**
     * The hash table constructor that takes in the array size
     * and load factor.
     * 
     * @param raSize The starting array size of the hash table
     * @param lFactor How full the array can be 
     */
    public HashMap226LP(int raSize, float lFactor) {
        //ERROR CHECKING
        if (raSize < 0 || lFactor < 0 || lFactor > 1) {
            throw new IllegalArgumentException();
        }
        
        this.arraySize = raSize;
        this.loadFactor = lFactor;
        this.slotsFilled = 0;
        this.hashTable = (Entry[]) java.lang.reflect.Array.newInstance(
                Entry.class, this.arraySize);
    }

    /**
     * Associate the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value 
     * is replaced.
     *
     * @param k
     *            the key with which the specified value is to be associated
     * @param v
     *            value to be associated with the specified key (may be null)
     * @return 
     *         the previous value associated with key, or null if there was
     *         no previous mapping for key.  (A null return value can also
     *         indicate that the map previously associated null with key.)
     */
    public V put(K k, V v) {
        V temp;
        int startIndex = k.hashCode() % this.arraySize;
        startIndex = Math.abs(startIndex);

        int position = 0;
        for (int i = 0; i < this.arraySize; i++) {
            position = (startIndex + i) % this.arraySize;
            if (this.hashTable[position] == null) {
                if (((double) (this.slotsFilled + 1)) / this.arraySize 
                        > this.loadFactor) {
                    this.reHash();
                    this.put(k , v); //put k and v into new rehashed map
                    return null;
                } else {
                    Entry newEntry = new Entry(k , v);
                    this.hashTable[position] = newEntry;
                    this.slotsFilled++;
                    return null;
                }
            } else if (this.hashTable[position].key.equals(k) 
                    && this.hashTable[position].ghost) {
                temp = this.hashTable[position].data;
                Entry newEntry = new Entry(k , v);
                this.hashTable[position] = newEntry;
                this.slotsFilled++;
                return temp;
            } else if (this.hashTable[position].key.equals(k) 
                    && !this.hashTable[position].ghost) {
                temp = this.hashTable[position].data;
                Entry newEntry = new Entry(k , v);
                this.hashTable[position] = newEntry;
                return temp;
            }

        }

        return null;

   
    }
    
    /**
     * Return the unique value to which the specified key is mapped, or null if
     * this map contains no mapping for the key.  (A return value of null does
     * not necessarily indicate that the map contains no mapping for the key, as
     * null values are permitted in this map.  Use containsKey to distinguish
     * the no-mapping-present case from the map-with-null-value case.)
     *
     * @param k
     *             the key whose associated value is to be returned
     * @return
     *          the value to which the specified key is mapped, or null if this
     *          map contains no mapping for the key
     */
    public V get(Object k) {
        int startIndex = k.hashCode() % this.arraySize;
        startIndex = Math.abs(startIndex);
        int position = 0;
        for (int i = 0; i < this.arraySize; i++) {
            position = (startIndex + i) % this.arraySize;
            if (this.hashTable[position] == null) {
                return null;
            } else if (this.hashTable[position].key.equals(k) 
                    && !this.hashTable[position].ghost) {
                return this.hashTable[position].data;
            }
        }
        return null;

    }
    
    /**
     * Return true if this map contains a mapping for the specified key.
     *
     * @param k
     *             the key whose presence in this map is to be tested
     * @return
     *             true if this map contains a mapping for the specified key
     */
    public boolean containsKey(Object k) {
        int startIndex = k.hashCode() % this.arraySize;
        
        startIndex = Math.abs(startIndex);
        int position = 0;
        for (int i = 0; i < this.arraySize; i++) {
            position = (startIndex + i) % this.arraySize;

            if (this.hashTable[position] == null) {
                return false;
            } else if (this.hashTable[position].key.equals(k)
                    && !this.hashTable[position].ghost) {
                return true;
            }
        }

        return false;
    }
    // (i + k)modM i = starting index, k is iterator, M arraySize



    /**
     * Remove the mapping for the specified key from this map if present.
     *
     * @param k
     *             the key whose mapping is to be removed from the map
     * @return
     *          the previous value associated with key, or null if there
     *          was no mapping for key.  (A null return can also indicate
     *          that the map previously associated null with the key.)
     */
    public V remove(Object k) {
        V temp;
        int startIndex = k.hashCode() % this.arraySize;
        startIndex = Math.abs(startIndex);
        int position = 0;
        for (int i = 0; i < this.arraySize; i++) {
            position = (startIndex + i) % this.arraySize;
            if (this.hashTable[position] == null) {
                return null;
            } else if (this.hashTable[position].key.equals(k) 
                    && !this.hashTable[position].ghost) {
                temp = this.hashTable[position].data;
                this.hashTable[position].data = null;
                this.hashTable[position].ghost = true;
                this.slotsFilled--;
                return temp;

            }
        }
        return null;
    }


    /**
     * Remove all of the mappings from this map.  The map will be empty
     * after this call returns.
     */
    public void clear() {
        Entry[] tempTable = (Entry[]) java.lang.reflect.Array.newInstance(
                Entry.class, this.arraySize);
        this.hashTable = tempTable;
        this.slotsFilled = 0;
        
    }


    /**
     * Return the number of key-value mappings in this map.
     * 
     * @return
     *          the number of key-value mappings in this map
     */
    public int size() {
        return this.slotsFilled;
    }
    
    /**
     * Return the set of keys in the hash table.
     * 
     * @return
     *          the set of keys in the hash table
     */
    public Set<K> keySet() {
        Set<K> keys = new HashSet<K>();
        for (int i = 0; i < this.arraySize; i++) {
            if (this.hashTable[i] != null && !this.hashTable[i].ghost) {
                keys.add(this.hashTable[i].key);
            }
        }
        return keys;
    }
    
    /**
     * Return the collection of values in the hash table.
     * 
     * @return
     *          the collection of values in the hash table
     */
    public Collection<V> values() {
        Collection<V> vals = new HashSet<V>();
        for (int i = 0; i < this.arraySize; i++) {
            if (this.hashTable[i] != null && !this.hashTable[i].ghost) {
                vals.add(this.hashTable[i].data);
            }
        }
        return vals;
    }
    

    /**
     * reHashes the table to an array of the next largest prime.
     */
    private void reHash() {
        
        Entry[] tempTable = (Entry[]) java.lang.reflect.Array.newInstance(
                Entry.class, this.arraySize);

        for (int i = 0; i < this.arraySize; i++) {
            tempTable[i] = this.hashTable[i];
        }

        int oldSize = this.arraySize;
        this.arraySize = nextPrime(2 * this.arraySize);
        this.slotsFilled = 0;
        Entry[] newTable = (Entry[]) java.lang.reflect.Array.newInstance(
                Entry.class, this.arraySize);
        this.hashTable = newTable;
        for (int i = 0; i < oldSize; i++) {

            if (tempTable[i] != null && !tempTable[i].ghost) {
                this.put(tempTable[i].key, tempTable[i].data);
            }
        }


    }

    /**
     * Internal method to find a prime number at least as large as n.
     * By: Mark Allen Weiss, I did not write this
     * @param n the starting number (must be positive).
     * @return a prime number larger than or equal to n.
     */
    private static int nextPrime(int n) {
        if (n % 2 == 0) {
            n++;
        }
        
        for ( ; !isPrime(n); n += 2) {
            int x = 0; //dummy statement that satisfies CS
        }
        return n;
    }

    /**
     * Internal method to test if a number is prime.
     * By: Mark Allen Weiss, I did not write this
     * Not an efficient algorithm.
     * @param n the number to test.
     * @return the result of the test.
     */
    private static boolean isPrime(int n) {
        if (n == 2 || n == PRIMENUM) {
            return true;
        }
        if (n == 1 || n % 2 == 0) {
            return false;
        }
        for (int i = PRIMENUM; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Defines object Entry that is placed into hash table.
     * 
     * @param <K>
     *            the type of keys stored in the hash map
     * @param <V>
     *            the type of the associated data stored in the map
     */
    protected class Entry {
        /**
         * The key of the entry.
         */
        public K key;
        
        /**
         * The data of the entry.
         */
        public V data;
        
        /**
         * Keeps track of whether the entry is removed or not.
         */
        public boolean ghost;

        /**
         * Constructs the Entry object.
         * 
         * @param k
         *            The key that is put into the entry.
         * @param d
         *            The data that is put into the entry.
         */
        public Entry(K k, V d) {
            this.key = k;
            this.data = d;
            this.ghost = false;
        }

    }
}