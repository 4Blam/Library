package springboot;

import org.springframework.stereotype.Component;
import service.Book;
import springboot.Dtos.BookDtoOutput;
import springboot.Dtos.BookDtoUpdate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Component
public class BookDtoTransformer {
   public List<BookDtoOutput> booksToBooksDtoOutput(List<Book> books, HashMap<Long,String> pubHousesNames){
       List<BookDtoOutput> webBooks = new ArrayList<>();
       for(Book b : books){
           if(pubHousesNames.get(b.getPublished_in()) == null){
               throw new IllegalArgumentException("User tried to insert incorrect PHID");
           }
           webBooks.add(new BookDtoOutput(b.getId().toString(), b.getAuthor(), b.getTitle(), pubHousesNames.get(b.getPublished_in())));
       }
       return webBooks;
   }
   public BookDtoOutput bookToBookDtoOutput(Book book, HashMap<Long,String> pubHousesNames){
        return new BookDtoOutput(book.getId().toString(), book.getAuthor(), book.getTitle(), pubHousesNames.get(book.getPublished_in()));
    }
}
