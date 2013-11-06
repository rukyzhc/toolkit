package net.zhanghc.toolkit.stat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class SortableMap<K, V extends Comparable<V>> implements Sortable<K, V>, Iterable<Map.Entry<K, V>> {
	HashMap<K, V> map = new HashMap<K, V>();
	
	@Override
	public void record(K key, V value) {
		map.put(key, value);
	}

	@Override
	public List<Entry<K, V>> sortedValues(boolean descending) {
		List<Entry<K, V>> ret = new ArrayList<Entry<K, V>>(map.entrySet());
		Collections.sort(
				ret, 
				descending ? new Comparator<Entry<K, V>>() {
					public int compare(Entry<K, V> a, Entry<K, V> b) { 
						return - a.getValue().compareTo(b.getValue()); 
					} 
				} : new Comparator<Entry<K, V>>() {
					public int compare(Entry<K, V> a, Entry<K, V> b) { 
						return a.getValue().compareTo(b.getValue());
					}
				}
				);
		return ret;
	}

	@Override
	public V result(K key) {
		if(map.containsKey(key)) {
			return map.get(key);
		} else {
			return zero();
		}
	}

	@Override
	public boolean containsKey(K key) {
		return map.containsKey(key);
	}

	@Override
	public Set<K> keySet() {
		return map.keySet();
	}

	@Override
	public Set<K> keySet(V threshold, boolean greater) {
		HashSet<K> ret = new HashSet<K>();
		if(greater) {
			for(Map.Entry<K, V> entry : map.entrySet()) {
				if(entry.getValue().compareTo(threshold) > 0) {
					ret.add(entry.getKey());
				}
			} 
		} else {
			for(Map.Entry<K, V> entry : map.entrySet()) {
				if(entry.getValue().compareTo(threshold) < 0) {
					ret.add(entry.getKey());
				}
			}
		}
		return ret;
	}

	@Override
	public void reset() {
		map.clear();
	}
	
	public int size() {
		return map.size();
	}
	
	public boolean isEmpty() {
		return map.isEmpty();
	}
	
	public Iterator<Map.Entry<K, V>> iterator() {
		return map.entrySet().iterator();
	}

	protected abstract V zero();

}
