package org.example.simple;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

import org.example.Sch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TwoSumTest {

    // O(n) complexity
    public int[] twoSumOn(int[] nums, int target) {
        var map = new HashMap<Integer, Integer>();

        for (int i = 0; i < nums.length; i++) {
            var current = nums[i];
            var search = target - current;
            if (map.containsKey(search)) {
                return new int[]{i, map.get(search)};
            }
            map.put(current, i);
        }
        return new int[]{};
    }

    // O(n^2)
    public int[] twoSumOn2(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            var a = nums[i];
            for (int j = nums.length - 1; j > i; j--) {
                var b = nums[j];
                if (a + b == target)
                    return new int[]{i, j};
            }
        }
        return new int[]{};
    }

    @ParameterizedTest(name = "{index} -> O n given arr: {0} sum: {1} expected: {2}")
    @MethodSource("args")
    void testOn(int[] arr, int target, int[] expected) {
        var result = twoSumOn(arr, target);
        Arrays.sort(expected);
        Arrays.sort(result);
        Assertions.assertArrayEquals(expected, result);
    }

    @ParameterizedTest(name = "{index} -> O n^2 given arr: {0} sum: {1} expected: {2}")
    @MethodSource("args")
    void testOn2(int[] arr, int target, int[] expected) {
        var result = twoSumOn(arr, target);
        Arrays.sort(expected);
        Arrays.sort(result);
        Assertions.assertArrayEquals(expected, result);
    }

    private static Stream<Arguments> args() {
        return Stream.of(
                Arguments.of(new int[] { 3, 3 }, 6, new int[] { 0, 1 }),
                Arguments.of(new int[] { 3, 2, 4 }, 6, new int[] { 1, 2 }),
                Arguments.of(new int[] { 2, 7, 11, 15 }, 9, new int[] { 0, 1 }),
                Arguments.of(new int[] { 2, 3, 6, 7, 11, 15 }, 9, new int[] { 1, 2 }),
                Arguments.of(new int[] { 2, 3, 6, 7, 11, 15 }, 14, new int[] { 1, 4 })
            );
    }

    @ParameterizedTest(name = "stress test O[n], target {1}")
    @MethodSource("stress")
    void stressTest1(int[] arr, int target) {
        Sch.assertExecutionTime(() -> twoSumOn(arr, target), Integer.MAX_VALUE);
    }

    @ParameterizedTest(name = "stress test O[n^2], target {1}")
    @MethodSource("stress")
    void stressTest2(int[] arr, int target) {
        Sch.assertExecutionTime(() -> twoSumOn2(arr, target), Integer.MAX_VALUE);
    }

    private static Stream<Arguments> stress() {
        var size = 100_000_000;
        var array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = ThreadLocalRandom.current().nextInt(0, size);
        }
        return Stream.of(
                Arguments.of(array, ThreadLocalRandom.current().nextInt(size - 50_000, size)));
    }

}
