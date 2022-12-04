package 图.二分图;

import java.util.*;

public class LC6256 {

    /**
     * 对连通块分组，必须是二分图才能完美分割。
     * 答案与选择的起点有关，枚举每个连通块的起点。（并查集）
     */
    public int magnificentSets(int n, int[][] edges) {
        //邻接表形式的边
        Map<Integer, List<Integer>> edgeMap = getEdgeMap(n, edges);
        //连通块
        Map<Integer, List<Integer>> nodeGroupMap = getNodeGroupMap(n, edges);
        //染色数组
        int[] color = new int[n + 1];
        int ans = 0;
        for (Map.Entry<Integer, List<Integer>> entry : nodeGroupMap.entrySet()) {
            //判断连通块是不是二分图
            int start = entry.getKey();
            System.out.println("start="+start);
            if (!isBipartiteGraph(start, color, edgeMap)) {
                return -1;
            }
            //枚举起点获取最大的组数
            int groupNum = getMaxGroup(entry.getValue(), edgeMap);
            ans += groupNum;
        }
        return ans;
    }

    private int getMaxGroup(List<Integer> groupNodes, Map<Integer, List<Integer>> edgeMap) {
        int n = edgeMap.size(), max = 0, t = 1;
        int[] vis = new int[n + 1];
        for (int start : groupNodes) {
            int groupNum = 0;
            Queue<Integer> que = new ArrayDeque<>();
            que.add(start);
            vis[start] = t;
            while (!que.isEmpty()) {
                groupNum++;
                //本层数量
                int size = que.size();
                while (size-- > 0) {
                    int now = que.poll();
                    List<Integer> nextNodes = edgeMap.get(now);
                    for (int next : nextNodes) {
                        if (vis[next] != t) {
                            que.add(next);
                            vis[next] = t;
                        }
                    }
                }
            }
            max = Math.max(max, groupNum);
            t++;
        }
        return max;
    }

    /**
     * bfs判断是否二分图
     */
    private boolean isBipartiteGraph(int start, int[] color, Map<Integer, List<Integer>> edgeMap) {
        Queue<Integer> que = new ArrayDeque<>();
        color[start] = 1;
        que.add(start);
        while (!que.isEmpty()) {
            int now = que.poll();
            List<Integer> nextNodes = edgeMap.get(now);
            for (int next : nextNodes) {
                if (color[next] == 0) {
                    color[next] = -color[now];
                    que.add(next);
                } else if (color[next] == color[now]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 邻接表形式的边
     */
    private Map<Integer, List<Integer>> getEdgeMap(int n, int[][] edges) {
        Map<Integer, List<Integer>> edgeMap = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            edgeMap.put(i, new ArrayList<>());
        }
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            edgeMap.get(u).add(v);
            edgeMap.get(v).add(u);
        }
        return edgeMap;
    }

    /**
     * 并查集获取连通块 Map<连通块代表， 连通块里的点>
     */
    private Map<Integer, List<Integer>> getNodeGroupMap(int n, int[][] edges) {
        int[] par = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            par[i] = i;
        }
        for (int i = 0; i < edges.length; i++) {
            unite(edges[i][0], edges[i][1], par);
        }
        Map<Integer, List<Integer>> groupMap = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int boss = find(i, par);
            List<Integer> nodes = groupMap.getOrDefault(boss, new ArrayList<>());
            nodes.add(i);
            groupMap.put(boss, nodes);
        }
        return groupMap;
    }

    private int find(int x, int[] par) {
        if (x == par[x]) {
            return x;
        }
        return par[x] = find(par[x], par);
    }

    private void unite(int x, int y, int[] par) {
        int xx = find(x, par);
        int yy = find(y, par);
        if (xx != yy) {
            par[xx] = yy;
        }
    }

}
