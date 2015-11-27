//HashMap226 interface file

import java.util.Collection;
import java.util.Set;

/**
 * An interface specifying methods for a hash map.
 *
 * @author more
 * 
 * @param <K>
 *            the type of keys stored in the hash map
 * @param <V>
 *            the type of the associated data stored in the map
 *
 */

public interface HashMap226<K, V> {

    /**
     * Associate the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key, the old value 
     * is replaced.
     *
     * @param key
     *            the key with which the specified value is to be associated
     * @param value
     *            value to be associated with the specified key (may be null)
     * @return 
     *         the previous value associated with key, or null if there was
     *         no previous mapping for key.  (A null return value can also
     *         indicate that the map previously associated null with key.)
     */
    V put(K key, V value);


    /**
     * Return the unique value to which the specified key is mapped, or null if
     * this map contains no mapping for the key.  (A return value of null does
     * not necessarily indicate that the map contains no mapping for the key, as
     * null values are permitted in this map.  Use containsKey to distinguish
     * the no-mapping-present case from the map-with-null-value case.)
     *
     * @param key
     *             the key whose associated value is to be returned
     * @return
     *          the value to which the specified key is mapped, or null if this
     *          map contains no mapping for the key
     */
    V get(Object key);


    /**
     * Return true if this map contains a mapping for the specified key.
     *
     * @param key
     *             the key whose presence in this map is to be tested
     * @return
     *             true if this map contains a mapping for the specified key
     */
    boolean containsKey(Object key);


    /**
     * Remove the mapping for the specified key from this map if present.
     *
     * @param key
     *             the key whose mapping is to be removed from the map
     * @return
     *          the previous value associated with key, or null if there
     *          was no mapping for key.  (A null return can also indicate
     *          that the map previously associated null with the key.)
     */
    V remove(Object key);


    /**
     * Remove all of the mappings from this map.  The map will be empty
     * after this call returns.
     */
    void clear();

    /**
     * Return the number of key-value mappings in this map.
     * 
     * @return
     *          the number of key-value mappings in this map
     */
    int size();

    /**
     * Return a Set view of the keys contained in this map.  
     * 
     * @return
     *          a Set view of the keys contained in this map
     */
    Set<K> keySet();


    /**
     * Return a Collection view of the values contained in this map. 
     * 
     * @return
     *          a Collection view of the values contained in this map
     */
    Collection<V> values();


}
