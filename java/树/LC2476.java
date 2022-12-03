package 树;

import java.util.*;

public class LC2476 {

    TreeSet<Integer> treeSet;

    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
        treeSet = new TreeSet<>();
        List<List<Integer>> resList = new ArrayList<>();
        dfs(root);
        for (int i = 0; i < queries.size(); i++) {
            //第一个小于或等于e的元素
            Integer min = treeSet.floor(queries.get(i));
            //第一个大于或等于e的元素
            Integer max = treeSet.ceiling(queries.get(i));
            List<Integer> list = new ArrayList<>();
            list.add(min == null ? -1 : min);
            list.add(max == null ? -1 : max);
            resList.add(list);
        }
        return resList;
    }

    public void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        treeSet.add(root.val);
        dfs(root.left);
        dfs(root.right);
    }


}
