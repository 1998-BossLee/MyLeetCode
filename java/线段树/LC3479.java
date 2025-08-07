package 线段树;

/**
 * @author: liyangjin
 * @create: 2025-08-06 09:43
 * @Description:
 */
public class LC3479 {

    /**
     * 线段树
     */
    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        SegmentTree tree = new SegmentTree(baskets);
        int ans = 0;
        for (int x : fruits) {
            // 找到第一个 >= x 的数，并更新为 -1
            int idx = tree.queryFirstAndUpdate(1, 0, baskets.length - 1, x);
            if (idx == -1) {
                // 没有找到，说明没有足够的篮子放下这个水果
                ans++;
            }
        }
        return ans;
    }

    class SegmentTree {
        //root从1开始算，max[root]表示区间[left, right]的最大值
        int[] max;

        public SegmentTree(int[] a) {
            int n = a.length;
            max = new int[n * 4];
            build(a, 1, 0, n - 1);
        }

        //max[root]表示区间[left, right]的最大值
        private void build(int[] a, int root, int left, int right) {
            if (left == right) {
                max[root] = a[left];
                return;
            }
            int mid = (left + right) / 2;
            build(a, root * 2, left, mid);
            build(a, root * 2 + 1, mid + 1, right);
            max[root] = Math.max(max[root * 2], max[root * 2 + 1]);
        }

        //找区间内的第一个 >= x 的数，并更新为 -1，返回这个数的下标（没有则返回 -1）
        public int queryFirstAndUpdate(int root, int left, int right, int x) {
            if (max[root] < x) {
                // 区间内没有 >= x 的数
                return -1;
            }
            //一定找到叶子节点，从下往上更新
            if (left == right) {
                max[root] = -1;
                return left;
            }
            int mid = (left + right) / 2;
            //先查询左子树，找不到就找右子树，这里是【隐式二分】
            int idx = queryFirstAndUpdate(root * 2, left, mid, x);
            if (idx == -1) {
                idx = queryFirstAndUpdate(root * 2 + 1, mid + 1, right, x);
            }
            //更新
            max[root] = Math.max(max[root * 2], max[root * 2 + 1]);
            return idx;
        }
    }

}
