package 周赛;

import java.util.*;

public class Match322 {

    public boolean isCircularSentence(String sentence) {
        String[] s = sentence.split(" ");
        if (s.length == 1) {
            return s[0].charAt(0) == s[0].charAt(s[0].length() - 1);
        }

        for (int i = 0; i < s.length - 1; i++) {
            String cur = s[i];
            String next = s[i + 1];
            if (cur.charAt(cur.length() - 1) != next.charAt(0)) {
                return false;
            }
        }
        return s[0].charAt(0) == s[s.length - 1].charAt(s[s.length - 1].length());
    }


    public long dividePlayers(int[] skill) {
        Arrays.sort(skill);
        int n = skill.length;
        int sum = skill[0] + skill[n - 1];
        long ans = skill[0] * skill[n - 1];
        for (int i = 1; i < n / 2; i++) {
            if (skill[i] + skill[n - 1 - i] != sum) {
                return -1;
            }
            ans += skill[i] * skill[n - 1 - i];
        }
        return ans;
    }

    /**
     * 连通路径的最小路径，因为可以走多次
     */
    public int minScore(int n, int[][] roads) {
        int minRoad = Integer.MAX_VALUE;
        Map<Integer, List<int[]>> edgeMap = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            edgeMap.put(i, new ArrayList<>());
        }
        for (int i = 0; i < roads.length; i++) {
            int u = roads[i][0];
            int v = roads[i][1];
            int w = roads[i][2];
            edgeMap.get(u).add(new int[]{u, v, w});
            edgeMap.get(v).add(new int[]{v, u, w});
        }
        boolean[] vis = new boolean[n + 1];
        Queue<Integer> que = new ArrayDeque<>();
        que.add(1);
        vis[1] = true;
        while (!que.isEmpty()) {
            int now = que.poll();
            List<int[]> edgeList = edgeMap.get(now);
            for (int[] edge : edgeList) {
                minRoad = Math.min(minRoad, edge[2]);
                int v = edge[1];
                if (!vis[v]) {
                    vis[v] = true;
                    que.add(v);
                }
            }
        }
        return minRoad;
    }

}
