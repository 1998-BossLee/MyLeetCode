package bfs;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author: liyangjin
 * @create: 2023-05-09 10:08
 * @Description:
 */
public class LC1263 {

    /**
     * 推箱子
     * 打破昨天的思路：先把空位找出来，再用箱子通过dfs找最短路，因为箱子可能把人走到箱子后面这个路径堵住了。
     * <p>
     * 学习官方思路
     * 1、把人和箱子一起维护一个状态，vis[px][py][bx][by]
     * 2、人移动，是否与箱子重合，如果重合表示推动了箱子
     * 3、箱子也得和人往同方向移动，如果越界表示本次推动无效
     */
    int[][] dis = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public int minPushBox(char[][] grid) {
        int n = grid.length, m = grid[0].length;
        int px = 0, py = 0, bx = 0, by = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'S') {
                    px = i;
                    py = j;
                } else if (grid[i][j] == 'B') {
                    bx = i;
                    by = j;
                }
            }
        }
        boolean[][][][] vis = new boolean[n][m][n][m];
        vis[px][py][bx][by] = true;
        LinkedList<Node> que = new LinkedList<>();
        que.add(new Node(px, py, bx, by, 0));
        while (!que.isEmpty()) {
            Node now = que.removeFirst();
            for (int i = 0; i < 4; i++) {
                int npx = now.px + dis[i][0];
                int npy = now.py + dis[i][1];
                int nbx = now.bx, nby = now.by, step = now.step;
                //人移动后的位置与箱子重合，表示推动了箱子，步数+1
                if (npx == nbx && npy == nby) {
                    step++;
                    nbx = now.bx + dis[i][0];
                    nby = now.by + dis[i][1];
                    //到终点了，拜拜
                    if (nbx < 0 || nbx == n || nby < 0 || nby == m || grid[nbx][nby] == '#') {
                        continue;
                    }
                    if (grid[nbx][nby] == 'T') {
                        return step;
                    }
                }
                //不越界 && 不是墙 && 没访问过
                if (npx < 0 || npx == n || npy < 0 || npy == m || grid[npx][npy] == '#') {
                    continue;
                }
                if (!vis[npx][npy][nbx][nby]) {
                    vis[npx][npy][nbx][nby] = true;
                    Node next = new Node(npx, npy, nbx, nby, step);
                    if (step == now.step) {
                        //保证推箱子步数少的排前面
                        que.addFirst(next);
                    } else {
                        que.addLast(next);
                    }
                }
            }
        }
        return -1;
    }

    class Node {
        int px, py, bx, by, step;

        public Node(int px, int py, int bx, int by, int step) {
            this.px = px;
            this.py = py;
            this.bx = bx;
            this.by = by;
            this.step = step;
        }
    }

}
