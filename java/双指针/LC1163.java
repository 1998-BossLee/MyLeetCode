package 双指针;

import java.util.Arrays;

/**
 * @author: liyangjin
 * @create: 2023-04-24 14:11
 * @Description:
 */
public class LC1163 {

    /**
     * 1、字典序最大的子串一定是s的某个后缀，因为如果前缀相同，越长越大，所以延伸到末尾
     * 2、所以我们只需要寻找前缀字典序最大的位置即可
     * 3、比较前缀，定义i、j表示起点，k表示位移步数，每次k++，可以比较出[i, i+k]和[j, j+k]的大小
     * 4、比较情况如下
     * (1) s[i+k] = s[j+k] 前缀相同，继续比较下一位
     * (2) s[i+k] < s[j+k]
     * 则以[i, i+k]开始的后缀 都干不过[j, j+k]开始的后缀。因为一定会存在某个节点s[i+k] < s[j+k]
     * 则[i, i+k]这部分都可以跳过。从 i+k+1开始比较，如果更新后i>j，则说明j在之前的[i, i+k]中，后缀也会干不过别人。保证i<j
     * (3) s[i+k] > s[j+k] 同理，[j, k+1]开始的后缀肯定干不过[i, i+k]开始的后缀
     */
    public String lastSubstring(String s) {
        int i = 0, j = 1, k = 0, n = s.length();
        while (j + k < n) {
            if (s.charAt(i + k) == s.charAt(j + k)) {
                k++;
            } else if (s.charAt(i + k) < s.charAt(j + k)) {
                i = i + k + 1;
                k = 0;
                if (i >= j) {
                    j = i + 1;
                }
            } else {
                j = j + k + 1;
                k = 0;
            }
        }
        return s.substring(i);
    }

}
