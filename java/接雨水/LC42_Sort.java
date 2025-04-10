package 接雨水;

import java.util.Arrays;

/**
 * @author: liyangjin
 * @create: 2025-04-03 18:08
 * @Description:
 */
public class LC42_Sort {

    public int trap(int[] height) {
        int len = height.length;
        if (len < 2) {
            return 0;
        }

        Node[] nodes = new Node[len];

        // 初始化节点
        for (int i = 0; i < len; i++) {
            nodes[i] = new Node(i, height[i]);
        }

        // 按高度降序排序
        Arrays.sort(nodes, (a, b) -> b.h - a.h);

        int sum = 0;
        int left = nodes[0].idx;
        int right = nodes[1].idx;
        //次高
        int minHeight = nodes[1].h;

        // 确保 left 在左，right 在右
        if (left > right) {
            int temp = left;
            left = right;
            right = temp;
        }

        // 计算初始存水量
        for (int i = left; i <= right; i++) {
            if (minHeight - height[i] > 0) {
                sum += minHeight - height[i];
            }
        }

        // 处理剩下的柱子
        for (int i = 2; i < len; i++) {
            int nowIdx = nodes[i].idx;
            int nowH = nodes[i].h;

            // 新柱子在左边 [nowIdx+1, left-1]之间的水
            if (nowIdx < left) {
                for (int j = nowIdx + 1; j < left; j++) {
                    if (nowH - height[j] > 0) {
                        sum += nowH - height[j];
                    }
                }
                left = nowIdx;
            } else if (nowIdx > right) {
                // 新柱子在右边 [right+1, nowIdx-1]之间的水
                for (int j = right + 1; j < nowIdx; j++) {
                    if (nowH - height[j] > 0) {
                        sum += nowH - height[j];
                    }
                }
                right = nowIdx;
            }
            //不断将left 和 right往两边扩大
        }
        return sum;
    }


    class Node {
        int idx; // 记录索引
        int h;   // 记录高度

        Node(int idx, int h) {
            this.idx = idx;
            this.h = h;
        }
    }

}
