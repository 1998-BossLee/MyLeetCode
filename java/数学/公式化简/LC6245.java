package 数学.公式化简;

public class LC6245 {

    /**
     * 假设选择的中枢为x
     * x*(x+1)/2 = (x+n)*(n-x-1)/2
     * x*(x+1)/2 = n*(n+1)/2 - x*(x-1)/2
     * x = sqrt((n)*(n+1)/2)
     */
    public int pivotInteger(int n) {
        int sum = n * (n + 1) / 2;
        int x = (int) Math.sqrt(sum);
        return (x * x == sum) ? x : -1;
    }

}
