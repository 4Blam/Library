package springboot;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryErrorController implements ErrorController {
    @GetMapping("/error")
    String error() {
        return "Sorry, internal error has occured";
    }
}
