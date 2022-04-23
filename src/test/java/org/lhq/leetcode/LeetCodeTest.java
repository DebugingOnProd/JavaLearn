package org.lhq.leetcode;

import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Week;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.lhq.leetcode.LeetCode;
import org.lhq.leetcode.struc.ListNode;
import org.lhq.leetcode.struc.TreeNode;
import org.lhq.utils.HolidayEnum;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;


/**
 * @program: org.lhq.leetcode
 * @description: org.lhq.leetcode
 * @create: 2021-07-29 13:45
 */
@Slf4j
public class LeetCodeTest {

	LeetCode leetCode;

	@BeforeAll
	public static void before() {
		log.debug("-------开始执行测试用例--------");
	}

	@AfterAll
	public static void after() {
		log.debug("-------测试用例执行完成--------");
	}

	@BeforeEach
	public void beforeEach() {
		log.debug("--------------测试对象实例化--------------");
		leetCode = new LeetCode();
	}

	@AfterEach
	public void afterEach() {
		log.debug("--------------测试用例结束--------------");
	}

	/**
	 * 整数反转
	 */
	@Test
	@Timeout(1)
	public void reverse() {
		int x = 1534236469;
		log.info(String.valueOf((1 << 31) - 1));
		log.info(String.valueOf(Integer.MAX_VALUE / 10));
		log.info("------------------");
		log.info(String.valueOf(-1 << 31));
		log.info(String.valueOf(Integer.MIN_VALUE / 10));
		log.info(String.valueOf(x));
		log.info(String.valueOf(leetCode.reverse(x)));
	}

	/**
	 * 整数反转
	 */
	@Test
	@Timeout(1)
	public void isPalindrome() {
		log.info(String.valueOf(leetCode.isPalindrome(123)));
	}

	/**
	 * 罗马数字转阿拉伯数字
	 */
	@Test
	@Timeout(1)
	void romanToInt() {
		log.info(leetCode.romanToInt("IVI").toString());
	}

	@Test
	@Timeout(1)
	void longestCommonPrefix() {
		String[] strings = {"flower", "flow", "flight"};
		log.info(leetCode.longestCommonPrefix(strings));
	}

	@Test
	@Timeout(1)
	void verticalTraversal() {
		TreeNode treeNode = new TreeNode(3);
		TreeNode treeNode1 = new TreeNode(9);
		TreeNode treeNode2 = new TreeNode(20);
		TreeNode treeNode3 = new TreeNode(15);
		TreeNode treeNode4 = new TreeNode(7);
		treeNode.left = treeNode1;
		treeNode.right = treeNode2;
		treeNode2.left = treeNode3;
		treeNode2.right = treeNode4;
		log.info(leetCode.verticalTraversal(treeNode).toString());
	}

	@Test
	@Timeout(1)
	void removeElement() {
		int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
		int i = leetCode.removeDuplicates(nums);
		int i1 = leetCode.removeElement(nums, 2);
		log.info(i + "," + i1);
	}

	@Test
	@Timeout(1)
	void aeiou() {
		log.info(leetCode.reverseVowels("Hello"));
	}

	@Test
	public void kWeakestRows() {
		int[][] array = {
				{1, 1, 1, 1, 1},
				{1, 1, 1, 0, 0},
				{1, 1, 1, 1, 0},
				{1, 0, 0, 0, 0},
				{0, 0, 0, 0, 0},
				{1, 1, 1, 1, 0},
		};
		log.info(Arrays.toString(leetCode.kWeakestRows2(array, 3)));
	}

	@Test
	public void lengthOfLongestSubstring() {
		String s = "abcabcbb";
		log.info(String.valueOf(leetCode.lengthOfLongestSubstring(s)));
	}

