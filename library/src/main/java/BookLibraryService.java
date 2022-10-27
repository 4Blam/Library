import java.util.ArrayList;

public interface BookLibraryService {
    /**
     * Gets array with all books in library
     * @return ArrayList with all books in library
     */
    ArrayList<Book> getAllBooks();

    /**
     * Gets array with books with the specified author
     * @param author author's name and surname
     * @return ArrayList that contains books written by given author
     */
    ArrayList<Book> getBooksByAuthor(String author);

    /**
     * Gets array with a book with given title
     * @param title book's title
     * @return ArrayList that contains books with given title
     */
    ArrayList<Book> getBookByTitle(String title);

    /**
     * Insert given values into library
     * @param title book's title
     * @param author book's author
     * @param year year, when book was published
     * @return book that was inserted
     */
    Book insertBook(String title, String author, int year);
}
