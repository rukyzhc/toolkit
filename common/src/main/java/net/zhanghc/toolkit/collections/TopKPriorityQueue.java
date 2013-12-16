package net.zhanghc.toolkit.collections;

import java.util.Comparator;
import java.util.PriorityQueue;

public class TopKPriorityQueue<E extends Comparable<E>> extends PriorityQueue<E> {
	private static final long serialVersionUID = 1L;
	private int maxSize = 0;
	private boolean descending = true;

	public TopKPriorityQueue(int maxSize) {
		this(maxSize, true);
	}

	public TopKPriorityQueue(int maxSize, boolean descending) {
		super(maxSize, descending ? 
				new Comparator<E>() {
			@Override
			public int compare(E e1, E e2) {
				return e1.compareTo(e2);
			}} :
				new Comparator<E>() {
				@Override
				public int compare(E e1, E e2) {
					return - e1.compareTo(e2);
				}
			});

		this.descending = descending;
		this.maxSize = maxSize;
	}

	@Override
	public boolean add(E elem) {
		if(super.size() < maxSize) {
			return super.add(elem);
		} else {
			E c = super.poll();
			return super.add((descending ^ (elem.compareTo(c) > 0)) ? c : elem);
		}
	}

}
