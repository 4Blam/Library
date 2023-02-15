package springboot;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import service.BookLibraryServiceImpl;
import service.BookWeb;

import java.util.ArrayList;
import java.util.List;
@RestController
@Slf4j
public class LibraryController {
    private final BookLibraryServiceImpl bookLibraryServiceImpl;
    @Autowired
    public LibraryController(BookLibraryServiceImpl bookLibraryServiceImpl){
        this.bookLibraryServiceImpl = bookLibraryServiceImpl;
    }
    @GetMapping("/")
    public String mainPage(){
            return CreatedPages.mainPageHTML();
    }
    @GetMapping("/getAllBooks")
    public String getAllBooks() {
        List<BookWeb> books;
         books = bookLibraryServiceImpl.getAllBooks();
         StringBuilder result = new StringBuilder();
         for(BookWeb book : books){
            result.append(book.toString()).append("<br>");
         }
         return result.toString();
    }
    @GetMapping("/getBookById")
    public String getBooksId(){
        return CreatedPages.startGettingIdHTML();
    }
    @GetMapping("/getBookById/")
    public String getBookById(@RequestParam(required = true) int id){
        List<BookWeb> books = new ArrayList<>();
        books = bookLibraryServiceImpl.getBookById(id);
        StringBuilder result = new StringBuilder();
        if(books.size() == 0){
            result.append("There is no book with id = " + id +"<br>");
        } else{
            for(BookWeb book : books){
                result.append(book.toString()).append("<br>");
            }}
        result.append("<button onclick=\"location.href = 'http://localhost:8080/';\" id=\"myButton\" class=\"float-left submit-button\" >Return home</button>\n");
        return result.toString();
    }
    @GetMapping("/getBooksByAuthor")
    public String getBooksAuthor(){
        return CreatedPages.startGettingAuthorHTML();
    }
    @GetMapping("/getBooksByAuthor/")
    public String getBooksByAuthor(@RequestParam(required = true) String author){
        List<BookWeb> books = new ArrayList<>();
        books = bookLibraryServiceImpl.getBooksByAuthor(author);
        StringBuilder result = new StringBuilder();
        if(books.size()==0){
           result.append("There's no books with author = '" + author + "'<br>");
        }else{
            for(BookWeb book : books){
                result.append(book.toString()).append("<br>");
            }
        }
        result.append("<button onclick=\"location.href = 'http://localhost:8080/';\" id=\"myButton\" class=\"float-left submit-button\" >Return home</button>\n");
        return result.toString();
    }

    @GetMapping("/getBookByTitle")
    public String getBooksTitle(){
        return CreatedPages.startGettingTitleHTML();
    }
    @GetMapping("/getBookByTitle/")
    public String getBooksByTitle(@RequestParam(required = true) String title){
        List<BookWeb> books = new ArrayList<>();
        books = bookLibraryServiceImpl.getBookByTitle(title);
        StringBuilder result = new StringBuilder();
        if(books.size()==0) {
            result.append("There's no book with title = '" + title + "'<br>");
        }else {
            for (BookWeb book : books) {
                result.append(book.toString()).append("<br>");
            }
        }
        result.append("<button onclick=\"location.href = 'http://localhost:8080/';\" id=\"myButton\" class=\"float-left submit-button\" >Return home</button>\n");
        return result.toString();
    }
    @GetMapping("/updateField")
    public String updateField(){
        return CreatedPages.updateHtml();
    }
    @GetMapping("/updateAuthor")
    public String beginAutUpdate(){return CreatedPages.updateAuthorHtml();}
    @GetMapping("/updateAuthor/")
    public RedirectView updateAuthor(@RequestParam(required = true) int id,
                               @RequestParam(required = true) String author){
        bookLibraryServiceImpl.updateBookById(id, "author", author);
        return new RedirectView("updatedSuccessfully");
    }
    @GetMapping("/updateTitle")
    public String beginTitleUpdate(){return CreatedPages.updateTitleHtml();}
    @GetMapping("/updateTitle/")
    public RedirectView updateTitle(@RequestParam(required = true) int id,
                               @RequestParam(required = true) String title){
        bookLibraryServiceImpl.updateBookById(id, "title", title);
        return new RedirectView("updatedSuccessfully");
    }
    @GetMapping("/updatePublished_in")
    public String beginPubUpdate(){return CreatedPages.updatePublishedInHtml();}
    @GetMapping("/updatePublished_in/")
    public RedirectView updatePublished_in(@RequestParam(required = true) int id,
                               @RequestParam(required = true) int published_in){
        bookLibraryServiceImpl.updateBookById(id, "published_in", "" + published_in);
        return new RedirectView("updatedSuccessfully");
    }
    @GetMapping("*/updatedSuccessfully")
    public String endUpdate(){
        return CreatedPages.successUpdate();
    }
    @GetMapping("/insertBook")
    public String beginInsertion(){
        return CreatedPages.insertPageHTML();
    }
    @GetMapping("/insertBook/")
    public RedirectView insert(@RequestParam(required = true) String title,
                               @RequestParam(required = true) String author,
                               @RequestParam(required = true) int published_in ){
        bookLibraryServiceImpl.insertBook(title, author, published_in);

        return new RedirectView("insertedSuccessfully");
    }
    @GetMapping("/insertBook/insertedSuccessfully")
    public String endInsertion(){
        return CreatedPages.successInsert();
    }
    @GetMapping("/deleteBook")
    public String startDeleting(){
        return CreatedPages.deletePageHTML();
    }
    @GetMapping("/deleteBook/")
    public RedirectView delete(@RequestParam(required = true) int id){
        bookLibraryServiceImpl.deleteBookById(id);
        return new RedirectView("deletedSuccessfully");
    }
    @GetMapping("/deleteBook/deletedSuccessfully")
    public String endDeleting(){ return CreatedPages.successDelete();}
    @ExceptionHandler(RuntimeException.class)
    public RedirectView handleError(Exception ex) {
        log.error(ex.getMessage());
        return new RedirectView("error");
    }
}