package Lab4;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deque<Integer> deque = new ArrayDeque<>();

        System.out.println("Введіть дек з цілими числами (розділяйте їх пробілами):");
        String input = scanner.nextLine();
        String[] numbers = input.split(" ");

        for (String numberStr : numbers) {
            try {
                int number = Integer.parseInt(numberStr);
                deque.addLast(number);
            } catch (NumberFormatException e) {
                System.out.println("Помилка: введено некоректний формат числа. Спробуйте ще раз.");
                scanner.close();
                return;
            }
        }

        while (true) {
            displayMenu(deque, scanner);
        }
    }

    public static void displayMenu(Deque<Integer> deque, Scanner scanner) {
        System.out.println("Меню:");
        System.out.println("1. Виконати операції з деком");
        System.out.println("2. Перевірка введеного рядка на паліндром");
        System.out.println("3. Виконати операції з людьми");
        System.out.print("Виберіть опцію: ");

        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                performDequeOperations(deque, scanner);
                break;
            case 2:
                System.out.print("Введіть текст для перевірки на паліндром: ");
                scanner.nextLine(); // Очистимо буфер введення після попереднього числа
                String input = scanner.nextLine();

                boolean isPalindrome = PalindromeChecker.isCyrillicPalindrome(input);
                if (isPalindrome) {
                    System.out.println(input + " є паліндромом.");
                } else {
                    System.out.println(input + " не є паліндромом.");
                }
                break;
            case 3:
                System.out.println("Виконати операції з чергою");
                System.out.println("Створення черги з 5 людей:");
                PriorityQueue<Person> personPriorityQueue = new PriorityQueue<>(new Comparator<Person>() {
                    @Override
                    public int compare(Person p1, Person p2) {
                        // Порівнюємо за стажем в порядку спадання
                        return Double.compare(p2.getExperience(), p1.getExperience());
                    }
                });

                // Створюємо готові екземпляри Person і додаємо їх до черги
                Person person1 = new Person("Василь", "Сапіжак", 10, 12, 11, 31);
                Person person2 = new Person("Василь", "Чибрик", 8, 15, 12, 28);
                Person person3 = new Person("Владислав", "Степанюк", 12, 22, 11, 24);
                Person person4 = new Person("Юрій", "Литовчук", 5, 17, 10, 23);
                Person person5 = new Person("Андрій", "Шевчук", 6, 19, 11, 26);

                personPriorityQueue.add(person1);
                personPriorityQueue.add(person2);
                personPriorityQueue.add(person3);
                personPriorityQueue.add(person4);
                personPriorityQueue.add(person5);

                System.out.println("Черга з 5 людей створена:");

                for (Person person : personPriorityQueue) {
                    System.out.println(person);
                }
                performPersonQueueOperations(personPriorityQueue, scanner);
                break;


        }
    }

    public static void performPersonQueueOperations(PriorityQueue<Person> personPriorityQueue, Scanner scanner) {
        while (true) {
            System.out.println("Меню операцій з чергою:");
            System.out.println("1. Додати людину в кінець черги");
            System.out.println("2. Видалити людину з початку черги");
            System.out.println("3. Видалити людину за прізвищем");
            System.out.println("4. Вивести чергу на екран");
            System.out.println("5. Повернутися до попереднього меню");
            System.out.print("Виберіть опцію: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Додавання людини в чергу
                    System.out.printf("Введіть ім'я працівника: ");
                    String name = scanner.next();
                    System.out.printf("Введіть прізвище працівника: ");
                    String surname = scanner.next();
                    System.out.printf("Введіть стаж працівника: ");
                    int experience = scanner.nextInt();
                    System.out.printf("Введіть кількість речей у багажі: ");
                    int baggage = scanner.nextInt();
                    System.out.printf("Введіть оцінки з інформатики: ");
                    int mark = scanner.nextInt();
                    System.out.printf("Введіть вік працівника: ");
                    int age = scanner.nextInt();

                    Person newPerson = new Person(name, surname, experience, baggage, mark, age);
                    personPriorityQueue.add(newPerson);

                    System.out.println("Людина додана в чергу.");
                    break;

                case 2:
                    // Видалення людини з черги
                    if (!personPriorityQueue.isEmpty()) {
                        Person removedPerson = personPriorityQueue.poll();
                        System.out.println("Видалена людина з черги: " + removedPerson.getName() + " " + removedPerson.getSurname());
                    } else {
                        System.out.println("Черга з людьми порожня. Неможливо видалити людину.");
                    }
                    break;

                case 3:
                    // Видалення людини за прізвищем
                    System.out.printf("Введіть прізвище людини, яку потрібно видалити: ");
                    String targetSurname = scanner.next();
                    boolean found = false;

                    Iterator<Person> iterator = personPriorityQueue.iterator();
                    while (iterator.hasNext()) {
                        Person person = iterator.next();
                        if (person.getSurname().equals(targetSurname)) {
                            iterator.remove(); // Remove the person with the matching surname
                            found = true;
                            System.out.println("Людина з прізвищем " + targetSurname + " видалена з черги.");
                        }
                    }

                    if (!found) {
                        System.out.println("Людину з прізвищем " + targetSurname + " не знайдено в черзі.");
                    }
                    break;

                case 4:
                    System.out.println("Черга з людьми:");
                    for (Person person : personPriorityQueue) {
                        System.out.println(person);
                    }
                    break;

                case 5:
                    clearConsole(); // Очистити консоль перед поверненням в головне меню
                    return;

                default:
                    System.out.println("Невірний вибір опції. Спробуйте ще раз.");
            }
        }
    }

    public static void performDequeOperations(Deque<Integer> deque, Scanner scanner) {
        while (true) {
            System.out.println("Меню операцій з деком:");
            System.out.println("1. Додати елемент в початок дека");
            System.out.println("2. Додати елемент в кінець дека");
            System.out.println("3. Видалити елемент з початку дека");
            System.out.println("4. Видалити елемент з кінця дека");
            System.out.println("5. Вивести дек на екран");
            System.out.println("6. Повернутися до попереднього меню");
            System.out.print("Виберіть опцію: ");

            int choice = scanner.nextInt();

            switch (choice) {

                case 10:
                    System.out.println("Введіть дек з цілими числами (розділяйте їх пробілами):");
                    String input = scanner.nextLine();
                    String[] numbers = input.split(" ");

                    for (String numberStr : numbers) {
                        try {
                            int number = Integer.parseInt(numberStr);
                            deque.addLast(number);
                        } catch (NumberFormatException e) {
                            System.out.println("Помилка: введено некоректний формат числа. Спробуйте ще раз.");
                            scanner.close();
                            return;
                        }
                    }

                    while (true) {
                        displayMenu(deque, scanner);
                    }
                case 1:
                    int elementToAdd1;
                    do {
                        System.out.print("Введіть ціле число для додавання в початок дека: ");
                        if (scanner.hasNextInt()) {
                            elementToAdd1 = scanner.nextInt();
                            break;
                        } else {
                            System.out.println("Помилка: введено некоректний формат числа. Спробуйте ще раз.");
                            scanner.nextLine();  // Пропустити некоректний ввід
                        }
                    } while (true);
                    deque.addFirst(elementToAdd1);
                    break;
                case 2:
                    int elementToAdd2;
                    do {
                        System.out.print("Введіть ціле число для додавання в кінець дека: ");
                        if (scanner.hasNextInt()) {
                            elementToAdd2 = scanner.nextInt();
                            break;
                        } else {
                            System.out.println("Помилка: введено некоректний формат числа. Спробуйте ще раз.");
                            scanner.nextLine();  // Пропустити некоректний ввід
                        }
                    } while (true);
                    deque.addLast(elementToAdd2);
                    break;
                case 3:
                    if (!deque.isEmpty()) {
                        int removedElement = deque.removeFirst();
                        System.out.println("Видалений елемент з початку дека: " + removedElement);
                    } else {
                        System.out.println("Дек порожній. Неможливо видалити елемент.");
                    }
                    break;
                case 4:
                    if (!deque.isEmpty()) {
                        int removedElement = deque.removeLast();
                        System.out.println("Видалений елемент з кінця дека: " + removedElement);
                    } else {
                        System.out.println("Дек порожній. Неможливо видалити елемент.");
                    }
                    break;
                case 5:
                    System.out.println("Зміст дека: " + deque);
                    break;
                case 6:
                    clearConsole(); // Очистити консоль перед поверненням в головне меню
                    return;
                default:
                    System.out.println("Невірний вибір опції. Спробуйте ще раз.");
            }
        }
    }
}


