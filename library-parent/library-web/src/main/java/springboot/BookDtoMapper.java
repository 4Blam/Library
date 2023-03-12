package springboot;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import service.Book;

import java.util.HashMap;

@Component
public class BookDtoMapper {
    public Book bookDtoCreationToBook(BookDtoCreate bookDtoCreate){
    return new Book(-1, bookDtoCreate.getAuthor(),
            bookDtoCreate.getTitle(),
            bookDtoCreate.getPhID());
    }
    public BookDtoOutput bookToBookDtoOutput(Book book){

        int phID = book.getPublished_in();
        String phName = "";

        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Object> res = restTemplate.getForEntity("http://localhost:8042/phs/" + phID, Object.class);
            HashMap<String, String> result = (HashMap<String, String>) res.getBody();
            phName = result.get("name");
        } catch (HttpClientErrorException e){
            //404
            phName = "No publishing house with id = " + phID + ", try to change it";
        } catch (ResourceAccessException e){
            book.setId(-503);
            //throw 503
        }

        return new BookDtoOutput(book.getId(), book.getAuthor(),
                book.getTitle(),
                phName);
    }
}
