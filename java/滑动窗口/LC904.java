package 滑动窗口;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: liyangjin
 * @create: 2025-08-05 09:44
 * @Description:
 */
public class LC904 {

    /**
     * 最外层不要用while(left<right)
     * 固定最外层用其中一个维度right，里面再用left
     */
    public int totalFruit(int[] fruits) {
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0, max = 0, n = fruits.length;
        for (int right = 0; right < n; right++) {
            map.put(fruits[right], map.getOrDefault(fruits[right], 0) + 1);
            while (map.size() > 2) {
                int cnt = map.get(fruits[left]) - 1;
                if (cnt == 0) {
                    map.remove(fruits[left]);
                } else {
                    map.put(fruits[left], cnt);
                }
                left++;
            }
            /**
             * 0 1 2 2
             * right = 0, left = 0, max = right - left + 1 = 1
             * right = 1, left = 0, max = right - left + 1 = 2
             * right = 2, left = 1, max = right - left + 1 = 2
             * right = 3, left = 1, max = right - left + 1 = 3
             */
            max = Math.max(max, right - left + 1);
        }
        return max;
    }

}
