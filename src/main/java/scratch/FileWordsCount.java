package scratch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileWordsCount {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src\\main\\resources\\README.txt"));

        Map<String, Long> wordCounts = lines.parallelStream()
                .flatMap(line -> Stream.of(line.split("\\s")))
                .map(String::trim) //Remove unnecessary spaces from the word
                .map(String::toLowerCase)
                .map(word -> word.replaceAll("[^A-Za-z0-9]", ""))
                .filter(word -> !word.isEmpty()) //Remove blank words
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        LinkedHashMap<String, Long> sortedMap = wordCounts.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o, o2) -> o, LinkedHashMap::new));

        System.out.println(sortedMap);

    }
}
