package other;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {

    }

    static int x=0;
    public static void say() {

    }

    public int countBalls(int lowLimit, int highLimit) {
        int max = 0;
        Map<Integer, Integer> idxNumMap = new HashMap<>();
        for (int i = lowLimit; i <= highLimit; i++) {
            int idx = getDigitalSum(i);
            int num = idxNumMap.getOrDefault(idx, 0) + 1;
            max = Math.max(max, num);
            idxNumMap.put(idx, num);
        }
        return max;
    }

    public int getDigitalSum(int x) {
        int sum = 0;
        while (x != 0) {
            sum = sum + x % 10;
            x = x / 10;
        }
        return sum;
    }

}
