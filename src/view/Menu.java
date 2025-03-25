package view;

import model.Book;
import model.Role;
import model.User;
import service.MainService;
import utils.ColorMe.Color;
import utils.ColorMe.ColorMe;
import utils.CreditsUpwards;
import utils.MyList;

import java.awt.*;
import java.awt.event.KeyEvent;
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
//        service.loginUser("reader@mail.ru", "R(12ader");
//        service.loginUser("superAdmin@mail.de", "superAdmin");
//        service.borrowBook(1);
//        User reader = service.registerUser("reader@mail.ru", "R(12ader")
//        service.loginUser("reader@mail.ru", "R(12ader");

        showMenu();
    }
    private void showMenu() {
        while (true) {
            clearConsole();
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

            int choice = checkIntInput();

            if (choice == 0) {
                clearConsole();
//                System.out.println(ColorMe.text(Color.GREEN, "👋 До свидания! Спасибо за использование программы."));
                CreditsUpwards.credits();
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
                if (service.isAdmin() || service.isSuperAdmin()) { showAdminMenu();} else {wrongMenuNumber();}
                break;
            default:
                wrongMenuNumber();
        }
    }


    private void showUserMenu() {
        while (true) {
            clearConsole();
            userPrompt();
            System.out.println("=======================================");
            System.out.println("         👤  МЕНЮ ПОЛЬЗОВАТЕЛЯ  👤      ");
            System.out.println("=======================================");
            if (!service.isLoggedIn()) {
                System.out.println("🔑 1️⃣  Войти");
                System.out.println("🆕 2️⃣  Регистрация нового пользователя");
            } else {
                System.out.println("🚪 1️⃣  Выйти из системы");
                System.out.println("🔄 2️⃣  Смена пароля");
                System.out.println("🔍 3️⃣  Список книг на абонементе");
            }

            System.out.println("---------------------------------------");
            System.out.println("🔙 0️⃣  Вернуться в главное меню");
            System.out.println("=======================================");
            System.out.print("▶️  Введите номер пункта меню: ");
            int choice = checkIntInput();

            // Прерываю текущий цикл
            if (choice == 0) break;

            handleUserMenuInput(choice);
        }
    }

    private void handleUserMenuInput(int input) {
        clearConsole();
        switch (input) {
            case 1:
                if (!service.isLoggedIn()) {
                    login();
                } else {
                    logout();
                }
                break;
            case 2:
                if (!service.isLoggedIn()) {
                    registration();
                } else {
                    passwordChange();
                }
                break;
            case 3:
                if (service.isLoggedIn()) {showUserBooks();} else {wrongMenuNumber();}
                break;
            default:
                wrongMenuNumber();
        }
    }

    private void showAdminMenu() {
        while (true) {
            clearConsole();
            if (!service.isAdmin() && !service.isSuperAdmin()) break;
            userPrompt();
            System.out.println("=======================================");
            System.out.println(ColorMe.text(Color.PURPLE,"     🔧  АДМИНИСТРАТОРСКОЕ МЕНЮ  🔧     "));
            System.out.println("=======================================");
            System.out.println("👥 1️⃣  Список всех пользователей");
            System.out.println("📖 2️⃣  Список всех читателей");
            System.out.println("🚫 3️⃣  Заблокировать / разблокировать пользователя");
            if (service.isSuperAdmin())
                System.out.println(ColorMe.text(Color.RED, "🔄 4️⃣  Изменить роль пользователя"));
            System.out.println("🗑️ 5️⃣  Удалить пользователя");
            System.out.println("---------------------------------------");
//            System.out.println("📚 6️⃣  Список книг в наличии");
//            System.out.println("📜 7️⃣  Список книг на абонементе");
//            System.out.println("🔍 8️⃣  Список книг у определенного читателя");
//            System.out.println("---------------------------------------");
//            System.out.println("➕ 9️⃣    Добавить новую книгу");
//            System.out.println("✏️ 1️⃣0️⃣  Редактировать книгу");
//            System.out.println("🗑️ 1️⃣1️⃣  Удалить книгу");
//            System.out.println("---------------------------------------");
            System.out.println("🔙 0️⃣  Вернуться в главное меню");
            System.out.println("=======================================");
            System.out.print("▶️  Введите номер пункта меню: ");

            int choice = checkIntInput();

            // Прерываю текущий цикл
            if (choice == 0) break;
            handleAdminMenuInput(choice);
        }
    }

    private void handleAdminMenuInput(int input) {
        clearConsole();
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
               if (service.isSuperAdmin()) { changeUserRole(); } else { wrongMenuNumber(); }
                break;
            case 5:
                deleteUser();
                break;
//            case 6:
//                showAvailableBooks();
//                break;
//            case 7:
//                showBorrowedBooks();
//                break;
//            case 8:
//                showBooksByReader();
//                break;
//            case 9:
//                addNewBook();
//                break;
//            case 10:
//                editBook();
//                break;
//            case 11:
//                deleteBook();
//                break;
            default:
                wrongMenuNumber();
        }
    }


    private void showBookMenu() {
        while (true) {
            clearConsole();
            userPrompt();
            System.out.println("=======================================");
            System.out.println("        📚 МЕНЮ БИБЛИОТЕКИ 📚         ");
            System.out.println("=======================================");
            System.out.println("1️⃣  📖  Показать все книги");
            System.out.println("2️⃣  📚  Показать доступные книги");
            System.out.println("3️⃣  🔍  Найти книгу по названию");
            System.out.println("4️⃣  ✍️  Найти книгу по автору");
            System.out.println("5️⃣  🎭  Найти книгу по жанру");
            if (service.isLoggedIn()) {
                System.out.println(ColorMe.text(Color.BLUE,"6️⃣  📥  Взять книгу из библиотеки"));
                System.out.println(ColorMe.text(Color.BLUE,"7️⃣  📤  Вернуть книгу в библиотеку"));
            }
            if (service.isAdmin() || service.isSuperAdmin()) {
                System.out.println("----------------Admin part-----------------------");
                System.out.println(ColorMe.text(Color.YELLOW,"8️⃣   🔍 ️ Список всех книг на абонементе"));
                System.out.println(ColorMe.text(Color.YELLOW,"9️⃣   🔍 ️ Список книг у определенного читателя"));
                System.out.println(ColorMe.text(Color.YELLOW,"1️⃣0️⃣ ➕ Добавить новую книгу"));
                System.out.println(ColorMe.text(Color.YELLOW,"1️⃣1️⃣ ✏️ Редактировать книгу"));
                System.out.println(ColorMe.text(Color.YELLOW,"1️⃣2️⃣ 🗑️ Удалить книгу"));
            }
            System.out.println("---------------------------------------");
            System.out.println("🔙 0️⃣  Вернуться в главное меню");
            System.out.println("=======================================");
            System.out.print("▶️  Введите номер пункта меню: ");
            int choice = checkIntInput();

            if (choice == 0) break;
            handleBookMenuInput(choice);
        }
    }

    private void handleBookMenuInput(int choice) {
        clearConsole();
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
                if (service.isLoggedIn()) { borrowBook(); } else {wrongMenuNumber();}
                break;
            case 7:
                if (service.isLoggedIn()) {returnBook(); } else {wrongMenuNumber();}
                break;
            case 8:
                if (service.isAdmin() || service.isSuperAdmin()) {showBorrowedBooks();} else {wrongMenuNumber();}
                break;
            case 9:
                if (service.isAdmin() || service.isSuperAdmin()) {showBooksByReader();} else {wrongMenuNumber();}
                break;
            case 10:
                if (service.isAdmin() || service.isSuperAdmin()) {addNewBook();} else {wrongMenuNumber();}
                break;
            case 11:
                if (service.isAdmin() || service.isSuperAdmin()) {editBook();} else {wrongMenuNumber();}
                break;
            case 12:
                if (service.isAdmin() || service.isSuperAdmin()) { deleteBook();} else {wrongMenuNumber();}
                break;
            default:
                wrongMenuNumber();
        }
    }


    private void waitRead() {
        System.out.println("\nДля продолжения нажмите Enter...");
        scanner.nextLine();
    }

    private void userPrompt(){
        User activeUser = service.getActiveUser();
        if (activeUser != null) {
            Color userColor = Color.BLUE;
            if (activeUser.getRole() == Role.ADMIN) userColor = Color.ORANGE;
            if (activeUser.getRole() == Role.SUPER_ADMIN) userColor = Color.RED;
            System.out.println(ColorMe.text(Color.WHITE, "Здравствуйте, ") + ColorMe.text(userColor, activeUser.getEmail()));
        }
    }

    public void clearConsole() {
        try {
            Robot robot = null;
            robot = new Robot();
            // Выставляем шорткат в натройках File -> Settings -> Keymap -> Other -> Clear All
            // SHIFT + SPACE для очистки консоли
            // Иначе работать не будет
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_SPACE);
            robot.keyRelease(KeyEvent.VK_SPACE);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            // Задержка, чтобы дать время переключить фокус на окно IntelliJ IDEA
            robot.delay(1000);  // Задержка
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    private void wrongMenuNumber() {
        System.out.println(ColorMe.text(Color.RED, "⛔ Ошибка: Введите корректный номер пункта меню!"));
        waitRead();
    }


    private int checkIntInput() {
        boolean valid = false;
        int inputInt = 0;
        while (!valid) {
            try {
                inputInt = scanner.nextInt();
                if (inputInt < 0) {
                    System.out.println(ColorMe.text(Color.RED, "⛔ Ошибка: Введите число больше нуля!"));
                } else {
                    valid = true;
                }
            } catch (Exception e) {
                System.out.println(ColorMe.text(Color.RED, "⛔ Ошибка: Введите корректное число!"));
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        return inputInt;
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

    private void passwordChange() {
        System.out.println("Введите текущий пароль: ");
        String oldPassword = scanner.nextLine();
        System.out.println("Введите новый пароль:\n(минимальные требования: >= 8 символов, спецсимвол, большая буква, цифра)");
        String newPassword = scanner.nextLine();
        if (service.changePassword(oldPassword, newPassword)) {
            System.out.println("Поздравляем! Пароль успешно изменен");
        } else {
            System.out.println("Не удалось изменить пароль");
        }
        waitRead();
    }

    private void registration() {
        System.out.println("Регистрация нового пользователя");
        System.out.println("Введите email:");
        String emailInput = scanner.nextLine();

        System.out.println("Введите пароль:\n(минимальные требования: >= 8 символов, спецсимвол, большая буква, цифра)");
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
           System.out.println("Пользователь с указанным email не найден");
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
          System.out.println("Пользователь с указанным email не найден");
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
             System.out.println("Пользователь с указанным email не найден");
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
        if (allBooks.isEmpty()) {
            System.out.println("В библиотеке нет ни одной книги!");
        } else {
            System.out.println(allBooks);
        }
        waitRead();
    }

    private void showAvailableBooks() {
        System.out.println("Список всех свободных книг");
        MyList<Book> availableBooks = service.getAvailableBooks();
        if (availableBooks.isEmpty()) {
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
        if (booksByTitle.isEmpty()) {
            System.out.println("Не удалось найти книгу с указанным названием");
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
        if (booksByAuthor.isEmpty()) {
            System.out.println("Не удалось найти книгу с такой фамилией автора");
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
        if (booksByGenre.isEmpty()) {
            System.out.println("Не удалось найти книгу такого жанра");
        } else {
            System.out.println(booksByGenre);
        }
        waitRead();
    }

    private void borrowBook() {
        System.out.println("Взять книгу из библиотеки");
        System.out.println("Введите id книги, которую хотите взять");
        int id = checkIntInput();
        Book book = service.getBookById(id);
        if (book != null) {
            System.out.printf("Книга \"%s: %s\" Берём? Да/Нет\n", book.getTitle(), book.getAuthor());
            String answerInput = scanner.nextLine();
            if (answerInput.trim().equalsIgnoreCase("да")) {
                if (!service.borrowBook(id)) {
                    System.out.println("Не удалось взять книгу на абонемент");
                } else {
                    System.out.println("Книга оформлена на абонемент");
                }
            } else {
                System.out.println("Не берем значит... Ну ок: ученье свет, а не ученье приятный полумрак...");
            }
        } else {
            System.out.println("Книга с указанным id не найдена!");
        }
        waitRead();
    }


 private void showBorrowedBooks() {
     // Список всех занятых книг
     System.out.println("Cписок книг у читателей");
     MyList<Book> borrowedBooks = service.getBorrowedBooks();
     if (borrowedBooks.isEmpty()) {
         System.out.println("Занятых книг пока нет");
     } else if (!borrowedBooks.isEmpty()){
         System.out.println(borrowedBooks);
     }
     waitRead();
 }

 private void showUserBooks(){
     MyList<Book> borrowedBooks = service.getUserBooks();
     if (borrowedBooks.isEmpty()) {
         System.out.println("Ваш абонемент пуст!");
     } else if (!borrowedBooks.isEmpty()){
         System.out.println("Список книг на абонементе");
         System.out.println(borrowedBooks);
     }
     waitRead();
 }

 private void showBooksByReader() {
     System.out.println("Посмотреть книги у определенного читателя");
     System.out.println("Введите email читателя, у которого хотите посмотреть книги:");
     String email = scanner.nextLine();

     User user = service.getUserByEmail(email);

     if (user == null) {
         System.out.println("Такого пользователя нет!");
     } else if (user.getUserBooks().isEmpty()) {
         System.out.println("У пользователя нет книг");
     } else {
         System.out.println(user.getUserBooks());
     }

     waitRead();
 }

 private void addNewBook() {
     System.out.println("Добавление новой книги");
     System.out.println("Введите название книги:");
     String title = scanner.nextLine().trim();

     System.out.println("Введите автора:");
     String author = scanner.nextLine().trim();

     System.out.println("Введите год книги:");
     int year = checkIntInput();

     System.out.println("Введите количество страниц:");
     int pages = checkIntInput();

     System.out.println("Введите жанр:");
     String genre = scanner.nextLine().trim();

     if (title.isEmpty() || author.isEmpty() || genre.isEmpty()) {
         System.out.println("Введите корректные данные!");

         waitRead();
         return;
     }

     Book book = service.addBook(title, author, year, pages, genre);
     if (book == null) {
         System.out.println("Не удалось добавить книгу");
     } else {
         System.out.println("Книга успешно добавлена!");
         System.out.println(book);
     }

     waitRead();
 }

 private void editBook() {
     System.out.println("Редактирование книг");
     System.out.println("Введите id книги, которую нужно отредактировать:");
     int id = checkIntInput();

     Book book = service.getBookById(id);
     if (book == null) {
         System.out.println("Книга с указанным id не найдена");
     } else {
         System.out.println("Заполните те поля, которые нужно отредактировать. Если поле редактировать НЕ нужно - оставьте пустым:");
         System.out.println("Текущее название книги: " + book.getTitle());
         System.out.println("Поменять название:");
         String newTitle = scanner.nextLine().trim();

         System.out.println("Текущий автор книги: " + book.getAuthor());
         System.out.println("Поменять автора:");
         String newAuthor = scanner.nextLine().trim();

         System.out.println("Текущий год книги: " + book.getYear());
         System.out.println("Поменять год:");
         int newYear = checkIntInput();

         System.out.println("Текущее кол-во страниц книги: " + book.getPages());
         System.out.println("Поменять кол-во страниц:");
         int newPages = checkIntInput();

         System.out.println("Текущий жанр книги: " + book.getGenre());
         System.out.println("Поменять жанр:");
         String newGenre = scanner.nextLine().trim();

         if (newTitle.isEmpty()) newTitle = null;
         if (newAuthor.isEmpty()) newAuthor = null;
         if (newGenre.isEmpty()) newGenre = null;

         if (service.editBook(id, newTitle, newAuthor, newYear, newPages, newGenre)) {
             System.out.println("Книга отредактирована!");
             System.out.println(service.getBookById(id));
         } else {
             System.out.println("Не получилось отредактировать книгу");
         }
     }
     waitRead();
 }

 private void deleteBook() {
     System.out.println("Удаление книг");
     System.out.println("Введите id книги, которую хотите удалить из библиотеки:");
     int bookToDelete = checkIntInput();
     Book book = service.getBookById(bookToDelete);
     if (book != null) {
         System.out.printf("Книга \"%s: %s\" Удаляем? Да/Нет\n", book.getTitle(), book.getAuthor());
         String answerInput = scanner.nextLine();
         if (answerInput.trim().equalsIgnoreCase("да")) {
             if (!service.deleteBookById(bookToDelete)) {
                 System.out.println("Не удалось удалить книгу");
             } else {
                 System.out.println("Вы успешно удалили книгу!");
             }
         }
     } else {
         System.out.println("Книга с указанным id не найдена!");
     }

     waitRead();
 }



    private void returnBook() {
        System.out.println("Вернуть книгу в библиотеку");
        MyList<Book> borrowedBooks = service.getUserBooks();
        if (borrowedBooks.isEmpty()) {
            System.out.println("У Вас нет взятых на абонемент книг");
        } else if (!borrowedBooks.isEmpty()) {
            System.out.println(borrowedBooks);
            System.out.println("Введите № книги, которую хотите вернуть");
            int idBook = checkIntInput();
            if (!service.returnBook(idBook)) {
                System.out.println("Не удалось вернуть книгу");
            } else {
                System.out.println("Вы вернули книгу!");
            }
        }
        waitRead();
    }
}
