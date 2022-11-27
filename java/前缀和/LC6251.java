package 前缀和;

public class LC6251 {

    /**
     * 枚举中心点，处理前缀和后缀关系
     * pre[i][j]表示左边先i后j的组合个数
     * suf[j][i]表示右边先j后i的组合个数
     * 相乘
     */
    public int countPalindromes(String s) {
        long ans = 0, p = 100000007;
        int[] suf = new int[10];
        int[][] suf2 = new int[10][10];
        for (int i = s.length() - 1; i >= 0; i--) {
            int a = s.charAt(i) - '0';
            //suf[i][j]的i与j位置严格不同，假设8899999 从后往前suf[8][9]=5，然后再加一次suf[8][9]+=5=10
            for (int j = 0; j < 10; j++) {
                suf2[a][j] += suf[j];
            }
            suf[a]++;
        }
        int[] pre = new int[10];
        int[][] pre2 = new int[10][10];
        for (int i = 0; i < s.length(); i++) {
            int a = s.charAt(i) - '0';
            //枚举中间点，需要减少后缀a的数量，即撤销操作
            suf[a]--;
            for (int j = 0; j < 10; j++) {
                suf2[a][j] -= suf[j];
            }
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    //pre[j][k]表示左边先j后k的组合数，suf[k][j]表示右边先k后j的组合数
                    ans += pre2[j][k] * suf2[k][j];
                }
            }
            for (int j = 0; j < 10; j++) {
                //pre[j][a]与suf[a][j]相反，更好理解下标的含义
                pre2[j][a] += pre[j];
            }
            pre[a]++;
        }
        return (int) (ans % p);
    }

}
