package org.example.middle;

import static org.mockito.ArgumentMatchers.endsWith;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

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
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;
        
        // Добавляем все интервалы, которые заканчиваются до начала newInterval
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }
        
        // Объединяем интервалы, которые пересекаются с newInterval
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);
        
        // Добавляем оставшиеся интервалы
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }
        
        // Преобразуем список в массив и возвращаем
        return result.toArray(new int[result.size()][]);
    }
}
