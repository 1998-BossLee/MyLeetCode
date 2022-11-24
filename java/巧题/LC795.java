package 巧题;

public class LC795 {

    /**
     * 把数据分成3类，0、1、2
     * 0<left
     * left<=1<=right
     * right<2
     *
     * 限定last2
     * 对于当前遍历到的i
     * nums[i]=1，与前面任意长度的元素都可以组成符合条件的子数组 [last2+1, last1]、[last2+2, last1]...
     * nums[i]=0，last1不变，当前i可以往前面找到last1组成符合条件的子数组
     * last2                   last1        cur
     *   2     1     1     0     1     0     0
     * 往前面找符合条件的子数组共有4个。 110100、10100、0100、100
     */
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int last2 = -1, last1 = -1, ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > right) {
                last2 = i;
            }
            if (nums[i]>=left) {
                last1 = i;
            }
            ans += last1 - last2;
        }
        return ans;
    }

}
