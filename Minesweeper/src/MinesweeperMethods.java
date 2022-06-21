import java.util.Random;
import java.util.Scanner;

public class MinesweeperMethods {
    private Scanner x = new Scanner(System.in);
    private int size;
    private int difficulty;
    private int coordX;
    private int coordY;
    private int mine = 9;
    private int[][] grid;
    private int[][] playerGrid;
    private final int REVEALED_SPACE = 9;
    public MinesweeperMethods(int sz, int df) {
        size = sz;
        difficulty = df;
        grid = new int[size][size];
        playerGrid = new int[size][size];

    }
    public void printGrid() {
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                System.out.print(playerGrid[i][j] + "  ");
            }
            System.out.println();
            System.out.println();
        }
    }
    private void revealMines() {
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                System.out.print(grid[i][j] + "  ");
            }
            System.out.println();
            System.out.println();
        }
    }
    public void randomMines() {
        Random rand = new Random();
        int upperbound = size;
        int divide;
        if (difficulty == 1) {
            divide = 4;
        }
        else if (difficulty == 2) {
            divide = 3;
        }
        else {
            divide = 2;
        }
        for (int i = 0; i < size * size / divide; i++) {
            int randomInt1 = rand.nextInt(upperbound);
            int randomInt2 = rand.nextInt(upperbound);
            grid[randomInt1][randomInt2] = mine;
        }
    }
    public void firstPick() {
        Random rand = new Random();
        int upperbound = size;
        int randomInt1 = rand.nextInt(upperbound);
        int randomInt2 = rand.nextInt(upperbound);
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0:
                    if (randomInt1 > 0 && randomInt2 > 0 && grid[randomInt1 - 1][randomInt2 - 1] == mine) {
                        playerGrid[randomInt1 - 1][randomInt2 - 1] = grid[randomInt1 - 1][randomInt2 - 1];
                    }
                case 1:
                    if (randomInt2 > 0 && grid[randomInt1][randomInt2 - 1] == mine) {
                        playerGrid[randomInt1][randomInt2 - 1] = grid[randomInt1][randomInt2 - 1];
                    }
                case 2:
                    if (randomInt1 < size - 1 && randomInt2 > 0 && grid[randomInt1 + 1][randomInt2 - 1] == mine) {
                        playerGrid[randomInt1 + 1][randomInt2 - 1] = grid[randomInt1 + 1][randomInt2 - 1];
                    }
                case 3:
                    if (randomInt1 < size - 1 && grid[randomInt1 + 1][randomInt2] == mine) {
                        playerGrid[randomInt1 + 1][randomInt2] = grid[randomInt1 + 1][randomInt2];
                    }
                case 4:
                    if (randomInt1 < size - 1 && randomInt2 < size - 1 && grid[randomInt1 + 1][randomInt2 + 1] == mine) {
                        playerGrid[randomInt1 + 1][randomInt2 + 1] = grid[randomInt1 + 1][randomInt2 + 1];
                    }
                case 5:
                    if (randomInt2 < size - 1 && grid[randomInt1][randomInt2 + 1] == mine) {
                        playerGrid[randomInt1][randomInt2 + 1] = grid[randomInt1][randomInt2 + 1];
                    }
                case 6:
                    if (randomInt1 > 0 && randomInt2 < size - 1 && grid[randomInt1 - 1][randomInt2 + 1] == mine) {
                        playerGrid[randomInt1 - 1][randomInt2 + 1] = grid[randomInt1 - 1][randomInt2 + 1];
                    }
                case 7:
                    if (randomInt1 > 0 && grid[randomInt1 - 1][randomInt2] == mine) {
                        playerGrid[randomInt1 - 1][randomInt2] = grid[randomInt1 - 1][randomInt2];
                    }
            }
        }
        printGrid();
    }
    public void askCoords() {
        System.out.print("Enter column of square you want to check: ");
        coordX = x.nextInt() - 1;
        System.out.print("Enter row of square you want to check: ");
        coordY = x.nextInt() - 1;
        while (coordX > size || coordX < 0 || coordY > size || coordY < 0) {
            System.out.println("Those coords don't exist on the grid");
            System.out.print("Enter column of square you want to check: ");
            coordX = x.nextInt() - 1;
            System.out.print("Enter row of square you want to check: ");
            coordY = x.nextInt() - 1;
        }
        checkSpace();
        //numberClue();
    }
    private void giveClue() {
        int numberOfMines = 0;
                    if (coordX > 0 && coordY > 0 && grid[coordX - 1][coordY - 1] == mine) {
                        numberOfMines++;
                    }
                    if (coordY > 0 && grid[coordX][coordY - 1] == mine) {
                        numberOfMines++;
                    }
                    if (coordX < size - 1 && coordY > 0 && grid[coordX + 1][coordY - 1] == mine) {
                        numberOfMines++;
                    }
                    if (coordX < size - 1 && grid[coordX + 1][coordY] == mine) {
                        numberOfMines++;
                    }
                    if (coordX < size - 1 && coordY < size - 1 && grid[coordX + 1][coordY + 1] == mine) {
                        numberOfMines++;
                    }
                    if (coordY < size - 1 && grid[coordX][coordY + 1] == mine) {
                        numberOfMines++;
                    }
                    if (coordX > 0 && coordY < size - 1 && grid[coordX - 1][coordY + 1] == mine) {
                        numberOfMines++;
                    }
                    if (coordX > 0 && grid[coordX - 1][coordY] == mine) {
                        numberOfMines++;
                    }
        playerGrid[coordX][coordY] = numberOfMines;
    }
    /*private void numberClue() {
        int numOfMines = 0;
        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 0:
                    coordX -= 1;
                    coordY -= 1;
                    giveClue();
                    coordX += 1;
                    coordY += 1;
                case 1:
                    coordY -= 1;
                    giveClue();
                    coordY += 1;
                case 2:
                    coordX += 1;
                    coordY -= 1;
                    giveClue();
                    coordX -= 1;
                    coordY += 1;
                case 3:
                    coordX += 1;
                    giveClue();
                    coordX -= 1;
                case 4:
                    coordX += 1;
                    coordY += 1;
                    giveClue();
                    coordX -= 1;
                    coordY -= 1;
                case 5:
                    coordY += 1;
                    giveClue();
                    coordY -= 1;
                case 6:
                    coordX -= 1;
                    coordY += 1;
                    giveClue();
                    coordX += 1;
                    coordY -= 1;
                case 7:
                    coordX -= 1;
                    giveClue();
                    coordX += 1;

            }
        }
    }
    */
    private void checkSpace() {
        if (grid[coordX][coordY] == mine) {
            System.out.println("That space had a mine, you lose.");
            revealMines();
        }
        else {
            playerGrid[coordX][coordY] = REVEALED_SPACE;
            giveClue();
            //numberClue();
            printGrid();
            askCoords();
        }
    }
}
