package 博弈;

import java.util.Arrays;

public class LC1753 {

    /**
     * 假设a<=b<=c
     * a+b <= c a和b全都和c配对，ans = a+b
     * a+b > c a和b配对到小于等于c的时候，全部和c配对。
     * 假设ab先匹配t组，a+b-2*t<=c t=(a+b-c)/2。
     * ans = t+c = (a+b-c)/2+c = 0.5a + 0.5b + 0.5c
     */
    public int maximumScore(int a, int b, int c) {
        int[] x = new int[]{a, b, c};
        Arrays.sort(x);
        if (x[0] + x[1] <= x[2]) {
            return x[0] + x[1];
        }
        return (x[0] + x[1] + x[2]) / 2;
    }

}
