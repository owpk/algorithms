package org.example.middle;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.stream.Stream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ZeroOneMatrix {

    public int[][] updateMatrix(int[][] mt) {
        var result = new int[mt.length][];
        Queue<Vertex> queue = new ArrayDeque<>();

        for (int y = 0; y < mt.length; y++) {
            result[y] = new int[mt[y].length];

            for (int x = 0; x < mt[y].length; x++) {
                var el = mt[y][x];
                if (el == 0) {
                    queue.add(new Vertex(y, x));
                } else {
                    result[y][x] = -1;
                }
            }
        }

        printArr(result);

        // Direction vectors for moving up, down, left, right
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // BFS traversal
        while (!queue.isEmpty()) {
            var vert = queue.poll();
            int row = vert.y, col = vert.x;

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                if (checkBounds(mt, newRow, newCol) && result[newRow][newCol] == -1) {
                    result[newRow][newCol] = result[row][col] + 1;
                    queue.offer(new Vertex(newRow, newCol));
                }
            }

            printArr(result);
        }

        return result;
    }

    private boolean checkBounds(int[][] mt, int y, int x) {
        return y < mt.length && y >= 0 && x >= 0 && x < mt[y].length;
    }

    private static class Vertex {
        private int x;
        private int y;

        public Vertex(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private void printArr(int[][] mt) {
        var sb = new StringBuilder();
        for (int y = 0; y < mt.length; y++) {
            for (int i = 0; i < mt[y].length; i++) {
                sb.append(String.format("%3d", mt[y][i]));
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    @ParameterizedTest
    @MethodSource("args")
    void test(int[][] mt, int[][] expected) {
        var result = updateMatrix(mt);
        Assertions.assertTrue(Arrays.deepEquals(expected, result));
    }

    private static Stream<Arguments> args() {
        return Stream.of(
            // Arguments.of(
            //     new int[][] {
            //             { 1, 0, 0 },
            //             { 1, 0, 0 },
            //             { 0, 0, 0 }
            //     },
            //     new int[][] {
            //             { 1, 0, 0 },
            //             { 1, 0, 0 },
            //             { 0, 0, 0 }
            //     }),

            // Arguments.of(
            //     new int[][] {
            //             { 0, 0, 0 },
            //             { 0, 1, 0 },
            //             { 1, 1, 1 }
            //     },
            //     new int[][] {
            //             { 0, 0, 0 },
            //             { 0, 1, 0 },
            //             { 1, 2, 1 }
            //     }),

            // Arguments.of(
            //     new int[][] {
            //             { 1, 0, 1, 1, 0, 0, 1, 0, 0, 1 },
            //             { 0, 1, 1, 0, 1, 0, 1, 0, 1, 1 },
            //             { 0, 0, 1, 0, 1, 0, 0, 1, 0, 0 },
            //             { 1, 0, 1, 0, 1, 1, 1, 1, 1, 1 },
            //             { 0, 1, 0, 1, 1, 0, 0, 0, 0, 1 },
            //             { 0, 0, 1, 0, 1, 1, 1, 0, 1, 0 },
            //             { 0, 1, 0, 1, 0, 1, 0, 0, 1, 1 },
            //             { 1, 0, 0, 0, 1, 1, 1, 1, 0, 1 },
            //             { 1, 1, 1, 1, 1, 1, 1, 0, 1, 0 },
            //             { 1, 1, 1, 1, 0, 1, 0, 0, 1, 1 }
            //     },
            //     new int[][] {
            //             { 1, 0, 1, 1, 0, 0, 1, 0, 0, 1 },
            //             { 0, 1, 1, 0, 1, 0, 1, 0, 1, 1 },
            //             { 0, 0, 1, 0, 1, 0, 0, 1, 0, 0 },
            //             { 1, 0, 1, 0, 1, 1, 1, 1, 1, 1 },
            //             { 0, 1, 0, 1, 1, 0, 0, 0, 0, 1 },
            //             { 0, 0, 1, 0, 1, 1, 1, 0, 1, 0 },
            //             { 0, 1, 0, 1, 0, 1, 0, 0, 1, 1 },
            //             { 1, 0, 0, 0, 1, 2, 1, 1, 0, 1 },
            //             { 2, 1, 1, 1, 1, 2, 1, 0, 1, 0 },
            //             { 3, 2, 2, 1, 0, 1, 0, 0, 1, 1 }
            //     }),

            Arguments.of(
                new int[][] {
                        { 1, 1, 0, 1, 1, 1, 1, 1, 1, 1 },
                        { 1, 1, 0, 1, 1, 1, 1, 1, 1, 1 },
                        { 1, 1, 1, 1, 0, 0, 0, 1, 1, 0 },
                        { 1, 1, 1, 1, 1, 1, 0, 0, 1, 0 },
                        { 1, 0, 0, 1, 1, 1, 0, 1, 0, 1 },
                        { 0, 0, 1, 0, 0, 1, 1, 0, 0, 1 },
                        { 0, 1, 0, 1, 1, 1, 1, 1, 1, 1 },
                        { 1, 0, 0, 1, 1, 0, 0, 0, 0, 0 },
                        { 0, 0, 1, 1, 1, 1, 0, 1, 1, 1 },
                        { 1, 1, 0, 0, 1, 0, 1, 0, 1, 1 }
                },
                new int[][] {
                        { 2, 1, 0, 1, 2, 2, 2, 3, 3, 2 },
                        { 2, 1, 0, 1, 1, 1, 1, 2, 2, 1 },
                        { 3, 2, 1, 1, 0, 0, 0, 1, 1, 0 },
                        { 2, 1, 1, 2, 1, 1, 0, 0, 1, 0 },
                        { 1, 0, 0, 1, 1, 1, 0, 1, 0, 1 },
                        { 0, 0, 1, 0, 0, 1, 1, 0, 0, 1 },
                        { 0, 1, 0, 1, 1, 1, 1, 1, 1, 1 },
                        { 1, 0, 0, 1, 1, 0, 0, 0, 0, 0 },
                        { 0, 0, 1, 1, 2, 1, 0, 1, 1, 1 },
                        { 1, 1, 0, 0, 1, 0, 1, 0, 1, 2 }
                }
            )
        );
    }
}
