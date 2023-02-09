package 栈;

import java.util.Stack;

public class LC42 {

    /**
     * 接雨水
     * 单调递增栈
     * 遇到比较低的柱子就取出，统计区间面积
     * 遇到比较高的柱子就累计蓄水量
     */
    public int trap(int[] height) {
        Stack<Integer> stack = new Stack<>();
        int sum = 0, maxIdx = 0, topIdx = 0;
        for (int i = 0; i < height.length; i++) {
            while (stack.size() > 0 && height[stack.peek()] < height[i]) {
                int left = stack.pop();
                int width = i - left - 1;
                //sum += s;
                //System.out.printf("maxIdx=%d i=%d topIdx=%d s=%d\n", maxIdx, i, topIdx, s);
                if (stack.size() == 0) {
                    break;
                }
                topIdx = stack.peek();
            }
            if (height[maxIdx] < height[i]) {
                maxIdx = i;
            }
            stack.add(i);
        }
        return sum;
    }

}
