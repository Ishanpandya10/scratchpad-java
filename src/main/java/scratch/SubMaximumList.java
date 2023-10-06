package scratch;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SubMaximumList {
    public static void main(String[] args) {
        List<Integer> intList = List.of(7, 7, 1, 1, 1, 2, 2, 3, 3, 3, 3, 4, 5, 6, 6, 6, 7, 3, 4, 4, 1, 1, 1, 1);

        Collection<List<Integer>> values = intList.stream()
                .collect(Collectors.groupingBy(Function.identity()))
                .values();

        Optional<List<Integer>> list = values.stream().max(Comparator.comparingInt(List::size));
        System.out.println(list.get());

    }
}
