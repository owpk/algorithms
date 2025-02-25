package org.example.middle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.example.utils.ArrayUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * There are a total of numCourses courses you have to take,
 * labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi]
 * indicates that you must take course bi first if you want to take course ai.
 * For example, the pair [0, 1], indicates that to take course 0
 * you have to first take course 1.
 * Return true if you can finish all courses. Otherwise, return false.
 * 
 * Example 1:
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: true
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0. So it is possible.
 * 
 * Example 2:
 * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 * Output: false
 * Explanation: There are a total of 2 courses to take.
 * To take course 1 you should have finished course 0, and to take course 0 you
 * should also have finished course 1. So it is impossible.
 */
public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0)
            return true;

        var nodesCount = numCourses;

        int[][] nodes = prerequisites;
        List<Integer>[] adj = new List[nodesCount];
        var indegree = new int[nodesCount];

        for (int i = 0; i < nodes.length; i++) {
            var node = nodes[i];
            var incomeVertex = node[1];
            var vertex = node[0];

            indegree[vertex]++;

            List<Integer> neighbors = adj[incomeVertex];

            if (neighbors == null) {
                neighbors = new ArrayList<Integer>();
                adj[incomeVertex] = neighbors;
            }

            neighbors.add(vertex);
        }

        var queue = new LinkedList<Integer>();

        for (int i = 0; i < nodesCount; i++)
            if (indegree[i] == 0)
                queue.add(i);

        var visited = 0;

        var sortedTopology = new ArrayList<Integer>();

        while (!queue.isEmpty()) {
            var next = queue.pop();
            var neighbors = adj[next];
            visited++;
            sortedTopology.add(next);
            if (neighbors != null)
                for (var node : neighbors) {
                    indegree[node]--;
                    if (indegree[node] == 0)
                        queue.add(node);
                }
        }
        System.out.println(sortedTopology);

        return visited == numCourses;
    }

    @ParameterizedTest
    @CsvSource({
            "'[[1,4],[2,4],[3,1],[3,2]]', 5, true", // learning path = 4,1,2,3,3
            "'[[1,0]]', 2, true",
            "'[[1,0],[0,1]]', 2, false",
            "'[[1,0]]', 3, true",
            "'[[0,10],[3,18],[5,5],[6,11],[11,14],[13,1],[15,1],[17,4]]', 20, false",
            "'[[1,0],[1,2],[0,1]]', 3, false"
    })
    void test(String prereq, int num, boolean expected) {
        var result = canFinish(num, ArrayUtils.to2dIntArray(prereq));
        Assertions.assertEquals(expected, result);
    }
}
