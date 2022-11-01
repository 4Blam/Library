import java.util.List;

/**
 * This interface provides two methods for getting book-entities with specified parameters:
 *<ul>
      *  <li>1: by author
      *  <li>2: by title
 *</ul>
 * One method for getting info about all books in library
 * And one method for inserting a book-entity into library
 */
public interface BookRepository {
    /**
     * Performs actual select statement in our database and returns all books
     * @return ArrayList of bookEntities that represents all books in our library
     * @throws IllegalAccessException when cant connect to database
     * @throws IllegalStateException when cant perform sql request because of trying to do that in wrong state
     * @throws IllegalArgumentException when cant handle request's result
     */
    List<BookEntity> selectAllBooks() throws IllegalAccessException, IllegalStateException, IllegalArgumentException;
    /**
     * Performs actual select statement in our database and returns all books
     * with given author
     * @param bookEntity bookEntity that contains only author (title, year are null and 0)
     * @return ArrayList of bookEntities, that has the same author as bookEntity param
     * @throws IllegalAccessException when cant connect to database
     * @throws IllegalStateException when cant perform sql request because of trying to do that in wrong state
     * @throws IllegalArgumentException when cant handle request's result
     */
    List<BookEntity> selectBooksByAuthor(BookEntity bookEntity) throws IllegalAccessException, IllegalStateException, IllegalArgumentException;
    /**
     * Performs actual select statement in our database and returns all books
     * with given title
     * @param bookEntity bookEntity that contains only title (author, year are null and 0)
     * @return ArrayList of bookEntities, that has the same title as bookEntity param
     * @throws IllegalAccessException when cant connect to database
     * @throws IllegalStateException when cant perform sql request because of trying to do that in wrong state
     * @throws IllegalArgumentException when cant handle request's result
     */
    List<BookEntity> selectBookByTitle(BookEntity bookEntity) throws IllegalAccessException, IllegalStateException, IllegalArgumentException;
    /**
     * Performs a book insertion into our database
     * @param bookEntity bookEntity that we will insert into database
     * @return inserted bookEntity for validation
     * @throws IllegalAccessException when cant connect to database
     * @throws IllegalStateException when cant perform sql request because of trying to do that in wrong state
     */
    BookEntity insertBook(BookEntity bookEntity) throws IllegalAccessException, IllegalStateException;
}
