package other;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String s = "YYNY";
    }

    public int pivotInteger(int n) {
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + i;
        }
        for (int i = 1; i <= n; i++) {
            if (sum[i] == sum[n] - sum[i - 1]) {
                return i;
            }
        }
        return -1;
    }

}
