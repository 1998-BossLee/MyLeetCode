package queue;

import java.util.PriorityQueue;

/**
 * @author: liyangjin
 * @create: 2025-09-08 11:03
 * @Description:
 */
public class LC1792 {

    /**
     * 贪心，放入优等生提高的通过率最大
     * 计算每个班级增加一个优等生后通过率的提升值
     */
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<Node> qp = new PriorityQueue<>((a, b) -> Double.compare(b.improvement, a.improvement));
        for (int[] c : classes) {
            Node node = new Node();
            node.pass = c[0];
            node.total = c[1];
            node.improvement = (double) (node.pass + 1) / (node.total + 1) - (double) node.pass / node.total;
            qp.offer(node);
        }
        while (extraStudents-- > 0) {
            Node node = qp.poll();
            node.pass++;
            node.total++;
            node.improvement = (double) (node.pass + 1) / (node.total + 1) - (double) node.pass / node.total;
            qp.offer(node);
        }
        double ans = 0;
        for (Node node : qp) {
            ans += (double) node.pass / node.total;
        }
        return ans / classes.length;
    }


    class Node {
        int pass;
        int total;
        double improvement;
    }

}
