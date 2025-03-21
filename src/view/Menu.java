package view;

import model.Book;
import model.User;
import service.MainService;
import utils.ColorMe.Color;
import utils.ColorMe.ColorMe;
import utils.MyList;

import javax.swing.plaf.synth.SynthOptionPaneUI;
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
            System.out.println(ColorMe.text(Color.PURPLE, "Добро пожаловать в меню"));
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
                showAdminMenu();
                break;
            default:
                System.out.println("Сделайте корректный выбор");
                waitRead();
        }
    }


    private void showUserMenu() {
        while (true) {
            System.out.println("Меню пользователя:");
            System.out.println("1. Войти");
            System.out.println("2. Регистрация нового пользователя");
            System.out.println("3. Выйти из системы");
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
                System.out.println("Вход в систему");
                System.out.println("Введите email: ");
                String emailUser = scanner.nextLine();

                System.out.println("Введите пароль: ");
                String passwordUser = scanner.nextLine();

                if (!service.loginUser(emailUser, passwordUser)) {
                    System.out.println("Такого пользователя в системе нет!");
                } else {
                    System.out.println("Добро пожаловать!");
                }

                waitRead();
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
                System.out.println("Введите email:");
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
            default:
                System.out.println("Сделайте корректный выбор");
                waitRead();

        }
    }

    private void showAdminMenu() {
        while (true) {
            // TODO добавить роль супер админа, который может менять роли всех остальных
            // Alex
            System.out.println("1. Посмотреть список пользователей");
            System.out.println("2. Посмотреть список читателей");
            System.out.println("3. Заблокировать пользователя"); //TODO добавить поле boolean active user
            System.out.println("4. Посмотреть взятые книги у читателя");

            // Kostia
            System.out.println("5. Список всех отданных читателям книг");
            System.out.println("6. Добавить новую книгу");
            System.out.println("7. Редактировать книгу");
            System.out.println("8. Удалить книгу");
            System.out.println("9. Список всех свободных книг");
            System.out.println("0. Вернутся в предыдущее меню");

            System.out.println("\nВыберите номер пункта меню");
            int input = scanner.nextInt();
            scanner.nextLine();

            // Прерываю текущий цикл
            if (input == 0) break;

            handleAdminMenuInput(input);
        }
    }

    private void handleAdminMenuInput(int input) {
        switch (input) {
            case 1:

            case 2:

            case 3:

            case 4:

            case 5:
                // Список всех занятых книг
                System.out.println("Cписок книг у читателей");
                MyList<Book> borrowedBooks = service.getBorrowedBooks();
                if (borrowedBooks == null) {
                    System.out.println("Занятых книг пока нет");

                    waitRead();
                    break;
                }
                System.out.println(borrowedBooks);

                waitRead();
                break;

            case 6:
                System.out.println("Добавление новой книги");
                System.out.println("Введите название книги:");
                String title = scanner.nextLine();

                System.out.println("Введите автора:");
                String author = scanner.nextLine();

                System.out.println("Введите год книги:");
                int year = scanner.nextInt();
                scanner.nextLine();

                System.out.println("Введите количество страниц:");
               int pages = scanner.nextInt();
               scanner.nextLine();

                Book book = service.addBook(title, author, year, pages);
                if (book == null) {
                    System.out.println("Не удалось добавить книгу");
                    break;
                } else {
                    System.out.println("Книга" + book + " успешно добавлена!");
                }

                waitRead();
                break;


            case 7:
                System.out.println("Редактирование книг");
                // TODO отдельное меню редактирования книг (ВОЗМОЖНО) На обсуждение

            case 8:
                System.out.println("Удаление книг");
                System.out.println("Введите id книги, которую хотите удалить из библиотеки");
                int bookToDelete = scanner.nextInt();
                scanner.nextLine();

                if (/*TODO service.geBookById(bookToDelete) == null || */ service.borrowBook(bookToDelete)) {
                    System.out.println("Операция провалена");

                    waitRead();
                    break;
                }

                if (true /*TODO !service.deleteBookById(bookToDelete)*/) {
                    System.out.println("Не удалось удалить эту книгу");

                    break;
                } else {
                    System.out.println("Вы успешно удалили книгу " + bookToDelete);
                }

                waitRead();
                break;


            case 9:
                System.out.println("Список всех свободных книг");

                System.out.println("Cписок книг которые сейчас находятся в библиотеке");
                MyList<Book> availableBooks = service.getAvailableBooks();

                if (availableBooks == null) {
                    System.out.println("Свободных книг сейчас нет");

                    waitRead();
                    break;
                }
                System.out.println(availableBooks);

                waitRead();
                break;

            default:
                System.out.println("Сделайте корректный выбор");
                waitRead();

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
