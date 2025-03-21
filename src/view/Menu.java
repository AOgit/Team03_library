package view;

import model.User;
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
            System.out.println("1. Меню книг");
            System.out.println("2. Меню пользователя");
            System.out.println("3. Меню администратора");
            System.out.println("0. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("До свидания!");
                // Завершение работы приложения
                System.exit(0);
            }

            showSubMenu(choice);
        }
    }

    private void showSubMenu(int choice) {
        switch (choice) {
            case 1:
                showBookMenu();
                break;
            case 2:
                showUserMenu();
                break;
            case 3:
                //Todo show admin menu
                break;
            default:
                System.out.println("Сделайте корректный выбор");
                waitRead();
        }
    }

    private void showUserMenu() {
        while (true) {
            System.out.println("Меню пользователя:");
            System.out.println("1. Login");
            System.out.println("2. Регистрация нового пользователя");
            System.out.println("3. Logout (разлогиниться)");
            System.out.println("0. Вернуться в предыдущее меню");

            System.out.println("\nВыберите номер пункта меню");
            int input = scanner.nextInt();
            scanner.nextLine();

            // Прерываю текущий цикл
            if (input == 0) break;

            handleUserMenuInput(input);
        }
    }

    private  void  test() {

    }

    private void handleUserMenuInput(int input) {

        switch (input) {
            case 1:
                // Авторизация
                /*
                1. Запросить у пользователя email и пароль
                2. Передать полученные данные в СЕРВИСНЫЙ слой
                3. Получить ответ от сервисного слоя - прошел ли успешно login
                4. Сообщить результат пользователю
                 */
                break;
            case 2:
                // Регистрация
                /*
                 1. Запросить у пользователя email и пароль
                2. Передать полученные данные в СЕРВИСНЫЙ слой
                3. Получить ответ от сервисного слоя -
                4. Сообщить результат пользователю
                 */
                System.out.println("Регистрация нового пользователя");
                System.out.println("Введи email:");
                String email = scanner.nextLine();

                System.out.println("Введите пароль:");
                String password = scanner.nextLine();

                User user = service.registerUser(email, password);

                if (user == null) {
                    System.out.println("Регистрация провалена");
                } else {
                    System.out.println("Вы успешно зарегистрировались в системе!");
                    // System.out.println(user.getEmail() + " ваш email");
                }

                waitRead();
                break;
            case 3:
                // Logout
                // Есть ли пользователь, который сейчас авторизован.
                if (service.getActiveUser() == null) {
                    System.out.println("Сейчас в системе нет авторизованных пользователей");
                    waitRead();
                    break;
                }
                service.logout();
                System.out.println("Вы вышли из системы");
                waitRead();
                break;

        }
    }


    private void showBookMenu() {
        while (true) {
            System.out.println("Добро пожаловать в меню");
            System.out.println("1. Список всех книг");
            System.out.println("2. Список всех свободных книг");
            System.out.println("3. Найти книгу по названию");
            System.out.println("4. Найти книгу по автору");
            System.out.println("5. Отдать книгу из библиотеки");
            System.out.println("6. Вернуть книгу в библиотеку");
//            System.out.println("7. Список всех отданных читателям книг"); ADMIN
//            System.out.println("8. Добавить новую книгу"); ADMIN
//            System.out.println("9. Редактировать книгу"); ADMIN

            System.out.println("0. Выход");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("До свидания!");
                // Завершение работы приложения
                System.exit(0);
            }

//            handleBookMenuInput(choice);
        }
    }


    private void waitRead() {
        System.out.println("\nДля продолжения нажмите Enter...");
        scanner.nextLine();
    }

}
