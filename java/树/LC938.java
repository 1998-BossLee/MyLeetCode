package 树;

/**
 * @author: liyangjin
 * @create: 2024-02-26 10:23
 * @Description:
 */
public class LC938 {

    /**
     * 二叉搜索树，返回范围位于[low, high]之间的节点和
     *
     * 左小右大
     * val < low  找right
     * val > high 找left
     * 否则 val + right + left
     */
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        }
        if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        }
        return root.val + rangeSumBST(root.right, low, high) + rangeSumBST(root.left, low, high);
    }




}
