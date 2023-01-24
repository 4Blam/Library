import org.junit.Test;
import repository.BookEntity;
import service.Book;
import service.BookMapper;

import static org.junit.Assert.assertEquals;

public class BookMapperTest {
    BookMapper mapper = new BookMapper();

    @Test
    public void bookToEntity_transformsCorrectly() {
        Book book = new Book("author", "title", 1111);
        BookEntity entity = new BookEntity("author", "title", 1111);

        assertEquals(entity, mapper.bookToEntity(book));
    }

    @Test
    public void entityToBook_transformsCorrectly() {
        BookEntity entity = new BookEntity("author", "title", 1111);
        Book book = new Book("author", "title", 1111);

        assertEquals(book, mapper.entityToBook(entity));
    }
}
