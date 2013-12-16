package net.zhanghc.toolkit.collections;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestTopKPriorityQueue {

	@Test
	public void test() {
		int[] cases = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
		TopKPriorityQueue<Integer> pq = new TopKPriorityQueue<Integer>(5, false);
		TopKPriorityQueue<Integer> ipq = new TopKPriorityQueue<Integer>(5, true);
		
		for(int i : cases) {
			pq.add(i);
			ipq.add(i);
		}
		
		for(int i = 0; i < 5; i++) {
			assertEquals((int)pq.poll(), cases[4 - i]);
			assertEquals((int)ipq.poll(), cases[5 + i]);
		}
	}

}
