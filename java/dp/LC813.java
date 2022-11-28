package dp;

public class LC813 {

    /**
     * sum[i]表示[0,i-1]的前缀和
     * dp[i][j] 表示 前i个元素分成j份的最大平均值和，1<=i<=n
     * j=1，dp[i][j] = sum[i]
     * j>1，dp[i][j] = max(dp[i][j], dp[x][j-1] + (sum[i]-sum[x])/(i-x) )
     * 因为不能有空子数组，x>=1 && x<=i-1
     *
     * idx        0   1   2   3   4   5
     * nums       4   5   2   1   4   6
     * sum        0   4   9   11  15  21
     * dp[i][1]   0   4   9   11  15  21
     *
     * dp[4][1] = 15，dp[i]从1开始算起
     * dp[4][2]的遍历
     * 当x=1时
     * dp[x][j-1] + (sum[i]-sum[x])/(i-x) = dp[1][1] + (sum[4]-sum[1])/(4-1)
     * 当x=2时
     * dp[x][j-1] + (sum[i]-sum[x])/(i-x) = dp[2][1] + (sum[4]-sum[2])/(4-2)
     */
    public static double largestSumOfAverages(int[] nums, int k) {
        int n = nums.length;
        double[][] dp = new double[n + 1][k + 1];
        double[] sum = new double[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
            dp[i + 1][1] = sum[i + 1] / (i + 1);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 2; j <= k; j++) {
                for (int x = 1; x <= i - 1; x++) {
                    dp[i][j] = Math.max(dp[i][j], dp[x][j - 1] + (sum[i] - sum[x]) / (i - x));
                }
            }
        }
        return dp[n][k];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{9, 1, 2, 3, 9};
        System.out.println(largestSumOfAverages(nums, 3));
    }

}
