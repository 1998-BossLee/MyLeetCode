package 二分;

public class LC1802 {

    /**
     * sum 不超过maxSum，
     * 二分边界：最后一个符合条件的数，返回r，判断l
     * 判断内容：是否能构建单调不严格递减的两条坡
     * 元素值>0，假设二分值是x=5
     * 坡的最大总和是 5 5 5 5 5 5 5 5 5 5 5
     * 坡的最小总和是 5 4 3 2 1 1 1 1 1 1 1
     */
    public int maxValue(int n, int index, int maxSum) {
        long left = 1, right = maxSum, mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (check(mid, index, n, maxSum)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return (int) right;
    }

    private boolean check(long x, int idx, int n, int maxSum) {
        int leftNum = idx + 1;
        int rightNum = n - idx;
        long sum = getSum(x, leftNum) + getSum(x, rightNum) - x;
        return sum <= maxSum;
    }

    private long getSum(long x, int num) {
        if (x >= num) {
            // 5 4
            // 5 4 3 2
            // (5+2)*4/2
            return (x + x - num + 1) * num / 2;
        } else {
            // 5 8
            // 5 4 3 2 1 1 1 1
            // (5+1)*5/2+3
            return (x + 1) * x / 2 + num - x;
        }
    }

}
