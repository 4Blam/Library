package service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BookEntity;
import repository.BookRepositoryImpl;

/**
 * Service-level class, where we get info by requests from repository-level and send it back
 * to Web-level
 */

@Service
public class BookLibraryServiceImpl implements BookLibraryService {
    private final BookRepositoryImpl bookRepositoryImpl;
    private final BookMapper bookMapper;
    @Autowired
    public BookLibraryServiceImpl(BookMapper bookMapper, BookRepositoryImpl bookRepositoryImpl){
        this.bookRepositoryImpl = bookRepositoryImpl;
        this.bookMapper = bookMapper;
    }
    public BookLibraryServiceImpl(BookRepositoryImpl impl) {
        this.bookRepositoryImpl = impl;
        this.bookMapper = new BookMapper();
    }

    @NotNull
    public List<Book> getAllBooks() {
        List<BookEntity> entities;
        List<Book> books = new ArrayList<>();

        entities = bookRepositoryImpl.selectAllBooks();

        for (BookEntity e : entities){
            books.add(bookMapper.entityToBook(e));
        }

        if(books.isEmpty()){
            return Collections.EMPTY_LIST;
        }

        return books;
    }
    @NotNull
    public List<Book> getBookById(int id) {
        Book book = new Book();
        book.setId(id);
        List<BookEntity> entities;
        List<Book> books = new ArrayList<>();

        entities = bookRepositoryImpl.selectBookById(bookMapper.bookToEntity(book));

        for (BookEntity e : entities){
            books.add(bookMapper.entityToBook(e));
        }

        if(books.isEmpty()){
            return Collections.EMPTY_LIST;
        }

        return books;

    }
    @NotNull
    public List<Book> getBooksByAuthor(String author) {
        Book book = new Book();
        book.setAuthor(author);
        List<BookEntity> entities;
        List<Book> books = new ArrayList<>();

        entities = bookRepositoryImpl.selectBooksByAuthor(bookMapper.bookToEntity(book));

        for (BookEntity e : entities){
            books.add(bookMapper.entityToBook(e));
        }

        if(books.isEmpty()){
            return Collections.EMPTY_LIST;
        }

        return books;
    }
    @NotNull
    public List<Book> getBookByTitle(String title) {
        Book book = new Book();
        book.setTitle(title);
        List<BookEntity> entities;
        List<Book> books = new ArrayList<>();

        entities = bookRepositoryImpl.selectBookByTitle(bookMapper.bookToEntity(book));

        for (BookEntity e : entities){
            books.add(bookMapper.entityToBook(e));
        }

        if(books.isEmpty()){
            return Collections.EMPTY_LIST;
        }

        return books;

    }
    public void insertBook(String title, String author, int year){
        Book book = new Book(0, author, title, year);
        BookMapper mapper = new BookMapper();
        bookRepositoryImpl.insertBook(mapper.bookToEntity(book));
    }
    public void deleteBookById(int id){
        Book book = new Book(id, "", "", 0);
        BookMapper mapper = new BookMapper();
        BookEntity entity = mapper.bookToEntity(book);
        bookRepositoryImpl.deleteBook(entity);
    }
    public void updateBookById(int id, String field, String value){
        Book book = new Book(id, "", "", 0);
        if(field.equals("author")) {
            book.setAuthor(value);
        }
        if(field.equals("title")) {
            book.setTitle(value);
        }
        if(field.equals("published_in")){
            book.setPublished_in(Integer.parseInt(value));
        }
        BookMapper mapper = new BookMapper();
        BookEntity entity = mapper.bookToEntity(book);
        bookRepositoryImpl.updateBook(entity, field);
    }
}
