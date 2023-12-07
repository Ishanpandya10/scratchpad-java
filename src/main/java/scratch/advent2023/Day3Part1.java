package scratch.advent2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day3Part1 { //135155 Too LOW //498559
    //35, 467, 633, 598, 617, 592, 755, 664
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/java/scratch/advent2023/inputfiles/Day3Input.txt"));
        Map<Coordinates, Character> allCordMap = getCoordinatesList(lines);

        List<Coordinates> forSum = getEligibleCords(allCordMap, lines);


        Map<Coordinates, Character> digitCords = forSum.stream()
                .filter(coordinates -> Character.isDigit(allCordMap.get(coordinates)))
                .collect(Collectors.toMap(cord -> cord, allCordMap::get));

        System.out.println("================");
        System.out.println(digitCords);

        List<Integer> toSum = new ArrayList<>();

        for (Map.Entry<Coordinates, Character> val : digitCords.entrySet()) {
            StringBuilder digitStr = new StringBuilder();
            Character currentCordValue = val.getValue();
            Coordinates currentCord = val.getKey();

            //LEFT 1
            if (currentCord.y() > 0) {
                Coordinates leftCord1 = new Coordinates(currentCord.x(), currentCord.y() - 1);
                Character leftChar = allCordMap.get(leftCord1);
                if (Character.isDigit(leftChar)) {
                    digitStr.insert(0, leftChar);
                    //Left 2
                    if (currentCord.y() > 1) {
                        Character leftChar1 = allCordMap.get(new Coordinates(currentCord.x(), currentCord.y() - 2));
                        if (Character.isDigit(leftChar1)) {
                            digitStr.insert(0, leftChar1);
                        }
                    }
                }
            }

            digitStr.append(currentCordValue);

            //Right 1
            if (currentCord.y() < lines.get(0).length() - 1) {
                Character right = allCordMap.get(new Coordinates(currentCord.x(), currentCord.y() + 1));
                if (Character.isDigit(right)) {
                    digitStr.append(right);
                    //Right 1
                    if (currentCord.y() < lines.get(0).length() - 2) {
                        Character right1 = allCordMap.get(new Coordinates(currentCord.x(), currentCord.y() + 2));
                        if (Character.isDigit(right1)) {
                            digitStr.append(right1);
                        }
                    }
                }
            }


            toSum.add(Integer.parseInt(digitStr.toString()));
        }
        System.out.println(toSum);
        System.out.println("SUM: " + toSum.stream().reduce(Integer::sum));

    }

    private static List<Coordinates> getEligibleCords(Map<Coordinates, Character> map, List<String> values) {
        List<Coordinates> forSum = new ArrayList<>();
        for (Map.Entry<Coordinates, Character> mp : map.entrySet()) {
            Coordinates cord = mp.getKey();
            if (isSymbol(mp.getValue())) {
                Coordinates cdL = new Coordinates(cord.x(), cord.y() - 1);
                Coordinates cdR = new Coordinates(cord.x(), cord.y() + 1);
                forSum.add(cdL);
                forSum.add(cdR);

                Coordinates cdT;
                if (cord.x() != 0) {
                    cdT = new Coordinates(cord.x() - 1, cord.y());
                    forSum.add(cdT);
                    if (!Character.isDigit(map.get(cdT))) {
                        //Diagonal top left
                        forSum.add(new Coordinates(cord.x() - 1, cord.y() - 1));
                        //Diagonal top right
                        forSum.add(new Coordinates(cord.x() - 1, cord.y() + 1));
                    }

                }
                Coordinates cdB;
                if (cord.x() != values.size() - 1) {
                    cdB = new Coordinates(cord.x() + 1, cord.y());
                    forSum.add(cdB);
                    if (!Character.isDigit(map.get(cdB))) {
                        //Diagonal bottom left
                        forSum.add(new Coordinates(cord.x() + 1, cord.y() - 1));
                        //Diagonal bottom right
                        forSum.add(new Coordinates(cord.x() + 1, cord.y() + 1));
                    }
                }
            }
        }
        return forSum;
    }

    private static boolean isSymbol(Character value) {
        return !Character.isDigit(value) && !value.equals('.');
        /*char[] charArray = value.toCharArray();
        OptionalInt anyDigit = IntStream.range(0, charArray.length)
                .filter(Character::isDigit)
                .findAny();
        return anyDigit.isEmpty();*/
    }

    private static Map<Coordinates, Character> getCoordinatesList(List<String> values) {
        Map<Coordinates, Character> map = new LinkedHashMap<>();
        for (int x = 0; x < values.size(); x++) {
            String line = values.get(x);
            char[] charArray = line.toCharArray();
            for (int y = 0; y < charArray.length; y++) {
                Coordinates coordinates = new Coordinates(x, y);
                map.put(coordinates, charArray[y]);
            }
        }
        return map;
    }
}

record Coordinates(int x, int y) {
}
