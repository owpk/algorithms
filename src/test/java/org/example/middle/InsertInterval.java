package org.example.middle;

import java.util.ArrayList;

import org.example.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * You are given an array of non-overlapping intervals intervals where
 * intervals[i] = [starti, endi] represent the start and the end of the ith
 * interval and intervals is sorted in ascending order by starti.
 * You are also given an interval newInterval = [start, end] that represents the
 * start
 * and end of another interval.
 * Insert newInterval into intervals such that intervals is still
 * sorted in ascending order by starti and intervals still does not have any
 * overlapping intervals (merge overlapping intervals if necessary).
 * Return intervals after the insertion.
 * Note that you don't need to modify intervals in-place.
 * You can make a new array and return it.
 * 
 * Example 1: //
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5] // [[1,4] [7,9]] | [5,6]
 * Output: [[1,5],[6,9]] // [[2,4],[9,10]] | [5,7]
 * 
 * Example 2:
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]],
 * newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps
 * with [3,5],[6,7],[8,10].
 * 
 * PS
 * newInterval.length == 2
 * intervals[i].length == 2
 */
public class InsertInterval {

    // [[1,3] [4,6]] | [2,5]
    // [[1,2] [2,3] [4,5] [5,6]]
    public int[][] insert(int[][] intervals, int[] newInterval) {
        var i = 0;
        var length = intervals.length;
        var result = new ArrayList<int[]>();

        while (i < length && newInterval[0] > intervals[i][1]) {
            result.add(intervals[i]);
            i++;
        }

        while (i < length && newInterval[0] <= intervals[i][1] 
                && newInterval[1] >= intervals[i][0]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        result.add(newInterval);

        while (i < length && newInterval[1] < intervals[i][0]) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

    @ParameterizedTest
    @CsvSource({
        "'[[1,4],[6,8]]', '[3,7]', '[[1,8]]'",
        "'[[1,8]]', '[3,7]', '[[1,8]]'",
        "'[[1,4]]', '[5,7]', '[[1,4][5,7]]'",
        "'[[5,7]]', '[1,4]', '[[1,4][5,7]]'",
        "'[[2,4],[5,6]]', '[5,6]', '[[2,4][5,6]]'",
        "'[[2,4],[5,6],[9,10]]', '[1,8]', '[[1,8][9,10]]'",
    })
    void test(String arr, String interval, String expected) {
        var a = ArrayUtils.to2dIntArray(arr);
        var i = ArrayUtils.toIntArray(interval);
        var e = ArrayUtils.to2dIntArray(expected);
        var result = insert(a, i);

        ArrayUtils.print(result);
        Assertions.assertEquals(e.length, result.length);
    }
}
