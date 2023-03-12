package springboot;

public class BookDtoUpdate extends BookDtoCreate {
    /**
    * ID of a publishing house where book was published
     */
    private Integer bookID;
    public BookDtoUpdate(){}
    public BookDtoUpdate(String author, String title, Integer phID, Integer bookID) {
        super(author, title, phID);
        this.bookID = bookID;
    }

    public Integer getBookID() {
            return bookID;
        }
    public void setBookID(Integer bookID) {
            this.bookID = bookID;
        }

}
