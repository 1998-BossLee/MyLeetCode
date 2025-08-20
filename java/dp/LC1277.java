package dp;

/**
 * @author: liyangjin
 * @create: 2025-08-20 23:27
 * @Description:
 */
public class LC1277 {

    /**
     * dp[i][j]表示以位置[i,j]为右下角的正方形的最大边长。
     * 假设[i,j]是一个正方形的右下角，dp[i][j]=x
     * 则左、上、左上均【至少】为x-1长度的正方形。
     * dp[i][j-1] >= x-1
     * dp[i-1][j] >= x-1
     * dp[i-1][j-1] >= x-1
     * 则 min(dp[i][j-1], dp[i-1][j], dp[i-1][j-1]) >= x-1
     * dp[i][j] <= min(dp[i][j-1], dp[i-1][j], dp[i-1][j-1]) + 1
     */
    public int countSquares(int[][] matrix) {
        int n = matrix.length, m = matrix[0].length, ans = 0;
        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j];
                } else if (matrix[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
                ans += dp[i][j];
            }
        }
        return ans;
    }
}
