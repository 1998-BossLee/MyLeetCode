package 正则;

import java.util.HashSet;
import java.util.Set;

public class LC1805 {

    public static void main(String[] args) {
        String s = "a1b01c001c0";
        System.out.println(numDifferentIntegers(s));
    }

    public static int numDifferentIntegers(String word) {
        Set<String> set = new HashSet<>();
        //匹配一串字母： [a-z]+ 或者 [^0-9]+
        String[] ss = word.split("[a-z]+");
        System.out.println(ss.length);
        for (int i = 0; i < ss.length; i++) {
            if (ss[i].length()>0) {
                //replace是字符串替换， replaceAll是正则匹配。去掉前导0： ^0+。如果遇到000，当作空字符串保存计数
                String s = ss[i].replaceAll("^0+", "");
                set.add(s);
            }
        }
        return set.size();
    }


}
