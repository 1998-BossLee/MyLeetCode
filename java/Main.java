import java.util.*;

// 本题为考试单行多行输入输出规范示例，无需提交，不计分。
public class Main {

    static int[] p;
    static int n, sum, avg;
    static int[] score;

    static boolean flag = false;

    public static void main(String[] args) throws Exception{
        String s = "";
        try {
            Scanner scan = new Scanner(System.in);
            n = scan.nextInt();
            s = s + n;
            p = new int[n];
            for (int i = 0; i < n; i++) {
                p[i] = scan.nextInt();
                s = s + "  " + p[i];
                sum = sum + p[i];
            }
            Arrays.sort(p);
            //0 1 2 3 4
            for (int i = 0; i < n / 2; i++) {
                int temp = p[i];
                p[i] = p[n - 1 - i];
                p[n - 1 - i] = temp;
            }
            //枚举得分人数，从大到小，则平均分是从小到大，第一个dfs符合条件直接退出
            for (int i = n; i >= 1 && !flag; i--) {
                if (sum % i != 0) {
                    continue;
                }
                avg = sum / i;
                score = new int[i];
                dfs(0);
            }
            System.out.println(avg);
        } catch (Exception e) {
            throw new Exception(s);
        }
    }

    private static void dfs(int idx) {
        if (flag) {
            return;
        }
        if (idx == n) {
            flag = true;
            return;
        }
        //第idx个得分应该分给谁？
        for (int i = 0; i < score.length; i++) {
            if (score[i] + p[idx] <= avg) {
                score[i] += p[idx];
                dfs(idx + 1);
                score[i] -= p[idx];
            }
        }
    }

}

/**
 * 有得分的队员都是MVP时最少的MVP得分
 * 有得分的队员是几个？ x
 * 假设x=1，则全部得分加起来就行。
 * 总分是sum，必须满足sum%x=0，avg=sum/x
 * 单个得分最大不能超过avg。组合过程不能超过avg
 * 枚举x和avg是一样的
 * 判断是否符合条件，回溯：把当前得分派发给谁？
 */

