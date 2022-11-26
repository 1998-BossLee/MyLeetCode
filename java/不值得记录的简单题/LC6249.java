package 不值得记录的简单题;

public class LC6249 {

    public int numberOfCuts(int n) {
        if (n == 1) {
            return 0;
        }
        if (n % 2 == 0) {
            return n / 2;
        }
        return n;
    }

}
