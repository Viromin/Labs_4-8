package Lab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;

class DuplicateMarker implements Comparable<DuplicateMarker> {
    @Override
    public int compareTo(DuplicateMarker other) {
        return 0;
    }

    @Override
    public String toString() {
        return "DuplicateMarker";
    }
}

class TreeNode<T extends Comparable<T>> {
    T data;
    TreeNode<T> left, right;

    public TreeNode(T data) {
        this.data = data;
        this.left = this.right = null;
    }
}

class BinarySearchTree<T extends Comparable<T>> {
    private TreeNode<T> root;

    public BinarySearchTree() {
        this.root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(T data) {
        root = insertRec(root, data);
    }

    private TreeNode<T> insertRec(TreeNode<T> root, T data) {
        if (root == null) {
            root = new TreeNode<>(data);
            return root;
        }

        if (data.compareTo(root.data) <= 0) {
            root.left = insertRec(root.left, data);
        } else {
            root.right = insertRec(root.right, data);
        }

        return root;
    }

    public void printTree() {
        printTree(root, 0);
    }

    private void printTree(TreeNode<T> root, int level) {
        if (root != null) {
            printTree(root.right, level + 1);
            for (int i = 0; i < level; i++)
                System.out.print("\t");
            System.out.println(root.data);
            printTree(root.left, level + 1);
        }
    }

    public TreeNode<T> search(T key) {
        return searchRec(root, key);
    }

    private TreeNode<T> searchRec(TreeNode<T> root, T key) {
        if (root == null || root.data.compareTo(key) == 0)
            return root;

        if (key.compareTo(root.data) < 0)
            return searchRec(root.left, key);

        return searchRec(root.right, key);
    }

    public T findMax() {
        return findMaxRec(root);
    }

    private T findMaxRec(TreeNode<T> root) {
        if (root == null)
            return null;

        if (root.right == null)
            return root.data;

        return findMaxRec(root.right);
    }

    public BinarySearchTree<T> removeDuplicates() {
        BinarySearchTree<T> newTree = new BinarySearchTree<>();
        root = removeDuplicatesRec(root, newTree);
        return newTree;
    }

    private TreeNode<T> removeDuplicatesRec(TreeNode<T> root, BinarySearchTree<T> newTree) {
        if (root == null)
            return null;

        root.right = removeDuplicatesRec(root.right, newTree);
        root.left = removeDuplicatesRec(root.left, newTree);

        int occurrences = countOccurrences(root, root.data);

        if (occurrences == 1) {
            newTree.insert(root.data);
        }

        return root;
    }

    private int countOccurrences(TreeNode<T> root, T key) {
        if (root == null)
            return 0;

        int count = 0;

        if (key != null && key.equals(root.data))
            count++;

        count += countOccurrences(root.left, key);
        count += countOccurrences(root.right, key);

        return count;
    }

    public int countNodesStartingWithLetter(char letter) {
        return countNodesStartingWithLetterRec(root, letter);
    }

    private int countNodesStartingWithLetterRec(TreeNode<T> root, char letter) {
        if (root == null)
            return 0;

        int count = 0;

        if (root.data.toString().charAt(0) == letter)
            count++;

        count += countNodesStartingWithLetterRec(root.left, letter);
        count += countNodesStartingWithLetterRec(root.right, letter);

        return count;
    }

    public void removeNodesStartingWithLetter(char letter) {
        root = removeNodesStartingWithLetterRec(root, letter);
    }

    private TreeNode<T> removeNodesStartingWithLetterRec(TreeNode<T> root, char letter) {
        if (root == null)
            return null;

        root.left = removeNodesStartingWithLetterRec(root.left, letter);
        root.right = removeNodesStartingWithLetterRec(root.right, letter);

        if (root.data.toString().charAt(0) == letter)
            return null;

        return root;
    }

    public void printDuplicateLetters() {
        System.out.println("\nСимволи які повторюються:");
        printDuplicateLettersRec(root);
    }

    private void printDuplicateLettersRec(TreeNode<T> root) {
        if (root != null) {
            printDuplicateLettersRec(root.left);
            int occurrences = countOccurrences(root, root.data);

            if (occurrences > 1) {
                System.out.println(root.data + " - " + occurrences + " повторення");
            }

            printDuplicateLettersRec(root.right);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        BinarySearchTree<Integer> intTree = new BinarySearchTree<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть цілі числа для побудови двійкового дерева пошуку (введіть 'stop' щоб зупинитися):");

        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                intTree.insert(scanner.nextInt());
            } else {
                String input = scanner.next();
                if (input.equalsIgnoreCase("stop")) {
                    break;
                } else {
                    System.out.println("Invalid input. Please enter an integer or 'stop' to finish.");
                }
            }
        }

        if (!intTree.isEmpty()) {
            System.out.println("Двійкове дерево пошуку:");
            intTree.printTree();

            System.out.print("Введіть число для пошуку: ");

            if (scanner.hasNextInt()) {
                int numberToSearch = scanner.nextInt();
                scanner.nextLine();
                TreeNode<Integer> foundNode = intTree.search(numberToSearch);

                if (foundNode != null) {
                    System.out.println("Вузол що містить число " + numberToSearch + " знайдено в дереві");
                } else {
                    System.out.println("Вузол що містить число " + numberToSearch + " не знайдено в дереві");
                }
            } else {
                System.out.println("Ви ввели неправильне число");
                scanner.nextLine();
            }

            System.out.println("Найбільший елемент: " + intTree.findMax());
        } else {
            System.out.println("Дерево порожнє. Неможливо знайти максимальний елемент.");
            scanner.nextLine();
        }

        BinarySearchTree<Character> charTree = new BinarySearchTree<>();
        System.out.println("Введіть рядок для побудови двійкового дерева пошуку (Натисніть enter щоб зупинитися):");

        String inputLine = scanner.nextLine();
        for (int i = 0; i < inputLine.length(); i++) {
            charTree.insert(inputLine.charAt(i));
        }

        System.out.println("\nДвійкове дерево пошуку символів:");
        charTree.printTree();
        charTree.printDuplicateLetters();
        BinarySearchTree<Character> nonDuplicateTree = charTree.removeDuplicates();
        System.out.println("Дерево з видаленими дублікатами:");
        nonDuplicateTree.printTree();

        BinarySearchTree<String> wordTree = new BinarySearchTree<>();
        try {
            File file = new File( Paths.get("src", "Lab5", "file.txt").toString());
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNext()) {
                String word = fileScanner.next().toLowerCase();
                wordTree.insert(word);
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено. Будь ласка, вкажіть правильний шлях до файлу.");
            return;
        }

        System.out.println("\nДвійкове дерево зі словами:");
        wordTree.printTree();

        System.out.print("Введіть символ для пошуку та видалення вершин: ");
        if (scanner.hasNext()) {
            char letterToSearch = scanner.next().charAt(0);
            System.out.println("Кількість вершин, що починаються з літери '" + letterToSearch + "': " +
                    wordTree.countNodesStartingWithLetter(letterToSearch));

            System.out.println("Видалення вершин, що починаються з літери '" + letterToSearch + "':");
            wordTree.removeNodesStartingWithLetter(letterToSearch);
            wordTree.printTree();
        } else {
            System.out.println("Введення не коректне. Введіть літеру для пошуку та видалення вершин.");
        }

        scanner.close();
    }
}
