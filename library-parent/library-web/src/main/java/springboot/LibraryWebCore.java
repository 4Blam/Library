package springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.Book;
import service.BookLibraryService;
import springboot.Dtos.BookDtoOutput;
import springboot.Dtos.BookDtoUpdate;

import java.util.List;
import java.util.UUID;

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
    public BookDtoOutput getBookById(String id){
        return bookDtoTransformer.bookToBookDtoOutput(
                bookLibraryService.getBookById(id),
                bookLibraryService.getPHInfo());
    }
    public void updateBookByDto(BookDtoUpdate bookDto){
        if(bookDto.getPhID()!=0){
            //Testing if there's a ph with given id
            if(bookLibraryService.getPHInfo().get(bookDto.getPhID()) == null){
                throw new IllegalArgumentException("User tried to insert incorrect PHID");
            }
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
        if(bookLibraryService.getPHInfo().get(publishedIn) == null){
            throw new IllegalArgumentException("User tried to insert incorrect PHID");
        }
        //Inserting
        return bookDtoTransformer.bookToBookDtoOutput(
                bookLibraryService.insertBook(title, author, publishedIn),
                bookLibraryService.getPHInfo());
    }

    public void deleteBookById(String id) {
        bookLibraryService.deleteBookById(id);
    }
}
