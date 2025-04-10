package 滑动窗口;

import java.util.ArrayList;
import java.util.*;

/**
 * @author: liyangjin
 * @create: 2025-04-03 15:55
 * @Description:
 */
public class LC438 {


    /**
     * 26个小写字母计数，滑动过程计算是否与p的字母数字一样
     */
    public List<Integer> findAnagrams(String s, String p) {
        int[] cntP = new int[26];
        for (char c : p.toCharArray()) {
            cntP[c - 'a']++;
        }

        int[] cntS = new int[26];
        List<Integer> res = new ArrayList<>();
        //起始索引
        int lenP = p.length();
        for (int i = 0; i < s.length(); i++) {
            cntS[s.charAt(i) - 'a']++;
            if (i >= lenP) {
                cntS[s.charAt(i - lenP) - 'a']--;
            }
            if (isSame(cntP, cntS)) {
                res.add(i - lenP + 1);
            }
        }
        return res;
    }

    private boolean isSame(int[] cntP, int[] cntS) {
        for (int i = 0; i < 26; i++) {
            if (cntP[i] != cntS[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 右进 字符-1
     * 左出 字符+1
     * 当数量==0的时候就删掉
     * 当map的key数量为0时就是异构子串的起点
     */
    public List<Integer> findAnagrams_0X3F(String s, String p) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        for (int right = 0; right < s.length(); right++) {
            char rightChar = s.charAt(right);
            map.put(rightChar, map.getOrDefault(rightChar, 0) - 1);
            if (map.get(rightChar) == 0) {
                map.remove(rightChar);
            }
            int left = right - p.length();
            if (left >= 0) {
                char leftChar = s.charAt(left);
                map.put(leftChar, map.getOrDefault(leftChar, 0) + 1);
                if (map.get(leftChar) == 0) {
                    map.remove(leftChar);
                }
            }
            if (map.isEmpty()) {
                res.add(left + 1);
            }
        }
        return res;
    }
}
