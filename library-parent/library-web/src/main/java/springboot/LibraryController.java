package springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.view.RedirectView;
import service.Book;


import java.net.ConnectException;
import java.util.Collections;
import java.util.List;
@RestController
@Slf4j
@RequestMapping("/books")
public class LibraryController {
    private final LibraryWebCore libraryWebCore;
    @Autowired
    public LibraryController(LibraryWebCore libraryWebCore){
        this.libraryWebCore = libraryWebCore;
    }
    @GetMapping("")
    public ResponseEntity<List<BookDtoOutput>> getAllBooks() {
        List<BookDtoOutput> webbooks = libraryWebCore.getAllBooks();
        if(webbooks.get(0).getBookID() == -503){
            return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
        }
        return new ResponseEntity<>(libraryWebCore.getAllBooks(), HttpStatus.OK);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<BookDtoOutput> getBookById(
            @PathVariable("id") int id){

        BookDtoOutput bookDtoOutput = libraryWebCore.getBookById(id);
        if(bookDtoOutput.getBookID()==0){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if(bookDtoOutput.getBookID() == -503){
            return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
        }
        return new ResponseEntity<>(bookDtoOutput, HttpStatus.OK);
    }
    @PutMapping("/")
    public ResponseEntity<BookDtoOutput> update(BookDtoUpdate bookDto){
        BookDtoOutput bookDtoOutput = libraryWebCore.getBookById(bookDto.getBookID());
        if(bookDtoOutput.getBookID()==0){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if(bookDtoOutput.getBookID() == -503){
            return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
        }
        libraryWebCore.updateBookByDto(bookDto);
        return new ResponseEntity<>(libraryWebCore.getBookById(bookDto.getBookID()), HttpStatus.OK);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public ResponseEntity<BookDtoOutput> insert(BookDtoCreate bookDto){

        BookDtoOutput bookDtoOutput = libraryWebCore.insertBook(bookDto.getTitle(),bookDto.getAuthor(),bookDto.getPhID());

        bookDtoOutput = libraryWebCore.getBookById(bookDtoOutput.getBookID());
        if(bookDtoOutput.getBookID()==0) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        if(bookDtoOutput.getBookID() == -503) {
            return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
        }
        return new ResponseEntity<>(bookDtoOutput, HttpStatus.CREATED);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(
            @PathVariable("id") int id){
        class BooleanResultDto {
                public boolean result = false;
        }
        BooleanResultDto booleanResultDto = new BooleanResultDto();
        BookDtoOutput bookDtoOutput = libraryWebCore.getBookById(id);

        libraryWebCore.deleteBookById(id);

        if(bookDtoOutput.getBookID() == 0){
            return new ResponseEntity<>(booleanResultDto, HttpStatus.NOT_FOUND);
        } else {
            booleanResultDto.result = true;
            return new ResponseEntity<>(booleanResultDto, HttpStatus.OK);
        }
    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleError(Exception ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(new ErrorDto(), HttpStatus.SERVICE_UNAVAILABLE);
    }
}