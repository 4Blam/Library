import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This interface provides two methods for getting book-entities with specified parameters:
 *<ul>
 *      *  <li>1: by author
 *      *  <li>2: by title
 *</ul>
 * One method for getting info about all books in library
 * And one method for inserting a book-entity into library
 */
public interface BookRepository {
    /**
     * Performs actual select statement in our database and returns all books
     * @return ArrayList of bookEntities that represents all books in our library
     * @throws SQLException
     */
    ArrayList<BookEntity> selectAllBooks() throws SQLException;
    /**
     * Performs actual select statement in our database and returns all books
     * with given author
     * @param bookEntity bookEntity that contains only author (title, year are null and 0)
     * @return ArrayList of bookEntities, that has the same author as bookEntity param
     * @throws SQLException
     */
    ArrayList<BookEntity> selectBooksByAuthor(BookEntity bookEntity) throws SQLException;
    /**
     * Performs actual select statement in our database and returns all books
     * with given title
     * @param bookEntity bookEntity that contains only title (author, year are null and 0)
     * @return ArrayList of bookEntities, that has the same title as bookEntity param
     * @throws SQLException
     */
    ArrayList<BookEntity> selectBookByTitle(BookEntity bookEntity) throws SQLException;
    /**
     * Performs a book insertion into our database
     * @param bookEntity bookEntity that we will insert into database
     * @return inserted bookEntity for validation
     * @throws SQLException
     */
    BookEntity insertBook(BookEntity bookEntity) throws SQLException;
}
