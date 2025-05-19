package 图.最短路;

import java.util.*;

/**
 * @author: liyangjin
 * @create: 2025-05-08 18:00
 * @Description:
 */
public class LC3341 {


    /**
     * 单源最短路
     * 看作无向图
     * d[i][j]表示从i到j的最短时间
     * 从[i,j]走到隔壁[u,v]的时间为max(d[i][j], moveTime[u][v])+1
     */
    public int minTimeToReach(int[][] moveTime) {
        int n = moveTime.length, m = moveTime[0].length;
        boolean[][] vis = new boolean[n][m];
        //上，下，左，右
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] d = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(d[i], Integer.MAX_VALUE);
        }
        d[0][0] = 0;
        PriorityQueue<State> q = new PriorityQueue<>();
        q.offer(new State(0, 0, 0));
        while (!q.isEmpty()) {
            State now = q.poll();
            if (vis[now.x][now.y]) {
                continue;
            }
            for (int[] dir : dirs) {
                int nx = now.x + dir[0];
                int ny = now.y + dir[1];
                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }
                int dist = Math.max(d[now.x][now.y], moveTime[nx][ny]) + 1;
                if (dist < d[nx][ny]) {
                    d[nx][ny] = dist;
                    q.offer(new State(nx, ny, dist));
                }
            }
        }
        return d[n - 1][m - 1];
    }

    static class State implements Comparable<State> {
        int x, y, dis;

        public State(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }

        @Override
        public int compareTo(State o) {
            return Integer.compare(this.dis, o.dis);
        }
    }

}
