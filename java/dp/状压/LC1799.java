package dp.状压;

public class LC1799 {


    /**
     * 状压DP，用0表示这些位置的数没有被删
     * 用整数s来表示当前元素的删除状态
     * dp[s]表示删除状态为s的最大值是多少
     */
    public int maxScore(int[] nums) {
        int n = nums.length;
        int[][] gcd = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                gcd[i][j] = gcd(nums[i], nums[j]);
            }
        }
        int[] dp = new int[1 << n];
        int maxBit = 1 << n;
        for (int s = 1; s < maxBit; s++) {
            //整数s中1的数量
            int one = Integer.bitCount(s);
            //如果是奇数个1，则不管，因为删除必须成双成对。
            if (one % 2 == 1) {
                continue;
            }
            //从左往右数，找第i位和第j位都是1的。尝试进行状态转移，用第i位和第j位都是0的dp值加上删除后的值
            for (int i = 0; i < n; i++) {
                if (((s >> i) & 1) == 0) {
                    continue;
                }
                for (int j = i + 1; j < n; j++) {
                    if (((s >> j) & 1) == 0) {
                        continue;
                    }
                    dp[s] = Math.max(dp[s], dp[s ^ (1 << i) ^ (1 << j)] + one / 2 * gcd[i][j]);
                }
            }
        }
        return dp[maxBit - 1];
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        System.out.println((9 >> 3));
    }

}
