package scratch;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileWordsCount {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("E:\\Projects\\scratchpad\\src\\main\\resources\\README.txt"));

        Map<String, Long> wordCounts = lines.parallelStream()
                .flatMap(line -> Stream.of(line.split("\\s")))
                .map(String::trim) //Remove unnecessary spaces from the word
                .map(String::toLowerCase)
                .map(word -> word.replaceAll("[^A-Za-z0-9]",""))
                .filter(word -> word.length() > 0) //Remove blank words
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        System.out.println(wordCounts);

    }
}
