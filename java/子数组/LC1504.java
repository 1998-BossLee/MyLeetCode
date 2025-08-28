package 子数组;

/**
 * @author: liyangjin
 * @create: 2025-08-28 10:01
 * @Description:
 */
public class LC1504 {

    /**
     * 单行来看，高度为1的矩阵个数就是 值为1的子数组数量
     * 2行压缩成1行的话，可以求高度为2的矩阵个数，也就是值为2的子数组数量
     * h行压缩成1行的话，可以求高度为h的矩阵个数，也就是值为h的子数组数量
     * 从上往下处理每一行
     * 1. 第1行，只能处理高度为1的矩阵个数
     * 2. 第2行，除了本行高度为1的矩阵个数，还可以处理高度为2的矩阵个数
     * 3. 第h行，除了本行高度为1的矩阵个数，还可以处理高度为2、3、4...h的矩阵个数
     * 4. 以此类推，有一个上边界top和下边界bottom的概念，还有一个高度h的概念
     * 5. 第top行和第bottom行之间，高度为h的矩阵个数。
     */
    public int numSubmat(int[][] mat) {
        int m = mat.length, n = mat[0].length, ans = 0;
        for (int top = 0; top < m; top++) {
            int[] colSum = new int[n];
            for (int bottom = top; bottom < m; bottom++) {
                int h = bottom - top + 1, last = -1;
                for (int j = 0; j < n; j++) {
                    colSum[j] += mat[bottom][j];
                    if (colSum[j] == h) {
                        ans = ans + (j - last);
                    } else {
                        last = j;
                    }
                }
            }
        }
        return ans;
    }
}
