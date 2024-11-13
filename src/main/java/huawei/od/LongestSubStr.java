package huawei.od;

import java.util.*;

/**
 * 3192.2024D-求满足条件的最长子串的长度
 * 中等	华为OD	不定滑窗
 * 题目描述
 * 给定一个字符串，只包含字母和数字，按要求找出字符串中的最长(连续)子串的长度，字符串本身是其最长的子串，子串要求:
 * 1.只包含1个字母(a~z,A~Z)，其余必须是数字
 * 2.字母可以在子串中的任意位置;
 * 如果找不到满足要求的子串，如全是字母或全是数字，则返回-1。
 *
 * 输入描述
 * 字符串(只包含字母和数字)
 * 输出描述
 * 最长子串的长度
 */
public class LongestSubStr {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LongestSubStr longestSubStr = new LongestSubStr(scanner.nextLine());
        System.out.println(longestSubStr.getLongestSubStr());
    }

    private String str;
    private int ans;

    LongestSubStr(String str) {
        this.str = str;
        this.ans = -1;
    }

    public int getLongestSubStr() {
        int left = 0;
        int alphaNum = 0, digitNum = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isAlphabetic(c)) {
                alphaNum++;
            }else{
                digitNum++;
            }
            while (alphaNum > 1) {
                if (Character.isAlphabetic(str.charAt(left))) {
                    alphaNum--;
                }else{
                    digitNum--;
                }
                left++;
            }
            if (alphaNum == 1) {
                this.ans = Math.max(this.ans, digitNum + 1);
            }
        }
        return this.ans;
    }
}
