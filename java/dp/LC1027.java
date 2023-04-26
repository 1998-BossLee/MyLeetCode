package dp;

/**
 * @author: liyangjin
 * @create: 2023-04-23 17:11
 * @Description: 最长等差子序列
 */
public class LC1027 {

    /**
     * dp[i][j]表示以a[i]结尾的，公差为j的最长等差子序列
     * 从前面找公差为j的最长子序列续上，dp[k][j] + 1。 0<=k<i
     * 公差可能是负数，最大公差为500，通过+500转为正数
     */
    public int longestArithSeqLength(int[] a) {
        int n = a.length, ans = 1;
        int[][] dp = new int[n][1001];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < i; k++) {
                int j = a[i] - a[k] + 500;
                //每个数自成一个等差数列，应当初始化为1
                dp[i][j] = Math.max(dp[i][j], (dp[k][j] == 0 ? 1 : dp[k][j]) + 1);
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }

}
