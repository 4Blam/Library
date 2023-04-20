package springboot.Dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "BookDto that is used for updating data")
public class BookDtoUpdate {
    @NotNull
    @Size(min = 36, max = 36, message = "UUID size is 36")
    @Schema(description = "ID(UUID) of a book that is about to update")
    private String bookID;
    @Schema(description = "Book's title")
    @Nullable
    @Size(min = 5, message = "Too short")
    @Size(max = 100, message = "Too large")
    private String title;
    @Schema(description = "Book's author")
    @Nullable
    @Size(min = 5, message = "Too short")
    @Size(max = 100, message = "Too large")
    private String author;
    @Min(1)
    @Schema(description = "ID of a publishing house, where book was published")
    private long phID;

    public BookDtoUpdate(){}

    public BookDtoUpdate(String bookID, String title, String author, long phID) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.phID = phID;
    }

    public String getBookID() {
            return bookID;
        }
    public void setBookID(String bookID) {
            this.bookID = bookID;
        }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getPhID() {
        return phID;
    }

    public void setPhID(long phID) {
        this.phID = phID;
    }
}
