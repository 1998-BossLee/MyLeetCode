package 思维;

/**
 * @author: liyangjin
 * @create: 2025-05-06 14:08
 * @Description:
 */
public class LC1920 {

    // ans[i] = nums[nums[i]]
    // 使用O(1)空间复杂度，最简单是乘1000来标记。存储【当前值】和【原值】。分别用3位数存储。
    //
    // 补码定义 -x = (~x) + 1
    // -(x+1) = -x - 1 = (~x) + 1 - 1 = ~x
    public int[] buildArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if (x < 0) {
                continue;
            }
            int cur = i;
            while (nums[cur] != i) {
                int nxt = nums[cur];
                nums[cur] = ~nums[nxt];
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int x = 0;
        System.out.println(~x);
    }

}
