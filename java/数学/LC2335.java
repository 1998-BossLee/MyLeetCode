package 数学;

import java.util.Arrays;

public class LC2335 {

    /**
     * a<=b<=c
     * a+b<=c 则a+b+(c-a-b)=c
     * a+b>c 2+4>5 3+4>5
     * 先处理一部分a和b，使得a'+b'=c
     * 假设处理了x，则a-x+b-x=c，(a+b-c)/2=x
     * 答案=c+x=(c+a+b)/2
     * 2,2,2可以拿到整数的x，x=1，变成1+1=2
     * 2,4,4可以拿到整数的x，x=1，变成1+3=4
     * 3,3,3不可以拿到整数的x，x=2，那咋办？撑死多1呗。
     * 1,3,3不可以拿到整数的x，x=1，也是撑死+1。
     * 所以a+b+c=偶数时，ans=(a+b+c)/2
     * a+b+c=奇数时，ans=(a+b+c)/2+1=(a+b+c+1)/2
     */
    public int fillCups(int[] amount) {
        Arrays.sort(amount);
        if (amount[0] + amount[1] <= amount[2]) {
            return amount[2];
        } else {
            return (amount[0] + amount[1] + amount[2] + 1) / 2;
        }
    }
}
