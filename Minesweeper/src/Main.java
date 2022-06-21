import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Scanner x = new Scanner(System.in);
        Scanner y = new Scanner(System.in);
        System.out.print("Enter size of grid: ");
        int size = x.nextInt();
        if (size == 0) {
            System.out.println("0 cannot be a size.");
            System.out.print("Enter size of grid: ");
            size = x.nextInt();
        }
        System.out.println("Easy: ≈25% mines \nMedium: ≈33% mines \nHard: ≈50% mines");
        System.out.print("Enter difficulty (1 for easy, 2 for medium, 3 for hard) : ");
        int difficulty = x.nextInt();
        MinesweeperMethods minesweeper = new MinesweeperMethods(size, difficulty);
        minesweeper.randomMines();
        minesweeper.firstPick();
        minesweeper.askCoords();
        System.out.println();
    }
}
