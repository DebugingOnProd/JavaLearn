import struc.TreeNode;
import java.util.*;

/**
 * @program: leetcode
 * @description:
 * @author: LittleCuteWangDF
 * @create: 2021-07-30 14:01
 */
public class LeetCode {
	/**
	 * 每日一题之
	 * 二叉树的垂序遍历
	 * @param root
	 * @return
	 */
	public List<List<Integer>> verticalTraversal(TreeNode root) {
		//nodes得到深度优先遍历之后的结果
		List<int[]> nodes = new ArrayList<int[]>();
		dfs(root, 0, 0, nodes);
		// 对node进行排序
		nodes.sort(Comparator
				// col 为第一关键字升序
				.comparingInt((int[] x) -> x[0])
				//row 为第二关键字升序
				.thenComparingInt(x -> x[1])
				//value 为第三关键字升序
				.thenComparingInt(x -> x[2]));

		List<List<Integer>> ans = new ArrayList<>();
		int size = 0;
		int lastcol = Integer.MIN_VALUE;
		// 遍历排序之后的node节点
		for (int[] tuple : nodes) {
			int col = tuple[0], row = tuple[1], value = tuple[2];
			if (col != lastcol) {
				lastcol = col;
				ans.add(new ArrayList<Integer>());
				size++;
			}
			ans.get(size - 1).add(value);
		}
		return ans;
	}
	// 二叉树深度优先遍历
	private void dfs(TreeNode node, int row, int col, List<int[]> nodes) {
		if (node == null) {
			return;
		}
		nodes.add(new int[]{col, row, node.val});
		dfs(node.left, row + 1, col - 1, nodes);
		dfs(node.right, row + 1, col + 1, nodes);
	}

	/**
	 * 最长公共前缀--纵向查找法
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix(String[] strs) {
		// 如果数组为空,或者长度为0 直接返回空字符串
		if (strs == null|| strs.length == 0){
			return "";
		}
		// 获取第一字符串的长度
		int length = strs[0].length();
		// 获取数组的长度
		int count = strs.length;
		// 纵向比较
		for (int i = 0; i < length; i++) {
			// 获取第一个字符串的第一个下标的值
			char a = strs[0].charAt(i);
			for (int j = 1; j < count; j++) {
				// 如果i等于当前第j个字符串的长度
				// 又或者第j个字符串的第i个字母与首个字符串的第i个不相等
				if (i == strs[j].length() || strs[j].charAt(i) != a){
					return strs[0].substring(0, i);
				}
			}
		}
		return strs[0];
	}
	/**
	 * 罗马数字转整数
	 * @param s
	 * @return
	 */
	public int romanToInt(String s) {
		int sum = 0;
		// 获取第一个阿拉伯数字
		int pre = _getValue(s.charAt(0));
		for (int i = 1; i < s.length(); i++) {
			//获取该下标对应的罗马数字的阿拉伯数字
			int n = _getValue(s.charAt(i));
			// 如果前一个数字小于数字n,则n的前面一个数为负数需要减去
			// 例如 IV  = -1 + 5 = 4
			if (pre<n){
				sum-=pre;
				// 若前一个数大于n 着这两个数正常相加
			}else {
				sum+=pre;
			}
			//循环下一个数,当前数变成前一个数
			pre = n;
		}
		sum+=pre;

		return sum;
	}

	/**
	 * 将罗马数字转换成对应的阿拉伯数字
	 * @param ch
	 * @return
	 */
	private int _getValue(char ch){
		switch (ch){
			case 'I':return 1;
			case 'V':return 5;
			case 'X':return 10;
			case 'L':return 50;
			case 'C':return 100;
			case 'D':return 500;
			case 'M':return 1000;
			default:return 0;
		}
	}
	/**
	 * 回文数
	 * @param x
	 * @return
	 */
	public boolean isPalindrome(int x) {
		int reverseNumber = 0;
		if (x<0 || x%10 == 0 && x!=0){
			return false;
		}while (x>reverseNumber){
			reverseNumber = reverseNumber*10 + x%10;
			x/=10;
		}
		return x==reverseNumber||x==reverseNumber/10;
	}

	/**
	 * 整数反转
	 * @param x
	 * @return
	 */
	public int reverse(int x) {
		if (x == 0){
			return 0;
		}
		int rev = 0;
		while (x!=0){
			if (rev > ((1<<31)-1)/10 || rev < (-1<<31)/10){
				return 0;
			}else {
				int last = x % 10;
				x = x / 10;
				rev = rev * 10 + last;
			}
		}
		return rev;
	}
}
