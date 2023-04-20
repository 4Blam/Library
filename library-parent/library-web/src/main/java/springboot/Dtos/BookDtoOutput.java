package springboot.Dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "BookDto that is shown to user (with inserted phName instead of phID, taken from another service)")
public class BookDtoOutput extends BookDto{
    /**
    * ID of a book
     */
    @NotNull
    @Size(min = 36, max = 36, message = "UUID size is 36")
    @Schema(description = "Book's ID(UUID) in database")
    String bookID;
    /**
     * Name of a Publishing house where book was published
     */
    @NotNull
    @Schema(description = "Name of a Publishing house where book was published")
    String phName;
    public BookDtoOutput(){}

    public BookDtoOutput(String bookID, String author, String title, String phName) {
        super(author, title);
        this.bookID = bookID;
        this.phName = phName;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getPhName() {
        return phName;
    }

    public void setPhName(String phName) {
        this.phName = phName;
    }
}