	@Test
	public void catBunana() {
		int[] banana1 = {3, 6, 7, 11};
		int[] banana2 = {30, 11, 23, 4, 20};
		int[] banana3 = {30, 11, 23, 4, 20};
		int[] banana4 = {312884470};
		int h1 = 8;
		int h2 = 5;
		int h3 = 6;
		int h4 = 312884469;
		int result = leetCode.minEatingSpeed(banana1, h1);
		int result2 = leetCode.minEatingSpeed(banana2, h2);
		int result3 = leetCode.minEatingSpeed(banana3, h3);
		int result4 = leetCode.minEatingSpeed(banana4, h4);
		log.info(String.valueOf(result));
		log.info(String.valueOf(result2));
		log.info(String.valueOf(result3));
		log.info(String.valueOf(result4));
	}

	@Test
	void plusOne() {
		int[] one = {3, 6, 7, 9};
		int[] ints = leetCode.plusOne(one);
		Arrays.stream(ints).boxed().forEach(integer -> log.info(integer.toString()));
	}

	@Test
	void dayCal() {
		LocalDate time = LocalDate.of(2021, 5, 26);
		LocalDate goHome = LocalDate.of(2023, 1, 22);
		Instant instant = time.atStartOfDay(ZoneId.systemDefault()).toInstant();
		Date leaveDate = Date.from(instant);
		Instant comeBack = goHome.atStartOfDay(ZoneId.systemDefault()).toInstant();
		Date comeBackDay = Date.from(comeBack);
		long betweenLeave = DateUtil.between(leaveDate, new Date(), DateUnit.DAY);
		long goHomeDay = DateUtil.between(new Date(), comeBackDay, DateUnit.DAY);
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int month = now.getMonth().getValue();
		int dayOfMonth = now.getDayOfMonth();
		log.trace("今天是{}年{}月{}日,我离开南宁的{}天,下一个春节还有{}天", year, month, dayOfMonth, betweenLeave, goHomeDay);
		log.debug("今天是{}年{}月{}日,我离开南宁的{}天,下一个春节还有{}天", year, month, dayOfMonth, betweenLeave, goHomeDay);
		log.info("今天是{}年{}月{}日,我离开南宁的{}天,下一个春节还有{}天", year, month, dayOfMonth, betweenLeave, goHomeDay);
		log.warn("今天是{}年{}月{}日,我离开南宁的{}天,下一个春节还有{}天", year, month, dayOfMonth, betweenLeave, goHomeDay);
		log.error("今天是{}年{}月{}日,我离开南宁的{}天,下一个春节还有{}天", year, month, dayOfMonth, betweenLeave, goHomeDay);

	}


	private Date getDayOfHoliday(int year, HolidayEnum holidayEnum) {
		if (holidayEnum.isLunar()) {
			ChineseDate chineseDate = new ChineseDate(year, holidayEnum.getHolidayMonth(), holidayEnum.getHolidayDay());
			return chineseDate.getGregorianDate();
		} else {
			LocalDate newYear = LocalDate.of(year, holidayEnum.getHolidayMonth(), holidayEnum.getHolidayDay());
			Instant newInstant = newYear.atStartOfDay(ZoneId.systemDefault()).toInstant();
			return Date.from(newInstant);
		}
	}

