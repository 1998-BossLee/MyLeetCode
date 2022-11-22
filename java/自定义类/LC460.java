package 自定义类;

import java.util.HashMap;
import java.util.Map;

public class LC460 {
}

class LFUCache {

    Map<Integer, Node> nodeMap;
    //key=cnt, value=频率链表
    Map<Integer, DoublyLinkedList> cntListMap;
    int capacity, minCnt;

    public LFUCache(int capacity) {
        nodeMap = new HashMap<>();
        cntListMap = new HashMap<>();
        this.capacity = capacity;
        this.minCnt = 0;
    }

    public int get(int key) {
        //1、判断是否存在
        Node node = nodeMap.get(key);
        if (capacity == 0 || node == null) {
            return -1;
        }
        //2、存在，加cnt，把Node换到doublyLinkedListMap里cnt+1的value里，塞在前面，表示最近使用到了，后续需要删直接删最后面的即可
        DoublyLinkedList doublyLinkedList = cntListMap.get(node.cnt);
        doublyLinkedList.remove(node);
        if (doublyLinkedList.size == 0) {
            cntListMap.remove(node.cnt);
            minCnt = (minCnt == node.cnt) ? node.cnt + 1 : minCnt;
        }
        node.cnt++;
        doublyLinkedList = cntListMap.getOrDefault(node.cnt, new DoublyLinkedList());
        doublyLinkedList.addFirst(node);
        cntListMap.put(node.cnt, doublyLinkedList);
        return node.val;
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        Node node = nodeMap.get(key);

        DoublyLinkedList doublyLinkedList;
        //不存在，新增需要判断是否达到阈值
        if (node == null) {
            node = new Node(key, value, 1);
            if (nodeMap.size() == capacity) {
                //删除最末尾的节点，因为它使用次数最少，并且使用得最早
                doublyLinkedList = cntListMap.get(minCnt);
                Node tail = doublyLinkedList.removeTail();
                if (doublyLinkedList.size == 0) {
                    cntListMap.remove(minCnt);
                }
                nodeMap.remove(tail.key);
            }
            nodeMap.put(key, node);
            minCnt = 1;
        } else {
            node.val = value;
            //存在，把老的频率链表删了
            doublyLinkedList = cntListMap.get(node.cnt);
            doublyLinkedList.remove(node);
            if (doublyLinkedList.size == 0) {
                cntListMap.remove(node.cnt);
                if (minCnt == node.cnt) {
                    minCnt++;
                }
            }
            node.cnt++;
        }
        //不论存不存在都要插入到新频率链表中
        doublyLinkedList = cntListMap.getOrDefault(node.cnt, new DoublyLinkedList());
        doublyLinkedList.addFirst(node);
        cntListMap.put(node.cnt, doublyLinkedList);
    }
}

class Node {
    int key, val, cnt;

    Node pre, next;

    public Node() {
        this(-1, -1, 0);
    }

    public Node(int key, int val, int cnt) {
        this.key = key;
        this.val = val;
        this.cnt = cnt;
    }
}

/**
 * 自定义双向链表
 * 不关注存储的内容有哪些，只关注首尾，数据结构由自己设计，因此不需要借助LinkedList来删首尾节点
 */
class DoublyLinkedList {
    Node head, tail;
    int size;

    public DoublyLinkedList() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
        size = 0;
    }

    public void remove(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
        size--;
    }

    public void addFirst(Node node) {
        //操作插入节点，前后连好
        node.next = head.next;
        node.pre = head;
        //操作头节点，处理next
        head.next = node;
        //操作原本的老二，处理pre
        node.next.pre = node;
        size++;
    }

    public Node removeTail() {
        //最后一个节点进行删除动作
        Node node = tail.pre;
        remove(node);
        return node;
    }

}