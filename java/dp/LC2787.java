package dp;

/**
 * @author: liyangjin
 * @create: 2025-08-13 10:50
 * @Description:
 */
public class LC2787 {

    /**
     * 给定一个正整数 n 和一个整数 x，返回可以表示为 1^x + 2^x + ... + k^x 的不同 k 的数量，使得结果等于 n。
     * 01背包问题，每个数只能用一次。
     * dp[i][j]表示前i个数组成j的方案数
     * 为什么把前i个数定义为第一维，它叫【阶段推进】，是时间顺序或处理顺序上的推进，每个状态都是固定的，前i就是前i
     * 为什么把j定义成第二维，它叫【阶段内的限制条件】，条件不同会导致走向不同，前i个数可能组成j，也可能组成j+1，j+2。
     * 对于第i个数，值为pow
     * 不选 dp[i][j] = dp[i-1][j]
     * 选 dp[i][j] = dp[i-1][j - pow]
     * 状态转移方程 dp[i][j] = dp[i-1][j] + dp[i-1][j - pow]
     * 由于dp[i]只和dp[i-1]有关，可以把二维数组压缩成一维数组
     * 对于第二维度j应该如何遍历？dp[j]依赖dp[j-pow]，dp[j-pow]必须是上一轮的值。大下标依赖小下标。
     * 假设j从小到大，dp[j-pow]在本轮已经被更新过，破坏了【只用上一轮的数据】的原则。
     * 所以j必须从大到小遍历。
     */
    private static final int MOD = 1000000007;

    public int numberOfWays(int n, int x) {
        long[] dp = new long[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            int pow = (int) Math.pow(i, x);
            if (pow > n) {
                break;
            }
            for (int j = n; j >= pow; j--) {
                dp[j] = (dp[j] + dp[j - pow]) % MOD;
            }
        }
        return (int)dp[n];
    }

}
