package 前缀和;

import java.util.ArrayList;
import java.util.List;

public class LC809 {

    public static void main(String[] args) {
        String s = "heeellooo";
        String[] words = {"hello", "hi", "helo"};
        System.err.println(expressiveWords(s, words));
    }

    /**
     * 每个单词：每个字母前缀和计数
     */
    public static int expressiveWords(String s, String[] words) {
        int res = 0;
        List<Node> sNodeList = getNodeList(s);
        for (int i = 0; i < words.length; i++) {
            List<Node> wordNodeList = getNodeList(words[i]);
            if (checkStretchy(sNodeList, wordNodeList)) {
                System.out.println(words[i]);
                res++;
            }
        }
        return res;
    }

    public static List<Node> getNodeList(String s) {
        List<Node> list = new ArrayList<>();
        char a = s.charAt(0);
        int num = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == a) {
                num++;
            } else {
                list.add(new Node(a, num));
                num = 1;
                a = s.charAt(i);
            }
        }
        list.add(new Node(a, num));
        System.out.println(list.toString());
        return list;
    }

    public static boolean checkStretchy(List<Node> sNodeList, List<Node> wordNodeList) {
        if (sNodeList.size() != wordNodeList.size()) {
            return false;
        }
        int len = sNodeList.size();
        for (int i = 0; i < len; i++) {
            Node sNode = sNodeList.get(i);
            Node wordNode = wordNodeList.get(i);
            if ((sNode.a != wordNode.a) || (sNode.num != wordNode.num && sNode.num < 3) || (sNode.num < wordNode.num)) {
                return false;
            }
        }
        return true;
    }


}

class Node {
    char a;
    int num;

    public Node(char a, int num) {
        this.a = a;
        this.num = num;
    }

    public String toString() {
        return "a=" + a + " num=" + num;
    }
}
