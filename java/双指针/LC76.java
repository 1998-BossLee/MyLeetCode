package 双指针;


import java.io.IOException;
import java.nio.file.*;
import java.util.*;

/**
 * @author: liyangjin
 * @create: 2026-02-26 18:04
 * @Description:
 */
public class LC76 {
    /**
     没有固定窗口，就是left和right形成滑动窗口。
     先移动right，满足条件后再尝试移动left缩短子串大小。
     当left移动后不满足条件，再移动right去满足条件。
     如何不遍历子串判断是否满足条件？Map计数 + 当前满足条件的key数量。
     */
    public String minWindow(String s, String t) {
        int m = s.length(), n = t.length(), okCnt = 0;
        if (m < n) {
            return "";
        }
        Map<Character, Integer> sMap = new HashMap<>(), tMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char a = t.charAt(i);
            tMap.put(a, tMap.getOrDefault(a, 0) + 1);
        }
        String ans = "";
        int ansLen = m + 1;
        for (int left = 0, right = 0; right < m;) {
            //移动right满足条件先
            while (right < m) {
                char a = s.charAt(right);
                sMap.put(a, sMap.getOrDefault(a, 0) + 1);
                //大数据不能用==，没有常量池
                if (Objects.equals(sMap.get(a), tMap.get(a))) {
                    okCnt++;
                }
                right++;
                //满足就跑，也要+1，让break和while出去时候的right一致。
                if (okCnt == tMap.size()) {
                    break;
                }
            }
            //必须加okCnt的判断，否则是while失效出来的会影响答案
            if (ansLen > right - left && okCnt == tMap.size()) {
                ans = s.substring(left, right);
                ansLen = ans.length();
            }
            //移动left看能不能缩短子串大小
            while (left < right) {
                char a = s.charAt(left);
                sMap.put(a, sMap.getOrDefault(a, 0) - 1);
                left++;
                if (tMap.containsKey(a) && sMap.get(a) == tMap.get(a) - 1) {
                    okCnt--;
                    break;
                }
                if (ansLen > right - left && okCnt == tMap.size()) {
                    ans = s.substring(left, right);
                    ansLen = ans.length();
                }

            }

        }

        return ans;
    }

    public static void main(String[] args) throws Exception{
        String s = readFileToString("/Users/liyangjin/Desktop/LC76-input1");
        String t =  readFileToString("/Users/liyangjin/Desktop/LC76-input2");
//        String ans = minWindow(s, t);
//        System.out.println(ans);
    }

    public static String readFileToString(String filePath) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        return new String(bytes);
    }
}
