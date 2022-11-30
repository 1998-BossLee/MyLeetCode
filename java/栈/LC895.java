package 栈;

import java.util.*;

public class LC895 {
}

class FreqStack {

    int maxCnt;

    //元素个数，key=元素值，value=次数
    Map<Integer, Integer> freqMap;

    //数量的Map，key=元素个数，value=这个元素的个数
    Map<Integer, ArrayDeque<Integer>> freqGroupMap;

    public FreqStack() {
        maxCnt = 0;
        freqMap = new HashMap<>();
        freqGroupMap = new HashMap<>();
    }

    public void push(int val) {
        int num = freqMap.getOrDefault(val, 0) + 1;
        freqMap.put(val, num);
        ArrayDeque<Integer> que = freqGroupMap.getOrDefault(num, new ArrayDeque<>());
        freqGroupMap.put(num, que);
        //从头插入
        que.addFirst(val);
        maxCnt = Math.max(maxCnt, num);
    }

    public int pop() {
        ArrayDeque<Integer> maxCntQue = freqGroupMap.get(maxCnt);
        int val = maxCntQue.pop();
        freqMap.put(val, freqMap.get(val) - 1);
        if (maxCntQue.isEmpty()) {
            maxCnt--;
        }
        return val;
    }
}

