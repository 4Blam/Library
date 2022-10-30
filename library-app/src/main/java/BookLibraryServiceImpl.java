import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
        try {
            this.bookRepositoryImpl = new BookRepositoryImpl();
        } catch (NullPointerException e) {
            logger.error("Couldn't create bookRepImpl because can't connect to database because: \n" + e.getMessage());
        }
    }
    public BookLibraryServiceImpl(BookRepositoryImpl repository){
        this.bookRepositoryImpl = repository;
    }
    public List<Book> getAllBooks(){
        BookMapper mapper = new BookMapper();
        List<BookEntity> entities = new ArrayList<>();
        List<Book> books = new ArrayList<>();

        try {
            entities = bookRepositoryImpl.selectAllBooks();
        } catch (NullPointerException e){
            throw(e);//запутался
            //logger.error("Couldn't complete request because: \n" + e.getMessage());
        } catch (SQLException e){
            logger.error("Wrong SQL request: \n" + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            logger.error("Couldn't handle request's result: \n" + e.getMessage());
        }

        for (BookEntity e : entities){
            books.add(mapper.entityToBook(e));
        }
        if(books.isEmpty()){
            return Collections.EMPTY_LIST;
        }
        return books;
    }
    public List<Book> getBooksByAuthor(String author){
        Book book = new Book();
        book.setAuthor(author);
        BookMapper mapper = new BookMapper();
        List<BookEntity> entities = new ArrayList<>();
        List<Book> books = new ArrayList<>();

        try {
            entities = bookRepositoryImpl.selectBooksByAuthor(mapper.bookToEntity(book));
        } catch (NullPointerException e){
            logger.error("Couldn't complete request because: \n" + e.getMessage());
        } catch (SQLException e){
            logger.error("Wrong SQL request: \n" + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            logger.error("Couldn't handle request's result: \n" + e.getMessage());
        }

        for (BookEntity e : entities){
            books.add(mapper.entityToBook(e));
        }
        if(books.isEmpty()){
            return Collections.EMPTY_LIST;
        }
        return books;
    }

    public List<Book> getBookByTitle(String title){

        Book book = new Book();
        book.setTitle(title);
        BookMapper mapper = new BookMapper();
        List<BookEntity> entities = new ArrayList<>();
        List<Book> books = new ArrayList<>();

        try {
            entities = bookRepositoryImpl.selectBookByTitle(mapper.bookToEntity(book));
        } catch (NullPointerException e){
            logger.error("Couldn't complete request because: \n" + e.getMessage());
        } catch (SQLException e){
            logger.error("Wrong SQL request: \n" + e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            logger.error("Couldn't handle request's result: \n" + e.getMessage());
        }

        for (BookEntity e : entities){
            books.add(mapper.entityToBook(e));
        }
        if(books.isEmpty()){
            return Collections.EMPTY_LIST;
        }
        return books;

    }
    public Book insertBook(String title, String author, int year){
        Book book = new Book(title, author, year);
        BookMapper mapper = new BookMapper();
        BookEntity entity = new BookEntity();
        try {
            entity = bookRepositoryImpl.insertBook(mapper.bookToEntity(book));
        } catch (NullPointerException e){
            logger.error("Couldn't complete request because: \n" + e.getMessage());
        } catch (SQLException e){
            logger.error("Wrong SQL request: \n" + e.getMessage());
        }
        //Если пустая книга? ИЗначально не давать вставить пустую инфу? (аннотации NotNull на параметрах)
        //Или ограничивать только внутри этой функции?
        return mapper.entityToBook(entity);
    }

}
