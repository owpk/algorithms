package org.example.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import org.example.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * Given an array of intervals where
 * intervals[i] = [starti, endi], merge all overlapping intervals,
 * and return an array of the non-overlapping intervals that cover all
 * the intervals in the input.
 * 
 * Example 1:
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * 
 * Example 2:
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 * 
 * Constraints:
 * 0 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 105
 * intervals is sorted by starti in ascending order.
 * newInterval.length == 2
 * 0 <= start <= end <= 105
 */
public class MergeIntervals {

    public int[][] merge(int[][] intervals) {
        var result = new ArrayList<int[]>();

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        int[] last = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= last[1]) { // Есть пересечение
                last[1] = Math.max(last[1], intervals[i][1]); // Обновляем конец
            } else {
                result.add(last);
                last = intervals[i]; // Начинаем новый интервал
            }
        }

        result.add(last);
        return result.toArray(new int[result.size()][]);
    }

    @ParameterizedTest
    @CsvSource({
        "'[[1,4],[0,0]]', '[0,0],[1,4]'",
        "'[[1,4],[0,5]]', '[0,5]'",
        "'[[1,4],[0,4]]', '[0,4]'",
        "'[[1,3],[2,6],[8,10],[15,18]]', '[[1,6],[8,10],[15,18]]'",
        "'[[1,4],[3,8]]', '[[1,8]]'",
        "'[[1,10],[2,15],[8,10],[15,18]]', '[[1,18]]'"
    })
    void test(String arr, String expected) {
        var a = ArrayUtils.to2dIntArray(arr);
        var e = ArrayUtils.to2dIntArray(expected);
        var result = merge(a);

        ArrayUtils.print(result);
        Assertions.assertEquals(e.length, result.length);
    }
}
