package 图.二分图.染色法判定;

public class LC785_DFS {

    boolean flag;
    int[] color;

    public boolean isBipartite(int[][] graph) {
        flag = true;
        color = new int[graph.length];
        //存在多个连通块，一次dfs搞不定
        for (int i = 0; i < graph.length; i++) {
            if (color[i] == 0) {
                color[i] = 1;
                dfs(i, 1, graph);
            }
        }
        return flag;
    }

    /**
     * 当前点、当前点的颜色、图
     *
     * @param x
     * @param nowColor
     * @param graph
     */
    private void dfs(int x, int nowColor, int[][] graph) {
        if (!flag) {
            return;
        }
        //遍历相邻节点，做2件事。
        for (int i = 0; i < graph[x].length; i++) {
            int next = graph[x][i];
            //1、没有染色则染不同颜色的色
            if (color[next] == 0) {
                color[next] = -nowColor;
                //只递归没有染色过的
                dfs(next, -nowColor, graph);
            } else if (color[next] == nowColor) {
                //2、染色了则判断颜色是否与自己不同，若不同则不是二分图
                flag = false;
                return;
            }
        }
    }


}
