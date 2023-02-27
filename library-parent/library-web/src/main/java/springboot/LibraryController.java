package springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;


import java.util.List;
@RestController
@Slf4j
public class LibraryController {
    private final LibraryWebCore libraryWebCore;
    @Autowired
    public LibraryController(LibraryWebCore libraryWebCore){
        this.libraryWebCore = libraryWebCore;
    }
    @GetMapping("/Books")
    public List<BookWeb> getAllBooks() { return libraryWebCore.getAllBooks(); }
    @GetMapping("/Books/Id")
    public List<BookWeb> getBookById(@RequestParam(required = true) int id){
        return libraryWebCore.getBookById(id);
    }
    @GetMapping("/Books/Author")
    public List<BookWeb> getBooksByAuthor(@RequestParam(required = true) String author){
        return libraryWebCore.getBooksByAuthor(author);
    }
    @GetMapping("/Books/Title")
    public List<BookWeb> getBooksByTitle(@RequestParam(required = true) String title){
        return libraryWebCore.getBookByTitle(title);
    }
    @PutMapping("/Books/Author")
    public String updateAuthor(@RequestParam(required = true) int id,
                               @RequestParam(required = true) String author){
        libraryWebCore.updateBookById(id, "author", author);
        return "Successfully updated book with id = " + id + ", changed author to " + author;
    }
    @PutMapping("/Books/Title")
    public String updateTitle(@RequestParam(required = true) int id,
                               @RequestParam(required = true) String title){
        libraryWebCore.updateBookById(id, "title", title);
        return "Successfully updated book with id = " + id + ", changed title to " + title;
    }
    @PutMapping("/Books/PublishedIn")
    public String updatePublished_in(@RequestParam(required = true) int id,
                               @RequestParam(required = true) int published_in){
        libraryWebCore.updateBookById(id, "published_in", "" + published_in);
        return "Successfully updated book with id = " + id + ", changed published_in to " + published_in;
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/Books")
    public String insert(@RequestParam(required = true) String title,
                               @RequestParam(required = true) String author,
                               @RequestParam(required = true) int published_in ){
        libraryWebCore.insertBook(title, author, published_in);
        return "Successfully inserted a book with title = " + title + ", author = " + author + ", published in = " + published_in;
    }
    @DeleteMapping("/Books/Id")
    public String delete(@RequestParam(required = true) int id){
        libraryWebCore.deleteBookById(id);
        return "Successfully deleted a book with id = " + id;
    }
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(RuntimeException.class)
    public RedirectView handleError(Exception ex) {
        log.error(ex.getMessage());
        return new RedirectView("error");
    }
}