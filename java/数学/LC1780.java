package 数学;

/**
 * @author: liyangjin
 * @create: 2025-08-14 10:47
 * @Description:
 */
public class LC1780 {

    /**
     * 不同的3的幂次方能否组成n，考虑3进制
     * 如果n的3进制表示中每一位都小于等于1，则可以。
     * 如果有3进制位上的数为2则不可以例如
     * 18的3进制表示为200，2表示不能用两个3的幂次方来表示。
     * 18 = 2 * 3^2
     * 21的3进制表示为210，2表示不能用两个3的幂次方来表示。
     * 21 = 2 * 3^2 + 1 * 3^0
     */
    public boolean checkPowersOfThree(int n) {
        while (n > 0) {
            if (n % 3 == 2) {
                return false;
            }
            n = n / 3;
        }
        return true;
    }


}
