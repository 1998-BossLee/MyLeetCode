package 自定义类;

import java.util.HashMap;
import java.util.Map;

public class LC146 {
}
class LRUCache {
    //双向链表
    class LinkedNode {
        int key;
        int val;
        LinkedNode pre;
        LinkedNode next;

        public LinkedNode() {};

        public LinkedNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    //用HashMap做哈希表
    private Map<Integer, LinkedNode> map = new HashMap<>();
    //当前容量
    private int size;
    //最大容量
    private int capacity;
    //头尾节点
    private LinkedNode head, tail;

    public LRUCache(int capacity) {
        //初始化缓存容量、首尾结点，并首尾相连，首尾都是伪的。
        this.size = 0;
        this.capacity = capacity;
        head = new LinkedNode();
        tail = new LinkedNode();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        //获取缓存
        LinkedNode node = map.get(key);
        //找不到就返回-1
        if (node == null) {
            return -1;
        }
        //找得到就移到头部
        moveToHead(node);
        return node.val;
    }

    public void put(int key, int value) {//设置缓存
        LinkedNode node = map.get(key);
        //原来没有就新开一个结点，移到头部
        if (node == null) {
            LinkedNode newNode = new LinkedNode(key, value);
            map.put(key, newNode);
            addToHead(newNode);
            size++;
            //超出容量就去掉尾部，尾部指的是【伪尾节点的前一个】
            if (size > capacity) {
                map.remove(tail.pre.key);
                removeTail();
                size--;
            }
        } else {
            //key存在的话就更新value，同样要移动到头部
            node.val = value;
            moveToHead(node);
        }
    }

    //添加新结点，到头部
    private void addToHead(LinkedNode node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }


    //删除结点
    private void removeNode(LinkedNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    //移动结点到头部=删除当前节点+添加新结点到头部
    private void moveToHead(LinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    //删除尾结点
    private void removeTail() {
        LinkedNode node = tail.pre;
        removeNode(node);
    }
}
