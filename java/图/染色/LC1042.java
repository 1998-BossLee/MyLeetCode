package 图.染色;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: liyangjin
 * @create: 2023-04-15 23:21
 * @Description:
 */
public class LC1042 {

    /**
     * 一张图，节点的相邻节点不可以是相同颜色。
     * 【所有花园最多有 333 条路径可以进入或离开】相当于节点的【度】<=3，选一个和邻节点不同的即可
     */
    public int[] gardenNoAdj(int n, int[][] paths) {
        List<Integer> g[] = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (int[] path : paths) {
            int u = path[0] - 1;
            int v = path[1] - 1;
            g[u].add(v);
            g[v].add(u);
        }
        int[] color = new int[n];
        for (int i = 0; i < n; i++) {
            boolean[] used = new boolean[5];
            for (int j : g[i]) {
                //邻节点的颜色已经用过了，如果没有用过则等于0
                used[color[j]] = true;
            }
            for (int c = 1; c <= 4; c++) {
                if (!used[c]) {
                    color[i] = c;
                }
            }
            //while (used[++color[i]]) ;
        }
        return color;
    }

}
