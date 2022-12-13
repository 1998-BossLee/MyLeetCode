package 巧题;

import java.util.Arrays;

public class LC1775 {

    public int minOperations(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        if (n1 * 6 < n2 || n1 > n2 * 6) {
            return -1;
        }
        int diff = Arrays.stream(nums2).sum() - Arrays.stream(nums1).sum();
        if (diff == 0) {
            return 0;
        }
        //交换，统一让nums1小于nums2
        if (diff < 0) {
            diff = -diff;
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }
        //nums1要变大，从1、2、3、4、5开始，nums2要变小，从6、5、4、3、2、1
        //cnt表示能够达到变化i的元素的个数，这样后续就不用有-1操作
        int[] cnt = new int[6];
        for (int x : nums1) {
            cnt[6 - x]++;
        }
        for (int x : nums2) {
            cnt[x - 1]++;
        }
        int ans = 0;
        for (int i = 5; i >= 0; i--) {
            if (i * cnt[i] >= diff) {
                //ans += (diff / i) + (diff % i == 0 ? 0 : 1);
                ans += (diff + i - 1) / i;
                break;
            } else {
                ans += cnt[i];
                diff -= i * cnt[i];
            }
        }
        return ans;
    }



}
