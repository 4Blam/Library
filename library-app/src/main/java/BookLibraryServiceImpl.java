import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jetbrains.annotations.NotNull;

/**
 * Service-level class, where we get info by requests from repository-level and send it back
 * to Web-level
 */
public class BookLibraryServiceImpl implements BookLibraryService {
    private final BookRepositoryImpl bookRepositoryImpl;
    private final BookMapper mapper;
    public BookLibraryServiceImpl(){
        this.bookRepositoryImpl = new BookRepositoryImpl();
        this.mapper = new BookMapper();
    }
    public BookLibraryServiceImpl(BookRepositoryImpl repository){
        this.bookRepositoryImpl = repository;
        this.mapper = new BookMapper();
    }

    @NotNull
    public List<Book> getAllBooks() {
        List<BookEntity> entities;
        List<Book> books = new ArrayList<>();

        entities = bookRepositoryImpl.selectAllBooks();

        for (BookEntity e : entities){
            books.add(mapper.entityToBook(e));
        }

        if(books.isEmpty()){
            return Collections.EMPTY_LIST;
        }

        return books;
    }
    @NotNull
    public List<Book> getBooksByAuthor(String author) {
        Book book = new Book();
        book.setAuthor(author);
        List<BookEntity> entities;
        List<Book> books = new ArrayList<>();

        entities = bookRepositoryImpl.selectBooksByAuthor(mapper.bookToEntity(book));

        for (BookEntity e : entities){
            books.add(mapper.entityToBook(e));
        }

        if(books.isEmpty()){
            return Collections.EMPTY_LIST;
        }

        return books;
    }
    @NotNull
    public List<Book> getBookByTitle(String title) {
        Book book = new Book();
        book.setTitle(title);
        List<BookEntity> entities;
        List<Book> books = new ArrayList<>();

        entities = bookRepositoryImpl.selectBookByTitle(mapper.bookToEntity(book));

        for (BookEntity e : entities){
            books.add(mapper.entityToBook(e));
        }

        if(books.isEmpty()){
            return Collections.EMPTY_LIST;
        }

        return books;

    }
    @NotNull
    public Book insertBook(String title, String author, int year){
        Book book = new Book(author, title, year);
        BookMapper mapper = new BookMapper();
        BookEntity entity = bookRepositoryImpl.insertBook(mapper.bookToEntity(book));

        return mapper.entityToBook(entity);
    }

}
