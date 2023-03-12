package springboot;

import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import service.Book;
import service.BookLibraryServiceImpl;

import java.util.ArrayList;
import java.util.List;

@Component
public class LibraryWebCore {
    private final BookLibraryServiceImpl bookLibraryServiceImpl;
    private final BookDtoMapper bookDtoMapper;

    @Autowired
    public LibraryWebCore(BookLibraryServiceImpl bookLibraryServiceImpl, BookDtoMapper bookDtoMapper){
        this.bookLibraryServiceImpl = bookLibraryServiceImpl;
        this.bookDtoMapper = bookDtoMapper;
    }

    public List<BookDtoOutput> getAllBooks(){
        List<Book> books = bookLibraryServiceImpl.getAllBooks();
        List<BookDtoOutput> webbooks = new ArrayList<>();
        for (Book b: books){
            webbooks.add(bookDtoMapper.bookToBookDtoOutput(b));
        }
        return webbooks;
    }
    public BookDtoOutput getBookById(int id){
        Book book = bookLibraryServiceImpl.getBookById(id);

        return bookDtoMapper.bookToBookDtoOutput(book);
    }
    public void updateBookByDto(BookDtoUpdate bookDto){
        if(bookDto.getTitle()!=null) {
            bookLibraryServiceImpl.updateBookById(bookDto.getBookID(), "title", bookDto.getTitle());
        }
        if(bookDto.getAuthor()!=null){
            bookLibraryServiceImpl.updateBookById(bookDto.getBookID(), "author", bookDto.getAuthor());
        }
        if(bookDto.getPhID()!=null){
            bookLibraryServiceImpl.updateBookById(bookDto.getBookID(), "published_in", String.valueOf(bookDto.getPhID()));
        }
    }
    public BookDtoOutput insertBook(String title, String author, int publishedIn) {
        BookDtoOutput bdto = bookDtoMapper.bookToBookDtoOutput(bookLibraryServiceImpl.insertBook(title, author, publishedIn));
        bdto = bookDtoMapper.bookToBookDtoOutput(bookLibraryServiceImpl.getBookById(bdto.getBookID()));
        return bdto;
    }

    public void deleteBookById(int id) {
        bookLibraryServiceImpl.deleteBookById(id);
    }
}
