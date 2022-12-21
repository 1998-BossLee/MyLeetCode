package kmp;

public class LC1764 {

    /**
     * kmp，主串匹配多个子串
     * 1、打印子串公共前缀表
     * 2、匹配
     */
    public boolean canChoose(int[][] groups, int[] nums) {
        int[][] pre = initPublicPrefix(groups);
        int i = 0, curIdx = 0, j = 0;
        while (curIdx < nums.length && i < groups.length) {
            if (nums[curIdx] == groups[i][j]) {
                curIdx++;
                j++;
            } else {
                if (j == 0) {
                    curIdx++;
                } else {
                    j = pre[i][j - 1];
                }
            }
            if (j == groups[i].length) {
                i++;
                j = 0;
            }
        }
        return i == groups.length;
    }

    /**
     * 初始化子串的公共前缀表
     */
    private int[][] initPublicPrefix(int[][] groups) {
        int n = groups.length;
        int[][] pre = new int[n][];
        for (int i = 0; i < n; i++) {
            int m = groups[i].length;
            pre[i] = new int[m];
            pre[i][0] = 0;
            //curIdx表示当前处理的元素下标，j表示有多少个公共前缀
            int curIdx = 1, j = 0;
            while (curIdx < m) {
                if (groups[i][curIdx] == groups[i][j]) {
                    pre[i][curIdx] = j + 1;
                    j++;
                    curIdx++;
                } else {
                    //核心处理，如果有公共前缀
                    if (j > 0) {
                        j = pre[i][j - 1];
                    } else {
                        pre[i][curIdx] = 0;
                        curIdx++;
                    }
                }
            }
        }
        return pre;
    }

}
