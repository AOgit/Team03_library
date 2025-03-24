package view;

import model.Book;
import model.Role;
import model.User;
import service.MainService;
import utils.ColorMe.Color;
import utils.ColorMe.ColorMe;
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
        // Потом убрать, для тестов выставляю
        service.loginUser("admin@mail.de", "admin");
        service.borrowBook(1);
//        User reader = service.registerUser("reader@mail.ru", "R(12ader")
//        service.loginUser("reader@mail.ru", "R(12ader");
//        service.borrowBook(reader, 1);
        showMenu();
    }
    private void showMenu() {
        while (true) {
            userPrompt();
            System.out.println("=======================================");
            System.out.println(ColorMe.text(Color.PURPLE, "🎉  ДОБРО ПОЖАЛОВАТЬ В БИБЛИОТЕКУ!  🎉"));
            System.out.println("=======================================");

            System.out.println("📚 1️⃣  Меню книг");
            System.out.println("👤 2️⃣  Меню пользователя");

            if (service.isAdmin() || service.isSuperAdmin())
                System.out.println(ColorMe.text(Color.ORANGE, "🔧 3️⃣  Меню администратора"));
            System.out.println("---------------------------------------");
            System.out.println("❌ 0️⃣  Выход");
            System.out.println("=======================================");
            System.out.print("▶️  Введите номер пункта меню: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println(ColorMe.text(Color.GREEN, "👋 До свидания! Спасибо за использование программы."));
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
                System.out.println(ColorMe.text(Color.RED, "⛔ Ошибка: Сделайте корректный выбор!"));
                waitRead();
        }
    }


    private void showUserMenu() {
        while (true) {
            userPrompt();
            System.out.println("=======================================");
            System.out.println("         👤  МЕНЮ ПОЛЬЗОВАТЕЛЯ  👤      ");
            System.out.println("=======================================");
            if (!service.isLoggedIn()) {
                System.out.println("🔑 1️⃣  Войти");
                System.out.println("🆕 2️⃣  Регистрация нового пользователя");
            } else {
                System.out.println("🚪 1️⃣  Выйти из системы");
            }

            System.out.println("---------------------------------------");
            System.out.println("🔙 0️⃣  Вернуться в предыдущее меню");
            System.out.println("=======================================");
            System.out.print("▶️  Введите номер пункта меню: ");
            int input = scanner.nextInt();
            scanner.nextLine();

            // Прерываю текущий цикл
            if (input == 0) break;

            handleUserMenuInput(input);
        }
    }

    private void handleUserMenuInput(int input) {
        switch (input) {
            case 1:
                if (!service.isLoggedIn()) {
                    login();
                } else {
                    logout();
                }
                break;
            case 2:
                if (!service.isLoggedIn())
                    registration();
                break;
            default:
                System.out.println(ColorMe.text(Color.RED, "⛔ Ошибка: Сделайте корректный выбор!"));
                waitRead();
        }
    }

    private void showAdminMenu() {
        while (true) {
            if (!service.isAdmin() && !service.isSuperAdmin()) break;
            userPrompt();

            System.out.println("=======================================");
            System.out.println(ColorMe.text(Color.PURPLE,"     🔧  АДМИНИСТРАТОРСКОЕ МЕНЮ  🔧     "));
            System.out.println("=======================================");
            System.out.println("👥 1️⃣  Список всех пользователей");
            System.out.println("📖 2️⃣  Список всех читателей");
            System.out.println("🚫 3️⃣  Заблокировать / разблокировать пользователя");
            System.out.println("🔄 4️⃣  Изменить роль пользователя");
            System.out.println("🗑️ 5️⃣. Удалить пользователя");
            System.out.println("---------------------------------------");
            System.out.println("📚 6️⃣  Список книг в наличии");
            System.out.println("📜 7️⃣  Список книг на абонементе");
            System.out.println("🔍 8️⃣  Список книг у определенного читателя");
            System.out.println("---------------------------------------");
            System.out.println("➕ 9️⃣    Добавить новую книгу");
            System.out.println("✏️ 1️⃣0️⃣  Редактировать книгу");
            System.out.println("🗑️ 1️⃣1️⃣  Удалить книгу");
            System.out.println("---------------------------------------");
            System.out.println("🔙 0️⃣  Вернуться в предыдущее меню");
            System.out.println("=======================================");
            System.out.print("▶️  Введите номер пункта меню: ");

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
                showAllUsers();
                break;
            case 2:
                showAllReaders();
                break;
            case 3:
                blockUnblokUser();
                break;
            case 4:
                changeUserRole();
                break;
            case 5:
                deleteUser();
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
//
//                if (
//                        service.borrowBook(bookToDelete)) {
//                    System.out.println("Операция провалена");
//
//                    waitRead();
//                    break;
//                }

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
            System.out.println("=======================================");
            System.out.println("        📚 МЕНЮ БИБЛИОТЕКИ 📚         ");
            System.out.println("=======================================");
            System.out.println("1️⃣  📖  Показать все книги");
            System.out.println("2️⃣  📚  Показать доступные книги");
            System.out.println("3️⃣  🔍  Найти книгу по названию");
            System.out.println("4️⃣  ✍️  Найти книгу по автору");
            System.out.println("5️⃣  🎭  Найти книгу по жанру");
            System.out.println("6️⃣  📥  Взять книгу из библиотеки");
            System.out.println("7️⃣  📤  Вернуть книгу в библиотеку");
            System.out.println("=======================================");
//            System.out.println("7. Список всех отданных читателям книг"); ADMIN
//            System.out.println("8. Добавить новую книгу"); ADMIN
//            System.out.println("9. Редактировать книгу"); ADMIN

            System.out.println("0️⃣  ❌  Вернуться в главное меню");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) break;

            handleBookMenuInput(choice);
        }
    }

    private void handleBookMenuInput(int choice) {
        switch (choice) {
            case 1:
                showAllBooks();
                break;
            case 2:
                showAvailableBooks();
                break;
            case 3:
                showBooksByTitle();
                break;
            case 4:
                showBooksByAuthor();
                break;
            case 5:
                showBooksByGenre();
                break;
            case 6:
                borrowBook();
                break;
            case 7:
                returnBook();
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

    private void userPrompt(){
        User activeUser = service.getActiveUser();
        if (activeUser != null)
            System.out.printf("Здравствуйте, %s\n", activeUser.getEmail());
    }

    private void login() {
        System.out.println("Введите email: ");
        String emailUser = scanner.nextLine();

        System.out.println("Введите пароль: ");
        String passwordUser = scanner.nextLine();

        if (!service.loginUser(emailUser, passwordUser)) {
            System.out.println("Ошибка аутентификации!");
            waitRead();
        }
    }

    private void logout() {
        service.logout();
        System.out.println("Вы успешно вышли из системы");
        waitRead();
    }


    private void registration() {
        System.out.println("Регистрация нового пользователя");
        System.out.println("Введите email:");
        String emailInput = scanner.nextLine();

        System.out.println("Введите пароль:");
        String passwordInput = scanner.nextLine();

        User user = service.registerUser(emailInput, passwordInput);

        if (user == null) {
            System.out.println("Регистрация провалена");
        } else {
            System.out.println("Вы успешно зарегистрировались в системе!");
        }
        waitRead();
    }

    private void showAllUsers () {
        System.out.println("Список пользователей библиотеки:");
        MyList<User> users = service.getAllUsers();
        if (!users.isEmpty()) {
            for (User user: users)
                System.out.println(user);
        } else {
            System.out.println("Зарегистрированных пользователей нет");
        }
        waitRead();
    }

    private void showAllReaders () {
        System.out.println("Список читателей библиотеки:");
        MyList<User> readers = service.getAllReaders();
        if (!readers.isEmpty()) {
            for (User reader: readers)
                System.out.println(reader);
        } else {
            System.out.println("Читателей в библиотеке нет ;(");
        }
        waitRead();
    }

   private void blockUnblokUser () {
       System.out.println("Смена статуса пользователя");
       System.out.println("Введите email пользователя, для смены статуса");
       String emailInput = scanner.nextLine();

       User user = service.getUserByEmail(emailInput.trim());
       if (user == null) {
           System.out.println("Пользователя с таким email не существует");
       } else {
           if (user.isBlocked()) {
               System.out.println("Статус пользователя: заблокирован. Разблокировать? Да/Нет");
               String answerInput = scanner.nextLine();
               if (answerInput.trim().equalsIgnoreCase("да"))
                   if (service.unblockUser(user))
                       System.out.println("Пользователь успешно разблокирован");
                   else
                       System.out.println("Статус пользователя не изменен");
           } else {
               System.out.println("Статус пользователя: активный. Заблокировать? Да/Нет");
               String answer = scanner.nextLine();
               if (answer.trim().equalsIgnoreCase("да"))
                   if (service.blockUser(user))
                       System.out.println("Пользователь заблокирован");
                   else
                       System.out.println("Статус пользователя не изменен");
           }
       }
      waitRead();
   }

  private void changeUserRole() {
        if (!service.isSuperAdmin())
            System.out.println("Только пользователь с ролью Супер администраторa имеет право менять роли");
      System.out.println("Изменить роль пользователя");
      System.out.println("Введите email пользователя, которому хотите изменить роль");
      String email = scanner.nextLine();

      User user = service.getUserByEmail(email.trim());
      if (user == null) {
          System.out.println("Пользователя с таким email не существует");
      } else {
          System.out.println("Текущая роль пользователя: " + user.getRole().toString());
          System.out.println("Укажите новую роль: " + Arrays.toString(Role.values()));
          String roleInput = scanner.nextLine();
          try {
              Role role = Role.valueOf(roleInput.toUpperCase()); // Преобразуем в верхний регистр для удобства
              if (service.changeUserRole(user, role))
                  System.out.println("Новая роль успешно установлена");
              else
                  System.out.println("Недостаточно прав для смены роли");
          } catch (IllegalArgumentException e) {
              System.out.println("Ошибка: некорректная роль");
          }
      }

      waitRead();
  }

     private void deleteUser() {
         System.out.println("Введите email пользователя, которого нужно удалить");
         String emailInput = scanner.nextLine();
         User user = service.getUserByEmail(emailInput.trim());
         if (user == null) {
             System.out.println("Пользователя с таким email не существует");
         } else {
             if (service.deleteUser(user))
                 System.out.println("Пользователь успешно удален");
             else
                 System.out.println("Ошибка удаления пользователя!");
         }
         waitRead();
     }

    private void showAllBooks(){
        MyList<Book> allBooks = service.getAllBooks();
        if (allBooks == null) {
            System.out.println("В библиотеке нет ни одной книги!");
        } else {
            System.out.println(allBooks);
        }
        waitRead();
    }

    private void showAvailableBooks() {
        System.out.println("Список всех свободных книг");
        MyList<Book> availableBooks = service.getAvailableBooks();
        if (availableBooks == null) {
            System.out.println("Свободных книг сейчас нет");
            waitRead();
        } else {
            System.out.println(availableBooks);
        }
        waitRead();
    }

    private void showBooksByTitle() {
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
    }

    private void showBooksByAuthor() {
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
    }

    private void showBooksByGenre() {
        System.out.println("Поиск книг по жанру");
        System.out.println("Введите название жанра");
        String genre = scanner.nextLine();
        MyList<Book> booksByGenre = service.getBookByGenre(genre);
        if (booksByGenre == null) {
            System.out.println("Не удалось найти книгу");
        } else {
            System.out.println(booksByGenre);
        }
        waitRead();
    }

    private void borrowBook() {
        System.out.println("Взять книгу из библиотеки");
        System.out.println("Введите id книги, которую хотите взять");
        int id = scanner.nextInt();
        scanner.nextLine();
        Book book = service.getBookById(id);
        if (book != null) {
            System.out.printf("Книга \"%s: %s\" Берём? Да/Нет\n", book.getTitle(), book.getAuthor());
            String answerInput = scanner.nextLine();
            if (answerInput.trim().equalsIgnoreCase("да")) {
                if (!service.borrowBook(id)) {
                    System.out.println("Не удалось взять книгу");
                } else {
                    System.out.println("Вы забрали книгу!");
                }
            }
        }
        waitRead();
    }

    private void returnBook() {
        System.out.println("Вернуть книгу в библиотеку");
        MyList<Book> borrowedBooks = service.getUserBooks();
        if (borrowedBooks.isEmpty()) {
            System.out.println("У Вас нет взятых на абонемент книг");
        } else {
            System.out.println(borrowedBooks);
            System.out.println("Введите № книги, которую хотите вернуть");
            int idBook = scanner.nextInt();
            scanner.nextLine();
            if (!service.returnBook(idBook)) {
                System.out.println("Не удалось вернуть книгу");
            } else {
                System.out.println("Вы вернули книгу!");
            }
        }
        waitRead();
    }
}
