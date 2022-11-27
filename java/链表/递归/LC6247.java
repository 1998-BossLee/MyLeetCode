package 链表.递归;

import 链表.ListNode;

public class LC6247 {

    /**
     * 最终结果是一个单调不严格递减链表
     * 从后往前处理，递归遍历到最后一个退出，逐渐向前
     * 5->2->13->3->8
     * 先处理8，再处理3，再处理13
     */
    public ListNode removeNodes(ListNode head) {
        if (head.next == null) {
            return head;
        }
        ListNode next = removeNodes(head.next);
        //如果当前节点小于下一个节点，则舍弃当前节点
        if (next.val > head.val) {
            return next;
        } else {
            head.next = next;
            return head;
        }
    }
}
