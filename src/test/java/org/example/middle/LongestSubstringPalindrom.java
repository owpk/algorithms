package org.example.middle;

import java.util.Arrays;
import java.util.HashMap;

public class LongestSubstringPalindrom {

    private String longestPalindrom(String s) {
        HashMap<Character, String> map = new HashMap<>();
        int[] cacheIdx = new int[('z' - 'A') + 1];
        Arrays.fill(cacheIdx, -1);

        for (int i = 0; i < s.length(); i++) {
            var ch = s.charAt(i);
            var lastIdx = cacheIdx['z' - ch];

            cacheIdx['z' - s.charAt(i)] = i;

            int j = i;
            if (lastIdx != -1) {
                var middle = 0;
                var bias = 0;

                if (i - lastIdx == 1) {
                    middle = lastIdx;
                    bias = 1;
                } else if (i - lastIdx == 2) {
                    middle = lastIdx + 1;
                } else continue;

                var palindrom = new StringBuilder();

                for(; j < s.length(); j++) {
                    var front = s.charAt(j);
                    cacheIdx['z' - s.charAt(j)] = j;
                    var backwardIdx = (middle - (j - middle)) + bias;
                    if (backwardIdx >= 0) {
                        var back = s.charAt(backwardIdx);
                        if (front == back)
                            palindrom.append(front);
                    } else break;
                }

                var _f = palindrom.toString();
                var _b = palindrom.reverse().toString();
                var result = _b + _f;
                System.out.println(result);
                map.put((char) i, result);
            }
        }

        return s;
    }

    public static void main(String[] args) {
        new LongestSubstringPalindrom().longestPalindrom("fgf");
    }
}
