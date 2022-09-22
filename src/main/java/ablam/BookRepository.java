package ablam;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BookRepository {
    /**
     * This method performs actual select statement in our database and returns all books
     * @return ArrayList of bookEntities that represents all books in our library
     * @throws SQLException
     */
    ArrayList<BookEntity> selectAllBooks() throws SQLException;
    /**
     * This method performs actual select statement in our database and returns all books
     * with given author
     * @param bookEntity bookEntity that contains only author (title, year are null and 0)
     * @return ArrayList of bookEntities, that has the same author as bookEntity param
     * @throws SQLException
     */
    ArrayList<BookEntity> selectBooksByAuthor(BookEntity bookEntity) throws SQLException;
    /**
     * This method performs actual select statement in our database and returns all books
     * with given title
     * @param bookEntity bookEntity that contains only title (author, year are null and 0)
     * @return ArrayList of bookEntities, that has the same title as bookEntity param
     * @throws SQLException
     */
    ArrayList<BookEntity> selectBookByTitle(BookEntity bookEntity) throws SQLException;
    /**
     * This method allows us to insert book into our database
     * @param bookEntity bookEntity that we will insert into database
     * @return inserted bookEntity for validation
     * @throws SQLException
     */
    BookEntity insertBook(BookEntity bookEntity) throws SQLException;
}
