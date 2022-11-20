package dp;

public class LC799 {
    /**
     * 0
     * 01
     * 012
     * 0123
     * 01234
     * 第1杯 第一层满
     * 第2杯 第一层满，第二层各0.5
     * 第3杯 第二层满
     * 第4杯 第三层 0.25:0.5:0.25
     * 第5杯 第三层 0.5:1:0.5
     * 第6杯 第三层 0.75:1:0.5 第四层 0:0.25:0.25:0
     * 假设香槟没有往下流，当前杯子无限大来存储这些香槟，然后再抽取1的香槟，剩下的往下流。
     */
    public double champagneTower(int poured, int query_row, int query_glass) {
        double[][] dp = new double[101][101];
        dp[0][0] = poured;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j <= i; j++) {
                //假设杯子无限大，取出1剩余的往左右分
                double extra = dp[i][j] - 1;
                if (extra > 0) {
                    dp[i + 1][j] += extra / 2;
                    dp[i + 1][j + 1] += extra / 2;
                    //超过1的话，分完之后自己变成1
                    dp[i][j] = 1;
                }
            }
        }
        return dp[query_row][query_glass];
    }
}
