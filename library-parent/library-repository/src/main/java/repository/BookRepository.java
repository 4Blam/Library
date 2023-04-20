package repository;

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
     */
    List<BookEntity> selectAllBooks();

    /**
     * Performs actual select statement in our database and returns book
     * with given id
     * @param bookEntity bookEntity that contains only id
     * @return bookEntity, that has the same id as bookEntity param
     */
    BookEntity selectBookById(BookEntity bookEntity);
    /**
     * Performs a book insertion into our database
     * @param bookEntity bookEntity that we will insert into database
     * @return inserted bookEntity for validation
     */
    BookEntity insertBook(BookEntity bookEntity);
    /**
     * Performs a book update in our database
     * @param bookEntity bookEntity that we will update in database
     * @param field field to update
     * @return ex bookEntity for validation
     */
    BookEntity updateBook(BookEntity bookEntity, String field);

    /**
     * Performs book deletion in our database
     * @param bookEntity bookEntity which id is used to find a book to delete in our database
     * @return ex bookEntity for validation
     */
    BookEntity deleteBook(BookEntity bookEntity);

}
