package net.zhanghc.toolkit.stat;

import java.util.Collection;

import net.zhanghc.toolkit.collections.ComputableMap;

/**
 * 
 * Utility that helps to record count for each key.<br/>
 * You can use this utility instead of Map<E, Integer> and do not care about checking null values and initialization.<br/> 
 * It will also provide some practical methods for statistic.<br/>
 * 
 * @author zhanghc.net
 *
 */
public final class Counter<K> extends ComputableMap<K, Integer> {
	private int total = 0;

	/**
	 * 
	 * Record 'key' as 1 time.<br/>
	 * Could be treated as Map{key}++.<br/>
	 * 
	 * @param key the given 'key'
	 */
	public void count(K key) {
		add(key, 1);
		total++;
	}

	/**
	 * 
	 * Record 'key' as n times.
	 * Could be treated as Map{key} += n.<br/>
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
	 * Record all 'key's in collection as 1 time for each.<br/>
	 * Could be treated as : <br/>
	 * foreach(K key : collection) { Map{key}++; } <br/>
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
