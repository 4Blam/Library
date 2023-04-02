package springboot.Dtos;

public class BookDtoOutput extends BookDto{
    /**
    * ID of a book
     */
    long bookID;
    /**
     * Name of a Publishing house where book was published
     */
    String phName;
    public BookDtoOutput(){}

    public BookDtoOutput(long bookID, String author, String title, String phName) {
        super(author, title);
        this.bookID = bookID;
        this.phName = phName;
    }

    public long getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getPhName() {
        return phName;
    }

    public void setPhName(String phName) {
        this.phName = phName;
    }
}
