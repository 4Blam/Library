import java.util.List;
/**
 * This interface provides two methods for getting books with specified parameters:
 *<ul>
 *      *  <li>1: by author
 *      *  <li>2: by title
 *</ul>
 * One method for getting info about all books in library
 * And one method for inserting a book into library
 */
public interface BookLibraryService {
    /**
     * Gets array with all books in library
     * @return ArrayList with all books in library
     * @throws Exception that has no type because user doesn't have to know why something went wrong
     */
    List<Book> getAllBooks() throws Exception;

    /**
     * Gets array with books with the specified author
     * @param author author's name and surname
     * @return ArrayList that contains books written by given author
     * @throws Exception that has no type because user doesn't have to know why something went wrong
     */
    List<Book> getBooksByAuthor(String author) throws Exception;

    /**
     * Gets array with a book with given title
     * @param title book's title
     * @return ArrayList that contains books with given title
     * @throws Exception that has no type because user doesn't have to know why something went wrong
     */
    List<Book> getBookByTitle(String title) throws Exception;

    /**
     * Insert given values into library
     * @param title book's title
     * @param author book's author
     * @param year year, when book was published
     * @return book that was inserted
     * @throws Exception that has no type because user doesn't have to know why something went wrong
     */
    Book insertBook(String title, String author, int year) throws Exception;
}
