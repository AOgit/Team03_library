import model.Book;
import repository.BookRepository;
import repository.BookRepositoryImpl;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import service.MainService;
import service.MainServiceImpl;
import utils.MyList;

public class Test {
    public static void main(String[] args) {

        BookRepository bookRepository = new BookRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();

        MainService service = new MainServiceImpl(bookRepository, userRepository);

        service.addBook("War", "Vasya", 1000, 500);

        System.out.println(service.getAllBooks());

        boolean result = service.editBook(1, null, null, 0, 0);

        System.out.println(result);

        System.out.println(service.getAllBooks());

    }
}
