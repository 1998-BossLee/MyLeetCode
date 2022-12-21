package 并查集;

public class LC1971 {

    int[] par;

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        init(n);
        for (int i = 0; i < edges.length; i++) {
            unite(edges[i][0], edges[i][1]);
        }
        return find(source) == find(destination);
    }

    private void init(int n) {
        par = new int[n];
        for (int i = 0; i < n; i++) {
            par[i] = i;
        }
    }

    private int find(int x) {
        if (x == par[x]) {
            return x;
        }
        return par[x] = find(par[x]);
    }

    private void unite(int x, int y) {
        int xx = find(x);
        int yy = find(y);
        if (xx != yy) {
            par[xx] = yy;
        }
    }

}
