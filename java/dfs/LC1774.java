package dfs;

public class LC1774 {


    int ans, minAbs;

    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        ans = 0;
        minAbs = Integer.MAX_VALUE;
        for (int i = 0; i < baseCosts.length; i++) {
            dfs(baseCosts[i], 0, toppingCosts, target);
        }
        return ans;
    }

    /**
     * 某种配料，选0、1、2份
     * @param sum          当前价格总数
     * @param idx          当前选择的配料下标
     * @param toppingCosts 配料价格表
     */
    private void dfs(int sum, int idx, int[] toppingCosts, int target) {
        int abs = Math.abs(sum - target);
        if (abs < minAbs) {
            ans = sum;
            minAbs = abs;
        }
        if (abs == minAbs && ans > sum) {
            ans = sum;
        }
        if (sum >= target || idx == toppingCosts.length) {
            return;
        }
        // 0
        dfs(sum, idx + 1, toppingCosts, target);
        // 1
        dfs(sum + toppingCosts[idx], idx + 1, toppingCosts, target);
        // 2
        dfs(sum + toppingCosts[idx] * 2, idx + 1, toppingCosts, target);
    }


}
