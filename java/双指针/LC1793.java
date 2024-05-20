package 双指针;

/**
 * @author: liyangjin
 * @create: 2024-03-20 11:20
 * @Description:
 */
public class LC1793 {

    /**
     * 求 min(nums[i]...nums[k]...nums[j]) * (j-i+1) 的最大值， i<=k<=j
     * 从i=k=j开始往左右移动，比较nums[i-1]和nums[j+1]大小，谁大就移动谁。
     * 大的不会影响min值，反而会增加宽度；如果一样大，则随便移动哪一方都可以。
     * 结论：一定存在 i=L 和 j=R 满足条件。
     * 证明：当i先达到L,此时j<R。设[L,R]之间的最小元素是m。
     * 则nums[L-1]<m，否则可以再把i移动到L-1，m不变增加长度使结果更大。
     * j<R，nums[j+1]>=m。进而得出nums[L-1] < m <= nums[j+1]，j一定会向右移动
     */
    public int maximumScore(int[] nums, int k) {
        int n = nums.length, i = k, j = k, minH = nums[k], ans = nums[k];
        //只循环n-1次，因为k不需要
        int t = n - 1;
        while (t-- > 0) {
            //右边先到达终点或者左边大于右边
            if (j == n - 1 || (i > 0 && nums[i - 1] > nums[j + 1])) {
                minH = Math.min(minH, nums[--i]);
            } else {
                minH = Math.min(minH, nums[++j]);
            }
            ans = Math.max(ans, minH * (j - i + 1));
        }
        return ans;
    }

}
