package springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.Book;
import service.BookLibraryService;

import java.io.IOException;

@RestController
@RequestMapping("/active")
public class BuyReturnController {
    @Autowired
    private JmsTemplate jmsTemplate;
    private final BookLibraryService bookLibraryService;
    @Autowired
    public BuyReturnController(BookLibraryService bookLibraryService){
        this.bookLibraryService = bookLibraryService;
    }

    @GetMapping(value = "/buy/{id}", produces = "text/html")
    public ResponseEntity<String> IncreasebyId(
            @PathVariable("id") String id) throws IOException {
        Book book = bookLibraryService.getBookById(id);
        jmsTemplate.convertAndSend("boughtInPhQueue", book.getPublished_in());
        return new ResponseEntity<String>("PHID(where bought) = " + book.getPublished_in(), HttpStatus.OK);

    }

    @GetMapping(value = "/return/{id}")
    public ResponseEntity<String> DecreaseById(
            @PathVariable("id") String id) throws IOException {
        Book book = bookLibraryService.getBookById(id);
        jmsTemplate.convertAndSend("returnedInPhQueue", book.getPublished_in());
        return new ResponseEntity<String>("PHID(where returned) = " + book.getPublished_in(), HttpStatus.OK);
    }
}
