package springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import service.Book;
import service.BookLibraryServiceImpl;

import java.util.List;

@RestController
public class Controller {
    BookLibraryServiceImpl bookLibraryServiceImpl = new BookLibraryServiceImpl();

    @GetMapping("/getBooksByController")
    public String index() {
        List<Book> books;

        try {
            books = bookLibraryServiceImpl.getAllBooks();
            StringBuilder result = new StringBuilder();
            for(Book book : books){
                result.append(book.toString()).append("\n");
            }
            return result.toString();
        } catch (RuntimeException e){
            return e.getMessage();
        }
    }

}