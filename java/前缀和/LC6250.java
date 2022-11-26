package 前缀和;

public class LC6250 {

    /**
     * 前缀和，注意下标要开到len+1，可以在第len小时关门
     */
    public int bestClosingTime(String customers) {
        int len = customers.length();
        int[] preY = new int[len + 1];
        int[] preN = new int[len + 1];
        int y = 0, n = 0;
        for (int i = 0; i < len; i++) {
            if (customers.charAt(i) - 'Y' == 0) {
                y++;
            } else {
                n++;
            }
            preY[i] = y;
            preN[i] = n;
        }
        preY[len] = preY[len - 1];
        preN[len] = preN[len - 1];
        int minHour = len + 1, minCost = len + 1;
        for (int i = 0; i < len + 1; i++) {
            int cost;
            if (i == 0) {
                cost = preY[len - 1];
            } else {
                cost = (preN[i - 1]) + (preY[len] - preY[i - 1]);
            }
            if (cost < minCost) {
                minHour = i;
                minCost = cost;
            }
        }
        return minHour;
    }

}
