package leetcode;

import leetcode.struc.TreeNode;

import java.util.*;

/**
 * @program: leetcode
 * @description:
 * @author: LittleCuteWangDF
 * @create: 2021-07-30 14:01
 */
public class LeetCode {
	/**
	 * 66. 加一
	 * @param digits
	 * @return
	 */
	public int[] plusOne(int[] digits) {

		int length = digits.length;
		for (int i = length - 1; i >= 0; i--) {
			if (digits[i]!=9){
				++digits[i];
				for (int j = i+1; j <length; j++) {
					digits[j] = 0;
				}
				return digits;
			}
		}
		int[] ans = new int[length + 1];
		ans[0] = 1;
		return ans;
	}


	public int lengthOfLongestSubstring(String s) {

		// 用来存放字符与他的
		HashMap<Character, Integer> windows = new HashMap<>();
		int max = 0, left = 0;
		for (int right = 0;right<s.length();){
			// 获取右边指针的字符
			char c = s.charAt(right);
			// 指针右移
			right++;
			Integer integer = windows.get(c);
			integer = integer == null ? 0 : integer;
			windows.put(c,++integer);
			// 如果该字符的值大于1则说明这个字符出超过一次
			while (windows.get(c)>1){
				//获取右边左边边界的字符
				char c1 = s.charAt(left);
				//左边边界++
				left++;
				Integer integer1 = windows.get(c1);
				integer1 = integer1 == null ? 0 :integer1;
				//使得该字符
				windows.put(c1,--integer1);
			}
			max = Math.max(max,right-left);
		}
		return max;
	}






	public boolean carPooling(int[][] trips, int capacity) {
		//创建一个以下车顺序的小根堆
		PriorityQueue<int[]> heap=new PriorityQueue<>(Comparator.comparingInt(o -> o[2]));
		//对上车顺序排序
		Arrays.sort(trips, Comparator.comparingInt(o -> o[1]));
		//便利行程表
		for (int[] trip : trips) {
			//先上车 减少车的空位
			capacity -= trip[0];
			//当座位为负值的时候就要踢人下车
			if (capacity < 0) {
				// 如果下车堆不为空, 并且下车的站小于等于当前要上车的
				while (!heap.isEmpty() && heap.peek()[2] <= trip[1]) {
					// 车的容量就加上当前要下车的人数,然后在堆里移除这个行程
					capacity += heap.poll()[0];
				}
				//能下的都下完,如果这个时候位置还是小于0,就说明行程表不满足
				if (capacity < 0) {
					return false;
					//能下完的 下完还不行就返回
				}
			}
			//吧这个行程表加到堆里,并按照下车顺序排序
			heap.offer(trip);
		}
		return true;
	}
	/**
	 * 爱吃香蕉
	 * @param piles
	 * @param h
	 * @return
	 */
	public int minEatingSpeed(int[] piles, int h) {
		// 从小到大排序
		//List<Integer> pile = Arrays.stream(piles).sorted().boxed().collect(Collectors.toList());
		Arrays.sort(piles);
		// 获取最大值
		Integer right = piles[piles.length-1];
		int left  = 1;
		while (left<right){
			int mid = left + (right-left)/2;
			if (!canEat(piles,h,mid)){
				left = mid+1;
			}else {
				right = mid;
			}
		}

		return left;
	}
	private boolean canEat(int[] pile,int h,int k){
		int sumTime = 0;
		for (Integer p : pile) {
			double i2 = p;
			double i3 = k;
			double i1 = Math.ceil(i2/i3);
			sumTime+=i1;
		}
		return sumTime <= h;
	}
	/**
	 * 二分查找
	 * @return
	 */
	public int search(int[] nums, int target) {
		 int right = nums.length-1;
		 int left = 0;
		 while (left<=right){
		 	// 获取偏移量
			 int offset = (right-left)/2;
			 // 中间下标等于左边位置加上偏移量;
		 	int mid = left+offset;
		 	if (nums[mid] == target){
		 		return mid;
		 		// 如果中间的下标的值大于目标的值,
				// 就说明该值在左边,就把右边的边界移动到现在mid值-1的位置
			}else if (nums[mid]>target){
		 		right = mid-1;
		 		//如果中间下标的值小于目标的值,
				// 就说明目标值在,中间值的右边,就把左边边界移动到现在中间值+1的位置
			}else if (nums[mid]<target){
		 		left = mid+1;
			}
		 }
		return -1;
	}

