package dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author: liyangjin
 * @create: 2025-08-18 18:33
 * @Description:
 */
public class LC679 {

    /**
     * 二元运算，总共4个数，每个数只能用一次，能否得到24
     * 1.枚举运算可能性 a+b a-b b-a a*b a/b b/a
     * 2.每次消耗1个数，dfs看最后结果能不能得到24
     * 3.不是整数触除法，是实数，浮点数偏差小于1e-9即可判断结果能否到24
     */
    double gap = 1e-9;
    public boolean judgePoint24(int[] cards) {
        List<Double> nums = new ArrayList<>();
        for (int card:cards) {
            nums.add((double)card);
        }
        return dfs(nums);
    }


    boolean dfs(List<Double> nums) {
        if (nums.size() == 1) {
            return Math.abs(nums.get(0) - 24) < gap;
        }
        int n = nums.size();
        for (int i = 0; i < n; i++) {
            double a = nums.get(i);
            for (int j = i + 1; j < n; j++) {
                double b = nums.get(j);
                Set<Double> mergeNums = new HashSet<>();
                mergeNums.add(a + b);
                mergeNums.add(a - b);
                mergeNums.add(b - a);
                mergeNums.add(a * b);
                // 注意除法可能会出现除数为0的情况
                if (Math.abs(b) > gap) {
                    mergeNums.add(a / b);
                }
                if (Math.abs(a) > gap) {
                    mergeNums.add(b / a);
                }
                //每次用一个新的数组
                List<Double> newNums = new ArrayList<>(nums);
                newNums.remove(j);
                for (double nextNum : mergeNums) {
                    newNums.set(i, nextNum); // 把第i个数替换成计算结果
                    if (dfs(newNums)) {
                        return true; // 如果能得到24，直接返回true
                    }
                }
            }
        }
        return false;
    }

}
