package Lab7;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Введіть кількість рядків матриці: ");
        int r = scanner.nextInt();
        System.out.printf("Введіть кількість стовпців матриці: ");
        int c = scanner.nextInt();
        int[][] A = createAndFillRandomArray(r,c);

        System.out.println("Матриця А:");
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.printf(A[i][j] + " ");
            }
            System.out.println();
        }

        int lastA = 0, lastI = 0, lastJ = 0;
        for (int j = 0; j < c; j++) {
            for (int i = 0; i < r; i++) {
                if (A[i][j] > 0){
                    lastA = A[i][j];
                    lastI = i + 1;
                    lastJ = j + 1;
                }
            }
        }
        System.out.println("Останній додатний елемент матриці А дорівнює " + lastA + ", та знаходиться на координатах: " + lastI + " рядок " + lastJ + " стовпець");
    }

    private static int[][] createAndFillRandomArray(int r, int c) {
        int[][] newArray = new int[r][c];
        Random random = new Random();

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                // Заповнення випадковими числами (від -100 до 100)
                newArray[i][j] = random.nextInt(201) - 100;
            }
        }

        return newArray;
    }
}
