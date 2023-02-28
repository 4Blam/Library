package springboot;

import org.springframework.stereotype.Component;
import service.Book;

@Component
public class BookDtoMapper {
    public Book webToBook(BookDto bookDto){
    return new Book(bookDto.getId(), bookDto.getAuthor(),
            bookDto.getTitle(),
            bookDto.getPublished_in());
    }
    public BookDto bookToWeb(Book book){
        return new BookDto(book.getId(), book.getAuthor(),
                book.getTitle(),
                book.getPublished_in());
    }
}
