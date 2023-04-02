package springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import springboot.Dtos.BookDtoCreate;
import springboot.Dtos.BookDtoOutput;
import springboot.Dtos.BookDtoUpdate;


import java.util.List;
@RestController
@Slf4j
@RequestMapping("/library")
public class LibraryController {
    private final LibraryWebCore libraryWebCore;
    @Autowired
    public LibraryController(LibraryWebCore libraryWebCore){
        this.libraryWebCore = libraryWebCore;
    }
    @GetMapping("/books")
    public ResponseEntity<List<BookDtoOutput>> getAllBooks() {
        return new ResponseEntity<>(
                libraryWebCore.getAllBooks(),
                HttpStatus.OK);
    }
    @GetMapping(value = "/books/{id}")
    public ResponseEntity<BookDtoOutput> getBookById(
            @PathVariable("id") long id){
        return new ResponseEntity<>(
                libraryWebCore.getBookById(id),
                HttpStatus.OK);
    }
    @PutMapping("/books")
    public ResponseEntity<BookDtoOutput> update(BookDtoUpdate bookDto){
        libraryWebCore.updateBookByDto(bookDto);
        return new ResponseEntity<>(
                libraryWebCore.getBookById(bookDto.getBookID()),
                HttpStatus.OK);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/books")
    public ResponseEntity<BookDtoOutput> insert(BookDtoCreate bookDto){
        return new ResponseEntity<>(
                libraryWebCore.insertBook(bookDto.getTitle(),bookDto.getAuthor(),bookDto.getPhID()),
                HttpStatus.CREATED);
    }

    class BooleanResultDto {
        public boolean result = true;
    }
    @DeleteMapping("/books/{id}")
    public ResponseEntity<BooleanResultDto> delete(
            @PathVariable("id") long id){

        //Checking if there is a book with given id
        BookDtoOutput bookDtoOutput = libraryWebCore.getBookById(id);
        //Deleting if it exists(if no, NOT_FOUND will be thrown)
        libraryWebCore.deleteBookById(id);
        BooleanResultDto booleanResultDto = new BooleanResultDto();
        return new ResponseEntity<>(
                booleanResultDto,
                HttpStatus.OK);
    }

    //Не работает с дто почему-то
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalArgumentException.class)
    public String internalError(IllegalArgumentException ex) {
        log.error(ex.getMessage());
        return "No publishing house with given id";
    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String internalError(EmptyResultDataAccessException ex) {
        log.error(ex.getMessage());
        return "Sorry, internal error has occured";
    }
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(ResourceAccessException.class)
    public String externalError(ResourceAccessException ex){
        log.error(ex.getMessage());
        return "Sorry, external error has occured";
    }
}