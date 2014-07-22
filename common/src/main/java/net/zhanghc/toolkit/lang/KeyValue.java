package net.zhanghc.toolkit.lang;

public class KeyValue<K, V> {
	K key;
	V value;
	public KeyValue(K k, V v) { this.key = k; this.value = v; }
	public K getKey() { return key; }
	public V getValue() { return value; }
}
