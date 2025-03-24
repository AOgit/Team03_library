import model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.BookRepository;
import repository.BookRepositoryImpl;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import service.MainServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    private BookRepository bookRepository;
    private UserRepository userRepository;
    private MainServiceImpl mainService;

    @BeforeEach
    void setUp() {
        bookRepository = new BookRepositoryImpl();
        userRepository = new UserRepositoryImpl();
        mainService = new MainServiceImpl(bookRepository, userRepository);

//        bookRepository.addBook("Java для чайников", "Берд Б.", 2013, 368, "сказка");
//        bookRepository.addBook("Война и мир", "Толстой Л.Н.", 1983, 5876, "роман");
    }

    @Test
    void testGetBookById() {
        Book book = mainService.getBookById(1);
        assertNotNull(book);
        assertEquals("\"Java для чайников\"", book.getTitle());
    }



}