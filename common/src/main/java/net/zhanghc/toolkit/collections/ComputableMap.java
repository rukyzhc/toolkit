package net.zhanghc.toolkit.collections;

import java.util.Map;

public abstract class ComputableMap<K, V extends Comparable<V>> extends SortableMap<K, V> {
	private static final long serialVersionUID = -3520573015238221682L;

	public void add(K key, V value) {
		if(containsKey(key)) {
			put(key, add(get(key), value));
		} else {
			put(key, value);
		}
	}

	public void subtract(K key, V value) {
		if(containsKey(key)) {
			put(key, subtract(get(key), value));
		} else {
			put(key, value);
		}
	}
	
	public void merge(SortableMap<K, V> collections, Compute<V> method) {
		for(Map.Entry<K, V> e : collections.entrySet()) {
			K k = e.getKey();
			V v = e.getValue();
			if(containsKey(k)) {
				put(k, method.merge(v, get(k)));
			}
		}
	}

	protected abstract V add(V a, V b);

	protected abstract V subtract(V a, V b);
	
	public interface Compute<V> {
		public V merge(V a, V b);
	}

}
