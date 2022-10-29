import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Service-level class, where we get info by requests from repository-level and send it back
 * to Web-level
 */
public class BookLibraryServiceImpl implements BookLibraryService {
    private BookRepositoryImpl bookRepositoryImpl;
    public BookLibraryServiceImpl(){
        this.bookRepositoryImpl = new BookRepositoryImpl();
    }
    public ArrayList<Book> getAllBooks(){
        try {
            BookMapper mapper = new BookMapper();
            ArrayList<BookEntity> entities = bookRepositoryImpl.selectAllBooks();

            ArrayList<Book> books = new ArrayList<Book>();
            for (BookEntity e : entities){
                books.add(mapper.entityToBook(e));
            }

            return books;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public ArrayList<Book> getBooksByAuthor(String author){
        try {
            Book book = new Book();
            book.setAuthor(author);
            BookMapper mapper = new BookMapper();
            ArrayList<BookEntity> entities = bookRepositoryImpl.selectBooksByAuthor(mapper.bookToEntity(book));

            ArrayList<Book> books = new ArrayList<Book>();
            for (BookEntity e : entities){
                books.add(mapper.entityToBook(e));
            }

            return books;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public ArrayList<Book> getBookByTitle(String title){
        try {
            Book book = new Book();
            book.setTitle(title);
            BookMapper mapper = new BookMapper();
            ArrayList<BookEntity> entities = bookRepositoryImpl.selectBookByTitle(mapper.bookToEntity(book));

            ArrayList<Book> books = new ArrayList<Book>();
            for (BookEntity e : entities){
                books.add(mapper.entityToBook(e));
            }

            return books;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public Book insertBook(String title, String author, int year){
            try {
                Book book = new Book(title, author, year);
                BookMapper mapper = new BookMapper();
                BookEntity entity = bookRepositoryImpl.insertBook(mapper.bookToEntity(book));
                return mapper.entityToBook(entity);
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
            return null;
    }

}
