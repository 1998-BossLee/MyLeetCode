package dp;

import java.util.Arrays;

/**
 * @author: liyangjin
 * @create: 2025-07-17 18:36
 * @Description:
 */
public class LC1235 {

    /**
     * 按结束时间排序
     * dp[i]表示前i个工作最大的报酬
     * 不选第i个工作，延续使用dp[i-1]
     * 选择第i个工作，由于时间不能重叠，不一定是前i-1个工作。可能是i-2、i-3等。
     * 需要找到一个工作j：endTime[j] <= startTime[i]
     * 问题转化为：前j个工作 + 第i个工作
     * dp[i] = max (dp[j] + profit[i], dp[i-1])。
     *
     * 如果i=0则会出现i-1=-1，全部dp的坐标都+1
     * ---------------------------------------------
     * 如何找到工作j？
     * j是工作，通过时间定义的，与工作数量无关。
     * 假设有a、b、c工作的结束时间都相同，谁是j？
     * a=[10, 15, 5];
     * b=[11, 15, 5];
     * c=[12, 15, 5];
     * 应当选排序中最后一个工作作为j，dp[j]表示前j个工作的最大工作时间，涵盖了所有结束时间相同的工作。
     * 排序先按结束时间升序，再按开始时间升序，可以用二分找到最后一个小于等于startTime[i]的工作j。
     * 如果在前面工作中没有符合条件的j，即当前工作i与前面都冲突，dp[j]当0处理。
     */
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b) -> a[1] - b[1]);
        int[] dp = new int[n];
        // 第一个工作的利润。不选就是0，必然要选。
        dp[0] = jobs[0][2];
        for (int i = 1; i < n; i++) {
            int j = binarySearchLastLess(jobs, i - 1, jobs[i][0]);
            // 没有前一个工作，dp[j]当0处理。
            if (j < 0) {
                dp[i] = Math.max(dp[i-1], jobs[i][2]);
            } else {
                dp[i] = Math.max(dp[i-1], dp[j] + jobs[i][2]);
            }
        }
        return dp[n - 1];
    }

    /**
     * 二分变种，找最后一个小于等于x的下标
     */
    private int binarySearchLastLess(int[][] jobs, int right, int x) {
        int left = 0, mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (jobs[mid][1] <= x) {
                left = mid + 1; // 找到一个满足条件的，继续向右找
            } else {
                right = mid - 1; // 找不到，向左找
            }
        }
        return right; // 返回最后一个满足条件的下标
    }


}