	@Test
	@DisplayName("摸鱼人提示")
	void countdown() {
		HashMap<String, BiFunction<Integer, String, Date>> functionHashMap = new HashMap<>();
		HashMap<String, HolidayEnum> holidayEnumMap = new HashMap<>();
		Arrays.stream(HolidayEnum.values()).forEach(holidayEnum -> holidayEnumMap.put(holidayEnum.getHolidayName(), holidayEnum));
		Arrays.stream(HolidayEnum.values()).forEach(holidayEnum -> functionHashMap.put(holidayEnum.getHolidayName(), (year, holiday) -> this.getDayOfHoliday(year, holidayEnumMap.get(holidayEnum.getHolidayName()))));
		LinkedHashMap<String, Long> festivalMap = Maps.newLinkedHashMap();
		DateTime now = DateTime.now();
		int year = now.year();
		int month = now.month() + 1;
		int day = now.dayOfMonth();
		Date date = now.toJdkDate();
		Arrays.stream(HolidayEnum.values()).forEach(holidayEnum -> {
			Date apply = functionHashMap.get(holidayEnum.getHolidayName()).apply(year, holidayEnum.getHolidayName());
			long between = DateUtil.between(date, apply, DateUnit.DAY, false);
			festivalMap.put(holidayEnum.getHolidayName(), between);
		});
		ArrayList<Map.Entry<String, Long>> entries = Lists.newArrayList(festivalMap.entrySet());
		entries.stream()
				.filter(item -> item.getValue() < 0)
				.forEach(item -> {
					String key = item.getKey();
					Date newDate = functionHashMap.get(key).apply(year + 1, key);
					long between = DateUtil.between(date, newDate, DateUnit.DAY, false);
					item.setValue(between);
				});
		entries.sort((o1, o2) -> Math.toIntExact(o1.getValue() - o2.getValue()));
		if (DateUtil.isAM(date)) {
			log.info("{}月{}日,{},早上好摸鱼人！", month, day,DateUtil.dayOfWeekEnum(date).toChinese());
		} else if (DateUtil.isPM(date)) {
			log.info("{}月{}日,{},下午好摸鱼人！", month, day,DateUtil.dayOfWeekEnum(date).toChinese());
		}
		log.info("工作再累，一定不要忘记摸鱼哦！");
		log.info("有事没事起身去茶水间去厕所去廊道走走，别老在工位上坐着，钱是老板的，但命是自己的 ！");
		entries.forEach(item -> log.info("距离[{}]还有:{}天", item.getKey(), item.getValue()));
		log.info("努力工作是帮老板赚钱，摸鱼是赚老板的钱！");
		log.info("最后，祝愿天下所有摸鱼人，都能愉快的渡过每一天…");
	}

	@Test
	void merge(){
		int [] nums1 = {1,2,3,0,0,0};
		int [] nums3 = {1,2,3};
		leetCode.merge(nums1,3,nums3,3);
	}


	@Test
	void deleteDuplicates() {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(1);
		ListNode node3 = new ListNode(2);
		ListNode node4 = new ListNode(3);
		ListNode node5 = new ListNode(3);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		//leetCode.deleteDuplicates(node1);
		log.info("{}", node1);
	}


	@Test
	void containsDuplicate() {
		boolean result = leetCode.containsDuplicate(new int[]{1, 2, 3, 4});
		log.info("{}", result);
		final boolean duplicate = leetCode.containsNearbyDuplicate(new int[]{1, 0, 1, 1}, 1);
		log.info("{}",duplicate);
		final int i = leetCode.missingNumber(new int[]{3, 0, 1});
		log.info("{}",i);
		final boolean b = leetCode.wordPattern("abba",
				"dog cat cat dog");
		log.info("{}",b);
	}

	@Test
	void majorityElement() {
		int i = leetCode.majorityElement(new int[]{3, 2, 3});
		log.info("{}",i);
		boolean happy = leetCode.isHappy(19);
		log.info("happy?{}",happy);
		boolean isomorphic = leetCode.isIsomorphic("badc", "baba");
		//boolean isomorphic = leetCode.isIsomorphic("add", "egg");
		log.info("isomorphic:{}",isomorphic);

	}
	@Test
	@DisplayName("完全平方数")
	void isPerfectSquare(){
	//boolean perfectSquare = leetCode.isPerfectSquare(2147483647);
		//int i = leetCode.mySqrt(4);
		//int[] ints = leetCode.searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8);
		int martix[][] = new int[][] {{1,3,5,7},{10,11,16,20},{23,30,34,60}};
		boolean b = leetCode.searchMatrix(martix, 3);
		log.info("是否完全平方数{}",b);
		log.info("xxxxx:{}",b);
}


}
