package scratch;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class GreatestDivisor {
    public static void main(String[] args) {
        int start = 1;
        int end = 1000;
        Map<Integer, Integer> divisor = findDivisorsForRange(start, end);

        List<Map.Entry<Integer, Integer>> result = findMaximumDivisors(divisor);
        for (Map.Entry<Integer, Integer> res : result)
            System.out.println("Number: " + res.getKey() + " : Divisors: " + res.getValue());
    }

    private static Map<Integer, Integer> findDivisorsForRange(int start, int end) {
        Map<Integer, Integer> mp = new LinkedHashMap<>();
        for (int i = start; i <= end; i++) {
            List<Integer> divisors = new ArrayList<>();
            if (i > 0) {
                for (int div = 1; div <= i; div++) {
                    if (i % div == 0) {
                        divisors.add(i / div);
                    }
                }
            }
            mp.put(i, divisors.size());
        }
//        System.out.println(mp);
        return mp;
    }

    private static List<Map.Entry<Integer, Integer>> findMaximumDivisors(Map<Integer, Integer> divisor) {

        return divisor.entrySet()
                .stream()
                .collect(groupingBy(Map.Entry::getValue, TreeMap::new, toList()))
                .lastEntry()
                .getValue();
    }

    /*private static Map.Entry<Integer, Integer> sortMapByValue(Map<Integer, Integer> divisor) {
        LinkedHashMap<Integer, Integer> collect = divisor.entrySet()
                .stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (int1, int2) -> int1, LinkedHashMap::new));
        Map.Entry<Integer, Integer> fistDivisor = collect.entrySet().iterator().next();
        return fistDivisor;
    }*/
}
