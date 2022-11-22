package 二分;

public class LC878 {

    /**
     * 如果a=2，b=10000，n比较小，那么几乎就没有b什么事了
     * 如果a=2，b=3，n比较大，就得考虑最小公倍数的倍数
     * 最终结果撑死也就4*1e13，不会爆long
     */
    public int nthMagicalNumber(int n, int a, int b) {
        int p = 1000000007;
        int gcd = gcd(a, b);
        int lcm = a * b / gcd;
        //二分法，在1 ～ 4*1e13之间找到一个值
        long l = 2, r = 1000000000000000L, m;
        //二分变种：第一个大于等于【符合第n个神奇数字】的数，n是target
        while (l <= r) {
            m = (l + r) / 2;
            //m大于等于第num个神奇数字
            long num = m / a + m / b - m / lcm;
            if (num >= n) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return (int) (l % p);
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

}
