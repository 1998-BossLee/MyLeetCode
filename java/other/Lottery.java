package other;

import java.util.*;

public class Lottery {

    static int n = 99;
    static int[][] a = new int[n][7];
    static int[] num1 = new int[36];
    static int[] num2 = new int[13];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
//        List<Node> list = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            String s = scan.nextLine();
//            if (s.equals("大乐透 - 历史开奖")) {
//                continue;
//            }
//
//            String id = s.substring(12, 20);
//            String num = s.substring(22);
//            list.add(new Node(id, num));
//            //System.out.println(id + " " + num);
//        }
//        for (int i = list.size()-1; i >= 0; i--) {
//            System.out.println(list.get(i).id + " " + list.get(i).num);
//        }


        for (int i = 0; i < n; i++) {
            String s = scan.next();
            //System.out.println(s);
            for (int j = 0; j < a[0].length; j++) {
                s = scan.next();
                //System.out.println(s);
                a[i][j] = Integer.parseInt(s);
                if (j < 5) {
                    num1[a[i][j]]++;
                } else {
                    num2[a[i][j]]++;
                }
            }
        }
        getNeverAppearMin(num1, 6);
        System.out.println();
        getNeverAppearMin(num2, 3);
        System.out.println();
        System.out.println();
        getNeverAppearMax(num1, 6);
        System.out.println();
        getNeverAppearMax(num2, 3);
        System.out.println();

    }

    public static void getNeverAppearMin(int num[], int t) {
        Set<Integer> set = new HashSet<>();
        List<Integer> resultList = new ArrayList<>();
        while (t-- > 0) {
            int idx = -1, min = 100;
            for (int i = 1; i < num.length; i++) {
                if (min > num[i] && !set.contains(i)) {
                    idx = i;
                    min = num[i];
                }
            }
            set.add(idx);
            resultList.add(idx);
        }
        Collections.sort(resultList);
        for (int i =0;i<resultList.size();i++) {
            System.out.print( resultList.get(i)+ " ");
        }
    }

    public static void getNeverAppearMax(int num[], int t) {
        Set<Integer> set = new HashSet<>();
        while (t-- > 0) {
            int idx = -1, max = -1;
            for (int i = 1; i < num.length; i++) {
                if (max <= num[i] && !set.contains(i)) {
                    idx = i;
                    max = num[i];
                }
            }
            set.add(idx);
            System.out.print(idx + " ");
        }
    }


}


class Node {
    String id;
    String num;

    public Node(String id, String num) {
        this.id = id;
        this.num = num;
    }
}
