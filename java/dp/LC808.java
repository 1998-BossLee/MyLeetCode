package dp;

public class LC808 {

    /**
     * A:B
     * 4:0
     * 3:1
     * 2:2
     * 1:3
     * 1、把汤先除以25，有余则+1
     * 2、单回合期望 2.5:1.5，当n越大，大概率A先分配完。写出正解后测试>0.99999的n，然后直接return 1
     * 3、假设dp[a][a]表示有a份A汤和b份B汤时，A先分完的概率 + (A和B同时分完的概率/2)
     * 由于存在4种分配操作并且概率相同，则
     * dp[a][b]=(dp[a-4][b] + dp[a-3][b-1] + dp[a-2][b-2] + dp[a-1][b-3])/4
     * 初始化dp[a][b]
     * dp[0][0]=0+1/2=0.5
     * dp[a][0]=0+0=0; a>0
     * dp[0][b]=1+0/2=1; b>0
     */
    public double soupServings(int n) {
        if (n > 5000) {
            return 1;
        }
        n = (int) Math.ceil(n / 25d);
        double[][] dp = new double[n + 1][n + 1];
        dp[0][0] = 0.5;
        for (int i = 1; i <= n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[Math.max(0, i - 4)][j] + dp[Math.max(0, i - 3)][j - 1] + dp[Math.max(0, i - 2)][Math.max(0, j - 2)] + dp[i - 1][Math.max(0, j - 3)]) * 0.25;
            }
        }
        return dp[n][n];
    }
}
