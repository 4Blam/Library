package springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.Book;
import service.BookLibraryServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Component
public class LibraryWebCore {
    private final BookLibraryServiceImpl bookLibraryServiceImpl;
    private final BookDtoMapper bookDtoMapper;

    @Autowired
    public LibraryWebCore(BookLibraryServiceImpl bookLibraryServiceImpl, BookDtoMapper bookDtoMapper){
        this.bookLibraryServiceImpl = bookLibraryServiceImpl;
        this.bookDtoMapper = bookDtoMapper;
    }

    public List<BookDto> getAllBooks() {
        List<Book> books = bookLibraryServiceImpl.getAllBooks();
        List<BookDto> webbooks = new ArrayList<>();
        for (Book b: books){
            webbooks.add(bookDtoMapper.bookToWeb(b));
        }
        return webbooks;
    }

    public List<BookDto> getBookById(int id) {
        List<Book> books = bookLibraryServiceImpl.getBookById(id);
        List<BookDto> webbooks = new ArrayList<>();
        for (Book b: books){
            webbooks.add(bookDtoMapper.bookToWeb(b));
        }
        return webbooks;
    }

    public List<BookDto> getBooksByAuthor(String author) {
        List<Book> books = bookLibraryServiceImpl.getBooksByAuthor(author);
        List<BookDto> webbooks = new ArrayList<>();
        for (Book b: books){
            webbooks.add(bookDtoMapper.bookToWeb(b));
        }
        return webbooks;
    }

    public List<BookDto> getBookByTitle(String title) {
        List<Book> books = bookLibraryServiceImpl.getBookByTitle(title);
        List<BookDto> webbooks = new ArrayList<>();
        for (Book b: books){
            webbooks.add(bookDtoMapper.bookToWeb(b));
        }
        return webbooks;
    }

    public void updateBookById(int id, String field, String value) {
        bookLibraryServiceImpl.updateBookById(id, field, value);
    }

    public void insertBook(String title, String author, int publishedIn) {
        bookLibraryServiceImpl.insertBook(title, author, publishedIn);
    }

    public void deleteBookById(int id) {
        bookLibraryServiceImpl.deleteBookById(id);
    }
}
