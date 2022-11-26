package 不值得记录的简单题;

public class LC1752 {

    public boolean check(int[] nums) {
        int idx = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                //找到异常点，break
                idx = i + 1;
                break;
            }
        }
        if (idx == 0) {
            return true;
        }
        for (int i = idx; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return false;
            }
        }
        //最后需要判断首尾是否符合条件
        return nums[nums.length - 1] <= nums[0];
    }

}
