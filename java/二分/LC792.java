package 二分;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC792 {

    /**
     * 二分解法
     * 把字符串s的各个字母所在的下标区分开
     * 遍历word的时候快速找到
     */
    public int numMatchingSubseq(String s, String[] words) {
        //字符串s的各个字母对应的有序下标
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            map.put((char) (i + 'a'), new ArrayList<>());
        }
        for (int i = 0; i < s.length(); i++) {
            map.get(s.charAt(i)).add(i);
        }
        int res = 0;
        for (int i = 0; i < words.length; i++) {
            int target = -1;
            for (int j = 0; j < words[i].length(); j++) {
                char a = words[i].charAt(j);
                List<Integer> list = map.get(a);
                int idx = findFirstMore(list, target);
                if (idx == -1) {
                    break;
                }
                target = list.get(idx);
                if (j == words[i].length() - 1) {
                    res++;
                }
            }
        }
        return res;
    }

    /**
     * 二分，寻找第一个大于
     */
    private int findFirstMore(List<Integer> list, int target) {
        int l = 0, r = list.size() - 1, m;
        while (l <= r) {
            m = (l + r) / 2;
            if (list.get(m) > target) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l >= list.size() ? -1 : l;
    }

}
