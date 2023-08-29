package 二分;

/**
 * @author: liyangjin
 * @create: 2023-05-08 00:01
 * @Description:
 */
public class LCP79 {

    /**
     * 1、左右端点可以无限膨胀
     * 2、中间城墙的 最大膨胀长度，撑死是左右两边的空格补满
     * 3、对第二块城墙，优先膨胀左边，其次膨胀右边。
     * 二分，判断膨胀长度是否够所有城墙膨胀。
     * 最后一个满足条件的数，返回r，移动l
     */
    public int rampartDefensiveLine(int[][] rampart) {
        int max = Integer.MAX_VALUE;
        for (int i = 1; i < rampart.length - 1; i++) {
            max = Math.min(max, rampart[i][0] - rampart[i - 1][1] + rampart[i + 1][0] - rampart[i][1]);
        }
        int l = 0, r = max, mid;
        while (l <= r) {
            mid = (l + r) / 2;
            if (checkout(rampart, mid)) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return r;
    }

    public boolean checkout(int[][] rampart, int x) {
        int r, left = rampart[1][0] - rampart[0][1], right = 0;
        for (int i = 1; i < rampart.length - 1; i++) {
            r = x;
            right = rampart[i + 1][0] - rampart[i][1];
            //左边有空位，优先向左膨胀
            if (left > 0) {
                r = r - left;
                //左边位置巨大，直接膨胀完了
                if (r <= 0) {
                    //下一个城墙的左边 = 现在的右边
                    left = right;
                    continue;
                }
            }

            //不论有没有向左膨胀，r都代表剩余需要膨胀长度，找右边进行膨胀
            right = right - r;
            if (right < 0) {
                return false;
            }
            left = right;
        }
        return true;
    }

}
