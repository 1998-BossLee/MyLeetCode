package dp;

import java.util.Arrays;

/**
 * @author: liyangjin
 * @create: 2025-07-17 18:36
 * @Description:
 */
public class LC1751 {


    /**
     * n*k <= 1e6
     * dp[i][j]表示前i个会议中，选择了最多参加j个会议的最大价值。这里的i从1算起。
     * 对于第i个会议，
     * 不参加 dp[i][j] = dp[i-1][j]
     * 参加 dp[i][j] = dp[p][j-1] + value[i]
     * 参加的条件是，对于上一个会议p的结束时间 endTime[p]<startTime[i]
     * -----
     * 由于i-1和j-1会出现负数，因此想改写成i+1的形式，j从1开始算
     * 参加第i个会议，设置会议p，endTime[p]<startTime[i]，严格小于。问题变成 [0,p]会议中至多j-1个会议的+当前会议的价值。
     * dp[i+1][j]=dp[p+1][j-1]+events[i][2]
     * 不参加第i个会议，dp[i][j]
     */
    public int maxValue(int[][] events, int k) {
        if (k == 1) {
            int max = 0;
            for (int[] event : events) {
                max = Math.max(max, event[2]);
            }
            return max;
        }
        int n = events.length;
        Arrays.sort(events, (a, b) -> a[1] - b[1]);
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i < n; i++) {
            int p = binarySearchLastLess(events, i, events[i][0]);
            for (int j = 1; j <= k; j++) {
                //先继承不参加的结果
                dp[i + 1][j] = dp[i][j];

                //参加，如果没有合适的p则设置0
                int prev = (p >= 0) ? dp[p + 1][j - 1] : 0;
                dp[i + 1][j] = Math.max(dp[i + 1][j], prev + events[i][2]);
            }
        }
        return dp[n][k];
    }

    //最后一个严格小于
    private int binarySearchLastLess(int[][] events, int right, int upper) {
        int left = 0, mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (events[mid][1] < upper) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }

}
