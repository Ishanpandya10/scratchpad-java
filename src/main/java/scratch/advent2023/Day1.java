package scratch.advent2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Day1 {
    public static void main(String[] args) throws IOException {
        List<String> values = Files.readAllLines(Paths.get("src/main/java/scratch/advent2023/inputfiles/CalibrationValues.txt"));
        List<Integer> numbValue = new ArrayList<>();
        for (String line : values) {
            int number = getFirstAndLastNumber(line);
            numbValue.add(number);
        }
        Integer sum = numbValue.stream().reduce(Integer::sum).get();
        System.out.println(sum);
    }

    private static int getFirstAndLastNumber(String line) {
        line = convertStringNumberToDigits(line);
        char[] charArray = line.toCharArray();
        List<Integer> digits = new ArrayList<>();
        for (char ch : charArray) {
            if (Character.isDigit(ch)) {
                digits.add(Integer.parseInt(String.valueOf(ch)));
            }
        }
        if (digits.isEmpty())
            return 0;
        else if (digits.size() > 1)
            return Integer.parseInt(digits.get(0) + "" + digits.get(digits.size() - 1));
        return Integer.parseInt(digits.get(0) + "" + digits.get(0));
    }

    private static String convertStringNumberToDigits(String line) {
        Map<Integer, String> finalMap = new HashMap<>();
        Map<String, Integer> stringDigitMap = getStringDigitMap();
        char[] lineCharArray = line.toCharArray();

        for (char ch : lineCharArray) {
            if (Character.isDigit(ch)) {
                List<Integer> allIndex = getAllIndex(line, ch + "");
                for (int idx : allIndex) {
                    finalMap.put(idx, String.valueOf(ch));
                }
            }
        }

        for (Map.Entry<String, Integer> entry : stringDigitMap.entrySet()) {
            List<Integer> allIndex = getAllIndex(line, entry.getKey());
            if (!allIndex.isEmpty()) {
                for (int idx : allIndex) {
                    finalMap.put(idx, String.valueOf(entry.getValue()));
                }
            }
        }

        List<String> res = finalMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .toList();

        return String.join("", res);
    }

    private static List<Integer> getAllIndex(String line, String guess) {
        List<Integer> integerList = new ArrayList<>();
        int index = line.indexOf(guess);
        while (index >= 0) {
            integerList.add(index);
            index = line.indexOf(guess, index + 1);
        }
        return integerList;
    }

    private static Map<String, Integer> getStringDigitMap() {
        Map<String, Integer> stringDigit = new LinkedHashMap<>();
        stringDigit.put("one", 1);
        stringDigit.put("two", 2);
        stringDigit.put("three", 3);
        stringDigit.put("four", 4);
        stringDigit.put("five", 5);
        stringDigit.put("six", 6);
        stringDigit.put("seven", 7);
        stringDigit.put("eight", 8);
        stringDigit.put("nine", 9);
        return stringDigit; //55078 //54086 //54623 //54628 //54629 //54610 //54591
    }
}
