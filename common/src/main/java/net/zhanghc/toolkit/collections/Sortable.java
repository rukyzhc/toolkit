package net.zhanghc.toolkit.collections;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface Sortable<K, V extends Comparable<V>> {
	
	public void record(K key, V value);

	/**
	 * 
	 * Reset this utility, while all values and elements would lost. 
	 * 
	 */
	public void reset();
	
	/**
	 * 
	 * Get count related to the given 'key'. 
	 * 
	 * @param elem the given element.
	 * @return count of 'key' or 0 if 'key' has never been involved.
	 */
	public V result(K key);
	
	/**
	 * 
	 * Return a list of all entries sorted by the count, where
	 * 'descending' specifies sorting descendingly or ascendingly.
	 * 
	 * @param descending sorting order.
	 * @return a sorted list with specific order. 
	 */
	public List<Map.Entry<K, V>> sortedValues(boolean descending);

	/**
	 * 
	 * Get set of all keys. 
	 * 
	 * @return set of all keys.
	 */
	public Set<K> keySet();

	/**
	 * 
	 * TODO
	 * 
	 * @param threshold
	 * @param greater
	 * @return
	 */
	public Set<K> keySet(V threshold, boolean greater);

	/**
	 * 
	 * Check whether 'key' has been involved.
	 * 
	 * @param key the given 'key'
	 * @return whether given 'key' has been involved.
	 */
	public boolean containsKey(K key);

}
