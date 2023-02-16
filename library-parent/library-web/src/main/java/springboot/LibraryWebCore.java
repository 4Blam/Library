package springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.Book;
import service.BookLibraryServiceImpl;
import service.BookMapper;

import java.util.ArrayList;
import java.util.List;

@Component
public class LibraryWebCore {
    private final BookLibraryServiceImpl bookLibraryServiceImpl;
    private final BookWebMapper bookWebMapper;

    @Autowired
    public LibraryWebCore(BookLibraryServiceImpl bookLibraryServiceImpl, BookWebMapper bookWebMapper){
        this.bookLibraryServiceImpl = bookLibraryServiceImpl;
        this.bookWebMapper = bookWebMapper;
    }

    public List<BookWeb> getAllBooks() {
        List<Book> books = bookLibraryServiceImpl.getAllBooks();
        List<BookWeb> webbooks = new ArrayList<>();
        for (Book b: books){
            webbooks.add(bookWebMapper.bookToWeb(b));
        }
        return webbooks;
    }

    public List<BookWeb> getBookById(int id) {
        List<Book> books = bookLibraryServiceImpl.getBookById(id);
        List<BookWeb> webbooks = new ArrayList<>();
        for (Book b: books){
            webbooks.add(bookWebMapper.bookToWeb(b));
        }
        return webbooks;
    }

    public List<BookWeb> getBooksByAuthor(String author) {
        List<Book> books = bookLibraryServiceImpl.getBooksByAuthor(author);
        List<BookWeb> webbooks = new ArrayList<>();
        for (Book b: books){
            webbooks.add(bookWebMapper.bookToWeb(b));
        }
        return webbooks;
    }

    public List<BookWeb> getBookByTitle(String title) {
        List<Book> books = bookLibraryServiceImpl.getBookByTitle(title);
        List<BookWeb> webbooks = new ArrayList<>();
        for (Book b: books){
            webbooks.add(bookWebMapper.bookToWeb(b));
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
