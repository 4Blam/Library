package springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


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
    public ResponseEntity<List<BookDto>> getAllBooks() { return new ResponseEntity<>(libraryWebCore.getAllBooks(), HttpStatus.OK); }
    @GetMapping(value = "/{id}")
    public ResponseEntity<List<BookDto>> getBookById(
            @PathVariable("id") int id){

        List<BookDto> bookDtos = libraryWebCore.getBookById(id);
        if(bookDtos.size()!=0){
            return new ResponseEntity<>(bookDtos, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping(value = "/author/{author}")
    public ResponseEntity<List<BookDto>> getBooksByAuthor(
            @PathVariable("author") String author){

        List<BookDto> bookDtos = libraryWebCore.getBooksByAuthor(author);
        if(bookDtos.size()!=0){
            return new ResponseEntity<>(bookDtos, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    @GetMapping("/title/{title}")
    public ResponseEntity<List<BookDto>> getBooksByTitle(
            @PathVariable("title") String title){

        List<BookDto> bookDtos = libraryWebCore.getBookByTitle(title);
        if(bookDtos.size()!=0){
            return new ResponseEntity<>(bookDtos, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    @PutMapping("/{id}/author/{author}")
    public ResponseEntity<List<BookDto>> updateAuthor(
            @PathVariable("id") int id,
            @PathVariable("author") String author){

        libraryWebCore.updateBookById(id, "author", author);
        List<BookDto> bookDtos = libraryWebCore.getBookById(id);
        if(bookDtos.size()!=0){
            return new ResponseEntity<>(bookDtos, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    @PutMapping("/{id}/title/{title}")
    public ResponseEntity<List<BookDto>> updateTitle(
            @PathVariable("id") int id,
            @PathVariable("title") String title){

        libraryWebCore.updateBookById(id, "title", title);
        List<BookDto> bookDtos = libraryWebCore.getBookById(id);
        if(bookDtos.size()!=0){
            return new ResponseEntity<>(bookDtos, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    @PutMapping("/{id}/published_in/{published_in}")
    public ResponseEntity<List<BookDto>>  updatePublished_in(
            @PathVariable("id") int id,
            @PathVariable("published_in") int published_in){

        libraryWebCore.updateBookById(id, "published_in", "" + published_in);

        List<BookDto> bookDtos = libraryWebCore.getBookById(id);
        if(bookDtos.size()!=0){
            return new ResponseEntity<>(bookDtos, HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public ResponseEntity<List<BookDto>> insert(
            @RequestParam() String title,
            @RequestParam() String author,
            @RequestParam() int published_in ){

        libraryWebCore.insertBook(title, author, published_in);
        List<BookDto> bookDtos = libraryWebCore.getBookByTitle(title); //to change?
        if(bookDtos.size()!=0){
            return new ResponseEntity<>(bookDtos, HttpStatus.CREATED);
        } else{
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(
            @PathVariable("id") int id){
        class BooleanResultDto {
                public boolean result = false;
        }
        BooleanResultDto booleanResultDto = new BooleanResultDto();
        List<BookDto> bookDtosOld = libraryWebCore.getBookById(id);

        libraryWebCore.deleteBookById(id);

        if(bookDtosOld.size() == 0){
            return new ResponseEntity<>(booleanResultDto, HttpStatus.NO_CONTENT);
        } else {
            booleanResultDto.result = true;
            return new ResponseEntity<>(booleanResultDto, HttpStatus.OK);
        }
    }
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(RuntimeException.class)
    public RedirectView handleError(Exception ex) {
        log.error(ex.getMessage());
        return new RedirectView("error");
    }
}