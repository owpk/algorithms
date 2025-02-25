package org.example.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.example.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SpiralMatrix {
    private int x;
    private int y;

    public List<SpiralEntry> spiralOrder(int[][] matrix) {
        var sprialEntries = new ArrayList<SpiralEntry>();
        //                                          right           down    left      up
        Directions[] directions = new Directions[]{Directions.RIGHT, Directions.DOWN, Directions.LEFT, Directions.UP};
        int[][] moves = new int[][]{{0, 0}, {0, 0}, {0, 0}, {0, 0}};

        var length = matrix[0].length * matrix.length;
        x = matrix[0].length;
        y = matrix.length;

        var direction = 0;


        while(length > 0) {
            direction = direction % directions.length;
            var move = moves[direction];
            var currentDirection = directions[direction];

            var axios = currentDirection.axios;
            var vector = currentDirection.vector;
            var size = axios == 0 ? x : y;

            var ey = move[0];
            var ex = move[1];

            var nums = new ArrayList<Integer>();

            for (int count = 0; count < size; count++, ey += vector[0], ex += vector[1]) {
                var element = matrix[ey][ex];
                nums.add(element);
                move[0] = ey;
                move[1] = ex;
                length--;
            }

            sprialEntries.add(new SpiralEntry(currentDirection, nums));

            var forwardMove = moves[(direction + 1) % directions.length];
            var forwardDirection = directions[(direction + 1) % directions.length];
            var prevMove = moves[direction - 1 == -1 ? directions.length - 1 : direction - 1];

            // if X 
            if (axios == 0) {
                forwardMove[1] = move[1]; 
                prevMove[1] = 0 - move[1];
                forwardMove[0] = move[0] + forwardDirection.vector[0];
                y--;
            } else { // if Y
                forwardMove[0] = move[0]; 
                prevMove[0] = 0 - move[0];
                forwardMove[1] = move[1]+ forwardDirection.vector[1];
                x--;               
            }
            
            direction++;
        }

        return sprialEntries;
    }

    private record SpiralEntry(Directions dir, List<Integer> result) {

    }

    private enum Directions {
        RIGHT(new int[]{0, 1}, 0),
        DOWN(new int[]{1, 0}, 1),
        LEFT(new int[]{0, -1}, 0),
        UP(new int[]{-1, 0}, 1);

        final int[] vector;
        final int axios;

        Directions(int[] vector, int axios) {
            this.vector = vector;
            this.axios = axios;
        }   
        
    }

    @ParameterizedTest
    @CsvSource({
        "'[[1,2,3,4],[5,6,7,8],[9,10,11,12]]', '[1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7]'",
        // [1, 2,  3,  4]
        // [5, 6,  7,  8]
        // [9, 10, 11, 12]
    })
    void test(String arr, String expected) {
        var eArr = Arrays.stream(ArrayUtils.toArray(expected, Integer.class)).toList();
        var dArr = ArrayUtils.to2dIntArray(arr);
        var result = spiralOrder(dArr);

        Assertions.assertEquals(eArr, result.stream().flatMap(it -> it.result.stream()).toList());
    }
}
