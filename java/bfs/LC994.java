package bfs;

import java.util.ArrayDeque;
import java.util.Queue;

public class LC994 {

    //上下左右
    int[][] d = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int orangesRotting(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        ArrayDeque<Node> que = new ArrayDeque<>();

        int one = 0, maxTs = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    one++;
                } else if (grid[i][j] == 2) {
                    que.addLast(new Node(i, j, 0));
                    vis[i][j] = true;
                }
            }
        }
        if (one == 0) {
            return 0;
        }

        while (!que.isEmpty()) {
            Node now = que.pop();
            for (int i = 0; i < 4; i++) {
                int x = now.x + d[i][0];
                int y = now.y + d[i][1];
                int ts = now.ts + 1;
                if (0 <= x && x < n && 0 <= y && y < m && grid[x][y] == 1 && !vis[x][y]) {
                    one--;
                    vis[x][y] = true;
                    que.add(new Node(x, y, ts));
                    maxTs = Math.max(maxTs, ts);
                }
            }
        }
        return one != 0 ? -1 : maxTs;
    }

    class Node {
        int x, y, ts;

        public Node(int x, int y, int ts) {
            this.x = x;
            this.y = y;
            this.ts = ts;
        }
    }

}
