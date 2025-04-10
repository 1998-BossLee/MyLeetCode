package 双指针;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: liyangjin
 * @create: 2025-04-03 17:23
 * @Description:
 */
public class LC3 {

    /**
     * Map
     * left right
     * 右进 如果有某个字符>1，则需要移动左指针把当前字符干掉
     * 左出
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) return 0;
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, right = 0, max = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0) + 1);
            if (map.get(c) > 1) {
                while (map.get(c) > 1) {
                    char d = s.charAt(left);
                    map.put(d, map.get(d) - 1);
                    left++;
                }
            }
            max = Math.max(max, right - left + 1);
            right++;
        }
        return max;
    }

}
