package 正则;

/**
 * @author: liyangjin
 * @create: 2023-04-28 16:25
 * @Description:
 */
public class LC125 {

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(s));
    }
    public static boolean isPalindrome(String s) {
        //移除非字母数字字符
        s = s.replaceAll("[^A-Za-z0-9]", "").toUpperCase();
        int n = s.length();
        for (int i = 0; i < n / 2; i++) {
            if (s.charAt(i) != s.charAt(n - 1 - i)) {
                return false;
            }
        }
        return true;
    }

}
