package net.zhanghc.toolkit.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeyIndex<T> {
	private List<T> index;
	private Map<T, Integer> map;
	
	public KeyIndex() {
		index = new ArrayList<T>();
		map = new HashMap<T, Integer>();
	}
	
	public int add(T key) {
		if(map.containsKey(key)) {
			return map.get(key);
		} else {
			int s = index.size();
			index.add(key);
			map.put(key, s);
			return s;
		}
	}
	
	public int size() {
		return index.size();
	}
	
	public int index(T key) {
		if(map.containsKey(key)) {
			return map.get(key);
		} else {
			return -1;
		}
	}
	
	public T get(int i) {
		return index.get(i);
	}

}
