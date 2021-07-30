import org.junit.jupiter.api.*;

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
	@Test
	public void isPalindrome(){
		System.out.println(leetCode.isPalindrome(123));
	}

}
