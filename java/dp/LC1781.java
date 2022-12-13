package dp;

public class LC1781 {

    /**
     * pre[i][j]前缀和数组，表示 字母 i 在[0,j]的数量
     * [x,y]之间的某个字母数量通过dp[i][y]-dp[i][x-1] x特判0
     * dp[i][j]表示[i,j]之间的美丽值，i<j
     * 根据前缀和数组找出数量最多的字母和最少的字母
     */
    public int beautySum(String s) {
        int n = s.length();
        if (n <= 2) {
            return 0;
        }
        int[][] pre = new int[26][n];
        pre[s.charAt(0) - 'a'][0] = 1;
        for (int j = 1; j < n; j++) {
            int x = s.charAt(j) - 'a';
            for (int i = 0; i < 26; i++) {
                if (i == x) {
                    pre[i][j] = pre[i][j - 1] + 1;
                } else {
                    pre[i][j] = pre[i][j - 1];
                }
            }
        }
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
                for (int k = 0; k < 26; k++) {
                    if (i == 0) {
                        if (pre[k][j] != 0) {
                            max = Math.max(max, pre[k][j]);
                            min = Math.min(min, pre[k][j]);
                        }
                    } else {
                        int num = pre[k][j] - pre[k][i - 1];
                        if (num != 0) {
                            max = Math.max(max, num);
                            min = Math.min(min, num);
                        }
                    }
                }
                sum += max - min;
            }
        }
        return sum;
    }

}
