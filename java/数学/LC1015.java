package 数学;

import java.util.HashSet;
import java.util.Set;

/**
 * @author: liyangjin
 * @create: 2023-05-10 09:55
 * @Description:
 */
public class LC1015 {


    /**
     * 1、2或者5的倍数直接返回-1
     * 2、同余定理
     * r=(x*10+1)%k
     * r如果循环则陷入死循环
     * @param k
     * @return
     */
    public int smallestRepunitDivByK(int k) {
        if (k % 2 == 0 || k % 5 == 0) {
            return -1;
        }
        Set<Integer> set = new HashSet<>();
        int x = 0, n = 0;
        while (true) {
            x = (x * 10 + 1) % k;
            n++;
            if (x == 0) {
                return n;
            }
            if (!set.add(x)) {
                return -1;
            }
        }
    }

}
