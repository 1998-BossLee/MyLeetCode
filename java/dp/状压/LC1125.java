package dp.状压;

import java.util.*;

/**
 * @author: liyangjin
 * @create: 2023-04-08 22:23
 * @Description:
 */
public class LC1125 {

    public static void main(String[] args) {
        System.out.println(1 << 1);
    }

    /**
     * 状压DP
     * n=16，n^16=65536
     * 第i位取0或者1表示当前团队是否有人掌握这门技术
     * dp[i]表示掌握情况为i的最小团队组成，dp[i]=团队组成情况(new ArrayList<>())
     * 时间复杂度 O(m^2 * 2^n)
     * 空间复杂度 O(m * 2^n)
     * 优化成：dp[i]表示掌握情况为i的最小团队人数,dp[i]=人数，但是需要额外开数组记录本次 增加的人员，以便后续追溯添加
     * 时间复杂度 O(m * 2^n)
     * 空间复杂度 O(2^n)
     */
    public int[] smallestSufficientTeam(String[] reqSkills, List<List<String>> people) {
        int n = reqSkills.length, m = people.size();
        Map<String, Integer> skillIdxMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            skillIdxMap.put(reqSkills[i], i);
        }
        //假设n=4 则最大情况为1111=15 1<<4 = 10000，刚好大于15，可以覆盖所以状态
        List<Integer>[] dp = new List[1 << n];
        dp[0] = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            //当前这个人的技能掌握情况
            int curSkill = 0;
            for (String skill : people.get(i)) {
                curSkill |= 1 << skillIdxMap.get(skill);
            }
            for (int cur = 0; cur < dp.length; cur++) {
                //没有到这个状态的，跳过
                if (dp[cur] == null) {
                    continue;
                }
                //把这个人加上后的技能掌握状态
                int next = cur | curSkill;
                //如果还没这种掌握状态 或者 加一个人之后团队人数更少，则可以修改当前状态的团队组成
                if (dp[next] == null || dp[cur].size() + 1 < dp[next].size()) {
                    dp[next] = new ArrayList<>(dp[cur]);
                    dp[next].add(i);
                }
            }
        }
        return dp[dp.length - 1].stream().mapToInt(i -> i).toArray();
    }

    /**
     * 优化成：dp[i]表示掌握情况为i的最小团队人数,dp[i]=人数，但是需要额外开数组记录本次 增加的人员，以便后续追溯添加
     * 时间复杂度 O(m * 2^n)
     * 空间复杂度 O(2^n)
     */
    public int[] smallestSufficientTeam2(String[] reqSkills, List<List<String>> people) {
        int n = reqSkills.length, m = people.size();
        Map<String, Integer> skillIdxMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            skillIdxMap.put(reqSkills[i], i);
        }
        //初始化所以状态的最大值
        int[] dp = new int[1 << n];
        Arrays.fill(dp, m);
        dp[0] = 0;
        //上一步的技能集合
        int[] preSkill = new int[1 << n];
        //最新加入的员工，表示技能情况i是通过加入prePeople[i]形成的
        int[] prePeople = new int[1 << n];
        for (int i = 0; i < m; i++) {
            int curSkill = 0;
            for (String skill : people.get(i)) {
                curSkill |= 1 << skillIdxMap.get(skill);
            }
            for (int cur = 0; cur < dp.length; cur++) {
                int next = cur | curSkill;
                if (dp[cur] + 1 < dp[next]) {
                    dp[next] = dp[cur] + 1;
                    preSkill[next] = cur;
                    prePeople[next] = i;
                }
                //如果还没这种掌握状态 或者 加一个人之后团队人数更少，则可以修改当前状态的团队组成

            }
        }
        List<Integer> res = new ArrayList<>();
        int x = (1 << n) - 1;
        while (x > 0) {
            res.add(prePeople[x]);
            x = preSkill[x];
        }
        return res.stream().mapToInt(i -> i).toArray();
    }

}
