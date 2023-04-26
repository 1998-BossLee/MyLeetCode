package dp;

/**
 * @author: liyangjin
 * @create: 2023-04-19 17:13
 * @Description:
 */
public class LC1043 {

    /**
     * 数组切割，每个子数组长度不超过k，切割后把值替换成子数组最大值，求最大总和
     * [1,9,10] k=2
     * 贪心不适用：先找10，把9变成10。则1没人管了。
     * [1] + [9,10]
     * [9,9] + [10]
     * <p>
     * dp[i]表示[0,i]数组里的数按照k分割后的最大总和
     * dp[2] = max(dp[0]+max[1,2]*2, dp[1]+max[2,2]*1 )
     * dp[i] = max (dp[i-j]+max[i-j+1, i]*j)
     * 注意判断i-j<0
     */
    public int maxSumAfterPartitioning(int[] a, int k) {
        int n = a.length;
        int[] dp = new int[n];
        dp[0] = a[0];
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                if (i - j + 1 < 0) {
                    continue;
                }
                dp[i] = Math.max(dp[i], (i - j < 0 ? 0 : dp[i - j]) + getMaxValue(a, i - j + 1, i) * j);
            }
        }
        return dp[n - 1];
    }

    private int getMaxValue(int[] a, int start, int end) {
        int max = a[start];
        for (int i = start + 1; i <= end; i++) {
            max = Math.max(max, a[i]);
        }
        return max;
    }

}
