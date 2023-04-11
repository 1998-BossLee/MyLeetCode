package 链表;

import java.util.*;

/**
 * @author: liyangjin
 * @create: 2023-04-10 14:13
 * @Description: 单调栈
 */
public class LC1019 {


    // 2-1-5          5-5-0
    // 2-7-4-3-5      7-0-5-5-0
    // 1-2-3-4        2-3-4-0

    /**
     * 单纯找下一个较大值，不是后续整个链表的最大值
     */
    public int[] nextLargerNodes(ListNode head) {
        int n = 0, idx = 0;
        ListNode cur = head;
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        int[] ans = new int[n];
        LinkedList<int[]> stack = new LinkedList<>(); // val idx
        cur = head;
        while (cur != null) {
            //但凡后续遇到一个比较大的，就可以弹出了
            while (!stack.isEmpty() && stack.getFirst()[0] < cur.val) {
                ans[stack.removeFirst()[1]] = cur.val;
            }
            stack.addFirst(new int[]{cur.val, idx++});
            cur = cur.next;
        }
        return ans;
    }


}
