package springboot;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryErrorController implements ErrorController {
    @GetMapping("/error")
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    String error() {
        return "Sorry, internal error has occured";
    }
}
