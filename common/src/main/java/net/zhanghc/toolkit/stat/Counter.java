package net.zhanghc.toolkit.stat;

import java.util.Collection;

import net.zhanghc.toolkit.collections.ComputableMap;

/**
 * 
 * Utility that helps to record count for each key. 
 * You can use this utility instead of Map<E, Integer> and do not care about checking null values and initialization.<br/> 
 * It will also provide some practical methods for statistic.
 * 
 * @author zhanghc.net
 *
 */
public final class Counter<K> extends ComputableMap<K, Integer> {
	private int total = 0;

	/**
	 * 
	 * Record 'key' as 1 time, which
	 * could be treated as Map{key}++.
	 * 
	 * @param key the given 'key'
	 */
	public void count(K key) {
		add(key, 1);
		total++;
	}

	/**
	 * 
	 * Record 'key' as n times, which
	 * could be treated as Map{key} += n.
	 * 
	 * @param key the given 'key'
	 * @param n recording times.
	 */
	public void count(K key, int n) {
		add(key, n);
		total += n;
	}

	/**
	 * 
	 * Record all 'key's in collection as 1 time for each, which
	 * could be treated as : 
	 * foreach(K key : collection) { Map{key}++; } 
	 * 
	 * @param keys
	 */
	public void countAll(Collection<K> keys) {
		for(K key : keys) {
			count(key);
		}
	}

	@Override
	public void reset() {
		super.reset();
		total = 0;
	}

	/**
	 * 
	 * TODO
	 * 
	 * @return
	 */
	public int totalCount() {
		return total;
	}

	@Override
	protected Integer add(Integer a, Integer b) {
		return a + b;
	}

	@Override
	protected Integer subtract(Integer a, Integer b) {
		return a - b;
	}

	@Override
	protected Integer zero() {
		return 0;
	}
}
