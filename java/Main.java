import javafx.util.Pair;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {


    public static void main(String[] args) {
        int[] nums = {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};
    }

    /**
     * 第i位员工想买股票的前置条件是他的直属上级也买了股票。
     * 树形结构，想遍历只能从上往下。先用邻接表建图。
     * <p>
     * 假设当前是父节点root，股票价格为j，当前预算为budget
     * 买：自己的收益 + 所有子树的收益(预算budget-j)
     * 不买：所有子树的收益(预算budget)
     * <p>
     * dp[0][son]
     * dp[1][son]
     * <p>
     * 对于子树，返回的不是单一值，而是一个数组，表示在不同预算下的最大收益。
     * subDp[budget]
     * <p>
     * 从下往上推的话，能返回什么?
     * 我能返回预算和收益。
     * subDp[budget] = maxProfit
     */
    public int maxProfit(int n, int[] present, int[] future, int[][] hierarchy, int budget) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : hierarchy) {
            int u = edge[0], v = edge[1];
            graph.putIfAbsent(u, new ArrayList<>());
            graph.get(u).add(v);
        }
        return 0;
    }

    private void dfs(int root, Map<Integer, List<Integer>> graph, int[] present, int[] future, int budget, int[][] dp) {

    }





}


