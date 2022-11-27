package 数学.公式化简;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC6248 {
    /**
     * 6248. 统计中位数为 K 的子数组
     * --------------------------
     * 长度 = 奇数， 小于 = 大于
     * 左边小于 + 右边小于 = 左边大于 + 右边大于
     * 左边小于 - 左边大于 = 右边大于 - 右边小于
     * --------------------------
     * 长度 = 偶数，小于 + 1 = 大于
     * 左边小于 + 右边小于 + 1 = 左边大于 + 右边大于
     * 左边小于 - 左边大于 + 1 = 右边大于 - 右边小于
     * --------------------------
     * 右侧：把小于k的当-1处理，大于k的当1处理，前后缀和相加
     * 左侧：相反
     * --------------------------
     * k=4
     * 5    1    7    4    2    6    3
     * -1   0   -1    0   -1    0   -1
     * 4、426、742、74263、174、17426、51742、5174263
     * <p>
     * 3    4    5    6    1    2
     * 1    0    1    2    1    0
     * 4、45、4561、45612、345、34561
     */
    public int countSubarrays(int[] nums, int k) {
        int idx = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == k) {
                idx = i;
                break;
            }
        }
        Map<Integer, Integer> numMap = new HashMap<>();
        int num = 0;
        numMap.put(0, 1);
        for (int i = idx + 1; i < n; i++) {
            if (nums[i] < k) {
                num--;
            } else {
                num++;
            }
            numMap.put(num, numMap.getOrDefault(num, 0) + 1);
        }
        int res = numMap.get(0) + numMap.getOrDefault(1, 0);
        num = 0;
        for (int i = idx - 1; i >= 0; i--) {
            if (nums[i] < k) {
                num++;
            } else {
                num--;
            }
            res += numMap.getOrDefault(num, 0) + numMap.getOrDefault(num + 1, 0);
        }
        return res;
    }
}