	/**
	 * 前缀和
	 * @param nums
	 * @param k
	 * @return
	 */
	public int subarraySum(int[] nums, int k) {
		int sum = 0,count=0;
		// 一次循环
		for (int i = 0; i < nums.length; i++) {
			// 二次循环  如果j下标等于 [3]
			for (int j = i; j >=0 ; j--) {
				// 那这里就是 [3]+[2]+[1]
				sum+=nums[j];
				if (sum == k){
					count++;
				}
			}
		}
		return count;
	}
	/**
	 *反转字符串中的元音字母
	 * @param s
	 * @return
	 */
	public String reverseVowels(String s) {
		int left = 0;
		int right = s.length() - 1;

		char[] ss = s.toCharArray();
		while (left < right) {
			// 如果左边小于右边并且左边是元音
			while(left < right && isVowel(ss[left])) {
				left++;
			}
			// 如果左边小于右边并且右边边是元音
			while(left < right && isVowel(ss[right])) {
				right--;
			}
			//左右互换
			if(left < right) {
				char temp = ss[left];
				ss[left] = ss[right];
				ss[right] = temp;
				//游标移动
				left ++;
				right --;
			}


		}
		return new String(ss);
	}
	public boolean isVowel(char ch) {
		return "aeiouAEIOU".indexOf(ch) < 0;
	}


	/**
	 * 去除重复元素
	 * @param nums
	 * @param val
	 * @return
	 */


	public int removeElement(int[] nums, int val) {
		if (nums==null||nums.length==0){
			return 0;
		}
		int m = 0;
		for (int i = 0; i < nums.length; i++) {

			if (nums[i]!=val){
				nums[m]=nums[i];
				m++;
			}
		}
		return m;
	}

	/**
	 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
	 *
	 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
	 *
	 * @param nums
	 * @return
	 */
	public int removeDuplicates(int[] nums) {
		if (nums.length<2){
			return nums.length;
		}
		int m = 0;
		//从1开始遍历
		for (int i = 1; i < nums.length; i++) {
			/**
			 * 如果该数前一个数小于该数则表明,不重复，
			 * 如果m>=i则 m下标的元素是等于i下标的元素的,则开启下一个循环,此时i+1,m没有改变
			 * 直到找到一个m下标的值小于i下标的值(也就是下一个不重复的值),就把m+1下标的元素赋予这个元素
			 */
			if (nums[m]<nums[i]){
				nums[++m]=nums[i];
			}
		}
		return ++m;
	}
	/**
	 * 网咯延时
	 * @param times
	 * @param n
	 * @param k
	 * @return
	 */
	public int networkDelayTime(int[][] times, int n, int k) {
		return 0;
	}

	/**
	 * 有效括号
	 * @param s
	 * @return
	 */
	public boolean isValid(String s) {
		int n = s.length();
		// 如果为单数直接返回
		if (n % 2 == 1) {
			return false;
		}

		Map<Character, Character> pairs = new HashMap<Character, Character>() {{
			put(')', '(');
			put(']', '[');
			put('}', '{');
		}};
		// 新建一个栈
		Deque<Character> stack = new LinkedList<Character>();
		for (int i = 0; i < n; i++) {
			char ch = s.charAt(i);
			if (pairs.containsKey(ch)) {
				if (stack.isEmpty() || !stack.peek().equals(pairs.get(ch))) {
					return false;
				}
				stack.pop();
			} else {
				stack.push(ch);
			}
		}
		return stack.isEmpty();
	}
	/**
	 * 矩阵中战斗力最弱的k行
	 * @param mat
	 * @param k
	 * @return
	 */
	public int[] kWeakestRows(int[][] mat, int k) {
		// 定义数组记录士兵的个数和行数
		int[][] sort = new int[mat.length][2];
		// 双层循环遍历数组的值
		for(int i=0;i<mat.length;i++){
			// count 记录每一行1的个数
			int count=0;
			for(int j=0;j<mat[i].length;j++){
				if(mat[i][j]==1){
					count++;
				} else {
					break;
				}
			}
			// 把记录的士兵的个数和行数放入新的数组
			sort[i] = new int[]{count,i};
		}
		//重写排序接口对sort进行排序
		Arrays.sort(sort, (e1, e2) -> {
			// 如果士兵个数不相等
			if(e1[0]!=e2[0]) {
				// 按照士兵个数进行升序排序
				return e1[0]-e2[0];
			}
			else {
				// 按照行数进行升序排序
				return e1[1]-e2[1];
			}
		});
		int[] ans = new int[k];
		// 把前面k个放入数组
		for(int i=0;i<k;i++){
			ans[i]=sort[i][1];
		}
		return ans;

	}
	/**
	 * 每日一题之
	 * 二叉树的垂序遍历
	 * @param root
	 * @return
	 */
	public List<List<Integer>> verticalTraversal(TreeNode root) {
		//nodes得到深度优先遍历之后的结果
		List<int[]> nodes = new ArrayList<>();
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
			int col = tuple[0], value = tuple[2];
			if (col != lastcol) {
				lastcol = col;
				ans.add(new ArrayList<>());
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
