package 贪心;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @author: liyangjin
 * @create: 2025-07-07 11:23
 * @Description:
 */
public class LC1353 {
    /**
     * 按结束时间排序死在这里，[[1,5],[1,5],[1,5],[2,3],[2,3]]
     * 按开始时间排序死在这里，[[1,2],[1,2],[3,3],[1,5],[1,5]]
     * 重新整理思路，对于第i天，可参加的会议有哪些？
     * 有一个最小堆来维护当前可参加的会议的结束时间，先参加最早结束的会议。
     */
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);  // 先按开始时间升序
            } else {
                return Integer.compare(a[1], b[1]);  // 再按结束时间升序
            }
        });
        int max = 0;
        for (int[] event : events) {
            max = Math.max(max, event[1]);
        }
        //第i天开始的会议
        List<Integer>[] groups = new ArrayList[max + 1];
        Arrays.setAll(groups, i -> new ArrayList<>());
        for (int[] event : events) {
            groups[event[0]].add(event[1]);
        }

        int cnt = 0;
        //维护可参加会议结束时间的最小堆
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int curDay = 1; curDay <= max; curDay++) {
            //删除过期会议
            while (!pq.isEmpty() && pq.peek() < curDay) {
                pq.poll();
            }
            //新增可参加的会议
            for (int endDay : groups[curDay]) {
                pq.offer(endDay);
            }
            //参加一个结束时间最早的会议
            if (!pq.isEmpty()) {
                pq.poll();
                cnt++;
            }
        }
        return cnt;
    }

    //不遍历max，跳到下一个
    public int maxEvents2(int[][] events) {
        int n = events.length;
        Arrays.sort(events, (a, b) -> a[0] - b[0]);  //按开始时间最小排序
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int cnt = 0, curDay = 0, idx = 0;
        while (idx < n || !pq.isEmpty()) {
            while (!pq.isEmpty() && pq.peek() < curDay) {
                pq.poll();  //删除过期会议
            }
            while (idx < n && events[idx][0] == curDay) {
                pq.offer(events[idx][1]);  //今天开始的会议，也就是可参加的会议
                idx++;
            }
            if (idx == n && pq.isEmpty()) {
                break;
            }
            if (pq.isEmpty()) {
                //空了就跳到下一个开始时间
                curDay = events[idx][0];
            } else {
                pq.poll();
                cnt++;
                curDay++;
            }
        }
        return cnt;
    }
}
