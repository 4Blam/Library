package service;

import org.springframework.stereotype.Component;
import repository.BookEntity;

/**
 * Mapper, that transforms book's entity (repository level) to book (service level) and vice versa
 */
@Component
public class BookMapper {
    public BookEntity bookToEntity(Book book){
        return new BookEntity(book.getId(), book.getAuthor(),
                book.getTitle(),
                book.getPublished_in());
    }
    public Book entityToBook(BookEntity bookEntity){
        return new Book(bookEntity.getId(), bookEntity.getAuthor(),
                bookEntity.getTitle(),
                bookEntity.getPublished_in());
    }
    public Book webToBook(BookWeb bookWeb){
        return new Book(bookWeb.getId(), bookWeb.getAuthor(),
                bookWeb.getTitle(),
                bookWeb.getPublished_in());
    }
    public BookWeb bookToWeb(Book book){
        return new BookWeb(book.getId(), book.getAuthor(),
                book.getTitle(),
                book.getPublished_in());
    }
}
