package springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    @GetMapping("/getAllBooks")
    public List<BookWeb> getAllBooks() { return libraryWebCore.getAllBooks(); }
    @GetMapping("/getBookById")
    public List<BookWeb> getBookById(@RequestParam(required = true) int id){
        return libraryWebCore.getBookById(id);
    }
    @GetMapping("/getBooksByAuthor")
    public List<BookWeb> getBooksByAuthor(@RequestParam(required = true) String author){
        return libraryWebCore.getBooksByAuthor(author);
    }
    @GetMapping("/getBookByTitle")
    public List<BookWeb> getBooksByTitle(@RequestParam(required = true) String title){
        return libraryWebCore.getBookByTitle(title);
    }
    @GetMapping("/updateAuthor")
    public String updateAuthor(@RequestParam(required = true) int id,
                               @RequestParam(required = true) String author){
        libraryWebCore.updateBookById(id, "author", author);
        return "Successfully updated book with id = " + id + ", changed author to " + author;
    }
    @GetMapping("/updateTitle")
    public String updateTitle(@RequestParam(required = true) int id,
                               @RequestParam(required = true) String title){
        libraryWebCore.updateBookById(id, "title", title);
        return "Successfully updated book with id = " + id + ", changed title to " + title;
    }
    @GetMapping("/updatePublished_in")
    public String updatePublished_in(@RequestParam(required = true) int id,
                               @RequestParam(required = true) int published_in){
        libraryWebCore.updateBookById(id, "published_in", "" + published_in);
        return "Successfully updated book with id = " + id + ", changed published_in to " + published_in;
    }
    @GetMapping("/insertBook")
    public String insert(@RequestParam(required = true) String title,
                               @RequestParam(required = true) String author,
                               @RequestParam(required = true) int published_in ){
        libraryWebCore.insertBook(title, author, published_in);
        return "Successfully inserted a book with title = " + title + ", author = " + author + ", published in = " + published_in;
    }
    @GetMapping("/deleteBook")
    public String delete(@RequestParam(required = true) int id){
        libraryWebCore.deleteBookById(id);
        return "Successfully deleted a book with id = " + id;
    }
    @ExceptionHandler(RuntimeException.class)
    public RedirectView handleError(Exception ex) {
        log.error(ex.getMessage());
        return new RedirectView("error");
    }
}