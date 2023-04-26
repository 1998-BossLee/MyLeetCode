package 树;

import javafx.util.Pair;

/**
 * @author: liyangjin
 * @create: 2023-04-18 11:45
 * @Description:
 */
public class LC1026 {

    /**
     * 找到节点A与子孙节点B的最大绝对值之差
     */
    int abs;

    public int maxAncestorDiff(TreeNode root) {
        abs = 0;
        dfs(root);
        return abs;
    }

    /**
     * 返回子树的<最小值，最大值>
     * 顺便更新abs
     */
    public Pair<Integer, Integer> dfs(TreeNode root) {
        int min = root.val, max = root.val;
        Pair<Integer, Integer> pair;
        if (root.left != null) {
            pair = dfs(root.left);
            min = Math.min(min, pair.getKey());
            max = Math.max(max, pair.getValue());
        }
        if (root.right != null) {
            pair = dfs(root.right);
            min = Math.min(min, pair.getKey());
            max = Math.max(max, pair.getValue());
        }
        abs = Math.max(abs, Math.max(Math.abs(root.val - min), Math.abs(root.val - max)));
        return new Pair<>(min, max);
    }

}
