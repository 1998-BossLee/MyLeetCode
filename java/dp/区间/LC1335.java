package dp.区间;

import java.util.Arrays;

/**
 * @author: liyangjin
 * @create: 2023-05-16 16:26
 * @Description:
 */
public class LC1335 {

    /**
     * dp[i][k] 前i份工作以j天分配的最小总难度
     * 当k=1，则取[0,i]的最大值
     * 工作区间划分dp[j-1][k-1]，前j-1天分配k-1天工作
     * [0,j-1] ... [j,i]
     * dp[i][k] = min(dp[j-1][k-1] + max(job[j,i]) )
     */
    public int minDifficulty(int[] job, int d) {
        int n = job.length;
        if (n < d) {
            return -1;
        }
        int[][] dp = new int[n + 1][d + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int k = 1; k <= d; k++) {
            int max = 0;
            for (int i = 0; i < n; i++) {
                if (k == 1) {
                    max = Math.max(max, job[i]);
                    dp[i][k] = max;
                } else {
                    max = 0;
                    for (int j = i; j >= k - 1; j--) {
                        max = Math.max(max, job[j]);
                        //[0,j-1] [j,i]
                        dp[i][k] = Math.min(dp[i][k], dp[j - 1][k - 1] + max);
                    }
                }
            }
        }
        return dp[n - 1][d];
    }

    /**
     * 空间优化
     */
    public int minDifficulty2(int[] job, int d) {
        int n = job.length;
        if (n < d) {
            return -1;
        }
        int[] dp = new int[n];
        int max = 0;
        //k=1
        for (int i = 0; i < n; i++) {
            max = Math.max(max, job[i]);
            dp[i] = max;
        }
        for (int k = 2; k <= d; k++) {
            //k表示天数，相比下标-1，[0,k-2]...[k-1, n-1]
            //k=2 [0,0]...[1, n-1]
            for (int i = n - 1; i >= k - 1; i--) {
                dp[i] = Integer.MAX_VALUE;
                max = 0;
                for (int j = i; j >= k - 1; j--) {
                    max = Math.max(max, job[j]);
                    //[0,j-1] [j,i]
                    //如果i从小到大，则dp[j-1]会受到前面dp[i]的影响
                    dp[i] = Math.min(dp[i], dp[j - 1] + max);
                }
            }
        }
        return dp[n - 1];
    }


}
