package 并查集;

/**
 * 并查集模板
 */
public class UniteTemplate {

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
