package 栈;

import java.util.LinkedList;

public class LC1124 {

    public static void main(String[] args) {

    }


    public int longestWPI(int[] hours) {
        int n = hours.length, ans = 0;
        int[] prefix = new int[n + 1];
        LinkedList<Integer> stack = new LinkedList<>();
        stack.push(0);
        for (int i = 1; i <= n; i++) {
            if (hours[i - 1] > 8) {
                prefix[i] = prefix[i - 1] + 1;
            } else {
                prefix[i] = prefix[i - 1] - 1;
            }
            //单调递减栈。按照 0 -1 -2 -3 -4 -5这样塞进栈里
            if (prefix[i] < prefix[stack.getLast()]) {
                stack.push(i);
            }
        }
        for (int i = n; i > 0; i--) {
            //只要当前的前缀和大于栈顶前缀和即可。前缀和必然会从-5一个一个累加到正数。 -4 -5 -4 -3 -2 -1 0 1 2
            //只要后续prefix[r]>prefix[l]就符合答案。 当然对于2这样的前缀和，直接把前面的都弹出来了。
            while (!stack.isEmpty() && prefix[i] > prefix[stack.getLast()]) {
                ans = Math.max(ans, i - stack.removeLast());
            }
        }
        return ans;
    }

}
