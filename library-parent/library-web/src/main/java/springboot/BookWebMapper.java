package springboot;

import org.springframework.stereotype.Component;
import service.Book;

@Component
public class BookWebMapper {
    public Book webToBook(BookWeb bookWeb){
    return new Book(bookWeb.getId(), bookWeb.getAuthor(),
            bookWeb.getTitle(),
            bookWeb.getPublished_in());
    }
    public BookWeb bookToWeb(Book book){
        return new BookWeb(book.getId(), book.getAuthor(),
                book.getTitle(),
                book.getPublished_in());
    }
}
