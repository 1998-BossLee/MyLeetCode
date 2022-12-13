package 并查集;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC1697 {

    /**
     * queries根据limit从小到达排序，把小于等于的边加到图里，用并查集判断是否连接
     */
    int[] par;

    public boolean[] distanceLimitedPathsExist(int n, int[][] edgeList, int[][] queries) {
        par = new int[n];
        for (int i = 0; i < n; i++) {
            par[i] = i;
        }
        Arrays.sort(edgeList, (e1, e2) -> e1[2] - e2[2]);
        PriorityQueue<Node> que = new PriorityQueue<>((n1, n2) -> n1.limit - n2.limit);
        for (int i = 0; i < queries.length; i++) {
            que.add(new Node(i, queries[i][0], queries[i][1], queries[i][2]));
        }
        boolean[] res = new boolean[queries.length];
        int edgeIdx = 0;
        while (!que.isEmpty()) {
            Node now = que.poll();
            int maxLimit = now.limit;
            while (edgeIdx < edgeList.length && edgeList[edgeIdx][2] < maxLimit) {
                unite(edgeList[edgeIdx][0], edgeList[edgeIdx][1]);
                edgeIdx++;
            }
            res[now.id] = checkSame(now.start, now.end);
        }
        return res;
    }

    class Node {
        int id, start, end, limit;

        public Node(int id, int start, int end, int limit) {
            this.id = id;
            this.start = start;
            this.end = end;
            this.limit = limit;
        }
    }

    private int findParent(int x) {
        if (par[x] == x) {
            return x;
        }
        return par[x] = findParent(par[x]);
    }

    private void unite(int x, int y) {
        int xx = findParent(x);
        int yy = findParent(y);
        if (xx != yy) {
            par[xx] = yy;
        }
    }

    private boolean checkSame(int x, int y) {
        int xx = findParent(x);
        int yy = findParent(y);
        return xx == yy;
    }


}
