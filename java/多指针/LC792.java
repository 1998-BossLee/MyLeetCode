package 多指针;

import java.util.*;

public class LC792 {

    /**
     * 题目：找words是s的子序列有多少个
     * 对每个word设置一个指针，跟着主指针走，看能不能走完
     */
    public int numMatchingSubseq(String s, String[] words) {
        int n = s.length();
        int m = words.length;
        //下一个首字母为key的，有value里的这些单词
        Map<Character, LinkedList<Integer>> map = new HashMap<>();
        //初始化Map
        for (int i = 0; i < 26; i++) {
            map.put((char) (i + 'a'), new LinkedList<>());
        }
        //在Map里初始化words的首字母
        for (int i = 0; i < words.length; i++) {
            map.get(words[i].charAt(0)).add(i);
        }
        //各个单词当前指针位置
        int[] pos = new int[m];
        int res = 0;
        for (int i = 0; i < n; i++) {
            char a = s.charAt(i);
            LinkedList<Integer> nextWords = map.get(a);
            int len = nextWords.size();
            while (len-- > 0) {
                int wordIdx = nextWords.removeFirst();
                pos[wordIdx]++;
                if (pos[wordIdx] == words[wordIdx].length()) {
                    res++;
                } else {
                    char nextChar = words[wordIdx].charAt(pos[wordIdx]);
                    map.get(nextChar).add(wordIdx);
                }
            }
        }
        return res;
    }

}
