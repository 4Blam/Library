package service;

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
     */
    List<BookWeb> getAllBooks();

    /**
     * Gets array with book with the specified id (array size is boolean)
     * @param id book's id
     * @return ArrayList that contains book with given id
     */
    List<BookWeb> getBookById(int id);
    /**
     * Gets array with books with the specified author
     * @param author author's name and surname
     * @return ArrayList that contains books written by given author
     */
    List<BookWeb> getBooksByAuthor(String author);

    /**
     * Gets array with a book with given title
     * @param title book's title
     * @return ArrayList that contains books with given title
     */
    List<BookWeb> getBookByTitle(String title);

    /**
     * Insert given values into library
     * @param title book's title
     * @param author book's author
     * @param year year, when book was published
     * @return
     */
    void insertBook(String title, String author, int year);
    /**
     * Delete book from library by id
     * @param id book's id
     * @return
     */
    void deleteBookById(int id);

    /**
     * Updates book in library by id
     * @param id id of a book that we want to update
     * @param field field to update
     * @param data new field data
     */
    void updateBookById(int id, String field, String data);
}
