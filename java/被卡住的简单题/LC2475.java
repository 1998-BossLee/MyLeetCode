package 被卡住的简单题;

import java.util.HashMap;
import java.util.Map;

public class LC2475 {

    /**
     * 只是说了不相等，没有大小要求，按大小排序分割即可。
     */
    public int unequalTriplets(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int a = nums[i];
            map.put(a, map.getOrDefault(a, 0) + 1);
        }
        int left = 0, right = nums.length, ans = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int mid = entry.getValue();
            right -= mid;
            ans += left * mid * right;
            left += mid;

        }
        return ans;
    }

}
