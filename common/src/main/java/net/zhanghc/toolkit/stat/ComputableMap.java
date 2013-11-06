package net.zhanghc.toolkit.stat;

public abstract class ComputableMap<K, V extends Comparable<V>> extends SortableMap<K, V> {

	public void add(K key, V value) {
		if(map.containsKey(key)) {
			map.put(key, add(map.get(key), value));
		} else {
			map.put(key, value);
		}
	}

	public void subtract(K key, V value) {
		if(map.containsKey(key)) {
			map.put(key, subtract(map.get(key), value));
		} else {
			map.put(key, value);
		}
	}

	protected abstract V add(V a, V b);

	protected abstract V subtract(V a, V b);

}
