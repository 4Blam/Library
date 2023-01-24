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
     * Performs actual select statement in our database and returns all books
     * with given author
     * @param bookEntity bookEntity that contains only author (title, year are null and 0)
     * @return ArrayList of bookEntities, that has the same author as bookEntity param
     */
    List<BookEntity> selectBooksByAuthor(BookEntity bookEntity);
    /**
     * Performs actual select statement in our database and returns all books
     * with given title
     * @param bookEntity bookEntity that contains only title (author, year are null and 0)
     * @return ArrayList of bookEntities, that has the same title as bookEntity param
     */
    List<BookEntity> selectBookByTitle(BookEntity bookEntity);
    /**
     * Performs a book insertion into our database
     * @param bookEntity bookEntity that we will insert into database
     * @return inserted bookEntity for validation
     */
    BookEntity insertBook(BookEntity bookEntity);
}