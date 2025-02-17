package org.example;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class ArrayUtils {

    public static int[][] parse2int(String array) {
        var pattern = Pattern.compile("\\[(\\w,?)+\\]");
        var matcher = pattern.matcher(array);
        var result = new ArrayList<Integer[]>();

        while (matcher.find()) {
            var subArr = matcher.group(0);
            Integer[] split = Arrays.stream(subArr.replaceAll("\\[|\\]", "")
                .trim().split(","))
                .map(it -> Integer.parseInt(it))
                .toArray(Integer[]::new);

            result.add(split);
        }

        return result.stream()
            .map(arr -> Arrays.stream(arr).mapToInt(Integer::intValue).toArray())
            .toArray(int[][]::new);
    }

    public static int[] toIntArray(String array) {
        return Arrays.stream(toArray(array, Integer.class))
            .mapToInt(Integer::intValue).toArray();
    }

    public static <T> T[] toArray(String array, Class<T> clazz) {
        if (array == null || array.length() < 2 || array.charAt(0) != '[' || array.charAt(array.length() - 1) != ']') {
            throw new IllegalArgumentException("Invalid array format: " + array);
        }
        
        array = array.substring(1, array.length() - 1).trim();
        if (array.isEmpty()) {
            return (T[]) Array.newInstance(clazz, 0);
        }

        List<T> list = new ArrayList<>();
        String[] elements = array.split(",");
        
        for (String element : elements) {
            element = element.trim();
            if (clazz == Integer.class) {
                list.add(clazz.cast(Integer.parseInt(element)));
            } else if (clazz == Double.class) {
                list.add(clazz.cast(Double.parseDouble(element)));
            } else if (clazz == Boolean.class) {
                list.add(clazz.cast(Boolean.parseBoolean(element.replaceAll("^\"|\"$", ""))));
            } else if (clazz == String.class) {
                list.add(clazz.cast(element.replaceAll("^\"|\"$", "")));
            } else {
                throw new IllegalArgumentException("Unsupported type: " + clazz.getSimpleName());
            }
        }

        T[] resultArray = (T[]) Array.newInstance(clazz, list.size());
        return list.toArray(resultArray);
    }

    public static void main(String[] args) {
        var result = toArray("[\"asd\", \"2\", \"3\"]", String.class);
        System.out.println(Arrays.toString(result));
    }
    
}
