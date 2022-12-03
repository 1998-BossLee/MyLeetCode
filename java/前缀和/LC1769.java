package 前缀和;

import java.util.HashMap;
import java.util.Map;

public class LC1769 {

    public static void main(String[] args) {
        String s = "ck077";
        System.out.println(secondHighest(s));
    }

    public static int[] minOperations(String boxes) {
        int n = boxes.length();
        //prefix[i]表示[0,i]一共有多少个珠子
        int[] prefix = new int[n];
        int[] res = new int[n];
        prefix[0] = boxes.charAt(0) - '0';
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1];
            if (boxes.charAt(i) == '1') {
                prefix[i]++;
                res[0] = res[0] + i;
            }
        }
        //从左往右遍历。左边的珠子多走一步，右边的珠子少走一步。
        for (int i = 1; i < n; i++) {
            res[i] = res[i - 1] + prefix[i - 1] - (prefix[n - 1] - prefix[i - 1]);
        }
        return res;
    }


    public static int secondHighest(String s) {
        int[] num = new int[10];
        for (int i = 0; i < s.length(); i++) {
            int a = s.charAt(i) - '0';
            if (0 <= a && a <= 9) {
                num[a]++;
            }
        }
        boolean secondTag = false;
        for (int i = 9; i > 0; i--) {
            if (num[i] > 0) {
                if (!secondTag) {
                    secondTag = true;
                } else {
                    return i;
                }
            }
        }
        return -1;
    }

}

