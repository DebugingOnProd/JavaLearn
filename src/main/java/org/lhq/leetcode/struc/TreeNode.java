package org.lhq.leetcode.struc;

/**
 * @program: org.lhq.leetcode
 * @description: TreeNode
 * @author: LittleCuteWangDF
 * @create: 2021-07-31 17:22
 */
public class TreeNode {
     public int val;
	public TreeNode left;
	public TreeNode right;
     public TreeNode() {}
	public TreeNode(int val) { this.val = val; }
	public TreeNode(int val, TreeNode left, TreeNode right) {
         this.val = val;
         this.left = left;
         this.right = right;
     }
 }
