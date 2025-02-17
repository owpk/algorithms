package org.example.simple;

import java.util.ArrayList;
import java.util.List;

public class RecursiveFullCombinations {

    public static void main(String[] args) {
        var result = new RecursiveFullCombinations()
            .combinations(new ArrayList<>(List.of(10, 20, 30, 40)));
        result.forEach(it -> System.out.println(it));
    }

    public List<List<Integer>> combinations(List<Integer> values) {
        var result = new ArrayList<List<Integer>>();
        comb(values, new ArrayList<>(), result);
        return result;
    }

    private void comb(List<Integer> values, 
            List<Integer> currentComb, 
            List<List<Integer>> combinations) {

        var iterator = values.iterator();

        while(iterator.hasNext()) {
            var newComb = new ArrayList<Integer>(currentComb);
            var next = iterator.next();
            iterator.remove();
            newComb.add(next);
            comb(new ArrayList<>(values), newComb, combinations);
        }

        if (!currentComb.isEmpty())
            combinations.add(currentComb);
    }
}
