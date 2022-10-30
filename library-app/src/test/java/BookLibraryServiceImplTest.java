import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.MockingDetails;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookLibraryServiceImplTest {
    BookLibraryServiceImpl service;
    @Mock
    BookRepositoryImpl impl;
    @Before
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void getAllBooks_returnBooks() throws SQLException {
        System.out.println("TEST getAllBooks_returnBooks is executed");
        service = new BookLibraryServiceImpl(impl);

        List<BookEntity> entities = new ArrayList<BookEntity>();
        BookEntity entity = new BookEntity("testName", "testTitle", 1111);
        entities.add(entity);

        List<Book> books = new ArrayList<Book>();

        when(impl.selectAllBooks()).thenReturn(entities);

        assertEquals(service.getAllBooks().getClass(), books.getClass());
    }
    @Test
    public void getAllBooks_returnsEmptyList_whenBooksDontExist() throws SQLException {
        System.out.println("TEST getAllBooks_returnsEmptyList_whenBooksDontExist is executed");
        service = new BookLibraryServiceImpl(impl);

        List<BookEntity> entities = new ArrayList<BookEntity>();

        when(impl.selectAllBooks()).thenReturn(entities);

        assertEquals(service.getAllBooks(), Collections.EMPTY_LIST);
    }
    @Test(expected = NullPointerException.class)
    public void getAllBooks_throwsException_whenProblemWithAccess(){
        System.out.println("TEST getAllBooks_throwsException_whenProblemWithAccess is executed");
        service = new BookLibraryServiceImpl(null);

        service.getAllBooks();
    }
}
