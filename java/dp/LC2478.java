package dp;

public class LC2478 {

    /**
     * TODO 还没理解
     * dp[i][j]表示前j个字符串分割成i段。
     * 每段的首字符必须是素数，尾字符不是素数。
     * 以j为分割点，则s[j]不是素数，且s[j+1]是素数
     * <p>
     * 枚举所有的i-1段，所有的dp[i-1][j']累加起来。j'是前i-1段的尾节点。
     * <p>
     * 答案是dp[k][n]表示前n个字符分割成k段
     */
    public int beautifulPartitions(String str, int k, int len) {
        char[] s = str.toCharArray();
        int n = s.length;
        //长度不达标 或者 首字符不是素数 或者 尾字符是素数
        if (k * len > n || !isPrime(s[0]) || isPrime(s[n - 1])) {
            return 0;
        }
        int MOD = 100000007;
        int[][] dp = new int[k + 1][n + 1];
        dp[0][0] = 1;
        //分割段数枚举，分割第i段，假设n=50，k=4，len=5
        //i=1时，j范围[5, 35]
        //i=2时，j范围[10, 40]
        //i=3时，j范围[15, 45]
        //i=4时，j范围[20, 50]
        for (int i = 1; i <= k; i++) {
            int sum = 0;
            //j是尾节点，至少预留出(k-i)*len个字符给后续的分段用
            //所有s数组的下标往后移一位，
            //当i=1，j=5时，j-len = 0，表示能否以s[0]作为新的起点，开头显然可以，因此canSplit()函数需要特判断0
            //canSplit(s, 0) = true  canSplit(s, 5) =
            //当i=1，j=6时，j-len = 0，表示能否以s[1]作为新的起点
            for (int j = i * len; j + (k - i) * len <= n; j++) {
                if (canSplit(s, j - len)) {
                    //前j-len个字符能否分割成i-1段，其实
                    sum = (sum + dp[i - 1][j - len]) % MOD;
                }
                if (canSplit(s, j)) {
                    dp[i][j] = sum;
                }
            }
        }
        return dp[k][n];
    }

    //是否素数
    private boolean isPrime(char c) {
        return c == '2' || c == '3' || c == '5' || c == '7';
    }

    //能否在j点分割，即s[j-1]不是素数，s[j]是素数
    private boolean canSplit(char[] s, int j) {
        return j == 0 || j == s.length || !isPrime(s[j - 1]) || isPrime(s[j]);
    }

}
