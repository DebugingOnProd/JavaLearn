/**
 * @program: leetcode
 * @description:
 * @author: LittleCuteWangDF
 * @create: 2021-07-30 14:01
 */
public class LeetCode {
	/**
	 * 回文数
	 * @param x
	 * @return
	 */
	public boolean isPalindrome(int x) {
		return true;
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
