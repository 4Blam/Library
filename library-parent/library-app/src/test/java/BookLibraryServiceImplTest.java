import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.BookEntity;
import repository.BookRepositoryImpl;
import service.Book;
import service.BookLibraryServiceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookLibraryServiceImplTest {
    private BookLibraryServiceImpl service;
    @Mock
    private BookRepositoryImpl impl;
    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
        service = new BookLibraryServiceImpl(impl);
    }
    @Test
    public void getAllBooks_returnBooks() {
        List<BookEntity> entities = new ArrayList<>();

        List<Book> books = new ArrayList<>();

        when(impl.selectAllBooks()).thenReturn(entities);

        assertEquals(books, service.getAllBooks());
    }
    @Test
    public void getAllBooks_returnsEmptyList_whenBooksDontExist() {
        List<BookEntity> entities = new ArrayList<BookEntity>();

        when(impl.selectAllBooks()).thenReturn(entities);

        assertEquals(Collections.EMPTY_LIST, service.getAllBooks());
    }
    @Test
    public void getBooksByAuthor_returnBooks() {
        List<BookEntity> entities = new ArrayList<>();
        BookEntity emptyEntity = new BookEntity();

        List<Book> books = new ArrayList<>();

        when(impl.selectBooksByAuthor(emptyEntity)).thenReturn(entities);

        assertEquals(books, service.getBooksByAuthor(""));
    }
    @Test
    public void getBooksByAuthor_returnsEmptyList_whenBooksDontExist() {
        List<BookEntity> entities = new ArrayList<BookEntity>();
        BookEntity emptyEntity = new BookEntity();

        when(impl.selectBooksByAuthor(emptyEntity)).thenReturn(entities);

        assertEquals(Collections.EMPTY_LIST, service.getBooksByAuthor(""));
    }@Test
    public void getBookByTitle_returnBooks() {
        List<BookEntity> entities = new ArrayList<BookEntity>();
        BookEntity emptyEntity = new BookEntity();

        List<Book> books = new ArrayList<Book>();

        when(impl.selectBookByTitle(emptyEntity)).thenReturn(entities);

        assertEquals(books, service.getAllBooks());
    }
    @Test
    public void getBookByTitle_returnsEmptyList_whenBookDoesntExist() {
        List<BookEntity> entities = new ArrayList<BookEntity>();
        BookEntity emptyEntity = new BookEntity();

        when(impl.selectBookByTitle(emptyEntity)).thenReturn(entities);

        assertEquals(Collections.EMPTY_LIST, service.getBookByTitle(""));
    }


}
