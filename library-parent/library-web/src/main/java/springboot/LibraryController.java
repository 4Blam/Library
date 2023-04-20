package springboot;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    @Operation(
            summary = "Get all books from library",
            description = "Get JSON array of all books that are stored in library database"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully returned all books from library",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = BookDtoOutput.class)
                            )),
                    @ApiResponse(
                            responseCode = "503",
                            description = "Couldn't connect to Publishing house service"
                    )
            }
    )
    public ResponseEntity<List<BookDtoOutput>> getAllBooks() {
        return new ResponseEntity<>(
                libraryWebCore.getAllBooks(),
                HttpStatus.OK);
    }
    @GetMapping(value = "/books/{id}")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully returned book by id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = BookDtoOutput.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "503",
                            description = "Couldn't connect to Publishing house service"
                    )
            }
    )
    @Operation(
            summary = "Get book by ID",
            description = "Get specific book with given id from library database"
    )
    public ResponseEntity<BookDtoOutput> getBookById(
            @PathVariable("id") @Size(min = 36, max = 36, message = "UUID size is 36") String id){
        return new ResponseEntity<>(
                libraryWebCore.getBookById(id),
                HttpStatus.OK);
    }
    @PutMapping("/books")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated book by id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = BookDtoOutput.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "503",
                            description = "Couldn't connect to Publishing house service"
                    )
            }
    )
    @Operation(
            summary = "Update info about a book",
            description = "Update information about a book stored in library database by given id"
    )
    public ResponseEntity<BookDtoOutput> update(BookDtoUpdate bookDto){
        libraryWebCore.updateBookByDto(bookDto);
        return new ResponseEntity<>(
                libraryWebCore.getBookById(bookDto.getBookID()),
                HttpStatus.OK);
    }
    @PostMapping("/books")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully returned book by id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = BookDtoOutput.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "503",
                            description = "Couldn't connect to Publishing house service"
                    )
            }
    )
    @Operation(
            summary = "Create a new book",
            description = "Create a new book in library database, all fields are needed"
    )
    public ResponseEntity<BookDtoOutput> insert(BookDtoCreate bookDto){
        return new ResponseEntity<>(
                libraryWebCore.insertBook(bookDto.getTitle(),bookDto.getAuthor(),bookDto.getPhID()),
                HttpStatus.CREATED);
    }
    @Schema(description = "ResultDto that returns result of deleting")
    class BooleanResultDto {
        public boolean result = true;
    }
    @DeleteMapping("/books/{id}")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully deleted book by id",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = BooleanResultDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "503",
                            description = "Couldn't connect to Publishing house service"
                    )
            }
    )
    @Operation(
            summary = "Delete a book",
            description = "Delete a book from library database by given id"
    )
    public ResponseEntity<BooleanResultDto> delete(
            @PathVariable("id") @Size(min = 36, max = 36, message = "UUID size is 36") String id){

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