package service;

import org.springframework.stereotype.Component;

import java.util.HashMap;
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
    List<Book> getAllBooks();

    /**
     * get book with the specified id
     * @param id book's id
     * @return book with given id
     */
    Book getBookById(String id);
    /**
     * Insert given values into library
     * @param title book's title
     * @param author book's author
     * @param phid PubHouse, where book was published
     * @return inserted book
     */
    Book insertBook(String title, String author, long phid);
    /**
     * Delete book from library by id
     * @param id book's id
     * @return
     */
    void deleteBookById(String id);

    /**
     * Updates book in library by id
     * @param id id of a book that we want to update
     * @param field field to update
     * @param data new field data
     */
    void updateBookById(String id, String field, String data);

    /**
     * Gets Hashmap with pairs of type <PHID; PHNAME>
     * @return hashmap with described data
     */
    HashMap<Long, String> getPHInfo();
}
