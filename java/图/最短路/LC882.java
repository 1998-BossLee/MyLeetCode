package 图.最短路;

import javafx.util.Pair;

import java.util.*;

public class LC882 {

    /**
     * 求出整个图的最短路
     * 最大步数-点的最短路步数，剩余的步数在边上延伸
     */
    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        Map<Integer, List<Pair<Integer, Integer>>> edgeMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            edgeMap.put(i, new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            edgeMap.get(u).add(new Pair(v, edges[i][2]));
            edgeMap.get(v).add(new Pair(u, edges[i][2]));
        }
        int[] dis = dijkstra(n, 0, edgeMap);
        int res = 0;
        Map<Integer, Integer> directedMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (dis[i] <= maxMoves) {
                res++;
                List<Pair<Integer, Integer>> edgeList = edgeMap.get(i);
                for (Pair<Integer, Integer> edge : edgeList) {
                    int v = edge.getKey();
                    //同一条边可能从两边的点为起点，往另一边延伸，directed表示正向，reverse表示反向
                    int directed = i * n + v;
                    int reverse = v * n + i;
                    int directedNum = Math.min(maxMoves - dis[i], edge.getValue());
                    if (!directedMap.containsKey(reverse)) {
                        //第一次遍历到这条边，直接加上可以延伸的点
                        directedMap.put(directed, directedNum);
                        res = res + directedNum;
                    } else {
                        //第二次遍历到这条边，即存在反向边的节点数，减去反向的再把二者的加起来。
                        int reverseNum = directedMap.get(reverse);
                        res = res - reverseNum + Math.min(edge.getValue(), reverseNum + directedNum);
                    }
                }
            }
        }
        return res;
    }


    /**
     * 单源最短路：迪杰斯特拉
     */
    public int[] dijkstra(int n, int start, Map<Integer, List<Pair<Integer, Integer>>> edgeMap) {
        int[] dis = new int[n];
        Arrays.fill(dis, Integer.MAX_VALUE);
        dis[start] = 0;
        //que里的int[]只有2个数, [dis[i], 当前点i]
        PriorityQueue<int[]> que = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        que.add(new int[]{dis[start], start});
        while (!que.isEmpty()) {
            int[] now = que.poll();
            int u = now[1];
            if (dis[u] == Integer.MAX_VALUE) {
                continue;
            }
            List<Pair<Integer, Integer>> edgeList = edgeMap.get(u);
            for (Pair<Integer, Integer> edge : edgeList) {
                //u->v = val
                int v = edge.getKey();
                int val = edge.getValue();
                //松弛边，这里+1是结合题意
                if (dis[v] > (dis[u] + val + 1)) {
                    dis[v] = dis[u] + val + 1;
                    que.add(new int[]{dis[v], v});
                }
            }
        }
        return dis;
    }


}

