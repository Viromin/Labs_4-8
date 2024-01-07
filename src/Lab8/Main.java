package Lab8;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.printf("Задайте розмір для масиву N: ");
        int k = scan.nextInt();
        int[] N = new int[k];
        System.out.println("Введіть послідовність цілих чисел N");
        for (int i = 0; i < k; i++) {
            int a = scan.nextInt();
            if (a > 1 && a < 256) {
                N[i] = a;
            }
        }

        System.out.printf("Задайте розмір для масиву M: ");
        int l = scan.nextInt();
        int[] M = new int[l];
        System.out.println("Введіть послідовність цілих чисел M");
        for (int i = 0; i < l; i++) {
            int a = scan.nextInt();
            if (a > 1 && a < 256) {
                M[i] = a;
            }
        }

        System.out.println("Відсортована послідовність N");
        quickSort(N, 0, N.length - 1);
        for (int i = 0; i < k; i++) {
            System.out.printf(N[i] + " ");
        }
        System.out.println();
        binarySearchAndPrint(N, M);
    }

    private static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);

            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }
    private static void binarySearchAndPrint(int[] sortedArray, int[] searchArray) {
        for (int i = 0; i < searchArray.length; i++) {
            int searchResult = binarySearch(sortedArray, searchArray[i]);

            if (searchResult >= 0) {
                System.out.println("Елемент " + searchArray[i] + " знаходиться в масиві на позиції " + (searchResult+1) );
            } else {
                System.out.println("Елемент " + searchArray[i] + " не знайдено в масиві");
            }
        }
    }
    private static int binarySearch(int[] sortedArray, int key) {
        int low = 0;
        int high = sortedArray.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (sortedArray[mid] == key) {
                return mid; // Елемент знайдено, повертаємо індекс
            } else if (sortedArray[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1; // Елемент не знайдено
    }
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;

                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }
}
