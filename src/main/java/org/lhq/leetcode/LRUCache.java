package org.lhq.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: JavaLearn
 * @description:
 * @author: wang.defa
 * @create: 2022-01-28 09:25
 */
public class LRUCache {
	private Map<Integer,Integer> cache ;
	public LRUCache(int capacity) {
		this.cache = new HashMap<>(capacity);
	}

	public int get(int key) {
		return -1;
	}

	public void put(int key, int value) {

	}
}
