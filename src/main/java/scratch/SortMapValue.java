package scratch;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SortMapValue {
    public static void main(String[] args) {
        degreeOfArray(Arrays.asList(1, 2, 1, 3, 2, 5, 5, 5));
    }

    public static int degreeOfArray(List<Integer> arr) {
        Map<Integer, Long> occurrenceMap = arr.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        LinkedHashMap<Integer, Long> collect = occurrenceMap.entrySet().stream()
                .sorted((Map.Entry.<Integer, Long>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (aLong, aLong2) -> aLong, LinkedHashMap::new));


        System.out.println(collect);
        return 1;
    }

}
