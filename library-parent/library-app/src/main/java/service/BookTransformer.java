package service;

import org.springframework.stereotype.Component;
import repository.BookEntity;

import java.util.ArrayList;
import java.util.List;
@Component
public class BookTransformer {
    public List<BookEntity> booksToEntities(List<Book> books){
        List<BookEntity> entities = new ArrayList<>();
        for (Book b: books){
            entities.add(bookToEntity(b));
        }
        return entities;
    }
    public List<Book> entitiesToBooks(List<BookEntity> entities){
        List<Book> books = new ArrayList<>();
        for(BookEntity b : entities){
            books.add(entityToBook(b));
        }
        return books;
    }
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
}
