package 栈;

import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @author: liyangjin
 * @create: 2023-04-28 13:39
 * @Description:
 */
public class LC1172 {



}

class DinnerPlates {

    /**
     * 从左到右第一个未满栈
     * 从右到左第一个非空栈
     * 指定下标pop，导致各个栈参差不齐。
     *
     * 如果单独维护一个未满栈，找下一个需要遍历，肯定会被卡
     * 可以维护所有未满栈，最小堆维护未满栈下标。
     */
    LinkedList<LinkedList<Integer>> stacks = new LinkedList<>();
    LinkedList<Integer> stack;
    PriorityQueue<Integer> notFullQue = new PriorityQueue<>();
    int capacity;

    public DinnerPlates(int capacity) {
        notFullQue.clear();
        stacks.clear();
        this.capacity = capacity;
    }

    public void push(int val) {
        //未满栈早就被pop移除了
        if (!notFullQue.isEmpty() && notFullQue.peek() >= stacks.size()) {
            notFullQue.clear();
        }
        if (notFullQue.isEmpty()) {
            stack = new LinkedList<>();
            stack.add(val);
            stacks.add(stack);
            if (stack.size() < capacity) {
                notFullQue.add(stacks.size() - 1);
            }
        } else {
            stack = stacks.get(notFullQue.peek());
            stack.add(val);
            if (stack.size() == capacity) {
                notFullQue.poll();
            }
        }
    }

    public int pop() {
        return popAtStack(stacks.size() - 1);
    }

    public int popAtStack(int index) {
        if (index < 0 || index >= stacks.size() || stacks.get(index).isEmpty()) {
            return -1;
        }
        stack = stacks.get(index);
        //从原状态判断满栈，变成非空栈。而不是先pop再判非空栈。
        if (stack.size() == capacity) {
            notFullQue.add(index);
        }
        int res = stack.removeLast();
        //去掉末尾空栈，保证最后一个是非空栈
        while (stacks.size() > 0 && stacks.getLast().isEmpty()) {
            stacks.removeLast();
        }
        return res;
    }


}



