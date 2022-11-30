package 数学.欧拉筛;

import java.util.Arrays;

public class CL204 {

    /**
     * 小于n的素数数量
     */
    public int countPrimes(int n) {
        int[] primes = eulerPrime(n);
        for (int i = 0; primes[i] < n; i++) {
            System.out.println("i=" + i + " prime=" + primes[i]);
        }
        int cnt = 0;
        for (int i = 0; primes[i] < n && primes[i] != 0; i++) {
            cnt++;
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] primes = eulerPrime(10);
        System.out.println(primes);
    }

    public static int[] eulerPrime(int n) {
        boolean[] vis = new boolean[n + 1];
        Arrays.fill(vis, true);
        int[] primes = new int[n + 1];
        int cnt = 0;
        for (int i = 2; i <= n; i++) {
            if (vis[i]) {
                primes[cnt++] = i;
            }
            for (int j = 0; j < cnt && primes[j] * i <= n; j++) {
                vis[primes[j] * i] = false;
                if (i % primes[j] == 0) {
                    break;
                }
            }
        }
        return primes;
    }

}
