package 数学;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: liyangjin
 * @create: 2023-05-11 16:54
 * @Description:
 */
public class LC1016 {

    /**
     * n的二进制位数x=log n，撑死32位
     * s的每个下标i开始往前推x的滑动窗口
     */
    public boolean queryString(String s, int n) {
        char[] a = s.toCharArray();
        Set<Integer> set = new HashSet<>();
        int m = s.length();
        for (int i = 0; i < m; i++) {
            if (a[i] == '0') {
                continue;
            }
            int sum = 0;
            for (int j = i; j < m; j++) {
                sum = (sum << 1) | (a[j] - '0');
                // sum = sum * 2 + a[j] - '0';
                if (sum > n) {
                    break;
                }
                set.add(sum);
            }
        }
        return set.size() == n;
    }

}
