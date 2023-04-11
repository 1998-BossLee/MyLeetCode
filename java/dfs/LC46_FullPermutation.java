package dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC46_FullPermutation {

    boolean[] vis;
    List<List<Integer>> resList;

    public List<List<Integer>> permute(int[] nums) {
        int n = nums.length;
        vis = new boolean[n];
        resList = new ArrayList<>();
        dfs(nums, new LinkedList<>());
        return resList;
    }

    private void dfs(int[] nums, LinkedList<Integer> nowList) {
        if (nowList.size() == nums.length) {
            resList.add(new ArrayList<>(nowList));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            //取与不取
            if (vis[i]) {
                continue;
            }
            vis[i] = true;
            nowList.add(nums[i]);
            dfs(nums, nowList);
            vis[i] = false;
            nowList.removeLast();
        }
    }

}
