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
            System.out.println("4. Разблокировать пользователя");
            System.out.println("5. Посмотреть взятые книги у читателя");

            // Kostia
            System.out.println("6. Список всех отданных читателям книг");
            System.out.println("7. Добавить новую книгу");
            System.out.println("8. Редактировать книгу");
            System.out.println("9. Удалить книгу");
            System.out.println("10. Список всех свободных книг");
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
                System.out.println("Посмотреть список пользователей");

                MyList<User> allUsers = null; // TODO service.getAllUsers();

                if (allUsers == null) {
                    System.out.println("Зарегистрированных пользователей нет");
                } else {
                    System.out.println(allUsers);
                }

                waitRead();
                break;

            case 2:
                System.out.println("Посмотреть список читателей");

                MyList<User> allReaders = null; // TODO service.geAllReaders();

                if (allReaders == null) {
                    System.out.println("Нет ни одного читателя");
                } else {
                    System.out.println(allReaders);
                }

                waitRead();
                break;

            case 3:
                System.out.println("Заблокировать пользователя");

                System.out.println("Введите email пользователя, которого хотите заблокировать");
                String emailForBlock = scanner.nextLine();

                if (true /* TODO service.getUserByEmail(email) == null*/) {
                    System.out.println("Такого пользователя нет!");
                } else if (true /* TODO service.blockUser(emailForBlock)*/) {
                    System.out.println("Не удалось заблокировать пользователя!");
                } else {
                    System.out.println("Пользователь " + emailForBlock + " успешно заблокирован!");
                }

                waitRead();
                break;
            case 4:
                System.out.println("Разблокировать пользователя");

                System.out.println("Введите email пользователя, которого хотите заблокировать");
                String emailForActive = scanner.nextLine();

                if (true /* TODO service.getUserByEmail(email) == null*/) {
                    System.out.println("Такого пользователя нет!");
                } else if (true /* TODO service.unblockUser(emailForBlock)*/) {
                    System.out.println("Не удалось разблокировать пользователя!");
                } else {
                    System.out.println("Пользователь " + emailForBlock + "  разблокирован!");
                }

                waitRead();
                break;


            case 5:
                System.out.println("Посмотреть взятые книги у читателя");

                System.out.println("Введите email пользователя, у которого нужно посмотреть книги");
                String email = scanner.nextLine();

                MyList<Book> booksByReader = null; // TODO service.getUserBooks(email);

                if (true /* TODO service.getUserByEmail(email) == null*/) {
                    System.out.println("Такого пользователя нет!");
                } else if (booksByReader == null) {
                    System.out.println("У этого читателя нет книг");
                } else {
                    System.out.println(booksByReader);
                }

                waitRead();
                break;

            case 6:
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

            case 7:
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

                System.out.println("Введите жанр:");
                String genre = scanner.nextLine();

                Book book = service.addBook(title, author, year, pages, genre);
                if (book == null) {
                    System.out.println("Не удалось добавить книгу");
                    break;
                } else {
                    System.out.println("Книга" + book + " успешно добавлена!");
                }

                waitRead();
                break;


            case 8:
                System.out.println("Редактирование книг");
                // TODO отдельное меню редактирования книг (ВОЗМОЖНО) На обсуждение

            case 9:
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


            case 10:
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
            System.out.println("Меню книг");
            System.out.println("1. Список всех книг");
            System.out.println("2. Список всех свободных книг");
            System.out.println("3. Найти книгу по названию");
            System.out.println("4. Найти книгу по автору");
            System.out.println("5. Найти книгу по жанру");
            System.out.println("6. Взять книгу из библиотеки");
            System.out.println("7. Вернуть книгу в библиотеку");
//            System.out.println("7. Список всех отданных читателям книг"); ADMIN
//            System.out.println("8. Добавить новую книгу"); ADMIN
//            System.out.println("9. Редактировать книгу"); ADMIN
            System.out.println("0. Вернуться в предыдущее меню");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("До свидания!");
                // Завершение работы приложения
                System.exit(0);
            }

            handleBookMenuInput(choice);
        }
    }

    private void handleBookMenuInput(int choice) {
        switch (choice) {
            case 1:
                MyList<Book> allBooks = service.getAllBooks();

                if (allBooks == null) {
                    System.out.println("В библиотеке нет ни одной книги!");
                } else {
                    System.out.println(allBooks);
                }

                waitRead();
                break;

            case 2:
                System.out.println("Список всех свободных книг");

                MyList<Book> availableBooks = service.getAvailableBooks();

                if (availableBooks == null) {
                    System.out.println("Свободных книг сейчас нет");

                    waitRead();
                    break;
                }
                System.out.println(availableBooks);

                waitRead();
                break;

            case 3:
                System.out.println("Поиск книг по названию");
                System.out.println("Введите название книги, которую хотите найти");
                String title = scanner.nextLine();

                MyList<Book> booksByTitle = service.getBooksByTitle(title);

                if (booksByTitle == null) {
                    System.out.println("Не удалось найти книгу");
                } else {
                    System.out.println(booksByTitle);
                }

                waitRead();
                break;

            case 4:
                System.out.println("Поиск книг по автору");
                System.out.println("Введите фамилию автора");
                String author = scanner.nextLine();

                MyList<Book> booksByAuthor = service.getBooksByAuthor(author);

                if (booksByAuthor == null) {
                    System.out.println("Не удалось найти книгу");
                } else {
                    System.out.println(booksByAuthor);
                }

                waitRead();
                break;
            case 5:
                System.out.println("Поиск книг по жанру");
                System.out.println("Введите название жанра");
                String genre = scanner.nextLine();

                MyList<Book> booksByGenre = service.getBooksByAuthor(genre);

                if (booksByGenre == null) {
                    System.out.println("Не удалось найти книгу");
                } else {
                    System.out.println(booksByGenre);
                }

                waitRead();
                break;
            case 6:
                System.out.println("Взять книгу из библиотеки");

                System.out.println("Введите id книги, которую хотите взять");
                int id = scanner.nextInt();
                scanner.nextLine();

                if (!service.borrowBook(id)) {
                    System.out.println("Не удалось взять книгу");
                } else {
                    System.out.println("Вы забрали книгу!");
                }

                waitRead();
                break;

            case 7:
                System.out.println("Вернуть книгу в библиотеку");

                System.out.println("Введите id книги, которую хотите вернуть");
                int idBook = scanner.nextInt();
                scanner.nextLine();

                if (!service.returnBook(idBook)) {
                    System.out.println("Не удалось вернуть книгу");
                } else {
                    System.out.println("Вы вернули книгу!");
                }

                waitRead();
                break;

            default:
                System.out.println("Сделайте корректный выбор");
                waitRead();
        }
    }


    private void waitRead() {
        System.out.println("\nДля продолжения нажмите Enter...");
        scanner.nextLine();
    }

}
