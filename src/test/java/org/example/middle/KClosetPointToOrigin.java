package org.example.middle;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Given an array of points where points[i] = [xi, yi]
 * represents a point on the X-Y plane and an integer k, return the k closest
 * points to the origin (0, 0).
 * 
 * The distance between two points on the X-Y plane is the
 * Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 * 
 * You may return the answer in any order. The answer is guaranteed to
 * be unique (except for the order that it is in).
 * 
 * Input: points = [[1,3],[-2,2]], k = 1
 * Output: [[-2,2]]
 * Explanation:
 * The distance between (1, 3) and the origin is sqrt(10).
 * The distance between (-2, 2) and the origin is sqrt(8).
 * Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
 * We only want the closest k = 1 points from the origin, so the answer is just
 * [[-2,2]].
 * 
 * Example 2:
 * Input: points = [[3,3],[5,-1],[-2,4]], k = 2
 * Output: [[3,3],[-2,4]]
 * Explanation: The answer [[-2,4],[3,3]] would also be accepted.
 */
public class KClosetPointToOrigin {

    public int[][] kClosest(int[][] points, int k) {
        k = Math.min(k, points.length);

        var map = new PriorityQueue<Ponit>();

        for (int y = 0; y < points.length; y++) {
            var point = new Ponit(points[y]);
            map.add(point);
        }

        var result = new int[k][2];

        var size = map.size();

        for (int i = 0; i < k && i < size; i++) {
            var point = map.poll().point;
            result[i] = point;
        }

        return result; 
    }

    @ParameterizedTest
    @MethodSource("args")
    void test(int[][] points, int k, int[][] expected) {
        var result = kClosest(points, k);
        Assertions.assertTrue(Arrays.deepEquals(expected, result));
    }

    private static Stream<Arguments> args() {
        return Stream.of(
            Arguments.of(new int[][]{{3,3},{5,-1},{-2,4}}, 2, new int[][]{{3,3},{-2,4}}),
            Arguments.of(new int[][]{{1,3},{-2,2}}, 1, new int[][]{{-2,2}})
        );
    }

    private static class Ponit implements Comparable<Ponit> {
        private int[] point;
        private double distance;

        public Ponit(int[] point) {
            this.point = point;
            this.distance = Math.sqrt(Math.pow(point[0], 2) + Math.pow(point[1], 2));
        }

        @Override
        public int compareTo(Ponit o) {
            return Double.compare(this.distance, o.distance);
        }

        @Override
        public String toString() {
            return String.format("[%d, %d]", point[0], point[1]);
        }
    }
}
