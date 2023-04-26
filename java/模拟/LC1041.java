package 模拟;

/**
 * @author: liyangjin
 * @create: 2023-04-11 23:09
 * @Description:
 */
public class LC1041 {

    /**
     * 执行完指令之后，位置在(x,y)
     * 如果在原点，那么显然是循环。 不管方向如何，再走一次都会回到原点。
     * 如果不在原点，需要看方向
     * 如果向北，则再指向一次指令还是向北，但是坐标会有偏移，不会循环。
     * 如果向南，第二次指令方向相反，位移是(-x,-y)，会回到原来的方向
     * 如果向东，每次执行指令，都会右转90度，第一次和第三次位移相反，第二次和第四次位移相反。四次就回到原点。
     * 循环条件是：在原点 或者 方向不向北
     */
    //北东南西
    int[][] d = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public boolean isRobotBounded(String instructions) {
        int dir = 0, x = 0, y = 0;
        for (int i = 0; i < instructions.length(); i++) {
            char a = instructions.charAt(i);
            if (a == 'G') {
                x += d[dir][0];
                y += d[dir][1];
            } else if (a == 'L') {
                dir = (dir - 1 + 4) % 4;
            } else {
                dir = (dir + 1 + 4) % 4;
            }
        }
        return (x == 0 && y == 0) || (dir != 0);
    }


}
