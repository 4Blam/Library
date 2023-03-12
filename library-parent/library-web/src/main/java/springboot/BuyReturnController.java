package springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import service.Book;
import service.BookLibraryServiceImpl;

import java.io.IOException;

@RestController
@RequestMapping("/active")
public class BuyReturnController {
    @Autowired
    private JmsTemplate jmsTemplate;
    private final BookLibraryServiceImpl bookLibraryService;
    @Autowired
    public BuyReturnController(BookLibraryServiceImpl bookLibraryService){
        this.bookLibraryService = bookLibraryService;
    }

    @GetMapping(value = "/buy/{id}", produces = "text/html")
    public ResponseEntity<String> IncreasebyId(
            @PathVariable("id") int id) throws IOException {
        Book book = bookLibraryService.getBookById(id);
        if(book.getId()>0){
            jmsTemplate.convertAndSend("boughtInPhqueue", book.getPublished_in());
            return new ResponseEntity<String>("PHID(where sold) = " + book.getPublished_in(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value = "/return/{id}")
    public ResponseEntity<String> DecreaseById(
            @PathVariable("id") int id) throws IOException {
        Book book = bookLibraryService.getBookById(id);
        if(book.getId()>0){
            jmsTemplate.convertAndSend("returnedInPhqueue", book.getPublished_in());
            return new ResponseEntity<String>("PHID(where returned) = " + book.getPublished_in(), HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
