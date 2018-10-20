package HW_1_3;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ForestTracker {
    public char[][] trackForestTerrain(char[][] forest, int years) {
        List<Coordinate> coordinates = new ArrayList<>();

        int[][] forestInts = new int[forest.length][forest[0].length];

        for (int i = 0; i < forest.length; i++) {
            for (int j = 0; j < forest[0].length; j++) {
                if (Character.isLetter(forest[i][j])) {
                    continue;
                }

                forestInts[i][j] = Integer.parseInt(String.valueOf(forest[i][j]));
                coordinates.add(new Coordinate(j, i));
            }
        }


        int loops = years / 10;
        Coordinate curCoord;

        Stack<Coordinate> stack = new Stack<>();
        for (int i = 0; i < loops; i++) {
            for (Coordinate coordinate : coordinates) {
                if (forestInts[coordinate.y][coordinate.x] != 4) {
                    continue;
                }

                if (checkNeighbors(forestInts, coordinate) < 3) {
                    continue;
                }

                stack.push(coordinate);
            }


            for (Coordinate coordinate : coordinates) {
                if (forestInts[coordinate.y][coordinate.x] == 4) {
                    continue;
                }

                forestInts[coordinate.y][coordinate.x] += 1;
            }

            while (!stack.isEmpty()) {
                curCoord = stack.pop();

                forestInts[curCoord.y][curCoord.x] = 3;
            }
        }

        for (Coordinate coordinate : coordinates) {
            forest[coordinate.y][coordinate.x] = Character.forDigit(forestInts[coordinate.y][coordinate.x], 10);
        }

        return forest;
    }

    int checkNeighbors(int[][] matrix, Coordinate coordinate) {
        int validNeighborsCounter = 0;

        int[] deltaX = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] deltaY = {-1, 0, 1, -1, 1, -1, 0, 1};

        int potentialX;
        int potentialY;

        for (int i = 0; i < deltaX.length; i++) {
            potentialX = coordinate.x + deltaX[i];
            potentialY = coordinate.y + deltaY[i];
            if (!(isValidCoordinate(matrix, potentialX, potentialY))) {
                continue;
            }

            if (matrix[potentialY][potentialX] != 4) {
                continue;
            }

            validNeighborsCounter ++;
        }

        return validNeighborsCounter;
    }

    boolean isValidCoordinate(int[][] matrix, int x, int y) {
        return x >= 0 && x < matrix[0].length && y >= 0 && y < matrix.length;
    }
}

class Coordinate {
    int x;
    int y;

    Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
