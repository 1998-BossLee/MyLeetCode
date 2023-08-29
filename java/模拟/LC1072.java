package 模拟;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: liyangjin
 * @create: 2023-05-15 09:45
 * @Description:
 */
public class LC1072 {

    /**
     * 选择任意多个列，对列上所有元素都可以进行01翻转。
     * 求最后行内元素都相等的行最多有几行，行内元素相等例如000和111这样的行。
     *
     * 0110和1001每位都不同的行可以当作等价行，可以通过翻转得到。
     * 0110
     * 1001
     * 翻转第2、3行，即可得到：
     * 0000
     * 1111
     * 同样，0110和0110一模一样的行也是等价行。
     */
    public int maxEqualRowsAfterFlips(int[][] matrix) {
        Map<String, Integer> cnt = new HashMap<>();
        int ans = 0, n = matrix.length, m = matrix[0].length;
        for (int i = 0; i < n; i++) {
            char[] s = new char[m];
            for (int j = 0; j < m; j++) {
                //对首位是0的全部取反
                if (s[0] == 0) {
                    s[j] = matrix[i][j] == 0 ? '1' : '0';
                }
                s[j] = (char) (matrix[i][j] + '0');
            }
            String str = new String(s);
            cnt.put(str, cnt.getOrDefault(str, 0) + 1);
            ans = Math.max(ans, cnt.get(str));
        }
        return ans;
    }

}