class PalindromeChecker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть текст для перевірки на паліндром: ");
        String input = scanner.nextLine();

        boolean isPalindrome = isCyrillicPalindrome(input);
        if (isPalindrome) {
            System.out.println(input + " є паліндромом.");
        } else {
            System.out.println(input + " не є паліндромом.");
        }

        scanner.close();
    }

    public static boolean isCyrillicPalindrome(String input) {
        String cleanedInput = input.toLowerCase();
        Deque<Character> deque = new ArrayDeque<>();

        for (char c : cleanedInput.toCharArray()) {
            deque.addFirst(c);
        }

        while (deque.size() > 1) {
            char firstChar = deque.removeFirst();
            char lastChar = deque.removeLast();
            if (firstChar != lastChar) {
                return false;
            }
        }

        return true;
    }
}


class Person {
    private String name;
    private String surname;
    private int experience;
    private int baggage;
    private int mark;
    private int age;

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public int getBaggage() {
        return baggage;
    }
    public int getAge() {
        return age;
    }
    public int getExperience() {
        return experience;
    }
    public int getMark() {
        return mark;
    }

    public Person(String name, String surname, int experience, int baggage, int mark, int age) {
        this.name = name;
        this.surname = surname;
        this.experience = experience;
        this.baggage = baggage;
        this.mark = mark;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Прізвище та Ім'я: " + surname + " " + name +
                ", стаж: " + experience +
                " років, кількість речей у багажі: " + baggage +
                ", оцінка з інформатики: " + mark + ", вік: " + age + ";";
    }
}
