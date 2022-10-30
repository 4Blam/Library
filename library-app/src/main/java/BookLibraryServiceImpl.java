import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Service-level class, where we get info by requests from repository-level and send it back
 * to Web-level
 */
public class BookLibraryServiceImpl implements BookLibraryService {
    static Logger logger = LoggerFactory.getLogger(BookLibraryServiceImpl.class);
    private BookRepositoryImpl bookRepositoryImpl;
    public BookLibraryServiceImpl(){
        this.bookRepositoryImpl = new BookRepositoryImpl();
    }
    public BookLibraryServiceImpl(BookRepositoryImpl repository){
        this.bookRepositoryImpl = repository;
    }

    @NotNull
    public List<Book> getAllBooks() throws Exception {
        BookMapper mapper = new BookMapper();
        List<BookEntity> entities;
        List<Book> books = new ArrayList<>();

        try {
            entities = bookRepositoryImpl.selectAllBooks();
        } catch (IllegalAccessException accessError){
            logger.error("Couldn't access database because: " + accessError.getMessage());
            throw accessError;
        } catch (IllegalStateException wrongState){
            logger.error("Couldn't complete request because tried to do it in wrong database state: "
                    + wrongState.getMessage());
            throw wrongState;
        } catch (IllegalArgumentException wrongResult){
            logger.error("Couldn't handle result due to: " + wrongResult.getMessage());
            throw wrongResult;
        }

        for (BookEntity e : entities){
            books.add(mapper.entityToBook(e));
        }

        if(books.isEmpty()){
            return Collections.EMPTY_LIST;
        }

        return books;
    }
    @NotNull
    public List<Book> getBooksByAuthor(String author) throws Exception {
        Book book = new Book();
        book.setAuthor(author);
        BookMapper mapper = new BookMapper();
        List<BookEntity> entities;
        List<Book> books = new ArrayList<>();

        try {
            entities = bookRepositoryImpl.selectBooksByAuthor(mapper.bookToEntity(book));
        } catch (IllegalAccessException accessError){
            logger.error("Couldn't access database because: " + accessError.getMessage());
            throw accessError;
        } catch (IllegalStateException wrongState){
            logger.error("Couldn't complete request because tried to do it in wrong database state: "
                    + wrongState.getMessage());
            throw wrongState;
        } catch (IllegalArgumentException wrongResult){
            logger.error("Couldn't handle result due to: " + wrongResult.getMessage());
            throw wrongResult;
        }

        for (BookEntity e : entities){
            books.add(mapper.entityToBook(e));
        }

        if(books.isEmpty()){
            return Collections.EMPTY_LIST;
        }

        return books;
    }
    @NotNull
    public List<Book> getBookByTitle(String title) throws Exception {

        Book book = new Book();
        book.setTitle(title);
        BookMapper mapper = new BookMapper();
        List<BookEntity> entities;
        List<Book> books = new ArrayList<>();

        try {
            entities = bookRepositoryImpl.selectBookByTitle(mapper.bookToEntity(book));
        } catch (IllegalAccessException accessError){
            logger.error("Couldn't access database because: " + accessError.getMessage());
            throw accessError;
        } catch (IllegalStateException wrongState){
            logger.error("Couldn't complete request because tried to do it in wrong database state: "
                    + wrongState.getMessage());
            throw wrongState;
        } catch (IllegalArgumentException wrongResult){
            logger.error("Couldn't handle result due to: " + wrongResult.getMessage());
            throw wrongResult;
        }

        for (BookEntity e : entities){
            books.add(mapper.entityToBook(e));
        }

        if(books.isEmpty()){
            return Collections.EMPTY_LIST;
        }

        return books;

    }
    @NotNull
    public Book insertBook(String title, String author, int year) throws Exception{
        Book book = new Book(author, title, year);
        BookMapper mapper = new BookMapper();
        BookEntity entity;
        try {
            entity = bookRepositoryImpl.insertBook(mapper.bookToEntity(book));
        } catch (IllegalAccessException accessError){
            logger.error("Couldn't access database because: " + accessError.getMessage());
            throw accessError;
        } catch (IllegalStateException wrongState){
            logger.error("Couldn't complete request because tried to do it in wrong database state: "
                    + wrongState.getMessage());
            throw wrongState;
        }
        return mapper.entityToBook(entity);
    }

}
