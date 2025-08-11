package 前缀和;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: liyangjin
 * @create: 2025-08-11 11:10
 * @Description:
 */
public class LC2438 {

    /**
     * 1、分解n
     * n是2的幂次方的乘积，2^a * 2^b * 2^c * 2^d * ...
     * 用二进制肯定表示为 01000100010011111 这种形式
     * 先把n的power数组构造出来，长度不会超过30
     * 2、预处理查询结果
     */
    private static final int MOD = 1000000007;

    public int[] productQueries(int n, int[][] queries) {
        List<Integer> powers = new ArrayList<>();
        //power存储的是具体值
        int pow = 1;
        while (n > 0) {
            if (n % 2 == 1) {
                powers.add(pow);
            }
            n /= 2;
            pow = pow * 2;
        }
        //n<1e9，power数组长度不会超过30，30*30=900，int可以存储
        int m = powers.size();
        int[][] results = new int[m][m];
        for (int i = 0; i < m; i++) {
            long now = 1;
            for (int j = i; j < m; j++) {
                now = (now * powers.get(j)) % MOD;
                results[i][j] = (int) now;
            }
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0];
            int right = queries[i][1];
            ans[i] = results[left][right];
        }
        return ans;
    }

    //快速幂
    private static long quickPow(long a, long b, long mod) {
        long res = 1;
        while (b > 0) {
            if (b % 2 == 1) {
                res = (res * a) % mod;
                b--;
            } else {
                a = (a * a) % mod;
                b /= 2;
            }
        }
        return res;
    }

}
