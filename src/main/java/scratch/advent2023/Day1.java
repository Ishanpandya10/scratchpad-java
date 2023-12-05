package scratch.advent2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
        char[] charArray = line.toCharArray();
        List<Integer> digits = new ArrayList<>();
        for(char ch: charArray){
            if(Character.isDigit(ch)){
                digits.add(Integer.parseInt(String.valueOf(ch)));
            }
        }
        if(digits.isEmpty())
            return 0;
        else if(digits.size() > 1)
            return Integer.parseInt(digits.get(0) + "" + digits.get(digits.size() - 1));
        return Integer.parseInt(digits.get(0) + "" + digits.get(0));
    }
}
