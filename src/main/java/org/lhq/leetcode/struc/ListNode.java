package org.lhq.leetcode.struc;

import lombok.ToString;

/**
 * @program: JavaLearn
 * @description:
 * @author: wang.defa
 * @create: 2022-01-24 14:16
 */
@ToString
public class ListNode {
	public int val;
	public ListNode next;

	public ListNode() {
	}

	public ListNode(int val) {
		this.val = val;
	}

	public ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}
