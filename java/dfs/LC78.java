package dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: liyangjin
 * @create: 2025-05-19 10:57
 * @Description:
 */
public class LC78 {

    /**
     * 求不重复子集合，像全排列
     */
    List<List<Integer>> results;
    List<Integer> nowList;

    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        nowList = new LinkedList<>();
        results = new ArrayList<>();
        dfs(0, nums);
        return results;
    }

    private void dfs(int idx, int[] nums) {
        results.add(new LinkedList<>(nowList));
        if (idx == nums.length) {
            return;
        }
        for (int i = idx; i < nums.length; i++) {
            nowList.add(nums[i]);
            dfs(idx + 1, nums);
            nowList.remove(nowList.size() - 1); // 回溯
        }
    }

}
