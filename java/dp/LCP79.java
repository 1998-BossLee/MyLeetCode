package dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: liyangjin
 * @create: 2023-05-07 23:59
 * @Description:
 */
public class LCP79 {

    /**
     * 1. 字母是否在矩阵里，没有可以直接退出
     * 2. 多个相同的字母，不知道下个字母选哪个才是最优，非常远的字母也可能是最优，因为下下个字母可能就在远字母的隔壁
     * 3. 从前往后找遥遥无期，考虑从后往前找
     * 4. 如果找到尾字母，不论是哪个都可以结束了，假设尾字母有y个，倒数第1个字母有x个，则从倒数第2个字母走到终点的可能路径有x*y种。
     * 5. 对于倒数第2个字母只需要保留最近的即可，供给倒数第3个字母寻找【直达终点】的路径。
     * 6. dp[i][j] = {x, y, d} 表示从下标i，以第j种位置走到终点的状态。
     * 7. 初始化dp[n-1][*] = {x, y, 0}; 同上假设有y种位置。
     * 8. 倒数第2个字母dp[n-2][j] = 二者距离 + min(dp[i][j].d)。
     */
    public int extractMantra(String[] matrix, String mantra) {
        //Map<字母，位置列表>
        Map<Character, List<Node>> map = new HashMap<>();
        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i].length();
            for (int j = 0; j < matrix[i].length(); j++) {
                Character a = matrix[i].charAt(j);
                List<Node> list = map.getOrDefault(a, new ArrayList<>());
                list.add(new Node(i, j, 0));
                map.put(a, list);
            }
        }
        //不存在
        int n = mantra.length();
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(mantra.charAt(i))) {
                return -1;
            }
        }
        //撑死只有sum条路径
        Node[][] dp = new Node[n][sum];
        //初始化n-1的dp值，原地就到了。
        List<Node> list = map.get(mantra.charAt(n - 1));
        for (int i = 0; i < list.size(); i++) {
            dp[n - 1][i] = new Node(list.get(i).x, list.get(i).y, 0);
        }

        //从后往前找，处理[i， n-1]的路径
        for (int i = n - 2; i >= 0; i--) {
            List<Node> startList = map.get(mantra.charAt(i));
            for (int j = 0; j < startList.size(); j++) {
                Node start = startList.get(j);
                int sx = start.x, sy = start.y;
                int num = map.get(mantra.charAt(i + 1)).size();
                int min = Integer.MAX_VALUE;
                //剪枝，相同字母原地踏步。没有这个if会超时
                if (mantra.charAt(i) == mantra.charAt(i + 1)) {
                    min = dp[i + 1][j].d;
                } else {
                    for (int k = 0; k < num; k++) {
                        Node end = dp[i + 1][k];
                        min = Math.min(min, end.d + Math.abs(sx - end.x) + Math.abs(sy - end.y));
                    }
                }
                dp[i][j] = new Node(sx, sy, min);
            }
        }

        //起点在(0,0)
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < map.get(mantra.charAt(0)).size(); j++) {
            min = Math.min(min, dp[0][j].x + dp[0][j].y + dp[0][j].d);
        }
        return min + n;
    }

    class Node {
        int x, y, d;

        public Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

}
