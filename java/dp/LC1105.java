package dp;

import javafx.util.Pair;

import java.util.Arrays;

/**
 * @author: liyangjin
 * @create: 2023-04-23 16:42
 * @Description:
 */
public class LC1105 {

    /**
     * dp[i]表示前i本书的最小高度
     * 最后x本书单独一层，假设区间范围是[j,i]，条件是[j, i]的widthSum <= shelfWidth
     * maxHeight表示区间[j,i]的最大高度
     * dp[i] = min(dp[j-1]+maxHeight)
     */
    public int minHeightShelves(int[][] books, int shelfWidth) {
        int n = books.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1000000);
        dp[0] = books[0][1];
        for (int i = 1; i < n; i++) {
            //后x本书自成一行，区间是[j,i], 0<=j<=i
            int widthSum = 0, maxHeight = 0;
            for (int j = i; j >= 0; j--) {
                widthSum += books[j][0];
                maxHeight = Math.max(maxHeight, books[i][1]);
                if (widthSum > shelfWidth) {
                    break;
                }
                dp[i] = Math.min(dp[i], (j == 0 ? 0 : dp[j - 1]) + maxHeight);
            }
        }
        return dp[n - 1];
    }

}
