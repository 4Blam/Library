package springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.Book;
import service.BookLibraryService;
import springboot.Dtos.BookDtoOutput;
import springboot.Dtos.BookDtoUpdate;

import java.util.List;

@Component
public class LibraryWebCore {
    private final BookLibraryService bookLibraryService;
    private final BookDtoTransformer bookDtoTransformer;

    @Autowired
    public LibraryWebCore(BookLibraryService bookLibraryService, BookDtoTransformer bookDtoTransformer){
        this.bookLibraryService = bookLibraryService;
        this.bookDtoTransformer = bookDtoTransformer;
    }

    public List<BookDtoOutput> getAllBooks(){
        return bookDtoTransformer.booksToBooksDtoOutput(
                bookLibraryService.getAllBooks(),
                bookLibraryService.getPHInfo());
    }
    public BookDtoOutput getBookById(long id){
        return bookDtoTransformer.bookToBookDtoOutput(
                bookLibraryService.getBookById(id),
                bookLibraryService.getPHInfo());
    }
    public void updateBookByDto(BookDtoUpdate bookDto){
        if(bookDto.getPhID()!=0){
            //Testing if there's a ph with given id
            bookDtoTransformer.bookToBookDtoOutput(new Book(0, "", "", bookDto.getPhID()), bookLibraryService.getPHInfo());
            //Updating
            bookLibraryService.updateBookById(bookDto.getBookID(), "published_in", String.valueOf(bookDto.getPhID()));
        }
        if(bookDto.getTitle()!=null) {
            bookLibraryService.updateBookById(bookDto.getBookID(), "title", bookDto.getTitle());
        }
        if(bookDto.getAuthor()!=null){
            bookLibraryService.updateBookById(bookDto.getBookID(), "author", bookDto.getAuthor());
        }
    }
    public BookDtoOutput insertBook(String title, String author, long publishedIn) {
        //Testing if there's a ph with given Id
        bookDtoTransformer.bookToBookDtoOutput(new Book(0, "", "", publishedIn), bookLibraryService.getPHInfo());
        //Inserting
        return bookDtoTransformer.bookToBookDtoOutput(
                bookLibraryService.insertBook(title, author, publishedIn),
                bookLibraryService.getPHInfo());
    }

    public void deleteBookById(long id) {
        bookLibraryService.deleteBookById(id);
    }
}
