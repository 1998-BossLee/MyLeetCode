package 接雨水;

/**
 * @author: liyangjin
 * @create: 2025-04-03 18:08
 * @Description:
 */
public class LC42_Prefix {


    //https://mini-dsp.oss-cn-beijing.aliyuncs.com/test-lyj/store/LC42-prefix.png
    public int trap(int[] height) {
        int len = height.length;
        if (len < 2) {
            return 0;
        }
        int[] prefixMax = new int[len];
        int[] suffixMax = new int[len];

        prefixMax[0] = height[0];
        for (int i = 1; i < len; i++) {
            prefixMax[i] = Math.max(prefixMax[i - 1], height[i]);
        }

        suffixMax[len - 1] = height[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            suffixMax[i] = Math.max(suffixMax[i + 1], height[i]);
        }

        int sum = 0;
        for (int i = 0; i < len; i++) {
            sum += Math.min(prefixMax[i], suffixMax[i]) - height[i];
        }
        return sum;
    }
}
