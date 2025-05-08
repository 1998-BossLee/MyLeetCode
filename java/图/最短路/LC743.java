package 图.最短路;

import javafx.util.Pair;

import java.util.*;

/**
 * @author: liyangjin
 * @create: 2025-05-08 16:36
 * @Description:
 */
public class LC743 {

    public int networkDelayTime(int[][] times, int n, int k) {
        //有向边
        Map<Integer, List<Pair<Integer, Integer>>> directedEdgeMap = new HashMap<>();
        for (int i = 0; i < times.length; i++) {
            int u = times[i][0];
            int v = times[i][1];
            int w = times[i][2];
            directedEdgeMap.putIfAbsent(u, new ArrayList<>());
            directedEdgeMap.get(u).add(new Pair(v, w));
        }
        int[] dis = dijkstra(n, k, directedEdgeMap);
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (dis[i] == Integer.MAX_VALUE) {
                return -1;
            }
            ans = Math.max(ans, dis[i]);
        }
        return ans;
    }

    private int[] dijkstra(int n, int start, Map<Integer, List<Pair<Integer, Integer>>> edgeMap) {
        int[] dis = new int[n + 1];//节点从1开始数
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[start] = 0;
        //int[] dis[i],i 从离起点最近的点开始松弛图
        PriorityQueue<int[]> que = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        que.add(new int[]{0, start});
        while (!que.isEmpty()) {
            int[] now = que.poll();
            //u=起点 v=终点 w=权重
            int u = now[1];
            List<Pair<Integer, Integer>> edgeList = edgeMap.get(u);
            if (edgeList == null) {
                continue;
            }
            for (Pair<Integer, Integer> edge : edgeList) {
                int v = edge.getKey();
                int w = edge.getValue();
                //如果当前点的最短路+边的权重小于下一个点的最短路，说明可以更新
                if (dis[u] + w < dis[v]) {
                    dis[v] = dis[u] + w;
                    que.add(new int[]{dis[v], v});
                }
            }
        }
        return dis;
    }

}
