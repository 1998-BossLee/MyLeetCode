package 前缀和;

/**
 * @author: liyangjin
 * @create: 2023-04-26 10:54
 * @Description:
 */
public class LC1013 {

    /**
     * prefix[i]
     * 分割线
     * leftMax + rightMax
     * firstLen 可能在左或者在右，分别尝试
     */
    public int maxSumTwoNoOverlap(int[] a, int firstLen, int secondLen) {
        int n = a.length, ans = 0;
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + a[i];
        }
        int idx = firstLen, leftMax = 0;
        // leftMax=[0,idx], rightMax=[idx+1, idx+secondLen]
        while (idx <= n - secondLen) {
            leftMax = Math.max(leftMax, prefix[idx] - prefix[idx - firstLen]);
            //移动idx即可维护左边最大值，右边最大值无法维护，只能当场与leftMax一起计算，维护在ans里
            ans = Math.max(ans, leftMax + prefix[idx + secondLen] - prefix[idx]);
            System.out.printf("idx=%d leftMax=%d ans=%d\n", idx, leftMax, ans);
            idx++;
        }
        idx = secondLen;
        leftMax = 0;
        while (idx <= n - firstLen) {
            leftMax = Math.max(leftMax, prefix[idx] - prefix[idx - secondLen]);
            ans = Math.max(ans, prefix[idx + firstLen] - prefix[idx]);
            idx++;
        }
        return ans;
    }

}
