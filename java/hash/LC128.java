package hash;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: liyangjin
 * @create: 2025-04-01 10:15
 * @Description:
 */
public class LC128 {

    //我的第一次写法
    public int longestConsecutive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int longest = 0;
        for (int i = 0; i < nums.length; i++) {
            int current = 0;
            int num = nums[i];
            while (set.contains(num)) {
                current++;
                num++;
                set.remove(num);
            }
            num = nums[i] - 1;
            while (set.contains(num)) {
                current++;
                num--;
                set.remove(num);
            }
            longest = Math.max(longest, current);
        }
        return longest;
    }

    //0x3f的写法
    public static int longestConsecutive0x3f(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int longest = 0;
        //遍历集合，不要遍历数组，数组会遇到 0000000123这种重复计算0的情况
        for (int x : set) {
            //找起点
            if (!set.contains(x - 1)) {
                int current = 1;
                int y = x + 1;
                while (set.contains(y)) {
                    current++;
                    y++;
                }
                longest = Math.max(longest, current);
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 0, 0, 1, 2, 3};
        System.out.println(longestConsecutive0x3f(nums));
    }

}
