package dfs;

public class LC1780 {

    boolean flag;

    int[] pow = new int[]{1, 3, 9, 27, 81, 243, 729, 2187, 6561, 19683, 59049, 177147, 531441, 1594323, 4782969};

    public boolean checkPowersOfThree(int n) {
        if (n % 3 != 0) {
            n--;
        }
        if (n % 3 != 0) {
            return false;
        }
        flag = false;
        dfs(15, n);
        return flag;
    }

    private void dfs(int idx, int n) {
        if (n == 0) {
            flag = true;
            return;
        }
        if (n < 0 || flag || idx == 0) {
            return;
        }
        dfs(idx - 1, n - pow[idx]);
        dfs(idx - 1, n);
    }

}
