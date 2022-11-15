package 思维;

public class LC775 {

    /**
     * 【局部倒置】一定是【全局倒置】，因此可以判断是否有【非局部倒置】即可
     * 是否存在nums[i]>nums[j]，j!=i+1。
     * 取min(nums[i+2], min[i+3], ... min[n-1])即可
     */
    public boolean isIdealPermutation(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return true;
        }
        int min = nums[n - 1];
        for (int i = n - 3; i >= 0; i--) {
            if (nums[i] > min) {
                return false;
            }
            min = Math.min(min, nums[i + 1]);
        }
        return true;
    }

}
