package dp.区间;

/**
 * @author: liyangjin
 * @create: 2023-04-05 15:24
 * @Description:
 */
public class LC1000 {

    /**
     * dp[i][j]表示[i,j]堆石头合并成1堆的最小成本。
     * 假设m=2
     * dp[i][j] = min(dp[i][k] + dp[k+1][j])
     * 最终结果dp[1][n]。 写法上两层循环遍历i和j，i必须从大到小，j必须从小到大，最后i=1，j=n。k则是[i, j]之间
     * <p>
     * 当m>2时，dp[i][j][t]表示[i,j]合并成t堆的最小成本。 t<=j-i+1
     * dp[i][i][1]表示初始堆成本0。
     * dp[i][j][1] = min(dp[i][k][t] + dp[k+1][j][m-t])
     * 如果左边是1堆，则右边是m-1堆。
     * 如果左边是2堆，则右边是m-2堆。
     * 如果左边是3堆，则右边是m-3堆。
     * 每一种t堆，不论是2、3、4，都是从1堆开始计算的。
     */
    public int mergeStones(int[] stones, int m) {
        // 不用Integer.MAX_VALUE,因为Integer.MAX_VALUE + 正数 会溢出变为负数
        int MAX_VALUE = 99999999;
        int n = stones.length;
        //贪吃蛇，n里拿1堆当作头，每次吃下m-1堆。
        if ((n - 1) % (m - 1) != 0) {
            return -1;
        }
        //初始化前缀和数组和dp数组
        int[] prefix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + stones[i - 1];
        }
        int[][][] dp = new int[n + 1][n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 2; k <= m; k++) {
                    dp[i][j][k] = MAX_VALUE;
                }
            }
            dp[i][i][1] = 0;
        }

        // 枚举区间长度len, 通过i确定区间终点j。 和暴力枚举i、j没有区别
        for (int len = 2; len <= n; len++) {
            for (int i = 1; i + len - 1 <= n; i++) {
                int j = i + len - 1;
                // 堆数，计算出所有的dp[i][j][t]，主要是dp[i][j][m]，然后按题目要求m堆合并成1堆。
                for (int t = 2; t <= m; t++) {
                    for (int k = i; k < j; k++) {
                        dp[i][j][t] = Math.min(dp[i][j][t], dp[i][k][1] + dp[k + 1][j][t - 1]);
                    }
                }
                //m堆合并成1堆
                dp[i][j][1] = dp[i][j][m] + prefix[j] - prefix[i - 1];
            }
        }
        return dp[1][n][1];
    }

}
