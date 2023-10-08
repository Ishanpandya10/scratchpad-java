package scratch;

import java.util.ArrayList;
import java.util.List;

public class MazeProblem {

    static String maze = """
            xxxx0xxxxxxxxxxxxxxxxxxxxxx
            xxxx0xxxxx0000xxxxxxxxxxxxx
            xxxx0000xx0xxxxxxxxxxxxxxxx
            xxxx0xx00000xxxxxxxxxxxxxxx
            xxxx0xx0xxxxxxxxxxxxxxxxxxx
            xx000xxx0000xxxxxxxxxxxxxxx
            xxxx00000xx0xxxxxxxxxxxxxxx
            xxxxxxxx00xxxxxxxxxxxxxxxxx
            xxxxxxxxx0xxxxxxxxxxxxxxxx0
                 
            """;

    public static void main(String[] args) {

        char[][] mazeValues = getMazeValuesAs2DArray();

        List<Coordinates> coordinates = getCoordinatesToExitMaze(mazeValues);
        System.out.println(coordinates);
    }

    private static List<Coordinates> getCoordinatesToExitMaze(char[][] mazeValues) {
        List<Coordinates> coordinates = new ArrayList<>();

        for (int xAxis = 0; xAxis < mazeValues.length; xAxis++) {
            for (int yAxis = 0; yAxis < mazeValues[xAxis].length; yAxis++) {
                if (mazeValues[xAxis][yAxis] == '0') {
                    if ((xAxis + 1 < mazeValues.length)) {
                        if ((mazeValues[xAxis + 1][yAxis] == '0')) {
                            Coordinates coord = new Coordinates(xAxis, yAxis);
                            coordinates.add(coord);
                            break;
                        }
                    } else {
                        Coordinates coord = new Coordinates(xAxis, yAxis);
                        coordinates.add(coord);
                        break;
                    }
                }
            }
        }
        if(coordinates.size() < mazeValues.length){
            System.out.println("Impossible to get out of the maze");
        }
        return coordinates;
    }

    private static char[][] getMazeValuesAs2DArray() {

        String[] split = maze.split("\n");
        char[][] mazeValues = new char[split.length][];

        for (int i = 0; i < split.length; i++) {
            String line = split[i];
            char[] charArray = line.toCharArray();
            mazeValues[i] = charArray;
        }
        return mazeValues;
    }
}

record Coordinates(int x, int y) {
}
