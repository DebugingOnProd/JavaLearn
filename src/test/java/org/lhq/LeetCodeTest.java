package org.lhq;

import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lhq.leetcode.LeetCode;
import org.lhq.leetcode.struc.TreeNode;
import org.lhq.utils.HolidayEnum;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;


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
	public void isPalindrome() {
		log.info(String.valueOf(leetCode.isPalindrome(123)));
	}

	/**
	 * 罗马数字转阿拉伯数字
	 */
	@Test
	public void romanToInt() {
		log.info(leetCode.romanToInt("IVI").toString());
	}

	@Test
	public void longestCommonPrefix() {
		String[] strings = {"flower", "flow", "flight"};
		log.info(leetCode.longestCommonPrefix(strings));
	}

	@Test
	public void verticalTraversal() {
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
	public void removeElement() {
		int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
		int i = leetCode.removeDuplicates(nums);
		int i1 = leetCode.removeElement(nums, 2);
		log.info(i + "," + i1);
	}

	@Test
	public void aeiou() {
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
		log.info(Arrays.toString(leetCode.kWeakestRows(array, 3)));
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
		LocalDate goHome = LocalDate.of(2022, 1, 29);
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
		log.trace("今天是{}年{}月{}日,我离开南宁的{}天,距离回家还有{}天", year, month, dayOfMonth, betweenLeave, goHomeDay);
		log.debug("今天是{}年{}月{}日,我离开南宁的{}天,距离回家还有{}天", year, month, dayOfMonth, betweenLeave, goHomeDay);
		log.info("今天是{}年{}月{}日,我离开南宁的{}天,距离回家还有{}天", year, month, dayOfMonth, betweenLeave, goHomeDay);
		log.warn("今天是{}年{}月{}日,我离开南宁的{}天,距离回家还有{}天", year, month, dayOfMonth, betweenLeave, goHomeDay);
		log.error("今天是{}年{}月{}日,我离开南宁的{}天,距离回家还有{}天", year, month, dayOfMonth, betweenLeave, goHomeDay);

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
	void countdown() {
		String[] holidays = {"元旦", "春节", "清明", "五一", "端午", "中秋", "国庆"};
		DateTime now = DateTime.now();
		int year = now.year();
		int month = now.month()+1;
		int day = now.dayOfMonth();
		Date date = now.toJdkDate();
		LinkedHashMap<String, Long> festivalMap = Maps.newLinkedHashMap();
		Arrays.stream(holidays).forEach(holiday->{
			switch (holiday){
				case "元旦":
					Date newYear = getDayOfHoliday(year, HolidayEnum.NewYear);
					long betweenNewYear = DateUtil.between(date, newYear, DateUnit.DAY,false);
					festivalMap.put(holiday, betweenNewYear);
					break;
				case "春节":
					Date chineseNewYear = getDayOfHoliday(year, HolidayEnum.ChineseNewYear);
					long betweenChineseNewYear = DateUtil.between(chineseNewYear, date, DateUnit.DAY);
					festivalMap.put(holiday, betweenChineseNewYear);
					break;
				case "清明":
					log.debug("清明节不确定日期");
					break;
				case "五一":
					Date labor = getDayOfHoliday(year,HolidayEnum.InternationalLaborDay);
					long betweenDay = DateUtil.between(date, labor, DateUnit.DAY,false);
					festivalMap.put(holiday,betweenDay);
					break;
				case "端午":
					Date dragonBoat = getDayOfHoliday(year, HolidayEnum.DragonBoatFestival);
					long dragonBoatBetweenDay = DateUtil.between(date, dragonBoat, DateUnit.DAY,false);
					festivalMap.put(holiday,dragonBoatBetweenDay);
					break;
				case "中秋":
					Date midAutumn = getDayOfHoliday(year, HolidayEnum.MidAutumnFestival);
					long midAutumnBetweenDay = DateUtil.between(date, midAutumn, DateUnit.DAY,false);
					festivalMap.put(holiday,midAutumnBetweenDay);
					break;
				case "国庆":
					Date nationalDay = getDayOfHoliday(year, HolidayEnum.NationalDay);
					long nationalDayBetweenDay = DateUtil.between(date, nationalDay, DateUnit.DAY,false);
					festivalMap.put(holiday,nationalDayBetweenDay);
					break;
				default:break;
			}
		});
		ArrayList<Map.Entry<String, Long>> entries = Lists.newArrayList(festivalMap.entrySet());
		entries.stream()
				.filter(item -> item.getValue() < 0)
				.forEach(item -> {
					String key = item.getKey();
					long betweenDay;
					switch (key){
						case "元旦":
							betweenDay = DateUtil.betweenDay(date, getDayOfHoliday(year + 1, HolidayEnum.NewYear), false);
							item.setValue(betweenDay);
							break;
						case "春节":
							betweenDay = DateUtil.betweenDay(date, getDayOfHoliday(year + 1, HolidayEnum.ChineseNewYear), false);
							item.setValue(betweenDay);
							break;
						case "清明":
							log.debug("清明节是哪一天啊");
						case "五一":
							betweenDay = DateUtil.betweenDay(date, getDayOfHoliday(year + 1, HolidayEnum.InternationalLaborDay), false);
							item.setValue(betweenDay);
							break;
						case "端午":
							betweenDay = DateUtil.betweenDay(date, getDayOfHoliday(year + 1, HolidayEnum.DragonBoatFestival), false);
							item.setValue(betweenDay);
							break;
						case "中秋":
							betweenDay = DateUtil.betweenDay(date, getDayOfHoliday(year + 1, HolidayEnum.MidAutumnFestival), false);
							item.setValue(betweenDay);
							break;
						case "国庆":
							betweenDay = DateUtil.betweenDay(date, getDayOfHoliday(year + 1, HolidayEnum.NationalDay), false);
							item.setValue(betweenDay);
							break;
					}


				});
		entries.sort((o1, o2) -> Math.toIntExact(o1.getValue() - o2.getValue()));
		if (DateUtil.isAM(date)) {
			log.info("{}月{}日,早上好摸鱼人！",month,day);
		} else if (DateUtil.isPM(date)) {
			log.info("{}月{}日,下午好摸鱼人！",month,day);
		}
		log.info("工作再累，一定不要忘记摸鱼哦！");
		log.info("有事没事起身去茶水间去厕所去廊道走走，别老在工位上坐着，钱是老板的，但命是自己的 ！");
		entries.forEach(item -> log.info("距离[{}]还有:{}天", item.getKey(), item.getValue()));
		log.info("努力工作是帮老板赚钱，摸鱼是赚老板的钱！");
		log.info("最后，祝愿天下所有摸鱼人，都能愉快的渡过每一天…");
	}


}
