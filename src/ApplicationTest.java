import model.Role;
import repository.BookRepository;
import repository.BookRepositoryImpl;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import service.MainService;
import service.MainServiceImpl;

import java.util.Arrays;

public class ApplicationTest {
    public static void main(String[] args) {

        BookRepository bookRepository = new BookRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();

        MainService service = new MainServiceImpl(bookRepository, userRepository);

//        System.out.println(Arrays.toString(Role.values()));

        service.addBook("\"Мы закончили этот проект\"", "Алекс, Костя, Ева, Лена", 2025, 1, "фантастика");

        System.out.println(service.getAllBooks());

        boolean result = service.editBook(1, null, null, 0, 0, null);

        System.out.println(result);

        System.out.println(service.getAllBooks());

        System.out.println(service.getBooksByTitle("война"));
        System.out.println(service.getBookByGenre("сказка"));
        System.out.println(service.getBooksByAuthor("толстой"));
        System.out.println(service.getBooksByAuthor("Чехов"));
        System.out.println(service.getBorrowedBooks());
        System.out.println(service.getAvailableBooks());
        System.out.println("Borrow book №3: " + service.borrowBook(3));
        System.out.println(service.getBorrowedBooks());
        System.out.println(service.getAvailableBooks());
        System.out.println("Borrow book №11: " + service.returnBook(11));



    }
}
