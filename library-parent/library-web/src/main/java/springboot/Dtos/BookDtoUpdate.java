package springboot.Dtos;

public class BookDtoUpdate extends BookDtoCreate {
    /**
    * ID of a publishing house where book was published
     */
    private long bookID;
    public BookDtoUpdate(){}
    public BookDtoUpdate(String author, String title, long phID, long bookID) {
        super(author, title, phID);
        this.bookID = bookID;
    }

    public long getBookID() {
            return bookID;
        }
    public void setBookID(long bookID) {
            this.bookID = bookID;
        }

}
