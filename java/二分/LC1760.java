package 二分;

public class LC1760 {


    /**
     * 二分结果 res
     * 判断是否能在maxOperations步操作内把数组nums拆分成最大只有res
     * 第一个符合条件的数，返回l，操作r
     */
    public int minimumSize(int[] nums, int maxOperations) {
        int left = 1, right = 1000000000, mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (check(nums, mid, maxOperations)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] nums, int res, int maxOpt) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= res) {
                continue;
            }
            //7->3
            int r = nums[i] % res;
            int opt = nums[i] / res - 1 + ((r != 0) ? 1 : 0);
            maxOpt -= opt;
            if (maxOpt < 0) {
                return false;
            }
        }
        return true;
    }


}
