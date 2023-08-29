import javafx.util.Pair;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Main {


    /**
     * 往左走x步，其余步数往右走
     * left = sum[startPos] - sum[startPos-x]
     * r = k - 2*x;
     * right = sum[startPos+r] - sum[startPos]
     * <p>
     * 往右走i步，其余步数往左走
     * right = sum[startPos+i] - sum[startPos]
     * r = k - 2*i;
     * left = sum[startPost] - sum[startPos-r]
     */
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int maxIdx = 0;
        for (int[] fruit : fruits) {
            maxIdx = Math.max(maxIdx, fruit[0]);
        }
        int[] sum = new int[maxIdx + 2];
        for (int[] fruit : fruits) {
            sum[fruit[0]] = fruit[1];
        }
        for (int i = 0; i < sum.length - 1; i++) {
            sum[i + 1] += sum[i];
            System.out.print(sum[i] + " ");
        }
        System.out.println();
        int max = 0;
        //先往左再往右
        for (int i = 1; i <= k && startPos - i >= 0; i++) {
            int now = sum[startPos] - ((startPos - i - 1) < 0 ? 0 : sum[startPos - i - 1]);
            int r = k - 2 * i;
            if (r > 0) {
                now += (startPos + r >= maxIdx ? sum[maxIdx] : sum[startPos + r]) - sum[startPos];
            }
            System.out.printf("i=%d now=%d\n", i, now);
            max = Math.max(max, now);
            if (startPos + r >= maxIdx) {
                break;
            }
        }
        System.out.println("---");
        for (int i = 1; i <= k && startPos + 1 + i <= maxIdx; i++) {
            int now = sum[startPos + i] - sum[startPos];
            int r = k - 2 * i;
            if (r > 0) {
                now += sum[startPos] - sum[(startPos - r >= 0) ? startPos - r : 0];
            }
            System.out.printf("i=%d now=%d\n", i, now);
            max = Math.max(max, now);
        }
        return max;

    }

    public int countSeniors(String[] details) {
        int x = 0;
        for (String s : details) {
            if (Integer.valueOf(s.substring(11, 13)) > 60) {
                x++;
            }
        }
        return x;
    }

    public int matrixSum(int[][] a) {
        // n * n *log N
        int n = a.length, m = a[0].length, res = 0;
        for (int i = 0; i < n; i++) {
            Arrays.sort(a[i]);
        }
        for (int j = 0; j < m; j++) {
            int max = -1;
            for (int i = 0; i < n; i++) {
                max = Math.max(max, a[i][j]);
            }
            res += max;
        }
        return res;
    }


    /**
     * 贡献类题目
     * 先排序
     * 每个数，有多少次充当最小值，多少次充当最大值？
     * 假设元素个数位n，当前元素值个数有x个
     * 小于的数量有a个，大于的数量有b个
     */
    public int sumOfPower(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, p = 1000000007;
        long[] powPrefix = new long[n + 1];
        for (int i = 1; i < n; i++) {
            powPrefix[i] = (powPrefix[i - 1] + (long) nums[i - 1] * nums[i - 1]) % p;
        }
        long res = 0;
        for (int i = 0; i < n; i++) {
            //充当最小值 * 好多个大数的平方
            res = (res + (nums[i] * (powPrefix[n] - powPrefix[i + 1])) % p) % p;
        }
        res = res + ((long) nums[0] * ((long) nums[n - 1] * nums[n - 1] % p)) % p;
        return (int) (res % p);
    }


    public int countBeautifulPairs(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                String s = String.valueOf(nums[i]);
                int a = s.charAt(0) - '0';
                s = String.valueOf(nums[i]);
                int b = s.charAt(s.length() - 1) - '0';
                if (gcd(a, b) == 1) {
                    res++;
                }
            }
        }
        return res;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    /**
     * 拆分成单个1的子数组的方式
     * 取决于两个1之间的0分给哪边，两端的0必定会归属于最近的1
     * 1001001
     * 1 00100 1
     * 1 001 001
     * 1 0010 01
     * 10 0100 1
     * 10 010 01
     * 10 01 001
     * <p>
     * 100 100 1
     */
    public int numberOfGoodSubarraySplits(int[] nums) {
        long p = 1000000007;
        int one = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                one++;
            }
        }
        if (one <= 1) {
            return one;
        }
        int[] gap = new int[one - 1];
        int idx = -1, zero = 0;
        long res = 1;
        for (int num : nums) {
            if (num == 0) {
                zero++;
            } else {
                if (idx != -1 && zero != 0) {
                    gap[idx] = zero;
                    res = res * (zero + 1) % p;
                }
                idx++;
                zero = 0;
            }
        }
        return (int) res;
    }

    public List<Integer> survivedRobotsHealths(int[] positions, int[] healths, String directions) {
        int n = positions.length;
        List<Robot> robotList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            robotList.add(new Robot(positions[i], healths[i], directions.charAt(i)));
        }
        Collections.sort(robotList, Comparator.comparingInt(r -> r.pos));
        List<Integer> resList = new ArrayList<>();
        List<Integer> endList = new ArrayList<>();
        Map<Integer, Integer> posHealthMap = new HashMap<>();
        for (Robot robot : robotList) {
            int end = robot.pos;
            if (robot.dir == 'L') {
                end--;
            } else {
                end++;
            }
            endList.add(end);
            Integer health = posHealthMap.get(end);
            if (health == null) {
                posHealthMap.put(end, robot.health);
            } else {
                if (health == robot.health) {
                    posHealthMap.remove(end);
                } else if (health < robot.health) {
                    posHealthMap.put(end, robot.health - 1);
                } else {
                    posHealthMap.put(end, health - 1);
                }
            }
        }
        for (int end : endList) {
            Integer health = posHealthMap.get(end);
            if (health != null && health > 0) {
                resList.add(health);
            }
        }
        return resList;
    }

    class Robot {

        int pos, health;

        char dir;

        public Robot(int pos, int health, char dir) {
            this.pos = pos;
            this.health = health;
            this.dir = dir;
        }
    }



    public static void main(String[] args) {
        int[] a = {2, 4};
    }


}


