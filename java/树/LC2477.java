package 树;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC2477 {

    /**
     * 以0为起点，找所有节点的子树大小，Math.max(num-seats,1);
     */
    static Map<Integer, List<int[]>> edgeMap;
    static long ans;

    public long minimumFuelCost(int[][] roads, int seats) {
        int n = roads.length + 1;
        ans = 0;
        edgeMap = new HashMap<>();
        //初始化双向边
        for (int i = 0; i < n; i++) {
            edgeMap.put(i, new ArrayList<>());
        }
        for (int i = 0; i < roads.length; i++) {
            int u = roads[i][0];
            int v = roads[i][1];
            edgeMap.get(u).add(new int[]{u, v});
            edgeMap.get(v).add(new int[]{v, u});
        }
        dfs(0, -1, seats);
        return ans;
    }

    public int dfs(int now, int parent, int seats) {
        List<int[]> edgeList = edgeMap.get(now);
        //以now为节点的树的大小
        int sonNum = 1;
        for (int[] edge : edgeList) {
            int v = edge[1];
            if (v == parent) {
                continue;
            }
            sonNum = sonNum + dfs(v, now, seats);
        }
        if (now != 0) {
            ans += Math.ceil((sonNum * 1.0) / seats);
        }
        return sonNum;
    }

}
