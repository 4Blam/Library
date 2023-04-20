package springboot.Dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public abstract class BookDto {
    @NotNull
    @Size(min = 5, message = "Too short")
    @Size(max = 100, message = "Too large")
    @Schema(description = "Book's title")
    private String title;
    @NotNull
    @Schema(description = "Book's author")
    @Size(min = 5, message = "Too short")
    @Size(max = 100, message = "Too large")
    private String author;
    public BookDto(){};

    public BookDto(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}