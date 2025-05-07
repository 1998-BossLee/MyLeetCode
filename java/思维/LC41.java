package 思维;

/**
 * @author: liyangjin
 * @create: 2025-05-06 16:13
 * @Description:
 */
public class LC41 {


    /**
     * nums长度就100000，缺失的最大正数不会超过100001
     * nums[i]范围在[1, n]才有意义，负数和大数可以忽略掉。
     * 对于有效数组nums[i]用x表示
     * nums[x] = -nums[x]，负数表示出现过的正数
     * 如果原数组就是负数，由于它没有用，先随便赋值成大数
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            //0也是没用的，别占用位置
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < n; i++) {
            int x = Math.abs(nums[i]); //nums[2] 可能被 nums[0]=2的时候标记成负数
            //n是边界值，也需要标记，把0干掉了都往前挪一位
            if (x <= n) {
                //这里为什么还需要用Math.abs，因为nums[x]可能已经被标记成负数了，可以有重复数字
                nums[x - 1] = -Math.abs(nums[x - 1]);
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

}
