package service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BookEntity;
import repository.BookRepository;

/**
 * Service-level class, where we get info by requests from repository-level and send it back
 * to Web-level
 */

@Service
public class BookLibraryServiceImpl implements BookLibraryService {
    private final BookRepository bookRepository;
    private final BookTransformer bookTransformer;
    private final PubHousesService pubHousesService;
    @Autowired
    public BookLibraryServiceImpl(BookTransformer bookTransformer, BookRepository bookRepository, PubHousesService pubHousesService){
        this.bookRepository = bookRepository;
        this.bookTransformer = bookTransformer;
        this.pubHousesService = pubHousesService;
    }
    @NotNull
    public List<Book> getAllBooks() {
        List<Book> books = bookTransformer.entitiesToBooks(bookRepository.selectAllBooks());
        if(books.isEmpty()){
            return Collections.EMPTY_LIST;
        }
        return books;
    }

    @NotNull
    public Book getBookById(String id) {
        Book book = new Book();
        book.setId(UUID.fromString(id));
        book = bookTransformer.entityToBook(bookRepository.selectBookById(bookTransformer.bookToEntity(book)));
        return book;
    }
    public Book insertBook(String title, String author, long phid){
        Book book = new Book(UUID.randomUUID(), author, title, phid);
        return bookTransformer.entityToBook(bookRepository.insertBook(bookTransformer.bookToEntity(book)));
    }
    public void deleteBookById(String id){
        Book book = new Book(UUID.fromString(id), "", "", 0);
        BookEntity entity = bookTransformer.bookToEntity(book);
        bookRepository.deleteBook(entity);
    }
    public void updateBookById(String id, String field, String value){
        Book book = new Book(UUID.fromString(id), "", "", 0);
        if(field.equals("author")) {
            book.setAuthor(value);
        }
        if(field.equals("title")) {
            book.setTitle(value);
        }
        if(field.equals("published_in")){
            book.setPublished_in(Integer.parseInt(value));
        }
        BookEntity entity = bookTransformer.bookToEntity(book);
        bookRepository.updateBook(entity, field);
    }
    public HashMap<Long, String> getPHInfo(){
        return pubHousesService.getPHNames();
    }
}
