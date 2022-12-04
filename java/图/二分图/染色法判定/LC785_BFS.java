package 图.二分图.染色法判定;

import java.util.ArrayDeque;
import java.util.Queue;

public class LC785_BFS {

    public boolean isBipartite(int[][] graph) {
        int[] color = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            if (color[i] == 0) {
                if (!bfs(i, color, graph)) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean bfs(int start, int[] color, int[][] graph) {
        Queue<Integer> que = new ArrayDeque<>();
        color[start] = 1;
        que.add(start);
        while (!que.isEmpty()) {
            int now = que.poll();
            for (int i = 0; i < graph[now].length; i++) {
                int next = graph[now][i];
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
}
