package dp.hard;

import java.util.HashSet;
import java.util.Set;

public class LC1687_DP_Queue {

    /**
     * 思考：
     * 如果是不同码头的箱子，行程数 = 码头数 + 1
     * 运输的箱子所在的码头：11222312，分割11 222 3 1 2，需要6趟
     * <p>
     * 每出车一次，装完当前箱子i，对于下一个箱子i+1
     * 1、相同码头-能装一定要装，
     * 2、不同码头-不一定要装，(i+1)或许可以与(i+2)一起，例如卡车最大重量是100，遇到这样3个箱子：1-90 2-10 2-90 3-1 3-1 3-1 3-1
     * <p>
     * 能装得下肯定要装。
     */
    public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
        return 0;
    }



}
