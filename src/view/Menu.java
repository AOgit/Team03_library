package view;

import service.MainService;

import java.util.Scanner;

public class Menu {
    private final MainService service;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(MainService service) {
        this.service = service;
    }

    public void start() {
        showMenu();
    }
    private void showMenu() {
        while (true) {
            System.out.println("Добро пожаловать в меню");
            System.out.println("1. Список всех книг");
            System.out.println("2. Список всех свободных книг");
            System.out.println("3. Список всех отданных читателям книг");
            System.out.println("4. Добавить новую книгу");
            System.out.println("5. Найти книгу по названию");
            System.out.println("6. Найти книгу по автору");
            System.out.println("7. Отдать книгу из библиотеки");
            System.out.println("8. Вернуть книгу в библиотеки");
            System.out.println("9. Вернуть книгу в библиотеки");

            System.out.println("0. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("До свидания!");
                // Завершение работы приложения
                System.exit(0);
            }

            handleMainMenuInput(choice);
        }
    }

    private void handleMainMenuInput(int choice) {
        switch (choice) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
        }
    }

    private void waitRead() {
        System.out.println("\nДля продолжения нажмите Enter...");
        scanner.nextLine();
    }

}
