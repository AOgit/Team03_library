package view;

import model.Book;
import service.MainService;
import utils.MyList;

import java.util.Arrays;
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
            System.out.println("8. Вернуть книгу в библиотеку");

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
                for (Book book: service.getAllBooks()) {
                    System.out.println(book);
                }
                waitRead();
                break;
            case 2:
                for (Book book: service.getAvailableBooks()) {
                    System.out.println(book);
                }
                waitRead();
                break;
            case 3:
                for (Book book: service.getBorrowedBooks()) {
                    System.out.println(book);
                }
                waitRead();
                break;
            case 4:
                System.out.println("Регистрация новой книги");
                System.out.println("Введи название:");
                String title = scanner.nextLine();
                System.out.println("Введите автора:");
                String author = scanner.nextLine();
                System.out.println("Введите кол-во страниц:");
                int pages = scanner.nextInt();
                System.out.println("Введите год издания:");
                int year = scanner.nextInt();

                Book book = service.addBook(title, author, year, pages);
                System.out.println("Поздравляем! Добавление книги прошло успешно!");
                System.out.println(book);

                waitRead();
                break;
            case 5:
                System.out.println("Введите частичное/полное название книги");
                String query = scanner.nextLine();
                MyList<Book> books = service.getBooksByTitle(query);
                if (books.isEmpty()) {
                    System.out.println("Книг с таким названием не найдено!");
                    waitRead();
                    break;
                }

                System.out.printf("Кол-во найденных книг: %d\n", books.size());
                for (Book bk: books) {
                    System.out.println(bk);
                }
                waitRead();
                break;
            case 6:
                System.out.println("Введите частичное/полное имя автора");
                String q = scanner.nextLine();
                MyList<Book> bks = service.getBooksByAuthor(q);
                if (bks.isEmpty()) {
                    System.out.println("Книг с таким автором не найдено!");
                    waitRead();
                    break;
                }

                System.out.printf("Кол-во найденных книг: %d\n", bks.size());
                for (Book bk: bks) {
                    System.out.println(bk);
                }
                waitRead();
                break;
        case 7:
                System.out.println("Введите Id книги");
                int id = scanner.nextInt();

                if (service.borrowBook(id)) {
                    System.out.println("Книга отдана читателю!");
                } else {
                    System.out.println("Книга недоступна или отсутствует в библиотечном фонде!");
                }
                waitRead();
                break;
            case 8:
                System.out.println("Введите Id книги");
                int idBook = scanner.nextInt();

                if (service.returnBook(idBook)) {
                    System.out.println("Книга возвращена в библиотеку!");
                } else {
                    System.out.println("Книга c таким id не выдавалась читателю!");
                }
                waitRead();
                break;
        }

    }

    private void waitRead() {
        System.out.println("\nДля продолжения нажмите Enter...");
        scanner.nextLine();
    }

}
