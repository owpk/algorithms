package org.example.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * You are a product manager and currently leading a team to develop a new
 * product.
 * Unfortunately, the latest version of your product fails the quality check.
 * Since each version is developed based on the previous version, all the
 * versions after
 * a bad version are also bad.
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first
 * bad one, which causes all the following ones to be bad.
 * You are given an API bool isBadVersion(version) which returns whether version
 * is bad.
 * Implement a function to find the first bad version. You should minimize the
 * number of calls to the API.
 * 
 * Example 1:
 * Input: n = 5, bad = 4
 * Output: 4
 * Explanation:
 * call isBadVersion(3) -> false
 * call isBadVersion(5) -> true
 * call isBadVersion(4) -> true
 * Then 4 is the first bad version.
 * 
 * Example 2:
 * Input: n = 1, bad = 1
 * Output: 1
 * 
 */
public class FirstBadVersion {
    private List<Boolean> arr = new ArrayList<>();

    public static void main(String[] args) {
        var sol = new FirstBadVersion();
        int size = 1;
        sol.init(size, 0);
        System.out.println(sol.arr.toString());
        var result = sol.firstBadVersion(size);
        System.out.println(result);
    }

    public int firstBadVersion(int n) {
        return binarySearch(0, n, false) + 1;
    }

    private int binarySearch(int left, int right, boolean lastResult) {
        var mid = left + (right - left) / 2;

        var apiCall = isBadVersion(mid);

        if (mid == 0 && !apiCall && lastResult)
            return 1;
        else if (mid == 0 && apiCall && lastResult)
            return 0;
        

        if (mid != 0 && left < right) {
            if (apiCall) {
                return binarySearch(left, mid - 1, apiCall);
            } else {
                return binarySearch(mid + 1, right, apiCall);
            }
        } else return apiCall ^ lastResult ? mid : -1;
    }

    private void init(int size, int badFrom) {
        if (arr.isEmpty()) {
            for (int i = 0; i < size; i++) {
                if (i >= badFrom) {
                    arr.add(true);
                } else
                    arr.add(false);
            }
        }
    }

    private boolean isBadVersion(int n) {
        return arr.get(n);
    }
}
