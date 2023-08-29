package 树;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: liyangjin
 * @create: 2023-08-29 14:39
 * @Description:
 */
public class LC823 {

    /**
     * 根节点 = 左 * 右
     * 如果纯双层遍历，举例 2 3 4 6 12 24，遍历两两相乘a*b=c，c++，但是这样没有顺序，c也可能充当a、b，不能准确把控，
     * 先分解因数，再自下而上累计树的数量，因数一定比根小，确保有序性。
     */
    public int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr);
        //以arr[i]为根节点的树有多少
        Map<Integer, Long> map = new HashMap<>();
        long res = 0, p = 1000000007;
        //分解因子，从小到大。
        for (int a : arr) {
            int q = (int) Math.sqrt(a);
            long sum = 1;
            for (int i = 2; i <= q; i++) {
                if (a % i == 0) {
                    int x = a / i;
                    if (!map.containsKey(i) || !map.containsKey(x)) {
                        continue;
                    }
                    sum = (sum + map.get(i) * map.get(x)) % p;
                    if (i != x) {
                        sum = (sum + map.get(i) * map.get(x)) % p;
                    }
                }
            }
            map.put(a, sum);
            res = (res + sum) % p;
        }
        return (int) res;
    }

}
