import repository.BookRepository;
import repository.BookRepositoryImpl;
import service.MainService;
import service.MainServiceImpl;
import view.Menu;

public class BookApp {
    public static void main(String[] args) {

        BookRepository bookRepository = new BookRepositoryImpl();

        MainService service = new MainServiceImpl(bookRepository);

        Menu menu = new Menu(service);

        menu.start();
    }
}
