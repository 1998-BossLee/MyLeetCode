package 数学;

/**
 * @author: liyangjin
 * @create: 2023-04-28 16:42
 * @Description:
 */
public class LC258 {

    /**
     * 数根
     * 原数: 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30
     * 数根: 1 2 3 4 5 6 7 8 9  1  2  3  4  5  6  7  8  9  1  2  3  4  5  6  7  8  9  1  2  3
     *
     * 1、0的数根是0
     * 2、9的倍数数根是9
     * 3、非9的倍数的树根是%9
     * 结合三者设计公式，先对数-1，再mod9，再+1
     */
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }

}
