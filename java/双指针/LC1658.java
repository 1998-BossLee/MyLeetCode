package 双指针;

public class LC1658 {

    /**
     * 找最长子串，和为sum
     * 移动右指针，如果总和大于sum，则移动左指针减少范围
     */
    public int minOperations(int[] nums, int x) {
        int sum = 0, n = nums.length;
        for (int num : nums) {
            sum += num;
        }
        sum -= x;
        int left = 0, right = 0, now = 0, maxGap = -1;
        while (left <= right && left < n) {
            while (right < n && now < sum) {
                now += nums[right];
                right++;
            }
            if (now == sum) {
                //这里的right通过自增跳出循环了，所以now的范围是[left,right)
                maxGap = Math.max(maxGap, right - left);
            }
            now -= nums[left];
            left++;
        }
        if (maxGap == -1) {
            return -1;
        }
        return n - maxGap;
    }

}
