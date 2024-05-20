package 前缀和;

/**
 * @author: liyangjin
 * @create: 2024-03-14 16:32
 * @Description:
 */
public class LC2789 {

    /**
     * 从右往左合并，直到遇到nums[i]停止，说明nums[i]>nums[i+1]+nums[i+2]+...+nums[n-1]
     * nums[i]> sum[i+1, ... n-1]
     * 可以从nums[i]从新往左合并
     */
    public static long maxArrayValue(int[] nums) {
        int n = nums.length;
        long merged = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] <= merged) {
                merged += nums[i];
            } else {
                merged = nums[i];
            }
        }
        return merged;
    }


    public static void main(String[] args) {
        int[] nums = {2, 3, 7, 9, 3};
        System.out.println(maxArrayValue(nums));
    }

}
