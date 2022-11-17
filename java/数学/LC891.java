package 数学;

import java.util.Arrays;

public class LC891 {

    /**
     * 891. 子序列宽度之和
     * 求所有子序列的(Max-Min)之和。一个数组的子序列个数有2^n-1个。
     * 由于顺序没有影响，所以可以先排序。
     * 对于min和max都只用加减处理，可以把所有的max之和 减去所有的min之和
     * <p>
     * 最小值，充当min的次数 2^(n-1)，这个怎么来的？ 除了最小值自己，其他的n-1个数，要么选，要么不选，可以组成子序列。所以是2^(n-1)
     * 次小值，充当min的次数 2^(n-2)
     * ...
     * 最大值，充当min的次数 2^(n-n)=1
     * <p>
     * 最大值，充当max次数 2^(n-1)
     * 次大值，充当max次数 2^(n-2)
     * ...
     * 最小值，充当max次数 2^(n-n)=1
     */
    public int sumSubseqWidths(int[] nums) {
        Arrays.sort(nums);
        long res = 0, p = 1000000007, n = nums.length, pow = 1;
        //充当max
        for (int i = 0; i < n; i++) {
            res = (res + nums[i] * pow) % p;
            pow = pow * 2 % p;
        }
        pow = 1;
        //充当min
        for (int i = nums.length - 1; i >= 0; i--) {
            res = (res - nums[i] * pow) % p;
            pow = pow * 2 % p;
        }
        //最怕减完遇到负数
        return (int) ((res + p) % p);
    }

}
