package springboot.Dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Schema(description = "BookDto that is used for book creation")
public class BookDtoCreate extends BookDto{
    @Min(1)
    @NotNull
    @Schema(description = "ID of a publishing house, where book was published")
    private long phID;
    public BookDtoCreate(){}
    public BookDtoCreate(String author, String title, long phID) {
        super(author, title);
        this.phID = phID;
    }

    public long getPhID() {
        return phID;
    }

    public void setPhID(long phID) {
        this.phID = phID;
    }
}
