package 贪心;

/**
 * @author: liyangjin
 * @create: 2025-12-25 18:46
 * @Description:
 */
public class LC955 {
    /**
     * 贪心
     * 如果某一列是升序，必然不删。
     * 如果某两行已经分出胜负，不需要再比较。
     */
    public int minDeletionSize(String[] strs) {
        int n = strs.length, m = strs[0].length(), ans = 0;
        //sorted[i] = true表示第i行 严格小于 第i+1行
        boolean[] sorted = new boolean[n - 1];

        for (int j = 0; j < m; j++) {
            boolean canKeep = true;
            for (int i = 0; i < n - 1; i++) {
                //只处理没有分出胜负的行
                if (!sorted[i]) {
                    //没有分出胜负的逆序对需要删除
                    if (strs[i].charAt(j) > strs[i + 1].charAt(j)) {
                        canKeep = false;
                        break;
                    }
                }
            }

            if (!canKeep) {
                ans++;
                continue;
            }

            for (int i = 0; i < n - 1; i++) {
                //第i行 严格小于 第i+1行
                if (strs[i].charAt(j) < strs[i + 1].charAt(j)) {
                    sorted[i] = true;
                }
            }
        }
        return ans;
    }
}
