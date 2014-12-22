package net.zhanghc.toolkit.collections;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class SortableMap<K, V extends Comparable<V>> extends HashMap<K, V> implements Sortable<K, V>, Iterable<Map.Entry<K, V>> {
	private static final long serialVersionUID = -322830270195194930L;

	@Override
	public void record(K key, V value) {
		put(key, value);
	}

	@Override
	public List<Entry<K, V>> sortedValues(boolean descending) {
		List<Entry<K, V>> ret = new ArrayList<Entry<K, V>>(entrySet());
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
		if(containsKey(key)) {
			return get(key);
		} else {
			return zero();
		}
	}

	@Override
	public Set<K> keySet(V threshold, boolean greater) {
		HashSet<K> ret = new HashSet<K>();
		if(greater) {
			for(Map.Entry<K, V> entry : entrySet()) {
				if(entry.getValue().compareTo(threshold) > 0) {
					ret.add(entry.getKey());
				}
			} 
		} else {
			for(Map.Entry<K, V> entry : entrySet()) {
				if(entry.getValue().compareTo(threshold) < 0) {
					ret.add(entry.getKey());
				}
			}
		}
		return ret;
	}

	@Override
	public void reset() {
		clear();
	}
	
	@Override
	public Iterator<Map.Entry<K, V>> iterator() {
		return entrySet().iterator();
	}

	protected abstract V zero();
	
	public void export(String target, boolean sorted) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(target));
		for(Map.Entry<K, V> entry : (sorted ? sortedValues(true) : entrySet())) {
			bw.write(String.format("%s\t%s", entry.getKey().toString(), entry.getValue().toString()));
			bw.newLine();
		}
		bw.close();
	}

}
