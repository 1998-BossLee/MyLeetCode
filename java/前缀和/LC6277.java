package 前缀和;

public class LC6277 {

    public static int[][] onesMinusZeros(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] row = new int[n][2];
        int[][] col = new int[m][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    row[i][0]++;
                } else {
                    row[i][1]++;
                }
            }
        }
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                if (grid[i][j] == 0) {
                    col[j][0]++;
                } else {
                    col[j][1]++;
                }
            }
        }
        int[][] diff = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                diff[i][j] = row[i][1] + col[j][1] - row[i][0] - col[j][0];
            }
        }
        return diff;
    }

}
