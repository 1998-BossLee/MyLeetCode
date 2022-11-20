package 不值得记录的简单题;

public class LC1732 {

    public int largestAltitude(int[] gain) {
        int max = -1001;
        int prefix = 0;
        for (int i = 0; i < gain.length; i++) {
            prefix += gain[i];
            System.out.println(prefix);
            max = Math.max(max, prefix);
        }
        return prefix;
    }

}
