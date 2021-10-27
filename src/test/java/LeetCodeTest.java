import leetcode.LeetCode;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import leetcode.struc.TreeNode;

import java.util.Arrays;


/**
 * @program: leetcode
 * @description: leetcode
 * @create: 2021-07-29 13:45
 */

public class LeetCodeTest {
	LeetCode leetCode;
	@BeforeAll
	public static void before(){
		System.out.println("-------开始执行测试用例--------");
	}
	@AfterAll
	public static void after(){
		System.out.println("-------测试用例执行完成--------");
	}
	@BeforeEach
	public void beforeEach(){
		System.out.println("--------------测试对象实例化--------------");
		leetCode = new LeetCode();
	}
	@AfterEach
	public void afterEach(){
		System.out.println("----------------------------");
	}

	/**
	 * 整数反转
	 */
	@Test
	public void reverse(){
		int x = 1534236469;
		System.out.println((1<<31)-1);
		System.out.println(Integer.MAX_VALUE/10);
		System.out.println("------------------");
		System.out.println(-1<<31);
		System.out.println(Integer.MIN_VALUE/10);
		System.out.println(x);
		System.out.println(leetCode.reverse(x));
	}

	/**
	 * 整数反转
	 */
	@Test
	public void isPalindrome(){
		System.out.println(leetCode.isPalindrome(123));
	}

	/**
	 * 罗马数字转阿拉伯数字
	 */
	@Test
	public void romanToInt(){
		System.out.println(leetCode.romanToInt("IVI"));
	}
	@Test
	public void longestCommonPrefix(){
		String[] strings = {"flower", "flow", "flight"};
		System.out.println(leetCode.longestCommonPrefix(strings));
	}
	@Test
	public void verticalTraversal(){
		TreeNode treeNode = new TreeNode(3);
		TreeNode treeNode1 = new TreeNode(9);
		TreeNode treeNode2 = new TreeNode(20);
		TreeNode treeNode3 = new TreeNode(15);
		TreeNode treeNode4 = new TreeNode(7);
		treeNode.left = treeNode1;
		treeNode.right = treeNode2;
		treeNode2.left = treeNode3;
		treeNode2.right = treeNode4;
		System.out.println(leetCode.verticalTraversal(treeNode));
	}
	@Test
	public void removeElement(){
		int[] nums =new int[]{0,0,1,1,1,2,2,3,3,4};
		int i = leetCode.removeDuplicates(nums);
		int i1 = leetCode.removeElement(nums, 2);
		System.out.println(i+","+i1);
	}
	@Test
	public void aeiou(){
		System.out.println(leetCode.reverseVowels("Hello"));
	}
	@Test
	public void kWeakestRows(){
		int[][] array = {
				{1,1,1,1,1},
				{1,1,1,0,0},
				{1,1,1,1,0},
				{1,0,0,0,0},
				{0,0,0,0,0},
				{1,1,1,1,0},
		};
		System.out.println(Arrays.toString(leetCode.kWeakestRows(array, 3)));
	}
	@Test
	public void lengthOfLongestSubstring(){
		String s = "abcabcbb";
		System.out.println(leetCode.lengthOfLongestSubstring(s));
	}
	@Test
	public void catBunana(){
		int[] banana1 = {3,6,7,11};
		int[] banana2 = {30,11,23,4,20};
		int[] banana3 = {30,11,23,4,20};
		int[] banana4 = {312884470};
		int h1 = 8;
		int h2 = 5;
		int h3 = 6;
		int h4 = 312884469;
		int result = leetCode.minEatingSpeed(banana1, h1);
		int result2 = leetCode.minEatingSpeed(banana2, h2);
		int result3 = leetCode.minEatingSpeed(banana3, h3);
		int result4 = leetCode.minEatingSpeed(banana4, h4);
		System.out.println(result);
		System.out.println(result2);
		System.out.println(result3);
		System.out.println(result4);
	}
	@Test
	public void plusOne(){
		int[] one = {3,6,7,9};
		int[] ints = leetCode.plusOne(one);
		Arrays.stream(ints).boxed().forEach(System.out::println);
	}

}
