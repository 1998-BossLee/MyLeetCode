package 双指针;

/**
 * @author: liyangjin
 * @create: 2023-04-12 14:01
 * @Description:
 */
public class LC1147 {

    /**
     * 尽可能切分，从两端切，枚举切割长度。
     * [left1, right1] ..... [left2, right2]
     * right1-left1 = right2-left2
     * 可以通过直接比较字符串判断是否可切割，单次比较时间复杂度是O(n)
     * 优化：可以通过求模等形式进行hash，比较hash值，单次比较时间复杂度O(1)
     */
    public int longestDecomposition(String s) {

        int ans = 0;
        while (!s.isEmpty()) {
            int i = 1, n = s.length();
            while (i <= n / 2 && !s.substring(0, i).equals(s.substring(n - i))) {
                i++;
            }
            if (i > n / 2) {
                ans++;
                break;
            }
            ans += 2;
            s = s.substring(i, n - i);
        }
        return ans;
    }

}
