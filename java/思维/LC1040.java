package 思维;

import java.util.Arrays;

/**
 * @author: liyangjin
 * @create: 2023-04-08 23:51
 * @Description:
 */
public class LC1040 {


    /**
     * 真实难度 hard
     * 端点 -> 非端点，有界距离，一定会慢慢缩小
     * 最大值理论：尽可能把所有空位都占一遍，每次往中间占1位，但是第一次移动的端点的间隙需舍弃。显然就是舍弃掉初始端点间隙较大的一个。
     * 可以通过累加间隙计算，但是由于石子数量一定，占位一定，可以通过最大间隙-石子数量去获得可以补的位
     * 从s[0]到s[n-2]之间的空位个数，或者从s[1]到s[n-1]之间的空位个数
     * 0 1 2 3 4
     * s[n-2]-s[0]-1表示间隙大小，这段间隙又被n-3个石子占住了。 s[n-2]-s[0]-1-(n-3)=s[n-2]-s[0]-n+2
     * max(s[n-2]-s[0]-n+2, s[n-1]-s[1]-n+2)
     * 最小值理论：长度为n的窗口-已经存在的石子  ---> 求长度为n的窗口内最多有多少石子。
     * 特殊情况：2-3-100 100没办法直接到1或者4，需要2->5，100->3
     * 2-3-5这种，s[0]到s[n-2]之间或者s[1]到s[n-1]之间没有空位的，一步到位
     */
    public int[] numMovesStonesII(int[] s) {
        Arrays.sort(s);
        int n = s.length;
        int e1 = s[n - 2] - s[0] - n + 2;
        int e2 = s[n - 1] - s[1] - n + 2;
        int maxMove = Math.max(e1, e2);
        //没有空位
        if (e1 == 0 || e2 == 0) {
            return new int[]{Math.min(2, maxMove), maxMove};
        }
        //找滑动窗口的最大石子数，下标是
        int maxCount = 0, left = 0, right = 0;
        for (right = 0; right < n; right++) {
            while (s[right] - s[left] + 1 > n) {
                left++;
            }
            //维护窗口n内的最大石子数
            maxCount = Math.max(maxCount, right - left + 1);
        }
        return new int[]{n - maxCount, maxMove};
    }

    public boolean checkDistances(String s, int[] distance) {
        int[] firstIdx = new int[26];
        for (int i = 0; i < s.length(); i++) {
            int a = s.charAt(i) - 'a';
            if (firstIdx[a] != 0) {
                if (i - firstIdx[a] != distance[a]) {
                    return false;
                }
            }
            firstIdx[a] = i + 1;
        }
        return true;
    }

}
