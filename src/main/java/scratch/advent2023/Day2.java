package scratch.advent2023;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Day2 {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("src/main/java/scratch/advent2023/inputfiles/Day2Input.txt"));
        List<CubeGame> list = getCubeGameList(lines);
        System.out.println(list);

        //12 red cubes, 13 green cubes, and 14 blue
        /*
        PART 1
        int red = 12;
        int green = 13;
        int blue = 14;*/

        int gameCount = 0;

        for (CubeGame cubeGame : list) {
            List<Game> games = cubeGame.getGames();
            List<Integer> redCount = games.stream()
                    .map(Game::getRedCubeCount)
                    .toList();
            List<Integer> blueCount = games.stream()
                    .map(Game::getBlueCubeCount)
                    .toList();
            List<Integer> greenCount = games.stream()
                    .map(Game::getGreenCubeCount)
                    .toList();

            int redMin = redCount.stream().max(Comparator.naturalOrder()).orElse(1);
            int blueMin = blueCount.stream().max(Comparator.naturalOrder()).orElse(1);
            int greenMin = greenCount.stream().max(Comparator.naturalOrder()).orElse(1);

            int power = redMin * blueMin * greenMin;
            gameCount += power;
            /*
            PART 1
            boolean match = games.stream()
                    .allMatch(game -> game.getRedCubeCount() <= red && game.getBlueCubeCount() <= blue && game.getGreenCubeCount() <= green);

            if(match){
                System.out.println(cubeGame.getGameName());
                gameCount += cubeGame.getGameNumber();
            }*/
        }
        System.out.println(gameCount);
    }

    private static List<CubeGame> getCubeGameList(List<String> lines) {
        List<CubeGame> list = new ArrayList<>();
        for (String line : lines) {
            String[] split = line.split(":");

            String cubeGame = split[0];

            CubeGame cubeGameObj = new CubeGame();
            cubeGameObj.setGameName(cubeGame);

            List<Game> allGame = new ArrayList<>();
            String[] games = split[1].split(";");
            for (String gm : games) {
                Game game = new Game();
                String[] gameColorSplit = gm.split(",");
                game.setGameByColor(gameColorSplit);
                allGame.add(game);
            }
            cubeGameObj.setGames(allGame);
            list.add(cubeGameObj);
        }
        return list;
    }

}

class CubeGame {
    private String gameName;
    private List<Game> games;

    public int getGameNumber() {
        if (gameName.length() > 1) {
            return Integer.parseInt(gameName.substring(gameName.indexOf(" ") + 1));
        } else {
            return 0;
        }
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    @Override
    public String toString() {
        return "CubeGame{" +
                "gameName='" + gameName + '\'' +
                ", games=" + games +
                '}';
    }
}

class Game {
    private String redCube;
    private String blueCube;
    private String greenCube;

    public void setGameByColor(String[] gameColorSplit) {
        for (String gm : gameColorSplit) {
            gm = gm.trim();
            if (gm.contains("red"))
                this.redCube = gm;
            else if (gm.contains("green"))
                this.greenCube = gm;
            else if (gm.contains("blue"))
                this.blueCube = gm;
        }
    }

    public int getRedCubeCount() {
        //4 red
        return getCubeCount(redCube);
    }

    public int getBlueCubeCount() {
        //4 red
        return getCubeCount(blueCube);
    }

    public int getGreenCubeCount() {
        //4 red
        return getCubeCount(greenCube);
    }

    private int getCubeCount(String colorCube) {
        if (Objects.nonNull(colorCube) && colorCube.length() > 1) {
            return Integer.parseInt(colorCube.substring(0, colorCube.indexOf(" ")));
        } else {
            return 0;
        }
    }

    public String getRedCube() {
        return redCube;
    }

    public void setRedCube(String redCube) {
        this.redCube = redCube;
    }

    public String getBlueCube() {
        return blueCube;
    }

    public void setBlueCube(String blueCube) {
        this.blueCube = blueCube;
    }

    public String getGreenCube() {
        return greenCube;
    }

    public void setGreenCube(String greenCube) {
        this.greenCube = greenCube;
    }

    @Override
    public String toString() {
        return "Game{" +
                "redCube='" + redCube + '\'' +
                ", blueCube='" + blueCube + '\'' +
                ", greenCube='" + greenCube + '\'' +
                '}';
    }
}
